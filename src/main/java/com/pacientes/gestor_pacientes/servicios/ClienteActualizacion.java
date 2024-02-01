/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;
import java.io.File;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.nio.file.Path;
import java.nio.file.Paths;
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
        
        

         String urlRepositorio = "https://github.com/usuario/repositorio.git";
        String carpetaDestino = selectedDirectory.getPath() + "/";

        try {
            clonarRepositorio(urlRepositorio, carpetaDestino);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    
   
    private static void clonarRepositorio(String urlRepositorio, String carpetaDestino) throws Exception {
       Path rutaDestino = Paths.get(carpetaDestino);

        // Clonar el repositorio
        Git.cloneRepository()
                .setURI(urlRepositorio)
                .setDirectory(rutaDestino.toFile())
                .setBranch("main") // Especifica la rama deseada
                .call();

        System.out.println("Clonación exitosa del repositorio en la carpeta: " + rutaDestino);
    }
    
   /* private static void descomprimirArchivoZip(String archivoZip, String directorioDestino) throws Exception {
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
    }*/

    
    
    
    
    
}
