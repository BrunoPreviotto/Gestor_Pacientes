package com.pacientes.gestor_pacientes.utilidades;

import com.pacientes.gestor_pacientes.controlador.ClasePadreMenuInicio;
import com.pacientes.gestor_pacientes.servicios.ServiciosPadre;
import java.util.concurrent.atomic.AtomicReference;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author previotto
 */
public interface DraggedScene{
     default void onDraggedScene(AnchorPane panelFather) {
         ServiciosPadre sp = new ServiciosPadre();
         
         
         
        AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
        AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);
        
        panelFather.setOnMousePressed(e -> {
            
            Stage stage = (Stage) panelFather.getScene().getWindow();
            xOffset.set(stage.getX() - e.getScreenX());
            yOffset.set(stage.getY() - e.getScreenY());

        });

        panelFather.setOnMouseDragged(e -> {
            
            Stage stage = (Stage) panelFather.getScene().getWindow();
            stage.setX(e.getScreenX() + xOffset.get());
            stage.setY(e.getScreenY() + yOffset.get());
            panelFather.setStyle("-fx-cursor: CLOSED_HAND;" + sp.iniciarColorApp());
        });

         panelFather.setOnMouseReleased(e -> {
             panelFather.setStyle(sp.iniciarColorApp() + "-fx-cursor: DEFAULT;");
             //panelFather.setStyle("-fx-cursor: DEFAULT;");

         });


    }
}
