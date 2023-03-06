import { NavLink } from "react-router-dom";
import style from "./Stations.module.css";

const StationListItem = (props) => {
	return (
		<NavLink
			to={`/my-hideout/stations/${props.station.id}`}
			className={({ isActive }) => (isActive ? style.active : style.inactive)}
		>
			<div className={style["station-item"]}>
				<h5>{props.station.name}</h5>
			</div>
		</NavLink>
	);
};

export default StationListItem;
