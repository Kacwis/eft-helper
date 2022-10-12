import Card from "../ui/Card";
import style from "./AccountInfo.module.css";
import AccountInfoRow from "./AccountInfoRow";

const AccountInfo = (props) => {
	return (
		<div className={style.main}>
			<div className={style["info-rows"]}>
				<AccountInfoRow label="E-mail:" value={props.email} />
				<AccountInfoRow label="Username:" value={props.username} />
			</div>
			<div className={style.buttons}>
				<button className={style["password-change"]}>Change password</button>
				<button className={style["remove-account"]}>Remove account</button>
			</div>
		</div>
	);
};

export default AccountInfo;
