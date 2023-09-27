package com.devteam.management_of_noncommunicable_diseases.Model;

public class Position {
    int id;
    String name;
    float bonus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public Position() {

    }
    public Position(int id, String name, float bonus) {
        this.id = id;
        this.name = name;
        this.bonus = bonus;
    }
}
