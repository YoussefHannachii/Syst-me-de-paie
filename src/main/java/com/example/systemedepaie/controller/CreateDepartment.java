package com.example.systemedepaie.controller;

import com.example.systemedepaie.HelloApplication;
import com.example.systemedepaie.util.DatabaseConn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;

public class CreateDepartment implements Initializable {

    @FXML
    Button createButton;
    @FXML
    TextField departmentFeeText;
    @FXML
    TextField departmentNameText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.setOnAction(actionEvent -> createDepartment());
    }

    public void createDepartment(){
        String name = departmentNameText.getText();
        String feeText = departmentFeeText.getText();

        if (name.isEmpty() || feeText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        double fee;
        try {
            fee = Double.parseDouble(feeText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid fee");
            return;
        }

        try {
            Connection connection = DatabaseConn.getConnection();
            String query = "INSERT INTO department (nomDepartement, tauxHoraire) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, fee);
            preparedStatement.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Department created successfully");
            connection.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not save the department: " + e.getMessage());
        }
    }


    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
