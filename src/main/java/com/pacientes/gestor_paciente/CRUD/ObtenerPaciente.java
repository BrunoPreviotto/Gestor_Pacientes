/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_paciente.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Afiliado;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.EstadoFacturacion;
import com.pacientes.gestor_pacientes.modelo.Honorario;
import com.pacientes.gestor_pacientes.modelo.ObraSocialPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.PlanObraSocial;
import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.SesionPaciente;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import com.pacientes.gestor_pacientes.modelo.TipoSesion;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author previotto
 */
public class ObtenerPaciente extends PacienteDAOImplementacion {
    
    
    
    
    public Paciente obtenerPaciente(Paciente pacienteParametro) {
        Telefono telefono;
        Honorario honorario;
        Paciente paciente;
        Paciente pacienteNull = new Paciente();
        try {
            String sqlDni = "SELECT n.nombre, n.apellido, p.edad, p.dni, t.numero_telefono, h.honorario  \n" +
                            "FROM pacientes p \n" +
                            "JOIN nombres n ON p.id_nombre = n.id_nombre \n" +
                            "JOIN telefonos_pacientes t ON p.id_telefono_paciente = t.id_telefono_paciente \n" +
                            "JOIN honorarios h ON p.id_honorario = h.id_honorario \n" +
                            "WHERE dni=? AND es_paciente=true;";
            
            PreparedStatement pSDni = conexion.conexion().prepareStatement(sqlDni);
            pSDni.setInt(1, pacienteParametro.getDni());
            ResultSet rsSPaciente = pSDni.executeQuery();
            if (rsSPaciente.next()) {
                telefono = new Telefono(rsSPaciente.getNString(5));
                honorario = new Honorario(rsSPaciente.getDouble("honorario"));
                paciente = new Paciente(rsSPaciente.getString(1), rsSPaciente.getString(2), rsSPaciente.getInt(3), rsSPaciente.getInt(4), honorario, telefono);
                
                pSDni.close();
                rsSPaciente.close();
                return paciente;
            }

        } catch (SQLException e) {
            return pacienteNull;
        }
        return pacienteNull;
    }
    
    
    public Paciente obtenerSesiones(int idPaciente) {
        String sqlListaSesiones = "SELECT sp.numero_sesion, sp.fecha, sp.trabajo_sesion, sp.observacion  AS obsevacionSesion, \n" +
                                  "sp.honorarios_por_sesion , a.numero_autorizacion, a.observacion AS observacionAutorizacion,\n" +
                                  "a.asociacion, a.copago, cf.nombre, ef.estado \n" +
                                  "FROM autorizaciones a JOIN sesiones_pacientes_autorizaciones spa ON a.id_autorizacion = spa.id_autorizacion\n" +
                                  "JOIN sesiones_pacientes sp ON spa.id_sesion_paciente = sp.id_sesion_paciente\n" +
                                  "JOIN codigos_facturaciones cf ON a.id_codigo_facturacion = cf.id_codigo_facturacion\n" +
                                  "JOIN estados_facturacion ef ON ef.id_estado_facturacion = sp.id_estado_facturacion \n" +
                                  "WHERE id_paciente=?";
        List<SesionPaciente> ts = new ArrayList();
         Paciente paciente = new Paciente();
        try {
           
            PreparedStatement psSesiones = conexion.conexion().prepareStatement(sqlListaSesiones);
            psSesiones.setInt(1, idPaciente);
            ResultSet rsSesiones = psSesiones.executeQuery();
            while (rsSesiones.next()) {
                
                ts.add(new SesionPaciente(rsSesiones.getInt("numero_sesion"), 
                        LocalDate.parse(rsSesiones.getString("fecha")), 
                        rsSesiones.getString("trabajo_sesion"), 
                        rsSesiones.getString("obsevacionSesion"), 
                        Double.parseDouble(rsSesiones.getString("honorarios_por_sesion")), 
                        new AutorizacionesSesionesObraSociales(rsSesiones.getInt("numero_autorizacion"), 
                                rsSesiones.getString("observacionAutorizacion"), 
                                LocalDate.parse(rsSesiones.getString("asociacion")), 
                                rsSesiones.getDouble("copago"), new CodigoFacturacion(rsSesiones.getString("nombre"))), 
                        new EstadoFacturacion(rsSesiones.getString("estado"))));
                
                
            }
            if(!Objects.isNull(ts)){
                paciente.setSesiones(ts);
            }
            psSesiones.close();
            rsSesiones.close();
            return paciente;

        } catch (SQLException e) {
            return paciente;
        }
        
    }
    
    
    public Paciente obtenerPlanTratamiento(int idPaciente) {
        Paciente paciente = new Paciente();
        try {
            String sqlPlanes = "SELECT fs.frecuencia, ts.nombre, ts.descripcion, pt.estrategia FROM planes_tratamientos pt JOIN tipos_sesiones ts ON pt.id_tipo_sesion = ts.id_tipo_sesion JOIN frecuencias_sesiones fs ON pt.id_frecuencia_sesion = fs.id_frecuencia_sesion WHERE id_paciente=?";
            
            PreparedStatement psPlan = conexion.conexion().prepareStatement(sqlPlanes);
            psPlan.setInt(1, idPaciente);
            ResultSet rsPlan = psPlan.executeQuery();
            
            if(rsPlan.next()){
                paciente.setPlanTratamiento(new PlanTratamiento(rsPlan.getString("estrategia"), rsPlan.getString("frecuencia"), new TipoSesion(rsPlan.getString("nombre"), rsPlan.getString("descripcion"))));
            }
            return paciente;
            
        } catch (SQLException e) {
            return paciente;
        }
    }
    
    
    public Paciente obtenerDiagnosticoPaciente(int idPaciente) {
        Paciente paciente = new Paciente();
        try {
            String sqlDiagnostico = "SELECT diagnostico, observacion FROM diagnosticos WHERE id_paciente=?";
            PreparedStatement psDiagnostico = conexion.conexion().prepareStatement(sqlDiagnostico);
            psDiagnostico.setInt(1, idPaciente);
            ResultSet rsDiagnostico = psDiagnostico.executeQuery();
            
            if(rsDiagnostico.next()){
                paciente.setDiagnostico(new DiagnosticoPaciente(rsDiagnostico.getString("diagnostico"), rsDiagnostico.getString("observacion")));
            }
            return paciente;
            
        } catch (SQLException e) {
            return paciente;
        }
    }
    
    
    public Paciente obtenerObraSocialPaciente(int idPaciente) {
        Paciente paciente = new Paciente();
        try {
            String sqlObraSocial = "SELECT aos.numero_afiliado, os.nombre AS nombreObraSocial, pos.nombre AS nombrePlan \n" +
                                   "FROM afiliados_obras_sociales aos JOIN planes_obras_sociales pos\n" +
                                   "ON pos.id_plan_obra_social  = aos.id_plan_obra_social \n" + 
                                   "JOIN obras_sociales_planes_obras_sociales ospos \n" +
                                   "ON pos.id_plan_obra_social = ospos.id_plan_obra_social \n" + 
                                   "JOIN obras_sociales os ON os.id_obra_social = ospos.id_obra_social \n" + 
                                   "WHERE id_paciente = ?";
            PreparedStatement psObraSocial = conexion.conexion().prepareStatement(sqlObraSocial);
            psObraSocial.setInt(1, idPaciente);
            ResultSet rsObraSocial = psObraSocial.executeQuery();
            
            if(rsObraSocial.next()){
                paciente.setObraSocialPaciente(new ObraSocialPaciente(new Afiliado(rsObraSocial.getInt("numero_afiliado")), rsObraSocial.getString("nombreObraSocial"), new PlanObraSocial(rsObraSocial.getString("nombrePlan"), "")));
            }
            return paciente;
            
        } catch (SQLException e) {
            return paciente;
        }
    }
    
    public String obtenerDescripcionTipoSesionPlan(String tipoSesion) throws SQLException{
        Paciente paciente = new Paciente();
        try {
            String sqlDescripcionTipoSesionPlan = "SELECT descripcion  FROM tipos_sesiones ts WHERE ts.nombre = ?";
            PreparedStatement psDescripcionTipoSesionPlan = conexion.conexion().prepareStatement(sqlDescripcionTipoSesionPlan);
            psDescripcionTipoSesionPlan.setString(1, tipoSesion);
            ResultSet rsDescripcionTipoSesionPlan = psDescripcionTipoSesionPlan.executeQuery();
            
            if(rsDescripcionTipoSesionPlan.next()){
                return rsDescripcionTipoSesionPlan.getString("descripcion");
            }
            
            
        } catch (SQLException e) {
           
        }
        return "";
    }
    
    
}
