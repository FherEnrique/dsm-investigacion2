package com.test.dsm_taller2.models;

public class Restaurant {
    private int id;
    private String name;
    private String owner;
    private FoodStage menu;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public FoodStage getMenu() {
        return menu;
    }
}
