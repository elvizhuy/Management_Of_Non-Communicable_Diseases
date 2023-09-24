package com.devteam.management_of_noncommunicable_diseases.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;
import java.util.Objects;

public class AddStaffController {
    @FXML
    private Button addStaffBtn;
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
    Window owner = addStaffBtn.getScene().getWindow();

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

        String userName = user_name.getText();
        String firstName = first_name.getText();
        String lastName = last_name.getText();
        String email = Email.getText();
        String idNumber = id_number.getText();
        String phoneNumber = phone_number.getText();
        String passWord = pass_word.getText();
        String confirm_password = pass_word.getText();
        String jobCode = job_code.getText();

        boolean checkName = validateEmptyFields(userName, "Nhập tên đăng nhập");
        boolean checkFirstName = validateEmptyFields(firstName, "Nhập tên Họ");
        boolean checkLastName = validateEmptyFields(lastName, "Nhập tên");
        boolean checkEmail = validateEmptyFields(email, "Nhập email");
        boolean checkIdNumber = validateEmptyFields(idNumber, "Nhập số cccd");
        boolean checkPhoneNumber = validateEmptyFields(phoneNumber, "Nhập số điện thoại");
        boolean checkPassword = validateEmptyFields(passWord, "Nhập mật khẩu");
        boolean checkJobCode = validateEmptyFields(jobCode, "Nhập jobcode");
        boolean checkMatchingPass = checkMatchingPassword(passWord, confirm_password, "Mật khẩu không khớp!");

        if (checkName && checkFirstName && checkLastName && checkEmail && checkIdNumber && checkPhoneNumber && checkJobCode && checkPassword && checkMatchingPass) {
            // thêm staff
        } else {
            // check lại thông tin
        }

//        String position = Position.getText();
//        String startWork = start_work.getText();
//        int facilityId = facility_id.getInt();
//        int departmentId = department_id.getInt();
        int departmentFacilityId = 0;

    }

    protected boolean validateEmptyFields(String dataField, String textToNotice) {
        if (dataField.isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }

    protected boolean checkMatchingPassword(String passWord, String confirmPassword, String textToNotice) {
        if (!Objects.equals(passWord, confirmPassword)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }
}
