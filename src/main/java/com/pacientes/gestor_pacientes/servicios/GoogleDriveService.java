
package com.pacientes.gestor_pacientes.servicios;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

public class GoogleDriveService {

    private static final String APPLICATION_NAME = "Backup App JavaFX";

    private static final java.util.Collection<String> SCOPES =
            Collections.singletonList(DriveScopes.DRIVE_FILE);

    private static final GsonFactory JSON_FACTORY =
            GsonFactory.getDefaultInstance();

    private static final File TOKENS_DIRECTORY =
            new File("tokens");

    /**
     * Obtiene el servicio autenticado de Google Drive.
     */
    private static Drive getDriveService() throws Exception {

        InputStream inputStream =
                GoogleDriveService.class.getResourceAsStream("/credentials.json");

        if (inputStream == null) {
            throw new IllegalStateException(
                    "No se encontró credentials.json en src/main/resources"
            );
        }

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(
                        JSON_FACTORY,
                        new InputStreamReader(inputStream)
                );

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JSON_FACTORY,
                        clientSecrets,
                        SCOPES
                )
                .setDataStoreFactory(
                        new FileDataStoreFactory(TOKENS_DIRECTORY)
                )
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver =
                new LocalServerReceiver.Builder()
                        .setPort(8888)
                        .build();

        Credential credential =
                new AuthorizationCodeInstalledApp(
                        flow,
                        receiver
                ).authorize("user");

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential
        )
        .setApplicationName(APPLICATION_NAME)
        .build();
    }

    /**
     * Sube un archivo a la raíz de Google Drive.
     *
     * @param archivoLocal archivo que se desea subir
     * @return ID del archivo creado en Google Drive
     */
    public static String subirArchivoADrive(File archivoLocal)
            throws Exception {

        if (archivoLocal == null || !archivoLocal.exists()) {
            throw new IllegalArgumentException(
                    "El archivo de backup no existe."
            );
        }

        Drive service = getDriveService();

        com.google.api.services.drive.model.File fileMetadata =
                new com.google.api.services.drive.model.File();

        fileMetadata.setName(archivoLocal.getName());

        FileContent mediaContent =
                new FileContent(
                        "application/sql",
                        archivoLocal
                );

        com.google.api.services.drive.model.File archivoSubido =
                service.files()
                        .create(fileMetadata, mediaContent)
                        .setFields("id, name, webViewLink")
                        .execute();

        System.out.println(
                "Backup subido correctamente."
        );

        System.out.println(
                "Nombre: " + archivoSubido.getName()
        );

        System.out.println(
                "ID: " + archivoSubido.getId()
        );

        return archivoSubido.getId();
    }
}

