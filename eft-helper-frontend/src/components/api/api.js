import { useContext } from "react";
import { AuthContext } from "../../store/auth-context";

const STATIONS_API_URL = "http://localhost:8080/api/hideout/my/stations";
const ALL_STATIONS_ERROR_MSG = "Something went wrong!";
const STATION_ERROR_MSG = ALL_STATIONS_ERROR_MSG;

export const getHideoutStations = async (token) => {
	console.log(token);
	const response = await fetch(STATIONS_API_URL, {
		method: "GET",
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	if (!response.ok) {
		throw new Error(ALL_STATIONS_ERROR_MSG);
	}
	const data = await response.json();
	const stationsList = [];
	for (const key in data) {
		stationsList.push(data[key]);
	}
	return stationsList;
};

export const getHideoutStationById = async (hideoutStationRequestData) => {
	const { stationId, token } = hideoutStationRequestData;
	const response = await fetch(`${STATIONS_API_URL}/details/${stationId}`, {
		method: "GET",
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error(STATION_ERROR_MSG);
	}
	const station = await data;
	console.log(station);
	return station;
};
