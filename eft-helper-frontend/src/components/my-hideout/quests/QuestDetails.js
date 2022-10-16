import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import useHttp from "../../../hooks/use-http";
import { AuthContext } from "../../../store/auth-context";
import { getMyHideoutQuestById } from "../../api/api";
import style from "./QuestDetails.module.css";
import QuestObjectives from "./QuestObjectives";

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

const initalQuest = {
	id: "",
	exp: 0,
	requiredLvl: 0,
	title: "",
	wiki: "",
	givingTrader: {
		id: "",
		name: "",
	},
	turningTrader: {
		id: "",
		name: "",
	},
	buildObjectives: [],
	collectObjectives: [],
	findObjectives: [],
	keyObjectives: [],
	killObjectives: [],
	locateObjectives: [],
	markObjectives: [],
	pickupObjectives: [],
	placeObjectives: [],
	reputationObjectives: [],
	skillObjectives: [],
	warningObjectives: [],
};

const QuestDetails = () => {
	const params = useParams();

	const [quest, setQuest] = useState(initalQuest);

	const authCtx = useContext(AuthContext);

	const {
		error,
		status,
		data: responseData,
		sendRequest,
	} = useHttp(getMyHideoutQuestById, true);

	useEffect(() => {
		if (status === "completed" && !error) {
			setQuest(responseData);
			console.log(quest);
		}
	}, [status, error, setQuest, responseData]);

	useEffect(() => {
		const requestData = {
			questId: params.questId,
			token: authCtx.token,
		};
		sendRequest(requestData);
	}, [params.questId]);

	if (status === "pending") {
		return <div>Pending...</div>;
	}

	if (status === "completed" && error) {
		return <div>{error}</div>;
	}

	const { givingTrader, turningTrader } = quest;

	return (
		<div className={style["quest-details-main"]}>
			<div className={style["quest-details-info"]}>
				<h3>{quest.title}</h3>
				<div className={style["general-info"]}>
					<p>{quest.requiredLvl} lvl</p>
					<p>{quest.exp} exp.</p>
					<a href={quest.wiki}>Wiki</a>
				</div>
				<div className={style.traders}>
					<p>From: {givingTrader.name}</p>
					<p>To: {turningTrader.name}</p>
				</div>
				<div className={style.reputations}>
					<p>Prapor</p>
					<p>0.02</p>
				</div>
			</div>
			<div>
				<h4>Objectives</h4>
				<QuestObjectives quest={quest} />
			</div>
		</div>
	);
};

export default QuestDetails;
