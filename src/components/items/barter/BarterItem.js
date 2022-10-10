import Card from "../../ui/Card";
import style from "./BarterItem.module.css";

import placeholder from "../../../assets/eft_logo_edited.png";

const BarterItem = (props) => {
	let nameTitleContent = <p>{props.item.name}</p>;

	if (props.item.name.length > 20) {
		nameTitleContent = <p>{props.item.shortname}</p>;
	}

	return (
		<li key={props.item.id}>
			<Card>
				<div className={style["main-info"]}>
					<img
						alt="barter_item_icon"
						src={`http://localhost:8080/api/icons/${props.item.id}`}
					/>
					{nameTitleContent}
				</div>
			</Card>
		</li>
	);
};

export default BarterItem;
