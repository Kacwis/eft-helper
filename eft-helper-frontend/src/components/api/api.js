import { useContext } from "react";
import { AuthContext } from "../../store/auth-context";

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
	return station;
};

export const getMyHideoutQuests = async (token) => {
	const response = await fetch(QUESTS_API_URL, {
		method: "GET",
		headers: {
			Authorization: `Bearer ${token}`
		}
	});
	const data = await response.json();
	if(!response.ok){
		throw new Error(ALL_QUESTS_ERROR_MSG);
	}
	const questsList = [];
	for(const key in data){
		questsList.push(data[key]);
	}
	return questsList;
}

export const getMyHideoutQuestById = async (hideoutQuestRequestData) => {
	const {questId, token} = hideoutQuestRequestData;
	const response = await fetch(`${QUESTS_API_URL}/details/${questId}`, {
		method: 'GET',
		headers: {
			Authorization: `Bearer ${token}`
		}
	});
	const data = await response.json();
	if(!response.ok){
		throw new Error(QUEST_ERROR_MSG);
	}
	const quest = await data;
	return quest;
}
