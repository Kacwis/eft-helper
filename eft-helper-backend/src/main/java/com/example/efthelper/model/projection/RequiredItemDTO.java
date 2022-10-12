package com.example.efthelper.model.projection;

public class RequiredItemDTO {

    private String name;

    private String id;


    public RequiredItemDTO(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public RequiredItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
