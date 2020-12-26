package com.example.cadin.model;

public class Licenciado {
    String idlic;
    String nomlic;
    String apeplic;
    String apemlic;
    String dnilic;
    String nombre;

    public String nombrelic(){
        return nomlic+" "+apeplic+" "+apemlic;
    }

    public String getIdlic() {
        return idlic;
    }

    public void setIdlic(String idlic) {
        this.idlic = idlic;
    }

    public String getNomlic() {
        return nomlic;
    }

    public void setNomlic(String nomlic) {
        this.nomlic = nomlic;
    }

    public String getApeplic() {
        return apeplic;
    }

    public void setApeplic(String apeplic) {
        this.apeplic = apeplic;
    }

    public String getApemlic() {
        return apemlic;
    }

    public void setApemlic(String apemlic) {
        this.apemlic = apemlic;
    }

    public String getDnilic() {
        return dnilic;
    }

    public void setDnilic(String dnilic) {
        this.dnilic = dnilic;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
