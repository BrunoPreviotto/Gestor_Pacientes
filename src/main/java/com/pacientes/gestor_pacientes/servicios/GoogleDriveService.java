package com.pacientes.gestor_pacientes.servicios;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Collections;

public class GoogleDriveService {

    private static final String APPLICATION_NAME = "Backup App JavaFX";
    private static final java.util.Collection<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);

    private static Drive getDriveService() throws Exception {
        var qq = GoogleDriveService.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), new InputStreamReader(qq));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), clientSecrets, SCOPES)
                .setDataStoreFactory(new com.google.api.client.util.store.FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void subirArchivoADrive(File archivoLocal) throws Exception {
        Drive service = getDriveService();

        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName(archivoLocal.getName());

        FileContent mediaContent = new FileContent("application/sql", archivoLocal);
        com.google.api.services.drive.model.File archivoSubido = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        System.out.println("Archivo subido exitosamente a Google Drive con ID: " + archivoSubido.getId());
    }
}