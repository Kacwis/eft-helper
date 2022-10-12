package com.example.efthelper.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "quest")
public class Quest {

    @Id
    private Integer id;

    private Integer levelRequired;

    @JsonBackReference
    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private Set<Quest> questsRequired;

    @JsonManagedReference
    @ManyToOne
    private Quest quest;

    @JsonBackReference
    @ManyToOne
    private Trader givingTrader;

    @JsonBackReference
    @ManyToOne
    private Trader turningTrader;

    private String title;

    private String wiki;

    private Integer exp;

    private String gameId;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private Set<QuestReputation> reputation = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "quest_items",
            joinColumns = {@JoinColumn(name = "quests_id")},
            inverseJoinColumns = {@JoinColumn(name = "items_id")}
    )
    private Set<Item> unlockedItems = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestObjective> objectives = new HashSet<>();


    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Set<QuestObjective> getObjectives() {
        return objectives;
    }

    public void setObjectives(Set<QuestObjective> objectives) {
        this.objectives = objectives;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(Integer levelRequired) {
        this.levelRequired = levelRequired;
    }

    public Set<Quest> getQuestsRequired() {
        return questsRequired;
    }

    public void setQuestsRequired(Set<Quest> questsRequired) {
        this.questsRequired = questsRequired;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Trader getGivingTrader() {
        return givingTrader;
    }

    public void setGivingTrader(Trader givingTrader) {
        this.givingTrader = givingTrader;
    }

    public Trader getTurningTrader() {
        return turningTrader;
    }

    public void setTurningTrader(Trader turningTrader) {
        this.turningTrader = turningTrader;
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

    public Set<Item> getUnlockedItems() {
        return unlockedItems;
    }

    public void setUnlockedItems(Set<Item> unlockedItems) {
        this.unlockedItems = unlockedItems;
    }

    public Set<QuestReputation> getReputation() {
        return reputation;
    }

    public void setReputation(Set<QuestReputation> reputation) {
        this.reputation = reputation;
    }
}
