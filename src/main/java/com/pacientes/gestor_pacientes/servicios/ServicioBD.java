/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

/**
 *
 * @author bruno
 */
public class ServicioBD {
    
    
    
    
    public String obtenerRutaMysqldump() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Ruta típica en Windows (puedes verificar si existe o empaquetarla)
            return "C:\\Program Files\\MariaDB 12.3\\bin\\mysqldump.exe";
        } else if (os.contains("nix") || os.contains("nux")) {
            // En Linux usualmente está en el PATH global o en /usr/bin/mysqldump
            return "/usr/bin/mysqldump";
        } else if (os.contains("mac")) {
            return "/usr/local/bin/mysqldump";
        }
        return "mysqldump"; // Fallback por defecto
    }
    
    
    
}
