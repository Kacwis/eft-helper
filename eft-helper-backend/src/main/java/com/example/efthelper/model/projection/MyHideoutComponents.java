package com.example.efthelper.model.projection;

import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.Quest;
import com.example.efthelper.model.projection.myHideoutProjection.quest.MyHideoutQuestDTO;

import java.util.List;
import java.util.Set;

public class MyHideoutComponents {

    private Set<MyHideoutQuestDTO> quests;

    private Set<HideoutStation> stations;

    public Set<MyHideoutQuestDTO> getQuests() {
        return quests;
    }

    public void setQuests(Set<MyHideoutQuestDTO> quests) {
        this.quests = quests;
    }

    public MyHideoutComponents() {
    }


    public Set<HideoutStation> getStations() {
        return stations;
    }

    public void setStations(Set<HideoutStation> stations) {
        this.stations = stations;
    }
}
