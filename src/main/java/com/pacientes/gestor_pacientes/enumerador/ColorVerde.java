/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.pacientes.gestor_pacientes.enumerador;

/**
 *
 * @author previotto
 */
public enum ColorVerde {
    COLOR_UNO("rgb(59, 95, 65)"),
    COLOR_DOS("rgb(102, 169, 107)"),
    COLOR_TRES("rgb(152, 225, 154)"),
    COLOR_CUATRO("rgb(197, 245, 194)"),
    COLOR_CINCO("rgb(59, 95, 65, 0.5)"),
    COLOR_SEIS("rgb(102, 169, 107, 0.5)"),
    COLOR_SIETE("rgb(152, 225, 154, 107)"),
    COLOR_OCHO("rgb(197, 245, 194)"),
    COLOR_NUEVE("#ffffff");
    
    
    private final String color;

    // Constructor privado para asignar valores a las constantes
    private ColorVerde(String color) {
        this.color = color;
    }
    
    
    // Método para obtener el nombre asociado a la constante
    public String getColor() {
        return color;
    }
}

/*
COLOR_UNO("rgba(20, 49, 26)"),
    COLOR_DOS("rgba(184, 255, 198)"),
    COLOR_TRES("rgba(107, 207, 127)"),
    COLOR_CUATRO("rgba(224, 255, 231)"),
    COLOR_CINCO("rgba(20, 49, 26, 0.5)"),
    COLOR_SEIS("rgba(184, 255, 198, 0.5)"),
    COLOR_SIETE("rgba(107, 207, 127, 0.5)"),
    COLOR_OCHO("rgba(224, 255, 231, 0.5)"),
    COLOR_NUEVE("#ffffff");


COLOR_UNO("rgb(22, 48, 32)"),
    COLOR_DOS("rgb(48, 77, 48)"),
    COLOR_TRES("rgb(182, 196, 182)"),
    COLOR_CUATRO("rgb(238, 240, 229)"),
    COLOR_CINCO("rgb(22, 48, 32, 0.5)"),
    COLOR_SEIS("rgb(48, 77, 48, 0.5)"),
    COLOR_SIETE("rgb(182, 196, 182, 0.5)"),
    COLOR_OCHO("rgb(238, 240, 229, 0.5)"),
    COLOR_NUEVE("#ffffff");
*/