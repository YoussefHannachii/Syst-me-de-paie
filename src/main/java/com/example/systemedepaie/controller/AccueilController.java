package com.example.systemedepaie.controller;

import com.example.systemedepaie.model.Employee;
import com.example.systemedepaie.util.DatabaseConn;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {


    @FXML
    Button employeeCreateButton;
    @FXML
    Button departmentCreateButton;
    @FXML
    Button paieCreateButton;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> departmentColumn;

    @FXML
    private TableColumn<Employee, Employee> actionsColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeCreateButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/systemedepaie/create-employee.fxml"));
                Parent employeeCreationForm = fxmlLoader.load();
                CreateEmployee employeeCreationFormController = fxmlLoader.getController();
                Scene scene = new Scene(employeeCreationForm);
                Stage stage = new Stage();
                stage.setTitle("Créer un nouveau employée");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        departmentCreateButton.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/systemedepaie/create-department.fxml"));
                Parent departmentCreationForm = fxmlLoader.load();
                CreateDepartment departmentCreationFormController = fxmlLoader.getController();
                Scene scene = new Scene(departmentCreationForm);
                Stage stage = new Stage();
                stage.setTitle("Créer un nouveau département");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        paieCreateButton.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/systemedepaie/create-periode-paie.fxml"));
                Parent paieCreationForm = fxmlLoader.load();
                CreatePeriodePaie paieCreationFormController = fxmlLoader.getController();
                Scene scene = new Scene(paieCreationForm);
                Stage stage = new Stage();
                stage.setTitle("Créer une nouvelle période de paie");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        nameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNom() + " " + cellData.getValue().getPrenom()));
        departmentColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(getDepartmentNameById(cellData.getValue().getDepartmentId())));

        // Configure la colonne des actions avec un bouton
        actionsColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button button = new Button("Voir Détails Employee");

            @Override
            protected void updateItem(Employee employee, boolean empty) {
                super.updateItem(employee, empty);

                if (empty || employee == null) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/systemedepaie/details-employee.fxml"));
                            Parent employeeDetails = fxmlLoader.load();

                            // Récupérer le contrôleur et mettre à jour avec les informations de l'employé
                            DetailsEmployee controller = fxmlLoader.getController();
                            controller.initializeWithData(employee);

                            Scene scene = new Scene(employeeDetails);
                            Stage stage = new Stage();
                            stage.setTitle("Details Employee");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });

        // Charger les données dans le TableView
        ObservableList<Employee> employees = FXCollections.observableArrayList(getAllEmployees());
        employeeTable.setItems(employees);
    }


    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT idEmploye, prenom, nom, idDepartement FROM Employee";

        try (Connection connection = DatabaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idEmploye = resultSet.getInt("idEmploye");
                String prenom = resultSet.getString("prenom");
                String nom = resultSet.getString("nom");
                int departmentId = resultSet.getInt("idDepartement");

                employees.add(new Employee(idEmploye, prenom, nom, departmentId));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des erreurs
        }

        return employees;
    }
    public static String getDepartmentNameById(int departmentId) {
        // Implémentez cette méthode pour obtenir le nom du département à partir de l'ID
        // Vous pouvez utiliser une autre requête SQL pour obtenir le nom du département
        String sql = "SELECT nomDepartement FROM Department WHERE idDepartement = ?";

        try (Connection connection = DatabaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("nomDepartement");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des erreurs
        }

        return "Unknown";
    }
}
