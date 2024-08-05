package com.example.systemedepaie.model;

public class Employee {
    private int idEmploye;
    private String prenom;
    private String nom;
    private int departmentId;

    public Employee(int idEmploye, String prenom, String nom, int departmentId) {
        this.idEmploye = idEmploye;
        this.prenom = prenom;
        this.nom = nom;
        this.departmentId = departmentId;
    }

    public Employee(String prenom, String nom, int departmentId) {
        this.prenom = prenom;
        this.nom = nom;
        this.departmentId = departmentId;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmploye='" + idEmploye + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
