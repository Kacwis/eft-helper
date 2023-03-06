import React, { useContext } from "react";
import { NavLink } from "react-router-dom";
import { AuthContext } from "../../store/auth-context";
import style from "./MainNavigation.module.css";

import logo from "../../assets/eft_logo_edited.png";

const MainNavigation = () => {
	const authCtx = useContext(AuthContext);

	return (
		<header className={style.header}>
			<nav className={style.nav}>
				<ul className={style["standard-nav"]}>
					<li>
						<NavLink to="/home">Main page</NavLink>
					</li>
					<li>
						<NavLink to="/items">Items</NavLink>
					</li>
				</ul>
				{!authCtx.isLoggedIn && (
					<ul className={style["auth-buttons"]}>
						<li>
							<NavLink to="/Log-in">Log in</NavLink>
						</li>
						<li>
							<NavLink to="/sign-in">Sign in</NavLink>
						</li>
					</ul>
				)}
				{authCtx.isLoggedIn && (
					<ul className={style["account"]}>
						<li>
							<NavLink to="/account-panel">Account</NavLink>
						</li>
						<li>
							<NavLink
								to="/my-hideout"
								className={({ isActive }) => (isActive ? "active" : "")}
							>
								My hideout
							</NavLink>
						</li>
					</ul>
				)}
			</nav>
		</header>
	);
};

export default MainNavigation;
