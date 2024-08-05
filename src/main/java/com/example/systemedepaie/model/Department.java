package com.example.systemedepaie.model;

public class Department {
    private int idDepartement;
    private String nomDepartement;
    private double tauxHoraire;

    public Department(int idDepartement, String nomDepartement, double tauxHoraire) {
        this.idDepartement = idDepartement;
        this.nomDepartement = nomDepartement;
        this.tauxHoraire = tauxHoraire;
    }

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
}
