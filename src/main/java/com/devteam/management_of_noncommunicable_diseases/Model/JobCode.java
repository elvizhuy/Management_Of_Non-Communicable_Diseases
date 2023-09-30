package com.devteam.management_of_noncommunicable_diseases.Model;

import com.devteam.management_of_noncommunicable_diseases.Dao.JobCodeDao;
import javafx.stage.Window;

import java.sql.SQLException;

public class JobCode {
    String id;
    String name;
    int jobLevel;
    int grade;
    float coefficientSalary;
    Window owner;
    JobCodeDao jobCodeDao;

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

    public int getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(int jobLevel) {
        this.jobLevel = jobLevel;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public float getCoefficientSalary() {
        return coefficientSalary;
    }

    public void setCoefficientSalary(float coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }

    public JobCode() {

    }

    public JobCode(String id, String name, int jobLevel, int grade, float coefficientSalary) {
        this.id = id;
        this.name = name;
        this.jobLevel = jobLevel;
        this.grade = grade;
        this.coefficientSalary = coefficientSalary;
    }

    public void add ()  throws SQLException {
        jobCodeDao = new JobCodeDao();
        jobCodeDao.addJobCode(owner,this.id,this.name,this.jobLevel,this.grade,this.coefficientSalary);
    }

}
