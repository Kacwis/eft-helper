package com.example.efthelper.model.projection.myHideoutProjection;


import java.util.HashSet;
import java.util.Set;

public class StationLevelDTO {

    private Integer id;

    private Integer level;

    private Set<RequiredItemDTO> requiredItems;

    private Set<RequiredItemDTO> requiredStations;

    public StationLevelDTO(Integer id, Integer level) {
        this.id = id;
        this.level = level;
        this.requiredItems = new HashSet<>();
        this.requiredStations = new HashSet<>();
    }

    public StationLevelDTO() {
    }

    public Set<RequiredItemDTO> getRequiredStations() {
        return requiredStations;
    }

    public void setRequiredStations(Set<RequiredItemDTO> requiredStations) {
        this.requiredStations = requiredStations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Set<RequiredItemDTO> getRequiredItems() {
        return requiredItems;
    }

    public void setRequiredItems(Set<RequiredItemDTO> requiredItems) {
        this.requiredItems = requiredItems;
    }
}
