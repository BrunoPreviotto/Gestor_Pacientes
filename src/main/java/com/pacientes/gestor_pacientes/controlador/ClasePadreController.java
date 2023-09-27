/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.servicios.ServicioPaciente;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.EXEDIDO_CARACTERES;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.NO_NUMEROS;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.NO_TEXTO;
import com.pacientes.gestor_pacientes.validacion.Validar;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author previotto
 */
public class ClasePadreController {
    protected Image imagenIocono = new Image(getClass().getResourceAsStream("/com/pacientes/gestor_pacientes/img/icono.png"));
    protected String cssNuevo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/cambio.css").toExternalForm();
    protected String imagenErrorRuta = getClass().getResource("/com/pacientes/gestor_pacientes/img/error.png").toExternalForm();
    
    private Image imgError = new Image(imagenErrorRuta);
    private Image imgOk = new Image("/com/pacientes/gestor_pacientes/img/exito.png");
    @FXML
    private Label etiquetaMensaje;
    
    protected CRUD daoImplementacion;
    
    @FXML
    protected void salir(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    protected void minimizarVentana(MouseEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    protected void maximizar(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setMaximized(true);
        
    }
          
    @FXML
    protected void desMaximizar(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setMaximized(false);
    }
    
    
    protected static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    
    @FXML
    protected void soloString(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        String character = event.getCharacter();
        if (character.matches("[0-9]")) {
            tf.deletePreviousChar();
        }
    }
    
    
    
    @FXML
    protected void volverCajaALaNormalidad(KeyEvent event) {
        //TextField tf = (TextField) event.getSource();
        Control tf = (Control) event.getSource();
        tf.getStyleClass().remove("cajasARellenar");
        
        
    }
    
    @FXML
    protected void volverChoiceALaNormalidad(MouseEvent event) {
        Control tf = (Control) event.getSource();
        tf.getStyleClass().remove("cajasARellenar");
    }
    
    
    @FXML
    protected void soloNumero(KeyEvent event) {        TextField tf = (TextField) event.getSource();
        String character = event.getCharacter();
        
        
        if (!character.matches("[0-9]") && !event.getCode().isArrowKey()) {
            tf.deletePreviousChar();
        }
    }
    
    
    
    @FXML
    protected void soloNumeroFlotantes(KeyEvent event) {
        
        TextField tf = (TextField) event.getSource();
        String character = event.getCharacter();
        if(tf.getText().length() == 1 || existeComa(tf.getText())){
            if (!character.matches("[0-9]")) {
               
                tf.deletePreviousChar();
            }
        }else{
            if (!character.matches(",") && !character.matches("[0-9]")) {
                tf.deletePreviousChar();
            }
        }
        
        
    }
    
    public boolean existeComa(String coma){
        int cantidadComas = 0;
        for (int i = 0; i < coma.length(); i++) {
            
            if(coma.charAt(i) == 44){
                cantidadComas++;
                if(cantidadComas > 1){
                   return true; 
                }
            }
        }
        return false;
    }
    
    
   protected void agregarImg(HBox v, int esError, String error){
       
       
       ImageView img = new ImageView();
       Label label = new Label(error);
       label.getStylesheets().add(cssNuevo);
       label.getStyleClass().add("error");
       
       if (v.getChildren().size() < 3) {
           if (esError == 1) {
               img.setImage(imgError);
               img.setId("imgError");
               v.getChildren().add(img);
               v.getChildren().add(label);
           } else {
               label = new Label(error);
               img.setImage(imgOk);
               img.setId("imgOk");
               v.getChildren().add(img);
               v.getChildren().add(label);
           }
       } else if (esError == 0  && v.getChildren().size() == 3) {
           ImageView imgv = (ImageView) v.getChildren().get(1);
           Label labelv = (Label) v.getChildren().get(2);
           imgv.setId("imgOk");
           imgv.setImage(imgOk);
           labelv.setText(error);

       } else if (esError == 1 && v.getChildren().size() == 3) {
           ImageView imgv = (ImageView) v.getChildren().get(1);
           Label labelv = (Label) v.getChildren().get(2);
           imgv.setImage(imgError);
           imgv.setId("imgError");
           labelv.setText(error);
       }else if(esError == 2){
           ImageView imgv = (ImageView) v.getChildren().get(1);
           Label labelv = (Label) v.getChildren().get(2);
           imgv.setImage(null);
           imgv.setId("imgVacio");
           labelv.setText(error);
       }
       
       
       img.setFitWidth(20);
       img.setFitHeight(20);
       img.setTranslateX(5);
       img.setTranslateY(14);
       label.setTranslateX(8);
       label.setTranslateY(18);
   }
   
   
   
   
   public void validarCajasExedidasCaracteres(Map<TextField, Method> mapValidar) {
        Validar v = new Validar();
        HBox hb;
        try {
            for (Map.Entry<TextField, Method> entry : mapValidar.entrySet()) {
                Object val = entry.getValue().invoke(v, entry.getKey().getText());
                if(val.toString().equals("false")){
                    hb = (HBox)entry.getKey().getParent();
                    agregarImg(hb, 1, EXEDIDO_CARACTERES);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   
   
   
   
   
   
   
    public void mensajeAdvertenciaError(String mensaje, Object obj, String imagen){
        try {
            
            
            
            FXMLLoader Loader = new FXMLLoader(App.class.getResource( "MensajeAdvertencia.fxml"));
            Parent root = Loader.load();
            MensajeAdvertenciaController controller = Loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            scene.setFill(Color.TRANSPARENT);
            
            
            stage.initOwner(VariablesEstaticas.stagePrincipal);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.WINDOW_MODAL);
            controller.mensajeAdvertencia(mensaje, stage, obj, imagen);
            stage.showAndWait();
            
            
           
        
        } catch (IOException ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public void mensajePreguntarSiONo(){
        try {
            
            FXMLLoader Loader = new FXMLLoader(App.class.getResource( "MensajePreguntarSiONo.fxml"));
            Parent root = Loader.load();
            MensajePreguntarSiONoController controller = Loader.getController();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initOwner(VariablesEstaticas.stagePrincipal);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
           
            
           
        
        } catch (IOException ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     
    
    
}
