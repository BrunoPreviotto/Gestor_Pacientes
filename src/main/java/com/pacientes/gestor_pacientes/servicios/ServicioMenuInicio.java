/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.controlador.MenuInicioController;
import com.pacientes.gestor_pacientes.implementacionDAO.ActualizacionDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Actualizacion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.SQLException;

/**
 *
 * @author bruno
 */
public class ServicioMenuInicio extends MenuInicioController{
    public void cerrarMenu(){
         vBoxRecuperarContraseña.setVisible(false);
         vBoxCodiRecuperacionOpciones.setVisible(false);
    }
    
    public  void consultarEstadoActualizacion(){
       
        
        
        try {
            
            ActualizacionDAOImplementacion aDao = new ActualizacionDAOImplementacion();
            
            Actualizacion act = aDao.obtener(new Actualizacion());
            
             if(act.isReciente()){
                 act.setReciente(false);
                 aDao.actualizar(act);
                 if(act.isExito()){
                     
                      mensajeAdvertenciaError("¡Actualización exitosa!", this, VariablesEstaticas.imgenExito);
                      
                     
                 }else{
                        mensajeAdvertenciaError("Error al actualizar", this, VariablesEstaticas.imgenError);
                     
                    
                     
                 }
                 
                 
                 
                 
            }
             
        } catch (Exception e) {
            e.printStackTrace();
           
          
            
        }
       
    }
}
