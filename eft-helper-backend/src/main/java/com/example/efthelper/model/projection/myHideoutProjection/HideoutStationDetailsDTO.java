package com.example.efthelper.model.projection.myHideoutProjection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HideoutStationDetailsDTO {

    private Integer id;

    private String name;

    private String description;

    private Set<StationLevelDTO> stationLevels;

    public HideoutStationDetailsDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stationLevels = new HashSet<>();
    }

    public HideoutStationDetailsDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StationLevelDTO> getStationLevels() {
        return stationLevels;
    }

    public void setStationLevels(Set<StationLevelDTO> requiredItems) {
        this.stationLevels = requiredItems;
    }
}
