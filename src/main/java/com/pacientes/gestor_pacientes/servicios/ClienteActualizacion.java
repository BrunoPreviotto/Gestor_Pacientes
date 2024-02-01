/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;
import java.io.*;
import java.net.HttpURLConnection;
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
import org.apache.commons.io.FileUtils;

public class ClienteActualizacion {
   
   
    
    
    public void descargarDrive()  {
       
        Stage primaryStage = new Stage();
        
        // Crear el selector de directorios
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar Directorio");

        // Mostrar el diálogo y obtener el directorio seleccionado
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        
        String urlRepositorioZip = "https://github.com/BrunoPreviotto/Gestor_Pacientes/blob/brnchNueva/src/gestor_pacientes.zip";
        String directorioDestino = selectedDirectory.getPath() + "/";

        // Verificar si se seleccionó un directorio
        if (selectedDirectory != null) {
            System.out.println("Directorio seleccionado: " + directorioDestino);
            // Aquí puedes realizar las operaciones que necesites con el directorio seleccionado
        } else {
            System.out.println("Ningún directorio seleccionado.");
        }
        
        
        

       
       try {
            // Descargar el archivo ZIP del repositorio
            descargarRepositorioZip(urlRepositorioZip, directorioDestino);

            // Descomprimir el archivo ZIP en el directorio de destino
            //descomprimirArchivoZip(directorioDestino + "repositorio-main.zip", directorioDestino);

            // Lógica para instalar la nueva versión y reiniciar la aplicación
            // ... (implementa según tus necesidades)
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    
   
    private static void descargarRepositorioZip(String urlRepositorioZip, String directorioDestino) throws IOException {
        // Asegurarse de que el directorio de destino exista
        Path destino = Path.of(directorioDestino);
        Files.createDirectories(destino);

        URL url = new URL(urlRepositorioZip);
        
        // Descargar el archivo ZIP utilizando java.nio.file
        try (InputStream in = url.openStream()) {
            
            
            
            Path archivoDestino = destino.resolve("gestor_paciente.zip");
            Files.copy(in, archivoDestino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Descarga exitosa del archivo ZIP.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al descargar el archivo ZIP: " + e.getMessage());
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
