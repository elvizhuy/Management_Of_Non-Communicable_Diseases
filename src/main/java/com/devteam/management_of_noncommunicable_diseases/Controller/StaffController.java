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

import java.sql.*;


public class StaffController {
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

    private DBConnection dbConnection;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    Window owner = addStaffBtn.getScene().getWindow();

    @FXML
    protected void initialize() throws SQLException {

        initializeComboBoxData();
    }
    Staff staff = new Staff();
    @FXML
    protected void addNewStaff(ActionEvent event) throws SQLException {
        String INSERT_ACCOUNTS_QUERY = "INSERT into accounts (user_name,password) VALUES (?,?)";
        String INSERT_STAFFS_QUERY = "INSERT into staffs (job_code,position,first_name,last_name,email,id_number,phone_number,start_work) VALUES (?,?,?,?,?,?,?,?)";
        String INSERT_DEPARTMENT_FACILITIES_QUERY = "INSERT INTO department_facilities (facility_id,department_id) VALUES (?,?)";
        String INSERT_DEPARTMENT_FACILITIES_INTO_STAFF_QUERY = "INSERT INTO staffs (department_facilities_id) VALUES (?)";

        staff.setUserName(user_name.getText());
        staff.setFirstName(first_name.getText());
        staff.setLastName(last_name.getText());
        staff.setEmail(Email.getText());
        staff.setIdNumber(id_number.getText());
        staff.setPhoneNumber(phone_number.getText());
        staff.setJobCode(job_code.getText());
        staff.setPassWord(pass_word.getText());
        staff.setConfirm_password(confirm_password.getText());
        staff.setStartWork(start_work.getValue());

        staff.addStaff(owner, INSERT_ACCOUNTS_QUERY, INSERT_STAFFS_QUERY);

//        String position = Position.getText();
//        String startWork = start_work.getText();
//        int facilityId = facility_id.getInt();
//        int departmentId = department_id.getInt();

    }

    private void initializeComboBoxData() throws SQLException {
        try {

            Connection connection = DBConnection.open();

            String SELECT_JOB_CODE_QUERY = "SELECT id FROM job_codes";
            String SELECT_POSITION_QUERY = "SELECT id FROM positions";
            String SELECT_SPECIALIZATION_QUERY = "SELECT id FROM specializations";
            String SELECT_DEPARTMENT_QUERY = "SELECT id FROM departments";
            String SELECT_FACILITY_QUERY = "SELECT id FROM facilities";
            String SELECT_FROM_DEPARTMENT_FACILITIES = "SELECT id FROM department_facilities WHERE facility_id = ?,department_id = ?";

            ObservableList<String> jobCodes = getComboBoxData(connection, SELECT_JOB_CODE_QUERY);
            ObservableList<String> facilities = getComboBoxData(connection, SELECT_FACILITY_QUERY);
            ObservableList<String> positions = getComboBoxData(connection, SELECT_POSITION_QUERY);
            ObservableList<String> departments = getComboBoxData(connection, SELECT_DEPARTMENT_QUERY);
            ObservableList<String> specializations = getComboBoxData(connection, SELECT_SPECIALIZATION_QUERY);

            jobCodeComboBox.setItems(jobCodes);
            facilityComboBox.setItems(facilities);
            positionComboBox.setItems(positions);
            departmentComboBox.setItems(departments);
            specializationComboBox.setItems(specializations);

        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
    }

    private ObservableList<String> getComboBoxData(Connection connection, String query) throws SQLException {
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
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
        return comboBoxData;
    }

    protected void updateStaff() throws SQLException {
        String FIND_SPECIFIC_STAFF = "SELECT id FROM staffs WHERE id = ?";
        try{
            staff.setId(Integer.parseInt(id.getText()));
            staff.setIdNumber(id_number.getText());
            staff.setEmail(Email.getText());
            staff.setFirstName(first_name.getText());
            staff.setLastName(last_name.getText());
            staff.setJobCode(job_code.getText());
            staff.setPhoneNumber(phone_number.getText());
            staff.updateStaff(owner,FIND_SPECIFIC_STAFF);


            String QUERY_UPDATE_STAFF = "UPDATE staffs SET id_number = ?, email = ?, first_name = ?, last_name = ?, job_code = ?, phone_number = ? WHERE id=?";
            preparedStatement = connection.prepareStatement(QUERY_UPDATE_STAFF);
            preparedStatement.setString(1, staff.getIdNumber());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getFirstName());
            preparedStatement.setString(4, staff.getLastName());
            preparedStatement.setString(5, staff.getJobCode());
            preparedStatement.setString(6, staff.getPhoneNumber());

            preparedStatement.executeUpdate();

        }catch (java.sql.SQLException e){
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
    }

    protected void disableStaff() {
        String FIND_SPECIFIC_STAFF = "SELECT id_number FROM staffs WHERE id_number = ?";
    }
}
