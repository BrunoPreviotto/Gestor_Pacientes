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
    
    
    
    public void insertarPaciente(Paciente pacienteParametro){
        
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";

        String sqlPaciente = "INSERT INTO pacientes(id_paciente, edad, dni, es_paciente, id_nombre, id_honorario, id_telefono_paciente) VALUES(?, ?, ?, ?, ?, ?, ?)";

        String sqlTelefono = "INSERT INTO telefonos_pacientes(id_telefono, numero_telefono) VALUES(?,?)";
        try {

            PreparedStatement pst;
            //selecciona id nombre si el nombre existe
            String sqlSNombre = "SELECT id_nombre FROM nombres WHERE nombre=? AND apellido=?;";
            PreparedStatement pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            pSNombre.setString(1, pacienteParametro.getNombre());
            pSNombre.setString(2, pacienteParametro.getApellido());
            ResultSet rsSNombre = pSNombre.executeQuery();

            //si no existe lo crea
            if (!rsSNombre.next()) {
                pst = conexion.conexion().prepareStatement(sqlNombre);
                pst.setInt(1, 0);
                pst.setString(2, pacienteParametro.getNombre());
                pst.setString(3, pacienteParametro.getApellido());
                pst.executeUpdate();
            }

            //
            sqlSNombre = "SELECT id_nombre FROM nombres WHERE nombre=? AND apellido=?;";
            pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            pSNombre.setString(1, pacienteParametro.getNombre());
            pSNombre.setString(2, pacienteParametro.getApellido());
            rsSNombre = pSNombre.executeQuery();

           

            String sqlSTelefono = "SELECT id_telefono FROM telefonos_pacientes WHERE numero_telefono=?;";
            PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlSTelefono);
            pSTelefono.setString(1, pacienteParametro.getTelefono().getTelefono());
            ResultSet rsSTelefono = pSTelefono.executeQuery();
            
            if (!rsSTelefono.next()) {
                pst = conexion.conexion().prepareStatement(sqlTelefono);
                pst.setInt(1, 0);
                pst.setString(2, pacienteParametro.getTelefono().getTelefono());
                pst.executeUpdate();
            }
            
            
            pSTelefono = conexion.conexion().prepareStatement(sqlSTelefono);
            pSTelefono.setString(1, pacienteParametro.getTelefono().getTelefono());
            rsSTelefono = pSTelefono.executeQuery();
            
            
             //crea el paciente
            pst = conexion.conexion().prepareStatement(sqlPaciente);
            pst.setInt(1, 0);
            pst.setInt(2, pacienteParametro.getEdad());
            pst.setInt(3, pacienteParametro.getDni());
            pst.setBoolean(4, true);
            
            
            if (rsSNombre.next()) {
                pst.setInt(5, rsSNombre.getInt("id_nombre"));
            }
            
            pst.setInt(6, 1);
            
            if(rsSTelefono.next()){
                pst.setInt(7, rsSTelefono.getInt("id_telefono"));
            }

            
            pst.executeUpdate();
           

            

            pSNombre.close();
            
            pSTelefono.close();

            rsSTelefono.close();
            rsSNombre.close();
            

            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void insertarObraSocialPaciente(Paciente pacienteParametro) {
        String sqlAfiliado = "INSERT INTO afiliados_obras_sociales (id_afiliado_obra_social, numero_afiliado, id_paciente, id_obra_social, id_plan_obra_social) VALUES (?,?,?,?,?)";
        String sqlIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre=?";
        //String sqlExistePlan = "SELECT nombre FROM planes_obras_sociales WHERE nombre=?";
        //String sqlPlanes = "INSERT INTO planes_obras_sociales (id_plan_obra_social, nombre, descripcion) VALUES (?,?,?)";
        String sqlIdPlan = "SELECT id_plan_obra_social FROM planes_obras_sociales WHERE nombre=?";
        String sqlExistePlanObraSocial = "SELECT id_obra_social, id_plan_obra_social FROM obras_sociales_planes_obras_sociales WHERE id_obra_social=? AND id_plan_obra_social=?";
        String sqlObrasSocialesPlanesObrasSociales = "INSERT INTO obras_sociales_planes_obras_sociales (id_obra_social, id_plan_obra_social) VALUES (?,?)";

        try {
            
            //BUSCAR ID OBRA SOCIAL
            PreparedStatement pSidObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
            pSidObraSocial.setString(1, pacienteParametro.getObraSocialPaciente().getNombre());
            ResultSet rsObraSocial = pSidObraSocial.executeQuery();
            
           

            /*PreparedStatement pSexistePlan = conexion.conexion().prepareStatement(sqlExistePlan);
            pSexistePlan.setString(1, paciente.getObraSocialPaciente().getPlan().getNombre());
            ResultSet rsExistePlan = pSexistePlan.executeQuery();

            if (!rsExistePlan.next()) {
                PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlPlanes);
                psPlanes.setInt(1, 0);
                psPlanes.setString(2, paciente.getObraSocialPaciente().getPlan().getNombre());
                psPlanes.setString(3, paciente.getObraSocialPaciente().getPlan().getDescripcion());
                psPlanes.executeUpdate();
            }*/
            
            //BUSCA ID PLAN
            PreparedStatement pSIdPlan = conexion.conexion().prepareStatement(sqlIdPlan);
            System.out.println(pacienteParametro.getObraSocialPaciente().getPlan().getNombre());
            pSIdPlan.setString(1, pacienteParametro.getObraSocialPaciente().getPlan().getNombre());
            ResultSet rsIdPlan = pSIdPlan.executeQuery();

            PreparedStatement pSExisteObraSocialPlan = conexion.conexion().prepareStatement(sqlExistePlanObraSocial);
            if(rsObraSocial.next()){
               pSExisteObraSocialPlan.setInt(1, rsObraSocial.getInt("id_obra_social")); 
            }
            
            if(rsIdPlan.next()){
                pSExisteObraSocialPlan.setInt(2, rsIdPlan.getInt("id_plan_obra_social"));
            }
            
            ResultSet rsExisteObraSocialPlan = pSExisteObraSocialPlan.executeQuery();
            
            //BUSCAR ID OBRA SOCIAL
            pSidObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
            pSidObraSocial.setString(1, pacienteParametro.getObraSocialPaciente().getNombre());
            rsObraSocial = pSidObraSocial.executeQuery();
            
            
            
            //CREA PLAN PARA EL USUARIO
            if (!rsExisteObraSocialPlan.next()) {
                PreparedStatement psObraSocialPlan = conexion.conexion().prepareStatement(sqlObrasSocialesPlanesObrasSociales);
                if(rsObraSocial.next()){
                    psObraSocialPlan.setInt(1, rsObraSocial.getInt("id_obra_social"));
                }
                psObraSocialPlan.setInt(2, rsIdPlan.getInt("id_plan_obra_social"));
                psObraSocialPlan.executeUpdate();
            }
            
            
            //BUSCA ID PLAN
            pSIdPlan = conexion.conexion().prepareStatement(sqlIdPlan);
            pSIdPlan.setString(1, pacienteParametro.getObraSocialPaciente().getPlan().getNombre());
            rsIdPlan = pSIdPlan.executeQuery();

            
            //BUSCAR ID OBRA SOCIAL
            pSidObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
            pSidObraSocial.setString(1, pacienteParametro.getObraSocialPaciente().getNombre());
            rsObraSocial = pSidObraSocial.executeQuery();
            
            
             //CREAR NUEVO AFILIADO
            PreparedStatement psAfiliado = conexion.conexion().prepareStatement(sqlAfiliado);
            psAfiliado.setInt(1, 0);
            psAfiliado.setInt(2, pacienteParametro.getObraSocialPaciente().getNumeroAfiliado());
            psAfiliado.setInt(3, pacienteParametro.getId());
            if (rsObraSocial.next()) {
                
                psAfiliado.setInt(4, rsObraSocial.getInt("id_obra_social"));
            }
            if(rsIdPlan.next()){
                psAfiliado.setInt(5, rsIdPlan.getInt("id_plan_obra_social"));
            }
            psAfiliado.executeUpdate();

            pSIdPlan.close();
            //pSexistePlan.close();
            pSidObraSocial.close();
            psAfiliado.close();
            rsExisteObraSocialPlan.close();
            //rsExistePlan.close();
            rsIdPlan.close();
            rsObraSocial.close();

        } catch (SQLException e) {
        }
    }
    
    public void insertarSesion(Paciente pacienteParametro) {
        
        String sqlSesion = "INSERT INTO sesiones_pacientes (id_sesion_paciente, fecha, trabajo_sesion, observacion, motivo_trabajo_emergente, id_paciente, numero_sesion) VALUES(?,?,?,?,?,?,?)";
        
        String sqlAutorizacion = "INSERT INTO autorizaciones  (id_autorizacion, numero_autorizacion, observacion, asociacion, copago, id_codigo_facturacion) VALUES(?,?,?,?,?,?)";

        String sqlIdSesion = "SELECT id_sesion_paciente FROM sesiones_pacientes WHERE fecha=? AND id_paciente=? AND numero_sesion=?";

        String sqlIdAutorizacion = "SELECT id_autorizacion FROM autorizaciones WHERE numero_autorizacion=? AND asociacion=?";
        
        String sqlSesionAutorizacion = "INSERT INTO sesiones_pacientes_autorizaciones (id_sesion_paciente, id_autorizacion) VALUES (?, ?)";
        
        String sqlIdCodFacturacion = "SELECT id_codigo_facturacion FROM codigos_facturaciones WHERE nombre =?";
        try {

            //crea el sesion
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlSesion);
            pst.setInt(1, 0);
            pst.setDate(2, Date.valueOf(pacienteParametro.getSesion().getFecha().toString()));
            pst.setString(3, pacienteParametro.getSesion().getTrabajoSesion());
            pst.setString(4, pacienteParametro.getSesion().getObservacion());
            pst.setString(5, pacienteParametro.getSesion().getMotivoTrabajoEmergente());
            pst.setInt(6, pacienteParametro.getId());
            pst.setInt(7, pacienteParametro.getSesion().getNumeroSesion());

            pst.executeUpdate();

            //crea sesion
            if (pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion()!=0) {
                
                //OBTENER ID SESION
                PreparedStatement pSidSesion = conexion.conexion().prepareStatement(sqlIdSesion);
                pSidSesion.setDate(1, Date.valueOf(pacienteParametro.getSesion().getFecha().toString()));
                pSidSesion.setInt(2, pacienteParametro.getId());
                pSidSesion.setInt(3, pacienteParametro.getSesion().getNumeroSesion());
                ResultSet rsSidIdSesion = pSidSesion.executeQuery();
                
                //OBTENER ID CODIGO FACTURACION
                PreparedStatement pSidCodFacturacion = conexion.conexion().prepareStatement(sqlIdCodFacturacion);
                pSidCodFacturacion.setString(1, pacienteParametro.getSesion().getAutorizacion().getCodigoFacturacion().getNombre());
                ResultSet rsSidIdCodFacturacion = pSidCodFacturacion.executeQuery();
                
                //INSERTAR AUTORIZACION
                PreparedStatement pstA = conexion.conexion().prepareStatement(sqlAutorizacion);
                pstA.setInt(1, 0);
                pstA.setInt(2, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
                pstA.setString(3, pacienteParametro.getSesion().getAutorizacion().getObservacion());
                pstA.setDate(4, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString()));
                pstA.setDouble(5, pacienteParametro.getSesion().getAutorizacion().getCopago());
                if (rsSidIdCodFacturacion.next()) {
                    pstA.setInt(6, rsSidIdCodFacturacion.getInt(1));
                }
                pstA.executeUpdate();

                
                //OBTENER ID AUTORIZACION
                PreparedStatement pSidAutorizacion = conexion.conexion().prepareStatement(sqlIdAutorizacion);
                
                pSidAutorizacion.setInt(1, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
                
                pSidAutorizacion.setDate(2, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString()));
                ResultSet rsSidIdAutorizacion = pSidAutorizacion.executeQuery();

                
                //ASOCIAR AUTORIZACION CON SESION
                PreparedStatement psSesionAutorizacion = conexion.conexion().prepareStatement(sqlSesionAutorizacion);
                if(rsSidIdSesion.next()){
                    
                    psSesionAutorizacion.setInt(1, rsSidIdSesion.getInt("id_sesion_paciente"));
                }
                if(rsSidIdAutorizacion.next()){
                    
                    psSesionAutorizacion.setInt(2, rsSidIdAutorizacion.getInt("id_autorizacion"));
                }
                psSesionAutorizacion.executeUpdate();
                
                
                
                pstA.close();
                pSidCodFacturacion.close();
                pSidSesion.close();
                rsSidIdCodFacturacion.close();
                rsSidIdSesion.close();
            }else{
                //OBTENER ID SESION
                PreparedStatement pSidSesion = conexion.conexion().prepareStatement(sqlIdSesion);
                pSidSesion.setDate(1, Date.valueOf(pacienteParametro.getSesion().getFecha().toString()));
                pSidSesion.setInt(2, pacienteParametro.getId());
                pSidSesion.setInt(3, pacienteParametro.getSesion().getNumeroSesion());
                ResultSet rsSidIdSesion = pSidSesion.executeQuery();
                
                //ASOCIAR AUTORIZACION VACIA CON SESION
                PreparedStatement psSesionAutorizacion = conexion.conexion().prepareStatement(sqlSesionAutorizacion);
                if(rsSidIdSesion.next()){
                    psSesionAutorizacion.setInt(1, rsSidIdSesion.getInt("id_sesion_paciente"));
                }
                psSesionAutorizacion.setInt(2, 5);
                psSesionAutorizacion.executeUpdate();
            }
            
           
            
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void insertarDiagnostico(Paciente paciente) {
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
    
    public void insertarPlanTratamiento(Paciente pacienteParametro) {
        String sqlPlanTratamiento = "INSERT INTO planes_tratamientos (id_plan_tratamiento, estrategia, id_frecuencia_sesion, id_paciente, id_tipo_sesion) VALUES (?,?,?,?,?)";
        String sqlFrecuenciaSesion = "INSERT INTO frecuencias_sesiones (id_frecuencia_sesion, frecuencia) VALUES (?,?)";
        String sqlTipoSesion = "INSERT INTO tipos_sesiones (id_tipo_sesion, nombre, descripcion) VALUES (?,?,?)";
        String sqlIdFrecuencia = "SELECT id_frecuencia_sesion FROM frecuencias_sesiones WHERE frecuencia=?";
        String sqlIdTipoSesion = "SELECT id_tipo_sesion FROM tipos_sesiones WHERE nombre=?";

        try {
            
            PreparedStatement pSidFrecuencia = conexion.conexion().prepareStatement(sqlIdFrecuencia);
            pSidFrecuencia.setString(1, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
            ResultSet rsSidIdFrecuencia = pSidFrecuencia.executeQuery();
            
            PreparedStatement pstFs = conexion.conexion().prepareStatement(sqlFrecuenciaSesion);
            pstFs.setInt(1, 0);
            if(!rsSidIdFrecuencia.next()){
                pstFs.setString(2, pacienteParametro.getPlanTratamiento().getFrecuenciaSesion());
            }
            pstFs.executeUpdate();

            PreparedStatement pstTs = conexion.conexion().prepareStatement(sqlTipoSesion);
            pstTs.setInt(1, 0);
            pstTs.setString(2, pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre());
            pstTs.setString(3, pacienteParametro.getPlanTratamiento().getTipoSEsion().getDecripcion());
            pstTs.executeUpdate();

            rsSidIdFrecuencia = pSidFrecuencia.executeQuery();

            PreparedStatement pSidTipo = conexion.conexion().prepareStatement(sqlIdTipoSesion);
            pSidTipo.setString(1, pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre());
            ResultSet rsSidIdTipo = pSidTipo.executeQuery();

            PreparedStatement pstPt = conexion.conexion().prepareStatement(sqlPlanTratamiento);
            pstPt.setInt(1, 0);
            pstPt.setString(2, pacienteParametro.getPlanTratamiento().getEstrategia());

            if (rsSidIdFrecuencia.next()) {
                pstPt.setInt(3, rsSidIdFrecuencia.getInt(1));
            }

            pstPt.setInt(4, pacienteParametro.getId());

            if (rsSidIdTipo.next()) {
                pstPt.setInt(5, rsSidIdTipo.getInt(1));
            }
            pstPt.executeUpdate();

            pstFs.close();
            pstPt.close();
            pstTs.close();
            rsSidIdFrecuencia.close();
            rsSidIdTipo.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
    
}
