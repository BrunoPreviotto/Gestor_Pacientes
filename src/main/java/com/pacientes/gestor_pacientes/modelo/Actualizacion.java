/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author bruno
 */
public class Actualizacion {
    int id;
    String versionActual;
    String versionNueva;
    String ruta;
    int id_usuario;
    boolean exito;
    boolean reciente;

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public boolean isReciente() {
        return reciente;
    }

    public void setReciente(boolean reciente) {
        this.reciente = reciente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Actualizacion() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionActual() {
        return versionActual;
    }

    public void setVersionActual(String versionActual) {
        this.versionActual = versionActual;
    }

    public String getVersionNueva() {
        return versionNueva;
    }

    public void setVersionNueva(String versionNueva) {
        this.versionNueva = versionNueva;
    }
    
    
    /*
    
    Manza amiga amiiigoo
    
    */
    
    
    
}
