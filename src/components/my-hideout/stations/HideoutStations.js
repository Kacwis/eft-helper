import style from "./HideoutStations.module.css";

import { getHideoutStations } from "../../api/api";
import { Link, Routes, Route } from "react-router-dom";
import { useState, useEffect } from "react";
import useHttp from "../../../hooks/use-http";

const DUMMY_STATIONS = [
	{ id: "s1", name: "Generator" },
	{ id: "s2", name: "Vents" },
	{ id: "s3", name: "Med station" },
	{ id: "s4", name: "Shooting range" },
	{ id: "s5", name: "Lavatory" },
];

const HideoutStations = (props) => {
	const [stationsList, setStationsList] = useState(DUMMY_STATIONS);

	const {
		error,
		status,
		data: responseData,
		sendRequest,
	} = useHttp(getHideoutStations, true);

	useEffect(() => {
		if (status === "completed" && !error) {
			console.log(responseData);
			setStationsList(responseData);
		}
	}, [status, error, setStationsList]);

	useEffect(() => {
		sendRequest();
	}, []);

	if (status === "pending") {
		return <div>Pending..</div>;
	}

	if (status === "completed" && error) {
		return <div>Something went wrong!</div>;
	}

	const stationsListContent = stationsList.map((station) => {
		return (
			<li>
				<Link to={`/my-hideout/station/${station.id}`}>{station.name} </Link>
			</li>
		);
	});

	return (
		<div className={style.main}>
			<h2>Stations</h2>
			<ul className={style["stations-list"]}>{stationsListContent}</ul>
		</div>
	);
};

export default HideoutStations;
