/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class AutorizacionDAOImplementacion extends PadreDAOImplementacion implements CRUD<AutorizacionesSesionesObraSociales>{

    @Override
    public List<AutorizacionesSesionesObraSociales> obtenerLista(AutorizacionesSesionesObraSociales objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AutorizacionesSesionesObraSociales obtener(AutorizacionesSesionesObraSociales objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(AutorizacionesSesionesObraSociales objetoParametro) throws SQLException {
        String sqlActualizarAutorizacion = "UPDATE autorizaciones SET \n"
                + "numero_autorizacion = ?,\n"
                + "observacion = ?,\n"
                + "asociacion = ?,\n"
                + "copago = ?,\n"
                + "id_codigo_facturacion = ?,\n"
                + "numeroIdentificadorAutorizacion = ? \n"
                + "WHERE id_autorizacion = ?";
        
        

        PreparedStatement psActualizarAutorizacion = conexion.conexion().prepareStatement(sqlActualizarAutorizacion);
        
        psActualizarAutorizacion.setInt(1, objetoParametro.getNumeroAutorizacion());

        psActualizarAutorizacion.setString(2, objetoParametro.getObservacion());

        psActualizarAutorizacion.setDate(3, Date.valueOf(objetoParametro.getAsociacion().toString()));
        
        psActualizarAutorizacion.setDouble(4, objetoParametro.getCopago());
        
        psActualizarAutorizacion.setInt(5, objetoParametro.getCodigoFacturacion().getId());

         psActualizarAutorizacion.setString(6, String.valueOf(VariablesEstaticas.usuario.getId()) + 
                                                                                objetoParametro.getIdPaciente() +
                                                                                objetoParametro.getIdSesion() +
                                                                                objetoParametro.getNumeroAutorizacion() + 
                                                                                objetoParametro.getAsociacion().getYear() +
                                                                                objetoParametro.getAsociacion().getDayOfMonth() +
                                                                                objetoParametro.getAsociacion().getMonthValue());
        
        psActualizarAutorizacion.setInt(7, objetoParametro.getId());
        System.out.println(objetoParametro.getId());
        
        psActualizarAutorizacion.executeUpdate();
    }

    @Override
    public void eliminar(AutorizacionesSesionesObraSociales objetoParametro) throws Exception {
        String sqlEliminarAutorizacion = "UPDATE sesiones_pacientes_autorizaciones  SET id_autorizacion = 5 WHERE id_sesion_paciente = ?;";
        PreparedStatement psEliminarAutorizacion = conexion.conexion().prepareStatement(sqlEliminarAutorizacion);
        
        psEliminarAutorizacion.setInt(1, objetoParametro.getIdSesion());
        psEliminarAutorizacion.executeUpdate();
    }

    @Override
    public void insertar(AutorizacionesSesionesObraSociales objetoParametro) throws Exception {
        String sqlAutorizacion = "INSERT INTO autorizaciones  (id_autorizacion, numero_autorizacion, observacion, asociacion, copago, id_codigo_facturacion, numeroIdentificadorAutorizacion) VALUES(?,?,?,?,?,?,?)";
        daoImplementacion =  new CodigoFacturacionDAOImplementacion();
        int idCodigoFacturacion = daoImplementacion.obtenerId(objetoParametro.getCodigoFacturacion());
        
        if(idCodigoFacturacion == 0){
            idCodigoFacturacion = 5;
        }
        
        //INSERTAR AUTORIZACION
        PreparedStatement pstA = conexion.conexion().prepareStatement(sqlAutorizacion);
        
        
       
        pstA.setInt(1,0);
        pstA.setInt(2, objetoParametro.getNumeroAutorizacion());
        pstA.setString(3, objetoParametro.getObservacion());
        pstA.setDate(4, Date.valueOf(objetoParametro.getAsociacion().toString()));
        pstA.setDouble(5, objetoParametro.getCopago());
        pstA.setInt(6, idCodigoFacturacion);
        pstA.setString(7, 
                String.valueOf(VariablesEstaticas.usuario.getId()) + 
                        objetoParametro.getIdPaciente() +
                        objetoParametro.getIdSesion() +
                        objetoParametro.getNumeroAutorizacion() + 
                        objetoParametro.getAsociacion().getYear() +
                        objetoParametro.getAsociacion().getDayOfMonth() +
                        objetoParametro.getAsociacion().getMonthValue());
        
        
        
        
        pstA.executeUpdate();
        pstA.close();
        
        
        
        
    }

    @Override
    public int obtenerId(AutorizacionesSesionesObraSociales objetoParametro) throws Exception {
        String sqlIdAutorizacion = "SELECT a.id_autorizacion \n"
                + "FROM autorizaciones a \n"
                + "WHERE a.numeroIdentificadorAutorizacion LIKE ?;";

        PreparedStatement psAutorizacion = conexion.conexion().prepareStatement(sqlIdAutorizacion);
        
        System.out.println(String.valueOf(VariablesEstaticas.usuario.getId()) + 
                        objetoParametro.getIdPaciente() +
                        objetoParametro.getIdSesion() +
                        objetoParametro.getNumeroAutorizacion() + 
                        objetoParametro.getAsociacion().getYear() +
                        objetoParametro.getAsociacion().getDayOfMonth() +
                        objetoParametro.getAsociacion().getMonthValue());
       
      
        psAutorizacion.setString(1, 
                String.valueOf(VariablesEstaticas.usuario.getId()) + 
                        objetoParametro.getIdPaciente() +
                        objetoParametro.getIdSesion() +
                        objetoParametro.getNumeroAutorizacion() + 
                        objetoParametro.getAsociacion().getYear() +
                        objetoParametro.getAsociacion().getDayOfMonth() +
                        objetoParametro.getAsociacion().getMonthValue());
        
       
        
        
        ResultSet rsAutorizacion = psAutorizacion.executeQuery();
        if (rsAutorizacion.next()) {
            return rsAutorizacion.getInt("id_autorizacion");
        } else {

            return 0;
        }
    }

   

    
    
}
