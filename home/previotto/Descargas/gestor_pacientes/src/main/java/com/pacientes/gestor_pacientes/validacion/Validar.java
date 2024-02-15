/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.validacion;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.VALID_EMAIL_ADDRESS_REGEX;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import java.util.regex.Matcher;


/**
 *
 * @author previotto
 */
public class Validar extends ValidarPadre{
    
    private UsuarioDAOImplementacion usuarioDao = new UsuarioDAOImplementacion();

    public boolean validarCinco(String cincuenta) {
        return cincuenta.length() <= 5;
    }
    
    public boolean validarDiez(String cincuenta) {
        return cincuenta.length() <= 10;
    }
    
    public boolean validarCincuenta(String cincuenta) {
        return cincuenta.length() <= 50;
    }
    
    
    //caja contraseña
    public boolean validarCien(String cien) {
        return cien.length() <= 100;
    }
    
    public boolean validarLongitudEmail(String email) {
            return email.length() <= 250;
    }
    
    
    public boolean validarEmail(String email) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            return matcher.find();
    }
    
    public boolean validarEdad(String edad) {
        return edad.length() <= 200;
    }
    
    public boolean validarDni(String dni) {
        return dni.length() <= 20;
    }
    
    public boolean validarTelefono(String telefono) {
        return telefono.length() <= 20;
    }
    
   
    
}
