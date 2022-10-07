import React, { useState } from "react";

export const AuthContext = React.createContext({
	token: "",
	isLoggedIn: false,
	login: (token) => {},
	logout: () => {},
});

export const AuthContextProvider = (props) => {
	const [token, setToken] = useState(null);
	const [isUserLoggedIn, setIsUserLoggedIn] = useState(false);

	const loginHandler = (token) => {
		setToken(token);
		setIsUserLoggedIn(true);
		console.log("loggedIn");
	};

	const logoutHandler = () => {
		setToken(null);
		setIsUserLoggedIn(false);
		console.log("loggedOut");
	};

	const contextValue = {
		token: token,
		isLoggedIn: isUserLoggedIn,
		login: loginHandler,
		logout: logoutHandler,
	};

	return (
		<AuthContext.Provider value={contextValue}>
			{props.children}
		</AuthContext.Provider>
	);
};
