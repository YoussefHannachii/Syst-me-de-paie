package com.example.systemedepaie.controller;

import com.example.systemedepaie.model.Employee;
import com.example.systemedepaie.model.Paie;
import com.example.systemedepaie.util.DatabaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailsEmployee implements Initializable {


    @FXML
    private Label employeLabel;

    @FXML
    private Label departmentLabel;

    @FXML
    private Label semaineLabel;

    @FXML
    private ListView<String> paieListView;

    @FXML
    private Button previousWeekButton;

    @FXML
    private Button nextWeekButton;

    private int currentWeek = 1; // Semaine courante
    Employee currentEmployee;
    private int totalWeeks = 52;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initializeWithData(Employee currentEmployee){
        updateCurrentEmployee(currentEmployee);
        previousWeekButton.setOnAction(event -> showPreviousWeek());
        nextWeekButton.setOnAction(event -> showNextWeek());

        // Exemple d'initialisation (à adapter selon la logique de ton application)
        setEmployeeDetails(currentEmployee.getNom()+" "+currentEmployee.getPrenom(),
                AccueilController.getDepartmentNameById(currentEmployee.getDepartmentId()));
        loadPaieDetails(currentWeek);
    }

    public void updateCurrentEmployee(Employee employee){
        currentEmployee = employee;
    }

    public void setEmployeeDetails(String employeName, String departmentName) {
        employeLabel.setText("Employé : " + employeName);
        departmentLabel.setText("Département : " + departmentName);
    }

    private void showPreviousWeek() {
        if (currentWeek > 1) {
            currentWeek--;
            loadPaieDetails(currentWeek);
        }
    }

    private void showNextWeek() {
        if (currentWeek < totalWeeks) {
            currentWeek++;
            loadPaieDetails(currentWeek);
        }
    }

    private void loadPaieDetails(int weekNumber) {
        List<Paie> paieDetails = getPaieDetailsForWeek(currentEmployee.getIdEmploye(), weekNumber);
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Paie paie : paieDetails) {
            items.add(formatPaieDetails(paie));
        }
        paieListView.setItems(items);
        semaineLabel.setText("Semaine n° " + weekNumber);
    }

    private List<Paie> getPaieDetailsForWeek(int employeeId, int weekNumber) {
        List<Paie> paieDetails = new ArrayList<>();
        String sql = "SELECT * FROM Paie WHERE idEmploye = ? AND semaineNumero = ?";

        try (Connection connection = DatabaseConn.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.setInt(2, weekNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int semaineNumero = resultSet.getInt("semaineNumero");
                    double nombreHeures = resultSet.getDouble("nombreHeures");
                    double tauxHoraire = resultSet.getDouble("tauxHoraire");
                    double heuresSupplementaire = resultSet.getDouble("heuresSupplementaire");
                    double impotProvincial = resultSet.getDouble("impotProvincial");
                    double impotFederal = resultSet.getDouble("impotFederal");
                    double RRQ = resultSet.getDouble("RRQ");
                    double assurance = resultSet.getDouble("assurance");
                    double salaireNet = resultSet.getDouble("salaireNet");
                    double salaireBrut = resultSet.getDouble("salaireBrut");

                    Paie paie = new Paie(semaineNumero, employeeId, nombreHeures, tauxHoraire, heuresSupplementaire, impotProvincial, impotFederal, RRQ, assurance, salaireNet, salaireBrut);
                    paieDetails.add(paie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des erreurs
        }

        return paieDetails;
    }

    private String formatPaieDetails(Paie paie) {
        return String.format("Semaine: %d,\n Heures: %.2f,\n Taux Horaire: %.2f,\n Heures Supplémentaires: %.2f,\n Impôt Provincial: %.2f,\n Impôt Fédéral: %.2f,\n RRQ: %.2f,\n Assurance: %.2f,\n Salaire Net: %.2f,\n Salaire Brut: %.2f",
                paie.getSemaineNumero(),
                paie.getNombreHeures(),
                paie.getTauxHoraire(),
                paie.getHeuresSupplementaire(),
                paie.getImpotProvincial(),
                paie.getImpotFederal(),
                paie.getRRQ(),
                paie.getAssurance(),
                paie.getSalaireNet(),
                paie.getSalaireBrut());
    }

}
