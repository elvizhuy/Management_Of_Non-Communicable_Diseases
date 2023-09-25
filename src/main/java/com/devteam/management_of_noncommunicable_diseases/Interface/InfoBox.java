package com.devteam.management_of_noncommunicable_diseases.Interface;

import javafx.scene.control.Alert;

public interface InfoBox {
    static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
