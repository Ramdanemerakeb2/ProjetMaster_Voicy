package com.example.voicy_v2.model;

public class Patient {
    private String id, genre, commentaire ;

    public Patient(String id, String genre, String commentaire) {
        this.id = id ;
        this.genre = genre;
        this.commentaire= commentaire;
    }

    //*****************getters-setters*****************//
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
