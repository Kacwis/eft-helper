package com.example.efthelper.model.projection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HideoutStationDetailsDTO {

    private Integer id;

    private String name;

    private String description;

    private Set<RequiredItemDTO> requiredItems;

    public HideoutStationDetailsDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requiredItems = new HashSet<>();
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

    public Set<RequiredItemDTO> getRequiredItems() {
        return requiredItems;
    }

    public void setRequiredItems(Set<RequiredItemDTO> requiredItems) {
        this.requiredItems = requiredItems;
    }
}
