import React, { useState } from "react";

export const AuthContext = React.createContext({
	token: "",
	isLoggedIn: false,
	username: "",
	login: (token, username) => {},
	logout: () => {},
});

export const AuthContextProvider = (props) => {
	const [token, setToken] = useState(null);
	const [isUserLoggedIn, setIsUserLoggedIn] = useState(false);
	const [username, setUsername] = useState("");

	const loginHandler = (token, username) => {
		setToken(token);
		setIsUserLoggedIn(true);
		setUsername(username);
		console.log("loggedIn");
	};

	const logoutHandler = () => {
		setToken(null);
		setIsUserLoggedIn(false);
		setUsername("");
		console.log("loggedOut");
	};

	const contextValue = {
		token: token,
		isLoggedIn: isUserLoggedIn,
		username: username,
		login: loginHandler,
		logout: logoutHandler,
	};

	return (
		<AuthContext.Provider value={contextValue}>
			{props.children}
		</AuthContext.Provider>
	);
};
