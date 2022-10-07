import style from './BarterItem.module.css'

const BarterItem = (props) => {
    return (
        <li className={style.container}>
            <div className={style['main-info']}>
                <img alt='barter_item_icon'/>
                <label>{props.item.name}</label>
            </div>
            <div>
                <label>{props.item.weight}</label>
                <label>{props.item.shortName}</label>
            </div>
        </li>
    )
}

export default BarterItem;