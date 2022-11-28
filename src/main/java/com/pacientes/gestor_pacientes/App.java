package com.pacientes.gestor_pacientes;


import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
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
    
    List<String> esUsuario = new ArrayList();
    UsuarioDAOImplementacion usuarioImp = new UsuarioDAOImplementacion();
    
    private static Scene scene;
    
    @Override
    public void init(){
        
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        
        esUsuario = usuarioImp.existeUsuarioReciente();
        
        if(esUsuario.get(1).equals("1")){
            scene = new Scene(loadFXML("MenuInicio"));
        }else{
            scene = new Scene(loadFXML("IniciarSesion"));
            
        }
        
        
        
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.isResizable();
        stage.show();
        
       
        
        
            
         
            
        
    }

    

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}