/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author previotto
 */
public class TipoSesion {
    private int id;
    private String nombre;
    private String decripcion;

    public TipoSesion() {
    }

    public TipoSesion(String nombre, String decripcion) {
        this.nombre = nombre;
        this.decripcion = decripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }
    
    
}
