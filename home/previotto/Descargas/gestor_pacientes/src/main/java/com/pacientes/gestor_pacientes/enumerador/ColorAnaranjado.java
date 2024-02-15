/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.pacientes.gestor_pacientes.enumerador;

/**
 *
 * @author previotto
 */
public enum ColorAnaranjado {
    COLOR_UNO("#C02A00"),
    COLOR_DOS("rgba(224, 111, 78)"),
    COLOR_TRES("rgba(255, 179, 156)"),
    COLOR_CUATRO("rgba(255, 236, 231)"),
    COLOR_CINCO("rgba(192, 42, 0, 0.5)"),
    COLOR_SEIS("rgba(224, 111, 78, 0.5)"),
    COLOR_SIETE("rgba(255, 179, 156, 0.5)"),
    COLOR_OCHO("rgba(255, 236, 231, 0.5)"),
    COLOR_NUEVE("#ffffff");
    
    
    private final String color;

    // Constructor privado para asignar valores a las constantes
    private ColorAnaranjado(String color) {
        this.color = color;
    }
    
    
    // Método para obtener el nombre asociado a la constante
    public String getColor() {
        return color;
    }
    
}
