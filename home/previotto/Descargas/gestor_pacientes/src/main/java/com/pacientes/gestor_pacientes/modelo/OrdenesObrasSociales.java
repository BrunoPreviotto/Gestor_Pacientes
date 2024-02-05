/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

import java.util.List;

/**
 *
 * @author previotto
 */
public class OrdenesObrasSociales {
    private int id;
    private String observacion;
    private String asociacion;
    private Double copago;
    private CodigoFacturacion codigoFacturacion;
    private List<AutorizacionesSesionesObraSociales> autorizaciones;   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(String asociacion) {
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

    public List<AutorizacionesSesionesObraSociales> getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(List<AutorizacionesSesionesObraSociales> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }
    
    
}
