/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.pacientes.gestor_pacientes.modelo.AccionesAgenda;
import com.pacientes.gestor_pacientes.servicios.ServiciosAgenda;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 *
 * @author previotto
 */
public class AgendaController extends MenuInicioController implements Initializable{
    
    private Object menu;
    private Stage stage;
    
    private ServiciosAgenda servicioAgenda = new ServiciosAgenda();
    
    private static int mesSeleccionado;
    private static int diaSeleccionado;
    private static int anoSeleccionado;
    
    private GridPane agenda;
    private AnchorPane anchorPrincipalAgenda;
    private AnchorPane anchorAgendaAgenda;
    @FXML
    private TextArea cajaAreaVerAccionAgenda;
    
    Pane calendarioCelda;
    
    private static LocalDate fechaActual = LocalDate.now();
    @FXML
    private Button botonCrearAccionAgenda;
    @FXML
    private Button botonActualizarAccionAgenda;
    @FXML
    private Button botonEliminarAccionAgenda;
    @FXML
    private Button botonRetornarAccionAgenda;
    
    List<TextArea> listaCajasAreaAccion;
    
    String cssCalendario = getClass().getResource("/com/pacientes/gestor_pacientes/styles/calendario.css").toExternalForm();
    @FXML
    private CheckBox checkRecordar;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   

    public int getMesSeleccionado() {
        return mesSeleccionado;
    }

    public void setMesSeleccionado(int mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    public int getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(int diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public int getAnoSeleccionado() {
        return anoSeleccionado;
    }

    public void setAnoSeleccionado(int anoSeleccionado) {
        this.anoSeleccionado = anoSeleccionado;
    }

   
    
    
    
    
    public void iniciarAdministrarAccion(Stage stage , Object obj, ImageView boton){
        this.menu = obj;
        this.stage = stage;
        fechaSeleccionado(boton);
        
        LocalDate d = LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);
        
        
        
        AccionesAgenda agenda = new AccionesAgenda();
        agenda.setFecha(LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado));
        
        
        
        try {
            agenda.setAccion(agendaDao.obtener(agenda).getAccion()); 
            if(Objects.nonNull(agenda.getAccion())){
                cajaAreaVerAccionAgenda.setText(agenda.getAccion());
            }
            if(cajaAreaVerAccionAgenda.getText().isBlank()){
                botonActualizarAccionAgenda.setDisable(true);
                botonCrearAccionAgenda.setDisable(false);
                botonEliminarAccionAgenda.setDisable(true);
            }else{
                botonActualizarAccionAgenda.setDisable(false);
                botonCrearAccionAgenda.setDisable(true);
                botonEliminarAccionAgenda.setDisable(false);
            }
            
        } catch (SQLException e) {
          
        }
    }
    
    

    public AgendaController() {
        this.agenda = VariablesEstaticas.gridAgenda;
        this.anchorPrincipalAgenda =  VariablesEstaticas.anchorPrincipalAgenda;
        this.anchorAgendaAgenda = VariablesEstaticas.anchorAgendaAgenda;
    }

    
   
    
    public void vaciarAgenda(){
        
        
        for (Node l : agenda.getChildren()) {
            //Button b = (Button) l;
            
           
            
            if(l.getStyleClass().equals("anchorCeldasCalendario")){
                AnchorPane p = (AnchorPane)l;
                for (Node n : p.getChildren()) {
                    System.out.println(n.getId());
                }
                
                
            }
        }
    }
    
    
    
    public AgendaController rellenarAgenda(int mesActualMayorMenor) {
// Obtiene el primer día del mes actual
        
        switch (mesActualMayorMenor) {
            case 1:
                
                break;
            case 2:
                
                
                fechaActual = fechaActual.plusMonths(1);
                //vaciarAgenda();
                agenda.getChildren().removeAll(agenda.getChildren());
                
                break;
           case 3:
               
                fechaActual = fechaActual.minusMonths(1);
                //vaciarAgenda();
                agenda.getChildren().removeAll(agenda.getChildren());
                
                break;
                
        }
        
        
        LocalDate firstDayOfMonth = fechaActual.withDayOfMonth(1);
        
        
        // Determina en qué día de la semana cae el primer día del mes
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();
        int column = dayOfWeek.getValue(); // 1 = Lunes, 2 = Martes, ..., 7 = Domingo

        
        // Agrega los días del mes al GridPane
        int diaMesSiguiete = 1;
        
        
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                
                Integer day = (row * 7) + col + 1 - column;
                

                AnchorPane anchorAgenda = aspectoCeldasCalendario(day);
                
                
                // Agrega el Label al GridPane
                agenda.add(anchorAgenda, col, row);

                
                diaMesSiguiete = setearLabelCeldasCalendario(anchorAgenda, day, firstDayOfMonth, diaMesSiguiete);;
                
            }
        }
        //agenda.setPadding(new Insets(10,10,10,10));
        
        // Actualiza el texto de la etiqueta de mes y año
        
        //monthLabel.setPadding(Insets.EMPTY);
        aspectoCalendario();
        mesSeleccionado = fechaActual.getMonth().getValue();
        anoSeleccionado = fechaActual.getYear();
        return this;
        
    }
    
    public int setearLabelCeldasCalendario(AnchorPane anchorAgenda, int day, LocalDate firstDayOfMonth, int diaMesSiguiete){
        
        //SI DIA ESTA DENTRO DEL MES ACTUAL
                if (day > 0 && day <= firstDayOfMonth.lengthOfMonth()) {
                    for (Node child : anchorAgenda.getChildren()) {
                        
                        if(child.getId().equals("dia")){
                            Label label = (Label)child;
                            label.setText(String.valueOf(day));
                            label.getStyleClass().add("labelCuerpo");
                            
                        }
                       
                    }
                //SI DIA ES MENOR AL MES ACTUAL    
                }else if(day <= 0){
                    for (Node child : anchorAgenda.getChildren()) {
                        
                        if(child.getId().equals("dia")){
                            Label label = (Label)child;
                            label.setText(String.valueOf(fechaActual.minusMonths(1).lengthOfMonth() - (day * -1) ));
                            label.getStylesheets().add(cssCalendario);
                            label.getStyleClass().add("labelDiaDeOtroMes");
                        }
                       
                       
                    }
                //SI DIA ES MAYO AL MES ACTUAL
                }else if(day >= firstDayOfMonth.lengthOfMonth()){
                    
                    for (Node child : anchorAgenda.getChildren()) {
                        
                        if(child.getId().equals("dia")){
                            Label label = (Label)child;
                            label.setText(String.valueOf(diaMesSiguiete));
                            label.getStylesheets().add(cssCalendario);
                            label.getStyleClass().add("labelDiaDeOtroMes");
                        }
                       
                       
                    }
                    diaMesSiguiete++;
                }
                return diaMesSiguiete;
    }
    
    public void aspectoCalendario() {
        
        for (Node n : anchorAgendaAgenda.getChildren()) {
            if (Objects.nonNull(n.getId())) {
                if (n.getId().equals("mes") || n.getId().equals("ano")) {
                    Label l = (Label) n;
                    l.setText("");
                }
            }
        }
        Label monthLabel = new Label();
        Label yearLabel = new Label();
        
        
        yearLabel.setText(String.valueOf(fechaActual.getYear()));
        yearLabel.setTranslateX(500);
        yearLabel.setTranslateY(5);
        yearLabel.getStylesheets().add(cssCalendario);
        yearLabel.getStyleClass().add("anoCalendario");
        yearLabel.setId("ano");
        
        
        monthLabel.setText(mesAEspanol(fechaActual.getMonth().toString()));
        monthLabel.setTranslateX(25);
        monthLabel.setTranslateY(35);
        monthLabel.getStylesheets().add(cssCalendario);
        monthLabel.getStyleClass().add("mesCalendario");
        monthLabel.setId("mes");
        
        
        anchorAgendaAgenda.getChildren().add(monthLabel);
        anchorAgendaAgenda.getChildren().add(yearLabel);
    }
    
    public AnchorPane aspectoCeldasCalendario(int dia) {
        
        
        
        Image imagen = new Image("/com/pacientes/gestor_pacientes/img/ver.png");
        ImageView agregarImagen = new ImageView(imagen);
        
        Label dayLabel = new Label();
        Pane pane = new Pane();
        AnchorPane anchorAgenda = new AnchorPane();

        // Establece el estilo del Label
        dayLabel.getStylesheets().add(cssCalendario);
        dayLabel.getStyleClass().add("etiquetaDia");
        dayLabel.setTranslateX(10);
        dayLabel.setTranslateY(5);
        dayLabel.setId("dia");

        agregarImagen.setFitWidth(30);
        agregarImagen.setFitHeight(30);
        agregarImagen.setOnMouseClicked(this::administraAccion);
        agregarImagen.setTranslateX(95);
        agregarImagen.setTranslateY(50);
        agregarImagen.setId("boton" + dia + fechaActual.getMonth().getValue() + fechaActual.getYear());
        
        anchorAgenda.getChildren().add(dayLabel);
        anchorAgenda.getStylesheets().add(cssCalendario);
        anchorAgenda.setId("anchorCeldasCalendario");
        anchorAgenda.setMaxSize(135, 81);
        
        
        if (dia > 0 && dia <= fechaActual.lengthOfMonth()) {
            if (dia % 2 != 0) {
                
                anchorAgenda.getChildren().add(agregarImagen);
                anchorAgenda.getStyleClass().add("calendarioCeldaImpar");
            } else {
                
                anchorAgenda.getChildren().add(agregarImagen);
                anchorAgenda.getStyleClass().add("calendarioCeldaPar");
            }
        }else {
            anchorAgenda.getStyleClass().add("calendarioCeldaDistintomes");
        }

        return anchorAgenda;
    }
    
    
    
    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
    
    public String mesAEspanol(String mes){
        
       
        switch (mes) {
            case "MAY":
                return "Mayo";
            case "JUNE":
                return "Junio";
            case "JULY":
                return "Julio";
            case "AUGUST":
                return "Agosto";
            case "SEPTEMBER":
                return "Septiembre";
            case "OCTOBER":
                return "Octubre";
            case "NOVEMBER":
                return "Noviembre";
            case "DECEMBER":
                return "Diciembre";
            case "JANUARY":
                return "Enero";
            case "FEBRUARY":
                return "Febrero";
            case "MARCH":
                return "Marzo";
            case "APRIL":
                return "Abril";
            default:
                return "Sin fecha";
        }
    }
    
    
    
    
    
    


    @FXML
    protected void salir(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    
    
    public void fechaSeleccionado(ImageView b){
        
        AnchorPane a =  (AnchorPane)  b.getParent();
        LocalDate fecha;
       
        for (Node child : a.getChildren()) {
            
            if(child.getId().equals("dia")){
                Label dia = (Label) child;
                diaSeleccionado = Integer.parseInt(dia.getText());
            }
        }
       
    }

    @FXML
    private void crearAccionAgenda(MouseEvent event) {
        LocalDate fechaCrear =  LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);
        listaCajasAreaAccion = Arrays.asList(cajaAreaVerAccionAgenda);
        if(cajaAreaVerAccionAgenda.getText().isBlank()){
            mensaje("Ingresar datos para crear acción", this, "/com/pacientes/gestor_pacientes/img/error.png");
            servicioAgenda.
                    pintarCajaAreaVaciaImportante(listaCajasAreaAccion);
            
        }else{
            AccionesAgenda accion = 
                    new AccionesAgenda(
                            cajaAreaVerAccionAgenda.getText(), 
                            fechaCrear, 
                            checkRecordar.isSelected());
           if(Objects.nonNull(accion.getAccion())){
                try {
                 agendaDao.insertar(accion, 0);
                 mensaje("Éxito al crear accion", this, "/com/pacientes/gestor_pacientes/img/error.png");
                 botonActualizarAccionAgenda.setDisable(false);
                 botonEliminarAccionAgenda.setDisable(false);
                 botonCrearAccionAgenda.setDisable(true);
                } catch (Exception e) {
                    mensaje("Error al crear accion", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
            
           }else{
               mensaje("Error al crear accion", this, "/com/pacientes/gestor_pacientes/img/error.png");
           }
        }
    }

    @FXML
    private void actualizarAccionAgenda(MouseEvent event) {
        LocalDate fechaCrear = LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);
        listaCajasAreaAccion = Arrays.asList(cajaAreaVerAccionAgenda);
        if (cajaAreaVerAccionAgenda.getText().isBlank()) {
            mensaje("Ingresar datos para actualizar acción", this, "/com/pacientes/gestor_pacientes/img/error.png");
            servicioAgenda.
                    pintarCajaAreaVaciaImportante(listaCajasAreaAccion);

        } else {
            AccionesAgenda accion
                    = new AccionesAgenda(
                            cajaAreaVerAccionAgenda.getText(),
                            fechaCrear);
            if (Objects.nonNull(accion.getAccion())) {
                try {
                    agendaDao.actualizar(accion, 0);
                    mensaje("Éxito al actualizar accion", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    cajaAreaVerAccionAgenda.setText(agendaDao.obtener(accion).getAccion());
                    
                } catch (Exception e) {
                    mensaje("Error al actualizar accion", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }

            } else {
                mensaje("Error al actualizar accion", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        }
    }

    @FXML
    private void eliminarAccionAgenda(MouseEvent event) {
        
        LocalDate fechaCrear =  LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);
        
        if(cajaAreaVerAccionAgenda.getText().isBlank()){
            mensaje("No hay accion para eliminar", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }else{
            AccionesAgenda accion = 
                    new AccionesAgenda(
                            cajaAreaVerAccionAgenda.getText(), 
                            fechaCrear);
           if(Objects.nonNull(accion.getAccion())){
                try {
                 agendaDao.eliminar(accion, 0);
                 mensaje("Éxito al eliminar acción", this, "/com/pacientes/gestor_pacientes/img/error.png");
                 cajaAreaVerAccionAgenda.setText("");
                 botonActualizarAccionAgenda.setDisable(true);
                 botonEliminarAccionAgenda.setDisable(true);
                 botonCrearAccionAgenda.setDisable(false);
                } catch (Exception e) {
                    mensaje("Error al eliminar acción", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
            
           }else{
               mensaje("Error al eliminar acción", this, "/com/pacientes/gestor_pacientes/img/error.png");
           }
        }
        
    }

    @FXML
    private void retornarAccionAgenda(MouseEvent event) {
    }

    
    
    
    
}
