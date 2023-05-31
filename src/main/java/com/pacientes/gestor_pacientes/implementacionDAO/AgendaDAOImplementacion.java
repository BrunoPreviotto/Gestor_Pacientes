/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_pacientes.DAO.IAgendaDAO;
import com.pacientes.gestor_pacientes.modelo.AccionesAgenda;
import com.pacientes.gestor_pacientes.modelo.Agenda;
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
public class AgendaDAOImplementacion extends PadreDAOImplementacion implements IAgendaDAO{

    

    @Override
    public AccionesAgenda obtener(AccionesAgenda agenda) throws SQLException {
        AccionesAgenda agendaResultado = new AccionesAgenda();
        String sqlObtenerAccion = "SELECT aa.accion \n" +
                                  "FROM agendas a \n" +
                                  "JOIN acciones_agendas aa ON a.id_agenda = aa.id_agenda\n" +
                                  "JOIN fechas_acciones_agendas faa ON aa.id_fecha = faa.id_fecha_accion\n" +
                                  "JOIN usuarios u ON a.id_usuario = u.id_usuario \n" +
                                  "WHERE u.es_ultima_sesion_iniciada = 1 AND faa.fecha = ?";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlObtenerAccion);
        psAgenda.setDate(1, Date.valueOf(agenda.getFecha()));
        ResultSet rsAgenda = psAgenda.executeQuery();
        
        
        if(rsAgenda.next()){
            agendaResultado.setAccion(rsAgenda.getString("accion"));
        }
        
        
        
        return agendaResultado;
    }
    
    
    @Override
    public List<AccionesAgenda> obtenerLista() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(AccionesAgenda accion, int numeroFucion) throws SQLException {
        String sqlActualizarAccion = "UPDATE acciones_agendas SET accion =? WHERE  id_agenda = ? AND id_fecha = ?;";
        
        int idAgenda = obtenerIdAgenda(VariablesEstaticas.usuario.getId());
        int idFecha = obtenerIdFecha(accion);
        
        System.out.println(idAgenda);
        
        System.out.println(idFecha);
        
        System.out.println(accion.getAccion());
        
        if(idAgenda != 0 && idFecha != 0){
            PreparedStatement psActualizarAccion = conexion.conexion().prepareStatement(sqlActualizarAccion);
            psActualizarAccion.setString(1, accion.getAccion());
            psActualizarAccion.setInt(2, idAgenda);
            psActualizarAccion.setInt(3, idFecha);
            psActualizarAccion.executeUpdate();
        }else{
            throw sqlException;
        }
        
    }

    @Override
    public void eliminar(AccionesAgenda accion, int numeroFuncion) throws SQLException {
        String sqlEliminarAccion = "DELETE FROM acciones_agendas WHERE id_fecha = ? AND id_agenda = ? AND accion = ?;";
        
        int idAgenda = obtenerIdAgenda(VariablesEstaticas.usuario.getId());
        int idFecha = obtenerIdFecha(accion);
        
        if(idAgenda != 0 && idFecha != 0){
            PreparedStatement psInsertarAccionAgenda = conexion.conexion().prepareStatement(sqlEliminarAccion);
            psInsertarAccionAgenda.setInt(1, idFecha);
            psInsertarAccionAgenda.setInt(2, idAgenda);
            psInsertarAccionAgenda.setString(3, accion.getAccion());
            psInsertarAccionAgenda.executeUpdate();
        }else{
            throw sqlException;
        }
        
        
                
                
    }

    @Override
    public void insertar(AccionesAgenda accion, int numeroFucion) throws SQLException {
        String sqlInsertarAccionAgenda = "INSERT INTO acciones_agendas (id_accion, accion, id_agenda, id_fecha, recordar) VALUES (0,?,?,?,?);";
        
        int idFecha = obtenerIdFecha(accion);
        int idAgenda = obtenerIdAgenda(VariablesEstaticas.usuario.getId());
        
        if(idFecha == 0){
            insertarFecha(accion);
        }
        
        
       if(idAgenda != 0){
           PreparedStatement psInsertarAccionAgenda = conexion.conexion().prepareStatement(sqlInsertarAccionAgenda);
           psInsertarAccionAgenda.setString(1, accion.getAccion());
           psInsertarAccionAgenda.setInt(2, idAgenda);
           psInsertarAccionAgenda.setInt(3, obtenerIdFecha(accion));
           psInsertarAccionAgenda.setBoolean(4, accion.getRecordar());
           psInsertarAccionAgenda.executeUpdate();
       }else{
           throw sqlException;
       }
        
        
        
    }
    
    public void insertarFecha(AccionesAgenda agenda) throws SQLException{
        String sqlFecha = "INSERT INTO fechas_acciones_agendas (id_fecha_accion, fecha) VALUES (0,?);";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlFecha);
        psAgenda.setDate(1, Date.valueOf(agenda.getFecha()));
        psAgenda.executeUpdate();
       
    }
    
    public int obtenerIdFecha(AccionesAgenda agenda)throws SQLException{
        String sqlFecha = "SELECT faa.id_fecha_accion FROM fechas_acciones_agendas faa WHERE faa.fecha = ?;";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlFecha);
        psAgenda.setDate(1, Date.valueOf(agenda.getFecha()));
        ResultSet rsIdFecha = psAgenda.executeQuery();
        
        if(rsIdFecha.next()){
            return rsIdFecha.getInt("id_fecha_accion");
        }else{
            return 0;
        }
        
    }
    
    public int obtenerIdAgenda(int idUsuario) throws SQLException{
        String sqlIdAgenda = "SELECT id_agenda FROM agendas a WHERE a.id_usuario = ?;";
        
        PreparedStatement psIdAgenda = conexion.conexion().prepareStatement(sqlIdAgenda);
        psIdAgenda.setInt(1, idUsuario);
        ResultSet rsIdAgenda = psIdAgenda.executeQuery();
        
        if(rsIdAgenda.next()){
            return rsIdAgenda.getInt("id_agenda");
        }else{
            return 0;
        }
    }
    
    
    

    
    
}
