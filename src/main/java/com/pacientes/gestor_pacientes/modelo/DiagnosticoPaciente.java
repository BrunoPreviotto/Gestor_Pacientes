/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author previotto
 */
public class DiagnosticoPaciente {
    private int id;
    private String diagnostico;
    private String observacion;
    private int idPaciente;

    public DiagnosticoPaciente() {
    }

    public DiagnosticoPaciente(String diagnostico, String observacion) {
        this.diagnostico = diagnostico;
        this.observacion = observacion;
    }

    public DiagnosticoPaciente(String diagnostico, String observacion, int idPaciente) {
        this.diagnostico = diagnostico;
        this.observacion = observacion;
        this.idPaciente = idPaciente;
    }
    
    

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}
