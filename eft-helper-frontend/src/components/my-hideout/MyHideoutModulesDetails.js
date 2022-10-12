import { useState } from "react";
import style from "./MyHideoutModuleDetails.module.css";

const MyHideoutModulesDetails = (props) => {
	const [isModuleBuiltPopUpVisible, setIsModuleBuildPopUpVisible] =
		useState(false);

	const module = props.module;

	const moduleBuiltHandler = () => {
		setIsModuleBuildPopUpVisible(true);
	};

	const cancelMarkingAsBuilt = () => {
		setIsModuleBuildPopUpVisible(false);
	};

	if (props.module === null) {
		return <div>Loading...</div>;
	}

	return (
		<>
			<div className={style.main}>
				<div className={style["detail-info"]}>
					<h2 className={style["module-name"]}>{module.name}</h2>
					<p className={style["module-description"]}>{module.description}</p>
				</div>
				<div className={style["required-items"]}>
					<h3>Required Items</h3>
					<div className={style["required-items-list"]}>
						<ul>
							{module.requiredItems.map((item) => (
								<RequiredItemRow name={item.name} />
							))}
						</ul>
					</div>
				</div>
				<div className={style["built-button"]}>
					<button onClick={moduleBuiltHandler}>Mark as Built</button>
				</div>
			</div>
			{isModuleBuiltPopUpVisible && (
				<div className={style.buttons}>
					<button onClick={cancelMarkingAsBuilt}>Cancel</button>
					<button onClick={cancelMarkingAsBuilt}>Confirm</button>
				</div>
			)}
		</>
	);
};

const RequiredItemRow = (props) => {
	return (
		<li className={style["required-items-row"]}>
			<img src="https://images.dog.ceo/breeds/bulldog-french/n02108915_1911.jpg" />
			<p>{props.name}</p>
		</li>
	);
};

export default MyHideoutModulesDetails;
