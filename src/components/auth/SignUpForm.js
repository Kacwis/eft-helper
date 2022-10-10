import { useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import useHttp from "../../hooks/use-http";
import useValidation from "../../hooks/use-validation";
import LoadingSpinner from "../ui/LoadingSpinner";

import style from "./SignUpForm.module.css";

const EMAIL_REGEX =
	/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

const PASSWORD_REGEX =
	/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?~_+-=|\]).{8,32}$/i;

const SIGN_UP_API_URL = "http://localhost:8080/api/auth/signup";

const emailValidationCondition = (emailInputValue) => {
	return emailInputValue.match(EMAIL_REGEX);
};

const usernameValidationCondition = (usernameInputValue) => {
	return usernameInputValue.length === 0 ? false : true;
};

const passwordValidationCondition = (passwordInputValue) => {
	let isLengthValid =
		passwordInputValue.length > 7 && passwordInputValue.length < 32;
	let hasUppercaseLetter = passwordInputValue.match(/.*[A-Z].*/i)
		? true
		: false;
	let hasSpecialCharacter = passwordInputValue.match(
		/.*[*.!@$%^&(){}[\]:;<>,.?/~_+-=|].*/i
	)
		? true
		: false;
	return isLengthValid && hasUppercaseLetter && hasSpecialCharacter;
};

const sendSignUpRequest = async (requestData) => {
	const response = await fetch(SIGN_UP_API_URL, {
		method: "POST",
		headers: {
			"Content-type": "application/json",
			Authorization: "",
		},
		body: JSON.stringify({
			email: requestData.email,
			username: requestData.username,
			password: requestData.password,
		}),
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error(data.message || "Something went wrong!");
	}
};

const SignUpForm = (props) => {
	const emailInputRef = useRef();
	const usernameInputRef = useRef();
	const passwordInputRef = useRef();

	const {
		inputValue: emailInputValue,
		isInputTouched: isEmailInputTouched,
		isInputValid: isEmailInputValid,
		inputBlurHandler: emailInputBlurHandler,
		inputChangeHandler: emailInputChangeHandler,
		cleanInput: cleanEmailInput,
	} = useValidation(emailValidationCondition);

	const {
		inputValue: usernameInputValue,
		isInputTouched: isUsernameInputTouched,
		isInputValid: isUsernameInputValid,
		inputBlurHandler: usernameInputBlurHandler,
		inputChangeHandler: usernameInputChangeHandler,
		cleanInput: cleanUsernameInput,
	} = useValidation(usernameValidationCondition);

	const {
		inputValue: passwordInputValue,
		isInputTouched: isPasswordInputTouched,
		isInputValid: isPasswordInputValid,
		inputBlurHandler: passwordInputBlurHandler,
		inputChangeHandler: passwordInputChangeHandler,
		cleanInput: cleanPasswordInput,
	} = useValidation(passwordValidationCondition);

	const navigate = useNavigate();

	const {
		data: response,
		error,
		status,
		sendRequest,
	} = useHttp(sendSignUpRequest);

	const submitHandler = (e) => {
		e.preventDefault();
		const signUpDTO = {
			email: emailInputRef.current.value,
			username: usernameInputRef.current.value,
			password: passwordInputRef.current.value,
		};
		sendRequest(signUpDTO);
	};

	useEffect(() => {
		if (status === "completed" && !error) {
			navigate("/log-in");
		}
	}, [status, error, navigate]);

	if (error) {
		return (
			<div className={style.container}>
				<h1>Something went wrong! {error}</h1>
			</div>
		);
	}

	if (status === "pending") {
		return <LoadingSpinner />;
	}

	const getInputClassNames = (isInputValid, isInputTouched) => {
		return `${style.input} ${
			!isInputValid && isInputTouched ? style.invalid : ""
		}`;
	};

	return (
		<div className={style.container}>
			<h1>Sign Up</h1>
			<form className={style.form} onSubmit={submitHandler}>
				<div
					className={getInputClassNames(isEmailInputValid, isEmailInputTouched)}
				>
					<label>Email</label>
					<input
						type="email"
						id="email"
						ref={emailInputRef}
						value={emailInputValue}
						onChange={emailInputChangeHandler}
						onBlur={emailInputBlurHandler}
					/>
					{!isEmailInputValid && isEmailInputTouched && (
						<p>E-mail is not valid!</p>
					)}
				</div>
				<div
					className={getInputClassNames(
						isUsernameInputValid,
						isUsernameInputTouched
					)}
				>
					<label>Username</label>
					<input
						type="text"
						id="username"
						ref={usernameInputRef}
						value={usernameInputValue}
						onChange={usernameInputChangeHandler}
						onBlur={usernameInputBlurHandler}
					/>
					{!isUsernameInputValid && isUsernameInputTouched && (
						<p>Username can not be blank!</p>
					)}
				</div>
				<div
					className={getInputClassNames(
						isPasswordInputValid,
						isPasswordInputTouched
					)}
				>
					<label>Password</label>
					<input
						type="password"
						id="password"
						ref={passwordInputRef}
						value={passwordInputValue}
						onChange={passwordInputChangeHandler}
						onBlur={passwordInputBlurHandler}
					/>
					{!isPasswordInputValid && isPasswordInputTouched && (
						<div className={style["password-invalid"]}>
							<p>Password must contains atleast:</p>
							<p className={style.row}>- one uppercase character</p>
							<p className={style.row}>- one special character</p>
							<p className={style.row}>- eight characters</p>
						</div>
					)}
				</div>
				<div className={style.buttons}>
					<button
						type="submit"
						className={style.submit}
						disabled={
							!isEmailInputValid ||
							!isPasswordInputValid ||
							!isUsernameInputValid
						}
					>
						Sign up
					</button>
					<button className={style.cancel}>Cancel</button>
				</div>
			</form>
		</div>
	);
};

export default SignUpForm;
