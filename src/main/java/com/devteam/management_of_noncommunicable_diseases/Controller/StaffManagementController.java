package com.devteam.management_of_noncommunicable_diseases.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffManagementController {
    @FXML
    private TextField SearchStaffsByName;

    @FXML
    protected void addStaffs(ActionEvent event) {
        openAddStaff();
    }

    private void openAddStaff() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/devteam/management_of_noncommunicable_diseases/View/addStaffs.fxml"));
            Parent addStaffsView = loader.load();
            StaffController staffController = loader.getController();

            Stage addStaffdStage = new Stage();
            addStaffdStage.initStyle(StageStyle.UTILITY);
            addStaffdStage.setTitle("Dashboard");
            addStaffdStage.setScene(new Scene(addStaffsView));

            addStaffdStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}