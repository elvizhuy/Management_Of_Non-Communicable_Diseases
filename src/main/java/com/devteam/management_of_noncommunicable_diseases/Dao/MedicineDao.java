package com.devteam.management_of_noncommunicable_diseases.Dao;

import com.devteam.management_of_noncommunicable_diseases.Controller.DBConnection;
import com.devteam.management_of_noncommunicable_diseases.Interface.ComboBoxData;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Medicine;
import com.devteam.management_of_noncommunicable_diseases.Model.MedicineGroups;
import com.devteam.management_of_noncommunicable_diseases.Model.MedicineTypes;
import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Window;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class MedicineDao implements ComboBoxData {
    Medicine medicine = new Medicine();
    MedicineTypes medicineTypes = new MedicineTypes();
    MedicineGroups medicineGroups = new MedicineGroups();
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @FXML
    private static ComboBox<String> medicineGroupComboBox;
    @FXML
    private static ComboBox<String> medicineTypeComboBox;

    public MedicineDao(ComboBox<String> medicineGroupComboBox, ComboBox<String> medicineTypeComboBox) {
        MedicineDao.medicineGroupComboBox = medicineGroupComboBox;
        MedicineDao.medicineTypeComboBox = medicineTypeComboBox;
    }

    public void addMedicine(Window owner) throws SQLException {
        String INSERT_MEDICINE_TYPE_QUERY = "INSERT into medicine_types (name) VALUES (?)";
        String INSERT_MEDICINE_GROUP_QUERY = "INSERT into medicine_groups (name,description) VALUES (?,?)";
        String INSERT_MEDICINE_QUERY = "INSERT into medicine (group_id,type_id,name,unit,description,instruction) VALUES (?,?,?,?,?,?)";

        boolean checkMedicineName = validateEmptyFields(medicine.getName(), "Nhập tên thuốc", owner);
        boolean checkUnit = validateEmptyFields(medicine.getUnit(), "Nhập đơn vị", owner);
        boolean checkDescription = validateEmptyFields(medicine.getDescription(), "Nhập mô tả", owner);
        boolean checkInstruction = validateEmptyFields(medicine.getInstruction(), "Nhập hướng dẫn", owner);

        if (checkMedicineName && checkUnit && checkDescription && checkInstruction) {
            try {
                connection = DBConnection.open();
                assert connection != null;
                preparedStatement = connection.prepareStatement(INSERT_MEDICINE_TYPE_QUERY);
                preparedStatement.setString(1, medicineTypes.getName());
                preparedStatement = connection.prepareStatement(INSERT_MEDICINE_GROUP_QUERY);
                preparedStatement.setString(1, medicineGroups.getName());
                preparedStatement.setString(2, medicineGroups.getDescription());
                preparedStatement = connection.prepareStatement(INSERT_MEDICINE_QUERY);
                preparedStatement.setInt(1, medicine.getGroupId());
                preparedStatement.setInt(2, medicine.getTypeId());
                preparedStatement.setString(3, medicine.getName());
                preparedStatement.setString(4, medicine.getUnit());
                preparedStatement.setString(5, medicine.getDescription());
                preparedStatement.setString(6, medicine.getInstruction());
                System.out.println(preparedStatement);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBConnection.closeAll(connection, preparedStatement, resultSet);
            }
        }  else {
            new Thread(() -> {
                Platform.runLater(() -> {
                    InfoBox.infoBox("Thiếu thông tin,hãy kiểm tra lại", null, "Thất Bại...");
                });
            }).start();
        }
    }

    public void updateStaff(Window owner) throws SQLException {
        Staff staff = new Staff();
        String FIND_SPECIFIC_STAFF = "SELECT id FROM staffs WHERE id = ?";
        String QUERY_UPDATE_STAFF = "UPDATE staffs SET id_number = ?, email = ?, first_name = ?, last_name = ?, job_code = ?, phone_number = ? WHERE id = ?";
        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(FIND_SPECIFIC_STAFF);
            preparedStatement.setInt(1, staff.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id_number = resultSet.getString("id_number");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String idOfUserInDb = resultSet.getString("phone_number");
                String Email = resultSet.getString("email");
                String job_code = resultSet.getString("job_code");
            }
            preparedStatement = connection.prepareStatement(QUERY_UPDATE_STAFF);
            preparedStatement.setString(1,staff.getIdNumber());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getFirstName());
            preparedStatement.setString(4, staff.getLastName());
            preparedStatement.setString(5, staff.getJobCode());
            preparedStatement.setString(6, staff.getPhoneNumber());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
    }


    public static void initializeComboBoxData() throws SQLException {
        try {
            connection = DBConnection.open();
            String SELECT_MEDICINE_GROUP_QUERY = "SELECT id,name FROM medicine_groups";
            String SELECT_MEDICINE_TYPE_QUERY = "SELECT id,name FROM medicine_types";

            ObservableList<String> medicineGroup = ComboBoxData.getComboBoxData(connection, SELECT_MEDICINE_GROUP_QUERY,preparedStatement);
            ObservableList<String> medicineType = ComboBoxData.getComboBoxData(connection, SELECT_MEDICINE_TYPE_QUERY,preparedStatement);

            medicineGroupComboBox.setItems(medicineGroup);
            medicineTypeComboBox.setItems(medicineType);

        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
    }


    protected boolean validateEmptyFields(String dataField, String textToNotice, Window owner) {
        if (dataField.isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }

    protected boolean checkMatchingPassword(String passWord, String confirmPassword, String textToNotice, Window owner) {
        if (!Objects.equals(passWord, confirmPassword)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }

}
