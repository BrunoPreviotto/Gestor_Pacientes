/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.lang.ModuleLayer.Controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author previotto
 */
public class ServicioPaciente extends ServiciosPadre {

    

    public ServicioPaciente vaciarTodo() {
        vaciarCajas(VariablesEstaticas.cajasDatosPrincipales).
                vaciarCajas(VariablesEstaticas.cajasSesiones).
                vaciarCajas(VariablesEstaticas.cajasPlanes).
                vaciarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                vaciarCajasArea(VariablesEstaticas.cajasAreaSesion).
                vaciarCajasArea(VariablesEstaticas.cajasAreaPlan).
                vaciarCajasArea(VariablesEstaticas.cajasAreaDiagnostico).
                vaciarTablas(VariablesEstaticas.tableSesiones).
                vaciarChoise(VariablesEstaticas.choiseSesiones).
                vaciarChoise(VariablesEstaticas.choisePlan).
                vaciarChoise(VariablesEstaticas.choiseObraSocialPaciente).
                vaciarFechas(VariablesEstaticas.datePickerSesiones);
        
        return this;

    }
    
   

    public ServicioPaciente rellenarListaDatosPrincipales(Paciente pacienteParametro) {
        /*
            1 NOMBRE
            2 APELLIDO
            3 EDAD
            4 DNI
            5 TELEFONO
         */

        VariablesEstaticas.valoresBUsquedaDatosPrincipales
                = Map.of(
                        "1", pacienteParametro.getNombre(),
                        "2", pacienteParametro.getApellido(),
                        "3", String.valueOf(pacienteParametro.getEdad()),
                        "4", String.valueOf(pacienteParametro.getDni()),
                        "5", String.valueOf(pacienteParametro.getTelefono()));
        return this;
    }
    
    public ServicioPaciente vaciarListaDatosPrincipales() {
        /*
            1 NOMBRE
            2 APELLIDO
            3 EDAD
            4 DNI
            5 TELEFONO
         */

        VariablesEstaticas.valoresBUsquedaDatosPrincipales
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "");
        return this;
    }

    public ServicioPaciente rellenarListaSesiones(Paciente pacienteParametro) {
        /*
            1 FECHA SESION
            2 NUMERO SESION
            3 TRABAJO SESION
            4 OBSERVACION SESION
            5 HONORARIOS POR SESION
            6 ESTADO DE FACTURACION
            
         */
        VariablesEstaticas.valoresBUsquedaListaSesiones
                = Map.of(
                        "1", pacienteParametro.getSesion().getFecha().toString(),
                        "2", pacienteParametro.getSesion().getNumeroSesion().toString(),
                        "3", pacienteParametro.getSesion().getTrabajoSesion(),
                        "4", pacienteParametro.getSesion().getObservacion(),
                        "5", pacienteParametro.getSesion().getHonorarioPorSesion().toString(),
                        "6", pacienteParametro.getSesion().getEstado().getEstado());
        return this;
    }
    
    
    
    public ServicioPaciente rellenarListaSesionesAutorizaciones(Paciente pacienteParametro) {
        /*
            1 NUMERO AUTORIZACION
            2 OBSERVACION AUTORIZACION
            3 ASOCIACION AUTORIZACION
            4 COPAGO AUTORIZACION
            5 CODIGO FACTURACION AUTORIZACION
        */
        VariablesEstaticas.valoresBUsquedaListaSesionesAutorizacion
                = Map.of(
                        "1", pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion().toString(),
                        "2", pacienteParametro.getSesion().getAutorizacion().getObservacion(),
                        "3", pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString(),
                        "4", pacienteParametro.getSesion().getAutorizacion().getCopago().toString(),
                        "5", pacienteParametro.getSesion().getAutorizacion().getCodigoFacturacion().toString());
        return this;
    }
    
    public ServicioPaciente vaciarListaSesiones() {
        /*
            1 NUMERO AUTORIZACION
            2 OBSERVACION AUTORIZACION
            3 ASOCIACION AUTORIZACION
            4 COPAGO AUTORIZACION
            5 CODIGO FACTURACION AUTORIZACION
        */
        VariablesEstaticas.valoresBUsquedaListaSesiones
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "",
                        "6", "");
        return this;
    }
    
    
    
    public ServicioPaciente vaciarListaSesionesAutorizacion() {
        /*
            1 NUMERO AUTORIZACION
            2 OBSERVACION AUTORIZACION
            3 ASOCIACION AUTORIZACION
            4 COPAGO AUTORIZACION
            5 CODIGO FACTURACION AUTORIZACION
        */
        VariablesEstaticas.valoresBUsquedaListaSesionesAutorizacion
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "");
        return this;
    }
    
    public ServicioPaciente habilitarTodo(){
        habilitarCajas(VariablesEstaticas.cajasDatosPrincipales).
                habilitarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                habilitarCajas(VariablesEstaticas.cajasPlanes).
                habilitarCajas(VariablesEstaticas.cajasSesiones).
                habilitarCajasArea(VariablesEstaticas.cajasAreaDiagnostico).
                habilitarCajasArea(VariablesEstaticas.cajasAreaPlan).
                habilitarCajasArea(VariablesEstaticas.cajasAreaSesion);
        return this;
    }
    
    public ServicioPaciente rellenarListaPlan(Paciente pacienteParametro) {
        /*
            1 FRECUENCIA SESIONES PLAN
            2 TIPO SESION PLAN
            3 DESCRIPCION PLAN
            4 ESTRATEGIA PLAN
         */
        VariablesEstaticas.valoresBUsquedaPlanes
                = Map.of(
                        "1", pacienteParametro.getPlanTratamiento().getFrecuenciaSesion(),
                        "2", pacienteParametro.getPlanTratamiento().getTipoSEsion().getNombre(),
                        "3", pacienteParametro.getPlanTratamiento().getTipoSEsion().getDecripcion(),
                        "4", pacienteParametro.getPlanTratamiento().getEstrategia());
        return this;
    }
    
    

    public ServicioPaciente vaciarListaPlan() {
        /*
            1 FRECUENCIA SESIONES PLAN
            2 TIPO SESION PLAN
            3 DESCRIPCION PLAN
            4 ESTRATEGIA PLAN
         */
        VariablesEstaticas.valoresBUsquedaPlanes
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "");
        return this;
    }

    public ServicioPaciente rellenarListaDiagnostico(Paciente pacienteParametro) {
        /*
            1 DIAGNOSTICO DIAGNOSTICO
            2 OBSERVACION DIAGNOSTICO
         */
        VariablesEstaticas.valoresBUsquedaDiagnostico
                = Map.of(
                        "1", pacienteParametro.getDiagnostico().getDiagnostico(),
                        "2", pacienteParametro.getDiagnostico().getObservacion());
        return this;
    }
    
     public ServicioPaciente vaciarListaDiagnostico() {
        /*
            1 DIAGNOSTICO DIAGNOSTICO
            2 OBSERVACION DIAGNOSTICO
         */
        VariablesEstaticas.valoresBUsquedaDiagnostico
                = Map.of(
                        "1", "",
                        "2", "");
        return this;
    }

    public ServicioPaciente rellenarListaObrasocialPaciente(Paciente pacienteParametro) {
        /*
            1 NOMBRE OBRA SOCIAL PACIENTE
            2 PLAN OBRA SOCIAL PACIENTE
            3 Nº AFILIADO OBRA SOCIAL PACIENTE
         */
        VariablesEstaticas.valoresBUsquedaObraSocialPaciente
                = Map.of(
                        "1", pacienteParametro.getObraSocialPaciente().getNombre(),
                        "2", pacienteParametro.getObraSocialPaciente().getPlan().getNombre(),
                        "3", String.valueOf(pacienteParametro.getObraSocialPaciente().getAfiliado().getNumero()));
        return this;
    }
    
    public ServicioPaciente vaciarListaObrasocialPaciente() {
        /*
            1 NOMBRE OBRA SOCIAL PACIENTE
            2 PLAN OBRA SOCIAL PACIENTE
            3 Nº AFILIADO OBRA SOCIAL PACIENTE
         */
        VariablesEstaticas.valoresBUsquedaObraSocialPaciente
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "");
        return this;
    }
    
     public ServicioPaciente vaciarListas() {
        vaciarListaDatosPrincipales().
                 vaciarListaSesiones().
                 vaciarListaDiagnostico().
                 vaciarListaPlan().
                 vaciarListaObrasocialPaciente();
        return this;
    }
    
    public ServicioPaciente comprobarTamañoStringDatosPrincipales(){
        for (TextField c : VariablesEstaticas.cajasDatosPrincipales) {
            switch (c.getId()) {
                case "cajaNombreDatosPrincipales":
                    
                    break;
                case "cajaApellidoDatosPrincipales":
                    
                    break;
                case "cajaEdadDatosPrincipales":
                    if(Integer.parseInt(c.getText()) > 150){
                        c.setText("150");
                    }
                    break;
                case "cajaDniDatosPrincipales":
                    
                    break;
                case "cajaTelefonoDatosPrincipales":
                    
                    break;
                
            }
        }
        return this;
    }

    
    
    public ServicioPaciente datosPrincipalesVacios() {
        for (TextField c : VariablesEstaticas.cajasDatosPrincipales) {
            if (c.getText().isBlank()) {
                switch (c.getId()) {
                    case "cajaEdadDatosPrincipales":
                        c.setText("0");
                        break;

                    case "cajaTelefonoDatosPrincipales":
                        c.setText("0000000000");
                        break;
                    case "cajaHonorariosDatosPrincipales":
                        c.setText("0");
                        break;

                }
            }
        }
        return this;
    }
    
    public ServicioPaciente datosSesion() {
        for (TextField c : VariablesEstaticas.cajasSesiones) {
            if (c.getText().isBlank()) {
                switch (c.getId()) {
                    case "cajaObservacionSesion":
                        c.setText("-----------");
                        break;

                    case "cajaMotivoTrabajoEmergenteSesion":
                        c.setText("-----------");
                        break;
                    case "cajaEstadoFacturacionSesionObraSocial":
                        c.setText("sin estado");
                        break;
                }
            }
        }
        return this;
    }
    
    public ServicioPaciente datosSesionAutorizacion() {
        for (TextField c : VariablesEstaticas.cajasSesiones) {
            if (c.getText().isBlank()) {
                switch (c.getId()) {
                    case "cajaObservacionSesionObraSocial":
                        c.setText("-----------");
                        break;

                    case "cajaCopagoSesionObraSocial":
                        c.setText("0");
                        break;
                }
            }
        }
        return this;
    }
    
    
    
    
    public ServicioPaciente datosPlanVacios(){
        for (TextField c : VariablesEstaticas.cajasPlanes) {
            if(c.getText().isBlank()){
                switch (c.getId()) {
                case "cajaEstrategiaPlan":
                    c.setText("-----------");
                    break;
                
            }
            }
        }
        return this;
    }
    
    
    public ServicioPaciente datosDiagnosticoVacios(){
        for (TextArea c : VariablesEstaticas.cajasAreaDiagnostico) {
            if(c.getText().isBlank()){
                switch (c.getId()) {
                case "cajaObservacionDiagnostico":
                    c.setText("-------------------");
                    break;
                
               
            }
            }
        }
        return this;
    }
    
    public ServicioPaciente datosSesionVacios(){
        for (TextArea c : VariablesEstaticas.cajasAreaSesion) {
            if(c.getText().isBlank()){
                switch (c.getId()) {
                case "cajaObservacionSesion":
                    c.setText("-------------------");
                    break;
                
                case "cajaMotivoTrabajoEmergenteSesion":
                    c.setText("-------------------");
                    break;
               
            }
            }
        }
        return this;
    }
    
    public ServicioPaciente datosAutorizacionSesionVacios(){
        for (TextArea c : VariablesEstaticas.cajasAreaSesion) {
            if (c.getText().isBlank()) {
                switch (c.getId()) {
                    case "cajaObservacionSesionObraSocial":
                        c.setText("-------------------");
                        break;
                }
            }
        }
        
        
        
        for (TextField caja : VariablesEstaticas.cajasSesiones) {
            if (caja.getText().isBlank()) {
                switch (caja.getId()) {
                    case "cajaObservacionSesionObraSocial":
                        caja.setText("0");
                        break;
                }
            }
        }
        return this;
    }
    
    
    


   
    

}
