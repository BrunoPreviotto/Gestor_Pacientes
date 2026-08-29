/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author previotto
 */
public class  Web {
     
    private Long id;
    private String web;

    public Web() {
    }

    public Web(Long id) {
        this.id = id;
    }

    public Web(String web) {
        this.web = web;
    }

    public Web(Long id, String web) {
        this.id = id;
        this.web = web;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    
}
