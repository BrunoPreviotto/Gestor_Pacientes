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
    private Integer numeroSesion;
    private LocalDate fecha;
    private String trabajoSesion;
    private String observacion;
    private String motivoTrabajoEmergente;
    private AutorizacionesSesionesObraSociales autorizacion;
    private EstadoFacturacion estado;
    private Double honorarioPorSesion;
    private int idPaciente;

   

    public SesionPaciente() {
    }
    
    public SesionPaciente(Integer numeroSesion, LocalDate fecha, String trabajoSesion, String observacion, Double honorarioPorSesion, EstadoFacturacion estado) {
        this.numeroSesion = numeroSesion;
        this.fecha = fecha;
        this.trabajoSesion = trabajoSesion;
        this.observacion = observacion;
        this.honorarioPorSesion = honorarioPorSesion;
        this.estado = estado;
    }

    public SesionPaciente(Integer numeroSesion, LocalDate fecha, String trabajoSesion, String observacion, Double honorarioPorSesion, AutorizacionesSesionesObraSociales autorizacion, EstadoFacturacion estado) {
        this.numeroSesion = numeroSesion;
        this.fecha = fecha;
        this.trabajoSesion = trabajoSesion;
        this.observacion = observacion;
        this.honorarioPorSesion = honorarioPorSesion;
        this.autorizacion = autorizacion;
        this.estado = estado;
    }

    public SesionPaciente(Integer numeroSesion, LocalDate fecha) {
        this.numeroSesion = numeroSesion;
        this.fecha = fecha;
    }

    public SesionPaciente(Integer numeroSesion, LocalDate fecha, int idPaciente) {
        this.numeroSesion = numeroSesion;
        this.fecha = fecha;
        this.idPaciente = idPaciente;
    }
    
    
    
    

    public Double getHonorarioPorSesion() {
        return honorarioPorSesion;
    }

    public void setHonorarioPorSesion(Double honorarioPorSesion) {
        this.honorarioPorSesion = honorarioPorSesion;
    }

        

    public SesionPaciente(String observacion) {
        this.observacion = observacion;
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

    public EstadoFacturacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoFacturacion estado) {
        this.estado = estado;
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
