import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import useHttp from "../../../hooks/use-http";
import { AuthContext } from "../../../store/auth-context";
import style from "./Quests.module.css";
import { getMyHideoutQuests } from "../../api/api";

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
	const [questsList, setQuestsList] = useState(DUMMY_QUESTS);
	const [query, setQuery] = useState("");

	const authCtx = useContext(AuthContext);

	const {
		error,
		status,
		data: responseData,
		sendRequest,
	} = useHttp(getMyHideoutQuests, true);

	useEffect(() => {
		if (status === "completed" && !error) {
			setQuestsList(responseData);
		}
	}, [error, status, setQuestsList]);

	useEffect(() => {
		sendRequest(authCtx.token);
	}, []);

	if (status === "pending") {
		return <div>Pending...</div>;
	}

	if (status === "completed" && error) {
		return <div>{error}</div>;
	}

	const onQuestSearchChangeHandler = (e) => {
		setQuery(e.target.value);
	};

	const questRowClickHandler = () => {
		setQuery("");
	};

	const questListContent = questsList
		.filter((quest) => {
			if (query === "") {
				return quest;
			} else if (quest.title.toLowerCase().includes(query.toLowerCase())) {
				return quest;
			}
		})
		.sort((quest1, quest2) => {
			return quest1.title > quest2.title ? 1 : -1;
		})
		.map((quest) => (
			<li key={quest.id}>
				<Link
					to={`/my-hideout/quest/${quest.id}`}
					onClick={questRowClickHandler}
				>
					{quest.title}{" "}
				</Link>
			</li>
		));

	return (
		<div className={style.main}>
			<h2>Quests</h2>
			<input onChange={onQuestSearchChangeHandler} type="text" value={query} />
			<ul className={style["quest-list"]}>{questListContent}</ul>
		</div>
	);
};

export default Quests;
