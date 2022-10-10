import { useContext } from "react";
import AccountInfo from "../components/account/AccountInfo";
import { AuthContext } from "../store/auth-context";

const AccountPanel = () => {
	const authCtx = useContext(AuthContext);

	return (
		<div className="account-panel-cont">
			<h1>{`jack's panel`} </h1>
			<div className="account-panel-main">
				<h2>Account info</h2>
				<AccountInfo email="wp.pl@wp.pl" username="jack" />
			</div>
		</div>
	);
};

export default AccountPanel;
