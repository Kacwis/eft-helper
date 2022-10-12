package com.example.efthelper.model.projection;

import java.util.List;
import java.util.Set;

public class NewQuestDTO {

    private Integer id;

    private Integer questRequirementLevel;

    private List<Integer> questRequiredIds;

    private Integer turningTraderId;

    private Integer giverTraderId;

    private String title;

    private String wiki;

    private Integer exp;

    private String gameId;

    private Set<Integer> questObjectivesIds;

    private Set<Integer> questReputationsIds;

    private List<String> unlockedItemsIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestRequirementLevel() {
        return questRequirementLevel;
    }

    public void setQuestRequirementLevel(Integer questRequirementLevel) {
        this.questRequirementLevel = questRequirementLevel;
    }

    public Integer getTurningTraderId() {
        return turningTraderId;
    }

    public void setTurningTraderId(Integer turningTraderId) {
        this.turningTraderId = turningTraderId;
    }

    public Integer getGiverTraderId() {
        return giverTraderId;
    }

    public void setGiverTraderId(Integer giverTraderId) {
        this.giverTraderId = giverTraderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Set<Integer> getQuestObjectivesIds() {
        return questObjectivesIds;
    }

    public void setQuestObjectivesIds(Set<Integer> questObjectivesIds) {
        this.questObjectivesIds = questObjectivesIds;
    }

    public Set<Integer> getQuestReputationsIds() {
        return questReputationsIds;
    }

    public List<Integer> getQuestRequiredIds() {
        return questRequiredIds;
    }

    public void setQuestRequiredIds(List<Integer> questRequiredIds) {
        this.questRequiredIds = questRequiredIds;
    }

    public void setQuestReputationsIds(Set<Integer> questReputationsIds) {
        this.questReputationsIds = questReputationsIds;
    }

    public List<String> getUnlockedItemsIds() {
        return unlockedItemsIds;
    }

    public void setUnlockedItemsIds(List<String> unlockedItemsIds) {
        this.unlockedItemsIds = unlockedItemsIds;
    }

    @Override
    public String toString() {
        var sBuilder = new StringBuilder();
        sBuilder.append(getId() + " " + getTitle() + "\n");
        sBuilder.append(getTurningTraderId() + " turnin | giver " + getGiverTraderId() + "\n");
        sBuilder.append(getWiki() + " " + getExp() + " " + getGameId() + "\n");
        sBuilder.append("Quest Required Ids: \n");
        for (var id : getQuestRequiredIds()){
            sBuilder.append(id + "\n");
        }
        sBuilder.append("Quest objectives ids\n");
        for(var id : getQuestObjectivesIds()){
            sBuilder.append(id + "\n");
        }
        sBuilder.append("Quest Reputation ids:\n");
        for(var id : getQuestReputationsIds()){
            sBuilder.append(id + "\n");
        }
        sBuilder.append("Unlocked items ids:\n");
        for(var id : getUnlockedItemsIds()){
            sBuilder.append(id + "\n");
        }
        return sBuilder.toString();
    }
}
