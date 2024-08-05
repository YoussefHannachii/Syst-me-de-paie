package com.example.systemedepaie.controller;

import com.example.systemedepaie.model.Department;
import com.example.systemedepaie.model.Employee;
import com.example.systemedepaie.util.DatabaseConn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.systemedepaie.controller.CreateDepartment.showAlert;

public class CreatePeriodePaie implements Initializable {


    @FXML
    MenuButton employeeMenu;

    List<Employee> employees = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeMenu.getItems().clear();
        try {
            populateEmployees();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void populateEmployees() throws SQLException{
        try{
            Connection connection = DatabaseConn.getConnection();
            String query = "SELECT * FROM Employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idDepartement = resultSet.getInt("idDepartement");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                int idEmployee = resultSet.getInt("idEmploye");

                Employee employee = new Employee(idEmployee,prenom,nom,idDepartement);
                employees.add(employee);
                MenuItem menuItem = new MenuItem(nom+" "+prenom);
                employeeMenu.getItems().add(menuItem);
                menuItem.setOnAction(event -> employeeMenu.setText(menuItem.getText()));

            }

        }catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not connect to database " + e.getMessage());
        }
    }
}
