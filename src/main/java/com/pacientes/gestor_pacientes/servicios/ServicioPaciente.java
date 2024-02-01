/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;

import com.pacientes.gestor_pacientes.modelo.Paciente;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;


/**
 *
 * @author previotto
 */
public class ServicioPaciente extends ServiciosPadre {

   public ServicioPaciente ocultarLIstVBox(List<VBox> listaVBox){
        for (VBox vBox : listaVBox) {
            vBox.setVisible(false);
        }
        return this;
        
    }
    
    public ServicioPaciente visibilizarLIstVBox(List<VBox> listaVBox){
        for (VBox vBox : listaVBox) {
            vBox.setVisible(true);
        }
       return this;
    }
    

    public ServicioPaciente vaciarTodo() {
        vaciarCajas(VariablesEstaticas.cajasDatosPrincipales).
               
                vaciarCajas(VariablesEstaticas.cajasPlanes).
                vaciarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                
                vaciarCajasArea(VariablesEstaticas.cajasAreaPlan).
                vaciarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico).
                vaciarTablas(VariablesEstaticas.tableSesiones).
                
                vaciarChoise(VariablesEstaticas.choisePlan).
                vaciarChoise(VariablesEstaticas.choiseObraSocialPaciente);
        
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
    
    
    
    public ServicioPaciente rellenarListaSesionesAutorizaciones(AutorizacionesSesionesObraSociales autorizacion) {
        /*
            1 NUMERO AUTORIZACION
            2 OBSERVACION AUTORIZACION
            3 ASOCIACION AUTORIZACION
            4 COPAGO AUTORIZACION
            5 CODIGO FACTURACION AUTORIZACION
        */
        VariablesEstaticas.valoresBUsquedaListaSesionesAutorizacion
                = Map.of(
                        "1", autorizacion.getNumeroAutorizacion().toString(),
                        "2", autorizacion.getObservacion(),
                        "3", autorizacion.getAsociacion().toString(),
                        "4", autorizacion.getCopago().toString(),
                        "5", autorizacion.getCodigoFacturacion().getNombre());
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
          
                habilitarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico).
                habilitarCajasArea(VariablesEstaticas.cajasAreaPlan);
          
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
                        "1", pacienteParametro.getPlanTratamiento().getFrecuenciaSesion().getFrecuencia(),
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

    public ServicioPaciente datosSesionChoiceVacios() {
        for (ChoiceBox c : VariablesEstaticas.choiseSesiones) {
            if (Objects.nonNull(c.getValue())) {
                switch (c.getId()) {
                    case "choiseCodigoFactSesionObraSocial":
                        c.setValue("sin codigo");
                        break;
                }
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
        for (HTMLEditor c : VariablesEstaticas.cajasAreaDiagnostico) {
            if(c.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") || c.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")){
                switch (c.getId()) {
                case "cajaObservacionDiagnostico":
                    c.setHtmlText("-------------------");
                    break;
                
               
            }
            }
        }
        return this;
    }
    
    public ServicioPaciente datosSesionCajasAreaVacios() {
        for (HTMLEditor c : VariablesEstaticas.cajasAreaSesion) {
            if (c.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") 
                    || c.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
                switch (c.getId()) {
                    case "cajaObservacionSesion":
                        c.setHtmlText("-------------------");
                        break;

                    case "cajaMotivoTrabajoEmergenteSesion":
                        c.setHtmlText("-------------------");
                        break;

                }
            }

        }
        return this;
    }
    
    
    public ServicioPaciente datosSesionCajasVacios(){
        for (TextField c : VariablesEstaticas.cajasSesiones) {
            if(c.getText().isBlank()){
                switch (c.getId()) {
                case "cajaEstadoFacturacionSesionObraSocial":
                    c.setText("Sin estado");
                    break;
                case "cajaHonorariosPorSesion":
                    c.setText("0.0");
                    break;
               
            }
            }
        }
        return this;
    }
    
    public ServicioPaciente datosAutorizacionSesionVacios(){
        for (HTMLEditor c : VariablesEstaticas.cajasAreaSesion) {
            if (c.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") || c.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
                switch (c.getId()) {
                    case "cajaObservacionSesionObraSocial":
                        c.setHtmlText("-------------------");
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
    
    
    
    public ServicioPaciente comprobarSiAcordeonEstaCerrado(List<TitledPane> listaTitled){
        int verdadero = 0;
        for (TitledPane titledPane : listaTitled) {
            
            
            if(titledPane.expandedProperty().getValue()){
                verdadero++;
                break;
            }
        }
        if(verdadero == 0){
            VariablesEstaticas.tabDatosPricipales.setExpanded(true);
        }
        return this;
    }

    public void rellenarCajasAutorizacionVacias(){
        
                                            
        if(VariablesEstaticas.cajaAutorizacionSesion.getText().isBlank()){
            VariablesEstaticas.cajaAutorizacionSesion.setText("0000");
            
        }
        
        if(VariablesEstaticas.cajaObservacionSesionObraSocial.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") 
                || VariablesEstaticas.cajaObservacionSesionObraSocial.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") 
                ||  VariablesEstaticas.cajaObservacionSesionObraSocial.getHtmlText().equals("")){
            
        VariablesEstaticas.cajaObservacionSesionObraSocial.setHtmlText("----------");
        
        }
        if(VariablesEstaticas.cajaCopagoSesionObraSocial.getText().isBlank()){
            VariablesEstaticas.cajaCopagoSesionObraSocial.setText("0.0");
            
        }
        if(Objects.isNull(VariablesEstaticas.choiseCodigoFactSesionObraSocial.getValue())){
            VariablesEstaticas.choiseCodigoFactSesionObraSocial.setValue("sin codigo");
            
        }
    }
   

}
