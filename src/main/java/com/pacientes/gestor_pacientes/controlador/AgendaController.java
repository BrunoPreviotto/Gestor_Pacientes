/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;



import com.pacientes.gestor_pacientes.modelo.AccionesAgenda;
import com.pacientes.gestor_pacientes.servicios.ServiciosAgenda;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.net.URL;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;


import java.util.Arrays;
import java.util.List;

import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;


import javafx.stage.Stage;



/**
 *
 * @author previotto
 */
public class AgendaController extends MenuInicioController implements Initializable{
    
    private String hboxAbiertoParaEditarAccion;
    
    private static ImageView agregarImagen;
    private  static ImageView recordarImagen;
   
     
    @FXML
    protected AnchorPane imagenOjoVer; 
    
    @FXML
    protected AnchorPane anchorAccionVer;
    
    
    private Object menu;
    private Stage stage;
    
    private ServiciosAgenda servicioAgenda = new ServiciosAgenda();
    
    private static int mesSeleccionado;
    private static int diaSeleccionado;
    private static int anoSeleccionado;
    
    private HBox hboxPrecionadoParaEditarOverAccion;
    private HBox hboxPrecionadoRecordatorio;
    
    private GridPane agenda;
    private AnchorPane anchorPrincipalAgenda;
    private VBox anchorAgendaAgenda;
    @FXML
    private TextArea cajaAreaVerAccionAgenda;
    
    //Pane calendarioCelda;
    
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
    @FXML
    private ScrollPane panelScrollBotonesAcciones;
    @FXML
    private TextField CajaHora;
    @FXML
    private TextField cajaMinutos;
    
    @FXML
    private TextField CajaId;
    
    @FXML
    private AnchorPane anchorScroll;

    public String getHboxAbiertoParaEditarAccion() {
        return hboxAbiertoParaEditarAccion;
    }

    public void setHboxAbiertoParaEditarAccion(String hboxAbiertoParaEditarAccion) {
        this.hboxAbiertoParaEditarAccion = hboxAbiertoParaEditarAccion;
    }
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       anchorAccionVer.setStyle(servicioPadre.iniciarColorApp());
       
    }   

    public HBox getHboxPrecionadoRecordatorio() {
        return hboxPrecionadoRecordatorio;
    }

    public void setHboxPrecionadoRecordatorio(HBox hboxPrecionadoRecordatorio) {
        this.hboxPrecionadoRecordatorio = hboxPrecionadoRecordatorio;
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

    public HBox getHboxPrecionadoParaEditarOverAccion() {
        return hboxPrecionadoParaEditarOverAccion;
    }

    public void setHboxPrecionadoParaEditarOverAccion(HBox hboxPrecionadoParaEditarOverAccion) {
        this.hboxPrecionadoParaEditarOverAccion = hboxPrecionadoParaEditarOverAccion;
    }

   
    
    
    
    
    public void iniciarAdministrarAccion(Stage stage , Object obj, HBox hbox){
        this.menu = obj;
        this.stage = stage;
        fechaSeleccionado(hbox);
        iniciarAccionesConHoraYRecordatorio();
        anchorAccionVer.setStyle(servicioPadre.iniciarColorApp());
        
        Stage newStage = new Stage();
       newStage.initOwner(stage.getOwner());
       
        Scene ownerScene = stage.getOwner().getScene();
       ownerScene.getRoot().setStyle(servicioPadre.iniciarColorApp());

       
       this.onDraggedScene(anchorAccionVer);
        // ... (configuración adicional para el nuevo Stage)

        // Mostrar el nuevo Stage
        
        
        //newStage.show();
        
    }
    
    public void iniciarAccionesConHoraYRecordatorio() {
       
        anchorScroll.getChildren().clear();
        LocalDate d = LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);

        AccionesAgenda agenda = new AccionesAgenda();
        agenda.setFecha(LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado));

        VBox vbAccionesPOrDia = new VBox();
        
        try {
            List<AccionesAgenda> listaAccion = agendaDao.obtenerLista(agenda);
            if (Objects.nonNull(listaAccion.get(0))) {
                
                
                Label labelIdCabecera = new Label("ID");
                labelIdCabecera.setPrefSize(60, 30);
                labelIdCabecera.getStyleClass().add("cabeceraVerCambiarAcciones");

                HBox hbIdAccionesDiaCabecera = new HBox(labelIdCabecera);
                hbIdAccionesDiaCabecera.setId("hbIdCabecera");
                hbIdAccionesDiaCabecera.setPadding(new Insets(1));


                Label labelHoraCabecera = new Label("Hora");
                labelHoraCabecera.setPrefSize(60, 30);
                labelHoraCabecera.getStyleClass().add("cabeceraVerCambiarAcciones");

                HBox hbHoraAccionesDiaCabecera = new HBox(labelHoraCabecera);
                hbHoraAccionesDiaCabecera.setId("hbHoraCabecera");
                hbHoraAccionesDiaCabecera.setPadding(new Insets(1));

                Label labelAccionCabecera = new Label("Acción");
                labelAccionCabecera.setPrefSize(800, 30);
                labelAccionCabecera.getStyleClass().add("cabeceraVerCambiarAcciones");

                HBox hbAccionAccionesDiaCabecera = new HBox(labelAccionCabecera);
                hbAccionAccionesDiaCabecera.setId("hbAccionCabecera");
                hbAccionAccionesDiaCabecera.setPadding(new Insets(1));
                
                
                
                Label labelRecordatorioCabecera = new Label("Recordatorio");
                labelRecordatorioCabecera.setPrefSize(88, 30);
                labelRecordatorioCabecera.getStyleClass().add("cabeceraVerCambiarAcciones");

                HBox hbRecordarAccionesDiaCabecera = new HBox(labelRecordatorioCabecera);
                hbRecordarAccionesDiaCabecera.setId("hbREcordarCabecera");
                hbRecordarAccionesDiaCabecera.setPadding(new Insets(1));
                
                HBox hbConjuntoAccionHoraRecordarCabecera = new HBox();
                hbConjuntoAccionHoraRecordarCabecera.getChildren().add(hbIdAccionesDiaCabecera);
                hbConjuntoAccionHoraRecordarCabecera.getChildren().add(hbHoraAccionesDiaCabecera);
                hbConjuntoAccionHoraRecordarCabecera.getChildren().add(hbAccionAccionesDiaCabecera);
                hbConjuntoAccionHoraRecordarCabecera.getChildren().add(hbRecordarAccionesDiaCabecera);
                
                vbAccionesPOrDia.getChildren().add(hbConjuntoAccionHoraRecordarCabecera);
                
                for (AccionesAgenda accion : listaAccion) {
                    
                    Label labelId = new Label(String.valueOf((accion.getId())));
                    labelId.setPrefSize(60, 60);
                    labelId.getStyleClass().add("botonHoraYFecha");
                    
                    HBox hbIdAccionesDia = new HBox(labelId);
                    hbIdAccionesDia.setId("hbId");
                    hbIdAccionesDia.setPadding(new Insets(1));
                    
                    
                    Label labelHora = new Label(String.valueOf(accion.getFechaYHora().getHour() + ":" + String.format("%02d", accion.getFechaYHora().getMinute())));
                    labelHora.setPrefSize(60, 60);
                    labelHora.getStyleClass().add("botonHoraYFecha");
                    
                    HBox hbHoraAccionesDia = new HBox(labelHora);
                    hbHoraAccionesDia.setId("hbHora");
                    hbHoraAccionesDia.setPadding(new Insets(1));
                    
                    Label labelAccion = new Label(accion.getAccion());
                    labelAccion.setPrefSize(800, 60);
                    labelAccion.getStyleClass().add("botonHoraYFecha");
                    
                    HBox hbAccionAccionesDia = new HBox(labelAccion);
                    hbAccionAccionesDia.setId("hbAccion");
                    hbAccionAccionesDia.setPadding(new Insets(1));
                    
                    

                    CheckBox checkRecordarPorAccion = new CheckBox();
                    checkRecordarPorAccion.getStyleClass().add("cbPorAccion");
                    checkRecordarPorAccion.setDisable(true);
                    checkRecordarPorAccion.setPrefSize(88, 60);
                    checkRecordarPorAccion.setSelected(accion.getRecordar());
                    checkRecordarPorAccion.setPadding(new Insets(1));
                    
                    
                    HBox hbRecordatorioAccionesDia = new HBox(checkRecordarPorAccion);
                    hbRecordatorioAccionesDia.setId("hbRecordatorio");
                    hbRecordatorioAccionesDia.setAlignment(Pos.BASELINE_CENTER);
                    
                    

                    
                    
                    
                    HBox hbConjuntoAccionHoraRecordar = new HBox();
                    hbConjuntoAccionHoraRecordar.getChildren().add(hbIdAccionesDia);
                    hbConjuntoAccionHoraRecordar.getChildren().add(hbHoraAccionesDia);
                    hbConjuntoAccionHoraRecordar.getChildren().add(hbAccionAccionesDia);
                    hbConjuntoAccionHoraRecordar.getChildren().add(hbRecordatorioAccionesDia);
                    
                    hbConjuntoAccionHoraRecordar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            seleccionarAccion(event);
                        }
                    });
                    
                    vbAccionesPOrDia.getChildren().add(hbConjuntoAccionHoraRecordar);

                }

                

                
                
               

                anchorScroll.getChildren().add(vbAccionesPOrDia);
            }

            if (cajaAreaVerAccionAgenda.getText().isBlank()) {
                botonActualizarAccionAgenda.setDisable(true);
                botonCrearAccionAgenda.setDisable(false);
                botonEliminarAccionAgenda.setDisable(true);
            } 

        } catch (Exception e) {
        }
    }

    public AgendaController() {
        this.agenda = VariablesEstaticas.gridAgenda;
        this.anchorPrincipalAgenda = VariablesEstaticas.anchorPrincipalAgenda;
        this.anchorAgendaAgenda = VariablesEstaticas.anchorAgendaAgenda;
    }

    
   public void seleccionarAccion(MouseEvent event){
       HBox hbAccion = (HBox)event.getSource();
       botonCrearAccionAgenda.setDisable(true);
       botonActualizarAccionAgenda.setDisable(false);
       botonEliminarAccionAgenda.setDisable(false);
       for (Node node : hbAccion.getChildren()) {
           Label label;
           HBox hb;
           CheckBox cb;
           
           if(node.getId().equals("hbHora")){
               
               hb = (HBox)node;
               label = (Label)hb.getChildren().get(0);
               String[] textoBoton = label.getText().split(":");
               CajaHora.setText(textoBoton[0]);
               cajaMinutos.setText(textoBoton[1]);
           }
           
           if(node.getId().equals("hbAccion")){
               
               hb = (HBox)node;
               label = (Label)hb.getChildren().get(0);
               
               cajaAreaVerAccionAgenda.setText(label.getText());
               
           }
           
           if(node.getId().equals("hbRecordatorio")){
               
               hb = (HBox)node;
               cb = (CheckBox)hb.getChildren().get(0);
               checkRecordar.setSelected(cb.isSelected());
           }
           
           if(node.getId().equals("hbId")){
               
               hb = (HBox)node;
               label = (Label)hb.getChildren().get(0);
               
               CajaId.setText(label.getText());
               
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
                
                agenda.getChildren().removeAll(agenda.getChildren());
                
                break;
           case 3:
               
                fechaActual = fechaActual.minusMonths(1);
                
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
            for (int col = 0; col < 7; col++) {
                
                Integer day = (row * 7) + col + 1 - column;
                

                VBox vBoxAgenda = aspectoCeldasCalendario(day);
                
                
                // Agrega el Label al GridPane
                agenda.add(vBoxAgenda, col, row);
                
                
                diaMesSiguiete = setearLabelCeldasCalendario(vBoxAgenda, day, firstDayOfMonth, diaMesSiguiete);
                
            }
        }
        
        
        
        aspectoCalendario();
        mesSeleccionado = fechaActual.getMonth().getValue();
        anoSeleccionado = fechaActual.getYear();
        return this;
        
    }
    
    public int setearLabelCeldasCalendario(VBox vBoxAgenda, int day, LocalDate firstDayOfMonth, int diaMesSiguiete){
        
        //SI DIA ESTA DENTRO DEL MES ACTUAL
                if (day > 0 && day <= firstDayOfMonth.lengthOfMonth()) {
                    for (Node child : vBoxAgenda.getChildren()) {
                        VBox vbagenda = (VBox)child;
                        for (Node node : vbagenda.getChildren()) {
                            HBox hb = (HBox)node;
                            if (Objects.nonNull(hb.getId())) {
                                if (hb.getId().equals("dia")) {
                                    Label label = (Label) hb.getChildren().get(0);
                                    label.setText(String.valueOf(day));
                                    label.getStyleClass().add("labelCuerpo");

                                }
                            }
                        }
                       
                    }
                //SI DIA ES MENOR AL MES ACTUAL    
                }else if(day <= 0){
                    for (Node child : vBoxAgenda.getChildren()) {
                        VBox vbagenda = (VBox)child;
                        for (Node node : vbagenda.getChildren()) {
                            HBox hb = (HBox)node;
                            if (Objects.nonNull(hb.getId())) {
                                if (hb.getId().equals("dia")) {
                                    Label label = (Label) hb.getChildren().get(0);
                                    label.setText(String.valueOf(fechaActual.minusMonths(1).lengthOfMonth() - (day * -1)));
                                    label.getStylesheets().add(cssCalendario);
                                    label.getStyleClass().add("labelDiaDeOtroMes");
                                }
                            }
                        }
                       
                       
                    }
                //SI DIA ES MAYO AL MES ACTUAL
                }else if(day >= firstDayOfMonth.lengthOfMonth()){
                    
                    for (Node child : vBoxAgenda.getChildren()) {
                        VBox vbagenda = (VBox)child;
                        for (Node node : vbagenda.getChildren()) {
                            HBox hb = (HBox)node;
                            if (Objects.nonNull(hb.getId())) {
                                if (hb.getId().equals("dia")) {
                                    Label label = (Label) hb.getChildren().get(0);
                                    label.setText(String.valueOf(diaMesSiguiete));
                                    label.getStylesheets().add(cssCalendario);
                                    label.getStyleClass().add("labelDiaDeOtroMes");
                                }
                            }
                        }
                       
                       
                    }
                    diaMesSiguiete++;
                }
                return diaMesSiguiete;
    }
    
    public void aspectoCalendario() {
        int existeMesYano = 0;
        for (Node n : anchorAgendaAgenda.getChildren()) {
           

            if (Objects.nonNull(n.getId())) {
                if (n.getId().equals("hboxMes")) {
                    existeMesYano++;
                    HBox hboxMes = (HBox) n;
                    Label mes = (Label) hboxMes.getChildren().get(0);
                    mes.setText(mesAEspanol(fechaActual.getMonth().toString()));
                    mes.getStyleClass().add("labelCuerpo");

                }
                if (n.getId().equals("hboxAno")) {
                    existeMesYano++;
                    HBox hboxAno = (HBox) n;
                    Label ano = (Label) hboxAno.getChildren().get(0);
                    ano.setText(String.valueOf(fechaActual.getYear()));
                    ano.getStyleClass().add("labelCuerpo");

                }
            }

        }

        if (existeMesYano == 0) {

            Label mes = new Label();
            mes.setText(mesAEspanol(fechaActual.getMonth().toString()));
            mes.setTranslateX(10);
            mes.getStylesheets().add(cssCalendario);
            mes.getStyleClass().add("mesCalendario");
            mes.setId("mes");
            HBox hBoxMes = new HBox(mes);
            hBoxMes.setId("hboxMes");
            hBoxMes.setAlignment(Pos.BASELINE_LEFT);
            mes.getStyleClass().add("labelCuerpo");

            Label ano = new Label();
            ano.setText(String.valueOf(fechaActual.getYear()));
            ano.getStylesheets().add(cssCalendario);
            ano.getStyleClass().add("anoCalendario");
            ano.setId("ano");
            HBox hBoxAno = new HBox(ano);
            hBoxAno.setId("hboxAno");
            hBoxAno.setAlignment(Pos.BASELINE_CENTER);
            ano.getStyleClass().add("labelCuerpo");

            anchorAgendaAgenda.getChildren().add(0, hBoxAno);
            anchorAgendaAgenda.getChildren().add(1, hBoxMes);

        }

    }
    
    public VBox aspectoCeldasCalendario(int dia) {
        
        
        
       
        
        Label dayLabel = new Label();
        Pane pane = new Pane();
        VBox vBoxAgenda = new VBox();
        

        // Establece el estilo del Label
        dayLabel.getStylesheets().add(cssCalendario);
        dayLabel.getStyleClass().add("etiquetaDia");
        dayLabel.setId("dia");
        
        HBox hboxLabel = new HBox(dayLabel);
        hboxLabel.setId("dia");
        hboxLabel.setPadding(new Insets(0, 0, 0, 5));
        
        //IMAGEN DE RECORDAR ACCION
         recordarImagen = new ImageView(VariablesEstaticas.imagenRecordar);
        recordarImagen.setFitWidth(20);
        recordarImagen.setFitHeight(20);
        recordarImagen.setVisible(false);
        
        
        //SI EL DIA NO EXEDE EL MES ACUTUAL O DA VALORES NEGATIVOSS
        if (dia > 0 && dia <= fechaActual.lengthOfMonth()) {
            //CONSULTA SI EXISTE UN RECORDATORIO PARA LA CELDA CONCURRENTE
             //LOCAL DATE CON LA FECHA DE LA CELDA CONCURRENTE
            LocalDate localDateConcurrente = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth().getValue(), dia);
            try {
                if (agendaDao.obtenerRecordatorio(localDateConcurrente)) {
                    recordarImagen.setVisible(true);
                }
            } catch (Exception e) {
            }
        }

        
        
        HBox hbRecordatorio = new HBox(recordarImagen);
        hbRecordatorio.setId("recordatorio");
        hbRecordatorio.setAlignment(Pos.BASELINE_RIGHT);
        hbRecordatorio.setPadding(new Insets(0, 5, 0, 0));
        
        //AGREGAR VER ACCION
        /*agregarImagen.setFitWidth(20);
        agregarImagen.setFitHeight(20);
        agregarImagen.setId("boton" + dia + fechaActual.getMonth().getValue() + fechaActual.getYear());*/
        
        AnchorPane anchorOjoVer = new AnchorPane();
        //SI EL DIA NO EXEDE EL MES ACUTUAL O DA VALORES NEGATIVOSS
        if (dia > 0 && dia <= fechaActual.getMonth().length(true)) {
            
            //COMPRUEBA SI EXISTE ACCION PARA MOSTRA EL BOTON DE VER ACCION
             //LOCAL DATE CON LA FECHA DE LA CELDA CONCURRENTE
             LocalDate localDateConcurrente;
             if((fechaActual.getYear() % 4 == 0  && fechaActual.getYear() % 100 != 0) || (fechaActual.getYear() % 400 == 0) && fechaActual.getMonth().getValue() == 2){
                 localDateConcurrente = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth().getValue(), dia);
             }else if(fechaActual.getMonth().getValue() == 2 && dia == 29){
                localDateConcurrente = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth().getValue(), dia-1);
             }else{
                 localDateConcurrente = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth().getValue(), dia);
             }
             
            try {
                if (agendaDao.obtenerSiExisteAccion(localDateConcurrente)) {
                   
                    anchorOjoVer = servicioPadre.imagenOjo();
                    //agregarImagen.setImage(VariablesEstaticas.imagenVer);
                    
                }else{
                    anchorOjoVer =  servicioPadre.imagenLapiz();
                    //agregarImagen.setImage(VariablesEstaticas.imagenAgregar);
                }
            } catch (Exception e) {
                anchorOjoVer = new AnchorPane();
            }
        }
        
        
        
        
      
        
        
        
        
        //servicioPadre.imagenOjo()
        //agregarImagen
        
        HBox hboxImg = new HBox(anchorOjoVer);
        
        
        hboxImg.setPrefSize(500, 500);
        hboxImg.setAlignment(Pos.BOTTOM_RIGHT);
        hboxImg.setId("" + fechaActual.getYear() + "-" + fechaActual.getMonth().getValue() + "-" + dia);
        hboxImg.setOnMouseClicked(this::administraAccion);
        hboxImg.setPadding(new Insets(0, 0, 0, 0));
        
        //VBOX QUE CONTIENE TODOS LOS ELEMENTOS DE LA CELDA HAY UN VBOX DENTRO DE OTRO PARA PODER SETERA MARGENES
        vBoxAgenda.getChildren().add(hboxLabel);
        vBoxAgenda.getChildren().add(hbRecordatorio);
        vBoxAgenda.getStylesheets().add(cssCalendario);
        
        vBoxAgenda.setId("anchorCeldasCalendario");
        vBoxAgenda.setPrefSize(500, 500);
        
       
        
        //COMPRUEBA SI EL DIA ESTA EN EL MES CONCUERRENTE Y SEGUN ESO LE DA UN ESTILO CSS
        if (dia > 0 && dia <= fechaActual.lengthOfMonth()) {
            
            if(LocalDate.now().getDayOfMonth() == dia && fechaActual.getMonth().getValue() == LocalDate.now().getMonthValue() && fechaActual.getYear() == LocalDate.now().getYear()){
                vBoxAgenda.getChildren().add(hboxImg);
                vBoxAgenda.getStyleClass().add("calendarioCeldaDiaActual");
            }
            else if (dia % 2 != 0) {
                
                vBoxAgenda.getChildren().add(hboxImg);
                vBoxAgenda.getStyleClass().add("calendarioCeldaImpar");
            } else {
                
                vBoxAgenda.getChildren().add(hboxImg);
                vBoxAgenda.getStyleClass().add("calendarioCeldaPar");
            }
            
        }else {
            vBoxAgenda.getStyleClass().add("calendarioCeldaDistintomes");
        }
        VBox.setMargin(vBoxAgenda, new Insets(5));
        VBox vboxResultado = new VBox(vBoxAgenda);
        //vboxResultado.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        vboxResultado.setSpacing(20);
        

        return vboxResultado;
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
    
    
    public void fechaSeleccionado(HBox b){
        
        VBox a =  (VBox)  b.getParent();
        
        LocalDate fecha;
       
        for (Node child : a.getChildren()) {
           
            if (Objects.nonNull(child.getId())) {
                if (child.getId().equals("dia")) {
                    HBox hb = (HBox) child;
                    Label dia = (Label) hb.getChildren().get(0);
                    diaSeleccionado = Integer.parseInt(dia.getText());
                }
            }
        }
       
    }

    @FXML
    private void crearAccionAgenda(MouseEvent event) {
        //fecha actual
        
       LocalDate fechaCrear = LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);
        
        listaCajasAreaAccion = Arrays.asList(cajaAreaVerAccionAgenda);
        
        if (cajaAreaVerAccionAgenda.getText().isBlank()) {
            mensajeAdvertenciaError("Ingresar datos para crear acción", this, VariablesEstaticas.imgenAdvertencia);
            servicioAgenda.
                    pintarCajaAreaVaciaImportante(listaCajasAreaAccion);

        }else if(CajaHora.getText().isEmpty() || cajaMinutos.getText().isEmpty()){
            mensajeAdvertenciaError("Ingresar horario para crear acción", this, VariablesEstaticas.imgenAdvertencia);
        }else {
            AccionesAgenda accion
                    = new AccionesAgenda(
                            cajaAreaVerAccionAgenda.getText(),
                            fechaCrear,
                            checkRecordar.isSelected(), 
                            LocalDateTime.of(anoSeleccionado, mesSeleccionado, diaSeleccionado, Integer.parseInt(CajaHora.getText()), Integer.parseInt(cajaMinutos.getText()), 00));
           
                if (Objects.nonNull(accion.getAccion())) {
                try {
                    
                    
                    agendaDao.insertar(accion);
                    mensajeAdvertenciaError("Éxito al crear accion", this, VariablesEstaticas.imgenExito);
                    hboxPrecionadoParaEditarOverAccion.getChildren().clear();
                    hboxPrecionadoParaEditarOverAccion.getChildren().add(servicioPadre.imagenOjo());
                    aspectoCeldasCalendario(diaSeleccionado);
                    
                    
                    String fechaParActivarRecordatorio = acomodarFechaParaPasarALocalDate(hboxPrecionadoParaEditarOverAccion.getId());
                    if (checkRecordar.isSelected()) {
                        
                        ImageView img = (ImageView) hboxPrecionadoRecordatorio.getChildren().get(0);
                        img.setVisible(true);
                        if (LocalDate.parse(fechaParActivarRecordatorio).equals(LocalDate.now())) {
                            VariablesEstaticas.imagenRecordatorioAgendaLateral.setVisible(true);

                        }
                    }
                    
                    
                    iniciarAccionesConHoraYRecordatorio();
                    CajaHora.setText("");
                    cajaMinutos.setText("");
                    cajaAreaVerAccionAgenda.setText("");
                    checkRecordar.setSelected(false);
                        
                    
                    
                    
                } catch (Exception e) {
                    mensajeAdvertenciaError("Error al crear acción", this, VariablesEstaticas.imgenError);
                    e.printStackTrace();
                }

                } else {
                    mensajeAdvertenciaError("Error al crear acción", this, VariablesEstaticas.imgenError);
                }
            
        }
    }

    @FXML
    private void actualizarAccionAgenda(MouseEvent event) {
        LocalDate fechaCrear = LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);
        listaCajasAreaAccion = Arrays.asList(cajaAreaVerAccionAgenda);
        
        if (cajaAreaVerAccionAgenda.getText().isBlank()) {
            mensajeAdvertenciaError("Ingresar datos para actualizar acción", this, VariablesEstaticas.imgenAdvertencia);
            servicioAgenda.
                    pintarCajaAreaVaciaImportante(listaCajasAreaAccion);

        } else {
            AccionesAgenda accion
                    = new AccionesAgenda(
                            cajaAreaVerAccionAgenda.getText(),
                            fechaCrear,
                            checkRecordar.isSelected());
            
            accion.setFechaYHora(LocalDateTime.of(anoSeleccionado, mesSeleccionado, diaSeleccionado, Integer.parseInt(CajaHora.getText()), Integer.parseInt(cajaMinutos.getText()), 00));
            accion.setId(Integer.parseInt(CajaId.getText()));
            
            if (Objects.nonNull(accion.getAccion()) && Objects.nonNull(accion.getFechaYHora())) {
                try {
                    agendaDao.actualizar(accion);
                    mensajeAdvertenciaError("Éxito al actualizar accion", this, VariablesEstaticas.imgenExito);
                    
                    String fechaParActivarRecordatorio = acomodarFechaParaPasarALocalDate(hboxPrecionadoParaEditarOverAccion.getId());
                    if (checkRecordar.isSelected()) {
                        ImageView img = (ImageView) hboxPrecionadoRecordatorio.getChildren().get(0);
                        img.setVisible(true);
                        if (LocalDate.parse(fechaParActivarRecordatorio).equals(LocalDate.now())) {
                            VariablesEstaticas.imagenRecordatorioAgendaLateral.setVisible(true);
                        }
                    }
                    
                    cajaAreaVerAccionAgenda.setText("");
                    CajaHora.setText("");
                    cajaMinutos.setText("");
                    CajaId.setText("");
                    botonActualizarAccionAgenda.setDisable(true);
                    botonEliminarAccionAgenda.setDisable(true);
                    botonCrearAccionAgenda.setDisable(false);
                    checkRecordar.setSelected(false);
                    iniciarAccionesConHoraYRecordatorio();
                    
                    //setear imagen de editar ver y recordar del calendario
                    hboxPrecionadoParaEditarOverAccion.getChildren().clear();
                    hboxPrecionadoParaEditarOverAccion.getChildren().add(servicioPadre.imagenOjo());
                    
                } catch (Exception e) {
                    mensajeAdvertenciaError("Error al actualizar acción", this, VariablesEstaticas.imgenError);
                    e.printStackTrace();
                }

            } else {
                mensajeAdvertenciaError("Seleccionar acción para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        }
    }

    @FXML
    private void eliminarAccionAgenda(MouseEvent event) {

        LocalDate fechaCrear = LocalDate.of(anoSeleccionado, mesSeleccionado, diaSeleccionado);

        if (cajaAreaVerAccionAgenda.getText().isBlank()) {
            mensajeAdvertenciaError("No hay accion para eliminar", this, VariablesEstaticas.imgenAdvertencia);
        } else {
            AccionesAgenda accion = new AccionesAgenda();
            accion.setId(Integer.parseInt(CajaId.getText()));
            if (Objects.nonNull(accion.getId())) {
                try {
                    
                    
                    
                    agendaDao.eliminar(accion);
                    mensajeAdvertenciaError("Éxito al eliminar acción", this, VariablesEstaticas.imgenExito);
                    
                    if(!agendaDao.obtenerRecordatorio(fechaCrear)){
                        ImageView img = (ImageView) hboxPrecionadoRecordatorio.getChildren().get(0);
                        img.setVisible(false);
                    }
                    
                    String fechaParActivarRecordatorio = acomodarFechaParaPasarALocalDate(hboxPrecionadoParaEditarOverAccion.getId());
                    if (LocalDate.parse(fechaParActivarRecordatorio).equals(LocalDate.now())) {
                        VariablesEstaticas.imagenRecordatorioAgendaLateral.setVisible(false);
                        
                    }
                    
                    cajaAreaVerAccionAgenda.setText("");
                    CajaHora.setText("");
                    cajaMinutos.setText("");
                    CajaId.setText("");
                    botonActualizarAccionAgenda.setDisable(true);
                    botonEliminarAccionAgenda.setDisable(true);
                    botonCrearAccionAgenda.setDisable(false);
                    checkRecordar.setSelected(false);
                    iniciarAccionesConHoraYRecordatorio();
                    
                    //setear imagen del calendario para ver accion o editar y activaer el recordatorio
                    hboxPrecionadoParaEditarOverAccion.getChildren().clear();
                    hboxPrecionadoParaEditarOverAccion.getChildren().add(servicioPadre.imagenLapiz());
                    
                    

                } catch (Exception e) {
                    mensajeAdvertenciaError("Error al eliminar acción", this, VariablesEstaticas.imgenError);
                    e.printStackTrace();
                }

            } else {
                mensajeAdvertenciaError("Seleccionar acción a eliminar", this, VariablesEstaticas.imgenAdvertencia);
            }
        }

    }

    @FXML
    private void retornarAccionAgenda(MouseEvent event) {
        cajaAreaVerAccionAgenda.setText("");
        CajaHora.setText("");
        cajaMinutos.setText("");
        CajaId.setText("");
        botonActualizarAccionAgenda.setDisable(true);
        botonEliminarAccionAgenda.setDisable(true);
        botonCrearAccionAgenda.setDisable(false);
        checkRecordar.setSelected(false);
    }

    public String acomodarFechaParaPasarALocalDate(String aFecha){
        String[] idHboxAgrEli = aFecha.split("-");
                    String result = "";
                    for (int i = 0; i < idHboxAgrEli.length; i++) {
                        if(idHboxAgrEli[i].length() == 1){
                            idHboxAgrEli[i] =  "0" + idHboxAgrEli[i];
                        }
                        if(idHboxAgrEli[i].length() < 4){
                            result += "-" + idHboxAgrEli[i];
                        }else{
                            result += idHboxAgrEli[i];
                        }
                    }
                    return result;
    }

    
    
    @FXML
    private void soloNumersoHora(KeyEvent event) {
        soloNumero(event);
        
        TextField tf = (TextField) event.getSource();
        String caracter = event.getCharacter();
        
        if (Character.isDigit(caracter.charAt(0))) {
            if (Integer.parseInt(tf.getText()) > 23 || Integer.parseInt(tf.getText()) < 0) {
                mensajeAdvertenciaError("Ingresar hora válida", this, "/com/pacientes/gestor_pacientes/img/error.png");
                tf.setText("");
            }
        }

    }

    @FXML
    private void soloNumersoMinutos(KeyEvent event) {
        soloNumero(event);
        
        TextField tf = (TextField) event.getSource();
        String caracter = event.getCharacter();
        
        if (Character.isDigit(caracter.charAt(0))) {
            
            if (Integer.parseInt(tf.getText()) > 59 || Integer.parseInt(tf.getText()) < 0) {
                mensajeAdvertenciaError("Ingresar minutos válidos", this, "/com/pacientes/gestor_pacientes/img/error.png");
                tf.setText("");
            }
        }
        
    }

    
    
    

   
   


   
    
    
    
}
