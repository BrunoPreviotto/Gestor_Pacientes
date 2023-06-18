/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;


import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Email;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.servicios.InicializarObjeto;
import com.pacientes.gestor_pacientes.servicios.ServicioRegistrar;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import com.pacientes.gestor_pacientes.validacion.Validar;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author previotto
 */
public class RegistrarController extends ClasePadreController implements Initializable {

    @FXML
    private TextField cajaNombre;
    @FXML
    private TextField cajaApellido;
    @FXML
    private PasswordField cajaContraseña;
    @FXML
    private PasswordField cajaRepetirContraseña;
    @FXML
    private TextField cajaEmail;
    @FXML
    private Label etiquetaError;
    @FXML
    private TextField cajaUusario;
    @FXML
    private Label etiquetaErrorApellido;
    @FXML
    private Label etiquetaErrorusuario;
    @FXML
    private Label etiquetaErrorcontraseña;
    @FXML
    private Label etiquetaErrorRepetirContraseña;
    @FXML
    private Label etiquetaErrorEmail;
    @FXML
    private Label reg;
    @FXML
    private Label etiquetaErrorNombre;
    
    
    private Validar validar = new Validar();
    private UsuarioDAOImplementacion usuarioDao = new UsuarioDAOImplementacion();
    private ServicioRegistrar servicioRegistrar = new ServicioRegistrar();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VariablesEstaticas.cajasRegistrar = 
                Arrays.asList(
                        cajaNombre, 
                        cajaApellido,
                        cajaUusario,
                        cajaContraseña,
                        cajaRepetirContraseña,
                        cajaEmail);
    }    

    /**
     * No permiten el ingreso de numeros
     * @param event 
     */
    

   
    /**
     * Permite regitrar a un usuario
     * @param event
     * @throws IOException 
     */
    @FXML
    private void registrar(MouseEvent event) throws IOException {

        if (!cajaNombre.getText().isBlank()
                || !cajaApellido.getText().isBlank()
                || !cajaUusario.getText().isBlank()
                || !cajaContraseña.getText().isBlank()
                || !cajaRepetirContraseña.getText().isBlank()) {
            
            if(cajaEmail.getText().isBlank()){
                cajaEmail.setText("Sin email");
            }
            
            if (cajaContraseña.getText().equals(cajaRepetirContraseña.getText())) {
                Usuario usuarioCrear = new Usuario(
                        cajaNombre.getText().trim(), 
                        cajaApellido.getText().trim(), 
                        cajaUusario.getText().trim(), 
                        cajaContraseña.getText().trim(), 
                        new Email(cajaEmail.getText().trim()), 
                        true, 
                        true);
                
                try {
                    usuarioDao.insertar(usuarioCrear);
                } catch (Exception e) {
                }
                
                
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Scene scene = new Scene(loadFXML("MenuInicio"));
                Stage newStage = new Stage();
                scene.setFill(Color.TRANSPARENT);
                newStage.setScene(scene);
                newStage.initStyle(StageStyle.TRANSPARENT);
                newStage.show();
            }else{
                mensajeAdvertenciaError("Las contraseñas no coinciden",  this, VariablesEstaticas.imgenAdvertencia);
            }

        }else{
            mensajeAdvertenciaError("Hay campos vacios",  this, VariablesEstaticas.imgenAdvertencia);
            servicioRegistrar.pintarCajaVaciaImportante(VariablesEstaticas.cajasRegistrar);
        }


    }
    
    
}
