package com.example.efthelper.model.projection;

import java.util.List;

public class QuestObjectiveDTO {

    private Integer id;

    private String type;

    private Integer number;

    private String target;

    private Integer location;

    private String hint;

    private String toolId;

    private List<String> with;

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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public List<String> getWith() {
        return with;
    }

    public void setWith(List<String> with) {
        this.with = with;
    }
}
