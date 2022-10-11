import HideoutModule from "./HideoutModule";
import style from './HideoutModules.module.css';

const DUMMY_MODULES = [
    {id: 'm1', name: "Generator", requiredModules: [
        {id: 'm2', name: "Vents", requiredModules: [], requiredItems: [{id: 'i1', name: "Screwdriver", quantity: 1}]}
    ], requiredItems: [{id: 'i1', name: "Fuel"}, {id: 'i1', name: "Bulb", quantity: 1}]}
]

const HideoutModules = () => {
    return <div className={style.main}>
        <h2>Hideout</h2>
        <div>
            <HideoutModule module={DUMMY_MODULES[0]} />
        </div>
    </div>
}

export default HideoutModules;