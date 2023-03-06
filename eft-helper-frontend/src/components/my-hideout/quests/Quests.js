import { useContext, useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import useHttp from "../../../hooks/use-http";
import { AuthContext } from "../../../store/auth-context";
import { getMyHideoutQuests } from "../../api/api";
import LoadingSpinner from "../../ui/LoadingSpinner";

import style from "./Quests.module.css";
import TraderQuests from "./TraderQuests";

const Quests = () => {
	const [tradersQuests, setTradersQuests] = useState([]);

	const authCtx = useContext(AuthContext);

	const { status, error, data, sendRequest } = useHttp(
		getMyHideoutQuests,
		true
	);

	useEffect(() => {
		sendRequest(authCtx.token);
	}, []);

	useEffect(() => {
		if (status === "completed" && !error) {
			console.log(data);
			setTradersQuests(data);
		}
	}, [status, error, data]);

	if (status === "pending") {
		return <LoadingSpinner />;
	}

	const tradersQuestListContent = tradersQuests.map((traderQuests) => {
		return (
			<li key={traderQuests.traderId}>
				<TraderQuests traderQuests={traderQuests} />
			</li>
		);
	});

	return (
		<div className={style.main}>
			<ul>{tradersQuestListContent}</ul>
			<Outlet />
		</div>
	);
};

export default Quests;
