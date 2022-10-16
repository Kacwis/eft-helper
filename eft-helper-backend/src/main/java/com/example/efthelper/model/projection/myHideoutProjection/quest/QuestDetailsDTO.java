package com.example.efthelper.model.projection.myHideoutProjection.quest;


import com.example.efthelper.model.projection.myHideoutProjection.TraderDTO;

import java.util.Set;

public class QuestDetailsDTO {

    private Integer id;

    private String title;

    private Integer requiredLvl;

    private Integer exp;

    private String wiki;

    private TraderDTO givingTrader;

    private TraderDTO turningTrader;

    private Set<QuestDetailsObjectiveDTO> buildObjectives;

    private Set<QuestDetailsObjectiveDTO> collectObjectives;

    private Set<QuestDetailsObjectiveDTO> findObjectives;

    private Set<QuestDetailsObjectiveDTO> keyObjectives;

    private Set<QuestDetailsObjectiveDTO> killObjectives;

    private Set<QuestDetailsObjectiveDTO> locateObjectives;

    private Set<QuestDetailsObjectiveDTO> markObjectives;

    private Set<QuestDetailsObjectiveDTO> pickupObjectives;

    private Set<QuestDetailsObjectiveDTO> placeObjectives;

    private Set<QuestDetailsObjectiveDTO> reputationObjectives;

    private Set<QuestDetailsObjectiveDTO> skillObjectives;

    private Set<QuestDetailsObjectiveDTO> warningObjectives;


    public QuestDetailsDTO(Integer id, String title, Integer requiredLvl, Integer exp, String wiki) {
        this.id = id;
        this.title = title;
        this.requiredLvl = requiredLvl;
        this.exp = exp;
        this.wiki = wiki;
    }

    public Set<QuestDetailsObjectiveDTO> getBuildObjectives() {
        return buildObjectives;
    }

    public void setBuildObjectives(Set<QuestDetailsObjectiveDTO> buildObjectives) {
        this.buildObjectives = buildObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getCollectObjectives() {
        return collectObjectives;
    }

    public void setCollectObjectives(Set<QuestDetailsObjectiveDTO> collectObjectives) {
        this.collectObjectives = collectObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getFindObjectives() {
        return findObjectives;
    }

    public void setFindObjectives(Set<QuestDetailsObjectiveDTO> findObjectives) {
        this.findObjectives = findObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getKeyObjectives() {
        return keyObjectives;
    }

    public void setKeyObjectives(Set<QuestDetailsObjectiveDTO> keyObjectives) {
        this.keyObjectives = keyObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getKillObjectives() {
        return killObjectives;
    }

    public void setKillObjectives(Set<QuestDetailsObjectiveDTO> killObjectives) {
        this.killObjectives = killObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getLocateObjectives() {
        return locateObjectives;
    }

    public void setLocateObjectives(Set<QuestDetailsObjectiveDTO> locateObjectives) {
        this.locateObjectives = locateObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getMarkObjectives() {
        return markObjectives;
    }

    public void setMarkObjectives(Set<QuestDetailsObjectiveDTO> markObjectives) {
        this.markObjectives = markObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getPickupObjectives() {
        return pickupObjectives;
    }

    public void setPickupObjectives(Set<QuestDetailsObjectiveDTO> pickupObjectives) {
        this.pickupObjectives = pickupObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getPlaceObjectives() {
        return placeObjectives;
    }

    public void setPlaceObjectives(Set<QuestDetailsObjectiveDTO> placeObjectives) {
        this.placeObjectives = placeObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getReputationObjectives() {
        return reputationObjectives;
    }

    public void setReputationObjectives(Set<QuestDetailsObjectiveDTO> reputationObjectives) {
        this.reputationObjectives = reputationObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getSkillObjectives() {
        return skillObjectives;
    }

    public void setSkillObjectives(Set<QuestDetailsObjectiveDTO> skillObjectives) {
        this.skillObjectives = skillObjectives;
    }

    public Set<QuestDetailsObjectiveDTO> getWarningObjectives() {
        return warningObjectives;
    }

    public void setWarningObjectives(Set<QuestDetailsObjectiveDTO> warningObjectives) {
        this.warningObjectives = warningObjectives;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRequiredLvl() {
        return requiredLvl;
    }

    public void setRequiredLvl(Integer requiredLvl) {
        this.requiredLvl = requiredLvl;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public TraderDTO getGivingTrader() {
        return givingTrader;
    }

    public void setGivingTrader(TraderDTO givingTrader) {
        this.givingTrader = givingTrader;
    }

    public TraderDTO getTurningTrader() {
        return turningTrader;
    }

    public void setTurningTrader(TraderDTO turningTrader) {
        this.turningTrader = turningTrader;
    }

}
