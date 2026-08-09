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

    public GitHubUpdateManager(String owner, String repository) {
        this.owner = owner;
        this.repository = repository;
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
 
    public void update(String currentVersion) throws Exception {

        String latestVersion = getLatestVersion();

        if (currentVersion.equals(latestVersion)) {
            mensajeAdvertenciaError("No hay actualización.", this, VariablesEstaticas.imgenExito);
            System.out.println("No hay actualización.");
            return;
        }

        System.out.println( "Actualización encontrada: " + latestVersion);

        String json = get("https://api.github.com/repos/" + owner + "/" + repository + "/releases/latest");

        String downloadUrl = findJarUrl(json);

        if (downloadUrl == null) {
            throw new Exception(
                    "No se encontró ningún JAR en el release."
            );
        }

        Path currentJar = getCurrentJar();

        Path newJar = Path.of(
                currentJar.toString() + ".new"
        );
        
        ActualizacionDAOImplementacion actualiazacionDAO = new ActualizacionDAOImplementacion();
        Actualizacion actualizacion = new Actualizacion();
        actualizacion.setVersionNueva(latestVersion);
        try {
            actualiazacionDAO.actualizar(actualizacion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        download(downloadUrl, newJar);

        restart(currentJar, newJar);
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

    private String findJarUrl(String json) {

        String search =
                "\"browser_download_url\":\"";

        int position = 0;

        while (true) {

            int start =
                    json.indexOf(search, position);

            if (start == -1) {
                return null;
            }

            start += search.length();

            int end =
                    json.indexOf("\"", start);

            if (end == -1) {
                return null;
            }

            String url =
                    json.substring(start, end);

            if (url.toLowerCase().endsWith(".jar")) {
                return url;
            }

            position = end;
        }
    }

    private void download(
            String urlString,
            Path destination
    ) throws Exception {

        System.out.println("Descargando...");

        URL url = new URL(urlString);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestProperty(
                "User-Agent",
                "MiApp"
        );

        InputStream input =
                connection.getInputStream();

        Files.copy(
                input,
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        input.close();

        System.out.println("Descarga terminada.");
    }

    private Path getCurrentJar()
            throws Exception {

        return Path.of(
                GitHubUpdateManager.class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()
        );
    }

    public boolean isNewer(String current, String latest) {
        return current.equals(latest);
    }

    private void restart(
            Path currentJar,
            Path newJar
    ) throws Exception {

        boolean windows =
                System.getProperty("os.name")
                        .toLowerCase()
                        .contains("win");

        long pid =
                ProcessHandle.current().pid();

        Path script =
                currentJar.resolveSibling(
                        windows
                                ? "update.bat"
                                : "update.sh"
                );

        if (windows) {

            String content =
                    "@echo off\n" +
                    ":wait\n" +
                    "tasklist /FI \"PID eq " + pid +
                    "\" | find \"" + pid +
                    "\" >nul\n" +
                    "if not errorlevel 1 (\n" +
                    " timeout /t 1 /nobreak >nul\n" +
                    " goto wait\n" +
                    ")\n" +
                    "copy /Y \"" +
                    newJar +
                    "\" \"" +
                    currentJar +
                    "\"\n" +
                    "del /Q \"" +
                    newJar +
                    "\"\n" +
                    "start \"\" java -jar \"" +
                    currentJar +
                    "\"\n" +
                    "del \"%~f0\"";

            Files.writeString(
                    script,
                    content
            );

            new ProcessBuilder(
                    "cmd",
                    "/c",
                    script.toString()
            ).start();

        } else {

            String content =
                    "#!/bin/sh\n" +
                    "while kill -0 " +
                    pid +
                    " 2>/dev/null; do sleep 1; done\n" +
                    "cp \"" +
                    newJar +
                    "\" \"" +
                    currentJar +
                    "\"\n" +
                    "rm -f \"" +
                    newJar +
                    "\"\n" +
                    "java -jar \"" +
                    currentJar +
                    "\"\n" +
                    "rm -f \"$0\"";

            Files.writeString(
                    script,
                    content
            );

            script.toFile().setExecutable(true);

            new ProcessBuilder(
                    "sh",
                    script.toString()
            ).start();
        }

        System.exit(0);
    }
}