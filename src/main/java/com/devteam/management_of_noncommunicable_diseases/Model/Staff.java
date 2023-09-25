package com.devteam.management_of_noncommunicable_diseases.Model;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

public class Staff extends SQLException {
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
    LocalDate startWork;
    int facilityId;
    int departmentId;
    int departmentFacilityId = 0;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

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

    public LocalDate getStartWork() {
        return startWork;
    }

    public void setStartWork(LocalDate startWork) {
        this.startWork = startWork;
    }

    public Staff() {

    }

    public Staff(String userName, String firstName, String lastName, String email, String idNumber, String phoneNumber, String passWord, String confirmPassword, String jobCode,LocalDate startWork) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.confirmPassword = confirmPassword;
        this.jobCode = jobCode;
        this.startWork = startWork;
    }

    public void addStaff(Window owner, String INSERT_ACCOUNTS_QUERY, String INSERT_STAFFS_QUERY) throws SQLException {
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
            try {
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_ACCOUNTS_QUERY);
                preparedStatement.setString(1, this.userName);
                preparedStatement.setString(2, this.passWord);
                preparedStatement = connection.prepareStatement(INSERT_STAFFS_QUERY);
                preparedStatement.setString(1, this.jobCode);
                preparedStatement.setString(2, this.position);
                preparedStatement.setString(3, this.firstName);
                preparedStatement.setString(4, this.lastName);
                preparedStatement.setString(5, this.email);
                preparedStatement.setString(6, this.idNumber);
                preparedStatement.setString(7, this.phoneNumber);
                preparedStatement.setDate(8, Date.valueOf(this.startWork));
                System.out.println(preparedStatement);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBConnection.closeAll(connection, preparedStatement, resultSet);
            }
        } else {
            // check lại thông tin
        }
    }

    public void updateStaff (Window owner, String SELECT_GET_ID_NUMBER_QUERY) throws SQLException {
        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(SELECT_GET_ID_NUMBER_QUERY);
            preparedStatement.setString(1,this.idNumber);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idOfUserInDb = resultSet.getString("id_number");
                boolean checkUserIdNumber = checkIdNumber(this.idNumber,idOfUserInDb,"CCCD đã tồn tại trên hệ thống!",owner);
                if (checkUserIdNumber) {
                    // thực hiện render user đó để update
                }else {
                    // hỏi có muốn add staff hay ko
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
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

    public boolean checkIdNumber (String IdFromUserInput,String IdInDatabase,String textToNotice, Window owner) {
        if (!Objects.equals(IdFromUserInput, IdInDatabase)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }
}
