const DUMMY_BARTER_ITEMS = [
    {id: b1, name: 'Glue', weight: 0.2, shortName: 'Glue'},
    {id: b2, name: 'Spray', weight: 0.5, shortName: 'Spray'},
    {id: b3, name: 'Shampoo', weight: 0.7, shortName: 'Shamp'},
]

const BarterItemsList = () => {

    const barterItemsListContent = DUMMY_BARTER_ITEMS.map((item) => {
        return <BarterItem item={item} />
    })

    return (
        <div>
            <h2>Barter items</h2>
            <div>
                <ul>
                    {barterItemsListContent}
                </ul>
            </div>
        </div>
    )
}

export default BarterItemsList;