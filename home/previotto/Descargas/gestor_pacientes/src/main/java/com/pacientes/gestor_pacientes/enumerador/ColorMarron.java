/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.pacientes.gestor_pacientes.enumerador;

/**
 *
 * @author previotto
 */
public enum ColorMarron {
    COLOR_UNO("rgba(64, 34, 24)"),
    COLOR_DOS("rgba(134, 84, 57)"),
    COLOR_TRES("rgba(198, 139, 89)"),
    COLOR_CUATRO("rgba(215, 177, 157)"),
    COLOR_CINCO("rgba(64, 34, 24, 0.5)"),
    COLOR_SEIS("rgba(134, 84, 57, 0.5)"),
    COLOR_SIETE("rgba(198, 139, 89, 0.5)"),
    COLOR_OCHO("rgba(215, 177, 157, 0.5)"),
    COLOR_NUEVE("#ffffff");
    
    
    private final String color;

    // Constructor privado para asignar valores a las constantes
    private ColorMarron(String color) {
        this.color = color;
    }
    
    
    // Método para obtener el nombre asociado a la constante
    public String getColor() {
        return color;
    }
}


/*

COLOR_UNO("rgba(119, 86, 111)"),
    COLOR_DOS("rgba(207, 182, 176)"),
    COLOR_TRES("rgba(231, 216, 210)"),
    COLOR_CUATRO("rgba(239, 233, 222)"),
    COLOR_CINCO("rgba(119, 86, 111, 0.5)"),
    COLOR_SEIS("rgba(207, 182, 176, 0.5)"),
    COLOR_SIETE("rgba(231, 216, 210, 0.5)"),
    COLOR_OCHO("rgba(239, 233, 222, 0.5)"),
    COLOR_NUEVE("#ffffff");


*/