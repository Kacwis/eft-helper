import style from './HideoutStations.module.css'

import { Link } from 'react-router-dom'

const DUMMY_STATIONS = [
    {id: 's1', name: 'Generator'},
    {id: 's2', name: 'Vents'},
    {id: 's3', name: 'Med station'},
    {id: 's4', name: 'Shooting range'},
    {id: 's5', name: 'Lavatory'}
]

const HideoutStations = (props) => {
    const stationsListContent = DUMMY_STATIONS.map((station) => <li><Link to={`/my-hideout/station/${station.id}`}>{station.name} </Link></li>)

    return <div className={style.main}>
        <h2>Stations</h2>
        <ul className={style['stations-list']}>
            {stationsListContent}
        </ul>
    </div>
}

export default HideoutStations;