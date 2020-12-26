package com.example.cadin.model;

public class RegistroUsuario {
    private String nom;
    private String ape;
    private String apeP;
    private String apeM;
    private String fch;
    private String dni;
    private String email;
    private String foto;
    private String apg;
    private String dnip;
    private String dnim;
    private String tel;
    private String cod;

    public String nombre(){
        return nom+", "+apeP+" "+apeM;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getApeP() {
        return apeP;
    }

    public void setApeP(String apeP) {
        this.apeP = apeP;
    }

    public String getApeM() {
        return apeM;
    }

    public void setApeM(String apeM) {
        this.apeM = apeM;
    }

    public String getFch() {
        return fch;
    }

    public void setFch(String fch) {
        this.fch = fch;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getApg() {
        return apg;
    }

    public void setApg(String apg) {
        this.apg = apg;
    }

    public String getDnip() {
        return dnip;
    }

    public void setDnip(String dnip) {
        this.dnip = dnip;
    }

    public String getDnim() {
        return dnim;
    }

    public void setDnim(String dnim) {
        this.dnim = dnim;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
