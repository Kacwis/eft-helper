import React from "react";
import MainNavigation from "./MainNavigation";

import style from "./Layout.module.css";

const Layout = (props) => {
	return (
		<React.Fragment>
			<MainNavigation></MainNavigation>
			<main className={style.main}>{props.children}</main>
		</React.Fragment>
	);
};

export default Layout;
