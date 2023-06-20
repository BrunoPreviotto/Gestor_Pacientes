/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

/**
 *
 * @author previotto
 */
public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contraseña;
    private Email email;
    private boolean es_usuario;
    private boolean es_ultima_sesion_iniciada;
    
    //CONSTRUCTOR 1
    public Usuario(){
        
    }

    public Usuario(String nombre, String apellido, String usuario, Email email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.email = email;
    }
    
    

    public Usuario(String nombre, String apellido, String usuario, String contraseña, Email email, boolean es_usuario, boolean es_ultima_sesion_iniciada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.email = email;
        this.es_usuario = es_usuario;
        this.es_ultima_sesion_iniciada = es_ultima_sesion_iniciada;
    }

    
    
    

    //CONSTRUCTOR 2
    public Usuario(int id, String nombre, String apellido, String usuario, String contraseña, Email email, boolean es_usuario, boolean id_es_ultima_sesion_iniciada) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.email = email;
        this.es_usuario = es_usuario;
        this.es_ultima_sesion_iniciada = id_es_ultima_sesion_iniciada;
    }

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean getId_es_ultima_sesion_iniciada() {
        return es_ultima_sesion_iniciada;
    }

    public void setId_es_ultima_sesion_iniciada(boolean id_es_ultima_sesion_iniciada) {
        this.es_ultima_sesion_iniciada = id_es_ultima_sesion_iniciada;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

   

    public boolean isEs_usuario() {
        return es_usuario;
    }

    public void setEs_usuario(boolean es_usuario) {
        this.es_usuario = es_usuario;
    }
    
    
    

    public Usuario(int id, String nombre, String apellido, String contraseña, Email mail, boolean es_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.email = email;
        this.es_usuario = es_usuario;
    }
    
    
    
    
    
}
