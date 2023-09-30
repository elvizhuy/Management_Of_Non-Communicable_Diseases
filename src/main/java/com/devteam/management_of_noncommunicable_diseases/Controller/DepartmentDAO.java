package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Department;
import com.devteam.management_of_noncommunicable_diseases.Model.DepartmentFacilities;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DepartmentDAO {
    Department department = new Department();

    DepartmentFacilities departmentFacilities = new DepartmentFacilities();

    static Connection connection = null;

    static PreparedStatement preparedStatement = null;

    static ResultSet resultSet = null;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    protected void addDepartment(Window owner) throws SQLException {
        String INSERT_DEPARTMENT_QUERY = "INSERT INTO departments (name, description, deleted_at) VALUES (?, ?, ?)";

        boolean checkDepartmentName = validateEmptyDields(department.getName(), "Nhập tên phòng ban", owner);
        boolean checkDescription = validateEmptyDields(department.getDescription(), "Nhâp mô tả", owner);
        boolean checkDeleted_at = validateEmptyDields(department.getName(), "Nhập Ngày xóa", owner);

        if(checkDepartmentName && checkDescription && checkDeleted_at){
            try{
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT_QUERY);
                preparedStatement.setString(1, department.getName());
                preparedStatement.setString(2, department.getDescription());
                preparedStatement.setString(3, String.valueOf(department.getDeletedAt()));
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

    protected boolean validateEmptyDields(String dateField, String textToNotice, Window owner){
        if(dateField.isEmpty()){
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Error !", textToNotice);
            return false;
        }
        return true;
    }
}
