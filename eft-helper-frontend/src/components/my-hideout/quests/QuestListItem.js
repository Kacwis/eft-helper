import style from "./Quests.module.css";
import { Link } from "react-router-dom";

const QuestListItem = (props) => {
	const quest = props.quest;

	return (
		<Link to={`/my-hideout/quests/${quest.id}`}>
			<div className={style["quest-item"]}>
				<h5>{quest.title}</h5>
			</div>
		</Link>
	);
};

export default QuestListItem;
 