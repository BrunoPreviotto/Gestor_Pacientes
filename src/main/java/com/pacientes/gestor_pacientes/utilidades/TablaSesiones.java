/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author previotto
 */
public class TablaSesiones {
    private String numeroSesion;
    private String fechaSesion;
    private String trabajoSesion;
    private String observacionSesion;
    private String motivoTrabajoEmergente;
   
    private String numeroAutorizacion;
    private String observacionAutorizacion;
    private String asociacion;
    private String copago;
    
    private String nombreCodigo;

    public TablaSesiones(String numeroSesion, String fechaSesion, String trabajoSesion, String observacionSesion, String motivoTrabajoEmergente, String numeroAutorizacion, String observacionAutorizacion, String asociacion, String copago, String nombreCodigo) {
        this.numeroSesion = numeroSesion;
        this.fechaSesion = fechaSesion;
        this.trabajoSesion = trabajoSesion;
        this.observacionSesion = observacionSesion;
        this.motivoTrabajoEmergente = motivoTrabajoEmergente;
        this.numeroAutorizacion = numeroAutorizacion;
        this.observacionAutorizacion = observacionAutorizacion;
        this.asociacion = asociacion;
        this.copago = copago;
        this.nombreCodigo = nombreCodigo;
    }
    

    

    public String getNumeroSesion() {
        return numeroSesion;
    }

    public void setNumeroSesion(String numeroSesion) {
        this.numeroSesion = numeroSesion;
    }

    public String getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(String fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public String getTrabajoSesion() {
        return trabajoSesion;
    }

    public void setTrabajoSesion(String trabajoSesion) {
        this.trabajoSesion = trabajoSesion;
    }

    public String getObservacionSesion() {
        return observacionSesion;
    }

    public void setObservacionSesion(String observacionSesion) {
        this.observacionSesion = observacionSesion;
    }

    public String getMotivoTrabajoEmergente() {
        return motivoTrabajoEmergente;
    }

    public void setMotivoTrabajoEmergente(String motivoTrabajoEmergente) {
        this.motivoTrabajoEmergente = motivoTrabajoEmergente;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getObservacionAutorizacion() {
        return observacionAutorizacion;
    }

    public void setObservacionAutorizacion(String observacionAutorizacion) {
        this.observacionAutorizacion = observacionAutorizacion;
    }

    public String getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(String asociacion) {
        this.asociacion = asociacion;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public String getNombreCodigo() {
        return nombreCodigo;
    }

    public void setNombreCodigo(String nombreCodigo) {
        this.nombreCodigo = nombreCodigo;
    }

   
    
    
}
