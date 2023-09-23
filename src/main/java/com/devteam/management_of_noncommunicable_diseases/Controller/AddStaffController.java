package com.devteam.management_of_noncommunicable_diseases.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class AddStaffController {

    @FXML
    protected void addStaff(ActionEvent event) throws java.sql.SQLException, SQLException {
        String INSERT_ACCOUNTS_QUERY = "INSERT into accounts (user_name,password) VALUES (?,?)";
        String INSERT_STAFFS_QUERY = "INSERT into staffs (job_code,position,first_name,last_name,email,id_number,phone_number,start_work) VALUES (?,?,?,?,?,?,?,?)";
        String SELECT_JOB_CODE_QUERY = "SELECT id FROM job_codes";
        String SELECT_POSITION_QUERY = "SELECT id FROM positions";
        String SELECT_SPECIALIZATION_QUERY = "SELECT id FROM specializations";
        String SELECT_DEPARTMENT_QUERY = "SELECT id FROM departments";
        String SELECT_FACILITY_QUERY = "SELECT id FROM facilities";
        String INSERT_DEPARTMENT_FACILITIES_QUERY = "INSERT INTO department_facilities (facility_id,department_id) VALUES (?,?)";
        String SELECT_FROM_DEPARTMENT_FACILITIES = "SELECT id FROM department_facilities WHERE facility_id = ?,department_id = ?";
        String INSERT_DEPARTMENT_FACILITIES_INTO_STAFF_QUERY = "INSERT INTO staffs (department_facilities_id) VALUES (?)";

//        String userName = user_name.getText();
//        String passWord = pass_word.getText();
//        String jobCode = job_code.getText();
//        String position = Position.getText();
//        String firstName = first_name.getText();
//        String lastName = last_name.getText();
//        String email = Email.getText();
//        String idNumber = id_number.getText();
//        String phoneNumber = phone_number.getText();
//        String startWork = start_work.getText();
//        int facilityId = facility_id.getInt();
//        int departmentId = department_id.getInt();
        int departmentFacilityId = 0;
    }
}
