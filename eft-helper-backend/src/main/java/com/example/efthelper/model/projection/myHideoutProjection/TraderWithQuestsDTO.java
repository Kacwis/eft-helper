package com.example.efthelper.model.projection.myHideoutProjection;

import com.example.efthelper.model.projection.myHideoutProjection.quest.MyHideoutQuestDTO;

import java.util.Set;

public class TraderWithQuestsDTO {

    private String traderName;

    private Integer traderId;

    Set<MyHideoutQuestDTO> quests;

    public TraderWithQuestsDTO(String traderName, Integer traderId, Set<MyHideoutQuestDTO> quests) {
        this.traderName = traderName;
        this.traderId = traderId;
        this.quests = quests;
    }

    public TraderWithQuestsDTO() {
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Integer getTraderId() {
        return traderId;
    }

    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }

    public Set<MyHideoutQuestDTO> getQuests() {
        return quests;
    }

    public void setQuests(Set<MyHideoutQuestDTO> quests) {
        this.quests = quests;
    }
}
