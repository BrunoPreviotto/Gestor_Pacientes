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
    private Integer id;
    private Afiliado afiliado;
    private String nombre;
    private PlanObraSocial plan;
    private int idPaciente;

    public ObraSocialPaciente() {
    }

    public ObraSocialPaciente(String nombre) {
        this.nombre = nombre;
    }
    
    

    public ObraSocialPaciente(Afiliado afiliado, String nombre, PlanObraSocial plan) {
        this.afiliado = afiliado;
        this.nombre = nombre;
        this.plan = plan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    
    

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
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
