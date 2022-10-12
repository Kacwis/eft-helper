package com.example.efthelper.model.projection;

public class NewQuestReputationDTO {

    private Double reputation;

    private Integer traderId;

    public NewQuestReputationDTO() {
    }

    public NewQuestReputationDTO(Double reputation, Integer traderId) {
        this.reputation = reputation;
        this.traderId = traderId;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Integer getTraderId() {
        return traderId;
    }

    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }
}
