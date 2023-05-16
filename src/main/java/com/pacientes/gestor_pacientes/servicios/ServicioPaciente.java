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
public class ServicioPaciente {

    public ServicioPaciente vaciarCajas(List<TextField> textfield) {
        for (TextField tf : textfield) {
            tf.setText("");
        }
        return this;
    }

    public ServicioPaciente vaciarCajasArea(List<TextArea> textArea) {
        for (TextArea ta : textArea) {
            ta.setText("");
        }
        return this;
    }

    public ServicioPaciente vaciarTablas(List<TableView> tableView) {
        for (TableView tv : tableView) {
            tv.getItems().clear();
        }
        return this;
    }

    public ServicioPaciente vaciarChoise(List<ChoiceBox> choiceBox) {
        for (ChoiceBox cb : choiceBox) {
            cb.setValue("");
        }
        return this;
    }

    public ServicioPaciente vaciarFechas(List<DatePicker> datePicker) {
        for (DatePicker dp : datePicker) {
            dp.setValue(LocalDate.now());
        }
        return this;
    }

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

    public ServicioPaciente rellenarListaSesiones(Paciente pacienteParametro) {
        /*
            1 FECHA SESION
            2 NUMERO SESION
            3 TRABAJO SESION
            4 OBSERVACION SESION
            5 MOTIVOS DE TRABAJO EMERGENTE
            6 NUMERO AUTORIZACION
            7 OBSERVACION AUTORIZACION
            8 ASOCIACION AUTORIZACION
            9 COPAGO AUTORIZACION
            10 CODIGO FACTURACION AUTORIZACION
         */
        VariablesEstaticas.valoresBUsquedaListaSesiones
                = Map.of(
                        "1", pacienteParametro.getSesion().getFecha().toString(),
                        "2", pacienteParametro.getSesion().getNumeroSesion().toString(),
                        "3", pacienteParametro.getSesion().getTrabajoSesion(),
                        "4", pacienteParametro.getSesion().getObservacion(),
                        "5", pacienteParametro.getSesion().getMotivoTrabajoEmergente(),
                        "6", pacienteParametro.getSesion().getAutorizacion().getNumeroAutorizacion().toString(),
                        "7", pacienteParametro.getSesion().getAutorizacion().getObservacion(),
                        "8", pacienteParametro.getSesion().getAutorizacion().getAsociacion().toString(),
                        "9", pacienteParametro.getSesion().getAutorizacion().getCopago().toString(),
                        "10", pacienteParametro.getSesion().getAutorizacion().getCodigoFacturacion().toString());
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
                        "3", String.valueOf(pacienteParametro.getObraSocialPaciente().getAfiliado()));
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

    public ServicioPaciente pintarCajaVaciaImportante(List<TextField> cajas) {
        for (TextField caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServicioPaciente pintarCajaAreaVaciaImportante(List<TextArea> cajas) {
        for (TextArea caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServicioPaciente pintarChoiseVacioImportante(List<ChoiceBox> choices) {
        for (ChoiceBox choice : choices) {
            if(Objects.nonNull (choice.getAccessibleHelp())){
               
                if (choice.getValue().equals("") && choice.getAccessibleHelp().equals("controlImportante")){
                    choice.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServicioPaciente datosPrincipalesVacios(){
        for (TextField c : VariablesEstaticas.cajasDatosPrincipales) {
            if(c.getText().isBlank()){
                switch (c.getId()) {
                case "cajaEdadDatosPrincipales":
                    c.setText("0");
                    break;
                
                case "cajaTelefonoDatosPrincipales":
                    c.setText("0000000000");
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
    
    public ServicioPaciente visivilizarCajas(List<TextField> listaTextField){
        
        for (TextField tf : listaTextField) {
            if(tf.getStyleClass().contains("cajasAEsconder")){
                tf.setVisible(true);
            }
        }
        return this;
    }
    
    public ServicioPaciente esconderCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            
            if(tf.getStyleClass().contains("cajasAEsconder")){
                tf.setVisible(false);
            }
        }
        return this;
    }
    
    public ServicioPaciente visivilizarChoice(List<ChoiceBox> choiceBox){
        for (ChoiceBox cb : choiceBox) {
            if(cb.getStyleClass().contains("cajasAEsconder")){
                cb.setVisible(true);
            }
        }
        return this;
    }
    
    public ServicioPaciente esconderChoice(List<ChoiceBox> choiceBox){
        for (ChoiceBox cb : choiceBox) {
            if(cb.getStyleClass().contains("cajasAEsconder")){
                cb.setVisible(false);
            }
        }
        return this;
    }
    
    public ServicioPaciente deshabilitarCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            //tf.setDisable(true);
            tf.editableProperty().set(false);
        }
        return this;
    }
    
    public ServicioPaciente habilitarCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            tf.editableProperty().set(true);
        }
        return this;
    }
    
    public ServicioPaciente deshabilitarCajasArea(List<TextArea> listaTextArea){
        for (TextArea ta : listaTextArea) {
            ta.editableProperty().set(false);
        }
        
        return this;
    }
    
    public ServicioPaciente habilitarCajasArea(List<TextArea> listaTextArea){
        for (TextArea ta : listaTextArea) {
            ta.editableProperty().set(true);
        }
        
        return this;
    }
    
     public void deshabilitarBotones(List<Button> botones){
        for (Button boton : botones) {
            boton.setDisable(true);
        }
    }
     
     public void habilitarBotones(List<Button> botones){
        for (Button boton : botones) {
            boton.setDisable(false);
        }
    }
    
    
    
    public static String CapitalCase(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split(" ");

        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                result.append(firstLetter).append(restOfWord).append(" ");
            }
        }

        return result.toString().trim();
    }
    
    
    public ServicioPaciente animarCajasAlDarABoton(List<TextField> listaCajas){
        for (TextField caja : listaCajas) {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), caja);
            st.setFromX(1);
            st.setToX(0.95);
            st.setCycleCount(2);
            st.setAutoReverse(true);
            st.play();
        }
        return this;
    }
    


   
    

}
