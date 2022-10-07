import AmmunitionList from "../components/items/ammo/AmmunitionList";

const ItemsPage = (props) => {
	return (
		<div>
			<h1 className="centered white">All items</h1>
			<div>
				<h2 className="centered white">Ammo list</h2>
				<AmmunitionList />
			</div>
		</div>
	);
};

export default ItemsPage;
