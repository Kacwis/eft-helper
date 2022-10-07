import { useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import useHttp from "../../hooks/use-http";
import LoadingSpinner from "../ui/LoadingSpinner";

import style from "./SignUpForm.module.css";

const SIGN_UP_API_URL = "http://localhost:8080/api/auth/signup";

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
	}, [status]);

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

	return (
		<div className={style.container}>
			<h1>Sign Up</h1>
			<form className={style.form} onSubmit={submitHandler}>
				<div className={style.input}>
					<label>Email</label>
					<input type="email" id="email" ref={emailInputRef} />
				</div>
				<div className={style.input}>
					<label>Username</label>
					<input type="text" id="username" ref={usernameInputRef} />
				</div>
				<div className={style.input}>
					<label>Password</label>
					<input type="password" id="password" ref={passwordInputRef} />
				</div>
				<div>
					<button type="submit">Sign up</button>
				</div>
			</form>
		</div>
	);
};

export default SignUpForm;
