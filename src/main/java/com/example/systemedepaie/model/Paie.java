package com.example.systemedepaie.model;

public class Paie {
    private int semaineNumero;
    private int employeeId;
    private double nombreHeures;
    private double tauxHoraire;
    private double heuresSupplementaire;
    private double impotProvincial;
    private double impotFederal;
    private double RRQ;
    private double assurance;
    private double salaireNet;
    private double salaireBrut;

    public Paie(int semaineNumero, int employeeId, double nombreHeures, double tauxHoraire, double heuresSupplementaire, double impotProvincial, double impotFederal, double RRQ, double assurance, double salaireNet, double salaireBrut) {
        this.semaineNumero = semaineNumero;
        this.employeeId = employeeId;
        this.nombreHeures = nombreHeures;
        this.tauxHoraire = tauxHoraire;
        this.heuresSupplementaire = heuresSupplementaire;
        this.impotProvincial = impotProvincial;
        this.impotFederal = impotFederal;
        this.RRQ = RRQ;
        this.assurance = assurance;
        this.salaireNet = salaireNet;
        this.salaireBrut = salaireBrut;
    }


    public int getSemaineNumero() {
        return semaineNumero;
    }

    public void setSemaineNumero(int semaineNumero) {
        this.semaineNumero = semaineNumero;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getNombreHeures() {
        return nombreHeures;
    }

    public void setNombreHeures(double nombreHeures) {
        this.nombreHeures = nombreHeures;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresSupplementaire() {
        return heuresSupplementaire;
    }

    public void setHeuresSupplementaire(double heuresSupplementaire) {
        this.heuresSupplementaire = heuresSupplementaire;
    }

    public double getImpotProvincial() {
        return impotProvincial;
    }

    public void setImpotProvincial(double impotProvincial) {
        this.impotProvincial = impotProvincial;
    }

    public double getImpotFederal() {
        return impotFederal;
    }

    public void setImpotFederal(double impotFederal) {
        this.impotFederal = impotFederal;
    }

    public double getRRQ() {
        return RRQ;
    }

    public void setRRQ(double RRQ) {
        this.RRQ = RRQ;
    }

    public double getAssurance() {
        return assurance;
    }

    public void setAssurance(double assurance) {
        this.assurance = assurance;
    }

    public double getSalaireNet() {
        return salaireNet;
    }

    public void setSalaireNet(double salaireNet) {
        this.salaireNet = salaireNet;
    }

    public double getSalaireBrut() {
        return salaireBrut;
    }

    public void setSalaireBrut(double salaireBrut) {
        this.salaireBrut = salaireBrut;
    }
}
