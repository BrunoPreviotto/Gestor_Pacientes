/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.TemaAplicacion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author previotto
 */
public class ControladorIniciador {
 
    private static Scene scenePrincipal;
    
    
    public void iniciar(Stage stage) throws IOException{
        
        
       FXMLLoader Loader;
        TemaAplicacion temaAplicacion = new TemaAplicacion();
        UsuarioDAOImplementacion u = new UsuarioDAOImplementacion();
        
       //VariablesEstaticas.setColorTeamaAplicacion(temaAplicacion.iniciarColor(u.obtenerColorTemaAplicacion()));
        
      //VariablesEstaticas.setColorTeamaAplicacion(temaAplicacion.iniciarColor(1));
        
        /*if(usuarioImp.existeUsuarioReciente() == 1){
            Loader = new FXMLLoader(App.class.getResource( "MenuInicio.fxml"));
             //MenuInicioController controllerMenu = Loader.getController();
        }else{
            Loader = new FXMLLoader(App.class.getResource( "IniciarSesion.fxml"));
            //IniciarSesionController controllerIniciar = Loader.getController();
            
        }*/
        Loader = new FXMLLoader(App.class.getResource( "MenuInicio.fxml"));
        
        Parent root = Loader.load();
        scenePrincipal = new Scene(root);
        scenePrincipal.setFill(Color.TRANSPARENT);
        stage.setScene(scenePrincipal);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.isResizable();
        Image imagenIocono = new Image(getClass().getResourceAsStream("/com/pacientes/gestor_pacientes/img/icono.png"));
        
        stage.getIcons().add(imagenIocono);
        
        VariablesEstaticas.stagePrincipal = stage;
        stage.show();
        
    }
    
}
