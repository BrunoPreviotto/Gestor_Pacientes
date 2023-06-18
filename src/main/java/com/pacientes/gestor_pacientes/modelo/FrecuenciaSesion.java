/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author previotto
 */
public class FrecuenciaSesion {
    private int idFrecuencia;
    private String frecuencia;
    
    
    public FrecuenciaSesion() {
        
    }

    public FrecuenciaSesion(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public FrecuenciaSesion(int idFrecuencia, String frecuencia) {
        this.idFrecuencia = idFrecuencia;
        this.frecuencia = frecuencia;
    }

    

        
    public int getIdFrecuencia() {
        return idFrecuencia;
    }

    public void setIdFrecuencia(int idFrecuencia) {
        this.idFrecuencia = idFrecuencia;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    
}
