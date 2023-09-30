package com.devteam.management_of_noncommunicable_diseases.Dao;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Facility;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityDao {
    static Connection connection = null;

    static PreparedStatement preparedStatement = null;

    static ResultSet resultSet = null;

    Facility facility = new Facility();
    public void addFacility(Window owner, String name, String address,String phone,String email) throws SQLException {
        String INSERT_FACILITY_QUERY = "INSERT INTO facilities (name,address,phone_number,email) VALUES (?, ?,?,?)";

        boolean checkFacilityName = validateEmptyFields(name, "Nhập tên phòng ban", owner);
        boolean checkFacilityAddress = validateEmptyFields(address, "Nhâp địa chỉ", owner);
        boolean checkFacilityPhone = validateEmptyFields(phone, "Nhâp số đt", owner);
        boolean checkFacilityEmail = validateEmptyFields(email, "Nhâp email", owner);

        if(checkFacilityName && checkFacilityAddress && checkFacilityEmail && checkFacilityPhone){
            try{
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_FACILITY_QUERY);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, phone);
                preparedStatement.setString(4, email);
                System.out.println(preparedStatement);
                resultSet = preparedStatement.executeQuery();
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
            finally {
                DBConnection.closeAll(connection, preparedStatement, resultSet);
            }
        }
        else {
            new Thread(() -> {
                Platform.runLater(() -> {
                    InfoBox.infoBox("Thiếu Thông Tin, Vui Long Kiểm Tra Lại !", null, "Thất Bại...");
                });
            }).start();
        }
    }
    public static Facility searchFacility () throws SQLException, ClassNotFoundException {
        String SELECT_FACILITY = "SELECT * FROM facilities";
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(SELECT_FACILITY);
            return (Facility) getFacilityList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Facility> getFacilityList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Facility> positionsList = FXCollections.observableArrayList();
        while (rs.next()) {
           Facility facility = new Facility();
            setFacilityProperties(rs, facility);
            positionsList.add(facility);
        }
        return positionsList;
    }

    private static void setFacilityProperties(ResultSet rs, Facility facility) throws SQLException {
        facility.setId(rs.getInt("id"));
        facility.setName(rs.getString("name"));
        facility.setAddress(rs.getString("address"));
        facility.setPhoneNumber(rs.getString("phone_number"));
        facility.setEmail(rs.getString("email"));
    }

    protected <T> boolean validateEmptyFields(T dateField, String textToNotice, Window owner){
        if(dateField == null){
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Error !", textToNotice);
            return false;
        }
        return true;
    }
}
