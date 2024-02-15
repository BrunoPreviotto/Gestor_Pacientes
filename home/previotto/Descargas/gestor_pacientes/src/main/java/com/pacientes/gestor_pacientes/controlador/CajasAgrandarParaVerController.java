/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;


import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author previotto
 */
public class CajasAgrandarParaVerController extends ClasePadreController implements Initializable, DraggedScene {


    @FXML
    private TextArea cajaVer;
    @FXML
    private Button botonMaximizarDesmaximizado;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void maximizarMenuInicio(MouseEvent event) {
    }

    public void llenarCaja(String texto){
        cajaVer.setText(texto);
    }
    
    @FXML
    protected void salir(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }

}
