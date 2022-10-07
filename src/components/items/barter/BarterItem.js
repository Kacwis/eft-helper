const BarterItem = (props) => {
    return (
        <ul>
            <div>
                <img />
                <label>{props.item.name}</label>
            </div>
            <div>
                <label>{props.item.weight}</label>
                <label>{props.item.shortName}</label>
            </div>
        </ul>
    )
}

export default BarterItem;