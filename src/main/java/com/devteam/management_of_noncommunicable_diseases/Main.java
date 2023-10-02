package com.devteam.management_of_noncommunicable_diseases;

import com.devteam.management_of_noncommunicable_diseases.Dao.PositionDao;
import com.devteam.management_of_noncommunicable_diseases.Dao.StaffDao;
import com.devteam.management_of_noncommunicable_diseases.Model.Position;
import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {

    double x, y = 0;

    @Override
    public void start(Stage stage) throws IOException, Exception {
        new Thread(() -> {
            Platform.runLater(() -> {
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View/Login.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.initStyle(StageStyle.TRANSPARENT);

                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.setScene(new Scene(root));
                stage.show();
            });
        }).start();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        launch();
        ObservableList<Staff> staffList = StaffDao.getStaffsListByConditionOrNot(null, "'943656129601'");
        Staff staff = staffList.get(0);
        System.out.println(staff.getFirstName());
        System.out.println(staff.getLastName());
        System.out.println(staff.getEmail());
        System.out.println(staff.getDateOfBirth());
        System.out.println(staff.getPhoneNumber());
        System.out.println(staff.getJobCode());
        System.out.println(staff.getIdNumber());
    }
}