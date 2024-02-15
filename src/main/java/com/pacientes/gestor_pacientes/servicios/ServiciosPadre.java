/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.jfoenix.controls.JFXTextArea;
import com.pacientes.gestor_pacientes.controlador.ClasePadreController;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

import java.util.Objects;
import java.util.Random;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;

import javafx.scene.shape.Shape;

import java.util.List;
import javafx.scene.web.HTMLEditor;

/**
 *
 * @author previotto
 */
public class ServiciosPadre {
    
    public ServiciosPadre vaciarCajas(List<TextField> textfield) {
        for (TextField tf : textfield) {
            tf.setText("");
        }
        return this;
    }

    public ServiciosPadre vaciarCajasArea(List<TextArea> textArea) {
        for (TextArea ta : textArea) {
            ta.setText("");
        }
        return this;
    }
    
     public ServiciosPadre vaciarCajasAreaHTML(List<HTMLEditor> textArea) {
        for (HTMLEditor ta : textArea) {
            ta.setHtmlText("");
        }
        return this;
    }
     
     public ServiciosPadre vaciarCajasAreaJFX(List<JFXTextArea> textArea) {
        for (JFXTextArea ta : textArea) {
            ta.setText("");
        }
        return this;
    }

    public ServiciosPadre vaciarTablas(List<TableView> tableView) {
        for (TableView tv : tableView) {
            tv.getItems().clear();
        }
        return this;
    }

    public ServiciosPadre vaciarValorChoise(List<ChoiceBox> choiceBox) {
        for (ChoiceBox cb : choiceBox) {
            cb.setValue("");
        }
        return this;
    }
    
    public ServiciosPadre vaciarChoise(List<ChoiceBox> choiceBox) {
        for (ChoiceBox cb : choiceBox) {
            cb.getItems().setAll("");
            cb.valueProperty().set("");
        }
        return this;
    }
    

    public ServiciosPadre vaciarFechas(List<DatePicker> datePicker) {
        for (DatePicker dp : datePicker) {
            dp.setValue(LocalDate.now());
        }
        return this;
    }
    
    
    public ServiciosPadre pintarCajaVaciaImportante(List<TextField> cajas) {
        for (TextField caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServiciosPadre pintarCajaAreaVaciaImportante(List<TextArea> cajas) {
        for (TextArea caja : cajas) {
            
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServiciosPadre pintarCajaAreaVaciaImportanteJFX(List<JFXTextArea> cajas) {
        for (JFXTextArea caja : cajas) {
            
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    
    public ServiciosPadre pintarCajaAreaVaciaImportanteHTML(List<HTMLEditor> cajas) {
        for (HTMLEditor caja : cajas) {
            
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if((caja.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") 
                        && caja.getAccessibleHelp().equals("controlImportante"))
                        ||  (caja.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") 
                        && caja.getAccessibleHelp().equals("controlImportante")) 
                        || (caja.getHtmlText().isBlank() 
                        && caja.getAccessibleHelp().equals("controlImportante"))){
                    
                    caja.getStyleClass().add("cajasRellenarHtml");
                    
                    
               } 
            }
        }
        return this;
    }
    
    
    public ServiciosPadre pintarChoiseVacioImportante(List<ChoiceBox> choices) {
        for (ChoiceBox choice : choices) {
            if(Objects.nonNull (choice.getAccessibleHelp())){
               
                if (choice.getValue().equals("") && choice.getAccessibleHelp().equals("controlImportante")){
                    choice.getStyleClass().add("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    
    public ServiciosPadre desPintarCajaVaciaImportante(List<TextField> cajas) {
        for (TextField caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().remove("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    public ServiciosPadre desPintarCajaAreaVaciaImportante(List<TextArea> cajas) {
        for (TextArea caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().remove("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
     public ServiciosPadre desPintarCajaAreaVaciaImportanteJFX(List<JFXTextArea> cajas) {
        for (JFXTextArea caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
               
                if(caja.getText().isBlank() && caja.getAccessibleHelp().equals("controlImportante") && caja.getText().isBlank()){
                    caja.getStyleClass().remove("cajasARellenar");
               } 
            }
        }
        return this;
    }
     
     public ServiciosPadre desPintarCajaAreaVaciaImportanteHTML(List<HTMLEditor> cajas) {
        for (HTMLEditor caja : cajas) {
            if(Objects.nonNull(caja.getAccessibleHelp())){
                
                if(caja.getAccessibleHelp().equals("controlImportante")){
                    caja.getStyleClass().remove("cajasRellenarHtml");
                    for (String styleClas : caja.getStyleClass()) {
                       
                    }
               } 
            }
        }
        return this;
    }
    
    
    
    public ServiciosPadre desPintarChoiseVacioImportante(List<ChoiceBox> choices) {
        for (ChoiceBox choice : choices) {
            if(Objects.nonNull (choice.getAccessibleHelp())){
               
                if (choice.getValue().equals("") && choice.getAccessibleHelp().equals("controlImportante")){
                    choice.getStyleClass().remove("cajasARellenar");
               } 
            }
        }
        return this;
    }
    
    
    
    public ServiciosPadre visivilizarCajas(List<TextField> listaTextField){
        
        for (TextField tf : listaTextField) {
            if(tf.getStyleClass().contains("cajasAEsconder")){
                tf.setVisible(true);
            }
        }
        return this;
    }
    
    public ServiciosPadre esconderCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            
            if(tf.getStyleClass().contains("cajasAEsconder")){
                tf.setVisible(false);
            }
        }
        return this;
    }
    
    public ServiciosPadre visivilizarChoice(List<ChoiceBox> choiceBox){
        for (ChoiceBox cb : choiceBox) {
            if(cb.getStyleClass().contains("cajasAEsconder")){
                cb.setVisible(true);
            }
        }
        return this;
    }
    
    public ServiciosPadre esconderChoice(List<ChoiceBox> choiceBox){
        for (ChoiceBox cb : choiceBox) {
            if(cb.getStyleClass().contains("cajasAEsconder")){
                cb.setVisible(false);
            }
        }
        return this;
    }
    
    public ServiciosPadre deshabilitarCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            //tf.setDisable(true);
            tf.editableProperty().set(false);
        }
        return this;
    }
    
    public ServiciosPadre habilitarCajas(List<TextField> listaTextField){
        for (TextField tf : listaTextField) {
            tf.editableProperty().set(true);
        }
        return this;
    }
    
    public ServiciosPadre deshabilitarCajasArea(List<TextArea> listaTextArea){
        for (TextArea ta : listaTextArea) {
            ta.editableProperty().set(false);
        }
        
        return this;
    }
    
    public ServiciosPadre deshabilitarCajasAreaJFX(List<JFXTextArea> listaTextArea){
        for (JFXTextArea ta : listaTextArea) {
            ta.editableProperty().set(false);
        }
        
        return this;
    }
    
    public ServiciosPadre deshabilitarCajasAreaHTML(List<HTMLEditor> listaTextArea){
        for (HTMLEditor ta : listaTextArea) {
            //ta.setDisable(true);
            
            
        }
        
        return this;
    }
    
    public ServiciosPadre habilitarCajasArea(List<TextArea> listaTextArea){
        for (TextArea ta : listaTextArea) {
            ta.editableProperty().set(true);
        }
        
        return this;
    }
    
    public ServiciosPadre habilitarCajasAreaJFX(List<JFXTextArea> listaTextArea){
        for (JFXTextArea ta : listaTextArea) {
            ta.editableProperty().set(true);
        }
        
        return this;
    }
    
    public ServiciosPadre habilitarCajasAreaHTML(List<HTMLEditor> listaTextArea){
        for (HTMLEditor ta : listaTextArea) {
            ta.setDisable(false);
        }
        
        return this;
    }
    
     public ServiciosPadre deshabilitarBotones(List<Button> botones){
        for (Button boton : botones) {
            boton.setDisable(true);
        }
        return this;
    }
     
     public ServiciosPadre habilitarBotones(List<Button> botones){
        for (Button boton : botones) {
            boton.setDisable(false);
        }
        return this;
    }
     
     public ServiciosPadre habilitarBotonCrear(Button boton){
        boton.setDisable(false);
        return this;
    }
     
     public ServiciosPadre desHabilitarBotonCrear(Button boton){
        boton.setDisable(true);
        return this;
    }
     
     public ServiciosPadre habilitarEliminarActualizar(Button eliminar, Button actualizar){
        actualizar.setDisable(false);
        eliminar.setDisable(false);
        return this;
    }
     
     public ServiciosPadre desHabilitarEliminarActualizar(Button eliminar, Button actualizar){
        actualizar.setDisable(true);
        eliminar.setDisable(true);
        return this;
    }
    
    
    
    public static String CapitalCase(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split(" ");

        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                result.append(firstLetter).append(restOfWord).append(" ");
            }
        }

        return result.toString().trim();
    }
    
    
    public ServiciosPadre animarCajasAlDarABoton(List<TextField> listaCajas){
        for (TextField caja : listaCajas) {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), caja);
            st.setFromX(1);
            st.setToX(0.95);
            st.setCycleCount(2);
            st.setAutoReverse(true);
            st.play();
        }
        return this;
    }
    
     public ServiciosPadre animarCajasAreaAlDarABoton(List<TextArea> listaCajas){
        for (TextArea caja : listaCajas) {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), caja);
            st.setFromX(1);
            st.setToX(0.95);
            st.setCycleCount(2);
            st.setAutoReverse(true);
            st.play();
        }
        return this;
    }
    
    public ServiciosPadre animarChoiceAlDarABoton(List<ChoiceBox> listaChoiceBox){
        for (ChoiceBox choice : listaChoiceBox) {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), choice);
            st.setFromX(1);
            st.setToX(0.95);
            st.setCycleCount(2);
            st.setAutoReverse(true);
            st.play();
        }
        return this;
    }
    
    public String iniciarColorApp(){
        
        return "-fx-color-1: " + VariablesEstaticas.colorTeamaAplicacion.getColor().get(1) + ";" + 
                "-fx-color-2:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(2) + ";" +
                "-fx-color-3:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(3) + ";" +
                "-fx-color-4:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(4) + ";" +
                "-fx-color-1-opaque:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(5) + ";" +
                "-fx-color-2-opaque:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(6) + ";" +
                "-fx-color-3-opaque:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(7) + ";" +
                "-fx-color-4-opaque:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(8) + ";" +
                "-fx-color-fondo:" + VariablesEstaticas.colorTeamaAplicacion.getColor().get(9) + ";";

    }
    
    public void guardarBD(){
        UsuarioDAOImplementacion dao = new UsuarioDAOImplementacion();
        ClasePadreController cp = new ClasePadreController();
        
            try{
                String dbName = "gestion_pacientes";
                String dbUser = "root";
                String dbPassword = "";
                String backupPath = dao.obtenerRutaGuardarBD();
            
                // Construye el comando para ejecutar mysqldump
                String command = "mysqldump --user=" + dbUser + " --password=" + dbPassword + " " + dbName + " -r " + backupPath;

                // Ejecuta el comando
                Process process = Runtime.getRuntime().exec(command);

                // Espera a que el proceso termine
                int exitCode = process.waitFor();
                
                
                InputStream errorStream = process.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
                StringBuilder errorMessage = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                errorMessage.append(line).append("\n");
            }

                // Verifica si la copia de seguridad fue exitosa
            if (exitCode == 0) {
                
                //cp.mensajeAdvertenciaError("Copia de seguridad exitosa.", this, VariablesEstaticas.imgenExito);
                
            } else {
                cp.mensajeAdvertenciaError("Error al realizar la copia de seguridad.", this, VariablesEstaticas.imgenError);
            }
            }catch(IOException | InterruptedException e){
                e.printStackTrace();
            }
        
    }
    
    public AnchorPane imagenOjo(){
        double width = 100;
        
        
        
        Circle circle = new Circle(100, 100, 50);
        circle.setRadius(4);
        circle.getStyleClass().add("figuraBoton");
        AnchorPane.setTopAnchor(circle, 6.0);
        //AnchorPane.setBottomAnchor(circle, 0.0);
        AnchorPane.setLeftAnchor(circle, 7.0);
        //AnchorPane.setRightAnchor(circle, 0.0);
        circle.setStrokeWidth(3);
        
        CubicCurve parpadoSuperior = new CubicCurve(
                width * 0.15, width * 0.25,
                width * 0.2, width * 0.15,
                width * 0.3, width * 0.15,
                width * 0.35, width * 0.25
        );
        
         
        
        
        AnchorPane.setTopAnchor(parpadoSuperior, 1.0);
        //AnchorPane.setBottomAnchor(parpadoSuperior, 0.0);
        //AnchorPane.setLeftAnchor(parpadoSuperior, 0.0);
        AnchorPane.setRightAnchor(parpadoSuperior, 3.0);
        parpadoSuperior.getStyleClass().add("figuraBoton");
        parpadoSuperior.setStrokeWidth(3);
        

        // Crear el párpado inferior con CubicCurve
        CubicCurve parpadoInferior = new CubicCurve(
                width * 0.15, width * 0.25,
                width * 0.2, width * 0.35,
                width * 0.3, width * 0.35,
                width * 0.35, width * 0.25
        );
        
        parpadoInferior.getStyleClass().add("figuraBoton");
        parpadoInferior.setStrokeWidth(3);
        
        AnchorPane.setTopAnchor(parpadoInferior, 10.0);
        //AnchorPane.setBottomAnchor(parpadoInferior, 0.0);
        //AnchorPane.setLeftAnchor(parpadoInferior, 0.0);
        AnchorPane.setRightAnchor(parpadoInferior, 3.0);
        
        
        
        return new AnchorPane(parpadoInferior, parpadoSuperior, circle);
    }
    
    public AnchorPane imagenLapiz(){
        double CUERPO_ANCHO = 4;
        double CUERPO_LARGO = 20;
        double PUNTA_DIAMETRO = 3;
        
        double PARTE_SUPERIOR_LARGO = 5;
        
        AnchorPane anchor = new AnchorPane();
        
        Rectangle cuerpoLapiz = new Rectangle(CUERPO_ANCHO, CUERPO_LARGO);
        cuerpoLapiz.getStyleClass().add("figuraBoton");

        // Punta del lápiz (círculo)
        Circle puntaLapiz = new Circle(CUERPO_ANCHO / 2, CUERPO_LARGO, PUNTA_DIAMETRO / 2);
        puntaLapiz.getStyleClass().add("figuraDeco");

        // Parte superior de la lapicera (rectángulo)
        Rectangle parteSuperior = new Rectangle(CUERPO_ANCHO, PARTE_SUPERIOR_LARGO);
        parteSuperior.getStyleClass().add("figuraDeco");
        
        
        AnchorPane.setRightAnchor(cuerpoLapiz, 5.0);
        AnchorPane.setRightAnchor(puntaLapiz, 5.5);
        AnchorPane.setRightAnchor(parteSuperior, 5.0);
        
        AnchorPane.setBottomAnchor(cuerpoLapiz, 6.0);
        AnchorPane.setBottomAnchor(puntaLapiz, 3.5);
        AnchorPane.setBottomAnchor(parteSuperior, 20.0);
       
        anchor.getChildren().add(cuerpoLapiz);
        anchor.getChildren().add(puntaLapiz);
        anchor.getChildren().add(parteSuperior);

        return anchor;
    }
    
    public AnchorPane patronBurbujas(){
        AnchorPane pane = new AnchorPane();
        
        int numRows = 18;
        int numBubblesPerRow = 12;

        for (int row = 0; row < numRows; row++) {
            double rowRadiusMultiplier = 0.3 - 0.01 * (numRows - 0.3 - row); // Degradado hacia arriba en el tamaño de las burbujas
            double yOffset = row * 10.0 * Math.exp(-0.1 * row); // Espaciado vertical entre las filas

            for (int col = 0; col < numBubblesPerRow; col++) {
                double colRadiusMultiplier = 1.0 - 0.1 * col; // Degradado hacia la derecha en el tamaño de las burbujas
                double angle = ((double) col / numBubblesPerRow) * 2 * Math.PI;
                double x = col * 1.5 * 4.0 * 1.5; // Espaciado horizontal entre las burbujas

                double amplitude = 18.0;
                double frequency = 5.0; // Ajusta la frecuencia de la onda
                double yOffsetWave = Math.sin(angle * frequency) * amplitude;

                double y = row * 1 * amplitude + yOffset + yOffsetWave; // Espaciado vertical entre las filas

                double radius = 10.0 * rowRadiusMultiplier * colRadiusMultiplier;

                Ellipse bubble = createBubble(x, y, radius);
                pane.getChildren().add(bubble);
            }
         
        }
        
        AnchorPane.setTopAnchor(pane, 50.0);
        AnchorPane.setBottomAnchor(pane, -20.0);

        return pane;
    }
    
    private Ellipse createBubble(double x, double y, double radius) {
        Ellipse bubble = new Ellipse(x, y, radius, radius);
        
        bubble.getStyleClass().add("figuraDeco");
        bubble.setStrokeWidth(2.0);
        return bubble;
    }
    
    
    
    
    public AnchorPane patronVariasFiguras(){
        AnchorPane anchor = new AnchorPane();
        
         Random random = new Random();
         
         int cantidad = 200;
         
         int tamanoMinimo = 10; 
         double difuminacion = 0.5;

        

        for (int i = 0; i < cantidad; i++) {
            Shape figura;
           
            double posX = random.nextDouble() * (100 - tamanoMinimo);
            double posY = random.nextDouble() * (620 - tamanoMinimo);

            switch (random.nextInt(6)) {
                case 0:
                    figura = new Circle(tamanoMinimo / 2);
                    break;
                case 1:
                    figura = new Rectangle(tamanoMinimo, tamanoMinimo);
                    break;
                case 2:
                    figura = crearEstrella(tamanoMinimo);
                    break;
                case 3:
                    figura = new Rectangle(tamanoMinimo, tamanoMinimo);
                    ((Rectangle) figura).setRotate(45);
                    break;
                case 4:
                    figura = new Polygon(
                            -tamanoMinimo / 2, 0,
                            tamanoMinimo / 2, 0,
                            0, tamanoMinimo
                    );
                    break;
                default:
                    figura = new Polygon(
                            -tamanoMinimo / 2, 0,
                            tamanoMinimo / 2, 0,
                            0, tamanoMinimo
                    );
                    break;
            }

            figura.setFill(Color.BLACK);
            figura.setOpacity(1 - difuminacion);
            figura.setLayoutX(posX);
            figura.setLayoutY(posY);
            figura.getStyleClass().add("figuraDeco");
            anchor.getChildren().add(figura);
        }
        
        
        return anchor;
    }
    
   private Shape crearEstrella(double radio) {
        Polygon estrella = new Polygon();
        for (int i = 0; i < 5; i++) {
            double angle = Math.toRadians(72 * i - 18);
            double deltaX = Math.cos(angle) * radio;
            double deltaY = Math.sin(angle) * radio;
            estrella.getPoints().addAll(deltaX, deltaY);
        }
        return estrella;
    }
    
}
