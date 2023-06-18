/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

/**
 *
 * @author previotto
 */
public class Hilo implements Runnable{
    @Override
    public void run() {
        
    }

    
    
    public void notificacionFecha(){
        // Crear la primera instancia de hilo
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Acción del hilo 1

            }
        });

        // Crear la segunda instancia de hilo
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Acción del hilo 2
                
                
            }
        });

        // Iniciar la ejecución de los hilos
        hilo1.start();
        hilo2.start();

        // Puedes realizar más tareas en el programa principal mientras los hilos se ejecutan en segundo plano
        
        
    }
}
