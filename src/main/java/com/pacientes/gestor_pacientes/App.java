package com.pacientes.gestor_pacientes;



import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.TemaAplicacion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.paint.Color;

import javafx.stage.StageStyle;


import java.sql.SQLException;



import javafx.scene.image.Image;


/**
 * JavaFX App
 */
public class App extends Application {
    
    
    UsuarioDAOImplementacion usuarioImp = new UsuarioDAOImplementacion();
    
    private static Scene scenePrincipal;

    
    
    
    @Override
    public void init(){
        
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        
        FXMLLoader Loader;
        TemaAplicacion temaAplicacion = new TemaAplicacion();
        UsuarioDAOImplementacion u = new UsuarioDAOImplementacion();
        
        VariablesEstaticas.setColorTeamaAplicacion(temaAplicacion.iniciarColor(u.obtenerColorTemaAplicacion()));
        
        
        if(usuarioImp.existeUsuarioReciente() == 1){
            Loader = new FXMLLoader(App.class.getResource( "MenuInicio.fxml"));
             //MenuInicioController controllerMenu = Loader.getController();
        }else{
            Loader = new FXMLLoader(App.class.getResource( "IniciarSesion.fxml"));
            //IniciarSesionController controllerIniciar = Loader.getController();
            
        }
        
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
    
     
     

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void stop(){
        
    }

}