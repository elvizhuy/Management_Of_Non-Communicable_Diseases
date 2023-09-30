package com.devteam.management_of_noncommunicable_diseases.Model;

import com.devteam.management_of_noncommunicable_diseases.DAO.SpecializationDao;
import javafx.stage.Window;

import java.sql.SQLException;

public class Specialization {
    int id;
    String name;
    String description;
    SpecializationDao specializationDao = new SpecializationDao();

    Window owner;
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

    public Specialization() {

    }

    public Specialization(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void add () throws SQLException {
        specializationDao.addSpecialization(owner,this.name,this.description);
    }
}
