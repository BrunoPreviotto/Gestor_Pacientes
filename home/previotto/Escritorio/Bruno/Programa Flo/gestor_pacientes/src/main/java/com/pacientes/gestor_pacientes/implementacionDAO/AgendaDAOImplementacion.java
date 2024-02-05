/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_pacientes.DAO.IAgendaDAO;
import com.pacientes.gestor_pacientes.modelo.AccionesAgenda;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author previotto
 */
public class AgendaDAOImplementacion extends PadreDAOImplementacion implements IAgendaDAO{

    

    @Override
    public AccionesAgenda obtener(AccionesAgenda agenda) throws SQLException {
        
        AccionesAgenda agendaResultado = new AccionesAgenda();
        String sqlObtenerAccion = "SELECT aa.accion, aa.recordar, hfaa.horario, faa.fecha \n" +
                                    "FROM agendas a\n" +
                                    "JOIN acciones_agendas aa ON a.id_agenda = aa.id_agenda\n" +
                                    "JOIN fechas_acciones_agendas faa ON aa.id_fecha = faa.id_fecha_accion\n" +
                                    "JOIN horario_fecha_accion_agenda hfaa  ON hfaa.id_horario = faa.id_horario \n" +
                                    "JOIN usuarios u ON a.id_usuario = u.id_usuario\n" +
                                    "WHERE u.es_ultima_sesion_iniciada = 1 AND faa.fecha = ?";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlObtenerAccion);
        psAgenda.setDate(1, Date.valueOf(agenda.getFecha()));
        ResultSet rsAgenda = psAgenda.executeQuery();
        
        
        if(rsAgenda.next()){
            String [] fecha = rsAgenda.getString("fecha").split("-");
            String [] hora = rsAgenda.getString("horario").split(":");
            
            
            
            agendaResultado.setAccion(rsAgenda.getString("accion"));
            agendaResultado.setRecordar(rsAgenda.getBoolean("recordar"));
            agendaResultado.setFechaYHora(LocalDateTime.of(Integer.parseInt(fecha[0]), 
                                                        Integer.parseInt(fecha[1]), 
                                                        Integer.parseInt(fecha[2]),
                                                        Integer.parseInt(hora[0]),
                                                        Integer.parseInt(hora[1]),
                                                        Integer.parseInt(hora[2])));
        }
        
        
        
        return agendaResultado;
    }
    
    public boolean obtenerRecordatorio(LocalDate fecha) throws SQLException{
        String sql = "SELECT aa.recordar\n" +
                    "FROM acciones_agendas aa\n" +
                    "JOIN fechas_acciones_agendas faa ON aa.id_fecha = faa.id_fecha_accion\n" +
                    "JOIN agendas a ON a.id_agenda = aa.id_agenda\n" +
                    "WHERE faa.fecha = ? AND a.id_usuario = ? AND aa.recordar = 1;";
        
        PreparedStatement ps = conexion.conexion().prepareStatement(sql);
        ps.setDate(1, Date.valueOf(fecha));
        ps.setInt(2, VariablesEstaticas.usuario.getId());
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            return rs.getBoolean("recordar");
        }
        
        return false;
    }
    
    
     public boolean obtenerSiExisteAccion(LocalDate fecha) throws SQLException {
        AccionesAgenda agendaResultado = new AccionesAgenda();
        String sqlObtenerAccion = "SELECT aa.accion \n" +
                                  "FROM agendas a \n" +
                                  "JOIN acciones_agendas aa ON a.id_agenda = aa.id_agenda\n" +
                                  "JOIN fechas_acciones_agendas faa ON aa.id_fecha = faa.id_fecha_accion\n" +
                                  "JOIN usuarios u ON a.id_usuario = u.id_usuario \n" +
                                  "WHERE u.es_ultima_sesion_iniciada = 1 AND faa.fecha = ?";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlObtenerAccion);
        psAgenda.setDate(1, Date.valueOf(fecha));
        ResultSet rsAgenda = psAgenda.executeQuery();
        
        
        if(rsAgenda.next()){
            return true;
        }
        
        
        
        return false;
    }
    
    
   

    @Override
    public void actualizar(AccionesAgenda accion) throws SQLException {
        String sqlActualizarAccion = "UPDATE acciones_agendas SET accion =?, recordar=?, id_horario=? WHERE  id_agenda = ? AND id_accion = ?;";
        
        int idAgenda = obtenerIdAgenda(VariablesEstaticas.usuario.getId());
        
        int idHOra = obtenerIdHora(accion);
        
        if(idHOra == 0){
            insertarHora(accion);
            idHOra = obtenerIdHora(accion);
        }
        
        
        if(idAgenda != 0){
            PreparedStatement psActualizarAccion = conexion.conexion().prepareStatement(sqlActualizarAccion);
            psActualizarAccion.setString(1, accion.getAccion());
            psActualizarAccion.setBoolean(2, accion.getRecordar());
            psActualizarAccion.setInt(3, idHOra);
            psActualizarAccion.setInt(4, idAgenda);
            psActualizarAccion.setInt(5, accion.getId());
            
            psActualizarAccion.executeUpdate();
        }else{
            throw sqlException;
        }
        
    }

    @Override
    public void eliminar(AccionesAgenda accion) throws SQLException {
        String sqlEliminarAccion = "DELETE FROM acciones_agendas WHERE id_agenda = ? AND id_accion = ?;";
        
        int idAgenda = obtenerIdAgenda(VariablesEstaticas.usuario.getId());
        
        
        if(idAgenda != 0){
            PreparedStatement psInsertarAccionAgenda = conexion.conexion().prepareStatement(sqlEliminarAccion);
            psInsertarAccionAgenda.setInt(1, idAgenda);
            psInsertarAccionAgenda.setInt(2, accion.getId());
            psInsertarAccionAgenda.executeUpdate();
        }else{
            throw sqlException;
        }
        
        
                
                
    }

    @Override
    public void insertar(AccionesAgenda accion) throws SQLException {
        String sqlInsertarAccionAgenda = "INSERT INTO acciones_agendas (id_accion, accion, id_agenda, id_fecha, recordar, id_horario) VALUES (0,?,?,?,?,?);";
        
        int idFecha = obtenerIdFecha(accion);
        int idAgenda = obtenerIdAgenda(VariablesEstaticas.usuario.getId());
        int idHora = obtenerIdHora(accion);
        
        if(idHora == 0){
            insertarHora(accion);
            idHora = obtenerIdHora(accion);
        }
        
        if(idFecha == 0){
            insertarFecha(accion);
            idFecha = obtenerIdFecha(accion);
        }
        
        
        
       if(idAgenda != 0){
           PreparedStatement psInsertarAccionAgenda = conexion.conexion().prepareStatement(sqlInsertarAccionAgenda);
           psInsertarAccionAgenda.setString(1, accion.getAccion());
           psInsertarAccionAgenda.setInt(2, idAgenda);
           psInsertarAccionAgenda.setInt(3, idFecha);
           psInsertarAccionAgenda.setBoolean(4, accion.getRecordar());
           psInsertarAccionAgenda.setInt(5, idHora);
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
    
    public void insertarHora(AccionesAgenda agenda) throws SQLException{
        String sqlFecha = "INSERT INTO horario_fecha_accion_agenda  (id_horario, horario) VALUES (0,?);";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlFecha);
        psAgenda.setTime(1, Time.valueOf(agenda.getFechaYHora().getHour() + ":" + agenda.getFechaYHora().getMinute() + ":" + agenda.getFechaYHora().getSecond()));
        psAgenda.executeUpdate();
       
    }
    
    public int obtenerIdHora(AccionesAgenda agenda)throws SQLException{
        String sqlHora = "SELECT id_horario FROM horario_fecha_accion_agenda WHERE horario = ?;";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlHora);
        psAgenda.setTime(1, Time.valueOf(agenda.getFechaYHora().getHour() + ":" + agenda.getFechaYHora().getMinute() + ":" + agenda.getFechaYHora().getSecond()));
        ResultSet rsIdHora = psAgenda.executeQuery();
        
        if(rsIdHora.next()){
            return rsIdHora.getInt("id_horario");
        }else{
            return 0;
        }
        
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

    @Override
    public int obtenerId(AccionesAgenda objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AccionesAgenda> obtenerLista(AccionesAgenda agenda) throws SQLException {
        
        List <AccionesAgenda> listaAgendaResultado = new ArrayList();
        String sqlObtenerAccion = "SELECT aa.id_accion, aa.accion, aa.recordar, hfaa.horario, faa.fecha\n" +
                                    "FROM agendas a\n" +
                                    "JOIN acciones_agendas aa ON a.id_agenda = aa.id_agenda\n" +
                                    "JOIN fechas_acciones_agendas faa ON aa.id_fecha = faa.id_fecha_accion\n" +
                                    "JOIN horario_fecha_accion_agenda hfaa  ON hfaa.id_horario = aa.id_horario\n" +
                                    "JOIN usuarios u ON a.id_usuario = u.id_usuario\n" +
                                    "WHERE u.es_ultima_sesion_iniciada = 1 AND faa.fecha = ?";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlObtenerAccion);
        psAgenda.setDate(1, Date.valueOf(agenda.getFecha()));
        ResultSet rsAgenda = psAgenda.executeQuery();
        
        
        while(rsAgenda.next()){
            AccionesAgenda agendaResultado = new AccionesAgenda();
            
            String [] fecha = rsAgenda.getString("fecha").split("-");
            String [] hora = rsAgenda.getString("horario").split(":");
            
            System.out.println(hora[1]);
           
            agendaResultado.setId(rsAgenda.getInt("id_accion"));
            agendaResultado.setAccion(rsAgenda.getString("accion"));
            agendaResultado.setRecordar(rsAgenda.getBoolean("recordar"));
            agendaResultado.setFechaYHora(LocalDateTime.of(Integer.parseInt(fecha[0]), 
                                                        Integer.parseInt(fecha[1]), 
                                                        Integer.parseInt(fecha[2]),
                                                        Integer.parseInt(hora[0]),
                                                        Integer.parseInt(hora[1]),
                                                        Integer.parseInt(hora[2])));
           
            
            listaAgendaResultado.add(agendaResultado);
        }
        
        
        
        return listaAgendaResultado;
    }

   
    
    

    
    
}
