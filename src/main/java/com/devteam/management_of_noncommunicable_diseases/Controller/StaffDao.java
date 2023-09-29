package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffDao implements InfoBox {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void addStaff(Window owner, String username, String firstName, String lastName, String email, String idNumber, String phoneNumber, String passWord, String confirmPassword, String startWork) throws SQLException {
        String INSERT_ACCOUNTS_QUERY = "INSERT into accounts (user_name,password) VALUES (?,?)";
        String INSERT_STAFFS_QUERY = "INSERT into staffs (job_code,position,first_name,last_name,email,id_number,phone_number,start_work) VALUES (?,?,?,?,?,?,?,?)";
        String INSERT_DEPARTMENT_FACILITIES_QUERY = "INSERT INTO department_facilities (facility_id,department_id) VALUES (?,?)";
        String INSERT_DEPARTMENT_FACILITIES_INTO_STAFF_QUERY = "INSERT INTO staffs (department_facilities_id) VALUES (?)";
        String IdInDatabase = null;

        boolean checkNameEmpty = validateEmptyFields(username, "Nhập tên đăng nhập", owner);
        boolean checkNameExisted = checkExistedItem(username,"accounts","Tên đã tồn tại",owner);
//        boolean checkFirstName = validateEmptyFields(staff.getFirstName(), "Nhập tên Họ", owner);
//        boolean checkLastName = validateEmptyFields(staff.getLastName(), "Nhập tên", owner);
//        boolean checkEmail = validateEmptyFields(staff.getEmail(), "Nhập email", owner);
//        boolean checkIdNumber = validateEmptyFields(staff.getIdNumber(), "Nhập số cccd", owner);
//        boolean checkPhoneNumber = validateEmptyFields(staff.getPhoneNumber(), "Nhập số điện thoại", owner);
//        boolean checkPassword = validateEmptyFields(staff.getPassWord(), "Nhập mật khẩu", owner);
//        boolean checkMatchingPass = checkMatchingPassword(staff.getPassWord(), staff.getConfirm_password(), "Mật khẩu không khớp!", owner);
//        boolean checkExistingIdNumber = checkIdNumber(staff.getIdNumber(), null,"ID đã tồn tại!",owner);

//        if (checkName && checkFirstName && checkLastName && checkEmail && checkIdNumber && checkPhoneNumber && checkPassword && checkMatchingPass && checkExistingIdNumber) {
        if (checkNameExisted && checkNameEmpty) {
            try {
                connection = DBConnection.open();
                assert connection != null;
                MD5 md5 = new MD5();
                String encodePassword = md5.encode(passWord);
                preparedStatement = connection.prepareStatement(INSERT_ACCOUNTS_QUERY);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, encodePassword);
//                    preparedStatement = connection.prepareStatement(INSERT_STAFFS_QUERY);
//                    preparedStatement.setString(1, null);
//                    preparedStatement.setString(2, null);
//                    preparedStatement.setString(3, firstName);
//                    preparedStatement.setString(4, lastName);
//                    preparedStatement.setString(5, email);
//                    preparedStatement.setString(6, idNumber);
//                    preparedStatement.setString(7, phoneNumber);
//                    preparedStatement.setDate(8, Date.valueOf(startWork));
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
        } finally {
                DBConnection.closeAll(connection, preparedStatement, resultSet);
            }
        } else {
            new Thread(() -> {
                Platform.runLater(() -> {
                    InfoBox.infoBox("Thiếu thông tin,hãy kiểm tra lại", null, "Thất Bại...");
                });
            }).start();
        }
    }

//    public void updateStaff(Window owner) throws SQLException {
//
//        String FIND_SPECIFIC_STAFF = "SELECT id FROM staffs WHERE id = ?";
//        String QUERY_UPDATE_STAFF = "UPDATE staffs SET id_number = ?, email = ?, first_name = ?, last_name = ?, job_code = ?, phone_number = ? WHERE id = ?";
//        try {
//            connection = DBConnection.open();
//            assert connection != null;
//            preparedStatement = connection.prepareStatement(FIND_SPECIFIC_STAFF);
//            preparedStatement.setInt(1, staff.getId());
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                String id_number = resultSet.getString("id_number");
//                String first_name = resultSet.getString("first_name");
//                String last_name = resultSet.getString("last_name");
//                String idOfUserInDb = resultSet.getString("phone_number");
//                String Email = resultSet.getString("email");
//                String job_code = resultSet.getString("job_code");
//            }
//            preparedStatement = connection.prepareStatement(QUERY_UPDATE_STAFF);
//            preparedStatement.setString(1, staff.getIdNumber());
//            preparedStatement.setString(2, staff.getEmail());
//            preparedStatement.setString(3, staff.getFirstName());
//            preparedStatement.setString(4, staff.getLastName());
//            preparedStatement.setString(5, staff.getJobCode());
//            preparedStatement.setString(6, staff.getPhoneNumber());
//
//            preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBConnection.closeAll(connection, preparedStatement, resultSet);
//        }
//    }

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

    public boolean checkIdNumber(String IdFromUserInput, String IdInDatabase, String textToNotice, Window owner) throws SQLException {
        Staff staff = new Staff();
        String SELECT_ID_NUMBER_QUERY = "SELECT id_number FROM staffs WHERE id_number = ?";
        connection = DBConnection.open();
        assert connection != null;
        preparedStatement = connection.prepareStatement(SELECT_ID_NUMBER_QUERY);
        preparedStatement.setInt(1, Integer.parseInt(staff.getIdNumber()));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            IdInDatabase = resultSet.getString("id_number");
        }
        Pattern pattern = Pattern.compile("[0-9]{12}");
        Matcher matcher = pattern.matcher(IdFromUserInput);
        int ID = Integer.parseInt(IdFromUserInput);
        if (!Objects.equals(IdFromUserInput, IdInDatabase)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        } else {
            if (!matcher.find()) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Độ dài không hợp lệ,Id phải đủ 12 số");
                return false;
            }
        }
        return true;
    }

    public boolean checkExistedItem(String itemFromUserInput,String databaseName, String textToNotice, Window owner) throws SQLException {
        Staff staff = new Staff();
        String SELECT_ITEM_QUERY = "SELECT user_name FROM " + databaseName + " WHERE user_name = ?";
        String itemInDatabase = null;
        connection = DBConnection.open();
        assert connection != null;
        preparedStatement = connection.prepareStatement(SELECT_ITEM_QUERY);
        preparedStatement.setString(1, itemFromUserInput);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            itemInDatabase = resultSet.getString("user_name");
            if (Objects.equals(itemFromUserInput, itemInDatabase)) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
                return false;
            }
        }
        return true;
    }
}
