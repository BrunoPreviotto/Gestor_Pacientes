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
    
    
    
    public void actualizarDiagnostico(Paciente pacienteParametro) throws SQLException{
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
    
    public void actualizarPlan(Paciente pacienteParametro) throws SQLException{
        
        //UPDATE planes
        String sqlPlanesTratamientos = "UPDATE planes_tratamientos SET estrategia=?, id_frecuencia_sesion=?, id_tipo_sesion=? WHERE id_paciente=?";
        
        
        try {
            
            int idFrecuencia = obtenerIdFrecuenciaSesion(pacienteParametro.getPlanTratamiento());
            int idTipoSesion = obtenerIdTipoSesion(pacienteParametro.getPlanTratamiento().getTipoSEsion());
            
            //Actualiza Planes
            PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlPlanesTratamientos);
            psPlanes.setString(1, pacienteParametro.getPlanTratamiento().getEstrategia());
            psPlanes.setInt(2, idFrecuencia);
            psPlanes.setInt(3, idTipoSesion);
            psPlanes.setInt(4, pacienteParametro.getId());
            psPlanes.executeQuery();
            
            psPlanes.close();
            
           
           
        } catch (SQLException e) {
            
        }
    
    }
    
    public void actualizarPaciente(Paciente pacienteParametro) throws SQLException{
        
        String sqlPaciente = "UPDATE pacientes SET edad=?, dni=?, id_nombre=?, id_telefono_paciente=?, es_paciente = true, id_honorario=? WHERE id_paciente=?;";
        
        //String sqlIdNombre = "SELECT id_nombre FROM nombres WHERE nombre = ? AND apellido = ?";
        
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";
        
        //String sqlIdTelefono = "SELECT id_telefono_paciente FROM telefonos_pacientes WHERE numero_telefono = ?";

        String sqlTelefono = "INSERT INTO telefonos_pacientes(id_telefono_paciente, numero_telefono) VALUES(?,?)";
        
        String sqlHonorario = "INSERT INTO honorarios (id_honorario, honorario) VALUES (?, ?);";
        
        try {

            int idNombre = obtenerIdNombre(pacienteParametro.getNombre(), pacienteParametro.getApellido());
            int idTelefono = obtenerIdTelefonoPaciente(pacienteParametro);
            int idHonorario = obtenerIdHonorario(pacienteParametro);
            
            PreparedStatement pst;
            
           
            //si no existe honorario lo crea
            if(idHonorario == 0){
                pst = conexion.conexion().prepareStatement(sqlHonorario);
                pst.setInt(1, 0);
                pst.setDouble(2, pacienteParametro.getHonorarios().getHonorario());
                pst.executeUpdate();
            }
            

            //si no existe nombre lo crea
            if (idNombre == 0) {
                pst = conexion.conexion().prepareStatement(sqlNombre);
                pst.setInt(1, 0);
                pst.setString(2, pacienteParametro.getNombre());
                pst.setString(3, pacienteParametro.getApellido());
                pst.executeUpdate();
            }

            
            if (idTelefono == 0) {
                pst = conexion.conexion().prepareStatement(sqlTelefono);
                pst.setInt(1, 0);
                pst.setString(2, pacienteParametro.getTelefono().getTelefono());
                pst.executeUpdate();
            }
            
           
            
            
            
             //crea el paciente
            pst = conexion.conexion().prepareStatement(sqlPaciente);
            
            pst.setInt(1, pacienteParametro.getEdad());
            pst.setInt(2, pacienteParametro.getDni());
            pst.setInt(3, obtenerIdNombre(pacienteParametro.getNombre(), pacienteParametro.getApellido()));
            pst.setInt(4, obtenerIdTelefonoPaciente(pacienteParametro));
            pst.setInt(5, obtenerIdHonorario(pacienteParametro));
            pst.setInt(6, pacienteParametro.getId());
            

            
            pst.executeUpdate();
           

            

            /*pSIdNombre.close();
            pSIdTelefono.close();
            rsIdTelefono.close();
            rsIdTelefono.close();*/
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarSesion(Paciente pacienteParametro) throws SQLException{
        
        
        String sqlActualizarSesion = "UPDATE sesiones_pacientes SET\n" +
                                    "fecha = ?, \n" +
                                    "trabajo_sesion = ?, \n" +
                                    "observacion = ?, \n" +
                                    "honorarios_por_sesion = ?,\n" +
                                    "id_estado_facturacion = ?\n" +
                                    "WHERE id_sesion_paciente = ? AND id_paciente=?;";
        
        String sqlActualizarAutorizacion = "UPDATE autorizaciones SET \n" +
                                           "numero_autorizacion = ?,\n" +
                                           "observacion = ?,\n" +
                                           "asociacion = ?,\n" +
                                           "copago = ?,\n" +
                                           "id_codigo_facturacion = ?\n" +
                                           "WHERE id_autorizacion = ?";
        
        String sqlEstadoFacturacion = "INSERT INTO estados_facturacion (id_estado_facturacion, estado) VALUES (0, ?);";
        
        try {
            int idEstadoFacturacion = obtenerIdEstadoFacturacion(pacienteParametro);
            int idCodigoFacturacion = obtenerIdCodigoFacturacion(pacienteParametro.getSesion().getAutorizacion().getCodigoFacturacion());
            
             if(idEstadoFacturacion == 0){
                PreparedStatement pstEstado = conexion.conexion().prepareStatement(sqlEstadoFacturacion);
                pstEstado.setString(1, pacienteParametro.getSesion().getEstado().getEstado());
                pstEstado.executeUpdate();
            }
            
            
            //ACTUALIZAR SESION
            PreparedStatement psActualizarSesion= conexion.conexion().prepareStatement(sqlActualizarSesion);
            psActualizarSesion.setDate(1, Date.valueOf(pacienteParametro.getSesion().getFecha().toString()));
            psActualizarSesion.setString(2, pacienteParametro.getSesion().getTrabajoSesion());
            psActualizarSesion.setString(3, pacienteParametro.getSesion().getObservacion());
            psActualizarSesion.setDouble(4, pacienteParametro.getSesion().getHonorarioPorSesion());
            psActualizarSesion.setInt(5, obtenerIdEstadoFacturacion(pacienteParametro));
            psActualizarSesion.setInt(6, pacienteParametro.getSesion().getIdSesion());
            psActualizarSesion.setInt(6, pacienteParametro.getId());
            
            psActualizarSesion.executeQuery();
            
            //ACTUALIZAR AUTORIZACION
            if(!existeAutorizacion(pacienteParametro)){
                PreparedStatement psActualizarAutorizacion = conexion.conexion().prepareStatement(sqlActualizarAutorizacion);
                psActualizarAutorizacion.setInt(1, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
                psActualizarAutorizacion.setString(2, pacienteParametro.getSesion().getAutorizacion().getObservacion());
                psActualizarAutorizacion.setDate(3, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString()));
                psActualizarAutorizacion.setDouble(4, pacienteParametro.getSesion().getAutorizacion().getCopago());
                psActualizarAutorizacion.setInt(5, idCodigoFacturacion);
                psActualizarAutorizacion.setInt(6, pacienteParametro.getSesion().getAutorizacion().getId());
                psActualizarAutorizacion.executeUpdate();
            }
            
        } catch (Exception e) {
        }
        
        
    }
    
    public void actualizarObraSocialPaciente (Paciente pacienteParametro) throws SQLException{
        Paciente pacienteActualizado = new Paciente();
        
        String sqlActualizarObraSocialPaciente = "UPDATE afiliados_obras_sociales \n" +
                                                 "SET id_obra_social = ?, id_plan_obra_social = ?, numero_afiliado = ?\n" +
                                                 "WHERE id_obra_social = ? AND id_plan_obra_social = ? AND id_afiliado_obra_social = ? AND id_paciente = ?";
        
        try {
            
            //ACTUALIZAR OBRA SOCIAL PACIENTE
            PreparedStatement psBuscarIdCodigo= conexion.conexion().prepareStatement(sqlActualizarObraSocialPaciente);
            
            
            psBuscarIdCodigo.setInt(1, obtenerIdObraSocia(pacienteParametro));
            
            
            psBuscarIdCodigo.setInt(2, obtenerIdPlanObraSocial(pacienteParametro));
            
             
            psBuscarIdCodigo.setInt(3, pacienteParametro.getObraSocialPaciente().getAfiliado().getNumero());
            
            
            psBuscarIdCodigo.setInt(4, pacienteParametro.getObraSocialPaciente().getId());
            
            
            psBuscarIdCodigo.setInt(5, pacienteParametro.getObraSocialPaciente().getPlan().getId());
            
            
            psBuscarIdCodigo.setInt(6, pacienteParametro.getObraSocialPaciente().getAfiliado().getId());
            
            psBuscarIdCodigo.setInt(7, pacienteParametro.getId());
            
            
            
//psBuscarIdCodigo.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
