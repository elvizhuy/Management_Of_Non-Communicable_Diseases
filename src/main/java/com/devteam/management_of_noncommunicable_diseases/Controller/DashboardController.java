package com.devteam.management_of_noncommunicable_diseases.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private ImageView ExitBtn;

    @FXML
    private ImageView menuBtn;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ExitBtn.setOnMouseClicked(event -> {
            System.exit(0);
        });

        pane1.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition.setByX(-900);
        translateTransition.play();

        menuBtn.setOnMouseClicked(event -> {
            pane1.setVisible(true);

            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5), pane1);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(0.15);
            fadeTransition.play();

            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5), pane2);
            translateTransition.setByX(+900);
            translateTransition.play();
        });

        pane1.setOnMouseClicked(event -> {
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5), pane1);
            fadeTransition.setFromValue(0.15);
            fadeTransition.setToValue(0);
            fadeTransition.play();

            fadeTransition2.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });

            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5), pane2);
            translateTransition.setByX(-900);
            translateTransition.play();
        });
    }
}
