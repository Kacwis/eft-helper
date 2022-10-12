import { useParams } from "react-router-dom";
import MyHideoutModulesDetails from "../MyHideoutModulesDetails";

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
		name: "Mechanics- part 2",
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

const QuestDetails = () => {
	const params = useParams();

	const allQuests = DUMMY_QUESTS;

	

	const quest = allQuests.find((quest) => quest.id === params.questId);

	return <MyHideoutModulesDetails module={quest} />;
};

export default QuestDetails;
