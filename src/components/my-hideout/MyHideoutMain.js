import HideoutModules from "./HideoutModules";
import style from "./MyHideoutMain.module.css";

const MyHideoutMain = () => {
    return <div className={style.main}>
        <HideoutModules />
    </div>
}

export default MyHideoutMain;

/* 
    Hideout (items, what completed) || Quests(items, which completed)
    Statistics
*/