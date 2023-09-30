package com.devteam.management_of_noncommunicable_diseases.Model;

import com.devteam.management_of_noncommunicable_diseases.Controller.PositionDao;
import javafx.stage.Window;

import java.sql.SQLException;

public class Position {
    int id;
    String name;
    float bonus;
    Window owner;
    PositionDao positionDao = new PositionDao();
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

    public void add () throws SQLException {
        positionDao.addPosition(owner,this.name,this.bonus);
    }
}
