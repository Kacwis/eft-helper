import { useContext } from "react";
import { AuthContext } from "../../store/auth-context";

const MY_HIDEOUT_COMPONENTS = "http://localhost:8080/api/hideout/my";

const STATIONS_API_URL = "http://localhost:8080/api/hideout/my/stations";
const QUESTS_API_URL = "http://localhost:8080/api/hideout/my/quests";
const ALL_STATIONS_ERROR_MSG = "Something went wrong!";
const STATION_ERROR_MSG = ALL_STATIONS_ERROR_MSG;
const ALL_QUESTS_ERROR_MSG = ALL_STATIONS_ERROR_MSG;
const QUEST_ERROR_MSG = ALL_QUESTS_ERROR_MSG;

export const getHideoutStations = async (token) => {
	const response = await fetch(STATIONS_API_URL, {
		method: "GET",
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error(ALL_STATIONS_ERROR_MSG);
	}
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

export const deleteHideoutStation = async (deleteHideoutStationRequestData) => {
	const { stationId, token } = deleteHideoutStationRequestData;
	const response = await fetch(`${STATIONS_API_URL}/${stationId}`, {
		method: "DELETE",
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error("something went wrong");
	}
	console.log(data);
};

export const getMyHideoutQuests = async (token) => {
	const response = await fetch(QUESTS_API_URL, {
		method: "GET",
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error(ALL_QUESTS_ERROR_MSG);
	}
	const questsList = [];
	for (const key in data) {
		questsList.push(data[key]);
	}
	return questsList;
};

export const getMyHideoutQuestById = async (hideoutQuestRequestData) => {
	const { questId, token } = hideoutQuestRequestData;
	const response = await fetch(`${QUESTS_API_URL}/details/${questId}`, {
		method: "GET",
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error(QUEST_ERROR_MSG);
	}
	const quest = await data;
	return quest;
};

export const getMyHideoutComponents = async (token) => {
	const response = await fetch(MY_HIDEOUT_COMPONENTS, {
		header: {
			Authorization: `Bearer ${token}`,
		},
	});
	const data = await response.json();
	if (!response.ok) {
		throw new Error("Something went wrong!");
	}
	console.log(data);
	return data;
};
