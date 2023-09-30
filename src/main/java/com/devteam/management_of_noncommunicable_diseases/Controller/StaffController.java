package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;

public class StaffController implements Runnable{

    @FXML
    private Button addStaffBtn;
    @FXML
    private TextField user_name;
    @FXML
    private TextField pass_word;
    @FXML
    private TextField confirm_password;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField Email;
    @FXML
    private TextField id_number;
    @FXML
    private TextField phone_number;
    @FXML
    private TextField job_code;
    @FXML
    private TextField specialization;
    @FXML
    private TextField id;
    @FXML
    private DatePicker date_of_birth;
    @FXML
    private DatePicker start_work;
    //các comboBox
    @FXML
    private ComboBox<String> jobCodeComboBox;
    @FXML
    private ComboBox<String> facilityComboBox;
    @FXML
    private ComboBox<String> positionComboBox;
    @FXML
    private ComboBox<String> specializationComboBox;
    @FXML
    private ComboBox<String> departmentComboBox;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Window  owner;
    Staff staff = new Staff();


    @FXML
    protected void initialize() throws SQLException {
//        initializeComboBoxData();
//        owner = addStaffBtn.getScene().getWindow();
    }
    @FXML
    public void start () throws InterruptedException {
        Thread staff = new Thread(new StaffController());
        Thread staffDao = new Thread(new StaffDao());
        staff.start();
        staff.join();
        Thread.sleep(1000);
        staffDao.start();
        staffDao.join();
    }


    protected void addNewStaff(ActionEvent event) throws SQLException {
            staff.setUserName(user_name.getText());
            staff.setFirstName(first_name.getText());
            staff.setLastName(last_name.getText());
            staff.setEmail(Email.getText());
            staff.setIdNumber(id_number.getText());
            staff.setPhoneNumber(phone_number.getText());
            staff.setPassWord(pass_word.getText());
            staff.setConfirm_password(confirm_password.getText());
            staff.setStartWork(start_work.getValue());
    }

    @Override
    public void run() {
        try {
            addNewStaff(new ActionEvent());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private void initializeComboBoxData() throws SQLException {
//        try {
//            connection = DBConnection.open();
//            String SELECT_JOB_CODE_QUERY = "SELECT name FROM job_codes";
//            String SELECT_POSITION_QUERY = "SELECT name FROM positions";
//            String SELECT_SPECIALIZATION_QUERY = "SELECT name FROM specializations";
//            String SELECT_DEPARTMENT_QUERY = "SELECT name FROM departments";
//            String SELECT_FACILITY_QUERY = "SELECT name FROM facilities";
//            String SELECT_FROM_DEPARTMENT_FACILITIES = "SELECT id FROM department_facilities WHERE facility_id = ?,department_id = ?";
//
//            ObservableList<String> jobCodes = getComboBoxData(connection, SELECT_JOB_CODE_QUERY);
//            ObservableList<String> facilities = getComboBoxData(connection, SELECT_FACILITY_QUERY);
//            ObservableList<String> positions = getComboBoxData(connection, SELECT_POSITION_QUERY);
//            ObservableList<String> departments = getComboBoxData(connection, SELECT_DEPARTMENT_QUERY);
//            ObservableList<String> specializations = getComboBoxData(connection, SELECT_SPECIALIZATION_QUERY);
//
//            jobCodeComboBox.setItems(jobCodes);
//            facilityComboBox.setItems(facilities);
//            positionComboBox.setItems(positions);
//            departmentComboBox.setItems(departments);
//            specializationComboBox.setItems(specializations);
//
//        } catch (java.sql.SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
////            DBConnection.closeAll(connection, preparedStatement, resultSet);
//        }
//    }

//    private ObservableList<String> getComboBoxData(Connection connection, String query) throws SQLException {
//        ObservableList<String> comboBoxData = FXCollections.observableArrayList();
//        try {
//            assert connection != null;
//            try (Statement statement = connection.createStatement();
//                     ResultSet resultSet = statement.executeQuery(query)) {
//                while (resultSet.next()) {
//                    String item = resultSet.getString(1);
//                    comboBoxData.add(item);
//                }
//            }
//        } catch (java.sql.SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
////            DBConnection.closeAll(connection, preparedStatement, resultSet);
//        }
//        return comboBoxData;
//    }

//    protected void updateStaff() throws SQLException {
//        try {
//            staff.setId(Integer.parseInt(id.getText()));
//            staff.setIdNumber(id_number.getText());
//            staff.setEmail(Email.getText());
//            staff.setFirstName(first_name.getText());
//            staff.setLastName(last_name.getText());
//            staff.setJobCode(job_code.getText());
//            staff.setPhoneNumber(phone_number.getText());
//            staffDao.updateStaff(owner);
//        } catch (java.sql.SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBConnection.closeAll(connection, preparedStatement, resultSet);
//        }
//    }

    /*protected void disableStaff() {
        String FIND_SPECIFIC_STAFF = "SELECT id_number FROM staffs WHERE id_number = ?";
    }*/
}
