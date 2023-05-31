/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author previotto
 */
public class ServiciosPadre {
    
    public ServiciosPadre vaciarCajas(List<TextField> textfield) {
        for (TextField tf : textfield) {
            tf.setText("");
        }
        return this;
    }

    public ServiciosPadre vaciarCajasArea(List<TextArea> textArea) {
        for (TextArea ta : textArea) {
            ta.setText("");
        }
        return this;
    }

    public ServiciosPadre vaciarTablas(List<TableView> tableView) {
        for (TableView tv : tableView) {
            tv.getItems().clear();
        }
        return this;
    }

    public ServiciosPadre vaciarValorChoise(List<ChoiceBox> choiceBox) {
        for (ChoiceBox cb : choiceBox) {
            cb.setValue("");
        }
        return this;
    }
    
    public ServiciosPadre vaciarChoise(List<ChoiceBox> choiceBox) {
        for (ChoiceBox cb : choiceBox) {
            cb.getItems().setAll("");
        }
        return this;
    }
    

    public ServiciosPadre vaciarFechas(List<DatePicker> datePicker) {
        for (DatePicker dp : datePicker) {
            dp.setValue(LocalDate.now());
        }
        return this;
    }
    
    
    public ServiciosPadre pintarCajaVaciaImportante(List<TextField> cajas) {
        for (TextField caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServiciosPadre pintarCajaAreaVaciaImportante(List<TextArea> cajas) {
        for (TextArea caja : cajas) {
            
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    
    
    public ServiciosPadre pintarChoiseVacioImportante(List<ChoiceBox> choices) {
        for (ChoiceBox choice : choices) {
            if(Objects.nonNull (choice.getAccessibleHelp())){
               
                if (choice.getValue().equals("") && choice.getAccessibleHelp().equals("controlImportante")){
                    choice.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    
    public ServiciosPadre desPintarCajaVaciaImportante(List<TextField> cajas) {
        for (TextField caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().remove("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServiciosPadre desPintarCajaAreaVaciaImportante(List<TextArea> cajas) {
        for (TextArea caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().remove("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    
    
    public ServiciosPadre desPintarChoiseVacioImportante(List<ChoiceBox> choices) {
        for (ChoiceBox choice : choices) {
            if(Objects.nonNull (choice.getAccessibleHelp())){
               
                if (choice.getValue().equals("") && choice.getAccessibleHelp().equals("controlImportante")){
                    choice.getStyleClass().remove("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    
    
    public ServiciosPadre visivilizarCajas(List<TextField> listaTextField){
        
        for (TextField tf : listaTextField) {
            if(tf.getStyleClass().contains("cajasAEsconder")){
                tf.setVisible(true);
            }
        }
        return this;
    }
    
    public ServiciosPadre esconderCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            
            if(tf.getStyleClass().contains("cajasAEsconder")){
                tf.setVisible(false);
            }
        }
        return this;
    }
    
    public ServiciosPadre visivilizarChoice(List<ChoiceBox> choiceBox){
        for (ChoiceBox cb : choiceBox) {
            if(cb.getStyleClass().contains("cajasAEsconder")){
                cb.setVisible(true);
            }
        }
        return this;
    }
    
    public ServiciosPadre esconderChoice(List<ChoiceBox> choiceBox){
        for (ChoiceBox cb : choiceBox) {
            if(cb.getStyleClass().contains("cajasAEsconder")){
                cb.setVisible(false);
            }
        }
        return this;
    }
    
    public ServiciosPadre deshabilitarCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            //tf.setDisable(true);
            tf.editableProperty().set(false);
        }
        return this;
    }
    
    public ServiciosPadre habilitarCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            tf.editableProperty().set(true);
        }
        return this;
    }
    
    public ServiciosPadre deshabilitarCajasArea(List<TextArea> listaTextArea){
        for (TextArea ta : listaTextArea) {
            ta.editableProperty().set(false);
        }
        
        return this;
    }
    
    public ServiciosPadre habilitarCajasArea(List<TextArea> listaTextArea){
        for (TextArea ta : listaTextArea) {
            ta.editableProperty().set(true);
        }
        
        return this;
    }
    
     public ServiciosPadre deshabilitarBotones(List<Button> botones){
        for (Button boton : botones) {
            boton.setDisable(true);
        }
        return this;
    }
     
     public ServiciosPadre habilitarBotones(List<Button> botones){
        for (Button boton : botones) {
            boton.setDisable(false);
        }
        return this;
    }
     
     public ServiciosPadre habilitarBotonCrear(Button boton){
        boton.setDisable(false);
        return this;
    }
     
     public ServiciosPadre desHabilitarBotonCrear(Button boton){
        boton.setDisable(true);
        return this;
    }
     
     public ServiciosPadre habilitarEliminarActualizar(Button eliminar, Button actualizar){
        actualizar.setDisable(false);
        eliminar.setDisable(false);
        return this;
    }
     
     public ServiciosPadre desHabilitarEliminarActualizar(Button eliminar, Button actualizar){
        actualizar.setDisable(true);
        eliminar.setDisable(true);
        return this;
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
    
    
    public ServiciosPadre animarCajasAlDarABoton(List<TextField> listaCajas){
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
    
     public ServiciosPadre animarCajasAreaAlDarABoton(List<TextArea> listaCajas){
        for (TextArea caja : listaCajas) {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), caja);
            st.setFromX(1);
            st.setToX(0.95);
            st.setCycleCount(2);
            st.setAutoReverse(true);
            st.play();
        }
        return this;
    }
    
    public ServiciosPadre animarChoiceAlDarABoton(List<ChoiceBox> listaChoiceBox){
        for (ChoiceBox choice : listaChoiceBox) {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), choice);
            st.setFromX(1);
            st.setToX(0.95);
            st.setCycleCount(2);
            st.setAutoReverse(true);
            st.play();
        }
        return this;
    }
    
    
    
}
