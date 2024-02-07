 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;



import java.io.File;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;

import java.util.Arrays;

/**
 *
 * @author previotto
 */
public class ConstruirApp {
    
    public void construir() {
        // Configurar la solicitud de invocación
        InvocationRequest request = new DefaultInvocationRequest();
        File file = new File("/home/previotto/Descargas/gestor_pacientes/pom.xml");
        request.setPomFile(file); // Ruta al archivo pom.xml de tu proyecto
        request.setGoals(Arrays.asList("clean", "install")); // Definir las metas Maven: clean install

        // Configurar el invocador
        DefaultInvoker invoker = new DefaultInvoker();

        try {
            // Invocar Maven y obtener el resultado
            InvocationResult result = invoker.execute(request);

            // Verificar el resultado
            if (result.getExitCode() == 0) {
                System.out.println("Proyecto Maven limpio e instalado exitosamente.");
            } else {
                System.err.println("Error al limpiar e instalar el proyecto Maven. Código de salida: " + result.getExitCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
