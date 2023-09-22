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
    private PasswordField passField;
    @FXML
    private TextField userField;

    @FXML
    public void Login(ActionEvent event) throws SQLException, SQLException {

        Window owner = btnLogin.getScene().getWindow();

        System.out.println(userField.getText());
        System.out.println(passField.getText());

        if (userField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nhập tên");
            return;
        }
        if (passField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nhập mật khẩu");
            return;
        }

        String username = userField.getText();
        String password = passField.getText();

        SelectLogin selectLogin = new SelectLogin();
        boolean flag = selectLogin.validate(username, password);

        if (!flag) {
            infoBox("Hãy kiểm tra lại tên đăng nhập và mật khẩu của bạn", null, "Thất Bại");
        } else {
            infoBox("Đăng nhập thành công!", null, "Thất Bại");
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
