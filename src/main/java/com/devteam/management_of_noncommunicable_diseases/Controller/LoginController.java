package com.devteam.management_of_noncommunicable_diseases.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

import java.sql.SQLException;

public class LoginController {
    @FXML
    private ImageView btnCloseLogin;

    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    @FXML
    public void login(ActionEvent event) throws SQLException, SQLException {

        Window owner = btnLogin.getScene().getWindow();

        System.out.println(userField.getText());
        System.out.println(passwordField.getText());

        if (userField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String username = userField.getText();
        String password = passwordField.getText();

        SelectLogin selectLogin = new SelectLogin();
        boolean flag = selectLogin.validate(username, password);

        if (!flag) {
            infoBox("Please enter correct Username and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
        }
    }
        public static void infoBox(String infoMessage, String headerText, String title) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(infoMessage);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.showAndWait();
        }

        private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }


}
