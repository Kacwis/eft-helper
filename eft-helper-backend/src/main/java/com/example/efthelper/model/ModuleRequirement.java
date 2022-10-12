package com.example.efthelper.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "module_requirements")
public class ModuleRequirement {

    @Id
    private Integer id;

    private String type;

    private String name;

    private Integer quantity;

    @ManyToOne
    private HideoutModule module;

    @ManyToOne
    private Trader trader;


    public Integer getId() {
        return id;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public HideoutModule getModule() {
        return module;
    }

    public void setModule(HideoutModule module) {
        this.module = module;
    }
}
