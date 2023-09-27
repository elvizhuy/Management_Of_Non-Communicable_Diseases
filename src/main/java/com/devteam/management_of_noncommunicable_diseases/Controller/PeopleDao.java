package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.People;
import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

public class PeopleDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void addStaff(Window owner) throws SQLException {
        People people = new People();
        String INSERT_PEOPLE_QUERY = "INSERT into people (id_number,first_name,last_name,date_of_birth,gender,address,email,phone_number) VALUES (?,?,?,?,?,?,?,?)";
        String INSERT_INSURANCE_QUERY = "INSERT INTO insurance (insurance_id,register_place,start_date,expiration_date) VALUES (?,?,?,?)";
        String INSERT_PATIENT_QUERY = "INSERT INTO patients (people_id,disease_id,first_year_found,first_place_found) VALUES (?,?,?,?)";

        boolean checkName = validateEmptyFields(staff.getUserName(), "Nhập tên đăng nhập", owner);
        boolean checkFirstName = validateEmptyFields(staff.getFirstName(), "Nhập tên Họ", owner);
        boolean checkLastName = validateEmptyFields(staff.getLastName(), "Nhập tên", owner);
        boolean checkEmail = validateEmptyFields(staff.getEmail(), "Nhập email", owner);
        boolean checkIdNumber = validateEmptyFields(staff.getIdNumber(), "Nhập số cccd", owner);
        boolean checkPhoneNumber = validateEmptyFields(staff.getPhoneNumber(), "Nhập số điện thoại", owner);
        boolean checkPassword = validateEmptyFields(staff.getPassWord(), "Nhập mật khẩu", owner);
        boolean checkJobCode = validateEmptyFields(staff.getJobCode(), "Nhập jobcode", owner);
        boolean checkMatchingPass = checkMatchingPassword(staff.getPassWord(), staff.getConfirm_password(), "Mật khẩu không khớp!", owner);

        if (checkName && checkFirstName && checkLastName && checkEmail && checkIdNumber && checkPhoneNumber && checkJobCode && checkPassword && checkMatchingPass) {
            try {
                connection = DBConnection.open();
                assert connection != null;
                MD5 md5 = new MD5();
                String encodePassword = md5.encode(staff.getPassWord());
                preparedStatement = connection.prepareStatement(INSERT_PEOPLE_QUERY);
                preparedStatement.setString(1, people.getIdNumber());
                preparedStatement.setString(2, people.getFirstName());
                preparedStatement.setString(3, people.getLastName());
                preparedStatement.setString(4, String.valueOf(people.getDateOfBirth()));
                preparedStatement.setString(5, String.valueOf(people.getGender()));
                preparedStatement.setString(6, people.getAddress());
                preparedStatement.setString(7, people.getEmail());
                preparedStatement.setString(8, people.getPhoneNumber());

                preparedStatement = connection.prepareStatement(INSERT_INSURANCE_QUERY);
                preparedStatement.setString(1, staff.getJobCode());
                preparedStatement.setString(2, staff.getPosition());
                preparedStatement.setString(3, staff.getFirstName());
                preparedStatement.setString(4, staff.getLastName());

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

    public void updateStaff(Window owner) throws SQLException {
        Staff staff = new Staff();
        String FIND_SPECIFIC_STAFF = "SELECT id FROM staffs WHERE id = ?";
        String QUERY_UPDATE_STAFF = "UPDATE staffs SET id_number = ?, email = ?, first_name = ?, last_name = ?, job_code = ?, phone_number = ? WHERE id = ?";
        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(FIND_SPECIFIC_STAFF);
            preparedStatement.setInt(1, staff.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id_number = resultSet.getString("id_number");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String idOfUserInDb = resultSet.getString("phone_number");
                String Email = resultSet.getString("email");
                String job_code = resultSet.getString("job_code");
            }
            preparedStatement = connection.prepareStatement(QUERY_UPDATE_STAFF);
            preparedStatement.setString(1,staff.getIdNumber());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getFirstName());
            preparedStatement.setString(4, staff.getLastName());
            preparedStatement.setString(5, staff.getJobCode());
            preparedStatement.setString(6, staff.getPhoneNumber());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
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

    public boolean checkIdNumber(String IdFromUserInput, String IdInDatabase, String textToNotice, Window owner) {
        if (!Objects.equals(IdFromUserInput, IdInDatabase)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }
}
