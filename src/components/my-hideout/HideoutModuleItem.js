import Card from '../ui/Card'
import style from './HideoutModuleItem.module.css';

import placeholder from '../../assets/eft_logo_edited.png';

const HideoutModuleItem = (props) => {
    return <li className={style.main}>
        <div className={style['item-row']}>
            <div className={style['item-row-name']}>
                <img src={placeholder} alt='placehodler' />
                <p>{props.name}</p>
            </div>
            <div className={style['quantity-buttons']}>
                <button className={style['substract-button']}>-</button>
                <p>{props.quantity}</p>
                <button className={style['add-button']}>+</button>
            </div>
        </div>
    </li>
}

export default HideoutModuleItem;