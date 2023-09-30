package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.SceneSwitch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends Thread implements Initializable, InfoBox, ShowAlert {
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

    public void start () {
        Thread login = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    login(new ActionEvent());
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        login.start();
    }
    @FXML
    protected void login(ActionEvent event) throws SQLException, SQLException, IOException {
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
        LoginRegisterDao loginRegisterDao = new LoginRegisterDao();
        String username = userField.getText();
        String password = passField.getText();
        boolean flag = loginRegisterDao.validate(username, password);

        if (!flag) {
            new Thread(() -> {
                Platform.runLater(()->{
                    InfoBox.infoBox("Hãy kiểm tra lại tên đăng nhập và mật khẩu của bạn", null, "Thất Bại");
                });
            }).start();
        } else {
            new Thread( () -> {
                Platform.runLater(() -> {
                    try {
                        InfoBox.infoBox("Đăng nhập thành công!", null, "Thành Công");
                        new SceneSwitch(loginView, "View/Dashboard.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }).start();
        }
    }

    @FXML
    void switchToRegister(ActionEvent event) throws IOException {
        new SceneSwitch(loginView, "View/Dashboard.fxml");
    }
    /*private void loadView(String fxmlFileName) {
        try {
            // Load FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

        } catch (IOException e) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ExitBtn.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }

}
