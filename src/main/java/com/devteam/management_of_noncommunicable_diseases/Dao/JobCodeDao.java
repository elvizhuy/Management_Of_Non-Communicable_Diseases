package com.devteam.management_of_noncommunicable_diseases.Dao;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.JobCode;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobCodeDao {
    static Connection connection = null;

    static PreparedStatement preparedStatement = null;

    static ResultSet resultSet = null;

    public void addJobCode(Window owner,String id, String name, int jobLevel,int grade,float coefficientSalary) throws SQLException {

        String INSERT_JOB_CODE_QUERY = "INSERT INTO job_codes (id,name, job_level,grade,coefficients_salary) VALUES (?,?, ?,?,?)";
        boolean checkJobCodeId = validateEmptyFields(id, "Nhập id job", owner);
        boolean checkJobCodeName = validateEmptyFields(name, "Nhập tên job", owner);
        boolean checkJobCodeLevel = validateEmptyFields(jobLevel, "Nhâp cấp độ job", owner);
        boolean checkJobCodeGrade = validateEmptyFields(grade, "Nhâp thứ hạng job", owner);
        boolean checkCoefficient = validateEmptyFields(coefficientSalary, "Nhâp lương chức danh", owner);

        if(checkCoefficient && checkJobCodeGrade && checkJobCodeLevel && checkJobCodeName){
            try{
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_JOB_CODE_QUERY);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, jobLevel);
                preparedStatement.setInt(4, grade);
                preparedStatement.setFloat(5, coefficientSalary);

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

    public static JobCode searchJobCode () throws SQLException, ClassNotFoundException {
        String SELECT_JOB_CODE = "SELECT * FROM job_codes";
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(SELECT_JOB_CODE);
            return (JobCode) getJobCodeList(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<JobCode> getJobCodeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<JobCode> jobCodesList = FXCollections.observableArrayList();
        while (rs.next()) {
            JobCode jobCode = new JobCode();
            setJobCodeProperties(rs, jobCode);
            jobCodesList.add(jobCode);
        }
        return jobCodesList;
    }

    private static void setJobCodeProperties(ResultSet rs, JobCode jobCode) throws SQLException {
        jobCode.setId(rs.getString("id"));
        jobCode.setName(rs.getString("name"));
        jobCode.setJobLevel(rs.getInt("job_level"));
        jobCode.setGrade(rs.getInt("grade"));
        jobCode.setCoefficientSalary(rs.getFloat("coefficients_salary"));

    }

    protected <T> boolean validateEmptyFields(T dateField, String textToNotice, Window owner){
        if(dateField == null){
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Error !", textToNotice);
            return false;
        }
        return true;
    }
}
