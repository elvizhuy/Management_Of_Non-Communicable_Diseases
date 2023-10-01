package com.devteam.management_of_noncommunicable_diseases.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController extends Thread implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane application;

    @FXML
    private ImageView exitBtn;

    @FXML
    private StackPane ContentArea;

    @FXML
    void Department(ActionEvent event) {

    }

    @FXML
    void Export(ActionEvent event) {

    }

    @FXML
    void Profile(ActionEvent event) {

    }

    @FXML
    void PillsInformation(ActionEvent event) {
        new Thread(() -> {
            Platform.runLater(() -> {
                loadView("/com/devteam/management_of_noncommunicable_diseases/View/medicine.fxml");
            });
        }).start();
    }

    @FXML
    void homePage(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitBtn.setOnMouseClicked(event -> {
            System.exit(0);
        });

    }

    @FXML
    void staffsManagement(ActionEvent event) {
        new Thread(() -> {
            Platform.runLater(() -> {
                loadView("/com/devteam/management_of_noncommunicable_diseases/View/StaffsManagement.fxml");
            });
        }).start();
    }

    private void loadView(String fxmlFileName) {
        new Thread(() -> {
            Platform.runLater(() -> {
                try {
                    // Load FXML file
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(fxmlFileName));
                    Parent root = loader.load();

                    // Set the loaded view to the ContentArea
                    ContentArea.getChildren().clear();
                    ContentArea.getChildren().add(root);
                } catch (IOException e) {
                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, e);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }

}
