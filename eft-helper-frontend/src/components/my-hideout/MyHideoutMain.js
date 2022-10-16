import { Outlet } from "react-router-dom";
import HideoutStations from "./stations/HideoutStations";
import Quests from "./quests/Quests";
import style from "./MyHideoutMain.module.css";

const MyHideoutMain = () => {
	return (
		<div className={style.main}>
			<div className={style["hideout-lists"]}>
				<HideoutStations />
				<Quests />
			</div>
			<Outlet />
		</div>
	);
};

export default MyHideoutMain;

/* 
    HideoutStations >       Quests>
        HideoutStation          Quest

    Stations / Quests Details 
*/
