package com.devteam.management_of_noncommunicable_diseases.Model;

import java.time.LocalDateTime;

public class Medicine {
    int id;
    int groupId;
    int typeId;
    String name;
    String unit;
    String description;
    String instruction;

    LocalDateTime deleteAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }

    public Medicine() {
    }

    public Medicine(int id, int groupId, int typeId, String name, String unit, String description, String instruction, LocalDateTime deleteAt) {
        this.id = id;
        this.groupId = groupId;
        this.typeId = typeId;
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.instruction = instruction;
        this.deleteAt = deleteAt;
    }
}
