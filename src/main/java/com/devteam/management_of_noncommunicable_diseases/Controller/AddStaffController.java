package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddStaffController{
    @FXML
    private TextField user_name;
    @FXML
    private TextField pass_word;
    @FXML
    private TextField confirm_password;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField Email;
    @FXML
    private TextField id_number;
    @FXML
    private TextField phone_number;
    @FXML
    private TextField job_code;

    @FXML
    protected void addNewStaff(ActionEvent event) {
        Staff staff = new Staff();
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

        staff.setUserName(user_name.getText());
        staff.setFirstName(first_name.getText());
        staff.setLastName(last_name.getText());
        staff.setEmail(Email.getText());
        staff.setIdNumber(id_number.getText());
        staff.setPhoneNumber(phone_number.getText());
        staff.setJobCode(job_code.getText());
        staff.setPassWord(pass_word.getText());
        staff.setConfirm_password(confirm_password.getText());

        staff.addStaff();

//        String position = Position.getText();
//        String startWork = start_work.getText();
//        int facilityId = facility_id.getInt();
//        int departmentId = department_id.getInt();

    }
}
