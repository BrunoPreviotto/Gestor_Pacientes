/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author previotto
 */
public class CodigoFacturacion {
    private int id;
    private String nombre;
    private Integer codigo;

    public CodigoFacturacion() {
    }

    public CodigoFacturacion(String nombre) {
        this.nombre = nombre;
    }

    
    
    public CodigoFacturacion(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
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

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    
}
