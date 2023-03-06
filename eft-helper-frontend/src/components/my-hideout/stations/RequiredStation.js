import style from "./Stations.module.css";

const RequiredStation = (props) => {
	const station = props.station;

	return (
		<div className={style["required-station"]}>
			<p>{station.name}</p>
			<p className={style["required-station-level"]}>Lvl. {station.quantity}</p>
		</div>
	);
};

export default RequiredStation;
