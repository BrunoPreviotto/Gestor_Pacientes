/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class Directorios {
    
    public static String buscarDirectorio(){
        
         DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecciona una carpeta para guardar");
        
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        
        String path = selectedDirectory.getAbsolutePath();
        
        return path;
    }
    
    
    public static String buscaArchivo(){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona archivo");
        
        File selectedDirectory =  fileChooser.showOpenDialog(new Stage());
        
        String path = selectedDirectory.getAbsolutePath();
        
        return path;
    }
    
    
}
