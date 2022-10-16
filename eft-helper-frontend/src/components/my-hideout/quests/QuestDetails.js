import { useParams } from "react-router-dom";
import style from "./QuestDetails.module.css";

const DUMMY_QUESTS = [
	{
		id: "q1",
		name: "Collector",
		description: "Collecting some scraps...",
		requiredItems: [
			{
				id: "i1",
				name: "Fuel",
			},
			{
				id: "i2",
				name: "Bolts",
			},
		],
	},
	{
		id: "q2",
		title: "Debut",
		requiredLvl: 1,
		wiki: "some wiki",
		givinTrader: {
			name: "Prapor",
		},
		turninTrader: {
			name: "Prapor",
		},
		exp: 1000,
	},
];

const QuestDetails = () => {
	const params = useParams();

	const allQuests = DUMMY_QUESTS;

	const quest = DUMMY_QUESTS[1];

	return (
		<div className={style["quest-details-main"]}>
			<div className={style["quest-details-info"]}>
				<h3>{quest.title}</h3>
				<div className={style["general-info"]}>
					<p>{quest.requiredLvl} lvl</p>
					<p>{quest.exp} exp.</p>
					<a>{quest.wiki}</a>
					{quest.hint !== null && <p>{quest.hint}</p>}
				</div>
				<div className={style.traders}>
					<a>{quest.givinTrader.name}</a>
					<a>{quest.turninTrader.name}</a>
				</div>
				<div className={style.reputations}>
					<p>Prapor</p>
					<p>0.02</p>
				</div>
			</div>
			<div>
				<h4>Objectives</h4>
			</div>
		</div>
	);
};

export default QuestDetails;
