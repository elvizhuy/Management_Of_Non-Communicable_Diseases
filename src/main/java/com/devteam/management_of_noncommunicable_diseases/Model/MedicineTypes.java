package com.devteam.management_of_noncommunicable_diseases.Model;

public class MedicineTypes {
    int id;
    String name;

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

    public MedicineTypes () {}
    public MedicineTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
