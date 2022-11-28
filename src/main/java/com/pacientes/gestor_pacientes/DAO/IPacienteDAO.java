/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pacientes.gestor_pacientes.DAO;
import com.pacientes.gestor_pacientes.modelo.*;
import javafx.collections.ObservableList;
import java.util.List;

/**
 *
 * @author previotto
 */
public interface IPacienteDAO extends CRUD<Paciente>{
    public List<String> obtenerListaTiposSesion();
    public List<String> obtenerListaCodigosFacturacion();
    public Paciente obtenerPaciente(Paciente paciente);
    public Paciente obtenerIdPaciente(Paciente paciente);
    public Paciente obtenerSesiones(int idPaciente);
    public Paciente obtenerPlanTratamiento(int idPaciente);
    public Paciente obtenerDiagnosticoPaciente(int idPaciente);
    public Paciente obtenerObraSocialPaciente(int idPaciente);
    
    public void insertarPaciente(Paciente paciente);
    public void insertarSesion(int idPaciente, SesionPaciente sesion);
    public void insertarDiagnostico(int idPaciente, DiagnosticoPaciente diagnostico);
    public void insertarPlanTratamiento(int idPaciente, PlanTratamiento plan);
    public void insertarObraSocialPaciente(int idPaciente, ObraSocialPaciente obraSocialPaciente);
}
