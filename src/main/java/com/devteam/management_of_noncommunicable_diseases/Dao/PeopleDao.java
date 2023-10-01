package com.devteam.management_of_noncommunicable_diseases.Dao;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.ComboBoxData;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.People;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PeopleDao implements InfoBox, ComboBoxData {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Window owner;

    public void addPeople(Window owner, String idNumber, String firstName, String lastName, String dateOfBirth, String gender, String address, String phone_number, String email, String note) throws SQLException {
        People people = new People();
        String INSERT_PEOPLE_QUERY = "INSERT into people (id_number,first_name,last_name,date_of_birth,gender,address,phone_number,email,note) VALUES (?,?,?,?,?,?,?,?,?)";
        String INSERT_INSURANCE_QUERY = "INSERT INTO insurance (insurance_id,register_place,start_date,expiration_date) VALUES (?,?,?,?)";
        String INSERT_PATIENT_QUERY = "INSERT INTO patients (people_id,disease_id,first_year_found,first_place_found) VALUES (?,?,?,?)";

        boolean checkIdNumber = validateEmptyFields(idNumber, "Nhập số cccd", owner);
        boolean checkFirstName = validateEmptyFields(firstName, "Nhập tên Họ", owner);
        boolean checkLastName = validateEmptyFields(lastName, "Nhập tên", owner);
        boolean checkDOB = validateEmptyFields(dateOfBirth, "Nhập DOB", owner);
        boolean checkGender = validateEmptyFields(gender, "Nhập giới tính", owner);
        boolean checkAddress = validateEmptyFields(address, "Nhập địa chỉ", owner);
        boolean checkPhoneNumber = validateEmptyFields(phone_number, "Nhập số điện thoại", owner);
        boolean checkEmail = validateEmptyFields(email, "Nhập email", owner);
        boolean checkEmailValid = checkEmailValid(email, owner);
        boolean checkEmptyIdNumber = validateEmptyFields(idNumber, "Nhập số cccd", owner);
        boolean checkEmptyPhoneNumber = validateEmptyFields(phone_number, "Nhập số điện thoại", owner);
        boolean checkExistingIdNumberAndLength = checkIdNumberAndLength(idNumber, "staffs", "ID đã tồn tại!", owner);

        if ( checkIdNumber && checkFirstName && checkLastName && checkEmail && checkPhoneNumber && checkDOB && checkGender && checkAddress && checkEmailValid && checkEmptyIdNumber && checkEmptyPhoneNumber && checkExistingIdNumberAndLength) {
            try {
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_PEOPLE_QUERY);
                preparedStatement.setString(1, idNumber);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, String.valueOf(Date.valueOf(dateOfBirth)));
                preparedStatement.setString(5, gender);
                preparedStatement.setString(6, address);
                preparedStatement.setString(8, phone_number);
                preparedStatement.setString(7, email);
                preparedStatement.setString(9, note);

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
            new Thread(() -> {
                Platform.runLater(() -> {
                    InfoBox.infoBox("Thiếu thông tin,hãy kiểm tra lại", null, "Thất Bại...");
                });
            }).start();
        }
    }

    private static void setPeopleProperties(ResultSet rs, People people) throws SQLException {
        people.setIdNumber(rs.getString("id_number"));
        people.setFirstName(rs.getString("first_name"));
        people.setLastName(rs.getString("last_name"));
        people.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        people.setGender(rs.getString("gender"));
        people.setAddress(rs.getString("address"));
        people.setPhoneNumber(rs.getString("phone_number"));
        people.setEmail(rs.getString("email"));
        people.setNote(rs.getString("note"));

    }

    private static ObservableList<People> getPeopleList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<People> peopleList = FXCollections.observableArrayList();
        while (rs.next()) {
            People people = new People();
            setPeopleProperties(rs, people);
            peopleList.add(people);
        }
        return peopleList;
    }

    public static ObservableList<People> searchPeople (String phone_number, String idNumber) throws SQLException, ClassNotFoundException {
        String SELECT_ALL_PEOPLE =
                """
                        SELECT *
                        FROM people
                """;
        String SELECT_ALL_WITH_CONDITION =
                """
                        SELECT *
                        FROM people
                        where phone_number = ? or id_number = ?
                """;
        try {
            if (phone_number == null || idNumber == null) {
                ResultSet rs = DBConnection.dbExecuteQuery(SELECT_ALL_PEOPLE);
                return getPeopleList(rs);
            }else {
                ResultSet rs = DBConnection.dbPrepareStatementAndExecuteQueryForPeople(SELECT_ALL_WITH_CONDITION, phone_number, idNumber);
                return getPeopleList(rs);
            }
        } catch (SQLException e) {
            System.out.println("Lệnh thực thi thất bại: " + e);
            throw e;
        }
    }

    public static People searchPeopleByIdNumber (String idNumber) throws SQLException, ClassNotFoundException {
        String FIND_SPECIFIC_PEOPLE = "SELECT * FROM people WHERE id_number = " + idNumber;
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(FIND_SPECIFIC_PEOPLE);
            return getPeopleFromResultSet(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static People getPeopleFromResultSet(ResultSet rs) throws SQLException {
        People people = null;
        if (rs.next()) {
            people = new People();
            setPeopleProperties(rs, people);
        }
        return people;
    }

//    public void updatePeople(Window owner) throws SQLException {
//        People people = new People();
//        String FIND_SPECIFIC_PEOPLE = "SELECT * FROM people WHERE id_number = ?";
//        String QUERY_UPDATE_PEOPLE = "UPDATE people SET id_number = ?, first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, address = ?, phone_number = ?, email = ?, note = ? WHERE id_number = ?";
//        try {
//            connection = DBConnection.open();
//            assert connection != null;
//            preparedStatement = connection.prepareStatement(FIND_SPECIFIC_PEOPLE);
//            preparedStatement.setString(1, people.getIdNumber());
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                String id_number = resultSet.getString("id_number");
//                String first_name = resultSet.getString("first_name");
//                String last_name = resultSet.getString("last_name");
//                String date_of_birth = String.valueOf(resultSet.getDate("date_of_birth"));
//                String gender = resultSet.getString("gender");
//                String address = resultSet.getString("address");
//                String phone_number = resultSet.getString("phone_number");
//                String Email = resultSet.getString("email");
//                String note = resultSet.getString("note");
//            }
//            preparedStatement = connection.prepareStatement(QUERY_UPDATE_PEOPLE);
//                preparedStatement.setString(1, idNumber);
//                preparedStatement.setString(2, firstName);
//                preparedStatement.setString(3, lastName);
//                preparedStatement.setString(4, String.valueOf(Date.valueOf(dateOfBirth)));
//                preparedStatement.setString(5, gender);
//                preparedStatement.setString(6, address);
//                preparedStatement.setString(8, phone_number);
//                preparedStatement.setString(7, email);
//                preparedStatement.setString(9, note);
//                preparedStatement.executeUpdate();
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

    protected boolean checkEmailValid(String Email, Window owner) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(Email);
        if (!matcher.matches()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Định dạng email ko hợp lệ");
            return false;
        }
        return true;
    }

    public boolean checkIdNumberAndLength(String IdFromUserInput, String database, String textToNotice, Window owner) throws SQLException {
        String SELECT_ID_NUMBER_QUERY = "SELECT id_number FROM " + database + " WHERE id_number = ?";
        String IdInDatabase = null;
        connection = DBConnection.open();
        assert connection != null;
        preparedStatement = connection.prepareStatement(SELECT_ID_NUMBER_QUERY);
        preparedStatement.setInt(1, Integer.parseInt(IdFromUserInput));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            IdInDatabase = resultSet.getString("id_number");
            Pattern pattern = Pattern.compile("[0-9]{12}");
            Matcher matcher = pattern.matcher(IdFromUserInput);
            if (!Objects.equals(IdFromUserInput, IdInDatabase)) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
                return false;
            } else {
                if (!matcher.matches()) {
                    ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Độ dài không hợp lệ,Id phải đủ 12 số");
                    return false;
                }
            }
        }
        return true;
    }
}
