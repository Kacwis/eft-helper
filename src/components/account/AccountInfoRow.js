import style from "./AccountInfoRow.module.css";
import Card from "../ui/Card";

const AccountInfoRow = (props) => {
	return (
		<Card>
			<div className={style["info-row"]}>
				<p className={style.label}>{props.label}</p>
				<p className={style.value}>{props.value}</p>
			</div>
		</Card>
	);
};

export default AccountInfoRow;
