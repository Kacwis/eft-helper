package com.example.efthelper.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity(name = "reputation")
public class QuestReputation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double reputation;

    @JsonBackReference
    @ManyToOne
    private Trader trader;

    @JsonBackReference
    @ManyToOne
    private Quest quest;


    public QuestReputation() {
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    @Override
    public String toString() {
        return "QuestReputation{" +
                "id=" + id +
                ", reputation=" + reputation +
                ", quest=" + quest +
                '}';
    }


}
