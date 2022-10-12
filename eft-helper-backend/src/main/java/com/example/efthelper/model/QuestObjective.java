package com.example.efthelper.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "objectives")
public class QuestObjective {

    @Id
    private Integer id;

    private String type;

    private Integer number;

    private String target;

    private Integer location;

    private String hint;

    @JsonManagedReference
    @ManyToOne
    private Item tool;

    @ElementCollection
    private List<String> executedWith = new ArrayList<>();

    @JsonManagedReference
    @ManyToOne
    private Quest quest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Item getTool() {
        return tool;
    }

    public void setTool(Item tool) {
        this.tool = tool;
    }

    public List<String> getExecutedWith() {
        return executedWith;
    }

    public void setExecutedWith(List<String> with) {
        this.executedWith = with;
    }
}
