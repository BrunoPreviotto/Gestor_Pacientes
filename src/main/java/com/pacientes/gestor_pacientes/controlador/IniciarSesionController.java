/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.pacientes.gestor_pacientes.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import com.pacientes.gestor_pacientes.DAO.IUsuarioDAO;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author previotto
 */
public class IniciarSesionController extends PadreController implements Initializable, DraggedScene {

    @FXML
    private Button botonInicioSesion;
    
    @FXML
    private PasswordField cajaContraseña;
    @FXML
    private Button botonInicioSesion1;
    @FXML
    private Label etiquetaError;
    @FXML
    private AnchorPane container;
    @FXML
    private TextField cajaUsuario;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.onDraggedScene(container);
    }    

    
    
   
    @FXML
    private void peticionParaIngresar(MouseEvent event) throws IOException{
        if(!cajaUsuario.getText().equals("") || !cajaContraseña.getText().equals("")){
            IUsuarioDAO usuario = new UsuarioDAOImplementacion();
            ArrayList<String> us = new ArrayList();
            us.add(cajaUsuario.getText());
            us.add(cajaContraseña.getText());

            if (!usuario.obtener(us.get(0), us.get(1)).isEmpty()) {
                etiquetaError.setVisible(false);
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Scene scene = new Scene(loadFXML("MenuInicio"));
                Stage newStage = new Stage();
                scene.setFill(Color.TRANSPARENT);
                newStage.setScene(scene);

                newStage.initStyle(StageStyle.TRANSPARENT);
                newStage.show();
            
            }
        }else {
            etiquetaError.setVisible(true);
        }
    }
    
    

    @FXML
    private void dragged(MouseEvent event) {
        
    }

    @FXML
    private void Registrarse(MouseEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Scene scene = new Scene(loadFXML("Registrar"));
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.show();
    }
    
}
