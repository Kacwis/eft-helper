package com.example.efthelper.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "modules")
public class HideoutModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonManagedReference
    @ManyToOne
    private HideoutStation station;

    @JsonBackReference
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private Set<ModuleRequirement> requirements = new HashSet<>();

    private String name;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public Set<ModuleRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<ModuleRequirement> requirements) {
        this.requirements = requirements;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HideoutStation getStation() {
        return station;
    }

    public void setStation(HideoutStation station) {
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
