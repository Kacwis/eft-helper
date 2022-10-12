package com.example.efthelper.model.projection;

import java.util.List;

public class HideoutModuleDTO {

    private Integer id;

    private String module;

    private Integer level;

    private Integer stationId;

    private List<Integer> requirementsIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public List<Integer> getRequirementsIds() {
        return requirementsIds;
    }

    public void setRequirementsIds(List<Integer> requirementsIds) {
        this.requirementsIds = requirementsIds;
    }
}
