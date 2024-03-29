/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author previotto
 */
public class ObraSocial {
    private int id;
    private String nombre;
    private Telefono telefono;
    private Web web;
    private boolean es_obra_social;
    private Email email;
    private List<String> planes;
    private String plan;
    private String listaPlanesToString = "";
    private int idPlan;

    

    public ObraSocial() {
    }

    public ObraSocial(String nombre, String plan) {
        this.nombre = nombre;
        this.plan = plan;
    }

    public ObraSocial(int id, String nombre, String plan) {
        this.id = id;
        this.nombre = nombre;
        this.plan = plan;
    }
    
    
    

    public ObraSocial(String nombre, Telefono telefono, Web web, Email email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
        this.email = email;
    }
    
    

    public ObraSocial(String nombre, Telefono telefono, Web web, Email email, List<String> planes) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
        this.email = email;
        this.planes = planes;
    }

    public ObraSocial(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    
    

    public ObraSocial(String nombre) {
        this.nombre = nombre;
    }
    
    public ObraSocial(int id, String nombre, Telefono telefono, Web web, boolean es_obra_social, Email email, List<String> planes) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
        this.es_obra_social = es_obra_social;
        this.email = email;
        this.planes = planes;
    }

    public ObraSocial(String nombre, Telefono telefono, Web web, boolean es_obra_social, Email email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
        this.es_obra_social = es_obra_social;
        this.email = email;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Web getWeb() {
        return web;
    }

    public void setWeb(Web web) {
        this.web = web;
    }

    public boolean isEs_obra_social() {
        return es_obra_social;
    }

    public void setEs_obra_social(boolean es_obra_social) {
        this.es_obra_social = es_obra_social;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<String> getPlanes() {
        return planes;
    }

    public void setPlanes(List<String> planes) {
        this.planes = planes;
    }

    public String getListaPlanesToString() {
        return listaPlanesToString;
    }

    public ObraSocial setListaPlanesToString() {
        if (Objects.nonNull(planes)) {
            for (String plan : planes) {
                if(Objects.nonNull(plan)){
                    listaPlanesToString += plan + "; ";
                }
            }
        }
        return this;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }
    
    
    
    
}
