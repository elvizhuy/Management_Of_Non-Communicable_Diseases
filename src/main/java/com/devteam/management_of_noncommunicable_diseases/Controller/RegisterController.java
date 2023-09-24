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
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable,InfoBox,ShowAlert {

    @FXML
    private AnchorPane registerView;

    @FXML
    private ImageView ExitBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField userName;

    @FXML
    private TextField fullName;

    @FXML
    protected void Register (ActionEvent event) throws SQLException, IOException {
        String INSERT_QUERY = "INSERT INTO accounts (user_name,password) VALUES (?, ?)";
        String SELECT_QUERY = "SELECT user_name FROM accounts WHERE user_name = ?";
        Window owner = registerBtn.getScene().getWindow();

        System.out.println(userName.getText());
        System.out.println(password.getText());
        System.out.println(confirmPassword.getText());

        if (userName.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nhập tên");
            return;
        }
        if (password.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nhập mật khẩu");
            return;
        }

        if (confirmPassword.getText().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Yêu cầu xác nhận mật khẩu");
            return;
        }

        if (!Objects.equals(password.getText(), confirmPassword.getText())) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Mật khẩu không khớp");
            return;
        }

        JdbcDaoLoginRegister jdbcDaoLoginRegister = new JdbcDaoLoginRegister();
        String username = userName.getText();
        String pass = password.getText();
        boolean flag = jdbcDaoLoginRegister.validateDuplicatedName(username,SELECT_QUERY);

        if (!flag) {
            InfoBox.infoBox("Hãy kiểm tra lại tên đăng nhập của bạn", null, "Thất Bại");
        } else {
            jdbcDaoLoginRegister.insertRecord(username,pass,INSERT_QUERY);
            new SceneSwitch(registerView, "View/Dashboard.fxml");
        }
    }

    @FXML
    void onSwitchToLogin(ActionEvent event) throws IOException {
        new SceneSwitch(registerView, "View/Login.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ExitBtn.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
}
