/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.pacientes.gestor_pacientes.App;

import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.*;
import com.pacientes.gestor_pacientes.validacion.Validar;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author previotto
 */
public abstract class PadreController {
    
    protected String cssNuevo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/cambio.css").toExternalForm();
    protected String imagenErrorRuta = getClass().getResource("/com/pacientes/gestor_pacientes/img/error.png").toExternalForm();
    protected String imagenOkRuta = getClass().getResource("/com/pacientes/gestor_pacientes/img/ok.png").toExternalForm();
    private Image imgError = new Image(imagenErrorRuta);
    private Image imgOk = new Image(imagenOkRuta);
    @FXML
    protected void salir(MouseEvent event) {
        Platform.exit();
    }
    
    protected static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    @FXML
    protected void soloString(KeyEvent event) {
        TextField caja = (TextField) event.getSource();
        HBox v = (HBox) ((Node)(event.getSource())).getParent();
        if( !(Character.isAlphabetic(event.getCharacter().charAt(0))) && !(Character.isSpaceChar(event.getCharacter().charAt(0))) && event.getCharacter().codePointAt(0) != 8){
           agregarImg(v, 1, NO_NUMEROS);
        }else if(caja.getText() == ""){
            agregarImg(v, 2, "");
        }else{
           agregarImg(v, 0, "");
        }
    }
    
    @FXML
    protected void soloNumero(KeyEvent event) {
        TextField caja = (TextField) event.getSource();
         HBox v = (HBox) ((Node) (event.getSource())).getParent();
        if( !(Character.isDigit(event.getCharacter().charAt(0))) && !(Character.isSpaceChar(event.getCharacter().charAt(0))) && event.getCharacter().codePointAt(0) != 8){
           agregarImg(v, 1, NO_TEXTO);
        }else if(caja.getText() == ""){
            agregarImg(v, 2, "");
        }else{
          agregarImg(v, 0, "");
        }
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

    
    
}
