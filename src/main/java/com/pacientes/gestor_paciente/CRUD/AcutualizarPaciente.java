/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_paciente.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author previotto
 */
public class AcutualizarPaciente extends PacienteDAOImplementacion {
    
    
    
    public void actualizarDiagnostico(Paciente pacienteParametro){
        String sqlDiagnostico = "UPDATE diagnosticos SET diagnostico=?, observacion=? WHERE id_paciente=?";
        
        try {
            
            PreparedStatement psDiagnostico = conexion.conexion().prepareStatement(sqlDiagnostico);
            psDiagnostico.setString(1, pacienteParametro.getDiagnostico().getDiagnostico());
            psDiagnostico.setString(2, pacienteParametro.getDiagnostico().getObservacion());
            psDiagnostico.setInt(3, pacienteParametro.getId());
            psDiagnostico.executeQuery();
            
            
        } catch (Exception e) {
        }

           
    }
    
    public void actualizarPlan(Paciente pacienteParametro) {
        
        //UPDATE planes
        String sqlPlanesTratamientos = "UPDATE planes_tratamientos SET estrategia=?, id_frecuencia_sesion=?, id_tipo_sesion=? WHERE id_paciente=?";
        
        
        
        //UPDATE frecuencia
        String chequearFrecuencia = "SELECT frecuencia FROM frecuencias_sesiones WHERE frecuencia LIKE ?";
        
        String insertFrecuencia = "INSERT INTO frecuencias_sesiones (id_frecuencia_sesion, frecuencia) VALUES (?, ?)";
        
        String buscarIdFrecuencia = "SELECT id_frecuencia_sesion FROM frecuencias_sesiones WHERE frecuencia = ?";
        
        //UPDATE tipo sesion
        String chequearTipoSesion = "SELECT nombre FROM tipos_sesiones WHERE nombre LIKE ?";
        
        String insertSesion = "INSERT INTO tipos_sesiones (id_tipo_sesion, id_tipo_sesion, id_tipo_sesion) VALUES (?, ?, ?)";
        
        String buscarIdSesion = "SELECT id_tipo_sesion FROM tipos_sesiones WHERE nombre = ?";
        
        
        try {
            
            
            
            /*PreparedStatement pSidFrecuencia = conexion.conexion().prepareStatement(sqlIdFrecuencia);
            pSidFrecuencia.setString(1, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
            ResultSet rsSidIdFrecuencia = pSidFrecuencia.executeQuery();*/

           
           
            
            

            //chequear si la frecuencia existe
            PreparedStatement psCheckFrecuencia = conexion.conexion().prepareStatement(chequearFrecuencia);
            psCheckFrecuencia.setString(1, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
            ResultSet rsCheckFrecuencia = psCheckFrecuencia.executeQuery();
            
            //Actualiza frecuencia
            PreparedStatement psIdFrecuencia;
            ResultSet rsIdFrecuencia;
            
            if(rsCheckFrecuencia.next()){
                psIdFrecuencia = conexion.conexion().prepareStatement(buscarIdFrecuencia);
                psIdFrecuencia.setString(1, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
                rsIdFrecuencia = psIdFrecuencia.executeQuery();
            }else{
                PreparedStatement psInsertarFrecuencia = conexion.conexion().prepareStatement(insertFrecuencia);
                psInsertarFrecuencia.setInt(1, 0);
                psInsertarFrecuencia.setString(2, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
                psInsertarFrecuencia.executeQuery();
                psIdFrecuencia = conexion.conexion().prepareStatement(buscarIdFrecuencia);
                psIdFrecuencia.setString(1, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
                rsIdFrecuencia = psIdFrecuencia.executeQuery();
                psInsertarFrecuencia.close();
            }
            
            
            //chequear si la tipo sesion existe
            PreparedStatement psCheckTipoSesion = conexion.conexion().prepareStatement(chequearTipoSesion);
            psCheckTipoSesion.setString(1, pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre());
            ResultSet rsCheckTipoSesion = psCheckTipoSesion.executeQuery();
            
            //Actualiza tipo sesion
            PreparedStatement psIdTipoSesion;
            ResultSet rsIdTipoSesion;
            
            if(rsCheckTipoSesion.next()){
                psIdTipoSesion = conexion.conexion().prepareStatement(buscarIdSesion);
                psIdTipoSesion.setString(1, pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre());
                rsIdTipoSesion = psIdTipoSesion.executeQuery();
            }else{
                PreparedStatement psInsertarSesion = conexion.conexion().prepareStatement(insertSesion);
                psInsertarSesion.setInt(1, 0);
                psInsertarSesion.setString(2, pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre());
                psInsertarSesion.executeQuery();
                psIdTipoSesion = conexion.conexion().prepareStatement(buscarIdSesion);
                psIdTipoSesion.setString(1, pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre());
                rsIdTipoSesion = psIdTipoSesion.executeQuery();
                psInsertarSesion.close();
            }
            
            
            //Actualiza Planes
            PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlPlanesTratamientos);
            psPlanes.setString(1, pacienteParametro.getPlanTratamiento().getEstrategia());
            if(rsIdFrecuencia.next()){
                psPlanes.setInt(2, rsIdFrecuencia.getInt("id_frecuencia_sesion"));
            }
            
            if(rsIdTipoSesion.next()){
                psPlanes.setInt(3, rsIdTipoSesion.getInt("id_tipo_sesion"));
            }
            
            psPlanes.setInt(4, pacienteParametro.getId());
            psPlanes.executeQuery();
            
            
             
            psIdTipoSesion.close();
            
            psCheckTipoSesion.close();

            psCheckFrecuencia.close();
            
            psIdTipoSesion.close();

            psPlanes.close();
            
            psIdFrecuencia.close();
            
            rsIdFrecuencia.close();
            
            rsCheckFrecuencia.close();

            rsCheckTipoSesion.close();
            
            rsIdTipoSesion.close();
           
        } catch (SQLException e) {
            
        }
    
    }
    
    public void actualizarPaciente(Paciente pacienteParametro) {
        
        String sqlPaciente = "UPDATE pacientes SET edad=?, dni=?, id_nombre=?, id_telefono_paciente=? WHERE id_paciente=?";
        
        String sqlIdNombre = "SELECT id_nombre FROM nombres WHERE nombre = ? AND apellido = ?";
        
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";
        
        String sqlIdTelefono = "SELECT id_telefono_paciente FROM telefonos_pacientes WHERE numero_telefono = ?";

        String sqlTelefono = "INSERT INTO telefonos_pacientes(id_telefono_paciente, numero_telefono) VALUES(?,?)";
        
        try {

            PreparedStatement pst;
            //selecciona id nombre si el nombre existe
            
            PreparedStatement pSIdNombre = conexion.conexion().prepareStatement(sqlIdNombre);
            pSIdNombre.setString(1, pacienteParametro.getNombre());
            pSIdNombre.setString(2, pacienteParametro.getApellido());
            ResultSet rsIdNombre = pSIdNombre.executeQuery();
           

            //si no existe lo crea
            if (!rsIdNombre.next()) {
                pst = conexion.conexion().prepareStatement(sqlNombre);
                pst.setInt(1, 0);
                pst.setString(2, pacienteParametro.getNombre());
                pst.setString(3, pacienteParametro.getApellido());
                pst.executeUpdate();
            }

            //
            
            pSIdNombre = conexion.conexion().prepareStatement(sqlIdNombre);
            pSIdNombre.setString(1, pacienteParametro.getNombre());
            pSIdNombre.setString(2, pacienteParametro.getApellido());
            rsIdNombre = pSIdNombre.executeQuery();

           
            PreparedStatement pSIdTelefono = conexion.conexion().prepareStatement(sqlIdTelefono);
            pSIdTelefono.setString(1, pacienteParametro.getTelefono().getTelefono());
            ResultSet rsIdTelefono = pSIdTelefono.executeQuery();
            
            if (!rsIdTelefono.next()) {
                pst = conexion.conexion().prepareStatement(sqlTelefono);
                pst.setInt(1, 0);
                pst.setString(2, pacienteParametro.getTelefono().getTelefono());
                pst.executeUpdate();
            }
            
            
            
            pSIdTelefono = conexion.conexion().prepareStatement(sqlIdTelefono);
            pSIdTelefono.setString(1, pacienteParametro.getTelefono().getTelefono());
            rsIdTelefono = pSIdTelefono.executeQuery();
            
            
             //crea el paciente
            pst = conexion.conexion().prepareStatement(sqlPaciente);
            
            pst.setInt(1, pacienteParametro.getEdad());
            pst.setInt(2, pacienteParametro.getDni());
            
            
            
            if (rsIdNombre.next()) {
                pst.setInt(3, rsIdNombre.getInt("id_nombre"));
                
            }
            
            
            
            if(rsIdTelefono.next()){
                pst.setInt(4, rsIdTelefono.getInt("id_telefono_paciente"));
               
            }
            
            pst.setInt(5, pacienteParametro.getId());

            
            pst.executeUpdate();
           

            

            pSIdNombre.close();
            pSIdTelefono.close();
            rsIdTelefono.close();
            rsIdTelefono.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarSesion(Paciente pacienteParametro){
        
        String buscarCodigo = "SELECT id_codigo_facturacion \n" +
                              "FROM codigos_facturaciones \n" +
                              "WHERE nombre = ? ";
        
   
        
        String sqlActualizarSesion = "UPDATE sesiones_pacientes SET \n" +
                                     "fecha = ?, \n" +
                                     "trabajo_sesion = ?,\n" +
                                     "observacion = ?,\n" +
                                     "motivo_trabajo_emergente = ?\n" +
                                     "WHERE id_sesion_paciente = ?;";
        
        String sqlActualizarAutorizacion = "UPDATE autorizaciones SET \n" +
                                           "numero_autorizacion = ?,\n" +
                                           "observacion = ?,\n" +
                                           "asociacion = ?,\n" +
                                           "copago = ?,\n" +
                                           "id_codigo_facturacion = ?\n" +
                                           "WHERE id_autorizacion = ?";
        
        try {
            
            //OBTENER ID CODIGO FACTURACION
            PreparedStatement psBuscarIdCodigo= conexion.conexion().prepareStatement(buscarCodigo);
            psBuscarIdCodigo.setString(1, pacienteParametro.getSesion().getAutorizacion().getCodigoFacturacion().getNombre());
            ResultSet rsIdCodigo = psBuscarIdCodigo.executeQuery();
            
            //ACTUALIZAR SESION
            PreparedStatement psActualizarSesion= conexion.conexion().prepareStatement(sqlActualizarSesion);
            psActualizarSesion.setDate(1, Date.valueOf(pacienteParametro.getSesion().getFecha().toString()));
            psActualizarSesion.setString(2, pacienteParametro.getSesion().getTrabajoSesion());
            psActualizarSesion.setString(3, pacienteParametro.getSesion().getObservacion());
            psActualizarSesion.setString(4, pacienteParametro.getSesion().getMotivoTrabajoEmergente());
            psActualizarSesion.setInt(5, pacienteParametro.getSesion().getIdSesion());
            
            psActualizarSesion.executeQuery();
            
            //ACTUALIZAR AUTORIZACION
            if(!existeAutorizacion(pacienteParametro)){
                PreparedStatement psActualizarAutorizacion = conexion.conexion().prepareStatement(sqlActualizarAutorizacion);
                psActualizarAutorizacion.setInt(1, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
                psActualizarAutorizacion.setString(2, pacienteParametro.getSesion().getAutorizacion().getObservacion());
                psActualizarAutorizacion.setDate(3, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString()));
                psActualizarAutorizacion.setDouble(4, pacienteParametro.getSesion().getAutorizacion().getCopago());
                if(rsIdCodigo.next()){
                    psActualizarAutorizacion.setInt(5, rsIdCodigo.getInt("id_codigo_facturacion"));
                }
                psActualizarAutorizacion.setInt(6, pacienteParametro.getSesion().getAutorizacion().getId());
                psActualizarAutorizacion.executeUpdate();
            }
            
        } catch (Exception e) {
        }
        
        
    }
    
    
    
}
