/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author previotto
 */
public class SesionPaciente {
    private int idSesion;

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }
    private Integer numeroSesion;
    private LocalDate fecha;
    private String trabajoSesion;
    private String observacion;
    private String motivoTrabajoEmergente;
    private AutorizacionesSesionesObraSociales autorizacion;

    public SesionPaciente() {
    }

    public SesionPaciente(Integer numeroSesion, LocalDate fecha, String trabajoSesion, String observacion, String motivoTrabajoEmergente, AutorizacionesSesionesObraSociales autorizacion) {
        this.numeroSesion = numeroSesion;
        this.fecha = fecha;
        this.trabajoSesion = trabajoSesion;
        this.observacion = observacion;
        this.motivoTrabajoEmergente = motivoTrabajoEmergente;
        this.autorizacion = autorizacion;
    }

    public SesionPaciente(String observacion) {
        this.observacion = observacion;
    }

    
    
    
    
    

    public AutorizacionesSesionesObraSociales getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(AutorizacionesSesionesObraSociales autorizacion) {
        this.autorizacion = autorizacion;
    }
    
    public Integer getNumeroSesion() {
        return numeroSesion;
    }

    public void setNumeroSesion(Integer numeroSesion) {
        this.numeroSesion = numeroSesion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTrabajoSesion() {
        return trabajoSesion;
    }

    public void setTrabajoSesion(String trabajoSesion) {
        this.trabajoSesion = trabajoSesion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMotivoTrabajoEmergente() {
        return motivoTrabajoEmergente;
    }

    public void setMotivoTrabajoEmergente(String motivoTrabajoEmergente) {
        this.motivoTrabajoEmergente = motivoTrabajoEmergente;
    }
    
    
    
}
