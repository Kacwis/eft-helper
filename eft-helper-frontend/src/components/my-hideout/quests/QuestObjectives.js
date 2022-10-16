import QuestObjectiveList from "../../ui/QuestObjectiveList";
import QuestObjectiveRow from "../../ui/QuestObjectiveRow";

const QuestObjectives = (props) => {
	const {
		buildObjectives,
		collectObjectives,
		findObjectives,
		keyObjectives,
		killObjectives,
		locateObjectives,
		markObjectives,
		pickupObjectives,
		placeObjectives,
		reputationObjectives,
		skillObjectives,
		warningObjectives,
	} = props.quest;

	const buildObjectivesContent = buildObjectives && (
		<QuestObjectiveList title="Build">
			{buildObjectives.map((bo) => (
				<QuestObjectiveRow objective={bo} />
			))}
		</QuestObjectiveList>
	);

	const collectObjectivesContent = collectObjectives && (
		<QuestObjectiveList title="Collect">
			{collectObjectives.map((co) => (
				<QuestObjectiveRow objective={co} />
			))}
		</QuestObjectiveList>
	);
	const findObjectivesContent = findObjectives && (
		<QuestObjectiveList title="Find">
			{findObjectives.map((fo) => (
				<QuestObjectiveRow objective={fo} />
			))}
		</QuestObjectiveList>
	);
	const keyObjectivesContent = keyObjectives && (
		<QuestObjectiveList title="Open with key">
			{keyObjectives.map((ko) => (
				<QuestObjectiveRow objective={ko} />
			))}
		</QuestObjectiveList>
	);
	const killObjectivesContent = killObjectives && (
		<QuestObjectiveList title="Kill">
			{killObjectives.map((ko) => (
				<QuestObjectiveRow objective={ko} />
			))}
		</QuestObjectiveList>
	);
	const locateObjectivesContent = locateObjectives && (
		<QuestObjectiveList title="Locate">
			{locateObjectives.map((lo) => (
				<QuestObjectiveRow objective={lo} />
			))}
		</QuestObjectiveList>
	);
	const markObjectivesContent = markObjectives && (
		<QuestObjectiveList title="Mark">
			{markObjectives.map((mo) => (
				<QuestObjectiveRow objective={mo} />
			))}
		</QuestObjectiveList>
	);
	const pickupObjectivesContent = pickupObjectives && (
		<QuestObjectiveList title="Pick up">
			{pickupObjectives.map((po) => (
				<QuestObjectiveRow objective={po} />
			))}
		</QuestObjectiveList>
	);
	const placeObjectivesContent = placeObjectives && (
		<QuestObjectiveList title="Place">
			{placeObjectives.map((po) => (
				<QuestObjectiveRow objective={po} />
			))}
		</QuestObjectiveList>
	);
	const reputationObjectivesContent = reputationObjectives && (
		<QuestObjectiveList title="Gain reputation">
			{reputationObjectives.map((ro) => (
				<QuestObjectiveRow objective={ro} />
			))}
		</QuestObjectiveList>
	);
	const skillObjectivesContent = skillObjectives && (
		<QuestObjectiveList title="Gain skill">
			{skillObjectives.map((so) => (
				<QuestObjectiveRow objective={so} />
			))}
		</QuestObjectiveList>
	);
	const warningObjectivesContent = warningObjectives && (
		<QuestObjectiveList title="Warning">
			{warningObjectives.map((wo) => (
				<QuestObjectiveRow objective={wo} />
			))}
		</QuestObjectiveList>
	);

	return (
		<div>
			{buildObjectives && buildObjectivesContent}
			{collectObjectives && collectObjectivesContent}
			{findObjectives && findObjectivesContent}
			{keyObjectives && keyObjectivesContent}
			{killObjectives && killObjectivesContent}
			{locateObjectives && locateObjectivesContent}
			{markObjectives && markObjectivesContent}
			{pickupObjectives && pickupObjectivesContent}
			{placeObjectives && placeObjectivesContent}
			{reputationObjectives && reputationObjectivesContent}
			{skillObjectives && skillObjectivesContent}
			{warningObjectives && warningObjectivesContent}
		</div>
	);
};

export default QuestObjectives;
