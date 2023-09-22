package com.devteam.management_of_noncommunicable_diseases.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable,InfoBox,ShowAlert {
    @FXML
    private ImageView btnCloseLogin;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField userField;
    @FXML
    private ImageView ExitBtn;

    @FXML
    protected void login(ActionEvent event) throws SQLException, SQLException {
        Window owner = btnLogin.getScene().getWindow();

        System.out.println(userField.getText());
        System.out.println(passField.getText());

        if (userField.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nhập tên");
            return;
        }
        if (passField.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nhập mật khẩu");
            return;
        }
        SelectLogin selectLogin = new SelectLogin();
        String username = userField.getText();
        String password = passField.getText();
        boolean flag = selectLogin.validate(username, password);

        if (!flag) {
            InfoBox.infoBox("Hãy kiểm tra lại tên đăng nhập và mật khẩu của bạn", null, "Thất Bại");
        } else {
            InfoBox.infoBox("Đăng nhập thành công!", null, "Thành Công");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ExitBtn.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }

}
