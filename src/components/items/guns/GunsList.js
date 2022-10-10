import style from "./GunsList.module.css";

const DUMMY_GUNS_LIST = [
	{
		id: "g1",
		name: "M4A1",
		caliber: "5.56x45 NATO",
	},
	{
		id: "g2",
		name: "AKM",
		caliber: "7.62x39",
	},
];

const GunsList = () => {
	const gunsListContent = DUMMY_GUNS_LIST.map((gun) => {
		return <GunItem item={gun} />;
	});

	return (
		<div>
			<ul>{gunsListContent}</ul>
		</div>
	);
};

export default GunsList;
