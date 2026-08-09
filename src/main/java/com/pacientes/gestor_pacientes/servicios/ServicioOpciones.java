/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.controlador.MenuInicioController;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ServicioOpciones extends MenuInicioController{
    
   
    public void registrarCodigoConExpiracion(int usuarioId, long codigo) {
        // 1. Calcular la fecha y hora de vencimiento (10 minutos desde ahora)
        LocalDateTime fechaExpiracion = LocalDateTime.now().plusMinutes(10);
        
        //UsuarioDAOImplementacion usuarioDao = new UsuarioDAOImplementacion();
        // Guardar código y su fecha de expiración en la BD
        
        System.out.println("Fecha: " + fechaExpiracion);
        
        /*usuarioDao.insertarCodigo(usuarioId, 0);
        actualizarCodigoEnBaseDeDatos(usuarioId, codigo, fechaExpiracion);

        // 2. Registrar el Shutdown Hook para limpiar el código si la app se cierra
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Cerrando la aplicación... Borrando código temporal.");
            borrarCodigoEnBaseDeDatos(usuarioId);
        }));*/
    }

    

    // Método que debes usar cuando el usuario intente validar el código
    public boolean esCodigoValido(String codigoIngresado, String codigoBD, LocalDateTime expiradoEnBD) {
        if (codigoBD == null || expiradoEnBD == null) {
            return false;
        }
        // Valida que coincida el código Y que la hora actual no haya superado la de expiración
        return codigoIngresado.equals(codigoBD) && LocalDateTime.now().isBefore(expiradoEnBD);
    }
    
    
    public void copiaBaseDeDatosLocal(){
         // Crea un objeto DirectoryChooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecciona una carpeta para guardar");

        // Muestra el diálogo de selección de directorios
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            // Puedes utilizar la carpeta seleccionada para guardar tu archivo
            UsuarioDAOImplementacion dao = new UsuarioDAOImplementacion();
            
            String dbName = "gestion_pacientes";
            String dbUser = "root";
            String dbPassword = "";
            
            
            String backupPath = selectedDirectory.getAbsolutePath() + "/" + VariablesEstaticas.usuario.getId() + VariablesEstaticas.usuario.getApellido().trim().replace(" ", "")  + VariablesEstaticas.usuario.getNombre().trim().replace(" ", "") + "Copia.sql";
            
            dao.actualizarRutaGuardaBD(selectedDirectory.getAbsolutePath() + "/" + VariablesEstaticas.usuario.getId() + VariablesEstaticas.usuario.getApellido().trim().replace(" ", "")  + VariablesEstaticas.usuario.getNombre().trim().replace(" ", "") + "Copia.sql");
            
            try{
                 // Construye el comando para ejecutar mysqldump
                String command = "mysqldump --user=" + dbUser + " --password=" + dbPassword + " " + dbName + " -r " + backupPath;

                // Ejecuta el comando
                Process process = Runtime.getRuntime().exec(command);

                // Espera a que el proceso termine
                int exitCode = process.waitFor();
                
                
                InputStream errorStream = process.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
                StringBuilder errorMessage = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                errorMessage.append(line).append("\n");
            }

                // Verifica si la copia de seguridad fue exitosa
            if (exitCode == 0) {
                mensajeAdvertenciaError("Copia de seguridad exitosa.", this, VariablesEstaticas.imgenExito);
                
            } else {
                mensajeAdvertenciaError("Error al realizar la copia de seguridad.", this, VariablesEstaticas.imgenError);
                
                 System.out.println("Mensaje de error:\n" + errorMessage.toString());
            }
            }catch(IOException | InterruptedException e){
                e.printStackTrace();
                mensajeAdvertenciaError("Error al guard base de dato", this, VariablesEstaticas.imgenError);
            }
        } else {
            System.out.println("Operación cancelada por el usuario.");
        }
    }
    
    
    
    
    
    
    public File generarBackupMariaDB(String host, String puerto, String usuario, String password, String nombreDb) throws IOException, InterruptedException {
        
        ServicioBD sbd = new ServicioBD();
        
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecciona una carpeta para guardar");
        
        File selectedDirectory = directoryChooser.showDialog(new Stage());

         
        
        File archivoSalida = new File(selectedDirectory.getAbsolutePath(), "backup_" + nombreDb + "_" + System.currentTimeMillis() + ".sql");
        
        System.out.println("rut: " + archivoSalida.getAbsolutePath());

       List<String> comando = new ArrayList<>();
        comando.add(sbd.obtenerRutaMysqldump());
        comando.add("-h" + host);
        comando.add("-P" + puerto);
        comando.add("-u" + usuario);
        if (password != null && !password.isEmpty()) {
            comando.add("-p" + password);
        }
        comando.add(nombreDb);

        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectOutput(archivoSalida);
        pb.redirectError(new File(selectedDirectory.getAbsolutePath(), "error_backup.log"));

        Process proceso = pb.start();
        int codigoSalida = proceso.waitFor();

        
       
        
        if (codigoSalida == 0) {
            return archivoSalida;
        } else {
            throw new RuntimeException("Error al generar el respaldo de MariaDB. Código: " + codigoSalida);
        }
       
    }
    
    
    
    
    
    
    
    
    
}
