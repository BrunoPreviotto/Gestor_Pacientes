/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.validacion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.util.regex.Matcher;

/**
 *
 * @author previotto
 */
public class ValidarRegistro extends ValidarPadre{

    
    public boolean validarNombreApellido(String nombreApellido) {
        return nombreApellido.length() <= 50;
    }
    
    
    
    public boolean validarUsuarioContraseña(String usuarioContraseña) {
        return usuarioContraseña.length() <= 100;
    }
    
     public boolean validarLongitudEmail(String email) {
            return email.length() <= 250;
    }
    
    
    public boolean validarEmail(String email) {
            Matcher matcher = VariablesEstaticas.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            return matcher.find();
    }
    
}
