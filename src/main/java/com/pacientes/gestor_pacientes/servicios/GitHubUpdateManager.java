/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;



import com.pacientes.gestor_pacientes.controlador.MenuInicioController;
import com.pacientes.gestor_pacientes.implementacionDAO.ActualizacionDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Actualizacion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class GitHubUpdateManager extends MenuInicioController{

    private String owner;
    private String repository;
     private final String jarName;

     public GitHubUpdateManager(
            String owner,
            String repository,
            String jarName) {

        this.owner = owner;
        this.repository = repository;
        this.jarName = jarName;
    }

    public String getLatestVersion() throws Exception {

        String url = "https://api.github.com/repos/" + owner + "/" + repository + "/releases/latest";

        String json = get(url);

        String tag = getJsonValue(json, "tag_name");

        if (tag == null) {
            throw new Exception("No se encontró tag_name");
        }

        return tag.replace("v", "");
    }
 
    public boolean update(String currentVersion) throws Exception {

        String latestVersion = getLatestVersion();

        if (currentVersion.equals(latestVersion)) {
            mensajeAdvertenciaError("No hay actualización.", this, VariablesEstaticas.imgenExito);
            System.out.println("No hay actualización.");
            return false;
        }

        System.out.println( "Actualización encontrada: " + latestVersion);

        String json = get("https://api.github.com/repos/" + owner + "/" + repository + "/releases/latest");

        String downloadUrl = findJarUrl(json, jarName);
        
        System.out.println("Download url: " + downloadUrl);

        if (downloadUrl == null) {
            throw new Exception(
                    "No se encontró ningún JAR en el release."
            );
        }

        Path currentJar = getCurrentJar();

        System.out.println("JAR actual:");
        System.out.println(currentJar);

        Path newJar =
                currentJar.resolveSibling(
                        jarName + ".new"
                );

        System.out.println("JAR nuevo:");
        System.out.println(newJar);

        
        ActualizacionDAOImplementacion actualiazacionDAO = new ActualizacionDAOImplementacion();
        Actualizacion actualizacion = new Actualizacion();
        actualizacion.setVersionNueva(latestVersion);
        try {
            actualiazacionDAO.actualizar(actualizacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         try {
           download(downloadUrl, newJar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        restart(currentJar, newJar);
        return true;
    }

    private String get(String urlString) throws Exception {

        URL url = new URL(urlString);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        connection.setRequestProperty(
                "Accept",
                "application/vnd.github+json"
        );

        connection.setRequestProperty( "User-Agent", "MiApp");

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new Exception(
                    "GitHub respondió: "
                            + responseCode
            );
        }

        InputStream input = connection.getInputStream();

        return new String(input.readAllBytes());
    }

    private String getJsonValue(
            String json,
            String key
    ) {

        String search = "\"" + key + "\":\"";

        int start = json.indexOf(search);

        if (start == -1) {
            return null;
        }

        start += search.length();

        int end = json.indexOf("\"", start);

        if (end == -1) {
            return null;
        }

        return json.substring(start, end);
    }

    private String findJarUrl(String json, String fileName) {
 
        String search =
                "\"name\":\"" + fileName
                        + "\"";

        int namePosition = json.indexOf(search);

       if (namePosition == -1) {
            return null;
        }

        int urlPosition =
                json.indexOf(
                        "\"browser_download_url\":\"",
                        namePosition
                );

        if (urlPosition == -1) {
            return null;
        }

        urlPosition +=
                "\"browser_download_url\":\""
                        .length();

        int end =
                json.indexOf(
                        "\"",
                        urlPosition
                );

        if (end == -1) {
            return null;
        }

        return json.substring(
                urlPosition,
                end
        );
    }
    
    private Path getCurrentJar()
            throws Exception {

        Path path =
                Path.of(
                        GitHubUpdateManager.class
                                .getProtectionDomain()
                                .getCodeSource()
                                .getLocation()
                                .toURI()
                );

        return path;
    }

    private void download(String urlString, Path destination) throws Exception {

         

        URL url =
                new URL(urlString);

        HttpURLConnection connection =
                (HttpURLConnection)
                        url.openConnection();

        connection.setRequestProperty(
                "User-Agent",
                "MiApp"
        );

        try (InputStream input =
                     connection.getInputStream()) {

            Files.copy(
                    input,
                    destination,
                    StandardCopyOption
                            .REPLACE_EXISTING
            );
        }
    }

   

    public boolean isNewer(String current, String latest) {
        return current.equals(latest);
    }

    private void restart(
        Path currentJar,
        Path newJar) throws Exception {

    boolean windows =
            System.getProperty("os.name")
                    .toLowerCase()
                    .contains("win");

    long pid = ProcessHandle.current().pid();

    if (windows) {

        Path script =
                currentJar.resolveSibling("update.bat");

        String text =
                "@echo off\r\n" +
                "echo Esperando que termine la aplicacion...\r\n" +
                ":wait\r\n" +
                "tasklist /FI \"PID eq " + pid + "\" | findstr /C:\"" + pid + "\" >nul\r\n" +
                "if not errorlevel 1 (\r\n" +
                "    timeout /t 1 /nobreak >nul\r\n" +
                "    goto wait\r\n" +
                ")\r\n" +
                "\r\n" +
                "echo Reemplazando JAR...\r\n" +
                "copy /Y \"" + newJar + "\" \"" + currentJar + "\"\r\n" +
                "\r\n" +
                "if errorlevel 1 (\r\n" +
                "    echo ERROR al reemplazar el JAR\r\n" +
                "    pause\r\n" +
                "    exit\r\n" +
                ")\r\n" +
                "\r\n" +
                "del /Q \"" + newJar + "\"\r\n" +
                "\r\n" +
                "echo Iniciando nueva version...\r\n" +
                "start \"\" java -jar \"" + currentJar + "\"\r\n" +
                "\r\n" +
                "del \"%~f0\"";

        Files.writeString(script, text);

        System.out.println("Ejecutando actualizador:");
        System.out.println(script);

        new ProcessBuilder(
                "cmd",
                "/c",
                script.toString()
        ).start();

    } else {

        Path script =
                currentJar.resolveSibling("update.sh");

        String text =
                "#!/bin/sh\n" +
                "\n" +
                "echo 'Esperando que termine la aplicacion...'\n" +
                "\n" +
                "while kill -0 " + pid + " 2>/dev/null\n" +
                "do\n" +
                "    sleep 1\n" +
                "done\n" +
                "\n" +
                "echo 'Reemplazando JAR...'\n" +
                "cp \"" + newJar + "\" \"" + currentJar + "\"\n" +
                "\n" +
                "rm -f \"" + newJar + "\"\n" +
                "\n" +
                "echo 'Iniciando nueva version...'\n" +
                "java -jar \"" + currentJar + "\"\n" +
                "\n" +
                "rm -f \"$0\"\n";

        Files.writeString(script, text);

        script.toFile().setExecutable(true);

        new ProcessBuilder(
                "sh",
                script.toString()
        ).start();
    }

    System.exit(0);
}
}