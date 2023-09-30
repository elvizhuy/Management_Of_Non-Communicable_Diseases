package com.devteam.management_of_noncommunicable_diseases.DAO;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Department;
import com.devteam.management_of_noncommunicable_diseases.Model.DepartmentFacilities;
import com.devteam.management_of_noncommunicable_diseases.Model.Department;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public void addDepartment(Window owner,String name, String description) throws SQLException {
        String INSERT_DEPARTMENT_QUERY = "INSERT INTO departments (name, description) VALUES (?, ?)";

        boolean checkDepartmentName = validateEmptyFields(name, "Nhập tên trụ sở", owner);
        boolean checkDepartmentDescription = validateEmptyFields(description, "Nhâp mô tả", owner);

        if(checkDepartmentName && checkDepartmentDescription){
            try{
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT_QUERY);
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

    public static Department searchDepartment () throws SQLException, ClassNotFoundException {
        String SELECT_DEPARTMENT = "SELECT * FROM departments";
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(SELECT_DEPARTMENT);
            return (Department) getDepartmentList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Department> getDepartmentList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Department> departmentsList = FXCollections.observableArrayList();
        while (rs.next()) {
            Department department = new Department();
            setDepartmentProperties(rs, department);
            departmentsList.add(department);
        }
        return departmentsList;
    }

    private static void setDepartmentProperties(ResultSet rs, Department department) throws SQLException {
        department.setId(rs.getInt("id"));
        department.setName(rs.getString("name"));
        department.setDescription(rs.getString("description"));
    }

    protected <T> boolean validateEmptyFields(T dateField, String textToNotice, Window owner){
        if(dateField == null){
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Error !", textToNotice);
            return false;
        }
        return true;
    }
}
