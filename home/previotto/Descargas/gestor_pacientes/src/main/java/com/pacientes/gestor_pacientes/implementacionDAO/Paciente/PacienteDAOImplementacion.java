/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;


import com.pacientes.gestor_pacientes.DAO.IPacienteDAO;

import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.*;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.Objects;


/**
 *
 * @author previotto
 */
public class PacienteDAOImplementacion extends PadreDAOImplementacion implements IPacienteDAO {
    
    
    
     @Override
    public Paciente obtener(Paciente pacienteParametro) throws Exception {
        Paciente paciente = new Paciente();
        Paciente pacienteResultado = new Paciente();

        if (!Objects.isNull(pacienteParametro)) {
            if (Objects.nonNull(pacienteParametro.getId())) {
                daoImplementacion = new DatosPrincipalesDAOImplementacion();
                pacienteResultado = (Paciente) daoImplementacion.obtener(pacienteParametro);
                paciente.setNombre(
                        pacienteResultado.
                                getNombre()).
                        setApellido(pacienteResultado.getApellido()).
                        setEdad(pacienteResultado.getEdad()).
                        setDni(pacienteResultado.getDni()).
                        setTelefono(pacienteResultado.getTelefono()).
                        setHonorarios(pacienteResultado.getHonorarios());
                
                //SESION
                daoImplementacion = new SesionDAOImplementacion();
                SesionPaciente sesion = new SesionPaciente();
                sesion.setIdPaciente(pacienteParametro.getId());
                paciente.setSesiones(daoImplementacion.obtenerLista(sesion));

                //PLAN
                daoImplementacion = new PlanTratamientoDAOImplementacion();
                PlanTratamiento planTratamiento = new PlanTratamiento();
                planTratamiento.setIdPaciente(pacienteParametro.getId());
                planTratamiento = (PlanTratamiento)daoImplementacion.obtener(planTratamiento);
                paciente.setPlanTratamiento(planTratamiento);

                //DIAGNOSTICO
                daoImplementacion = new DiagnosticoDAOImplementacion();
                DiagnosticoPaciente diagnosticoPaciente = new DiagnosticoPaciente();
                diagnosticoPaciente.setIdPaciente(pacienteParametro.getId());
                diagnosticoPaciente = (DiagnosticoPaciente) daoImplementacion.obtener(diagnosticoPaciente);
                paciente.setDiagnostico(diagnosticoPaciente);

                //OBRA SOCIAL PACIENTE
                daoImplementacion = new ObraSocialPacienteDAOImplementacion();
                ObraSocialPaciente obraSocialPaciente = new ObraSocialPaciente();
                obraSocialPaciente.setIdPaciente(pacienteParametro.getId());
                obraSocialPaciente = (ObraSocialPaciente) daoImplementacion.obtener(obraSocialPaciente);
                paciente.setObraSocialPaciente(obraSocialPaciente);
            }

        }
        return paciente;
    }
    
    @Override
    public void actualizar(Paciente pacienteParametro) throws SQLException {
        
    }
    
   
    @Override
    public void eliminar(Paciente pacienteParametro) throws SQLException {

        String sqlEliminar = "DELETE FROM usuarios_pacientes  WHERE id_usuario = ? AND id_paciente = ?";
        PreparedStatement pSeliminar = conexion.conexion().prepareStatement(sqlEliminar);
        pSeliminar.setInt(1, VariablesEstaticas.usuario.getId());
        System.out.println(VariablesEstaticas.usuario.getId());
        pSeliminar.setInt(2, obtenerId(pacienteParametro));
        System.out.println(obtenerId(pacienteParametro));
        pSeliminar.executeUpdate();
        

    }

    @Override
    public void insertar(Paciente paciente) throws SQLException{
        
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
    
    

       
    
    /***
     * 
     * @param objetoParametro recibe paciente seteado con el DNI
     * @return retorna un entero con el id del paciente
     * @throws Exception 
     */
    @Override
    public int obtenerId(Paciente objetoParametro) throws SQLException {
        Paciente paciente = new Paciente();
        
        
        String sqlDni = "SELECT p.id_paciente \n" +
                        "FROM pacientes p\n" +
                        "JOIN usuarios_pacientes up ON p.id_paciente = up.id_paciente \n" +
                        "WHERE p.dni=? AND up.id_usuario = ?";
        PreparedStatement pSDni = conexion.conexion().prepareStatement(sqlDni);
        pSDni.setInt(1, objetoParametro.getDni());
        pSDni.setInt(2, VariablesEstaticas.usuario.getId());
        
        
       
        ResultSet rsSPaciente = pSDni.executeQuery();
        if (rsSPaciente.next()) {
            pSDni.close();
            rsSPaciente.close();
            return rsSPaciente.getInt("id_paciente");
        }else{

            return 0;
        }
    }

    @Override
    public List<Paciente> obtenerLista(Paciente objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    

    

    

   
    

}
