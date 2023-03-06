import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import useHttp from "../../../hooks/use-http";
import { AuthContext } from "../../../store/auth-context";
import { getHideoutStationById } from "../../api/api";
import LoadingSpinner from "../../ui/LoadingSpinner";
import StationLevel from "./StationLevel";

import style from "./Stations.module.css";

const StationDetails = () => {
	const [station, setStation] = useState(null);
	const [currentLevel, setCurrentLevel] = useState("");
	const [currentLevelNumber, setCurrentLevelNumber] = useState(0);

	const params = useParams();

	const authCtx = useContext(AuthContext);

	const { status, error, data, sendRequest } = useHttp(
		getHideoutStationById,
		true
	);

	useEffect(() => {
		const requestData = {
			token: authCtx.token,
			stationId: params.stationId,
		};
		sendRequest(requestData);
		setCurrentLevel("");
		setCurrentLevelNumber(0);
	}, [params.stationId]);

	useEffect(() => {
		if (status === "completed" && !error) {
			setStation(data);
		}
	}, [status, error, data]);

	if (status === "pending" || station === null) {
		return <LoadingSpinner />;
	}

	const levelClickHandler = (event) => {
		const level = +event.target.innerHTML;
		const currentLevel = station.stationLevels.find(
			(stationLevel) => stationLevel.level === level
		);
		setCurrentLevelNumber(level);
		setCurrentLevel(<StationLevel level={currentLevel} />);
	};

	const levelsListContent = [...Array(station.stationLevels.length).keys()].map(
		(index) => {
			const className =
				index === currentLevelNumber - 1
					? style["station-level-active"]
					: style["station-level-inactive"];
			return (
				<li key={index + 1}>
					<button onClick={levelClickHandler} className={className}>
						{index + 1}
					</button>
				</li>
			);
		}
	);

	return (
		<div className={style["station-details"]}>
			<div className={style["station-details-left"]}>
				<div className={style["station-info"]}>
					<h3>{station.name}</h3>
					<p>{station.description}</p>
				</div>
				<div className={style["stations-levels"]}>
					<ul>{levelsListContent}</ul>
				</div>
				<div className={style["station-buttons"]}>
					<button>Marked as built</button>
					
				</div>
			</div>
			{currentLevel}
		</div>
	);
};

export default StationDetails;
