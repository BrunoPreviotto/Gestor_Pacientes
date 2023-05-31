package com.pacientes.gestor_pacientes;


import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
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
import java.util.ArrayList;

import java.util.List;

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
        
       
        
        if(usuarioImp.existeUsuarioReciente() == 1){
            scenePrincipal = new Scene(loadFXML("MenuInicio"));
            
        }else{
            scenePrincipal = new Scene(loadFXML("IniciarSesion"));
            
        }
        
        
        
        scenePrincipal.setFill(Color.TRANSPARENT);
        stage.setScene(scenePrincipal);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.isResizable();
        stage.show();
        
        VariablesEstaticas.scenePrincipalVar = scenePrincipal;
        
       
            
         
            
        
    }

    

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}