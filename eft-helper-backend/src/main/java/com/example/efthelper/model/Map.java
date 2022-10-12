package com.example.efthelper.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity(name = "maps")
public class Map {

    @Id
    private Integer id;

    private String name;

    private String wiki;

    private String description;

    @ElementCollection
    private List<String> enemies;

    private Integer raidDurationDay;

    private Integer raidDurationNight;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<String> enemies) {
        this.enemies = enemies;
    }

    public Integer getRaidDurationDay() {
        return raidDurationDay;
    }

    public void setRaidDurationDay(Integer raidDurationDay) {
        this.raidDurationDay = raidDurationDay;
    }

    public Integer getRaidDurationNight() {
        return raidDurationNight;
    }

    public void setRaidDurationNight(Integer raidDurationNight) {
        this.raidDurationNight = raidDurationNight;
    }
}
