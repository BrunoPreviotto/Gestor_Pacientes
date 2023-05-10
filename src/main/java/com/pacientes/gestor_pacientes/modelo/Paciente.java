/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

import java.util.List;

import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;

/**
 *
 * @author previotto
 */
public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private Integer dni;
    private List<Telefono> listaTelefonos;
    private boolean es_paciente;
    private double honorarios;
    private DiagnosticoPaciente diagnostico;
    private PlanTratamiento planTratamiento;
    private SesionPaciente sesion;
    private List<SesionPaciente> sesiones;
    private ObraSocialPaciente obraSocialPaciente;
    private Telefono telefono;

    public Paciente() {
    }

    public Paciente(Integer dni) {
        this.dni = dni;
    }
    
    

    public Paciente(String nombre, Integer dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Paciente(String nombre, String apellido, int edad, Integer dni, Telefono telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
    }
    
    

    public Paciente(int id, String nombre, int edad, Integer dni, List<Telefono> telefono, boolean es_paciente, double honorarios, DiagnosticoPaciente diagnostico, PlanTratamiento planTratamiento, List<SesionPaciente> sesiones, ObraSocialPaciente obraSocialPaciente) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.listaTelefonos = listaTelefonos;
        this.es_paciente = es_paciente;
        this.honorarios = honorarios;
        this.diagnostico = diagnostico;
        this.planTratamiento = planTratamiento;
        this.sesiones = sesiones;
        this.obraSocialPaciente = obraSocialPaciente;
    }

    public Paciente(int id, String nombre, String apellido, int edad, Integer dni, Telefono telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
    }
    
    

    public String getApellido() {
        return apellido;
    }

    public Paciente setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Paciente setTelefono(Telefono telefono) {
        this.telefono = telefono;
        return this;
    }

    public SesionPaciente getSesion() {
        return sesion;
    }

    public Paciente setSesion(SesionPaciente sesion) {
        this.sesion = sesion;
        return this;
    }
    
    
    
    
    
    public int getId() {
        return id;
    }

    public Paciente setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Paciente setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public int getEdad() {
        return edad;
    }

    public Paciente setEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public Integer getDni() {
        return dni;
    }

    public Paciente setDni(Integer dni) {
        this.dni = dni;
        return this;
    }

    public List<Telefono> getTelefonos() {
        return listaTelefonos;
    }

    public Paciente setTelefonos(List<Telefono> telefonos) {
        this.listaTelefonos = telefonos;
        return this;
    }

    public boolean isEs_paciente() {
        return es_paciente;
    }

    public Paciente setEs_paciente(boolean es_paciente) {
        this.es_paciente = es_paciente;
        return this;
    }

    public double getHonorarios() {
        return honorarios;
    }

    public Paciente setHonorarios(double honorarios) {
        this.honorarios = honorarios;
        return this;
    }

    public DiagnosticoPaciente getDiagnostico() {
        return diagnostico;
    }

    public Paciente setDiagnostico(DiagnosticoPaciente diagnostico) {
        this.diagnostico = diagnostico;
        return this;
    }

    public PlanTratamiento getPlanTratamiento() {
        return planTratamiento;
    }

    public Paciente setPlanTratamiento(PlanTratamiento planTratamiento) {
        this.planTratamiento = planTratamiento;
        return this;
    }

    public List<SesionPaciente> getSesiones() {
        return sesiones;
    }

    public Paciente setSesiones(List<SesionPaciente> sesiones) {
        this.sesiones = sesiones;
        return this;
    }

    public ObraSocialPaciente getObraSocialPaciente() {
        return obraSocialPaciente;
    }

    public Paciente setObraSocialPaciente(ObraSocialPaciente obraSocialPaciente) {
        this.obraSocialPaciente = obraSocialPaciente;
        return this;
    }

   
    
    
}
