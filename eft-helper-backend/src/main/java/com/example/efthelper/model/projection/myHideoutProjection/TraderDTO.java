package com.example.efthelper.model.projection.myHideoutProjection;

public class TraderDTO {

    private Integer id;

    private String name;


    public TraderDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TraderDTO() {
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
}
