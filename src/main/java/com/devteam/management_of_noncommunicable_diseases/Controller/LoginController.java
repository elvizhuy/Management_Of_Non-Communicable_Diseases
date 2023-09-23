package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
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
    private AnchorPane loginView;

    @FXML
    protected void login(ActionEvent event) throws SQLException, SQLException, IOException {
        String SELECT_QUERY = "SELECT user_name,password FROM accounts WHERE user_name = ? and password = ?";
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
        JdbcDaoLoginRegister jdbcDaoLoginRegister = new JdbcDaoLoginRegister();
        String username = userField.getText();
        String password = passField.getText();
        boolean flag = jdbcDaoLoginRegister.validate(username, password,SELECT_QUERY);

        if (!flag) {
            InfoBox.infoBox("Hãy kiểm tra lại tên đăng nhập và mật khẩu của bạn", null, "Thất Bại");
        } else {
            InfoBox.infoBox("Đăng nhập thành công!", null, "Thành Công");
            new SceneSwitch(loginView, "View/Dashboard.fxml");
        }
    }

    @FXML
    void switchToRegister(ActionEvent event) throws IOException {
        new SceneSwitch(loginView, "View/Register.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ExitBtn.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }

}
