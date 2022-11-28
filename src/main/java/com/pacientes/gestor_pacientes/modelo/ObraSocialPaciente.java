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
public class ObraSocialPaciente {
    private Integer numeroAfiliado;
    private String nombre;
    private PlanObraSocial plan;

    public ObraSocialPaciente() {
    }

    public ObraSocialPaciente(Integer numeroAfiliado, String nombre, PlanObraSocial plan) {
        this.numeroAfiliado = numeroAfiliado;
        this.nombre = nombre;
        this.plan = plan;
    }

    
    
    

    public Integer getNumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setNumeroAfiliado(Integer numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PlanObraSocial getPlan() {
        return plan;
    }

    public void setPlan(PlanObraSocial plan) {
        this.plan = plan;
    }

    
    
}
