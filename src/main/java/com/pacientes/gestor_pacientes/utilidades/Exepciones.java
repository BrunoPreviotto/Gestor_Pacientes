/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

/**
 *
 * @author previotto
 */
public class Exepciones extends Exception {
    
    private int codigoError;
    
    public Exepciones(int codigoError) {
        super();
        this.codigoError=codigoError;
    }
    
    @Override
    public String getMessage(){
         
        String mensaje="";
         
        switch(codigoError){
            case 111:
                mensaje="Ya existe paciente registrado con ese DNI";
                break;
            case 222:
                mensaje="Error al crear paciente";
                break;
            case 333:
                mensaje="Nombre de obra social ya existe";
                break;
        }
         
        return mensaje;
         
    }
    
}
