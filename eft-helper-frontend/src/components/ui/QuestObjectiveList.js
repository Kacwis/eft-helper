import style from './QuestObjective.module.css';

const QuestObjectiveList = (props) => {
	return (
		<div className={style["objective-list"]}>
			<h5>{props.title}</h5>
			<ul>{props.children}</ul>
		</div>
	);
};

export default QuestObjectiveList;
