package com.example.efthelper.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "item")
public class  Item {

    @Id
    private String id;

    private String name;

    private String shortname;

    @JsonBackReference
    @ManyToMany(mappedBy = "unlockedItems")
    private Set<Quest> quests = new HashSet<>();

    public Item(String id, String name, String shortname) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
    }

    public Item() {
    }

    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return  id + " " +  name + " " +
                 shortname;

    }
}
