package com.devteam.management_of_noncommunicable_diseases.DAO;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Position;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDao {
    Position position = new Position();
    static Connection connection = null;

    static PreparedStatement preparedStatement = null;

    static ResultSet resultSet = null;
    public void addPosition(Window owner,String name,Float bonus) throws SQLException {
        String INSERT_POSITION_QUERY = "INSERT INTO positions (name, bonus) VALUES (?, ?)";

        boolean checkPositionName = validateEmptyFields(name, "Nhập tên vị trí", owner);
        boolean checkPositionDescription = validateEmptyFields(bonus, "Nhâp bonus", owner);

        if(checkPositionName && checkPositionDescription){
            try{
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_POSITION_QUERY);
                preparedStatement.setString(1, name);
                preparedStatement.setFloat(2, bonus);
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
    public static Position searchPosition () throws SQLException, ClassNotFoundException {
        String SELECT_POSITION = "SELECT * FROM positions";
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(SELECT_POSITION);
            return (Position) getPositionList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Position> getPositionList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Position> positionsList = FXCollections.observableArrayList();
        while (rs.next()) {
           Position position = new Position();
            setPositionProperties(rs, position);
            positionsList.add(position);
        }
        return positionsList;
    }

    private static void setPositionProperties(ResultSet rs, Position position) throws SQLException {
        position.setId(rs.getInt("id"));
        position.setName(rs.getString("name"));
        position.setBonus(rs.getFloat("bonus"));
    }

    protected <T> boolean validateEmptyFields(T dateField, String textToNotice, Window owner){
        if(dateField == null){
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Error !", textToNotice);
            return false;
        }
        return true;
    }
}
