package com.example.asus.tp1;

public class Contact {

    private int id;
    private String prenom;
    private String nom;
    private String numero;
    private String sexe;

    public Contact() {

    }

    public Contact(String prenom, String nom, String numero, String sexe) {
        this.prenom = prenom;
        this.nom = nom;
        this.numero = numero;
        this.sexe = sexe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
}
