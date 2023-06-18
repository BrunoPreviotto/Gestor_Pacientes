/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author previotto
 */
public class MensajeAdvertenciaController extends ClasePadreController implements Initializable {
    
    private Object menu;
    private Stage stage;
    
    
    @FXML
    private Label etiquetaMensaje;
    @FXML
    private ImageView imgMensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    public void mensajeAdvertencia(String mensaje, Stage stage , Object obj, String imagen){
       
        Image img = new Image(getClass().getResourceAsStream(imagen));
        try {
            etiquetaMensaje.setText(mensaje);
            
            imgMensaje.setImage(img);
            
          
            this.menu = obj;
            this.stage = stage;
        } catch (Exception ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        

    
    @FXML
    protected void salir(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    
    
   

}