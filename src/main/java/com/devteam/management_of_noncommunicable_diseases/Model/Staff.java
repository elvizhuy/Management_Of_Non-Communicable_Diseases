package com.devteam.management_of_noncommunicable_diseases.Model;

import com.devteam.management_of_noncommunicable_diseases.Controller.ShowAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.util.Objects;

public class Staff {
    String userName;
    String firstName;
    String lastName;
    String email;
    String idNumber;
    String phoneNumber;
    String passWord;
    String confirmPassword;
    String jobCode;

    String position;
    String startWork;
    int facilityId;
    int departmentId;
    int departmentFacilityId = 0;
    @FXML
    private Button addStaffBtn;

    Window owner = addStaffBtn.getScene().getWindow();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getConfirm_password() {
        return confirmPassword;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirmPassword = confirm_password;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public Staff() {

    }

    public Staff(String userName, String firstName, String lastName, String email, String idNumber, String phoneNumber, String passWord, String confirmPassword, String jobCode) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.confirmPassword = confirmPassword;
        this.jobCode = jobCode;
    }

    public void addStaff() {
        boolean checkName = validateEmptyFields(this.userName, "Nhập tên đăng nhập", owner);
        boolean checkFirstName = validateEmptyFields(this.firstName, "Nhập tên Họ", owner);
        boolean checkLastName = validateEmptyFields(this.lastName, "Nhập tên", owner);
        boolean checkEmail = validateEmptyFields(this.email, "Nhập email", owner);
        boolean checkIdNumber = validateEmptyFields(this.idNumber, "Nhập số cccd", owner);
        boolean checkPhoneNumber = validateEmptyFields(this.phoneNumber, "Nhập số điện thoại", owner);
        boolean checkPassword = validateEmptyFields(this.passWord, "Nhập mật khẩu", owner);
        boolean checkJobCode = validateEmptyFields(this.jobCode, "Nhập jobcode", owner);
        boolean checkMatchingPass = checkMatchingPassword(this.passWord, this.confirmPassword, "Mật khẩu không khớp!", owner);

        if (checkName && checkFirstName && checkLastName && checkEmail && checkIdNumber && checkPhoneNumber && checkJobCode && checkPassword && checkMatchingPass) {
            // thêm staff
        } else {
            // check lại thông tin
        }
    }

    protected boolean validateEmptyFields(String dataField, String textToNotice, Window owner) {
        if (dataField.isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }

    protected boolean checkMatchingPassword(String passWord, String confirmPassword, String textToNotice, Window owner) {
        if (!Objects.equals(passWord, confirmPassword)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }
}
