import { useEffect, useState } from "react";
import useHttp from "../../../hooks/use-http";
import LoadingSpinner from "../../ui/LoadingSpinner";
import AmmoItem from "./AmmoItem";
import style from "./AmmunitionList.module.css";

const AMMO_API_URL = "http://192.168.0.112:8080/api/ammo";

const readAllAmmo = async () => {
	const response = await fetch(AMMO_API_URL);
	const data = await response.json();
	if (!response.ok) {
		throw new Error("Something went wrong!");
	}
	const ammoList = [];
	for (const key in data) {
		ammoList.push(data[key]);
	}

	return ammoList;
};

const DUMMY_AMMO_LIST = [
	{id: "a1", name: 'PBM ghz', caliber: '9x19'},
	{id: "a2", name: 'PPM ghz', caliber: '9x19'},
	{id: "a3", name: 'PST ghz', caliber: '9x19'}	
]

const AmmunitionList = () => {
	// const [ammoList, setAmmoList] = useState([]);

	// const {
	// 	error,
	// 	status,
	// 	data: responseData,
	// 	sendRequest,
	// } = useHttp(readAllAmmo, true);

	// useEffect(() => {
	// 	if (status === "completed" && !error) {
	// 		setAmmoList(responseData);
	// 	}
	// }, [status, setAmmoList, error]);

	// useEffect(() => {
	// 	sendRequest();
	// }, []);

	const ammoList = DUMMY_AMMO_LIST;

	let ammoListContent = (
		<ul>
			{ammoList.map((ammo) => {
				console.log(ammo);
				return <AmmoItem ammo={ammo} />;
			})}
		</ul>
	);

	// if (status === "pending") {
	// 	ammoListContent = <LoadingSpinner />;
	// }

	// if (status === "completed" && error) {
	// 	ammoListContent = (
	// 		<img src="http://localhost:8080/api/icons/5a0c27731526d80618476ac4" />
	// 	);
	// }

	return <div className={style["ammo-list"]}>{ammoListContent}</div>;
};

export default AmmunitionList;
