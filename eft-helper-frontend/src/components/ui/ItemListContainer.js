const ItemListContainer = (props) => {
	return (
		<div className="item-list-cont">
			<ul>{props.children}</ul>
		</div>
	);
};

export default ItemListContainer;
