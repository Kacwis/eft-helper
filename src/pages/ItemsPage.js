import AmmunitionList from "../components/items/ammo/AmmunitionList";
import BarterItemsList from "../components/items/barter/BarterItemsList";

const ItemsPage = (props) => {
	return (
		<div>
			<h1 className="centered white">All items</h1>
			<div>
				<h2 className="centered white">Ammo list</h2>
				<AmmunitionList />
			</div>
			<div>
				<h2 className="centered white">Barter items</h2>
				<BarterItemsList />
			</div>
		</div>
	);
};

export default ItemsPage;
