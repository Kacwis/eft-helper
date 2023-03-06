import { useEffect, useState } from "react";
import RequiredItem from "./RequiredItem";
import RequiredStation from "./RequiredStation";

import style from "./Stations.module.css";

const StationLevel = (props) => {
	const [level, setLevel] = useState(props.level);

	console.log(props.level);

	useEffect(() => {
		setLevel(props.level);
		console.log(props.level);
	}, [props.level]);

	const requiredItemsListContent = level.requiredItems.map((item) => {
		return (
			<li key={item.id}>
				<RequiredItem item={item} />
			</li>
		);
	});

	const requiredStationsListContent = level.requiredStations.map((station) => {
		return (
			<li key={station.id}>
				<RequiredStation station={station} />
			</li>
		);
	});

	return (
		<div className={style["station-details-right"]}>
			<div className={style["required-items"]}>
				<h5>Required Items</h5>
				<ul>{requiredItemsListContent} </ul>
			</div>
			{!(level.requiredStations.length === 0) && (
				<div className={style["required-stations"]}>
					<h5>Required Stations</h5>
					<ul>{requiredStationsListContent}</ul>
				</div>
			)}
		</div>
	);
};

export default StationLevel;
