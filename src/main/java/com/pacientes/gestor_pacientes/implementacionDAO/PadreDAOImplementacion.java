

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.HonorarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
import com.pacientes.gestor_pacientes.modelo.Email;
import com.pacientes.gestor_pacientes.modelo.FrecuenciaSesion;
import com.pacientes.gestor_pacientes.modelo.ObraSocialPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.PlanObraSocial;
import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.SesionPaciente;
import com.pacientes.gestor_pacientes.modelo.TipoSesion;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author previotto
 */
public class PadreDAOImplementacion {
    
    protected ConexionMariadb conexion = ConexionMariadb.getInstacia();
    protected SQLException sqlException = new SQLException();
    protected CRUD daoImplementacion;
    
    
    
    
    
    
    
   
    
    
    public int obtenerIdPacienteConTodosLosDatos(Paciente pacienteParametro) throws Exception {
        Paciente paciente = new Paciente();

        String sqlDni = "SELECT p.id_paciente \n"
                + "FROM pacientes p\n"
                + "WHERE p.edad = ?\n"
                + "AND p.dni=? \n"
                + "AND p.id_nombre = ? \n"
                + "AND id_honorario = ? \n"
                + "AND id_telefono_paciente";
        PreparedStatement pSDni = conexion.conexion().prepareStatement(sqlDni);
        pSDni.setInt(1, pacienteParametro.getEdad());
        pSDni.setInt(2, pacienteParametro.getDni());

        pSDni.setInt(3, obtenerIdNombre(pacienteParametro.getNombre(), pacienteParametro.getApellido()));
        daoImplementacion = new HonorarioDAOImplementacion();
        pSDni.setInt(4, daoImplementacion.obtenerId(pacienteParametro.getHonorarios()));

        daoImplementacion =  new TelefonoDAOImplementacion();
        pSDni.setInt(5, daoImplementacion.obtenerId(pacienteParametro.getTelefono()));

        ResultSet rsSPaciente = pSDni.executeQuery();
        if (rsSPaciente.next()) {
            pSDni.close();
            rsSPaciente.close();
            return rsSPaciente.getInt("id_paciente");
        } else {

            return 0;
        }

    }
    
    
    
    /*public Paciente obtenerIdSesionAutorizacion(Paciente pacienteParametro) throws SQLException{
        SesionPaciente sesion = new SesionPaciente();
        AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
        Paciente pacienteResultado = new Paciente();
        
        String sqlSesionAutorizacion = "SELECT sp.id_sesion_paciente, a.id_autorizacion \n" +
                                        "FROM sesiones_pacientes sp \n" +
                                        "JOIN sesiones_pacientes_autorizaciones spa ON sp.id_sesion_paciente = spa.id_sesion_paciente \n" +
                                        "JOIN autorizaciones a ON spa.id_autorizacion = a.id_autorizacion \n" +
                                        "WHERE sp.fecha =? \n" +
                                        "AND sp.id_paciente =? \n" +
                                        "AND sp.numero_sesion =? \n" +
                                        "AND a.numero_autorizacion =? \n" +
                                        "AND a.asociacion =? ";
        
        
        
        PreparedStatement psSesionAutorizacion = conexion.conexion().prepareStatement(sqlSesionAutorizacion);
        psSesionAutorizacion.setDate(1, Date.valueOf(pacienteParametro.getSesion().getFecha()));
        psSesionAutorizacion.setInt(2, pacienteParametro.getId());
        psSesionAutorizacion.setInt(3, pacienteParametro.getSesion().getNumeroSesion());
        psSesionAutorizacion.setInt(4, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
        psSesionAutorizacion.setDate(5, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion()));
        ResultSet rsSesionAutorizacion = psSesionAutorizacion.executeQuery();
        if (rsSesionAutorizacion.next()) {
            psSesionAutorizacion.close();
            rsSesionAutorizacion.close();
            autorizacion.setId(rsSesionAutorizacion.getInt("id_autorizacion"));
            sesion.setAutorizacion(autorizacion);
            sesion.setIdSesion(rsSesionAutorizacion.getInt("id_sesion_paciente"));
            pacienteResultado.setSesion(sesion);
            
            
            return pacienteResultado;
        }else{

            return null;
        }
        
    }*/
    
    
    
    
     
     
     
     public int obtenerIdNombre(String nombre, String apellido){
       
        String sqlSNombre = "SELECT id_nombre FROM nombres WHERE nombre=? AND apellido=?;";
        try {
            
            PreparedStatement pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            pSNombre.setString(1, nombre);
            pSNombre.setString(2, apellido);
            ResultSet rsSNombre = pSNombre.executeQuery();
            if(rsSNombre.next()){
                return rsSNombre.getInt("id_nombre");
            }
            
           pSNombre.close();
           rsSNombre.close();
            
            
        } catch (Exception e) {
        }
        return 0;
    }
     
     
     
     public int pacientesPorUnUsuario(int idPaciente){
        int cantidadPacientesPorUsuario = 0;
        String sql = "SELECT id_usuario, id_paciente FROM usuarios_pacientes up WHERE id_paciente = ? AND id_usuario = ?";
        try {
            
            PreparedStatement ps = conexion.conexion().prepareStatement(sql);
            ps.setInt(1, idPaciente);
             ps.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rs = ps.executeQuery();
            
            
            
           ps.close();
           rs.close();
           if(rs.next()){
               return 1;
           }
            return 0;
            
        } catch (Exception e) {
        }
        return 0;
    }
     
     /*public int idPacientesPorUnUsuario(int idPaciente){
        int cantidadPacientesPorUsuario = 0;
        String sql = "SELECT id_paciente FROM usuarios_pacientes up WHERE id_paciente = ? AND id_usuario = ?";
        try {
            
            PreparedStatement ps = conexion.conexion().prepareStatement(sql);
            ps.setInt(1, idPaciente);
             ps.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rs = ps.executeQuery();
            
            
            
           ps.close();
           rs.close();
           if(rs.next()){
               return rs.getInt("id_paciente");
           }
            return 0;
            
        } catch (Exception e) {
        }
        return 0;
    }*/
     
     
     
             
    public int ExisteMasDeUnPacientePOrUsuario(int dni) {
        int cantidadPacientesPorUsuario = 0;
        String sql = "SELECT p.id_paciente \n" +
                        "FROM pacientes p \n" +
                        "JOIN usuarios_pacientes up ON p.id_paciente = up.id_paciente \n" +
                        "WHERE p.dni = ? AND up.id_usuario  = ?";
        try {

            PreparedStatement ps = conexion.conexion().prepareStatement(sql);
            ps.setInt(1, dni);
            ps.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                cantidadPacientesPorUsuario += pacientesPorUnUsuario(rs.getInt("id_paciente"));
            }

            ps.close();
            rs.close();
            
            
            
            
            return cantidadPacientesPorUsuario;
            
            

        } catch (Exception e) {
        }
        return 0;
    }
    
    
    public int obtenerIdPacientePOrUsuario(int dni) {
        int cantidadPacientesPorUsuario = 0;
        String sql = "SELECT p.id_paciente \n" +
                        "FROM pacientes p \n" +
                        "JOIN usuarios_pacientes up ON p.id_paciente = up.id_paciente \n" +
                        "WHERE p.dni = ? AND up.id_usuario  = ?";
        try {

            PreparedStatement ps = conexion.conexion().prepareStatement(sql);
            ps.setInt(1, dni);
            ps.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {                
                return rs.getInt("id_paciente");
            }else{
                return 0;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
}
