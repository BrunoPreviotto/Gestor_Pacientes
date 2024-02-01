/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.modelo;

import java.util.Map;

import com.pacientes.gestor_pacientes.enumerador.*;


/**
 *
 * @author previotto
 */
public class TemaAplicacion {
    private Map<Integer, String> color;

    
    public Map<Integer, String> getColor() {
        return color;
    }

    public void setColor(Map<Integer, String> color) {
        this.color = color;
    }
    
    public TemaAplicacion iniciarColor(int numeroColor){
        switch (numeroColor) {
            case 1:
                setColor(Map.of(1, ColorAnaranjado.COLOR_UNO.getColor(),
                        2, ColorAnaranjado.COLOR_DOS.getColor(),
                        3, ColorAnaranjado.COLOR_TRES.getColor(),
                        4, ColorAnaranjado.COLOR_CUATRO.getColor(),
                        5, ColorAnaranjado.COLOR_CINCO.getColor(),
                        6, ColorAnaranjado.COLOR_SEIS.getColor(),
                        7, ColorAnaranjado.COLOR_SIETE.getColor(),
                        8, ColorAnaranjado.COLOR_OCHO.getColor(),
                        9, ColorAnaranjado.COLOR_NUEVE.getColor()));
                break;
            case 2:
                setColor(Map.of(1, ColorOscuro.COLOR_UNO.getColor(),
                        2, ColorOscuro.COLOR_DOS.getColor(),
                        3, ColorOscuro.COLOR_TRES.getColor(),
                        4, ColorOscuro.COLOR_CUATRO.getColor(),
                        5, ColorOscuro.COLOR_CINCO.getColor(),
                        6, ColorOscuro.COLOR_SEIS.getColor(),
                        7, ColorOscuro.COLOR_SIETE.getColor(),
                        8, ColorOscuro.COLOR_OCHO.getColor(),
                        9, ColorOscuro.COLOR_NUEVE.getColor()));
                break;
            case 3:
                setColor(Map.of(1, ColorVerde.COLOR_UNO.getColor(),
                        2, ColorVerde.COLOR_DOS.getColor(),
                        3, ColorVerde.COLOR_TRES.getColor(),
                        4, ColorVerde.COLOR_CUATRO.getColor(),
                        5, ColorVerde.COLOR_CINCO.getColor(),
                        6, ColorVerde.COLOR_SEIS.getColor(),
                        7, ColorVerde.COLOR_SIETE.getColor(),
                        8, ColorVerde.COLOR_OCHO.getColor(),
                        9, ColorVerde.COLOR_NUEVE.getColor()));
                break;
           case 4:
                setColor(Map.of(1, ColorAzul.COLOR_UNO.getColor(),
                        2, ColorAzul.COLOR_DOS.getColor(),
                        3, ColorAzul.COLOR_TRES.getColor(),
                        4, ColorAzul.COLOR_CUATRO.getColor(),
                        5, ColorAzul.COLOR_CINCO.getColor(),
                        6, ColorAzul.COLOR_SEIS.getColor(),
                        7, ColorAzul.COLOR_SIETE.getColor(),
                        8, ColorAzul.COLOR_OCHO.getColor(),
                        9, ColorAzul.COLOR_NUEVE.getColor()));
                break;
          case 5:
                setColor(Map.of(1, ColorMarron.COLOR_UNO.getColor(),
                        2, ColorMarron.COLOR_DOS.getColor(),
                        3, ColorMarron.COLOR_TRES.getColor(),
                        4, ColorMarron.COLOR_CUATRO.getColor(),
                        5, ColorMarron.COLOR_CINCO.getColor(),
                        6, ColorMarron.COLOR_SEIS.getColor(),
                        7, ColorMarron.COLOR_SIETE.getColor(),
                        8, ColorMarron.COLOR_OCHO.getColor(),
                        9, ColorMarron.COLOR_NUEVE.getColor()));
                break;
        }
        
        return this;
    }
}
