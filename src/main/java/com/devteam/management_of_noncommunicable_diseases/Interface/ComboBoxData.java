package com.devteam.management_of_noncommunicable_diseases.Interface;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.SQLException;

public interface ComboBoxData {
    static ObservableList<String> getComboBoxData(Connection connection, String query, PreparedStatement preparedStatement) throws SQLException {
        ObservableList<String> comboBoxData = FXCollections.observableArrayList();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String item = resultSet.getString(1);
                comboBoxData.add(item);
            }
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ResultSet resultSet = null;
            DBConnection.closeAll(connection, preparedStatement, null);
        }
        return comboBoxData;
    }
}
