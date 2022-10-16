package com.example.efthelper.service;

import com.example.efthelper.model.*;
import com.example.efthelper.model.projection.NewQuestDTO;
import com.example.efthelper.model.projection.NewQuestReputationDTO;
import com.example.efthelper.model.projection.myHideoutProjection.RequiredItemDTO;
import com.example.efthelper.model.projection.myHideoutProjection.TraderDTO;
import com.example.efthelper.model.projection.myHideoutProjection.quest.MyHideoutQuestDTO;
import com.example.efthelper.model.projection.myHideoutProjection.quest.QuestDetailsDTO;
import com.example.efthelper.model.projection.myHideoutProjection.quest.QuestDetailsObjectiveDTO;
import com.example.efthelper.model.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestService {

    private final QuestReputationRepository reputationRepository;

    private final QuestObjectiveRepository objectiveRepository;

    private final QuestRepository questRepository;

    private final TraderRepository traderRepository;

    private final ItemRepository itemRepository;

    private final MapRepository mapRepository;

    private final UserRepository userRepository;


    public QuestService(final QuestReputationRepository reputationRepository,
                        final QuestObjectiveRepository objectiveRepository,
                        final QuestRepository questRepository,
                        final TraderRepository traderRepository,
                        final ItemRepository itemRepository,
                        final MapRepository mapRepository,
                        final UserRepository userRepository) {
        this.reputationRepository = reputationRepository;
        this.objectiveRepository = objectiveRepository;
        this.questRepository = questRepository;
        this.traderRepository = traderRepository;
        this.itemRepository = itemRepository;
        this.mapRepository = mapRepository;
        this.userRepository = userRepository;
    }

    // Quest

    public List<Quest> findAllQuests() {
        return questRepository.findAll();
    }

    public Quest findByIdQuest(Integer id) {
        if(!questRepository.existsById(id))
            return null;
        return questRepository.findById(id).get();
    }

    public Quest saveQuest(NewQuestDTO questDTO) {
        var questRequiredList = new HashSet<Quest>();
        for(var questId : questDTO.getQuestRequiredIds()){
            var quest = findByIdQuest(questId);
            if(quest != null)
                questRequiredList.add(quest);
        }
        var turningTraderId = questDTO.getTurningTraderId();
        var giverTraderId = questDTO.getGiverTraderId();
        Trader turningTrader = null;
        Trader giverTrader = null;
        if(traderRepository.existsById(turningTraderId))
            turningTrader = traderRepository.findById(turningTraderId).get();
        if(traderRepository.existsById(giverTraderId))
            giverTrader = traderRepository.findById(giverTraderId).get();
        var questObjectivesList = getQuestObjectivesList(questDTO.getQuestObjectivesIds());
        var questReputationList = getQuestReputationsList(questDTO.getQuestReputationsIds());
        var itemsList = getUnlockedItems(questDTO.getUnlockedItemsIds());
        var questToSave = createNewQuest(questDTO.getId(),
                questDTO.getQuestRequirementLevel(),
                questRequiredList,
                giverTrader,
                turningTrader,
                questDTO.getTitle(),
                questDTO.getWiki(),
                questDTO.getExp(),
                questDTO.getGameId(),
                questReputationList,
                itemsList,
                questObjectivesList
        );
        var result = questRepository.save(questToSave);
        questObjectivesList.forEach(qo -> qo.setQuest(result));
        questReputationList.forEach(qr -> qr.setQuest(result));
        questObjectivesList.forEach(qo -> objectiveRepository.save(qo));
        questReputationList.forEach(qr -> reputationRepository.save(qr));
        return result;
    }

    private Set<QuestReputation> getQuestReputationsList(Set<Integer> questsReputationsIds){
        var questReputationList = new HashSet<QuestReputation>();
        for(var questReputationId : questsReputationsIds){
            if(!reputationRepository.existsById(questReputationId)) {
                System.out.println(" --- QueRep --- NO ID --- || id: " + questReputationId);
                continue;
            }
            questReputationList.add(reputationRepository.findById(questReputationId).get());
        }
        return questReputationList;
    }

    private Set<Item> getUnlockedItems(List<String> unlockedItemsIds){
        var itemsList = new HashSet<Item>();
        for(var itemId : unlockedItemsIds){
            if(!itemRepository.existsById(itemId)) {
                System.out.println(" --- QueItem --- NO ID --- || id: " + itemId);
                continue;
            }
            itemsList.add(itemRepository.findById(itemId).get());
        }
        return itemsList;
    }

    private Set<QuestObjective> getQuestObjectivesList(Set<Integer> questObjectivesIds){
        var questObjectivesList = new HashSet<QuestObjective>();
        for(var questObjectiveId : questObjectivesIds){
            if(!objectiveRepository.existsById(questObjectiveId)) {
                System.out.println(" --- QueObj --- NO ID --- || id: " + questObjectiveId);
                continue;
            }
            questObjectivesList.add(objectiveRepository.findById(questObjectiveId).get());
        }
        return questObjectivesList;
    }

    private Quest createNewQuest(Integer id,
                                 Integer questRequirementLevel,
                                 Set<Quest> questRequiredList,
                                 Trader giverTrader,
                                 Trader turningTrader,
                                 String title,
                                 String wiki,
                                 Integer exp,
                                 String gameId,
                                 Set<QuestReputation> questReputationList,
                                 Set<Item> itemsList,
                                 Set<QuestObjective> questObjectivesList){
        var questToSave = new Quest();

        questToSave.setId(id);
        questToSave.setLevelRequired(questRequirementLevel);
        questToSave.setQuestsRequired(questRequiredList);
        questToSave.setGivingTrader(giverTrader);
        questToSave.setTurningTrader(turningTrader);
        questToSave.setTitle(title);
        questToSave.setWiki(wiki);
        questToSave.setExp(exp);
        questToSave.setGameId(gameId);
        questToSave.setReputation(questReputationList);
        questToSave.setUnlockedItems(itemsList);
        questToSave.setObjectives(questObjectivesList);

        return questToSave;
    }

    // My hideout quest

    public Set<MyHideoutQuestDTO> getAllQuestsForUser(String username) throws Exception {
        var user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new Exception("User not found");
        }
        var userQuests = user.get().getQuests();
        var questDTOs = new HashSet<MyHideoutQuestDTO>();
        userQuests.forEach(quest -> questDTOs.add(new MyHideoutQuestDTO(quest.getId(), quest.getTitle())));
        return questDTOs;
    }


    public QuestDetailsDTO getQuestDetailsForUser(Integer questId){
        var questResult = questRepository.findById(questId);
        if(questResult.isEmpty()) {
            return null;
        }
        var quest = questResult.get();
        var questDetailsDTO = new QuestDetailsDTO(
                quest.getId(),
                quest.getTitle(),
                quest.getLevelRequired(),
                quest.getExp(),
                quest.getWiki()
        );
        // All objectives
        questDetailsDTO.setBuildObjectives(getItemObjectives(quest.getObjectives(), "build"));
        questDetailsDTO.setCollectObjectives(getItemAndQuantityObjectives(quest.getObjectives(), "collect"));
        questDetailsDTO.setFindObjectives(getItemAndQuantityObjectives(quest.getObjectives(), "find"));
        questDetailsDTO.setKeyObjectives(getItemObjectives(quest.getObjectives(), "key"));
        questDetailsDTO.setKillObjectives(getTargetAndQuantityObjectives(quest.getObjectives(), "kill"));
        questDetailsDTO.setLocateObjectives(getTargetObjectives(quest.getObjectives(), "locate"));
        questDetailsDTO.setPickupObjectives(getTargetObjectives(quest.getObjectives(), "pickup"));
        questDetailsDTO.setMarkObjectives(getTargetObjectives(quest.getObjectives(), "mark"));
        questDetailsDTO.setWarningObjectives(getTargetObjectives(quest.getObjectives(), "warning"));
        questDetailsDTO.setReputationObjectives(getTraderObjectives(quest.getObjectives()));
        questDetailsDTO.setSkillObjectives(getTargetAndQuantityObjectives(quest.getObjectives(), "skill"));
        questDetailsDTO.setPlaceObjectives(getPlaceObjectives(quest.getObjectives()));
        // traders
        var givingTrader = quest.getGivingTrader();
        var turningTrader = quest.getTurningTrader();
        questDetailsDTO.setGivingTrader(new TraderDTO(givingTrader.getId(), givingTrader.getName()));
        questDetailsDTO.setTurningTrader(new TraderDTO(turningTrader.getId(), turningTrader.getName()));
        return questDetailsDTO;
    }

    private Set<QuestDetailsObjectiveDTO> getPlaceObjectives(Set<QuestObjective> objectives) {
        var currentObjectives = getObjectivesByType(objectives, "place");
        if(currentObjectives.size() == 0){
            return null;
        }
        var placeObjectivesDTOs = new HashSet<QuestDetailsObjectiveDTO>();
        currentObjectives.forEach(questObjective -> {
            var mapResult = mapRepository.findById(questObjective.getLocation());
            var mapName = "All";
            if(mapResult.isPresent()){
                mapName = mapResult.get().getName();
            }
            if(itemRepository.existsById(questObjective.getTarget())){
                var item = itemRepository.findById(questObjective.getTarget()).get();
                placeObjectivesDTOs.add(new QuestDetailsObjectiveDTO(
                        questObjective.getId(),
                        mapName,
                        new RequiredItemDTO(item.getId(), item.getName(), 1),
                        questObjective.getNumber()
                ));
            }
            else{
                placeObjectivesDTOs.add(new QuestDetailsObjectiveDTO(
                        questObjective.getId(),
                        mapName,
                        questObjective.getTarget(),
                        questObjective.getNumber()
                ));
            }
        });
        return placeObjectivesDTOs;
    }

    private Set<QuestDetailsObjectiveDTO> getTraderObjectives(Set<QuestObjective> objectives) {
        var currentObjectives = getObjectivesByType(objectives, "reputation");
        if(currentObjectives.size() == 0){
            return null;
        }
        var traderObjectivesDTOList = new HashSet<QuestDetailsObjectiveDTO>();
        currentObjectives.forEach(questObjective -> {
            var traderResult = traderRepository.findById(Integer.parseInt(questObjective.getTarget()));
            if(traderResult.isEmpty()){
                return;
            }
            var mapName = "All";
            var trader = traderResult.get();
            traderObjectivesDTOList.add(new QuestDetailsObjectiveDTO(
                    questObjective.getId(),
                    mapName,
                    questObjective.getNumber(),
                    new TraderDTO(trader.getId(), trader.getName())
            ));
        });
        return traderObjectivesDTOList;
    }

    private Set<QuestDetailsObjectiveDTO> getTargetObjectives(Set<QuestObjective> objectives, String type) {
        var currentObjectives = getObjectivesByType(objectives, type);
        if(currentObjectives.size() == 0){
            return null;
        }
        var objectivesDTOList = new HashSet<QuestDetailsObjectiveDTO>();
        currentObjectives.forEach(questObjective -> {
            var mapResult = mapRepository.findById(questObjective.getLocation());
            String mapName = "All";
            if(mapResult.isPresent()){
                mapName = mapResult.get().getName();
            }
            objectivesDTOList.add(new QuestDetailsObjectiveDTO(
                    questObjective.getId(),
                    mapName,
                    questObjective.getTarget()
            ));
        });
        return objectivesDTOList;
    }

    private Set<QuestDetailsObjectiveDTO> getTargetAndQuantityObjectives(Set<QuestObjective> objectives, String type) {
        var currentObjectives = getObjectivesByType(objectives, type);
        if(currentObjectives.size() == 0){
            return null;
        }
        var objectivesDTOList = new HashSet<QuestDetailsObjectiveDTO>();
        currentObjectives.forEach(questObjective -> {
            var mapResult = mapRepository.findById(questObjective.getLocation());
            String mapName = "All";
            if(mapResult.isPresent()){
                mapName = mapResult.get().getName();
            }
            objectivesDTOList.add(new QuestDetailsObjectiveDTO(
                    questObjective.getId(),
                    mapName,
                    questObjective.getTarget(),
                    questObjective.getNumber()
            ));
        });
        return objectivesDTOList;
    }

    private Set<QuestDetailsObjectiveDTO> getItemAndQuantityObjectives(Set<QuestObjective> objectives, String type){
        var currentObjectives = getObjectivesByType(objectives, type);
        if(currentObjectives.size() == 0){
            return null;
        }
        var objectivesDetailsList = new HashSet<QuestDetailsObjectiveDTO>();
        currentObjectives.forEach(questObjective -> {
            var itemResult = itemRepository.findById(questObjective.getTarget());
            if(itemResult.isEmpty()){
                return;
            }
            var mapResult = mapRepository.findById(questObjective.getLocation());
            String mapName = "All";
            if(mapResult.isPresent()){
                mapName = mapResult.get().getName();
            }
            var item = itemResult.get();
            var requiredItemDto = new RequiredItemDTO(item.getId(), item.getName(), 1);
            objectivesDetailsList.add(new QuestDetailsObjectiveDTO(
                    questObjective.getId(),
                    mapName,
                    requiredItemDto,
                    questObjective.getNumber()
            ));
        });
        return objectivesDetailsList;
    }

    private Set<QuestDetailsObjectiveDTO> getItemObjectives(Set<QuestObjective> objectives, String type){
        var currentObjectives = getObjectivesByType(objectives, type);
        var objectivesDTOs = new HashSet<QuestDetailsObjectiveDTO>();
        if(currentObjectives.size() == 0){
            return null;
        }
        currentObjectives.forEach(bo -> {
            var itemResult = itemRepository.findById(bo.getTarget());
            if(itemResult.isEmpty()){
                return;
            }
            var item = itemResult.get();
            var requiredItemDTO = new RequiredItemDTO(item.getId(), item.getName(), 1);
            var locationResult = mapRepository.findById(bo.getId());
            String mapName = "All";
            if(locationResult.isPresent()) {
                mapName = locationResult.get().getName();
            }
            else
            objectivesDTOs.add(new QuestDetailsObjectiveDTO(
                    bo.getId(),
                    mapName,
                    requiredItemDTO
            ));
        });
        return objectivesDTOs;
    }

    private List<QuestObjective> getObjectivesByType(Set<QuestObjective> objectives, String type){
        return objectives
                .stream()
                .filter(o -> o.getType().equals(type))
                .collect(Collectors.toList());
    }




    // Quest reputation

    public QuestReputation saveQuestReputation(NewQuestReputationDTO questReputationDTO){
        var traderId = questReputationDTO.getTraderId();
        var reputation = questReputationDTO.getReputation();
        Trader trader;
        var questReputation = new QuestReputation();
        questReputation.setReputation(reputation);
        if(traderRepository.existsById(traderId)){
            trader = traderRepository.findById(traderId).get();
            questReputation.setTrader(trader);
        }
        var result = reputationRepository.save(questReputation);
        return result;
    }

    public List<QuestReputation> findAllQuestReputations(){
        return reputationRepository.findAll();
    }

    public Boolean existsByIdQuestReputation(Integer id){
        return reputationRepository.existsById(id);
    }

    public QuestReputation findByIdQuestReputation(Integer id){
        if(!existsByIdQuestReputation(id))
            return null;
        return reputationRepository.findById(id).get();
    }

    // Quest objective

    public List<QuestObjective> findAllQuestObjectives() {
        return objectiveRepository.findAll();
    }

    public QuestObjective findByIdQuestObjective(Integer id) {
        if(!objectiveRepository.existsById(id))
            return null;
        return objectiveRepository.findById(id).get();
    }

    public QuestObjective saveQuestObjective(QuestObjective questObjective) {
        return objectiveRepository.save(questObjective);
    }


}
