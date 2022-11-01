/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.servicios.InicializarObjeto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import com.pacientes.gestor_pacientes.validacion.ValidarRegistro;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author previotto
 */
public class RegistrarController extends PadreController implements Initializable {

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
    private Label etiquetaErrorNombre;
    @FXML
    private Label etiquetaErrorApellido;
    
    private  final String CHEQUEAR_DATOS = "Corrigir datos inválidos";
    private  final String NO_NUMEROS = "Ingresar solo texto";
    private  final String NOMBRE_EXEDIDO_CARACTERES = "El nombre debe ser menor a 50 caracteres";
    private  final String APELLIDO_EXEDIDO_CARACTERES = "El apellido debe ser menor a 50 caracteres";
    private  final String USUARIO_EXEDIDO_CARACTERES = "El usuario debe ser menor a 100 caracteres";
    private  final String CONTRASEÑA_EXEDIDO_CARACTERES = "La contraseña debe ser menor a 100 caracteres";
    private  final String CONTRASEÑA_NO_COINCIDEN = "Las contraseñas no coinciden";
    private  final String EMAIL_EXEDIDO_CARACTERES = "El email debe ser menor a 250 caracteres";
    private  final String EMAIL_NO_VALIDO = "El email tiene un formato no válido";
    
    private ValidarRegistro validar = new ValidarRegistro();
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
    
    UsuarioDAOImplementacion usuarioDao = new UsuarioDAOImplementacion();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void noNumerosNombre(KeyEvent event) {
        
        etiquetaErrorNombre.setText("");
        if(!Character.isAlphabetic(event.getCharacter().charAt(0))){
           cajaNombre.setText("");
           etiquetaErrorNombre.setText(NO_NUMEROS);
        }
    }

    
    @FXML
    private void noNumerosApellido(KeyEvent event) {
        
        etiquetaErrorApellido.setText("");
        if(!Character.isAlphabetic(event.getCharacter().charAt(0))){
           cajaApellido.setText("");
           etiquetaErrorApellido.setText(NO_NUMEROS);
        }
    }

   

    @FXML
    private void registrar(MouseEvent event) throws IOException {
       
        
       if(!validar.validarNombreApellido(cajaNombre.getText())){
           etiquetaErrorNombre.setText(NOMBRE_EXEDIDO_CARACTERES);
           
       }
       
       if(!validar.validarNombreApellido(cajaApellido.getText())){
           etiquetaErrorNombre.setText(APELLIDO_EXEDIDO_CARACTERES);
           
       }
       
       if(!validar.validarUsuarioContraseña(cajaUusario.getText())){
           etiquetaErrorusuario.setText(USUARIO_EXEDIDO_CARACTERES);
          
       }
       if(!validar.validarUsuarioContraseña(cajaContraseña.getText())){
           etiquetaErrorcontraseña.setText(CONTRASEÑA_EXEDIDO_CARACTERES);
          
       }
       
       if(!validar.validarUsuarioContraseña(cajaRepetirContraseña.getText())){
           etiquetaErrorRepetirContraseña.setText(CONTRASEÑA_EXEDIDO_CARACTERES);
       }
       
       if(!cajaContraseña.getText().equals(cajaRepetirContraseña.getText())){
           etiquetaErrorRepetirContraseña.setText(CONTRASEÑA_NO_COINCIDEN);
          
       }
       
       if(!validar.validarLongitudEmail(cajaEmail.getText())){
           etiquetaErrorRepetirContraseña.setText(EMAIL_EXEDIDO_CARACTERES);
       }
       
       if(!validar.validarEmail(cajaEmail.getText())){
           etiquetaErrorRepetirContraseña.setText(EMAIL_NO_VALIDO);
       }
       
       if(etiquetaErrorEmail.visibleProperty().get() && etiquetaErrorNombre.visibleProperty().get() && etiquetaErrorApellido.visibleProperty().get() && etiquetaErrorcontraseña.visibleProperty().get() && etiquetaErrorRepetirContraseña.visibleProperty().get()){
            usuarioDao.insertar(InicializarObjeto.inicializarUsuario(0, cajaNombre.getText().trim(), cajaApellido.getText().trim(), cajaUusario.getText().trim(), cajaContraseña.getText().trim(), cajaEmail.getPromptText().trim(), true, 2));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Scene scene = new Scene(loadFXML("MenuInicio"));
            Stage newStage = new Stage();
            scene.setFill(Color.TRANSPARENT);
            newStage.setScene(scene);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.show();
       }else{
           etiquetaError.setText(CHEQUEAR_DATOS);
       }
       
       
       
    }

   
    
   

    

    
    
    
    
}
