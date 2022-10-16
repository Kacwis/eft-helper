package com.example.efthelper.model.projection.myHideoutProjection.quest;

import com.example.efthelper.model.projection.myHideoutProjection.RequiredItemDTO;
import com.example.efthelper.model.projection.myHideoutProjection.TraderDTO;

public class QuestDetailsObjectiveDTO {

    private Integer id;

    private String location;

    private RequiredItemDTO requiredItem;

    private String target;

    private Integer quantity;

    private TraderDTO trader;

    // Objective with item and quantity constructor

    public QuestDetailsObjectiveDTO(Integer id,String location, RequiredItemDTO requiredItem, Integer quantity) {
        this.id = id;
        this.location = location;
        this.requiredItem = requiredItem;
        this.quantity = quantity;
    }

    // Objective with item only constructor

    public QuestDetailsObjectiveDTO(Integer id, String location, RequiredItemDTO requiredItem) {
        this.id = id;
        this.location = location;
        this.requiredItem = requiredItem;
    }

    // Objective with target and quantity constructor

    public QuestDetailsObjectiveDTO(Integer id, String location, String target, Integer quantity) {
        this.id = id;
        this.location = location;
        this.target = target;
        this.quantity = quantity;
    }

    // Objective with target constructor

    public QuestDetailsObjectiveDTO(Integer id, String location, String target) {
        this.id = id;
        this.location = location;
        this.target = target;
    }

    // Objective with trader constructor


    public QuestDetailsObjectiveDTO(Integer id, String location, Integer quantity, TraderDTO trader) {
        this.id = id;
        this.location = location;
        this.quantity = quantity;
        this.trader = trader;
    }

    public RequiredItemDTO getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(RequiredItemDTO requiredItem) {
        this.requiredItem = requiredItem;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TraderDTO getTrader() {
        return trader;
    }

    public void setTrader(TraderDTO trader) {
        this.trader = trader;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
