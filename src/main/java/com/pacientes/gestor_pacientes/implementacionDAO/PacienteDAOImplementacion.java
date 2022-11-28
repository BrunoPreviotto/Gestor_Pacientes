/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.google.common.collect.ImmutableMap;
import com.pacientes.gestor_pacientes.DAO.IPacienteDAO;
import com.pacientes.gestor_pacientes.modelo.*;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import com.pacientes.gestor_pacientes.servicios.Encriptar;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author previotto
 */
public class PacienteDAOImplementacion implements IPacienteDAO {

    private ConexionMariadb conexion = ConexionMariadb.getInstacia();

    @Override
    public Paciente obtenerIdPaciente(Paciente pacienteParametro) {
        Paciente paciente = new Paciente();
        try {
            String sqlDni = "SELECT id_paciente FROM pacientes WHERE dni=?";
            PreparedStatement pSDni = conexion.conexion().prepareStatement(sqlDni);
            pSDni.setInt(1, pacienteParametro.getDni());
            ResultSet rsSPaciente = pSDni.executeQuery();
            if (rsSPaciente.next()) {
                paciente.setId(rsSPaciente.getInt(1));
                pSDni.close();
                rsSPaciente.close();
                return paciente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paciente obtener(Paciente pacienteParametro) {
        Paciente paciente = new Paciente();
         Paciente pacienteResultado = new Paciente();
        if(!Objects.isNull(pacienteParametro)){
            if(!Objects.isNull(pacienteParametro.getDni())){
                pacienteResultado = obtenerPaciente(pacienteParametro);
                paciente.setNombre(pacienteResultado.getNombre()).setApellido(pacienteResultado.getApellido()).setEdad(pacienteResultado.getEdad()).setDni(pacienteResultado.getDni()).setTelefono(pacienteResultado.getTelefono());
            }
            if(!Objects.isNull(pacienteParametro.getId())){
                pacienteResultado = obtenerSesiones(pacienteParametro.getId());
                paciente.setSesiones(pacienteResultado.getSesiones());
                
                pacienteResultado = obtenerPlanTratamiento(pacienteParametro.getId());
                paciente.setPlanTratamiento(pacienteResultado.getPlanTratamiento());
                
                pacienteResultado = obtenerDiagnosticoPaciente(pacienteParametro.getId());
                paciente.setDiagnostico(pacienteResultado.getDiagnostico());
                
                pacienteResultado = obtenerObraSocialPaciente(pacienteParametro.getId());
                paciente.setObraSocialPaciente(pacienteResultado.getObraSocialPaciente());
            }
            
        }
        return paciente;
    }
    
    @Override
    public List<Paciente> obtenerLista(Paciente objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Paciente obtenerPaciente(Paciente pacienteParametro) {
        Telefono telefono;
        Paciente paciente;
        Paciente pacienteNull = new Paciente();
        try {
            String sqlDni = "SELECT n.nombre, n.apellido, p.edad, p.dni, t.numero_telefono FROM pacientes AS p JOIN nombres AS n ON p.id_nombre = n.id_nombre JOIN telefonos_pacientes AS t ON p.id_telefono_paciente = t.id_telefono_paciente WHERE dni=? AND es_paciente=true;";
            PreparedStatement pSDni = conexion.conexion().prepareStatement(sqlDni);
            pSDni.setInt(1, pacienteParametro.getDni());
            ResultSet rsSPaciente = pSDni.executeQuery();
            if (rsSPaciente.next()) {
                telefono = new Telefono(rsSPaciente.getNString(5));
                paciente = new Paciente(rsSPaciente.getString(1), rsSPaciente.getString(2), rsSPaciente.getInt(3), rsSPaciente.getInt(4), telefono);
                pSDni.close();
                rsSPaciente.close();
                return paciente;
            }

        } catch (SQLException e) {
            return pacienteNull;
        }
        return pacienteNull;
    }

    @Override
    public Paciente obtenerSesiones(int idPaciente) {
        String sqlListaSesiones = "SELECT sp.numero_sesion, sp.fecha, sp.trabajo_sesion, sp.observacion AS obsevacionSesion, sp.motivo_trabajo_emergente, a.numero_autorizacion, a.observacion AS observacionAutorizacion, a.asociacion, a.copago, cf.nombre  FROM autorizaciones a JOIN sesiones_pacientes sp ON a.id_sesion_paciente = sp.id_sesion_paciente JOIN codigos_facturaciones cf ON a.id_codigo_facturacion = cf.id_codigo_facturacion WHERE id_paciente=?";
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
                        rsSesiones.getString("motivo_trabajo_emergente"), 
                        new AutorizacionesSesionesObraSociales(rsSesiones.getInt("numero_autorizacion"), 
                                rsSesiones.getString("observacionAutorizacion"), 
                                LocalDate.parse(rsSesiones.getString("asociacion")), 
                                rsSesiones.getDouble("copago"), new CodigoFacturacion(rsSesiones.getString("nombre")))));
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
    
    @Override
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


    @Override
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
    
    @Override
    public Paciente obtenerObraSocialPaciente(int idPaciente) {
        Paciente paciente = new Paciente();
        try {
            String sqlObraSocial = "SELECT os.nombre AS nombreObraSocial, pos.nombre AS nombrePlan, aos.numero_afiliado FROM obras_sociales_planes_obras_sociales ospos JOIN obras_sociales os ON ospos.id_obra_social = os.id_obra_social JOIN planes_obras_sociales pos ON ospos.id_plan_obra_social = pos.id_plan_obra_social JOIN afiliados_obras_sociales aos ON ospos.id_obra_social = aos.id_obra_social WHERE id_paciente=?";
            PreparedStatement psObraSocial = conexion.conexion().prepareStatement(sqlObraSocial);
            psObraSocial.setInt(1, idPaciente);
            ResultSet rsObraSocial = psObraSocial.executeQuery();
            
            if(rsObraSocial.next()){
                paciente.setObraSocialPaciente(new ObraSocialPaciente(rsObraSocial.getInt("numero_afiliado"), rsObraSocial.getString("nombreObraSocial"), new PlanObraSocial(rsObraSocial.getString("nombrePlan"), "")));
            }
            return paciente;
            
        } catch (SQLException e) {
            return paciente;
        }
    }
    
    @Override
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

    @Override
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
    

    @Override
    public void actualizar(Paciente paciente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Paciente paciente) {

        try {
            String sqlEliminar = "UPDATE pacientes SET es_paciente = false WHERE dni=?";
            PreparedStatement pSeliminar = conexion.conexion().prepareStatement(sqlEliminar);
            pSeliminar.setInt(1, paciente.getDni());
            pSeliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertar(Paciente paciente) {
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";

        String sqlPaciente = "INSERT INTO pacientes(id_paciente, edad, dni, es_paciente, id_nombre, id_honorario, id_telefono_paciente) VALUES(?, ?, ?, ?, ?, ?, ?)";

        String sqlTelefono = "INSERT INTO telefonos_pacientes(id_telefono, numero_telefono) VALUES(?,?)";
        try {

            PreparedStatement pst;
            //selecciona id nombre si el nombre existe
            String sqlSNombre = "SELECT id_nombre FROM nombres WHERE nombre=? AND apellido=?;";
            PreparedStatement pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            pSNombre.setString(1, paciente.getNombre());
            pSNombre.setString(2, paciente.getApellido());
            ResultSet rsSNombre = pSNombre.executeQuery();

            //si no existe lo crea
            if (!rsSNombre.next()) {
                pst = conexion.conexion().prepareStatement(sqlNombre);
                pst.setInt(1, 0);
                pst.setString(2, paciente.getNombre());
                pst.setString(3, paciente.getApellido());
                pst.executeUpdate();
            }

            //
            sqlSNombre = "SELECT id_nombre FROM nombres WHERE nombre=? AND apellido=?;";
            pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            pSNombre.setString(1, paciente.getNombre());
            pSNombre.setString(2, paciente.getApellido());
            rsSNombre = pSNombre.executeQuery();

           

            String sqlSTelefono = "SELECT id_telefono FROM telefonos_pacientes WHERE numero_telefono=?;";
            PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlSTelefono);
            pSTelefono.setString(1, paciente.getTelefono().getTelefono());
            ResultSet rsSTelefono = pSTelefono.executeQuery();
            
            if (!rsSTelefono.next()) {
                pst = conexion.conexion().prepareStatement(sqlTelefono);
                pst.setInt(1, 0);
                pst.setString(2, paciente.getTelefono().getTelefono());
                pst.executeUpdate();
            }
            
            
            pSTelefono = conexion.conexion().prepareStatement(sqlSTelefono);
            pSTelefono.setString(1, paciente.getTelefono().getTelefono());
            rsSTelefono = pSTelefono.executeQuery();
            
            
             //crea el paciente
            pst = conexion.conexion().prepareStatement(sqlPaciente);
            pst.setInt(1, 0);
            pst.setInt(2, paciente.getEdad());
            pst.setInt(3, paciente.getDni());
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

    

    @Override
    public void insertarPaciente(Paciente paciente) {

    }

    @Override
    public void insertarSesion(int idPaciente, SesionPaciente sesion) {
        String sqlSesion = "INSERT INTO sesiones_pacientes (id_sesion_paciente, fecha, trabajo_sesion, observacion, motivo_trabajo_emergente, id_paciente, numero_sesion) VALUES(?,?,?,?,?,?,?)";

        String sqlAutorizacion = "INSERT INTO autorizaciones  (id_autorizacion, numero_autorizacion, id_sesion_paciente, observacion, asociacion, copago, id_codigo_facturacion) VALUES(?,?,?,?,?,?,?)";

        String sqlIdSesion = "SELECT id_sesion_paciente FROM sesiones_pacientes WHERE fecha=? AND id_paciente=?";

        String sqlIdCodFacturacion = "SELECT id_codigo_facturacion FROM codigos_facturaciones WHERE nombre =?";
        try {

            //crea el sesion
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlSesion);
            pst.setInt(1, 0);
            pst.setDate(2, Date.valueOf(sesion.getFecha().toString()));
            pst.setString(3, sesion.getTrabajoSesion());
            pst.setString(4, sesion.getObservacion());
            pst.setString(5, sesion.getMotivoTrabajoEmergente());
            pst.setInt(6, idPaciente);
            pst.setInt(7, sesion.getNumeroSesion());

            pst.executeUpdate();

            //crea obra social sesion
            if (!Objects.isNull(sesion.getAutorizacion())) {

                PreparedStatement pstA = conexion.conexion().prepareStatement(sqlAutorizacion);
                pstA.setInt(1, 0);
                pstA.setInt(2, sesion.getAutorizacion().getNumeroAutorizacion());

                PreparedStatement pSidSesion = conexion.conexion().prepareStatement(sqlIdSesion);
                pSidSesion.setDate(1, Date.valueOf(sesion.getFecha().toString()));
                pSidSesion.setInt(2, idPaciente);
                ResultSet rsSidIdSesion = pSidSesion.executeQuery();

                if (rsSidIdSesion.next()) {
                    pstA.setInt(3, rsSidIdSesion.getInt(1));
                }

                pstA.setString(4, sesion.getAutorizacion().getObservacion());
                pstA.setDate(5, Date.valueOf(sesion.getAutorizacion().getAsociacion().toString()));
                pstA.setDouble(6, sesion.getAutorizacion().getCopago());

                PreparedStatement pSidCodFacturacion = conexion.conexion().prepareStatement(sqlIdCodFacturacion);
                pSidCodFacturacion.setString(1, sesion.getAutorizacion().getCodigoFacturacion().getNombre());
                ResultSet rsSidIdCodFacturacion = pSidCodFacturacion.executeQuery();

                if (rsSidIdCodFacturacion.next()) {
                    pstA.setInt(7, rsSidIdCodFacturacion.getInt(1));
                }
                
                pstA.executeUpdate();
                pstA.close();
                pSidCodFacturacion.close();
                pSidSesion.close();
                rsSidIdCodFacturacion.close();
                rsSidIdSesion.close();
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertarDiagnostico(int idPaciente, DiagnosticoPaciente diagnostico) {
        String sqlDiagnostico = "INSERT INTO diagnosticos (id_diagnostico, diagnostico, observacion, id_paciente) VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlDiagnostico);
            pst.setInt(1, 0);
            pst.setString(2, diagnostico.getDiagnostico());
            pst.setString(3, diagnostico.getObservacion());
            pst.setInt(4, idPaciente);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertarPlanTratamiento(int idPaciente, PlanTratamiento plan) {
        String sqlPlanTratamiento = "INSERT INTO planes_tratamientos (id_plan_tratamiento, estrategia, id_frecuencia_sesion, id_paciente, id_tipo_sesion) VALUES (?,?,?,?,?)";
        String sqlFrecuenciaSesion = "INSERT INTO frecuencias_sesiones (id_frecuencia_sesion, frecuencia) VALUES (?,?)";
        String sqlTipoSesion = "INSERT INTO tipos_sesiones (id_tipo_sesion, nombre, descripcion) VALUES (?,?,?)";
        String sqlIdFrecuencia = "SELECT id_frecuencia_sesion FROM frecuencias_sesiones WHERE frecuencia=?";
        String sqlIdTipoSesion = "SELECT id_tipo_sesion FROM tipos_sesiones WHERE nombre=?";

        try {
            PreparedStatement pstFs = conexion.conexion().prepareStatement(sqlFrecuenciaSesion);
            pstFs.setInt(1, 0);
            pstFs.setString(2, plan.getFrecuenciaSesion());
            pstFs.executeUpdate();

            PreparedStatement pstTs = conexion.conexion().prepareStatement(sqlTipoSesion);
            pstTs.setInt(1, 0);
            pstTs.setString(2, plan.getTipoSEsion().getNombre());
            pstTs.setString(3, plan.getTipoSEsion().getDecripcion());
            pstTs.executeUpdate();

            PreparedStatement pSidFrecuencia = conexion.conexion().prepareStatement(sqlIdFrecuencia);
            pSidFrecuencia.setString(1, plan.getFrecuenciaSesion());
            ResultSet rsSidIdFrecuencia = pSidFrecuencia.executeQuery();

            PreparedStatement pSidTipo = conexion.conexion().prepareStatement(sqlIdTipoSesion);
            pSidTipo.setString(1, plan.getTipoSEsion().getNombre());
            ResultSet rsSidIdTipo = pSidTipo.executeQuery();

            PreparedStatement pstPt = conexion.conexion().prepareStatement(sqlPlanTratamiento);
            pstPt.setInt(1, 0);
            pstPt.setString(2, plan.getEstrategia());

            if (rsSidIdFrecuencia.next()) {
                pstPt.setInt(3, rsSidIdFrecuencia.getInt(1));
            }

            pstPt.setInt(4, idPaciente);

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

    @Override
    public void insertarObraSocialPaciente(int idPaciente, ObraSocialPaciente obraSocialPaciente) {
        String sqlAfiliado = "INSERT INTO afiliados_obras_sociales (id_afiliado_obra_social, numero_afiliado, id_paciente, id_obra_social) VALUES (?,?,?,?)";
        String sqlIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre=?";
        String sqlExistePlan = "SELECT nombre FROM planes_obras_sociales WHERE nombre=?";
        String sqlPlanes = "INSERT INTO planes_obras_sociales (id_plan_obra_social, nombre, descripcion) VALUES (?,?,?)";
        String sqlIdPlan = "SELECT id_plan_obra_social FROM planes_obras_sociales WHERE nombre=?";
        String sqlExistePlanObraSocial = "SELECT id_obra_social, id_plan_obra_social FROM obras_sociales_planes_obras_sociales WHERE id_obra_social=? AND id_plan_obra_social=?";
        String sqlObrasSocialesPlanesObrasSociales = "INSERT INTO obras_sociales_planes_obras_sociales (id_obra_social, id_plan_obra_social) VALUES (?,?)";

        try {
            PreparedStatement pSidObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
            pSidObraSocial.setString(1, obraSocialPaciente.getNombre());
            ResultSet rsObraSocial = pSidObraSocial.executeQuery();

            PreparedStatement psAfiliado = conexion.conexion().prepareStatement(sqlAfiliado);
            psAfiliado.setInt(1, 0);
            psAfiliado.setInt(2, obraSocialPaciente.getNumeroAfiliado());
            psAfiliado.setInt(3, idPaciente);
            if (rsObraSocial.next()) {
                psAfiliado.setInt(4, rsObraSocial.getInt("id_obra_social"));
            }
            psAfiliado.executeUpdate();

            PreparedStatement pSexistePlan = conexion.conexion().prepareStatement(sqlExistePlan);
            pSexistePlan.setString(1, obraSocialPaciente.getPlan().getNombre());
            ResultSet rsExistePlan = pSexistePlan.executeQuery();

            if (!rsExistePlan.next()) {
                PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlPlanes);
                psPlanes.setInt(1, 0);
                psPlanes.setString(2, obraSocialPaciente.getPlan().getNombre());
                psPlanes.setString(3, obraSocialPaciente.getPlan().getDescripcion());
                psPlanes.executeUpdate();
            }

            PreparedStatement pSIdPlan = conexion.conexion().prepareStatement(sqlIdPlan);
            pSIdPlan.setString(1, obraSocialPaciente.getPlan().getNombre());
            ResultSet rsIdPlan = pSIdPlan.executeQuery();

            PreparedStatement pSExisteObraSocialPlan = conexion.conexion().prepareStatement(sqlExistePlanObraSocial);
            pSExisteObraSocialPlan.setInt(1, rsObraSocial.getInt("id_obra_social"));
            
            if(rsIdPlan.next()){
                pSExisteObraSocialPlan.setInt(2, rsIdPlan.getInt("id_plan_obra_social"));
            }
            
            ResultSet rsExisteObraSocialPlan = pSExisteObraSocialPlan.executeQuery();
            
            
            if (!rsExisteObraSocialPlan.next()) {
                PreparedStatement psObraSocialPlan = conexion.conexion().prepareStatement(sqlObrasSocialesPlanesObrasSociales);
                psObraSocialPlan.setInt(1, rsObraSocial.getInt("id_obra_social"));
                psObraSocialPlan.setInt(2, rsIdPlan.getInt("id_plan_obra_social"));
                psObraSocialPlan.executeUpdate();
            }

            pSIdPlan.close();
            pSexistePlan.close();
            pSidObraSocial.close();
            psAfiliado.close();
            rsExisteObraSocialPlan.close();
            rsExistePlan.close();
            rsIdPlan.close();
            rsObraSocial.close();

        } catch (SQLException e) {
        }
    }

    

   
    

}
