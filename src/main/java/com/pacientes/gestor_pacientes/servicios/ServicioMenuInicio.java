/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.controlador.MenuInicioController;

/**
 *
 * @author bruno
 */
public class ServicioMenuInicio extends MenuInicioController{
    public void cerrarMenu(){
         vBoxRecuperarContraseña.setVisible(false);
         vBoxCodiRecuperacionOpciones.setVisible(false);
    }
}
