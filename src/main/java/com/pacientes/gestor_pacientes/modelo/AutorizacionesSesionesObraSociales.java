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

    public AutorizacionesSesionesObraSociales() {
    }

    public AutorizacionesSesionesObraSociales(Integer numeroAutorizacion, String observacion, LocalDate asociacion, Double copago, CodigoFacturacion codigoFacturacion) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.observacion = observacion;
        this.asociacion = asociacion;
        this.copago = copago;
        this.codigoFacturacion = codigoFacturacion;
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
