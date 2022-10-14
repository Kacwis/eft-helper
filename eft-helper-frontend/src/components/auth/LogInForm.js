import { useRef, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useHttp from "../../hooks/use-http";
import { AuthContext } from "../../store/auth-context";
import LoadingSpinner from "../ui/LoadingSpinner";
import style from "./SignUpForm.module.css";

const LOG_IN_API_URL = "http://localhost:8080/api/auth/login";

const sendLogInRequest = async (requestData) => {
	const response = await fetch(LOG_IN_API_URL, {
		method: "POST",
		headers: {
			"Content-type": "application/json",
			Authorization: "",
		},
		body: JSON.stringify({
			username: requestData.username,
			password: requestData.password,
		}),
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error("Something went wrong!");
	}
	return {
		token: data.token,
		type: data.type,
	};
};

const LogInForm = () => {
	const usernameInputRef = useRef();
	const passwordInputRef = useRef();

	const navigate = useNavigate();

	const [username, setUsername] = useState("");
	const authCtx = useContext(AuthContext);

	const {
		data: responseData,
		status,
		error,
		sendRequest,
	} = useHttp(sendLogInRequest);

	const submitHandler = (e) => {
		e.preventDefault();
		sendRequest({
			username: usernameInputRef.current.value,
			password: passwordInputRef.current.value,
		});
		setUsername(usernameInputRef.current.value);
	};

	useEffect(() => {
		if (status === "completed" && !error) {
			console.log(responseData.token, username);
			authCtx.login(responseData.token, username);
			navigate("/home");
		}
	}, [status, error, responseData, username]);

	if (error) {
		return (
			<div>
				<h1>Something went wrong! {error}</h1>
			</div>
		);
	}

	if (status === "pending") {
		return <LoadingSpinner />;
	}

	return (
		<div className={style.container}>
			<h1>Log in</h1>
			<form className={style.form} onSubmit={submitHandler}>
				<div className={style.input}>
					<label>Username</label>
					<input id="username" type="text" ref={usernameInputRef} />
				</div>
				<div className={style.input}>
					<label>Password</label>
					<input id="password" type="password" ref={passwordInputRef} />
				</div>
				<div>
					<button type="submit">Log in</button>
				</div>
			</form>
		</div>
	);
};

export default LogInForm;
