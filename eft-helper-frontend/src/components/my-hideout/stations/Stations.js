import { useContext, useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import useHttp from "../../../hooks/use-http";
import { AuthContext } from "../../../store/auth-context";
import { getHideoutStations } from "../../api/api";
import StationListItem from "./StationListItem";

import style from "./Stations.module.css";

const DUMMY_STATIONS = [
	{ id: "s1", name: "Generator" },
	{ id: "s2", name: "Vents" },
	{ id: "s3", name: "Med station" },
	{ id: "s4", name: "Shooting range" },
	{ id: "s5", name: "Lavatory" },
	{ id: "s6", name: "Lavatory" },
	{ id: "s7", name: "Lavatory" },
	{ id: "s8", name: "Lavatory" },
	{ id: "s9", name: "Lavatory" },
	{ id: "s10", name: "Lavatory" },
	{ id: "s11", name: "Lavatory" },
	{ id: "s12", name: "Lavatory" },
	{ id: "s13", name: "Lavatory" },
	{ id: "s14", name: "Lavatory" },
	{ id: "s15", name: "Lavatory" },
	{ id: "s16", name: "Lavatory" },
];

const Stations = () => {
	const [stations, setStations] = useState([]);

	const authCtx = useContext(AuthContext);

	const { status, error, data, sendRequest } = useHttp(
		getHideoutStations,
		true
	);

	useEffect(() => {
		sendRequest(authCtx.token);
	}, []);

	useEffect(() => {
		if (status === "completed" && !error) {
			setStations(data);
		}
	}, [status, error, data]);

	const stationsListContent = stations.map((station) => {
		return (
			<li key={station.id}>
				<StationListItem station={station} />
			</li>
		);
	});

	return (
		<div className={style.main}>
			<h1>Stations</h1>
			<div className={style["stations-list"]}>
				<ul>{stationsListContent}</ul>
			</div>
			<Outlet />
		</div>
	);
};

export default Stations;
