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
    private FrecuenciaSesion frecuenciaSesion;
    private TipoSesion tipoSEsion;
    private int idPaciente;

    public PlanTratamiento() {
    }
    
    public PlanTratamiento(String estrategia, FrecuenciaSesion frecuenciaSesion, TipoSesion tipoSEsion) {
        this.estrategia = estrategia;
        this.frecuenciaSesion = frecuenciaSesion;
        this.tipoSEsion = tipoSEsion;
        this.idPaciente = idPaciente;
    }

    public PlanTratamiento(String estrategia, FrecuenciaSesion frecuenciaSesion, TipoSesion tipoSEsion, int idPaciente) {
        this.estrategia = estrategia;
        this.frecuenciaSesion = frecuenciaSesion;
        this.tipoSEsion = tipoSEsion;
        this.idPaciente = idPaciente;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public FrecuenciaSesion getFrecuenciaSesion() {
        return frecuenciaSesion;
    }

    public void setFrecuenciaSesion(FrecuenciaSesion frecuenciaSesion) {
        this.frecuenciaSesion = frecuenciaSesion;
    }

    public TipoSesion getTipoSEsion() {
        return tipoSEsion;
    }

    public void setTipoSEsion(TipoSesion tipoSEsion) {
        this.tipoSEsion = tipoSEsion;
    }
    
    
}
