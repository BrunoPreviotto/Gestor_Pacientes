package com.pacientes.gestor_pacientes;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;






import java.io.IOException;
import javafx.geometry.NodeOrientation;
import javafx.scene.paint.Color;

import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {
    
    
    private static Scene scene;
    
    @Override
    public void init(){
        
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("IniciarSesion"));
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