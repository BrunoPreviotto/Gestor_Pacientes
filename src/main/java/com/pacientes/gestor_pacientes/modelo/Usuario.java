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
    private String mail;
    private boolean es_usuario;
    private int id_es_ultima_sesion_iniciada;
    
    public Usuario(){
        
    }

    public Usuario(int id, String nombre, String apellido, String usuario, String contraseña, String mail, boolean es_usuario, int id_es_ultima_sesion_iniciada) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.mail = mail;
        this.es_usuario = es_usuario;
        this.id_es_ultima_sesion_iniciada = id_es_ultima_sesion_iniciada;
    }
    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId_es_ultima_sesion_iniciada() {
        return id_es_ultima_sesion_iniciada;
    }

    public void setId_es_ultima_sesion_iniciada(int id_es_ultima_sesion_iniciada) {
        this.id_es_ultima_sesion_iniciada = id_es_ultima_sesion_iniciada;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isEs_usuario() {
        return es_usuario;
    }

    public void setEs_usuario(boolean es_usuario) {
        this.es_usuario = es_usuario;
    }
    
    
    

    public Usuario(int id, String nombre, String apellido, String contraseña, String mail, boolean es_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.mail = mail;
        this.es_usuario = es_usuario;
    }
    
    
    
    
    
}
