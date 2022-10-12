package com.example.efthelper.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "traders")
public class Trader {

    @Id
    private Integer id;

    private String name;

    private String fullName;

    private String wiki;

    @ElementCollection
    private List<String> currencies = new ArrayList<>();

    @ElementCollection
    private List<Loyalty> loyalties = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "givingTrader")
    private Set<Quest> givingGuests = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "turningTrader")
    private Set<Quest> turningQuests = new HashSet<>();

    @OneToMany(mappedBy = "trader")
    private Set<ModuleRequirement> moduleRequirements = new HashSet<>();

    public Trader(Integer id, String name, String fullName, String wiki, List<String> currencies, List<Loyalty> loyalties) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.wiki = wiki;
        this.currencies = currencies;
        this.loyalties = loyalties;
    }

    public Trader() {
    }

    public Set<ModuleRequirement> getModuleRequirements() {
        return moduleRequirements;
    }

    public void setModuleRequirements(Set<ModuleRequirement> moduleRequirements) {
        this.moduleRequirements = moduleRequirements;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }


    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<Loyalty> getLoyalties() {
        return loyalties;
    }

    public void setLoyalties(List<Loyalty> loyalties) {
        this.loyalties = loyalties;
    }

    public Set<Quest> getGivingGuests() {
        return givingGuests;
    }

    public void setGivingGuests(Set<Quest> givingGuests) {
        this.givingGuests = givingGuests;
    }

    public Set<Quest> getTurningQuests() {
        return turningQuests;
    }

    public void setTurningQuests(Set<Quest> turningQuests) {
        this.turningQuests = turningQuests;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", wiki='" + wiki + '\'' +
                ", currencies=" + currencies +
                ", loyalties=" + loyalties +
                '}';
    }
}
