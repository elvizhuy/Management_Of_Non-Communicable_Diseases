package com.devteam.management_of_noncommunicable_diseases.Model;

public class MedicineGroups {
    int id;
    String name;
    String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MedicineGroups() {

    }

    public MedicineGroups(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
