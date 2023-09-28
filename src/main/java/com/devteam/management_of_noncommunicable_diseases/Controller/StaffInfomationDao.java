package com.devteam.management_of_noncommunicable_diseases.Controller;


import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.stage.Window;
import java.sql.*;

public class StaffInfomationDao {
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn department_facilities_id;
    @FXML
    private TableColumn job_code;
    @FXML
    private TableColumn position_id;
    @FXML
    private TableColumn specialization_id;
    @FXML
    private TableColumn first_name;
    @FXML
    private TableColumn last_name;
    @FXML
    private TableColumn phone_number;
    @FXML
    private TableColumn Email;
    @FXML
    private TableColumn id_number;
    @FXML
    private TableColumn base_salary;
    @FXML
    private TableColumn start_work;
    @FXML
    private TableColumn delete_at;

}
