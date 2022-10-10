const GunItem = (props) => {
	return (
		<li key={props.item.id}>
			<div>
				<img />
				<label>{props.item.name}</label>
			</div>
			<div>
				<label>{props.item.caliber}</label>
			</div>
		</li>
	);
};

export default GunItem;
