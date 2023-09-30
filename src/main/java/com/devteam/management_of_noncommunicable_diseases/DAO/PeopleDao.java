package com.devteam.management_of_noncommunicable_diseases.DAO;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
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

    static Staff staff;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void addPeople(Window owner) throws SQLException {
        People people = new People();
        String INSERT_PEOPLE_QUERY = "INSERT into people (id_number,first_name,last_name,date_of_birth,gender,address,phone_number,email,note) VALUES (?,?,?,?,?,?,?,?,?)";
        String INSERT_INSURANCE_QUERY = "INSERT INTO insurance (insurance_id,register_place,start_date,expiration_date) VALUES (?,?,?,?)";
        String INSERT_PATIENT_QUERY = "INSERT INTO patients (people_id,disease_id,first_year_found,first_place_found) VALUES (?,?,?,?)";

        boolean checkIdNumber = validateEmptyFields(people.getIdNumber(), "Nhập số cccd", owner);
        boolean checkFirstName = validateEmptyFields(people.getFirstName(), "Nhập tên Họ", owner);
        boolean checkLastName = validateEmptyFields(people.getLastName(), "Nhập tên", owner);
        boolean checkDOB = validateEmptyFields(String.valueOf(people.getDateOfBirth()), "Nhập DOB", owner);
        boolean checkGender = validateEmptyFields(String.valueOf(people.getGender()), "Nhập giới tính", owner);
        boolean checkAddress = validateEmptyFields(people.getAddress(), "Nhập địa chỉ", owner);
        boolean checkPhoneNumber = validateEmptyFields(people.getPhoneNumber(), "Nhập số điện thoại", owner);
        boolean checkEmail = validateEmptyFields(people.getEmail(), "Nhập email", owner);

        if ( checkIdNumber && checkFirstName && checkLastName && checkEmail && checkPhoneNumber && checkDOB && checkGender && checkAddress) {
            try {
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_PEOPLE_QUERY);
                preparedStatement.setString(1, people.getIdNumber());
                preparedStatement.setString(2, people.getFirstName());
                preparedStatement.setString(3, people.getLastName());
                preparedStatement.setString(4, String.valueOf(people.getDateOfBirth()));
                preparedStatement.setString(5, String.valueOf(people.getGender()));
                preparedStatement.setString(6, people.getAddress());
                preparedStatement.setString(7, people.getEmail());
                preparedStatement.setString(8, people.getPhoneNumber());
                preparedStatement.setString(9, people.getNote());

//                preparedStatement = connection.prepareStatement(INSERT_INSURANCE_QUERY);
//                preparedStatement.setString(1, staff.getJobCode());
//                preparedStatement.setString(2, staff.getPosition());
//                preparedStatement.setString(3, staff.getFirstName());
//                preparedStatement.setString(4, staff.getLastName());

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

    public void updatePeople(Window owner) throws SQLException {
        People people = new People();
        String FIND_SPECIFIC_PEOPLE = "SELECT id_number FROM people WHERE id_number = ?";
        String QUERY_UPDATE_PEOPLE = "UPDATE people SET id_number = ?, first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, address = ?, phone_number = ?, email = ?, note = ? WHERE id_number = ?";
        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(FIND_SPECIFIC_PEOPLE);
            preparedStatement.setString(1, people.getIdNumber());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id_number = resultSet.getString("id_number");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String date_of_birth = String.valueOf(resultSet.getDate("date_of_birth"));
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String phone_number = resultSet.getString("phone_number");
                String Email = resultSet.getString("email");
                String note = resultSet.getString("note");
            }
            preparedStatement = connection.prepareStatement(QUERY_UPDATE_PEOPLE);
            preparedStatement.setString(1, people.getIdNumber());
            preparedStatement.setString(2, people.getFirstName());
            preparedStatement.setString(3, people.getLastName());
            preparedStatement.setString(4, String.valueOf(people.getDateOfBirth()));
            preparedStatement.setString(5, String.valueOf(people.getGender()));
            preparedStatement.setString(6, people.getAddress());
            preparedStatement.setString(7, people.getPhoneNumber());
            preparedStatement.setString(8, people.getEmail());
            preparedStatement.setString(9, people.getNote());
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

    public boolean checkIdNumber(String IdNumberFromUserInput, String IdNumberInDatabase, String textToNotice, Window owner) {
        if (!Objects.equals(IdNumberFromUserInput, IdNumberInDatabase)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "People not exist !", textToNotice);
            return false;
        }
        return true;
    }
}
