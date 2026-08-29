/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;



/**
 *
 * @author previotto
 */
public class Telefono {
    private Long id;
    private String telefono;
    private int idObjeto;

    public Telefono(Long id) {
        this.id = id;
    }

    
    public Telefono(Long id, String telefono, int idObjeto) {
        this.id = id;
        this.telefono = telefono;
        this.idObjeto = idObjeto;
    }
    

    public Telefono(String telefono, int idObjeto) {
        this.telefono = telefono;
        this.idObjeto = idObjeto;
    }
    
       public Telefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       
      

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }
    
 

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    
    
    
    
}
