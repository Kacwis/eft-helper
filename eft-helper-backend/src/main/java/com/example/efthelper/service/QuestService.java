package com.example.efthelper.service;

import com.example.efthelper.model.*;
import com.example.efthelper.model.projection.NewQuestDTO;
import com.example.efthelper.model.projection.NewQuestReputationDTO;
import com.example.efthelper.model.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestService {

    private final QuestReputationRepository reputationRepository;

    private final QuestObjectiveRepository objectiveRepository;

    private final QuestRepository questRepository;

    private final TraderRepository traderRepository;

    private final ItemRepository itemRepository;


    public QuestService(final QuestReputationRepository reputationRepository,
                        final QuestObjectiveRepository objectiveRepository,
                        final QuestRepository questRepository,
                        final TraderRepository traderRepository,
                        final ItemRepository itemRepository ) {
        this.reputationRepository = reputationRepository;
        this.objectiveRepository = objectiveRepository;
        this.questRepository = questRepository;
        this.traderRepository = traderRepository;
        this.itemRepository = itemRepository;
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
