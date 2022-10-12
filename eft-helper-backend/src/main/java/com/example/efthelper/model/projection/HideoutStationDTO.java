package com.example.efthelper.model.projection;

import java.util.List;

public class HideoutStationDTO {

    private Integer id;

    private String name;

    private String function;

    private String imgSource;

    private List<Integer> modulesIds;

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

    public List<Integer> getModulesIds() {
        return modulesIds;
    }

    public void setModulesIds(List<Integer> modulesIds) {
        this.modulesIds = modulesIds;
    }
}
