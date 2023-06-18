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
public class AutorizacionesSesionesObraSociales {
    private int id;
    private Integer numeroAutorizacion;
    private String observacion;
    private LocalDate asociacion;
    private Double copago;
    private CodigoFacturacion codigoFacturacion;
    private int idSesion;
    private int idPaciente;
    

    public AutorizacionesSesionesObraSociales() {
    }

    public AutorizacionesSesionesObraSociales(Integer numeroAutorizacion, LocalDate asociacion) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.asociacion = asociacion;
    }

    public AutorizacionesSesionesObraSociales(Integer numeroAutorizacion, LocalDate asociacion, int idSesion, int idPaciente) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.asociacion = asociacion;
        this.idSesion = idSesion;
        this.idPaciente = idPaciente;
    }
    
    
    
    

    public AutorizacionesSesionesObraSociales(Integer numeroAutorizacion, String observacion, LocalDate asociacion, Double copago, CodigoFacturacion codigoFacturacion) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.observacion = observacion;
        this.asociacion = asociacion;
        this.copago = copago;
        this.codigoFacturacion = codigoFacturacion;
    }
    
    public AutorizacionesSesionesObraSociales(int id, Integer numeroAutorizacion, String observacion, LocalDate asociacion, Double copago, CodigoFacturacion codigoFacturacion, int idSesion, int idPaciente) {
        this.id = id;
        this.numeroAutorizacion = numeroAutorizacion;
        this.observacion = observacion;
        this.asociacion = asociacion;
        this.copago = copago;
        this.codigoFacturacion = codigoFacturacion;
        this.idSesion = idSesion;
        this.idPaciente = idPaciente;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }
    
    

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public LocalDate getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(LocalDate asociacion) {
        this.asociacion = asociacion;
    }

    public Double getCopago() {
        return copago;
    }

    public void setCopago(Double copago) {
        this.copago = copago;
    }

    public CodigoFacturacion getCodigoFacturacion() {
        return codigoFacturacion;
    }

    public void setCodigoFacturacion(CodigoFacturacion codigoFacturacion) {
        this.codigoFacturacion = codigoFacturacion;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(Integer numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }
    
    
}
