import AmmunitionList from "../components/items/ammo/AmmunitionList";
import BarterItemsList from "../components/items/barter/BarterItemsList";

const ItemsPage = (props) => {
	return (
		<div className="item-page-container">
			<AmmunitionList />
			<BarterItemsList />
		</div>
	);
};

export default ItemsPage;
