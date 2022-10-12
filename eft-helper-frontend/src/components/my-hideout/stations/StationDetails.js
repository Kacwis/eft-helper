import { useEffect, useState } from "react";
import { Outlet, useParams } from "react-router-dom";
import useHttp from "../../../hooks/use-http";
import MyHideoutModulesDetails from "../MyHideoutModulesDetails";
import { getHideoutStationById } from "../../api/api";

import style from "./StationDetails.module.css";

const DUMMY_STATIONS = [
	{
		id: "s1",
		name: "Generator",
		requiredItems: [
			{ id: "i1", name: "Fuel" },
			{ id: "i2", name: "Screwdriver" },
		],
		description: "Some short description about random item...",
	},
	{
		id: "s2",
		name: "Vents",
		requiredItems: [
			{ id: "i3", name: "Screws" },
			{ id: "i4", name: "Bolts" },
		],
		description: "Some short description about random item...",
	},
	{
		id: "s3",
		name: "Med station",
		requiredItems: [
			{ id: "i5", name: "LEDX" },
			{ id: "i6", name: "Bandage" },
		],
		description: "Some short description about random item...",
	},
	{
		id: "s4",
		name: "Shooting range",
		requiredItems: [
			{ id: "i7", name: "Pack of nails" },
			{ id: "i8", name: "Bullet" },
		],
		description: "Some short description about random item...",
	},
	{
		id: "s5",
		name: "Lavatory",
		requiredItems: [
			{ id: "i9", name: "Toliet paper" },
			{ id: "i10", name: "Toothpaste" },
		],
		description: "Some short description about random item...",
	},
];

const StationDetails = () => {
	const [station, setStation] = useState(null);
	const params = useParams();

	const {
		error,
		status,
		data: responseData,
		sendRequest,
	} = useHttp(getHideoutStationById, true);

	useEffect(() => {
		if (status === "completed" && !error) {
			setStation(responseData);
		}
	}, [status, error, setStation]);

	useEffect(() => {
		sendRequest(params.stationId);
	}, [params.stationId]);

	if (status === "pending") {
		return <div>Pending...</div>;
	}

	if (error) {
		return <div>Something went wrong!</div>;
	}

	return <MyHideoutModulesDetails module={station} />;
};

export default StationDetails;
