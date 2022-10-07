import style from "./AmmoItem.module.css";

const AmmoItem = (props) => {
	const []
	

	

	return (
		<li key={props.ammo.id}>
			<div className={stylse["ammo-info"]}>
				{/* <img
					src={`http://localhost:8080/api/icons/${props.ammo.id}`}
					alt={`${props.ammo.name} icon ${props.ammo.id}`}
				/> */}
				<label>{props.ammo.name}</label>
			</div>
		</li>
	);
};

export default AmmoItem;
