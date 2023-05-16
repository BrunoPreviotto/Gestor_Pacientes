/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author previotto
 */
public class EliminarSesionController {

    private Object menu;
    private Stage stage;
        
    @FXML
    private CheckBox checkBoxSesionEliminarSesion;
    @FXML
    private CheckBox checkBoxAutorizacionSesionElminarSesion;
    @FXML
    private Button BotonSiEliminarSesion;
    @FXML
    private Button BotonNoEliminarSesion;
    @FXML
    private Label etiquetaSeleccionarCasillaEliminarSesion;
    
    private Boolean eliminar;
    
    private BiMap<List<CheckBox>, Boolean> valoresElimenarSesion = HashBiMap.create();
    
    VariablesEstaticas variablesEstaticas = new VariablesEstaticas();
    
    
    public BiMap<List<CheckBox>, Boolean> mensajeEliminarSesion(Stage stage , Object obj){
        
        try {
            this.menu = obj;
            this.stage = stage;
        } catch (Exception ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return valoresElimenarSesion;
    }
    

    @FXML
    private void salir(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void confirmarEliminarONo(MouseEvent event) {
        Button boton = (Button) event.getSource();
        if(boton.getId().equals("BotonSiEliminarSesion")){
            eliminar = true;
        }else{
            eliminar = false;
        }
        List<CheckBox> check = Arrays.asList(checkBoxSesionEliminarSesion, checkBoxAutorizacionSesionElminarSesion);
        valoresElimenarSesion =   ImmutableBiMap.of(check, eliminar);
        variablesEstaticas.valoresElimenarSesion = valoresElimenarSesion;
        salir(event);
    }
    
}
