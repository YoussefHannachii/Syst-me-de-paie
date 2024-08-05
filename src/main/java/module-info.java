module com.example.systemedepaie {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.systemedepaie to javafx.fxml;
    opens com.example.systemedepaie.controller to javafx.fxml;
    exports com.example.systemedepaie;
    exports com.example.systemedepaie.controller;
}