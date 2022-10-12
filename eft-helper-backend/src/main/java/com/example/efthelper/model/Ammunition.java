package com.example.efthelper.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ammo")
public class Ammunition {

    @Id
    private String id;

    private String name;

    private String shortName;

    private Double weight;

    private String caliber;

    private Integer stackMaxSize;

    private Boolean tracer;

    private String tracerColor;

    private String ammoType;

    private Integer projectileCount;

    //BALLISTICS

    private Integer damage;

    private Integer armorDamage;

    private Double fragmentationChance;

    private Double ricochetChance;

    private Double penetrationChance;

    private Integer penetrationPower;

    private Integer accuracy;

    private Integer recoil;

    private Integer initialSpeed;

    public Ammunition(String id,
                      String name,
                      String shortName,
                      Double weight,
                      String caliber,
                      Integer stackMaxSize,
                      Boolean tracer,
                      String tracerColor,
                      String ammoType,
                      int projectileCount,
                      Integer damage,
                      Integer armorDamage,
                      Double fragmentationChance,
                      Double ricochetChance,
                      Double penetrationChance,
                      Integer penetrationPower,
                      Integer accuracy,
                      Integer recoil,
                      Integer initialSpeed) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.weight = weight;
        this.caliber = caliber;
        this.stackMaxSize = stackMaxSize;
        this.tracer = tracer;
        this.tracerColor = tracerColor;
        this.ammoType = ammoType;
        this.projectileCount = projectileCount;
        this.damage = damage;
        this.armorDamage = armorDamage;
        this.fragmentationChance = fragmentationChance;
        this.ricochetChance = ricochetChance;
        this.penetrationChance = penetrationChance;
        this.penetrationPower = penetrationPower;
        this.accuracy = accuracy;
        this.recoil = recoil;
        this.initialSpeed = initialSpeed;
    }

    public Ammunition() {
    }

    public String getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(String ammoType) {
        this.ammoType = ammoType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public Integer getStackMaxSize() {
        return stackMaxSize;
    }

    public void setStackMaxSize(Integer stackMaxSize) {
        this.stackMaxSize = stackMaxSize;
    }

    public Boolean getTracer() {
        return tracer;
    }

    public void setTracer(Boolean tracker) {
        this.tracer = tracker;
    }

    public String getTracerColor() {
        return tracerColor;
    }

    public void setTracerColor(String tracerColor) {
        this.tracerColor = tracerColor;
    }

    public Integer getProjectileCount() {
        return projectileCount;
    }

    public void setProjectileCount(int projectileCount) {
        this.projectileCount = projectileCount;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getArmorDamage() {
        return armorDamage;
    }

    public void setArmorDamage(Integer armorDamage) {
        this.armorDamage = armorDamage;
    }

    public Double getFragmentationChance() {
        return fragmentationChance;
    }

    public void setFragmentationChance(Double fragmentationChance) {
        this.fragmentationChance = fragmentationChance;
    }

    public Double getRicochetChance() {
        return ricochetChance;
    }

    public void setRicochetChance(Double ricochetChance) {
        this.ricochetChance = ricochetChance;
    }

    public Double getPenetrationChance() {
        return penetrationChance;
    }

    public void setPenetrationChance(Double penetrationChance) {
        this.penetrationChance = penetrationChance;
    }

    public Integer getPenetrationPower() {
        return penetrationPower;
    }

    public void setPenetrationPower(Integer penetrationPower) {
        this.penetrationPower = penetrationPower;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getRecoil() {
        return recoil;
    }

    public void setRecoil(Integer recoil) {
        this.recoil = recoil;
    }

    public Integer getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(Integer initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    @Override
    public String toString() {
        return "Ammunition{" +
                "id='" + id + '\n' +
                ", name='" + name + '\n' +
                ", shortName='" + shortName + '\n' +
                ", weight=" + weight +
                ", caliber='" + caliber + '\n' +
                ", stackMaxSize=" + stackMaxSize +
                ", tracer=" + tracer +
                ", tracerColor='" + tracerColor + '\n' +
                ", ammoType='" + ammoType + '\n' +
                ", projectileCount=" + projectileCount +
                ", damage=" + damage +
                ", armorDamage=" + armorDamage +
                ", fragmentationChance=" + fragmentationChance +
                ", ricochetChance=" + ricochetChance +
                ", penetrationChance=" + penetrationChance +
                ", penetrationPower=" + penetrationPower +
                ", accuracy=" + accuracy +
                ", recoil=" + recoil +
                ", initialSpeed=" + initialSpeed +
                '}';
    }
}
