package com.pacientes.gestor_pacientes.utilidades;

import java.util.concurrent.atomic.AtomicReference;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author previotto
 */
public interface DraggedScene {
     default void onDraggedScene(AnchorPane panelFather) {
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
            panelFather.setStyle("-fx-cursor: CLOSED_HAND;");
        });

        panelFather.setOnMouseReleased(e-> panelFather.setStyle("-fx-cursor: DEFAULT;"));


    }
}
