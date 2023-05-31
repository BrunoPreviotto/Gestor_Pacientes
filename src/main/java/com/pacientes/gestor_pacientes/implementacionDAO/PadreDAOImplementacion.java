/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
import com.pacientes.gestor_pacientes.modelo.Email;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.SesionPaciente;
import com.pacientes.gestor_pacientes.modelo.TipoSesion;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
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
    
    public int obtenerIdEmail(Email email) throws SQLException{
        String sqlObtenerIdEmail = "SELECT e.id_email \n" +
                                        "FROM emails e \n" +
                                        "WHERE e.email LIKE ?";
        
        System.out.println(email.getEmail());
        
        PreparedStatement psIdEmail = conexion.conexion().prepareStatement(sqlObtenerIdEmail);
        psIdEmail.setString(1, email.getEmail());
        ResultSet rsIdEmail = psIdEmail.executeQuery();
        
        if(rsIdEmail.next()){
            return rsIdEmail.getInt("id_email");
        }else{
            return 0;
        }
        
    }
    
    
    public int obtenerIdPaciente(Paciente pacienteParametro) throws SQLException{
        Paciente paciente = new Paciente();
        
        
        String sqlDni = "SELECT id_paciente FROM pacientes WHERE dni=?";
        PreparedStatement pSDni = conexion.conexion().prepareStatement(sqlDni);
        pSDni.setInt(1, pacienteParametro.getDni());
        ResultSet rsSPaciente = pSDni.executeQuery();
        if (rsSPaciente.next()) {
            pSDni.close();
            rsSPaciente.close();
            return rsSPaciente.getInt("id_paciente");
        }else{

            return 0;
        }

        
    }
    
    
    
    public int obtenerIdHonorario(Paciente pacienteParametro) throws SQLException{
        String sqlHonorario = "SELECT  id_honorario  FROM honorarios h WHERE h.honorario =?";
        
        PreparedStatement psHonorario = conexion.conexion().prepareStatement(sqlHonorario);
        psHonorario.setDouble(1, pacienteParametro.getHonorarios().getHonorario());
        ResultSet rsHonorario = psHonorario.executeQuery();
        if (rsHonorario.next()) {
            psHonorario.close();
            rsHonorario.close();
            return rsHonorario.getInt("id_honorario");
        }else{

            return 0;
        }

        
    }
    
    
    public int obtenerIdEstadoFacturacion(Paciente pacienteParametro) throws SQLException{
        String sqlEstado = "SELECT ef.id_estado_facturacion FROM estados_facturacion ef WHERE estado = ?";
        
        
        
        PreparedStatement psEstado = conexion.conexion().prepareStatement(sqlEstado);
        psEstado.setString(1, pacienteParametro.getSesion().getEstado().getEstado());
        ResultSet rsEstado = psEstado.executeQuery();
        if (rsEstado.next()) {
            psEstado.close();
            rsEstado.close();
            return rsEstado.getInt("id_estado_facturacion");
        }else{

            return 0;
        }
        

        
    }
    
    public Paciente obtenerIdSesionAutorizacion(Paciente pacienteParametro) throws SQLException{
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
        System.out.println("id " + pacienteParametro.getId());
        System.out.println("numSesion " + pacienteParametro.getSesion().getNumeroSesion());
        System.out.println("numAutorizacion "  + pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
        System.out.println("asociacion " + pacienteParametro.getSesion().getAutorizacion().getAsociacion());
        
        
        
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
        
    }
    
    
    
    public int obtenerIdCodigoFacturacion(CodigoFacturacion codigo) throws SQLException{
        String sqlIdCodFacturacion = "SELECT id_codigo_facturacion FROM codigos_facturaciones WHERE nombre =?";
        
        PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlIdCodFacturacion);
        psCodigo.setString(1, codigo.getNombre());
        ResultSet rsCodigo = psCodigo.executeQuery();
        if (rsCodigo.next()) {
            psCodigo.close();
            rsCodigo.close();
            return rsCodigo.getInt("id_codigo_facturacion");
        }else{

            return 0;
        }

        
    }
    
    public int obtenerIdAutorizacion(Paciente pacienteParametro) throws SQLException{
        String sqlIdAutorizacion = "SELECT id_autorizacion FROM autorizaciones WHERE numero_autorizacion=? AND asociacion=?";
        
        PreparedStatement psAutorizacion = conexion.conexion().prepareStatement(sqlIdAutorizacion);
        
        
        psAutorizacion.setInt(1, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
        psAutorizacion.setDate(2, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion()));
        ResultSet rsAutorizacion = psAutorizacion.executeQuery();
        if (rsAutorizacion.next()) {
            psAutorizacion.close();
            rsAutorizacion.close();
            return rsAutorizacion.getInt("id_autorizacion");
        }else{

            return 0;
        }

        
    }
    
    
    
    public int obtenerIdPlanObraSocial(Paciente pacienteParametro){
        String sqlObtenerIdObraSocial = "SELECT id_plan_obra_social FROM planes_obras_sociales WHERE nombre = ?;";
        
        try {
            
            //BUSCAR ID DE PLAN OBRA SOCIAL  
            PreparedStatement psBuscarIdPlanObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
            psBuscarIdPlanObraSocial.setString(1, pacienteParametro.getObraSocialPaciente().getPlan().getNombre());
            ResultSet rsBuscarIdPlanObraSocial= psBuscarIdPlanObraSocial.executeQuery();
            if(rsBuscarIdPlanObraSocial.next()){
                return rsBuscarIdPlanObraSocial.getInt("id_plan_obra_social");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    public int obtenerIdFrecuenciaSesion(PlanTratamiento plan){
        String sqlObtenerIdAFrecuencia = "SELECT id_frecuencia_sesion FROM frecuencias_sesiones WHERE frecuencia LIKE ?;";
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdFrecuencia = conexion.conexion().prepareStatement(sqlObtenerIdAFrecuencia);
            psBuscarIdFrecuencia.setString(1, plan.getFrecuenciaSesion());
            ResultSet rsBuscarIdFrecuencia= psBuscarIdFrecuencia.executeQuery();
            if(rsBuscarIdFrecuencia.next()){
                return rsBuscarIdFrecuencia.getInt("id_frecuencia_sesion");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    public int obtenerIdAfiliado(Paciente pacienteParametro){
        String sqlObtenerIdAfiliado = "SELECT id_afiliado_obra_social  FROM afiliados_obras_sociales aos WHERE numero_afiliado = ?;";
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdFrecuencia = conexion.conexion().prepareStatement(sqlObtenerIdAfiliado);
            psBuscarIdFrecuencia.setInt(1, pacienteParametro.getObraSocialPaciente().getAfiliado().getNumero());
            ResultSet rsBuscarIdAfiliado= psBuscarIdFrecuencia.executeQuery();
            if(rsBuscarIdAfiliado.next()){
                return rsBuscarIdAfiliado.getInt("id_afiliado_obra_social");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
    
     public int obtenerIdTipoSesion(TipoSesion tipo){
        String sqlObtenerIdATipoSesion = "SELECT id_tipo_sesion FROM tipos_sesiones WHERE nombre=?;";
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdTipoSesion = conexion.conexion().prepareStatement(sqlObtenerIdATipoSesion);
            psBuscarIdTipoSesion.setString(1, tipo.getNombre());
            ResultSet rsBuscarIdTipoSesion= psBuscarIdTipoSesion.executeQuery();
            if(rsBuscarIdTipoSesion.next()){
                return rsBuscarIdTipoSesion.getInt("id_tipo_sesion");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
     
     
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
    
}
