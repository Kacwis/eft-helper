import style from "./QuestObjective.module.css";

const QuestObjectiveRow = (props) => {
	const objective = props.objective;
	return (
		<li key={objective.id} className={style["objective-list-row"]}>
			{objective.requiredItem && <p>{objective.requiredItem.name}</p>}
			{objective.trader && <p>{objective.trader.name}</p>}
			{objective.target && <p>{objective.target}</p>}
			{objective.quantity && <p>{objective.quantity}</p>}
			{objective.location && <p>{objective.location}</p>}
		</li>
	);
};

export default QuestObjectiveRow;
