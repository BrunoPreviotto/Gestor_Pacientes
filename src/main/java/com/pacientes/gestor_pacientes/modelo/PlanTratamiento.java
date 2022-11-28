/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author previotto
 */
public class PlanTratamiento {
    private int idPlan;
    private String estrategia;
    private String frecuenciaSesion;
    private TipoSesion tipoSEsion;

    public PlanTratamiento() {
    }

    public PlanTratamiento(String estrategia, String frecuenciaSesion, TipoSesion tipoSEsion) {
        this.estrategia = estrategia;
        this.frecuenciaSesion = frecuenciaSesion;
        this.tipoSEsion = tipoSEsion;
    }

    
    
    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public String getFrecuenciaSesion() {
        return frecuenciaSesion;
    }

    public void setFrecuenciaSesion(String frecuenciaSesion) {
        this.frecuenciaSesion = frecuenciaSesion;
    }

    public TipoSesion getTipoSEsion() {
        return tipoSEsion;
    }

    public void setTipoSEsion(TipoSesion tipoSEsion) {
        this.tipoSEsion = tipoSEsion;
    }
    
    
}
