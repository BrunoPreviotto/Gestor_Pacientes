/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.pacientes.gestor_pacientes.enumerador;

/**
 *
 * @author previotto
 */
public enum ColorAzul {
    COLOR_UNO("rgba(7, 13, 89)"),
    COLOR_DOS("rgba(10, 110, 189)"),
    COLOR_TRES("rgba(90, 150, 227)"),
    COLOR_CUATRO("rgba(161, 194, 241)"),
    COLOR_CINCO("rgba(7, 13, 89, 0.5)"),
    COLOR_SEIS("rgba(10, 110, 189, 0.5)"),
    COLOR_SIETE("rgba(90, 150, 227, 0.5)"),
    COLOR_OCHO("rgba(161, 194, 241, 0.5)"),
    COLOR_NUEVE("#ffffff");
    
    //rgba(143, 169, 220) rgba(38, 94, 186)
    
    private final String color;

    // Constructor privado para asignar valores a las constantes
    private ColorAzul(String color) {
        this.color = color;
    }
    
    
    // Método para obtener el nombre asociado a la constante
    public String getColor() {
        return color;
    }
}

/*

COLOR_UNO("rgba(7, 13, 89)"),
    COLOR_DOS("rgba(61, 86, 178)"),
    COLOR_TRES("rgba(92, 122, 234)"),
    COLOR_CUATRO("rgba(230, 230, 230)"),
    COLOR_CINCO("rgba(7, 13, 89, 0.5)"),
    COLOR_SEIS("rgba(61, 86, 178, 0.5)"),
    COLOR_SIETE("rgba(92, 122, 234, 0.5)"),
    COLOR_OCHO("rgba(230, 230, 230, 0.5)"),
    COLOR_NUEVE("#ffffff");


COLOR_UNO("rgba(7, 13, 89)"),
    COLOR_DOS("rgba(31, 60, 136)"),
    COLOR_TRES("rgba(88, 147, 212)"),
    COLOR_CUATRO("rgba(206, 221, 239)"),
    COLOR_CINCO("rgba(7, 13, 89, 0.5)"),
    COLOR_SEIS("rgba(31, 60, 136, 0.5)"),
    COLOR_SIETE("rgba(88, 147, 212, 0.5)"),
    COLOR_OCHO("rgba(206, 221, 239, 0.5)"),
    COLOR_NUEVE("#ffffff");


COLOR_UNO("rgba(48, 84, 115)"),
    COLOR_DOS("rgba(116, 166, 220)"),
    COLOR_TRES("rgba(212, 231, 235)"),
    COLOR_CUATRO("rgba(249, 249, 249)"),
    COLOR_CINCO("rgba(48, 84, 115, 0.5)"),
    COLOR_SEIS("rgba(116, 166, 220, 0.5)"),
    COLOR_SIETE("rgba(212, 231, 235, 0.5)"),
    COLOR_OCHO("rgba(249, 249, 249, 0.5)"),
    COLOR_NUEVE("#ffffff");

*/
