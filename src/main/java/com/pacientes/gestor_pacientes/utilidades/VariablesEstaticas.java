/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import java.util.regex.Pattern;

/**
 *
 * @author previotto
 */
public class VariablesEstaticas {
    //valida email
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final String CHEQUEAR_DATOS = "Corrigir datos inválidos";
    public static  final String NO_NUMEROS = "Ingresar solo texto";
    public static  final String NO_TEXTO = "Ingresar solo números";
    public static final String EXEDIDO_CARACTERES = "Exedido de caracteres";
    public static final String CONTRASEÑA_NO_COINCIDEN = "Las contraseñas no coinciden";
    public static final String EMAIL_NO_VALIDO = "El email tiene un formato no válido";
    
    
}
