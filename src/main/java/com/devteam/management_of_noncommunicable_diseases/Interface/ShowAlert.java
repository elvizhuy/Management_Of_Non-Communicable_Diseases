package com.devteam.management_of_noncommunicable_diseases.Interface;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public interface ShowAlert {
    static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
