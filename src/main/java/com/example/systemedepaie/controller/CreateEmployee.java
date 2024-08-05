package com.example.systemedepaie.controller;

import com.example.systemedepaie.model.Department;
import com.example.systemedepaie.util.DatabaseConn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.nio.Buffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.systemedepaie.controller.CreateDepartment.showAlert;

public class CreateEmployee implements Initializable {

    @FXML
    Button createEmployeeBtn;
    @FXML
    TextField nomText;
    @FXML
    TextField prenomText;
    @FXML
    MenuButton departmentMenu;

    List<Department> departments = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departmentMenu.getItems().clear();
        try {
            populateDepatrmentBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        createEmployeeBtn.setOnAction(actionEvent -> createEmployee());
    }



    public void populateDepatrmentBox() throws SQLException {
        try{
            Connection connection = DatabaseConn.getConnection();
            String query = "SELECT * FROM Department";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idDepartement = resultSet.getInt("idDepartement");
                String nomDepartement = resultSet.getString("nomDepartement");
                double tauxHoraire = resultSet.getDouble("tauxHoraire");

                Department department = new Department(idDepartement, nomDepartement, tauxHoraire);
                departments.add(department);
                MenuItem menuItem = new MenuItem(resultSet.getString("nomDepartement"));
                departmentMenu.getItems().add(menuItem);
                menuItem.setOnAction(event -> departmentMenu.setText(menuItem.getText()));
            }

        }catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not connect to database " + e.getMessage());
        }
    }

    public void createEmployee(){
        String nom = nomText.getText();
        String prenom = prenomText.getText();
        String departementName = departmentMenu.getText();
        int departmentId = findDepartmentIdByName(departments,departementName);

        if (nom.isEmpty() || prenom.isEmpty() || departementName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }


        try {
            Connection connection = DatabaseConn.getConnection();
            String query = "INSERT INTO employee (prenom, nom, idDepartement) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, prenom);
            preparedStatement.setString(2, nom);
            preparedStatement.setInt(3, departmentId);
            preparedStatement.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Employee created successfully");
            connection.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not save the department: " + e.getMessage());
        }
    }

    public static Integer findDepartmentIdByName(List<Department> departments, String name) {
        for (Department department : departments) {
            if (department.getNomDepartement().equalsIgnoreCase(name)) {
                return department.getIdDepartement();
            }
        }
        return null; // Retourne null si le département n'est pas trouvé
    }
}
