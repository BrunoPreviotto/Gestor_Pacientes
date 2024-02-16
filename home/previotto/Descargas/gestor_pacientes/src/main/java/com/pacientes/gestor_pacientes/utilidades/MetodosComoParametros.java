/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import com.pacientes.gestor_pacientes.validacion.Validar;
import java.lang.reflect.Method;

/**
 *
 * @author previotto
 */
public class MetodosComoParametros {

    private Class[] parametroMetodo = new Class[1];
    

    public Method retornarMetodosValidar(int numerMetodo) {
        parametroMetodo[0] = String.class;
        try {
            if (numerMetodo == 1) {
               
            }
            
            switch (numerMetodo) {
                case 1:
                    return Validar.class.getMethod("validarCinco", parametroMetodo);
                case 2:
                    return Validar.class.getMethod("validarDiez", parametroMetodo);
                case 3:
                    return Validar.class.getMethod("validarCincuenta", parametroMetodo);
                case 4:
                    return Validar.class.getMethod("validarCien", parametroMetodo);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
        
    }
}
