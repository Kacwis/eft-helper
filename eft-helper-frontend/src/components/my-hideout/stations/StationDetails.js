import { useContext, useEffect, useState } from "react";
import { Outlet, useParams, Link } from "react-router-dom";
import useHttp from "../../../hooks/use-http";
import MyHideoutModulesDetails from "../MyHideoutModulesDetails";
import { getHideoutStationById } from "../../api/api";

import style from "./StationDetails.module.css";
import { AuthContext } from "../../../store/auth-context";

const DUMMY_STATIONS = [
	{
		id: "s1",
		name: "Generator",
		stationLevels: [
			{ id: "sL1", level: 1, requiredItems: [], requiredStations: [] },
		],
		requiredItems: [
			{ id: "i1", name: "Fuel" },
			{ id: "i2", name: "Screwdriver" },
		],
		description: "Some short description about random item...",
	},
];

const StationDetails = () => {
	const [station, setStation] = useState(DUMMY_STATIONS[0]);
	const [isModuleBuiltPopUpVisible, setIsModuleBuildPopUpVisible] =
		useState(false);
	const params = useParams();

	const authCtx = useContext(AuthContext);

	const stationBuiltHandler = () => {
		setIsModuleBuildPopUpVisible(true);
	};

	const cancelMarkingAsBuilt = () => {
		setIsModuleBuildPopUpVisible(false);
	};

	const {
		error,
		status,
		data: responseData,
		sendRequest,
	} = useHttp(getHideoutStationById, true);

	useEffect(() => {
		if (status === "completed" && !error) {
			responseData.stationLevels.sort((sl1, sl2) =>
				sl1.level < sl2.level ? -1 : 1
			);
			setStation(responseData);
		}
	}, [status, error, setStation, responseData]);

	useEffect(() => {
		sendRequest({ token: authCtx.token, stationId: params.stationId });
	}, [params.stationId]);

	if (status === "pending") {
		return <div>Pending...</div>;
	}

	if (error) {
		return <div>Something went wrong!</div>;
	}

	const stationLevelsContent = station.stationLevels.map((level) => {
		console.log(level);
		return (
			<li key={level.id}>
				<h4>Level {level.level}</h4>
				<div className={style["station-level-details"]}>
					<h4>Required items</h4>
					<div className={style["required-items-list"]}>
						<ul>
							{level.requiredItems.map((item) => (
								<RequiredItemRow
									name={item.name}
									id={item.id}
									quantity={item.quantity}
								/>
							))}
						</ul>
					</div>
					<h4>Required stations</h4>
					<div className={style["required-items-list"]}>
						<ul>
							{level.requiredStations.map((rs) => {
								return (
									<li className={style["required-items-row"]}>
										<Link to={`/my-hideout/station/${rs.id}`}>
											<p>{rs.name}</p>
											<p>{rs.quantity} lvl</p>
										</Link>
									</li>
								);
							})}
						</ul>
					</div>
				</div>
			</li>
		);
	});

	return (
		<>
			<div className={style.main}>
				<div className={style["detail-info"]}>
					<h2 className={style["station-name"]}>{station.name}</h2>
					<p className={style["station-description"]}>{station.description}</p>
				</div>
				<div className={style["required-items"]}>
					<h3>Levels</h3>
					<ul className={style["station-levels-list"]}>
						{stationLevelsContent}
					</ul>
				</div>
			</div>
		</>
	);
};

const RequiredItemRow = (props) => {
	return (
		<li className={style["required-items-row"]}>
			<img src={`http://localhost:8080/api/icons/${props.id}`} />
			<p>{props.name}</p>
			<p>{props.quantity}</p>
		</li>
	);
};

export default StationDetails;
