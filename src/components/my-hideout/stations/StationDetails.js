import { useParams } from "react-router-dom"

const DUMMY_STATIONS = [
    {id: 's1', name: 'Generator'},
    {id: 's2', name: 'Vents'},
    {id: 's3', name: 'Med station'},
    {id: 's4', name: 'Shooting range'},
    {id: 's5', name: 'Lavatory'}
]

const StationDetails = () => {
    console.log('in');
    const params = useParams();
    console.log(params.stationId);
    const stationName = DUMMY_STATIONS.find((station) => station.id === params.stationId);
    console.log(stationName);

    return <div>
        <p>{stationName}</p>
    </div>
}

export default StationDetails;