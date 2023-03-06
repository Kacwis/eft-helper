import { Link, NavLink, Outlet } from "react-router-dom";
import style from "./MyHideoutMain.module.css";

const MyHideoutMain = () => {
	return (
		<div className={style.main}>
			<div className={style["component-buttons"]}>
				<NavLink
					to="/my-hideout/quests"
					className={({ isActive }) =>
						isActive ? style["component-active"] : style.quests
					}
				>
					Quests
				</NavLink>
				<NavLink
					to="/my-hideout/stations"
					className={({ isActive }) =>
						isActive ? style["component-active"] : style.stations
					}
				>
					Stations
				</NavLink>
			</div>
			<Outlet />
		</div>
	);
};

export default MyHideoutMain;
