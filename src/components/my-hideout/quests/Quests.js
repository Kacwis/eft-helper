import { Link } from "react-router-dom";
import style from "./Quests.module.css";

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
		name: "Collector",
		description: "Collecting some scraps...",
		requiredItems: [
			{
				id: "i3",
				name: "Can of spray",
			},
			{
				id: "i4",
				name: "Graphics card",
			},
		],
	},
];

const Quests = () => {
	const quests = DUMMY_QUESTS;

	const questListContent = quests.map((quest) => (
		<li>
			<Link to={`/my-hideout/quest/${quest.id}`}>{quest.name} </Link>
		</li>
	));

	return (
		<div className={style.main}>
			<h2>Quests</h2>
			<ul className={style["quest-list"]}>{questListContent}</ul>
		</div>
	);
};

export default Quests;
