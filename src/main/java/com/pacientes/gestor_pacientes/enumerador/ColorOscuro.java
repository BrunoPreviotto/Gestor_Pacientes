/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.pacientes.gestor_pacientes.enumerador;

/**
 *
 * @author previotto
 */
public enum ColorOscuro {
    COLOR_UNO("rgba(33, 42, 62)"),
    COLOR_DOS("rgba(57, 72, 103)"),
    COLOR_TRES("rgba(155, 164, 181)"),
    COLOR_CUATRO("rgba(241, 246, 249)"),
    COLOR_CINCO("rgba(33, 42, 62, 0.5)"),
    COLOR_SEIS("rgba(57, 72, 103, 0.5)"),
    COLOR_SIETE("rgba(155, 164, 181, 0.5)"),
    COLOR_OCHO("rgba(241, 246, 249, 0.5)"),
    COLOR_NUEVE("#ffffff");
    
    
    private final String color;

    // Constructor privado para asignar valores a las constantes
    private ColorOscuro(String color) {
        this.color = color;
    }
    
    
    // Método para obtener el nombre asociado a la constante
    public String getColor() {
        return color;
    }
}




/*

COLOR_UNO("rgba(33, 42, 62)"),
    COLOR_DOS("rgba(57, 72, 103)"),
    COLOR_TRES("rgba(155, 164, 181)"),
    COLOR_CUATRO("rgba(155, 164, 181)"),
    COLOR_CINCO("rgba(33, 42, 62, 0.5)"),
    COLOR_SEIS("rgba(57, 72, 103, 0.5)"),
    COLOR_SIETE("rgba(155, 164, 181, 0.5)"),
    COLOR_OCHO("rgba(155, 164, 181, 0.5)"),
    COLOR_NUEVE("#ffffff")


COLOR_UNO("rgba(66, 66, 66)"),
    COLOR_DOS("rgba(161, 161, 161)"),
    COLOR_TRES("rgba(208, 208, 208)"),
    COLOR_CUATRO("rgba(232, 232, 232)"),
    COLOR_CINCO("rgba(66, 66, 66, 0.5)"),
    COLOR_SEIS("rgba(161, 161, 161, 0.5)"),
    COLOR_SIETE("rgba(208, 208, 208, 0.5)"),
    COLOR_OCHO("rgba(232, 232, 232, 0.5)"),



COLOR_UNO("rgba(66, 66, 66)"),
    COLOR_DOS("rgba(161, 161, 161)"),
    COLOR_TRES("rgba(208, 208, 208)"),
    COLOR_CUATRO("rgba(232, 232, 232)"),
    COLOR_CINCO("rgba(66, 66, 66, 0.5)"),
    COLOR_SEIS("rgba(161, 161, 161, 0.5)"),
    COLOR_SIETE("rgba(208, 208, 208, 0.5)"),
    COLOR_OCHO("rgba(232, 232, 232, 0.5)"),
    COLOR_NUEVE("#ffffff");






*/