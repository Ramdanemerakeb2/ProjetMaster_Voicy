package com.example.voicy_v2.model;

public class Clinicien {

    private String nom ;
    private String prenom ;
    private String identifiant ;
    private String mdp ;


    public Clinicien(String identifiant, String nom, String prenom, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    /********** getters ******************/

    public String getNom() {
        return nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    /********** Setters ******************/

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


}
