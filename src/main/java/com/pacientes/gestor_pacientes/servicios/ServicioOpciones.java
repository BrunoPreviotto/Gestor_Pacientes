/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import java.time.LocalDateTime;

public class ServicioOpciones {
    
   
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
    
    
}
