package com.devteam.management_of_noncommunicable_diseases.DAO;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Specialization;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecializationDao {

    Specialization specialization = new Specialization();
    static Connection connection = null;

    static PreparedStatement preparedStatement = null;

    static ResultSet resultSet = null;
    public void addSpecialization(Window owner, String name, String description) throws SQLException {
        String INSERT_SPECIALIZATION_QUERY = "INSERT INTO specializations (name, description) VALUES (?, ?)";

        boolean checkSpecializationName = validateEmptyFields(name, "Nhập tên chuyên môn", owner);
        boolean checkSpecializationDescription = validateEmptyFields(description, "Nhâp mô tả", owner);

        if(checkSpecializationName && checkSpecializationDescription){
            try{
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_SPECIALIZATION_QUERY);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
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

    public static Specialization searchSpecialization () throws SQLException, ClassNotFoundException {
        String SELECT_SPECIALIZATION = "SELECT * FROM specializations";
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(SELECT_SPECIALIZATION);
            return (Specialization) getSpecializationList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Specialization> getSpecializationList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Specialization> specializationsList = FXCollections.observableArrayList();
        while (rs.next()) {
            Specialization specialization = new Specialization();
            setSpecializationProperties(rs, specialization);
            specializationsList.add(specialization);
        }
        return specializationsList;
    }

    private static void setSpecializationProperties(ResultSet rs, Specialization specialization) throws SQLException {
        specialization.setId(rs.getInt("id"));
        specialization.setName(rs.getString("name"));
        specialization.setDescription(rs.getString("description"));
    }

    protected <T> boolean validateEmptyFields(T dateField, String textToNotice, Window owner){
        if(dateField == null){
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Error !", textToNotice);
            return false;
        }
        return true;
    }
}
