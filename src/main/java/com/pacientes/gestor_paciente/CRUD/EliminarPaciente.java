/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_paciente.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author previotto
 */
public class EliminarPaciente extends PacienteDAOImplementacion {
    
    public void eliminarPaciente(Paciente pacienteParametro){
        try {
            String sqlEliminar = "UPDATE pacientes SET es_paciente = false WHERE dni=?";
            PreparedStatement pSeliminar = conexion.conexion().prepareStatement(sqlEliminar);
            pSeliminar.setInt(1, pacienteParametro.getDni());
            pSeliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void eliminarDiagnostico(Paciente pacienteParametro) throws SQLException{
        
        String sqlEliminarDiagnostico = "DELETE FROM diagnosticos WHERE id_paciente = ?;";
        PreparedStatement psEliminarDiagnostico = conexion.conexion().prepareStatement(sqlEliminarDiagnostico);
        psEliminarDiagnostico.setInt(1, obtenerIdPaciente(pacienteParametro).getId());
        psEliminarDiagnostico.executeUpdate();
        
        
    }
    
    
    
    public void eliminarObraSocialPaciente(Paciente pacienteParametro) throws SQLException{
        
        String sqlEliminarObraSocialPaciente = "DELETE FROM afiliados_obras_sociales  WHERE id_paciente = ?;";
        PreparedStatement psEliminarObraSocialPaciente = conexion.conexion().prepareStatement(sqlEliminarObraSocialPaciente);
        psEliminarObraSocialPaciente.setInt(1, obtenerIdPaciente(pacienteParametro).getId());
        psEliminarObraSocialPaciente.executeUpdate();
        
        
    }
    
    
    public void eliminarPlanTratamiento(Paciente pacienteParametro) throws SQLException{
        
        String sqlEliminarPlanTratamiento = "DELETE FROM planes_tratamientos  WHERE id_paciente = ?;";
        PreparedStatement psEliminarPlanTratamiento = conexion.conexion().prepareStatement(sqlEliminarPlanTratamiento);
        psEliminarPlanTratamiento.setInt(1, obtenerIdPaciente(pacienteParametro).getId());
        psEliminarPlanTratamiento.executeUpdate();
        
        
    }
    
    public void eliminarSesion(Paciente pacienteParametro) throws SQLException{
        String sqlEliminarSesion = "DELETE from sesiones_pacientes_autorizaciones  WHERE id_sesion_paciente = ? AND id_autorizacion = ?;";
        
        PreparedStatement psEliminarSesion = conexion.conexion().prepareStatement(sqlEliminarSesion);
        psEliminarSesion.setInt(1, obtenerIdSesionAutorizacion(pacienteParametro).getSesion().getIdSesion());
        psEliminarSesion.setInt(2, obtenerIdSesionAutorizacion(pacienteParametro).getSesion().getAutorizacion().getId());
        
        
        psEliminarSesion.executeUpdate();
        
        
    }
    
    public void eliminarAutorizacion(Paciente pacienteParametro) throws SQLException{
        String sqlEliminarAutorizacion = "UPDATE sesiones_pacientes_autorizaciones  SET id_autorizacion = 5 WHERE id_sesion_paciente = ?;";
        
        PreparedStatement psEliminarAutorizacion = conexion.conexion().prepareStatement(sqlEliminarAutorizacion);
        psEliminarAutorizacion.setInt(1, obtenerIdSesionAutorizacion(pacienteParametro).getSesion().getIdSesion());
        psEliminarAutorizacion.executeUpdate();
        
        
    }
    
    
}
