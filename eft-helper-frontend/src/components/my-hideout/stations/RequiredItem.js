import style from "./Stations.module.css";

const RequiredItem = (props) => {
	const item = props.item;

	return (
		<div className={style["required-item"]}>
			<img
				src={`http://localhost:8080/api/icons/${item.id}`}
				alt={`${item.name}`}
			/>
			<div className={style["item-info"]}>
				<p>{item.name}</p>
				<p>x {item.quantity}</p>
			</div>
		</div>
	);
};

export default RequiredItem;
