/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_paciente.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.ObraSocialPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.SesionPaciente;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import com.pacientes.gestor_pacientes.utilidades.Exepciones;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author previotto
 */
public class InsertarPaciente extends PacienteDAOImplementacion {
    
    
    
    public void insertarPaciente(Paciente pacienteParametro) throws Exception {

        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";

        String sqlPaciente = "INSERT INTO pacientes(id_paciente, edad, dni, es_paciente, id_nombre, id_honorario, id_telefono_paciente) VALUES(?, ?, ?, ?, ?, ?, ?)";

        String sqlTelefono = "INSERT INTO telefonos_pacientes(id_telefono_paciente, numero_telefono) VALUES(?,?)";

        String sqlHonorario = "INSERT INTO honorarios (id_honorario, honorario) VALUES (?, ?);";

        String sqlAsociarPacienteConUsuario = "INSERT INTO usuarios_pacientes (id_usuario, id_paciente) VALUES (?, ?)";

        if (ExisteMasDeUnPacientePOrUsuario(pacienteParametro.getDni()) > 0) {
            Exepciones exepcionPacienteExiste = new Exepciones(111);
            throw exepcionPacienteExiste;
        } else {
            try {
                PreparedStatement pst;

                int idPaciente = obtenerIdPacienteConTodosLosDatos(pacienteParametro);
                int idNombre = obtenerIdNombre(pacienteParametro.getNombre(), pacienteParametro.getApellido());
                int idTelefono = obtenerIdTelefonoPaciente(pacienteParametro);
                int idHonorario = obtenerIdHonorario(pacienteParametro);

                //si no existe honorario lo crea
                if (idHonorario == 0) {
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

                //si no existe telefono lo crea
                if (idTelefono == 0) {
                    pst = conexion.conexion().prepareStatement(sqlTelefono);
                    pst.setInt(1, 0);
                    pst.setString(2, pacienteParametro.getTelefono().getTelefono());
                    pst.executeUpdate();
                }

                //crea el paciente
                if (idPaciente == 0) {
                    pst = conexion.conexion().prepareStatement(sqlPaciente);
                    pst.setInt(1, 0);
                    pst.setInt(2, pacienteParametro.getEdad());
                    pst.setInt(3, pacienteParametro.getDni());
                    pst.setBoolean(4, true);
                    pst.setInt(5, obtenerIdNombre(pacienteParametro.getNombre(), pacienteParametro.getApellido()));
                    pst.setInt(6, obtenerIdHonorario(pacienteParametro));
                    pst.setInt(7, obtenerIdTelefonoPaciente(pacienteParametro));
                    pst.executeUpdate();
                } else {
                    actualizar(pacienteParametro, 3);
                }
                
                //ASOCIAR PACIENTE CON USUARIO
                //crea el paciente
                pst = conexion.conexion().prepareStatement(sqlAsociarPacienteConUsuario);
                pst.setInt(1, VariablesEstaticas.usuario.getId());
                pst.setInt(2, obtenerIdPacienteConTodosLosDatos(pacienteParametro));
                pst.executeUpdate();

                pst.close();
            } catch (Exception e) {
                Exepciones exepcioSql = new Exepciones(222);
                throw exepcioSql;
            }

        }

    }
    
    
    public void insertarObraSocialPaciente(Paciente pacienteParametro) throws Exception {
        String sqlAfiliado = "INSERT INTO afiliados_obras_sociales (id_afiliado_obra_social, numero_afiliado, id_paciente, id_obra_social, id_plan_obra_social) VALUES (?,?,?,?,?)";
        //String sqlIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre=?";
        //String sqlExistePlan = "SELECT nombre FROM planes_obras_sociales WHERE nombre=?";
        //String sqlPlanes = "INSERT INTO planes_obras_sociales (id_plan_obra_social, nombre, descripcion) VALUES (?,?,?)";

        //String sqlExistePlanObraSocial = "SELECT id_obra_social, id_plan_obra_social FROM obras_sociales_planes_obras_sociales WHERE id_obra_social=? AND id_plan_obra_social=?";
        String sqlObrasSocialesPlanesObrasSociales = "INSERT INTO obras_sociales_planes_obras_sociales (id_obra_social, id_plan_obra_social) VALUES (?,?)";

        
        int idPlan = obtenerIdPlanObraSocial(pacienteParametro);
        int idObraSocia = obtenerIdObraSocia(pacienteParametro);
            
        //CREAR NUEVO AFILIADO
        PreparedStatement psAfiliado = conexion.conexion().prepareStatement(sqlAfiliado);
        psAfiliado.setInt(1, 0);
        psAfiliado.setInt(2, pacienteParametro.getObraSocialPaciente().getAfiliado().getNumero());
        psAfiliado.setInt(3, pacienteParametro.getId());
        psAfiliado.setInt(4, idObraSocia);

        psAfiliado.setInt(5, idPlan);

        psAfiliado.executeQuery();
                
    }
    
    public void insertarSesion(Paciente pacienteParametro) throws SQLException{
        
        String sqlSesion = "INSERT INTO sesiones_pacientes (id_sesion_paciente, fecha, trabajo_sesion, observacion, honorarios_por_sesion, id_paciente, numero_sesion, id_estado_facturacion) VALUES(?,?,?,?,?,?,?,?)";
        
        String sqlAutorizacion = "INSERT INTO autorizaciones  (id_autorizacion, numero_autorizacion, observacion, asociacion, copago, id_codigo_facturacion) VALUES(?,?,?,?,?,?)";

        String sqlSesionAutorizacion = "INSERT INTO sesiones_pacientes_autorizaciones (id_sesion_paciente, id_autorizacion) VALUES (?, ?)";
        
        String sqlEstadoFacturacion = "INSERT INTO estados_facturacion (id_estado_facturacion, estado) VALUES (0, ?);";
        
        try {
            
            int idEstadoFacturacion = obtenerIdEstadoFacturacion(pacienteParametro);
            
            int idAutorizacion = obtenerIdAutorizacion(pacienteParametro);
            int idCodigoFacturacion = obtenerIdCodigoFacturacion(pacienteParametro.getSesion().getAutorizacion().getCodigoFacturacion());
            
            System.out.println(idCodigoFacturacion);
            
            if(idEstadoFacturacion == 0){
                PreparedStatement pstEstado = conexion.conexion().prepareStatement(sqlEstadoFacturacion);
                pstEstado.setString(1, pacienteParametro.getSesion().getEstado().getEstado());
                pstEstado.executeUpdate();
            }
            
            //crea sesion
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlSesion);
            pst.setInt(1, 0);
            pst.setDate(2, Date.valueOf(pacienteParametro.getSesion().getFecha().toString()));
            pst.setString(3, pacienteParametro.getSesion().getTrabajoSesion());
            pst.setString(4, pacienteParametro.getSesion().getObservacion());
            pst.setDouble(5, pacienteParametro.getSesion().getHonorarioPorSesion());
            pst.setInt(6, pacienteParametro.getId());
            pst.setInt(7, pacienteParametro.getSesion().getNumeroSesion());
            pst.setInt(8, obtenerIdEstadoFacturacion(pacienteParametro));
            pst.executeUpdate();
            
            int idSesion = obtenerIdSesion(pacienteParametro);

            //crea sesion
            if (idAutorizacion == 0) {
                
                
                //INSERTAR AUTORIZACION
                PreparedStatement pstA = conexion.conexion().prepareStatement(sqlAutorizacion);
                pstA.setInt(1, 0);
                pstA.setInt(2, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
                pstA.setString(3, pacienteParametro.getSesion().getAutorizacion().getObservacion());
                pstA.setDate(4, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString()));
                pstA.setDouble(5, pacienteParametro.getSesion().getAutorizacion().getCopago());
                pstA.setInt(6, idCodigoFacturacion);
                pstA.executeUpdate();

                idAutorizacion = obtenerIdAutorizacion(pacienteParametro);
                
               //ASOCIAR AUTORIZACION CON SESION
                PreparedStatement psSesionAutorizacion = conexion.conexion().prepareStatement(sqlSesionAutorizacion);
                psSesionAutorizacion.setInt(1, idSesion);
                psSesionAutorizacion.setInt(2, idAutorizacion);
                
                psSesionAutorizacion.executeUpdate();
                
                pstA.close();
                
                
            }else{
                
                //ASOCIAR AUTORIZACION VACIA CON SESION
                PreparedStatement psSesionAutorizacion = conexion.conexion().prepareStatement(sqlSesionAutorizacion);
                psSesionAutorizacion.setInt(1, idSesion);
                psSesionAutorizacion.setInt(2, 5);
                psSesionAutorizacion.executeUpdate();
            }
            
           
            
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void insertarDiagnostico(Paciente paciente) throws SQLException{
        String sqlDiagnostico = "INSERT INTO diagnosticos (id_diagnostico, diagnostico, observacion, id_paciente) VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlDiagnostico);
            pst.setInt(1, 0);
            pst.setString(2, paciente.getDiagnostico().getDiagnostico());
            pst.setString(3, paciente.getDiagnostico().getObservacion());
            pst.setInt(4, paciente.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertarPlanTratamiento(Paciente pacienteParametro) throws SQLException{
        String sqlPlanTratamiento = "INSERT INTO planes_tratamientos (id_plan_tratamiento, estrategia, id_frecuencia_sesion, id_paciente, id_tipo_sesion) VALUES (?,?,?,?,?)";
        
        
        
        try {
            
            int idFrecuencia = obtenerIdFrecuenciaSesion(pacienteParametro.getPlanTratamiento());
            
            
            
            int idTipoSesion = obtenerIdTipoSesion(pacienteParametro.getPlanTratamiento().getTipoSEsion());

            //INSERTAR PLAN PACIENTE
            PreparedStatement pstPt = conexion.conexion().prepareStatement(sqlPlanTratamiento);
            pstPt.setInt(1, 0);
            pstPt.setString(2, pacienteParametro.getPlanTratamiento().getEstrategia());

            pstPt.setInt(3, idFrecuencia);
            

            pstPt.setInt(4, pacienteParametro.getId());

            pstPt.setInt(5, idTipoSesion);
            
            pstPt.executeUpdate();

            
            pstPt.close();
            
           
           

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public void insertarFrecuenciaPlan(Paciente pacienteParametro){
        String sqlFrecuenciaSesion = "INSERT INTO frecuencias_sesiones (id_frecuencia_sesion, frecuencia) VALUES (?,?)";
        
        try {
            int idFrecuencia = obtenerIdFrecuenciaSesion(pacienteParametro.getPlanTratamiento());
            
            //INSERTAR FRECUENCIA SI NO EXISTE
            PreparedStatement pstFs = conexion.conexion().prepareStatement(sqlFrecuenciaSesion);
            pstFs.setInt(1, 0);
            pstFs.setString(2, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
            if(idFrecuencia == 0){
                pstFs.executeUpdate();
            }
            
            pstFs.close();
        } catch (Exception e) {
        }
        
    }
    
    
    public void insertarTipoPlan(Paciente pacienteParametro){
        String sqlTipoSesion = "INSERT INTO tipos_sesiones (id_tipo_sesion, nombre, descripcion) VALUES (?,?,?)";
        
        try {
            int idTipoSesion = obtenerIdTipoSesion(pacienteParametro.getPlanTratamiento().getTipoSEsion());

            //INSERTAR SESION SI NO EXISTE
            PreparedStatement pstTs = conexion.conexion().prepareStatement(sqlTipoSesion);
            pstTs.setInt(1, 0);
            pstTs.setString(2, pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre());
            pstTs.setString(3, pacienteParametro.getPlanTratamiento().getTipoSEsion().getDecripcion());
            if(idTipoSesion == 0){
                pstTs.executeUpdate();
            }
            
            pstTs.close();
        } catch (Exception e) {
        }
        
    }
    
    
    
}
