package com.example.cadin.model;

import java.text.DateFormat;

public class Usuario {
    String nom;
    String apellP;
    String apellM;
    String tel;
    String dniUser;

    public String nombre(){
        String n= nom+", "+apellP+" "+apellM;
        return n.toUpperCase();
    }
    public String dni(){
        return dniUser;
    }
    public String telefono(){
        return tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApellP() {
        return apellP;
    }

    public void setApellP(String apellP) {
        this.apellP = apellP;
    }

    public String getApellM() {
        return apellM;
    }

    public void setApellM(String apellM) {
        this.apellM = apellM;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDniUser() {
        return dniUser;
    }

    public void setDniUser(String dniUser) {
        this.dniUser = dniUser;
    }
}
