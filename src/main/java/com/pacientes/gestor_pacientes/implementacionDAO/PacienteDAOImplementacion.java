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

    protected ConexionMariadb conexion = ConexionMariadb.getInstacia();
    
     @Override
    public Paciente obtener(Paciente pacienteParametro) {
        ObtenerPaciente obtenerPaciente = new ObtenerPaciente();
        Paciente paciente = new Paciente();
        Paciente pacienteResultado = new Paciente();
       
        if (esPacienteDeUsuarioActual(pacienteParametro)) {
            
            if (!Objects.isNull(pacienteParametro)) {
                if (!Objects.isNull(pacienteParametro.getDni())) {
                    pacienteResultado = obtenerPaciente.obtenerPaciente(pacienteParametro);
                    paciente.setNombre(pacienteResultado.getNombre()).setApellido(pacienteResultado.getApellido()).setEdad(pacienteResultado.getEdad()).setDni(pacienteResultado.getDni()).setTelefono(pacienteResultado.getTelefono());
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
    public void actualizar(Paciente paciente, int numeroFuncion) {
        AcutualizarPaciente actualizarPaciente = new AcutualizarPaciente();
        MenuInicioController menuInicio = new MenuInicioController();
        
        switch (numeroFuncion) {
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
                //OBRA SOCIAL
                break;
        }
        
        
    }

    @Override
    public void eliminar(Paciente paciente, int numeroFuncion) {
        
        EliminarPaciente eliminar = new EliminarPaciente();
        
        switch (numeroFuncion) {
            case 1:
                eliminar.eliminarPaciente(paciente);
                break;
            case 2:
                //DATOS PRINCIPALES
                break;
            case 3:
                //DIAGNOSTICO
                break;
            case 4:
                //SESIONES
                break;
            case 5:
                //OBRA SOCIAL
                break;
            case 6:
                //PLAN
                break;
        }
        
        
        
    }

    @Override
    public void insertar(Paciente paciente, int numeroFucion) {
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
                insertarPaciente.insertarDiagnostico(paciente);
                break;
                
        }
        
        

    }

    @Override
    public List<Paciente> obtenerLista(Paciente objeto) {
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
            PreparedStatement psUltimaSesion = conexion.conexion().prepareStatement(sqlUltimaSesion);
            
            psUltimaSesion.setInt(1, obtenerIdPaciente(pacienteParametro).getId());
            ResultSet rsUltimaSesion = psUltimaSesion.executeQuery();
            if(rsUltimaSesion.next()){
                return rsUltimaSesion.getInt("numeroSesion");
            }
            return 0;
        } catch (Exception e) {
        }
        return 0;
    }

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
            }else{
                paciente.setDni(0);
                return paciente;
            }

        } catch (SQLException e) {
            paciente.setDni(0);
            return paciente;
        }
    }

    public boolean existeAutorizacion(Paciente pacienteParametro){
       
        return pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion() == 0 
               && pacienteParametro.getSesion().getAutorizacion().getObservacion().equals("-") 
               && pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString().equals("1700-01-01")
               && pacienteParametro.getSesion().getAutorizacion().getCopago() == 0;
    }
    
    public Paciente obtenerIdSesionAutorizacion(Paciente pacienteParametro){
        Paciente pacienteResult = new Paciente();
        SesionPaciente sesion =  new SesionPaciente();
        AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
        String buscarIdSesion = "SELECT sp.id_sesion_paciente, a.id_autorizacion \n" +
                                             "FROM sesiones_pacientes sp \n" +
                                             "JOIN sesiones_pacientes_autorizaciones spa ON sp.id_sesion_paciente = spa.id_sesion_paciente \n" +
                                             "JOIN autorizaciones a ON spa.id_autorizacion = a.id_autorizacion\n" +
                                             "WHERE sp.fecha = ? \n" +
                                             "AND sp.id_paciente = ?\n" +
                                             "AND sp.numero_sesion = ?\n" +
                                             "AND a.numero_autorizacion = ?\n" +
                                             "AND a.asociacion = ?";
        
        try {
            //BUSCAR ID DE SESION Y AUTORIZACION
            PreparedStatement psBuscarIdSesionYAutorizacion = conexion.conexion().prepareStatement(buscarIdSesion);
            psBuscarIdSesionYAutorizacion.setDate(1, Date.valueOf(pacienteParametro.getSesion().getFecha().toString()));
            psBuscarIdSesionYAutorizacion.setInt(2, pacienteParametro.getId());
            psBuscarIdSesionYAutorizacion.setInt(3, pacienteParametro.getSesion().getNumeroSesion());
            psBuscarIdSesionYAutorizacion.setInt(4, pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion());
            psBuscarIdSesionYAutorizacion.setDate(5, Date.valueOf(pacienteParametro.getSesion().getAutorizacion().getAsociacion()));
            ResultSet rsBuscarIdSesionYAutorizacion = psBuscarIdSesionYAutorizacion.executeQuery();
            
            if(rsBuscarIdSesionYAutorizacion.next()){
                autorizacion.setId(rsBuscarIdSesionYAutorizacion.getInt("id_autorizacion"));
                sesion.setAutorizacion(autorizacion);
                sesion.setIdSesion(rsBuscarIdSesionYAutorizacion.getInt("id_sesion_paciente"));
                pacienteResult.setSesion(sesion);
            }
            
            return pacienteResult;
            
        } catch (Exception e) {
        }
        return pacienteResult;
    }
    
   

    

    

    

    

    

    

   
    

}
