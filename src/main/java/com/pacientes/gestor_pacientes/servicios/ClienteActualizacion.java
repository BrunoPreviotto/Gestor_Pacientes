/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import java.io.BufferedReader;
import org.eclipse.jgit.api.Git;



import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

import org.apache.commons.io.FileUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ClienteActualizacion {
   
   
    
    
    public void descargarDrive(String carpetaDestino)  {
       
        
        

         String urlRepositorio = "https://github.com/BrunoPreviotto/Gestor_Pacientes.git";
        

        try {
            actualizar(carpetaDestino);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    
     private void actualizar(String rutaArchivo){
        

        try {
            // Lanzar la otra aplicación
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", rutaArchivo);
            Process procesoOtraAplicacion = processBuilder.start();
            System.out.println(processBuilder.toString());
            // Puedes esperar a que el proceso termine si es necesario
            // procesoOtraAplicacion.waitFor();

            // Cerrar la aplicación actual
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
   
    
    
   
    public String getReadmeContent() throws IOException {
        String readmeUrl = "https://raw.githubusercontent.com/BrunoPreviotto/Gestor_Pacientes/master/README.md";
        
        URL url = new URL(readmeUrl);
        URLConnection connection = url.openConnection();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            
            return content.toString();
        }
    }
    
    
    public void VaciarDirectorio(String rutaDirectorio) {

        // Ruta del directorio que deseas vaciar
        File directorio = new File(rutaDirectorio);

        try {
            FileUtils.cleanDirectory(directorio);
            System.out.println("Directorio vaciado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al vaciar el directorio: " + e.getMessage());

        }
    }
    
    
}


     

/*
private static void clonarRepositorio(String urlRepositorio, String carpetaDestino) throws Exception {
       Path rutaDestino = Paths.get(carpetaDestino);

        // Clonar el repositorio
        Git.cloneRepository()
                .setURI(urlRepositorio)
                .setDirectory(rutaDestino.toFile())
                .setBranch("master") // Especifica la rama deseada
                .call();

        System.out.println("Clonación exitosa del repositorio en la carpeta: " + rutaDestino);
    }
    
    private static void limpiarDirectorio(String rutaDirectorio) throws IOException {
        File directorio = new File(rutaDirectorio);
        if (directorio.exists()) {
            FileUtils.deleteDirectory(directorio);
            System.out.println("Directorio limpiado: " + directorio);
        } else {
            System.out.println("El directorio no existe: " + directorio);
        }
    }
*/