import { useState } from "react";
import QuestListItem from "./QuestListItem";
import style from "./Quests.module.css";

const TraderQuests = (props) => {
	const [isQuestsVisible, setIsQuestsVisible] = useState(false);
	const traderQuests = props.traderQuests;

	const questListContent = traderQuests.quests.map((quest) => {
		return (
			<li key={quest.id}>
				<QuestListItem quest={quest} />
			</li>
		);
	});

    const onTraderClickHandler = () => {
        setIsQuestsVisible(!isQuestsVisible);
    }

	return (
		<div className={style["traders-quests"]}>
			<button onClick={onTraderClickHandler}>{traderQuests.traderName}</button>
			{isQuestsVisible && (
				<div className={style["traders-quests-list"]}>
					<ul>{questListContent}</ul>
				</div>
			)}
		</div>
	);
};

export default TraderQuests;
