package com.example.efthelper.model;

import javax.persistence.Embeddable;

@Embeddable
public class Loyalty {

    private int level;

    private int requiredLevel;

    private double requiredReputation;

    private long requiredSales;

    public Loyalty(int level, int requiredLevel, double requiredReputation, long requiredSales) {
        this.level = level;
        this.requiredLevel = requiredLevel;
        this.requiredReputation = requiredReputation;
        this.requiredSales = requiredSales;
    }

    public Loyalty() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public double getRequiredReputation() {
        return requiredReputation;
    }

    public void setRequiredReputation(double requiredReputation) {
        this.requiredReputation = requiredReputation;
    }

    public long getRequiredSales() {
        return requiredSales;
    }

    public void setRequiredSales(long requiredSales) {
        this.requiredSales = requiredSales;
    }

    @Override
    public String toString() {
        return "Loyalty{" +
                "level=" + level +
                ", requiredLevel=" + requiredLevel +
                ", requiredReputation=" + requiredReputation +
                ", requiredSales=" + requiredSales +
                '}';
    }
}
