package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Medicine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;

public class MedicineController extends Thread implements InfoBox, ShowAlert {

    @FXML
    private Button btnAddMedicine;

    @FXML
    private TextField type_id;

    @FXML
    private TextField group_id;
    @FXML
    private TextField name;

    @FXML
    private TextField unit;

    @FXML
    private TextArea description;

    @FXML
    private TextField instruction;
    @FXML
    private ComboBox<String> medicineGroupComboBox;
    @FXML
    private ComboBox<String> medicineTypeComboBox;
    @FXML
    protected void initialize() throws SQLException {
        MedicineDao.initializeComboBoxData();
        owner = btnAddMedicine.getScene().getWindow();
    }

    Window owner;
    Medicine medicine = new Medicine();
    MedicineDao medicineDao = new MedicineDao(medicineGroupComboBox, medicineTypeComboBox);

    @FXML
    void addPills(ActionEvent event) throws SQLException {
        add();
    }

    public void add() throws SQLException {
        medicine.setTypeId(Integer.parseInt(type_id.getText()));
        medicine.setTypeId(Integer.parseInt(group_id.getText()));
        medicine.setName(name.getText());
        medicine.setUnit(unit.getText());
        medicine.setDescription(description.getText());
        medicine.setInstruction(instruction.getText());

        medicineDao.addMedicine(owner);
    }

}
