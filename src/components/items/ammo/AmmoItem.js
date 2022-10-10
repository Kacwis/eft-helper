import style from "./AmmoItem.module.css";
import placeholder from "../../../assets/eft_logo_edited.png";
import Card from "../../ui/Card";

const AmmoItem = (props) => {
	return (
		<li key={props.ammo.id}>
			<Card>
				<div className={style["ammo-info"]}>
					<img
						src={`http://localhost:8080/api/icons/${props.ammo.id}`}
						alt="item placeholder"
					/>
					<p>{props.ammo.name}</p>
				</div>
			</Card>
		</li>
	);
};

export default AmmoItem;
