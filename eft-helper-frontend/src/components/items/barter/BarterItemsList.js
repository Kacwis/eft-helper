import { useEffect, useState } from "react";
import useHttp from "../../../hooks/use-http";
import ItemListContainer from "../../ui/ItemListContainer";
import LoadingSpinner from "../../ui/LoadingSpinner";
import BarterItem from "./BarterItem";
import style from "./BarterItemsList.module.css";

const ITEMS_API_URL = "http://localhost:8080/api/items";

const readAllItems = async () => {
	const resposne = await fetch(ITEMS_API_URL);
	const data = await resposne.json();
	if (!resposne.ok) {
		throw new Error("Something went wrong!");
	}
	const itemsList = [];
	for (const key in data) {
		itemsList.push(data[key]);
		console.log(itemsList[itemsList.length - 1]);
	}

	return itemsList;
};

const DUMMY_BARTER_ITEMS = [
	{ id: "b1", name: "Glue", weight: 0.2, shortName: "Glue" },
	{ id: "b2", name: "Spray", weight: 0.5, shortName: "Spray" },
	{ id: "b3", name: "Shampoo", weight: 0.7, shortName: "Shamp" },
];

const BarterItemsList = () => {
	const [itemsList, setItemsList] = useState([]);

	const {
		error,
		status,
		data: responseData,
		sendRequest,
	} = useHttp(readAllItems, true);

	useEffect(() => {
		if (status === "completed" && !error) {
			setItemsList(responseData);
		}
	}, [status, setItemsList, error]);

	useEffect(() => {
		sendRequest();
	}, []);

	const barterItemsListContent = itemsList.map((item) => {
		return <BarterItem item={item} />;
	});

	if (status === "pending") {
		return <LoadingSpinner />;
	}

	if (status === "completed" && error) {
		return (
			<div>
				<h2>Something went wrong!</h2>
			</div>
		);
	}
	return (
		<div className={style["barter-items-list"]}>
			<h2>Barter items</h2>
			<ItemListContainer title="Barter items">
				{barterItemsListContent}
			</ItemListContainer>
		</div>
	);
};

export default BarterItemsList;
