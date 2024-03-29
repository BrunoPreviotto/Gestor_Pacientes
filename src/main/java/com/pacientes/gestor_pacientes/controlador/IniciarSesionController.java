/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.servicios.ServiciosPadre;



import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.io.IOException;
import javafx.event.Event;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author previotto
 */
public class IniciarSesionController extends ClasePadreController implements Initializable, DraggedScene {

    @FXML
    private Button botonInicioSesion;
    
    @FXML
    private PasswordField cajaContraseña;
    @FXML
    private Button botonInicioSesion1;
    @FXML
    private Label etiquetaError;
    @FXML
    private AnchorPane containerIniciar;
    @FXML
    private TextField cajaUsuario;
    
    private Usuario usuario;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiciosPadre serviciosPadre = new ServiciosPadre();
        containerIniciar.setStyle(serviciosPadre.iniciarColorApp());
           
        VariablesEstaticas.setImgenExito("/com/pacientes/gestor_pacientes/img/exito.png");
        VariablesEstaticas.setImgenError("/com/pacientes/gestor_pacientes/img/error.png");
        VariablesEstaticas.setImgenAdvertencia("/com/pacientes/gestor_pacientes/img/warning.png");
        
        VariablesEstaticas.setImagenVer(new Image("/com/pacientes/gestor_pacientes/img/ver.png"));
        VariablesEstaticas.setImagenAgregar(new Image("/com/pacientes/gestor_pacientes/img/lapiz.png"));
        VariablesEstaticas.setImagenRecordar(new Image("/com/pacientes/gestor_pacientes/img/recordatorio.png"));
        // TODO
        this.onDraggedScene( containerIniciar );
    }    

    
    
   /**
    * permite el ingreso a la aplicacion si es que el usuario esta registrado
    * @param event
    * @throws IOException 
    */
    @FXML
    private void peticionParaIngresar(Event event) throws IOException{
        UsuarioDAOImplementacion usuarioDao = new UsuarioDAOImplementacion();
        
        try {
            if(!cajaUsuario.getText().equals("") || !cajaContraseña.getText().equals("")){
            
            usuario = new Usuario();
            usuario.setUsuario(cajaUsuario.getText());
            usuario.setContraseña(cajaContraseña.getText());
              

            if (!usuarioDao.obtener(usuario).getUsuario().equals("")) {
                usuarioDao.abrirSesion(usuario);
                etiquetaError.setVisible(false);
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Scene scene = new Scene(loadFXML("MenuInicio"));
                Stage newStage = new Stage();
                scene.setFill(Color.TRANSPARENT);
                newStage.setScene(scene);

                newStage.initStyle(StageStyle.TRANSPARENT);
                newStage.getIcons().add(imagenIocono);
                newStage.show();
                
            
                
            }else{
                mensajeAdvertenciaError ("Usuario no encontrado", this, VariablesEstaticas.imgenError);
            }
        }else {
                mensajeAdvertenciaError ("Hay campos vacios", this, VariablesEstaticas.imgenAdvertencia);
            //etiquetaError.setVisible(true);
        }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al iniciar sesión", this, VariablesEstaticas.imgenError);
        }
    }
    
   
    

    @FXML
    private void dragged(MouseEvent event) {
        
    }

    /**
     * Si se aprieta el boton registrar envia a la ventana de registrar
     * @param event
     * @throws IOException 
     */
    @FXML
    private void Registrarse(MouseEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Scene scene = new Scene(loadFXML("Registrar"));
        scene.setFill(Color.TRANSPARENT);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.getIcons().add(imagenIocono);
        newStage.show();
    }

    
    @FXML
    protected void alDarEnterBoton(KeyEvent event){
    Node node = (Node)event.getSource();
        if (event.getCode() == KeyCode.ENTER) {
            
            if(node.getId().equals("cajaUsuario") || node.getId().equals("cajaContraseña") || node.getId().equals("container")){
                try {
                    peticionParaIngresar(event);
                } catch (Exception e) {
                    System.out.println("ola");
                }
            }
            
        }
    }
    

    
    
}
