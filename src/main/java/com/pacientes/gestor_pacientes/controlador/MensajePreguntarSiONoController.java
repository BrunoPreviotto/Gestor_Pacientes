/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author previotto
 */
public class MensajePreguntarSiONoController implements Initializable {

    @FXML
    private Button botonSiMensajePreguntarSiONo;
    @FXML
    private Button botonNoMensajePreguntarSiONo;
    
    private Object menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void preguntarSiONo(Object obj){
        this.menu = obj;
    }

    @FXML
    private void salir(MouseEvent event) {
         Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void botonSeleccionado(MouseEvent event) {
        Button boton = (Button)event.getSource();
        switch (boton.getId()) {
            case "botonSiMensajePreguntarSiONo":
                VariablesEstaticas.esSiONoMensajePrguntarSiONo = true;
                break;
            case "botonNoMensajePreguntarSiONo":
                VariablesEstaticas.esSiONoMensajePrguntarSiONo = false;
                break;
            
        }
        salir(event);
    }
    
}
