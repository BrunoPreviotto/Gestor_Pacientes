/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

import java.time.LocalDate;

/**
 *
 * @author previotto
 */
public class AccionesAgenda {
    private int id;
    private String  accion;
    private LocalDate fecha;
    private Boolean recordar;
    
    public AccionesAgenda() {
    }
    
    

    public AccionesAgenda(int id, String accion, LocalDate fecha, Boolean recordar) {
        this.id = id;
        this.accion = accion;
        this.fecha = fecha;
        this.recordar = recordar;
    }

    public AccionesAgenda(String accion, LocalDate fecha) {
        this.accion = accion;
        this.fecha = fecha;
    }

    public AccionesAgenda(String accion, LocalDate fecha, Boolean recordar) {
        this.accion = accion;
        this.fecha = fecha;
        this.recordar = recordar;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getRecordar() {
        return recordar;
    }

    public void setRecordar(Boolean recordar) {
        this.recordar = recordar;
    }
    
    
    
    
}
