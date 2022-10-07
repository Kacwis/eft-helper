import style from "./AmmoItem.module.css";
import placeholder from '../../../assets/eft_logo_edited.png';

const AmmoItem = (props) => {

	return (
		<li key={props.ammo.id}>
			<div className={style["ammo-info"]}>
				<img src={placeholder} alt='item placeholder' />
				<div className={style.name}>
					<p>{props.ammo.caliber}</p>
					<p>{props.ammo.name}</p>
				</div>
			</div>
		</li>
	);
};

export default AmmoItem;
