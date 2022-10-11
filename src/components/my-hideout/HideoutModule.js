import HideoutModuleItem from "./HideoutModuleItem";
import style from './HideoutModule.module.css';
import { useState } from "react";


const HideoutModule = (props) => {

    const intialState = [
    ]

    props.module.requiredModules.forEach(module => {
        intialState.push({id: module.id, isActive: false})
        
    });

    const [showModuleButtons, setShowModuleButtons] = useState(intialState);

    const hideoutItemsContent = props.module.requiredItems.map((item) => {
        return <HideoutModuleItem name={item.name} quantity={item.quantity} />
    })

    const onRequiredModulesShowButtonHandler = (moduleId) => {
        const showModuleButtonsCopy = showModuleButtons;
        const currentModuleIndex = showModuleButtonsCopy.findIndex(moduleButton => {
            return moduleButton.id === moduleId;
        })
        const currentModuleButtonState = showModuleButtonsCopy[currentModuleIndex].isActive;
        showModuleButtonsCopy.splice(currentModuleIndex, 1, {id: moduleId, isActive: !currentModuleButtonState})
        console.log(currentModuleButtonState);
        setShowModuleButtons(showModuleButtonsCopy);
    }

    const hideoutRequiredModulesContent = props.module.requiredModules.map((module) => {
        return <li>
            <p>{module.name}</p>
            <button onClick={() => onRequiredModulesShowButtonHandler(module.id)}>Show</button>
            {showModuleButtons.find((mb) => mb.id === module.id).isActive && 
            <div>
                {module.requiredItems.map((item) => {
                    return <HideoutModuleItem name={item.name} quantity={item.quantity} />
                })}
            </div>
            }
        </li>
    })


    return <div className={style.main}>
        <h3>{props.module.name}</h3>
        <div className={style.main}>
            <h3>Required items</h3>
            <div>
                <ul>
                    {hideoutItemsContent}
                </ul>
            </div>
            <h3>Required modules</h3>
            <div>
                <ul>
                    {hideoutRequiredModulesContent}
                </ul>
            </div>
        </div>
    </div>
}

export default HideoutModule;