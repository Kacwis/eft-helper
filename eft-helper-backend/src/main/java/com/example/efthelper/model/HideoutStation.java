package com.example.efthelper.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "stations")
public class HideoutStation {

    @Id
    private Integer id;

    private String name;

    private String function;

    private String imgSource;

    @JsonBackReference
    @OneToMany(mappedBy = "station", cascade = {CascadeType.MERGE, CascadeType.ALL})
    private Set<HideoutModule> modules = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public Set<HideoutModule> getModules() {
        return modules;
    }

    public void setModules(Set<HideoutModule> modules) {
        this.modules = modules;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }
}
