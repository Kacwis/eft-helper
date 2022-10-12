const STATIONS_API_URL = "http://localhost:8080/api/hideout/stations";
const ALL_STATIONS_ERROR_MSG = "Something went wrong!";
const STATION_ERROR_MSG = ALL_STATIONS_ERROR_MSG;

export const getHideoutStations = async () => {
	const response = await fetch(STATIONS_API_URL);
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

export const getHideoutStationById = async (stationId) => {
	const response = await fetch(`${STATIONS_API_URL}/details/${stationId}`);
	if (!response.ok) {
		throw new Error(STATION_ERROR_MSG);
	}
	const data = await response.json();
	const station = data;
	console.log(station);
	return station;
};
