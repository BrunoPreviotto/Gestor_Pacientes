/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.Honorario;
import com.pacientes.gestor_pacientes.modelo.ObraSocialPaciente;
import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.SesionPaciente;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * @author previotto
 */
public class TablaDatosPrincipales implements Tablas{
    private String id;
    private String nombre;
    private String apellido;
    private String edad;
    private String dni;
    private String telefono;
    private String honorarios;
    //private DiagnosticoPaciente diagnostico;
    //private PlanTratamiento planTratamiento;
    //private SesionPaciente sesion;
    //private List<SesionPaciente> sesiones;
    //private ObraSocialPaciente obraSocialPaciente;
    //private Telefono telefono;

    public TablaDatosPrincipales(String nombre, String apellido, String edad, String dni, String telefono, String honorarios) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
        this.honorarios = honorarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(String honorarios) {
        this.honorarios = honorarios;
    }

    @Override
    public Map<Integer, String> obtenerPropiedades() {
        return Map.of(1,"Nombre",
                2, "Apellido",
                3, "Edad",
                4, "DNI",
                5, "Telefono",
                6, "Honorarios");
    }
    
    
}
