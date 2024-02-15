/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.modelo.ObraSocial;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.util.Map;


/**
 *
 * @author previotto
 */
public class ServicioObraSocial extends ServiciosPadre {
 
    
    public ServicioObraSocial rellenarListaObraSocial(ObraSocial obraSocial) {
         /*
            1 NOMBRE
            2 TELEFONO
            3 MAIL
            4 WEB
            5 PLAN
         */

        VariablesEstaticas.valoresBUsquedaObraSocial
                = Map.of(
                        "1", obraSocial.getNombre(),
                        "2", obraSocial.getTelefono().getTelefono(),
                        "3", obraSocial.getEmail().getEmail(),
                        "4", obraSocial.getWeb().getWeb(),
                        "5", obraSocial.getListaPlanesToString());
        return this;
    }
    
    public ServicioObraSocial vaciarListaObraSocial() {
         /*
            1 NOMBRE
            2 TELEFONO
            3 MAIL
            4 WEB
            5 PLAN
         */

        VariablesEstaticas.valoresBUsquedaObraSocial
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "");
        return this;
    }
    
    public ObraSocial rellenarDatosObraSocialVacia(ObraSocial obraSocial){
        if(obraSocial.getTelefono().getTelefono().equals("")){
            obraSocial.getTelefono().setTelefono("000000000");
        }
        if(obraSocial.getWeb().getWeb().equals("")){
            obraSocial.getWeb().setWeb("Sin web");
        }
        if(obraSocial.getEmail().getEmail().equals("")){
            obraSocial.getEmail().setEmail("Sin email");
        }
        return obraSocial;
    }
         
    
    
}
