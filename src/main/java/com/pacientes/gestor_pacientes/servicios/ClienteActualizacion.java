/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;
import java.io.*;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class ClienteActualizacion {
   
   
    
    
    public void descargarDrive()  {
       
        Stage primaryStage = new Stage();
        
        // Crear el selector de directorios
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar Directorio");

        // Mostrar el diálogo y obtener el directorio seleccionado
        File selectedDirectory = directoryChooser.showDialog(primaryStage);

        // Verificar si se seleccionó un directorio
        if (selectedDirectory != null) {
            System.out.println("Directorio seleccionado: " + selectedDirectory.getAbsolutePath());
            // Aquí puedes realizar las operaciones que necesites con el directorio seleccionado
        } else {
            System.out.println("Ningún directorio seleccionado.");
        }
        
        
        String urlRepositorioZip = "https://github.com/usuario/repositorio/archive/refs/heads/main.zip";
        String directorioDestino = "actualizaciones/";

        try {
            // Descargar el archivo ZIP del repositorio
            descargarRepositorioZip(urlRepositorioZip, selectedDirectory.getPath());

            // Descomprimir el archivo ZIP en el directorio de destino
            descomprimirArchivoZip(directorioDestino + "repositorio-main.zip", directorioDestino);

            // Lógica para instalar la nueva versión y reiniciar la aplicación
            // ... (implementa según tus necesidades)
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
    
  private static void descargarRepositorioZip(String urlRepositorioZip, String directorioDestino) throws IOException {
        try (InputStream in = new URL(urlRepositorioZip).openStream()) {
            Files.copy(in, new File(directorioDestino + "repositorio-main.zip").toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void descomprimirArchivoZip(String archivoZip, String directorioDestino) throws IOException {
        try (ZipFile zipFile = new ZipFile(archivoZip)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                Path entryPath = new File(directorioDestino + entry.getName()).toPath();

                // Crear directorios si no existen
                if (entry.isDirectory()) {
                    Files.createDirectories(entryPath);
                } else {
                    Files.copy(zipFile.getInputStream(entry), entryPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    
    
    
    
    
}
