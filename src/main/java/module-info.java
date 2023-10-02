module com.devteam.management_of_noncommunicable_diseases {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;


    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.devteam.management_of_noncommunicable_diseases to javafx.fxml;
    exports com.devteam.management_of_noncommunicable_diseases;
    exports com.devteam.management_of_noncommunicable_diseases.Controller;
    opens com.devteam.management_of_noncommunicable_diseases.Controller to javafx.fxml;
    exports com.devteam.management_of_noncommunicable_diseases.Interface;
    opens com.devteam.management_of_noncommunicable_diseases.Interface to javafx.fxml;
    exports com.devteam.management_of_noncommunicable_diseases.Dao;
    opens com.devteam.management_of_noncommunicable_diseases.Dao to javafx.fxml;
}