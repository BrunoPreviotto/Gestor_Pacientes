/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_paciente.CRUD.InsertarPaciente;
import com.pacientes.gestor_paciente.CRUD.AcutualizarPaciente;
import com.google.common.collect.ImmutableMap;
import com.pacientes.gestor_paciente.CRUD.EliminarPaciente;
import com.pacientes.gestor_paciente.CRUD.ObtenerPaciente;
import com.pacientes.gestor_pacientes.DAO.IPacienteDAO;
import com.pacientes.gestor_pacientes.controlador.MenuInicioController;
import com.pacientes.gestor_pacientes.modelo.*;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import com.pacientes.gestor_pacientes.servicios.Encriptar;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author previotto
 */
public class PacienteDAOImplementacion extends PadreDAOImplementacion implements IPacienteDAO {
    
    protected ConexionMariadb conexion = ConexionMariadb.getInstacia();
    
     @Override
    public Paciente obtener(Paciente pacienteParametro) throws SQLException{
        ObtenerPaciente obtenerPaciente = new ObtenerPaciente();
        Paciente paciente = new Paciente();
        Paciente pacienteResultado = new Paciente();
       
        if (esPacienteDeUsuarioActual(pacienteParametro)) {
            
            if (!Objects.isNull(pacienteParametro)) {
                if (!Objects.isNull(pacienteParametro.getDni())) {
                    pacienteResultado = obtenerPaciente.obtenerPaciente(pacienteParametro);
                    paciente.setNombre(
                            pacienteResultado.
                                    getNombre()).
                            setApellido(pacienteResultado.getApellido()).
                            setEdad(pacienteResultado.getEdad()).
                            setDni(pacienteResultado.getDni()).
                            setTelefono(pacienteResultado.getTelefono()).
                            setHonorarios(pacienteResultado.getHonorarios());
                }
                if (!Objects.isNull(pacienteParametro.getId())) {
                    pacienteResultado = obtenerPaciente.obtenerSesiones(pacienteParametro.getId());
                    paciente.setSesiones(pacienteResultado.getSesiones());

                    pacienteResultado = obtenerPaciente.obtenerPlanTratamiento(pacienteParametro.getId());
                    paciente.setPlanTratamiento(pacienteResultado.getPlanTratamiento());

                    pacienteResultado = obtenerPaciente.obtenerDiagnosticoPaciente(pacienteParametro.getId());
                    paciente.setDiagnostico(pacienteResultado.getDiagnostico());

                    pacienteResultado = obtenerPaciente.obtenerObraSocialPaciente(pacienteParametro.getId());
                    paciente.setObraSocialPaciente(pacienteResultado.getObraSocialPaciente());
                }

            }
            return paciente;
        }
        return null;
    }
    
    @Override
    public void actualizar(Paciente paciente, int numeroFucion) throws SQLException {
        AcutualizarPaciente actualizarPaciente = new AcutualizarPaciente();
        MenuInicioController menuInicio = new MenuInicioController();
        
        switch (numeroFucion) {
            case 1:
                actualizarPaciente.actualizarDiagnostico(paciente);
                break;
            case 2:
                actualizarPaciente.actualizarPlan(paciente);
                break;
            case 3:
                actualizarPaciente.actualizarPaciente(paciente);
                break;
            case 4:
                actualizarPaciente.actualizarSesion(paciente);
                break;
            case 5:
                actualizarPaciente.actualizarObraSocialPaciente(paciente);
                break;
        }
    }
    
    public void actualizarFrecuenciaPlan(PlanTratamiento plan) throws SQLException{
        String sqlActualizarFrecuencia = "UPDATE frecuencias_sesiones SET frecuencia = ? WHERE id_frecuencia_sesion = ?;";
        
        PreparedStatement psFrecuencia = conexion.conexion().prepareStatement(sqlActualizarFrecuencia);
        psFrecuencia.setString(1, plan.getFrecuenciaSesion());
        psFrecuencia.setInt(2, plan.getIdPlan());
        psFrecuencia.executeUpdate();
        
    }
    
    public void actualizarTipoSesion(TipoSesion tipo) throws SQLException{
        String sqlActualizarTipoSesion = "UPDATE tipos_sesiones SET nombre = ?, descripcion = ? WHERE id_tipo_sesion = ?;";
        
        PreparedStatement psTipoSesion = conexion.conexion().prepareStatement(sqlActualizarTipoSesion);
        psTipoSesion.setString(1, tipo.getNombre());
        psTipoSesion.setString(2, tipo.getDecripcion());
        psTipoSesion.setInt(3, tipo.getId());
        psTipoSesion.executeUpdate();
        
    }
    
    @Override
    public void eliminar(Paciente paciente, int numeroFuncion) throws SQLException{
        
        EliminarPaciente eliminar = new EliminarPaciente();
        
        switch (numeroFuncion) {
            case 1:
                eliminar.eliminarPaciente(paciente);
                break;
            case 2:
                //DATOS PRINCIPALES
                break;
            case 3:
                eliminar.eliminarDiagnostico(paciente);
                break;
            case 4:
                eliminar.eliminarSesion(paciente);
                break;
            case 5:
                eliminar.eliminarObraSocialPaciente(paciente);
                break;
            case 6:
                eliminar.eliminarPlanTratamiento(paciente);
                break;
            case 7:
                eliminar.eliminarAutorizacion(paciente);
                break;
        }
        
        
        
    }

    @Override
    public void insertar(Paciente paciente, int numeroFucion) throws Exception{
        InsertarPaciente insertarPaciente = new InsertarPaciente();
        switch (numeroFucion) {
            case 1:
                insertarPaciente.insertarPaciente(paciente);
                break;
            case 2:
                insertarPaciente.insertarObraSocialPaciente(paciente);
                break;
            case 3:
                insertarPaciente.insertarSesion(paciente);
                break;
            case 4:
                insertarPaciente.insertarDiagnostico(paciente);
                break;
            case 5:
                insertarPaciente.insertarPlanTratamiento(paciente);
                break;
            case 6:
                insertarPaciente.insertarTipoPlan(paciente);
                break;
            case 7:
                insertarPaciente.insertarFrecuenciaPlan(paciente);
                break;
                
        }
        
        

    }

    @Override
    public List<Paciente> obtenerLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
      
    public List<String> obtenerListaTiposSesion() {
        String sqlListaTipos = "SELECT nombre FROM tipos_sesiones";
        List<String> ts = new ArrayList();

        try {
            PreparedStatement psTipo = conexion.conexion().prepareStatement(sqlListaTipos);
            ResultSet rsTipo = psTipo.executeQuery();
            while (rsTipo.next()) {
                ts.add(rsTipo.getString("nombre"));
            }
            psTipo.close();
            rsTipo.close();
            return ts;

        } catch (SQLException e) {
        }
        return null;
    }
    
    public void insertarCodigoFacturacion(CodigoFacturacion codigo) throws SQLException{
        
        String sqlCodigoFacturacion = "INSERT INTO codigos_facturaciones (id_codigo_facturacion, nombre, codigo) VALUES (0, ?, ?);";
        
        int idCodigo = obtenerIdCodigoFacturacion(codigo);
        System.out.println(idCodigo);
        
        if(idCodigo == 0){
            PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlCodigoFacturacion);
            psCodigo.setString(1, codigo.getNombre());
            psCodigo.setInt(2, codigo.getCodigo());
            psCodigo.executeUpdate();
        }
    }
    
    public void actualizarCodigoFacturacion(CodigoFacturacion codigo) throws SQLException{
        String sqlActualizarCodigoFacturacion = "UPDATE codigos_facturaciones SET nombre = ?, codigo = ? WHERE id_codigo_facturacion = ?;";
    
        System.out.println(codigo.getId());
        System.out.println(codigo.getNombre());
        System.out.println(codigo.getCodigo());
        PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlActualizarCodigoFacturacion);
        psCodigo.setString(1, codigo.getNombre());
        psCodigo.setInt(2, codigo.getCodigo());
        psCodigo.setInt(3, codigo.getId());
        psCodigo.executeUpdate();
        
    
    }
    
    public String obtenerStringCodigoFacturacion(String nombreCodigo) throws SQLException{
        String sqlCodigo = "SELECT cf.codigo  FROM codigos_facturaciones cf WHERE nombre = ?";
        PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlCodigo);
        psCodigo.setString(1, nombreCodigo);
        ResultSet rsCodigo = psCodigo.executeQuery();
        if(rsCodigo.next()){
            return rsCodigo.getString("codigo");
        }else{
            return "";
        }
    }
    
    public List<String> obtenerListaFrecuencia() {
        String sqlListaFrecuencia = "SELECT frecuencia  FROM frecuencias_sesiones";
        List<String> ts = new ArrayList();

        try {
            PreparedStatement psFrecuencia = conexion.conexion().prepareStatement(sqlListaFrecuencia);
            ResultSet rsFrecuencia = psFrecuencia.executeQuery();
            while (rsFrecuencia.next()) {
                ts.add(rsFrecuencia.getString("frecuencia"));
            }
            psFrecuencia.close();
            rsFrecuencia.close();
            return ts;

        } catch (SQLException e) {
        }
        return null;
    }
    

    public List<String> obtenerListaCodigosFacturacion() {
        String sqlListaCodigos = "SELECT nombre FROM codigos_facturaciones";
        List<String> ts = new ArrayList();

        try {
            PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlListaCodigos);
            ResultSet rsCodigos = psCodigo.executeQuery();
            while (rsCodigos.next()) {
                ts.add(rsCodigos.getString("nombre"));
            }
            psCodigo.close();
            rsCodigos.close();
            return ts;

        } catch (SQLException e) {
        }
        return null;
    }
    
    public boolean esPacienteDeUsuarioActual(Paciente pacienteParametro){
        String sqlesPacientedeUsuarioActual = "SELECT p.dni, u.es_ultima_sesion_iniciada, p.es_paciente  \n" +
                        "FROM pacientes AS p\n" +
                        "JOIN usuarios_pacientes up ON p.id_paciente = up.id_paciente\n" +
                        "JOIN usuarios u ON up.id_usuario = u.id_usuario \n" +
                        "WHERE es_paciente=true AND u.es_ultima_sesion_iniciada=1 AND p.dni=?;";
        try {
            PreparedStatement psEsPacienteDeUsuarioActual = conexion.conexion().prepareStatement(sqlesPacientedeUsuarioActual);
            psEsPacienteDeUsuarioActual.setInt(1, pacienteParametro.getDni());
            ResultSet rsEsPacienteDeUsuarioActual = psEsPacienteDeUsuarioActual.executeQuery();
            if(rsEsPacienteDeUsuarioActual.next()){
                return true;
            }
            
        } catch (Exception e) {
        }
        return false;
    }
    
    public int obtenerultimaSesion(Paciente pacienteParametro){
        String sqlUltimaSesion = "SELECT MAX(numero_sesion) AS numeroSesion FROM sesiones_pacientes WHERE id_paciente = ?;";
        try {
            int idPaciente = obtenerIdPaciente(pacienteParametro);
            
            PreparedStatement psUltimaSesion = conexion.conexion().prepareStatement(sqlUltimaSesion);
            
            if(idPaciente != 0){
                psUltimaSesion.setInt(1, idPaciente);
            }else{
                throw sqlException;
            }
            
            ResultSet rsUltimaSesion = psUltimaSesion.executeQuery();
            if(rsUltimaSesion.next()){
                return rsUltimaSesion.getInt("numeroSesion");
            }
            return 0;
        } catch (Exception e) {
        }
        return 0;
    }

    

    public boolean existeAutorizacion(Paciente pacienteParametro){
       
        return pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion() == 0 
               && pacienteParametro.getSesion().getAutorizacion().getObservacion().equals("-") 
               && pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString().equals("1700-01-01")
               && pacienteParametro.getSesion().getAutorizacion().getCopago() == 0;
    }
    
    
    
    public int obtenerIdObraSocia(Paciente pacienteParametro){
        String sqlObtenerIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre = ?;";
        
        try {
            
            //BUSCAR ID DE OBRA SOCIAL  
            PreparedStatement psBuscarIdObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
            psBuscarIdObraSocial.setString(1, pacienteParametro.getObraSocialPaciente().getNombre());
            ResultSet rsBuscarIdObraSocial= psBuscarIdObraSocial.executeQuery();
            if(rsBuscarIdObraSocial.next()){
                
                return rsBuscarIdObraSocial.getInt("id_obra_social");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
    
     
     
     
    public int obtenerIdAfiliadoObraSocial(Paciente pacienteParametro){
        String sqlObtenerIdAfiliadoObraSocial = "SELECT id_afiliado_obra_social FROM afiliados_obras_sociales WHERE numero_afiliado = ?;";
        
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdAfiliadoObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdAfiliadoObraSocial);
            psBuscarIdAfiliadoObraSocial.setInt(1, pacienteParametro.getObraSocialPaciente().getAfiliado().getNumero());
            ResultSet rsBuscarIdAfiliadoObraSocial= psBuscarIdAfiliadoObraSocial.executeQuery();
            if(rsBuscarIdAfiliadoObraSocial.next()){
                return rsBuscarIdAfiliadoObraSocial.getInt("id_afiliado_obra_social");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    
    
    
   
    
    public int obtenerIdSesion(Paciente pacienteParametro){
        String sqlObtenerIdSesion = "SELECT sp.id_sesion_paciente  \n" +
                                         "FROM sesiones_pacientes sp \n" +
                                         "WHERE sp.numero_sesion  = ? \n" +
                                         "AND sp.fecha = ? \n" +
                                         "AND sp.id_paciente = ?;";
        
         
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdSesion = conexion.conexion().prepareStatement(sqlObtenerIdSesion);
            psBuscarIdSesion.setInt(1, pacienteParametro.getSesion().getNumeroSesion());
            psBuscarIdSesion.setDate(2, Date.valueOf(pacienteParametro.getSesion().getFecha()));
            psBuscarIdSesion.setInt(3, pacienteParametro.getId());
            ResultSet rsBuscarSesion= psBuscarIdSesion.executeQuery();
            if(rsBuscarSesion.next()){
                return rsBuscarSesion.getInt("id_sesion_paciente");
            } 
            
            
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    
    public int obtenerIdTelefono(Paciente pacienteParametro){
       
        String sqlSTelefono = "SELECT id_telefono_paciente FROM telefonos_pacientes WHERE numero_telefono=?;";
        try {
            
            
            
            PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlSTelefono);
            pSTelefono.setString(1, pacienteParametro.getTelefono().getTelefono());
            ResultSet rsSTelefono = pSTelefono.executeQuery();
            
            
            if(rsSTelefono.next()){
                return rsSTelefono.getInt("id_telefono_paciente");
            }
            
           pSTelefono.close();
           rsSTelefono.close();
            
            
        } catch (Exception e) {
        }
        return 0;
    }
    
   

    

    public int obtenerIdUsuario(){
       
        String sqlIdUsuario = "SELECT id_usuario FROM usuarios u WHERE es_usuario = 1 AND es_ultima_sesion_iniciada = 1";
        try {
            PreparedStatement psIdUsuario = conexion.conexion().prepareStatement(sqlIdUsuario);
            ResultSet rsIdUsuario = psIdUsuario.executeQuery();
            
            if(rsIdUsuario.next()){
                return rsIdUsuario.getInt("id_usuario");
            }
            
           psIdUsuario.close();
           rsIdUsuario.close();
            
            
        } catch (Exception e) {
        }
        return 0;
    }

    

    

    

    

   
    

}
