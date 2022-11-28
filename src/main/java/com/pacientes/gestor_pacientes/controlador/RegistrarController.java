/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;


import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.servicios.InicializarObjeto;
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
       
        validarRegistro();
        
        if (etiquetaErrorEmail.visibleProperty().get() && etiquetaErrorNombre.visibleProperty().get() && etiquetaErrorApellido.visibleProperty().get() && etiquetaErrorcontraseña.visibleProperty().get() && etiquetaErrorRepetirContraseña.visibleProperty().get()) {
            usuarioDao.insertar(InicializarObjeto.inicializarUsuario(0, cajaNombre.getText().trim(), cajaApellido.getText().trim(), cajaUusario.getText().trim(), cajaContraseña.getText().trim(), cajaEmail.getText().trim(), true, true));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Scene scene = new Scene(loadFXML("MenuInicio"));
            Stage newStage = new Stage();
            scene.setFill(Color.TRANSPARENT);
            newStage.setScene(scene);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.show();
        } else {
            etiquetaError.setText(CHEQUEAR_DATOS);
        }
       
    }
    
    /**
     * Valida las entradas. Si no son validas imprime error
     */
    public void validarRegistro(){
        if(!validar.validarCincuenta(cajaNombre.getText())){
           etiquetaErrorNombre.setText(EXEDIDO_CARACTERES);
           
       }
       
       if(!validar.validarCincuenta(cajaApellido.getText())){
           etiquetaErrorApellido.setText(EXEDIDO_CARACTERES);
           
       }
       
        if(!validar.validarCien(cajaUusario.getText())){
           etiquetaErrorusuario.setText(EXEDIDO_CARACTERES);
          
       }
       if(!validar.validarCien(cajaContraseña.getText())){
           etiquetaErrorcontraseña.setText(EXEDIDO_CARACTERES);
          
       }
       
       if(!validar.validarCien(cajaRepetirContraseña.getText())){
           etiquetaErrorRepetirContraseña.setText(EXEDIDO_CARACTERES);
       }
       
       if(!cajaContraseña.getText().equals(cajaRepetirContraseña.getText())){
           etiquetaErrorRepetirContraseña.setText(CONTRASEÑA_NO_COINCIDEN);
          
       }
       
       if(!validar.validarLongitudEmail(cajaEmail.getText())){
           etiquetaErrorRepetirContraseña.setText(EXEDIDO_CARACTERES);
       }
       
       if(!validar.validarEmail(cajaEmail.getText())){
           etiquetaErrorRepetirContraseña.setText(EMAIL_NO_VALIDO);
       }
    }

   
    
   

    

    
    
    
    
}
