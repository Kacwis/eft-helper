import { Route } from "react-router-dom";
import HideoutStations from "./HideoutStations";
import StationDetails from "./stations/StationDetails";
import QuestDetails from "./quests/QuestDetails";
import style from "./MyHideoutMain.module.css";

const MyHideoutMain = () => {
    return <div className={style.main}>
        <div>
            <HideoutStations />
            {/* <Quests /> */}
        </div>
    </div>
}

export default MyHideoutMain;

/* 
    HideoutStations >       Quests>
        HideoutStation          Quest

    Stations / Quests Details 
*/