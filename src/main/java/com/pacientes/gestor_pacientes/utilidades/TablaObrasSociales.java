/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;



/**
 *
 * @author previotto
 */
public class TablaObrasSociales {
    private String nombre;
    private String telefono;
    private String email;
    private String web;
    private String planes;
    
    public TablaObrasSociales() {
       
    }

    public TablaObrasSociales(String nombre, String telefono, String email, String web, String planes) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.web = web;
        this.planes = planes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }
    
    
    
    
    
}
