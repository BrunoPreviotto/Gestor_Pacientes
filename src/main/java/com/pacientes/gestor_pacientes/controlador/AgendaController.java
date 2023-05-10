/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author previotto
 */
public class AgendaController {
    private GridPane agenda;

    public AgendaController(GridPane agenda) {
        this.agenda = agenda;
    }

    
    
    public void rellenarAgenda(){
        
        Button calendarioPar;
        AnchorPane calendarioImpar = new AnchorPane();
        
        String cssPar = getClass().getResource("/com/pacientes/gestor_pacientes/styles/calendario.css").toExternalForm();
        
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 5; j++) {
                if (j % 2 == 0) {
                    if (i % 2 == 0) {
                        calendarioPar = new Button();
                        calendarioPar.getStylesheets().add(cssPar);
                        calendarioPar.getStyleClass().add("calendarioPar");
                        calendarioPar.setOpacity(0.5);
                        agenda.add(calendarioPar, i, j);
                    }

                }else{
                    if (i % 2 != 0) {
                        calendarioPar = new Button();
                        calendarioPar.getStylesheets().add(cssPar);
                        calendarioPar.getStyleClass().add("calendarioPar");
                        calendarioPar.setOpacity(0.5);
                        agenda.add(calendarioPar, i, j);
                    }
                }
            }
        }
    }
    
    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
    
}
