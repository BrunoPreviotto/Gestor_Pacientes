/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

// PROYECTO
import com.pacientes.gestor_pacientes.implementacionDAO.*;
import com.pacientes.gestor_pacientes.modelo.*;
import com.pacientes.gestor_pacientes.utilidades.MetodosComoParametros;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.*;
import static com.pacientes.gestor_pacientes.servicios.InicializarObjeto.*;
import com.pacientes.gestor_pacientes.validacion.Validar;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import com.pacientes.gestor_pacientes.utilidades.TablaSesiones;

// EXTERNAS
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import com.google.common.collect.ImmutableMap;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * FXML Controller class
 *
 * @author previotto
 */
public class MenuInicioController extends ClasePadreMenuInicio implements Initializable, DraggedScene {

    //                          ****
    //                          ****
    //                          ****
    //      INICIALIZADORES  
    //                          ****
    //                          ****
    //                          ****
    /**
     * Inicializa: El nombre del usuario que ha iniciado sesion Los choise list
     * La agenda
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(container);
        usuarioDao = new UsuarioDAOImplementacion();
        usuario = usuarioDao.obtenerNombreUsuario();
        labelNombreDeUsuario1.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario2.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario3.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario4.setText(usuario.getNombre() + " " + usuario.getApellido());
        iniciarChoiseList();

        AgendaController agenda = new AgendaController(gpCalendario);
        agenda.rellenarAgenda();

        choiseTipoSesionPlan.setOnAction(this::cambiarCategoria);

        choiseNombreObraSocialPaciente.setOnAction(this::cambiarPlanesObraSocial);

        //INICIALIZAR CAJAS ESTATICAS
        VariablesEstaticas.cajasDatosPrincipales
                = Arrays.asList(
                        cajaNombreDatosPrincipales,
                        cajaApellidoDatosPrincipales,
                        cajaEdadDatosPrincipales,
                        cajaDniDatosPrincipales,
                        cajaTelefonoDatosPrincipales);

        VariablesEstaticas.cajasSesiones
                = Arrays.asList(
                        cajaAutorizacionSesion,
                        cajaCopagoSesionObraSocial);

        VariablesEstaticas.cajasAreaSesion
                = Arrays.asList(
                        cajaTrabajoSesion,
                        cajaObservacionSesion,
                        cajaMotivoTrabajoEmergenteSesion,
                        cajaObservacionSesionObraSocial);

        VariablesEstaticas.datePickerSesiones
                = Arrays.asList(
                        cajaFechaSesion,
                        cajaAsociacionSesionObraSocial);

        VariablesEstaticas.choiseSesiones
                = Arrays.asList(
                        choiseCodigoFactSesionObraSocial);

        VariablesEstaticas.tableSesiones
                = Arrays.asList(
                        tableSesiones,
                        tablaAutorizacion);

        VariablesEstaticas.cajasPlanes
                = Arrays.asList(
                        cajaPlanFrecuenciaSesiones,
                        cajaNombreTipoSesionPlan,
                        cajaEstrategiaPlan);

        VariablesEstaticas.choisePlan
                = Arrays.asList(
                        choiseFrecuenciaSesionPlan,
                        choiseTipoSesionPlan);

        VariablesEstaticas.cajasAreaPlan
                = Arrays.asList(
                        cajaDescripcionTipoSesionPlan);

        VariablesEstaticas.cajasAreaDiagnostico
                = Arrays.asList(
                        cajaDiagnosticoDiagnostico,
                        cajaObservacionDiagnostico);

        VariablesEstaticas.cajasObraSocialPaciente
                = Arrays.asList(
                        cajaNombreObraSocialPaciente,
                        cajaPlanObraSocialPaciente,
                        cajaNAfiliadoObraSocialPaciente);

        VariablesEstaticas.choiseObraSocialPaciente
                = Arrays.asList(
                        choiseNombreObraSocialPaciente,
                        choisePlanesObraSocialPacientePlan);

        /*
            1 NOMBRE
            2 APELLIDO
            3 EDAD
            4 DNI
            5 TELEFONO
         */
        VariablesEstaticas.valoresBUsquedaDatosPrincipales
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "");

        /*
            
            1 FECHA SESION
            2 NUMERO SESION
            3 TRABAJO SESION
            4 OBSERVACION SESION
            5 MOTIVOS DE TRABAJO EMERGENTE
            6 NUMERO AUTORIZACION
            7 OBSERVACION AUTORIZACION
            8 ASOCIACION AUTORIZACION
            9 COPAGO AUTORIZACION
            10 CODIGO FACTURACION AUTORIZACION
        
         */
        VariablesEstaticas.valoresBUsquedaSesiones
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "",
                        "6", "",
                        "7", "",
                        "8", "",
                        "9", "",
                        "10", "");

        /*
        
            1 FRECUENCIA SESIONES PLAN
            2 TIPO SESION PLAN
            3 DESCRIPCION PLAN
            4 ESTRATEGIA PLAN
        
         */
        VariablesEstaticas.valoresBUsquedaPlanes
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "");

        /*
        
            1 DIAGNOSTICO DIAGNOSTICO
            2 OBSERVACION DIAGNOSTICO
        
         */
        VariablesEstaticas.valoresBUsquedaDiagnostico
                = Map.of(
                        "1", "",
                        "2", "");

        /*
            
            1 NOMBRE OBRA SOCIAL PACIENTE
            2 PLAN OBRA SOCIAL PACIENTE
            3 Nº AFILIADO OBRA SOCIAL PACIENTE
        
         */
        VariablesEstaticas.valoresBUsquedaObraSocialPaciente
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "");

    }

    private void iniciarChoiseList() {
        obraSocialDao = new ObraSocialDAOImplementacion();
        List<String> listaCodigosFacturacion = pacienteDao.obtenerListaCodigosFacturacion();
        List<String> listaTiposSesiones = pacienteDao.obtenerListaTiposSesion();
        List<String> listaNombreObrasSociales = obraSocialDao.obtenerListaNombresObrasSociales();
        List<String> listaFreceunciaPlan = pacienteDao.obtenerListaFrecuencia();

        if (!Objects.isNull(listaNombreObrasSociales)) {
            choiseNombreObraSocialPaciente.getItems().addAll(listaNombreObrasSociales);
        }

        listaPlanesObrasSociales = obraSocialDao.obtenerListaPlanesObrasSociales(new ObraSocial(choiseNombreObraSocialPaciente.getValue()));
        valorInicialNombreObraSocialPaciente = choiseNombreObraSocialPaciente.getValue();

        if (!Objects.isNull(listaPlanesObrasSociales)) {
            choisePlanesObraSocialPacientePlan.getItems().addAll(listaPlanesObrasSociales);
        }
        if (!Objects.isNull(listaTiposSesiones)) {
            choiseTipoSesionPlan.getItems().addAll(listaTiposSesiones);
        }
        if (!Objects.isNull(listaFreceunciaPlan)) {
            choiseFrecuenciaSesionPlan.getItems().addAll(listaFreceunciaPlan);
        }
        if (!Objects.isNull(listaCodigosFacturacion)) {
            choiseCodigoFactSesionObraSocial.getItems().addAll(listaCodigosFacturacion);
        }
    }

    /*
        PACIENTE                    PACIENTE                PACIENTE            PACIENTE
    
        PACIENTE                    PACIENTE                PACIENTE            PACIENTE
        
        PACIENTE                    PACIENTE                PACIENTE            PACIENTE
        
        PACIENTE                    PACIENTE                PACIENTE            PACIENTE
        
        PACIENTE                    PACIENTE                PACIENTE            PACIENTE
     */
    //                          ****
    //                          ****
    //                          ****
    //      BUSCAR PACIENTE 
    //                          ****
    //                          ****
    //                          ****
    @FXML
    public void buscarPaciente() {
        List<Button> listaDeshabilitarBotonesAgregar = new ArrayList() ;
        List<Button> listaHabilitarBotonesAgregar = Arrays.asList(
                botonAgregarDatosPrincipales, 
                botonAgregarPlanTratamiento,
                botonAgregarPlanFrecuencia,
                botonAgregarPlanTipoSesion,
                botonAgregarDiagnostico,
                botonAgregarObraSocialPaciente);
        Paciente pacienteBuscar = new Paciente();
        if (!cajaBuscarPaciente.getText().isEmpty()) {
            //BUSCAR ID PACIENTE BUSCADO
            pacienteBuscar.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
            pacienteBuscar.setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))).getId());
            //SI PACIENTE EXISTE
            if (pacienteBuscar.getId() != 0) {
                Paciente pacienteResultado = pacienteDao.obtener(pacienteBuscar);
                
                ObservableList<TablaSesiones> ol = FXCollections.observableArrayList();
                //PACIENTE NO ESTA VACIO
                if (Objects.nonNull(pacienteResultado)) {
                    
                    //SI DATOS PRINCIPALES EXISTEN
                    if (Objects.nonNull(pacienteResultado.getDni())) {
                        cajaNombreDatosPrincipales.setText(servicioPaciente.CapitalCase(pacienteResultado.getNombre()));
                        cajaApellidoDatosPrincipales.setText(servicioPaciente.CapitalCase(pacienteResultado.getApellido()));
                        cajaEdadDatosPrincipales.setText(String.valueOf(pacienteResultado.getEdad()));
                        cajaDniDatosPrincipales.setText(String.valueOf(pacienteResultado.getDni()));
                        cajaTelefonoDatosPrincipales.setText(pacienteResultado.getTelefono().getTelefono());
                        //RELENAR LISTA CON PACIENTE ACTUAL
                        servicioPaciente.
                                rellenarListaDatosPrincipales(pacienteResultado).
                                deshabilitarCajas(VariablesEstaticas.cajasDatosPrincipales);
                        
                        titlePaneDatosPrincipales.setExpanded(true);
                        listaDeshabilitarBotonesAgregar.add(botonAgregarDatosPrincipales);
                    }

                    //SI SESIONES EXISTEN
                    if (Objects.nonNull(pacienteResultado.getSesiones())) {
                        for (SesionPaciente sp : pacienteResultado.getSesiones()) {

                            ol.add(new TablaSesiones(
                                    String.valueOf(sp.getNumeroSesion()),
                                    sp.getFecha().toString(),
                                    sp.getTrabajoSesion(),
                                    sp.getObservacion(),
                                    sp.getMotivoTrabajoEmergente(),
                                    String.valueOf(sp.getAutorizacion().getNumeroAutorizacion()),
                                    sp.getAutorizacion().getObservacion(),
                                    sp.getAutorizacion().getAsociacion().toString(),
                                    String.valueOf(sp.getAutorizacion().getCopago()),
                                    sp.getAutorizacion().getCodigoFacturacion().getNombre()));
                        }

                        tablaAutorizacion.setItems(ol);
                        tableSesiones.setItems(ol);
                        ColumnaSesionNumero.setCellValueFactory(new PropertyValueFactory<>("numeroSesion"));
                        ColumnaSesionFecha.setCellValueFactory(new PropertyValueFactory<>("fechaSesion"));
                        ColumnaSesionTrabajo.setCellValueFactory(new PropertyValueFactory<>("trabajoSesion"));
                        ColumnaSesionObservacion.setCellValueFactory(new PropertyValueFactory<>("observacionSesion"));
                        ColumnaSesionMotivoTRabajoEmergente.setCellValueFactory(new PropertyValueFactory<>("motivoTrabajoEmergente"));
                        columnaAutorizacionNumero.setCellValueFactory(new PropertyValueFactory<>("numeroAutorizacion"));
                        columnaAutorizacionObservacion.setCellValueFactory(new PropertyValueFactory<>("observacionAutorizacion"));
                        columnaAutorizacionAsociacion.setCellValueFactory(new PropertyValueFactory<>("asociacion"));
                        columnaAutorizacionCopago.setCellValueFactory(new PropertyValueFactory<>("copago"));
                        columnaAutorizacionCodigoFacturacion.setCellValueFactory(new PropertyValueFactory<>("nombreCodigo"));
                        columnaNumeroSecionAutorizacion.setCellValueFactory(new PropertyValueFactory<>("numeroSesion"));

                    }

                    //SI PLANES DE TRATAMIENTOS EXISTEN
                    if (Objects.nonNull(pacienteResultado.getPlanTratamiento())) {
                        choiseFrecuenciaSesionPlan.setValue(pacienteResultado.getPlanTratamiento().getFrecuenciaSesion());
                        cajaPlanFrecuenciaSesiones.setText(pacienteResultado.getPlanTratamiento().getFrecuenciaSesion());
                        cajaDescripcionTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getDecripcion());
                        cajaEstrategiaPlan.setText(pacienteResultado.getPlanTratamiento().getEstrategia());
                        choiseTipoSesionPlan.setValue(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());
                        cajaNombreTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());

                        servicioPaciente.
                                rellenarListaPlan(pacienteResultado).
                                visivilizarCajas(VariablesEstaticas.cajasPlanes).
                                esconderChoice(VariablesEstaticas.choisePlan).
                                deshabilitarCajas(VariablesEstaticas.cajasPlanes);
                        
                        listaDeshabilitarBotonesAgregar.add(botonAgregarPlanTratamiento);
                        listaDeshabilitarBotonesAgregar.add(botonAgregarPlanFrecuencia);
                        listaDeshabilitarBotonesAgregar.add(botonAgregarPlanTipoSesion);
                    }

                    //SI DIAGNOSTICO EXISTE
                    if (Objects.nonNull(pacienteResultado.getDiagnostico())) {
                        cajaDiagnosticoDiagnostico.setText(pacienteResultado.getDiagnostico().getDiagnostico());
                        cajaObservacionDiagnostico.setText(pacienteResultado.getDiagnostico().getObservacion());
                        
                        servicioPaciente.
                                rellenarListaDiagnostico(pacienteResultado).
                                deshabilitarCajasArea(VariablesEstaticas.cajasAreaDiagnostico);
                        listaDeshabilitarBotonesAgregar.add(botonAgregarDiagnostico);
                    }

                    //SI OBRA SOCIAL PACIENTE EXISTE
                    if (Objects.nonNull(pacienteResultado.getObraSocialPaciente())) {

                        cajaNombreObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getNombre());
                        choiseNombreObraSocialPaciente.setValue(pacienteResultado.getObraSocialPaciente().getNombre());
                        choisePlanesObraSocialPacientePlan.setValue(pacienteResultado.getObraSocialPaciente().getPlan().getNombre());
                        cajaPlanObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getPlan().getNombre());
                        cajaNAfiliadoObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getAfiliado().getNumero().toString());

                        servicioPaciente.
                                rellenarListaObrasocialPaciente(pacienteResultado).
                                visivilizarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                esconderChoice(VariablesEstaticas.choiseObraSocialPaciente).
                                deshabilitarCajas(VariablesEstaticas.cajasObraSocialPaciente);
                        listaDeshabilitarBotonesAgregar.add(botonAgregarObraSocialPaciente);
                    }
                    
                    servicioPaciente.deshabilitarBotones(listaDeshabilitarBotonesAgregar);

                } else {
                    mensaje("Paciente no encontrado", this, "/com/pacientes/gestor_pacientes/img/PacienteNoEncontrado.png");
                    servicioPaciente.
                            vaciarTodo().
                            esconderCajas(VariablesEstaticas.cajasPlanes).
                            visivilizarChoice(VariablesEstaticas.choisePlan).
                            esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                            visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                            habilitarBotones(listaHabilitarBotonesAgregar);
                }

            } else {
                mensaje("Paciente no encontrado", this, "/com/pacientes/gestor_pacientes/img/PacienteNoEncontrado.png");
                servicioPaciente.
                        vaciarTodo().
                        esconderCajas(VariablesEstaticas.cajasPlanes).
                        visivilizarChoice(VariablesEstaticas.choisePlan).
                        esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                        visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                        habilitarBotones(listaHabilitarBotonesAgregar);
            }

        } else {
            
            cajaPlanFrecuenciaSesiones.setVisible(false);
            cajaNombreTipoSesionPlan.setVisible(false);
            mensaje("Paciente no encontrado", this, "/com/pacientes/gestor_pacientes/img/PacienteNoEncontrado.png");
            //VACIAR CAJAS SI PACIENTE NO EXISTE
            servicioPaciente.
                    vaciarTodo().
                    esconderCajas(VariablesEstaticas.cajasPlanes).
                    visivilizarChoice(VariablesEstaticas.choisePlan).
                    esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                    visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                    habilitarBotones(listaHabilitarBotonesAgregar);
        }

    }

    //                      ****
    //                      ****
    //                      ****
    //   CREAR PACIENTE   
    //                      ****
    //                      ****
    //                      ****
    @FXML
    private void crearPaciente(MouseEvent event) {

        try {
            List<TextField> listaCajas = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));
            if (cajaNombreDatosPrincipales.getText().isBlank() || cajaApellidoDatosPrincipales.getText().isBlank() || cajaDniDatosPrincipales.getText().isBlank()) {
                //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                servicioPaciente.pintarCajaVaciaImportante(VariablesEstaticas.cajasDatosPrincipales);
            } else {
                //SI EXISTEN DATOS VACIOS NO IMPORTANTES LOS RELLENA CON VALORES POR DEFECTO
                servicioPaciente.datosPrincipalesVacios();
                //INSERTAR PACIENTE
                pacienteDao.
                        insertar(inicializarPacienteRegistroDatosPrincipales(cajaNombreDatosPrincipales.getText(),
                                cajaApellidoDatosPrincipales.getText(),
                                Integer.parseInt(cajaEdadDatosPrincipales.getText()),
                                Integer.parseInt(cajaDniDatosPrincipales.getText()),
                                new Telefono(cajaTelefonoDatosPrincipales.getText())),
                                1);
                //SETEAR CAJA BUSCAR CON EL PACIENTE CREADO
                cajaBuscarPaciente.setText(cajaDniDatosPrincipales.getText());
                buscarPaciente();
                //INFORMAR DE CREACION CON EXITO
                mensaje("Paciente creado con èxito", this, "/com/pacientes/gestor_pacientes/img/error.png");

            }

        } catch (Exception e) {
            mensaje("Error al crear paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            cajaNombreDatosPrincipales.getStyleClass().add("cajasARellenar");
        }
    }

    @FXML
    private void crearSesion(MouseEvent event) {

        try {
            tablaAutorizacion.setVisible(false);
            tableSesiones.setVisible(false);
            botonAgregarSesiones.setDisable(false);
            botonEliminarSesiones.setDisable(true);
            botonActualizarSesiones.setDisable(true);
            botonRetornarSesiones.setDisable(false);
            vBoxAutorizacion.setVisible(true);
            vBoxSesiones.setVisible(true);

            if (!botonAgregarSesiones.getId().equals("1")) {

                cajaFechaSesion.setValue(LocalDate.now());
                int ultimaSesion = pacienteDao.obtenerultimaSesion(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))) + 1;
                cajaNumeroSesion.setText(String.valueOf(ultimaSesion));

                servicioPaciente.
                        vaciarCajasArea(VariablesEstaticas.cajasAreaSesion).
                        vaciarCajas(VariablesEstaticas.cajasSesiones).
                        vaciarFechas(VariablesEstaticas.datePickerSesiones).
                        vaciarChoise(VariablesEstaticas.choiseSesiones);
                //SI EXISTEN CAJAS PRINCIPALES VACIAS DE SESION
                botonAgregarSesiones.setId("1");
            } else {

                if (Objects.isNull(
                        cajaFechaSesion.getValue())
                        || cajaTrabajoSesion.getText().isBlank()) {

                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.
                            pintarCajaVaciaImportante(VariablesEstaticas.cajasSesiones).
                            pintarCajaAreaVaciaImportante(VariablesEstaticas.cajasAreaSesion);
                            
                            
                    } else {

                    Paciente pacienteCrearSesion = new Paciente();
                    AutorizacionesSesionesObraSociales autorizacionesSesionesObraSociales;
                    //SI EXISTEN CAJAS PRINCIPALES VACIAS DE AUTORIZACION
                    if (!cajaAutorizacionSesion.getText().isEmpty()) {

                        autorizacionesSesionesObraSociales = new AutorizacionesSesionesObraSociales(
                                Integer.valueOf(cajaAutorizacionSesion.getText()),
                                cajaObservacionSesionObraSocial.getText(), cajaAsociacionSesionObraSocial.getValue(),
                                Double.valueOf(cajaCopagoSesionObraSocial.getText()),
                                new CodigoFacturacion(choiseCodigoFactSesionObraSocial.getValue()));
                    } else {
                        autorizacionesSesionesObraSociales = new AutorizacionesSesionesObraSociales(
                                0,
                                "", LocalDate.MAX,
                                0.0,
                                new CodigoFacturacion(""));
                    }

                    SesionPaciente sesion = new SesionPaciente(Integer.valueOf(cajaNumeroSesion.getText()),
                            cajaFechaSesion.getValue(),
                            cajaTrabajoSesion.getText(),
                            cajaObservacionSesion.getText(),
                            cajaMotivoTrabajoEmergenteSesion.getText(),
                            autorizacionesSesionesObraSociales);

                    pacienteCrearSesion.setSesion(sesion);
                    pacienteCrearSesion.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
                    pacienteCrearSesion.setId(pacienteDao.obtenerIdPaciente(pacienteCrearSesion).getId());

                    if (!cajaDniDatosPrincipales.getText().isEmpty()) {
                        
                        if(!cajaAutorizacionSesion.getText().equals("0") || !choiseCodigoFactSesionObraSocial.getValue().equals("")){
                          
                                
                        }
                        
                        servicioPaciente.
                                datosSesionVacios().
                                datosAutorizacionSesionVacios();
                                

                        pacienteDao.insertar(pacienteCrearSesion, 3);
                        vBoxSesiones.setVisible(false);
                        vBoxAutorizacion.setVisible(false);
                        tableSesiones.setVisible(true);
                        tablaAutorizacion.setVisible(true);
                        buscarPaciente();
                        mensaje("Sesion creado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        botonAgregarSesiones.setId("botonAgregarSesiones");
                    } else {
                        mensaje("Buscar paciente para crear sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    }
                }
            }
            
        } catch (Exception e) {
            mensaje("Error al crear sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

    }

    @FXML
    private void crearPlanTratamiento(MouseEvent event) {
        try {
            //SI PACIENTE FUE BUSCADO
            if (!cajaBuscarPaciente.getText().isBlank()) {
                //CREAR PLAN
                Paciente pacientePlan = new Paciente();
                TipoSesion ts = new TipoSesion(choiseTipoSesionPlan.getValue(), cajaDescripcionTipoSesionPlan.getText());
                PlanTratamiento planTratamiento = new PlanTratamiento(cajaEstrategiaPlan.getText(), choiseFrecuenciaSesionPlan.getValue(), ts);
                pacientePlan.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
                pacientePlan.setId(pacienteDao.obtenerIdPaciente(pacientePlan).getId());
                pacientePlan.setPlanTratamiento(planTratamiento);
                
                //VALIDAR CAMPOS NECESARIOS
                if (Objects.isNull(choiseFrecuenciaSesionPlan.getValue()) || Objects.isNull(choiseTipoSesionPlan.getValue())) {
                    System.out.println("entra");
                    servicioPaciente.pintarChoiseVacioImportante(VariablesEstaticas.choisePlan);
                    mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                } else {
                    servicioPaciente.datosPlanVacios();
                    pacienteDao.insertar(pacientePlan, 5);
                    mensaje("Plan creado con èxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    buscarPaciente();
                }
            }else{
                mensaje("Buscar paciente para crear plan", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } catch (Exception e) {
            mensaje("Error al crear plan de tratamiento", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
        
    }

    @FXML
    private void crearDiagnostico(MouseEvent event) {
        try {

            if (!cajaBuscarPaciente.getText().isBlank()) {
                Paciente pacienteCrearDiagnostico = new Paciente();
                DiagnosticoPaciente diagnostico = new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText());
                pacienteCrearDiagnostico.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
                pacienteCrearDiagnostico.setId(pacienteDao.obtenerIdPaciente(pacienteCrearDiagnostico).getId());
                pacienteCrearDiagnostico.setDiagnostico(diagnostico);

                if (cajaDiagnosticoDiagnostico.getText().isBlank()) {
                    servicioPaciente.pintarCajaAreaVaciaImportante(VariablesEstaticas.cajasAreaDiagnostico);
                    mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                } else {
                    mensaje("Diagnostico creado con èxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioPaciente.datosDiagnosticoVacios();
                    pacienteDao.insertar(pacienteCrearDiagnostico, 4);
                    buscarPaciente();
                }
            }else{
                mensaje("Buscar paciente para crear Diagnóstico", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }

        } catch (Exception e) {
            mensaje("Error al crear diagnóstico", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }

    @FXML
    private void crearObraSocialPaciente(MouseEvent event) {
        try {
            
            if (!cajaBuscarPaciente.getText().isBlank()) {
                
                if (Objects.isNull(choisePlanesObraSocialPacientePlan.getValue()) || cajaNAfiliadoObraSocialPaciente.getText().isBlank()) {
                    servicioPaciente.pintarChoiseVacioImportante(VariablesEstaticas.choiseObraSocialPaciente);
                    servicioPaciente.pintarCajaVaciaImportante(VariablesEstaticas.cajasObraSocialPaciente);
                    mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                } else {
                    Paciente pacienteCrearObraSocial = new Paciente();
                    
                    ObraSocialPaciente obraSocialPaciente = new ObraSocialPaciente(new Afiliado(Integer.parseInt(cajaNAfiliadoObraSocialPaciente.getText())), choiseNombreObraSocialPaciente.getValue().toString(), new PlanObraSocial(choisePlanesObraSocialPacientePlan.getValue().toString(), "Sin descripcion"));
                    pacienteCrearObraSocial.setObraSocialPaciente(obraSocialPaciente);
                    pacienteCrearObraSocial.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
                    pacienteCrearObraSocial.setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId());
                    pacienteDao.insertar(pacienteCrearObraSocial, 2);
                    mensaje("Obra Social del Paciente creado con èxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    
                    buscarPaciente();
                }
            }else{
                mensaje("Buscar paciente para crear Obra Social del paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
            
            
        } catch (Exception e) {
            mensaje("Error al crear obra social del paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }

    @FXML
    private void insertarTipoPlan(MouseEvent event) {

        try {
            Paciente pacienteTipoSesion = new Paciente();
            PlanTratamiento plan = new PlanTratamiento();
            TipoSesion tipo = new TipoSesion();
            tipo.setNombre(cajaNombreTipoSesionPlan.getText());
            plan.setTipoSEsion(tipo);
            pacienteTipoSesion.setPlanTratamiento(plan);
            cajaNombreTipoSesionPlan.setVisible(true);
            cajaDescripcionTipoSesionPlan.setDisable(false);
            if (botonAgregarPlanTipoSesion.getId().equals("1")) {
                pacienteDao.insertar(pacienteTipoSesion, 6);
                botonAgregarPlanTipoSesion.setId("botonAgregarTipoSesion");
                cajaNombreTipoSesionPlan.setVisible(false);
                cajaDescripcionTipoSesionPlan.setDisable(true);
            }
            botonAgregarPlanTipoSesion.setId("1");
        } catch (Exception e) {

        }
        choiseTipoSesionPlan.getItems().clear();
        iniciarChoiseList();
    }

    @FXML
    private void insertarFrecuenciaPlan(MouseEvent event) {

        try {
            Paciente pacienteTipoSesion = new Paciente();
            PlanTratamiento plan = new PlanTratamiento();
            plan.setFrecuenciaSesion(cajaPlanFrecuenciaSesiones.getText());
            pacienteTipoSesion.setPlanTratamiento(plan);
            cajaPlanFrecuenciaSesiones.setVisible(true);
            if (botonAgregarPlanFrecuencia.getId().equals("1")) {
                pacienteDao.insertar(pacienteTipoSesion, 7);
                botonAgregarPlanFrecuencia.setId("botonAgregarPlanFrecuencia");
                cajaPlanFrecuenciaSesiones.setVisible(false);
            }
            botonAgregarPlanFrecuencia.setId("1");
        } catch (Exception e) {

        }
        choiseFrecuenciaSesionPlan.getItems().clear();
        iniciarChoiseList();
    }

    //                              ****
    //                              ****
    //                              ****
    //      ACTUALIZAR PACIENTE  
    //                              ****
    //                              ****
    //                              ****
    
    @FXML
    private void actualizarPaciente(MouseEvent event) {

        if (botonActualizarDatosPrincipales.getId().equals("1")) {
            try {
                Paciente paciente = new Paciente();
                List<TextField> listaCajasDatosPrincipales = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));

                if (!cajaBuscarPaciente.getText().isEmpty()) {
                    if (cajaNombreDatosPrincipales.getText().isBlank() || cajaApellidoDatosPrincipales.getText().isBlank() || cajaDniDatosPrincipales.getText().isBlank()) {
                        //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                        mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                        servicioPaciente.pintarCajaVaciaImportante(VariablesEstaticas.cajasDatosPrincipales);
                    }else{
                        pacienteDao.actualizar(paciente.setNombre(cajaNombreDatosPrincipales.getText()).setApellido(cajaApellidoDatosPrincipales.getText()).setEdad(Integer.parseInt(cajaEdadDatosPrincipales.getText())).setDni(Integer.parseInt(cajaDniDatosPrincipales.getText())).setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId()).setTelefono(new Telefono(cajaTelefonoDatosPrincipales.getText())), 3);
                        servicioPaciente.deshabilitarCajas(VariablesEstaticas.cajasDatosPrincipales);
                        botonActualizarDatosPrincipales.setId("botonAgregarPlanTratamiento");
                        mensaje("Paciente actualizado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        cajaBuscarPaciente.setText(cajaDniDatosPrincipales.getText());
                        buscarPaciente();
                    }
                    
                } else {
                    mensaje("Buscar paciente a actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
                
            } catch (Exception e) {
                mensaje("Error al actualizar Paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            botonActualizarDatosPrincipales.setId("1");
            servicioPaciente.
                    animarCajasAlDarABoton(VariablesEstaticas.cajasDatosPrincipales).
                    habilitarCajas(VariablesEstaticas.cajasDatosPrincipales);

        }
    }
    
    
    @FXML
    private void actualizarObraSocialPaciente(MouseEvent event) {
        /*try {
            Paciente pacienteObraSocialPaciente = new Paciente();
            ObraSocialPaciente obraSocialPaciente = new ObraSocialPaciente();
            PlanObraSocial planObraSocial = new PlanObraSocial();
            Afiliado afiliado = new Afiliado();

            for (Map.Entry<String, String> entry : VariablesEstaticas.valoresBUsquedaObraSocialPaciente.entrySet()) {
                switch (entry.getKey().getId()) {
                    case "cajaNombreObraSocialPaciente":
                        obraSocialPaciente.setNombre(entry.getValue());
                        break;
                    case "cajaPlanObraSocialPaciente":
                        planObraSocial.setNombre(entry.getValue());
                        break;
                    case "cajaNAfiliadoObraSocialPaciente":
                        afiliado.setNumero(Integer.parseInt(entry.getValue()));
                        break;

                }
            }

            obraSocialPaciente.setAfiliado(afiliado);

            obraSocialPaciente.setPlan(planObraSocial);

            pacienteObraSocialPaciente.setObraSocialPaciente(obraSocialPaciente);

            afiliado.setId(pacienteDao.obtenerIdAfiliadoObraSocial(pacienteObraSocialPaciente));
            planObraSocial.setId(pacienteDao.obtenerIdPlanObraSocial(pacienteObraSocialPaciente));
            obraSocialPaciente.setId(pacienteDao.obtenerIdObraSocia(pacienteObraSocialPaciente));

            obraSocialPaciente.getPlan().setNombre(choisePlanesObraSocialPacientePlan.getValue());
            obraSocialPaciente.getAfiliado().setNumero(Integer.parseInt(cajaNAfiliadoObraSocialPaciente.getText()));
            obraSocialPaciente.setNombre(choiseNombreObraSocialPaciente.getValue());

            pacienteDao.actualizar(pacienteObraSocialPaciente, 5);

            buscarPaciente();
        } catch (Exception e) {
            mensaje("Error al actualizar obra social del paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }*/

    }

    @FXML
    private void actualizarSesion(MouseEvent event) {

        try {
            MensajeAdvertenciaController mensajeAdvertencia = new MensajeAdvertenciaController();

            if (tableSesiones.getSelectionModel().isEmpty()) {
                mensaje("Seleccione sesion para pode actualizar", this, "/com/pacientes/gestor_pacientes/img/warning.png");

            } else {
                LocalDate ldAutorizacion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion());
                LocalDate ldSesion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
                Paciente pacienteSesion = new Paciente();
                SesionPaciente sesion = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
                CodigoFacturacion codigo = new CodigoFacturacion();

                Paciente pacienteBuscar = new Paciente();
                SesionPaciente sesionBuscar = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacionBuscar = new AutorizacionesSesionesObraSociales();

                if (botonActualizarSesiones.getId().equals("1")) {

                    pacienteBuscar.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteBuscar.setId(pacienteDao.obtenerIdPaciente(pacienteBuscar).getId());

                    autorizacionBuscar.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
                    autorizacionBuscar.setAsociacion(ldAutorizacion);
                    sesionBuscar.setAutorizacion(autorizacionBuscar);
                    sesionBuscar.setFecha(ldSesion);
                    sesionBuscar.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
                    pacienteBuscar.setSesion(sesionBuscar);

                    int idSesion = pacienteDao.obtenerIdSesionAutorizacion(pacienteBuscar).getSesion().getIdSesion();
                    int idAutorizacion = pacienteDao.obtenerIdSesionAutorizacion(pacienteBuscar).getSesion().getAutorizacion().getId();

                    LocalDate ldsNuevo = LocalDate.parse(cajaFechaSesion.getValue().toString());
                    LocalDate ldsaNuevo = LocalDate.parse(cajaAsociacionSesionObraSocial.getValue().toString());

                    if (cajaAutorizacionSesion.getText().equals("0") && cajaCopagoSesionObraSocial.getText().equals("0.0") && cajaAsociacionSesionObraSocial.getValue().toString().equals("1700-01-01") && cajaObservacionSesionObraSocial.getText().trim().toString().equals("-")) {
                        sesion.setAutorizacion(rellenarAutorizacionVacia());
                    } else {

                        autorizacion.setId(idAutorizacion);
                        autorizacion.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
                        autorizacion.setAsociacion(ldsaNuevo);
                        autorizacion.setObservacion(cajaObservacionSesionObraSocial.getText());
                        autorizacion.setCopago(Double.parseDouble(cajaCopagoSesionObraSocial.getText()));
                        codigo.setNombre(choiseCodigoFactSesionObraSocial.getValue());
                        autorizacion.setCodigoFacturacion(codigo);
                        sesion.setAutorizacion(autorizacion);
                    }

                    sesion.setIdSesion(idSesion);
                    sesion.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
                    sesion.setFecha(ldsNuevo);
                    sesion.setTrabajoSesion(cajaTrabajoSesion.getText());
                    sesion.setObservacion(cajaObservacionSesion.getText());
                    sesion.setMotivoTrabajoEmergente(cajaMotivoTrabajoEmergenteSesion.getText());
                    pacienteSesion.setSesion(sesion);
                    pacienteSesion.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteSesion.setId(pacienteDao.obtenerIdPaciente(pacienteSesion).getId());

                    pacienteDao.actualizar(pacienteSesion, 4);
                    botonActualizarSesiones.setId("botonActualizarSesiones");
                } else {
                    //BOTONERA
                    tablaAutorizacion.setVisible(false);
                    tableSesiones.setVisible(false);
                    botonAgregarSesiones.setDisable(true);
                    botonEliminarSesiones.setDisable(true);
                    botonRetornarSesiones.setDisable(false);
                    vBoxAutorizacion.setVisible(true);
                    vBoxSesiones.setVisible(true);

                    //SESION
                    cajaFechaSesion.setValue(ldSesion);
                    cajaNumeroSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion());
                    cajaTrabajoSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getTrabajoSesion());
                    cajaObservacionSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getObservacionSesion());
                    cajaMotivoTrabajoEmergenteSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getMotivoTrabajoEmergente());

                    //AUTORIZACION
                    cajaAutorizacionSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getNumeroAutorizacion());
                    cajaObservacionSesionObraSocial.setText(tableSesiones.getSelectionModel().getSelectedItem().getObservacionAutorizacion());
                    cajaAsociacionSesionObraSocial.setValue(ldAutorizacion);
                    cajaCopagoSesionObraSocial.setText(tableSesiones.getSelectionModel().getSelectedItem().getCopago());
                    choiseCodigoFactSesionObraSocial.setValue(tableSesiones.getSelectionModel().getSelectedItem().getNombreCodigo());
                }
                botonActualizarSesiones.setId("1");
            }
        } catch (Exception e) {
            mensaje("Error al actualizar sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

    }

    @FXML
    private void actualizarDiagnostico(MouseEvent event) {
        try {
            Paciente paciente = new Paciente();
            pacienteDao.actualizar(paciente.setDiagnostico(new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText())).setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId()), 1);
        } catch (Exception e) {
            mensaje("Error al actualizar diagnóstico", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

    }

    @FXML
    private void actualizarPlan(MouseEvent event) {
        try {
            Paciente paciente = new Paciente();
            List<TextField> listaCajasPlan = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));
            //List<TextField> listaCajasDatosPrincipales = new ArrayList<TextField>(Arrays.asList(cajaPlanFrecuenciaSesiones, cajaNombreTipoSesionPlan, cajaDescripcionTipoSesionPlan, cajaEstrategiaPlan));
            /*if(esCajaValida(listaCajasPlan)){
                pacienteDao.actualizar(paciente.setPlanTratamiento(new PlanTratamiento(cajaEstrategiaPlan.getText(), cajaPlanFrecuenciaSesiones.getText(), new TipoSesion(choiseTipoSesionPlan.getValue(), "Sin descripcion"))).setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId()), 2);
            }*/
        } catch (Exception e) {
            mensaje("Error al actualizar Plan", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }

    

    @FXML
    private void actualizarPlanesObrasSocial(MouseEvent event) {
        obraSocialDao = new ObraSocialDAOImplementacion();
        ObraSocial obraSocial = new ObraSocial();
        if (!cajaAgregarPlanObraSocial.getText().isEmpty()) {
            obraSocial.setNombre(cajaBuscarObraSocial.getText());
            obraSocial.setPlan(cajaAgregarPlanObraSocial.getText());
            obraSocialDao.agregarPlan(obraSocial);
        }
        cajaAgregarPlanObraSocial.setText("");
        cajaAgregarPlanObraSocial.setDisable(true);
        botonActualizarPlanesObraSocial.setDisable(true);
        cajaVerPlanesObraSocial.setDisable(false);
        botonAgregarPlanesObraSocial.setDisable(false);
    }

    //                              ****
    //                              ****
    //                              ****
    //      ELIMINAR  PACIENTE  
    //                              ****
    //                              ****
    //                              ****
    @FXML
    private void eliminarSesion(MouseEvent event) {

        List<CheckBox> check;

        try {
            MensajeAdvertenciaController mensajeAdvertencia = new MensajeAdvertenciaController();

            if (tableSesiones.getSelectionModel().isEmpty()) {
                mensaje("Seleccione sesion para pode eliminar", this, "/com/pacientes/gestor_pacientes/img/warning.png");

            } else {
                //INICIALIZAR VARIABLES
                LocalDate ldAutorizacion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion());
                LocalDate ldSesion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
                Paciente pacienteSesion = new Paciente();
                SesionPaciente sesion = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
                CodigoFacturacion codigo = new CodigoFacturacion();

                Paciente pacienteBuscar = new Paciente();
                SesionPaciente sesionBuscar = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacionBuscar = new AutorizacionesSesionesObraSociales();

                //SI SE SELECCIONO SESION Y SE APRETO EL BOTON DE ELIMINAR
                if (botonEliminarSesiones.getId().equals("1")) {

                    //INICIALIZAR VARIABLES
                    pacienteBuscar.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteBuscar.setId(pacienteDao.obtenerIdPaciente(pacienteBuscar).getId());

                    autorizacionBuscar.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
                    autorizacionBuscar.setAsociacion(ldAutorizacion);
                    sesionBuscar.setAutorizacion(autorizacionBuscar);
                    sesionBuscar.setFecha(ldSesion);
                    sesionBuscar.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
                    pacienteBuscar.setSesion(sesionBuscar);

                    int idSesion = pacienteDao.obtenerIdSesionAutorizacion(pacienteBuscar).getSesion().getIdSesion();
                    int idAutorizacion = pacienteDao.obtenerIdSesionAutorizacion(pacienteBuscar).getSesion().getAutorizacion().getId();

                    LocalDate ldsNuevo = LocalDate.parse(cajaFechaSesion.getValue().toString());
                    LocalDate ldsaNuevo = LocalDate.parse(cajaAsociacionSesionObraSocial.getValue().toString());

                    //SI NO EXISTE AUTORIZACION LA RELLENA CON VALORES VACIOS
                    if (cajaAutorizacionSesion.getText().equals("0") && cajaCopagoSesionObraSocial.getText().equals("0.0") && cajaAsociacionSesionObraSocial.getValue().toString().equals("1700-01-01") && cajaObservacionSesionObraSocial.getText().trim().toString().equals("-")) {
                        sesion.setAutorizacion(rellenarAutorizacionVacia());
                    } else {

                        autorizacion.setId(idAutorizacion);
                        autorizacion.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
                        autorizacion.setAsociacion(ldsaNuevo);
                        autorizacion.setObservacion(cajaObservacionSesionObraSocial.getText());
                        autorizacion.setCopago(Double.parseDouble(cajaCopagoSesionObraSocial.getText()));
                        codigo.setNombre(choiseCodigoFactSesionObraSocial.getValue());
                        autorizacion.setCodigoFacturacion(codigo);
                        sesion.setAutorizacion(autorizacion);
                    }

                    sesion.setIdSesion(idSesion);
                    sesion.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
                    sesion.setFecha(ldsNuevo);
                    sesion.setTrabajoSesion(cajaTrabajoSesion.getText());
                    sesion.setObservacion(cajaObservacionSesion.getText());
                    sesion.setMotivoTrabajoEmergente(cajaMotivoTrabajoEmergenteSesion.getText());
                    pacienteSesion.setSesion(sesion);
                    pacienteSesion.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteSesion.setId(pacienteDao.obtenerIdPaciente(pacienteSesion).getId());
                    mensajeEliminarSesion(this);
                    for (Map.Entry<List<CheckBox>, Boolean> entry : valoresElimenarSesion.entrySet()) {
                        check = entry.getKey();
                        Object val = entry.getValue();
                        System.out.println(check.get(0).isSelected() + " " + check.get(1).isSelected());
                        if (val.equals(true)) {
                            if (!check.isEmpty()) {
                                if (!check.get(0).isSelected() && check.get(1).isSelected()) {
                                    try {
                                        pacienteDao.eliminar(pacienteSesion, 7);
                                    } catch (Exception e) {
                                    }
                                } else if (check.get(0).isSelected()) {
                                    try {
                                        pacienteDao.eliminar(pacienteSesion, 4);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }

                    }

                    vBoxSesiones.setVisible(false);
                    vBoxAutorizacion.setVisible(false);
                    tableSesiones.setVisible(true);
                    tablaAutorizacion.setVisible(true);
                    buscarPaciente();
                    botonEliminarSesiones.setId("botonEliminarSesiones");

                } else {
                    //BOTONERA
                    tablaAutorizacion.setVisible(false);
                    tableSesiones.setVisible(false);
                    //botonAgregarSesiones.setDisable(true);
                    //botonEliminarSesiones.setDisable(true);
                    //botonRetornarSesiones.setDisable(false);
                    vBoxAutorizacion.setVisible(true);
                    vBoxSesiones.setVisible(true);

                    //SESION
                    cajaFechaSesion.setValue(ldSesion);
                    cajaNumeroSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion());
                    cajaTrabajoSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getTrabajoSesion());
                    cajaObservacionSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getObservacionSesion());
                    cajaMotivoTrabajoEmergenteSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getMotivoTrabajoEmergente());

                    //AUTORIZACION
                    cajaAutorizacionSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getNumeroAutorizacion());
                    cajaObservacionSesionObraSocial.setText(tableSesiones.getSelectionModel().getSelectedItem().getObservacionAutorizacion());
                    cajaAsociacionSesionObraSocial.setValue(ldAutorizacion);
                    cajaCopagoSesionObraSocial.setText(tableSesiones.getSelectionModel().getSelectedItem().getCopago());
                    choiseCodigoFactSesionObraSocial.setValue(tableSesiones.getSelectionModel().getSelectedItem().getNombreCodigo());
                }

                botonEliminarSesiones.setId("1");
            }
        } catch (Exception e) {
            mensaje("Error al eliminar sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

    }

    @FXML
    private void eliminarPlan(MouseEvent event) {

        try {
            Paciente pacienteEliminarPlanTratamiento = new Paciente();
            pacienteEliminarPlanTratamiento.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
            pacienteDao.eliminar(pacienteEliminarPlanTratamiento, 6);
        } catch (Exception e) {
            mensaje("Error al eliminar Plan de tratamiento", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
        buscarPaciente();

    }

    @FXML
    private void eliminarDiagnostico(MouseEvent event) {

        try {
            Paciente pacienteEliminarDiagnostico = new Paciente();
            pacienteEliminarDiagnostico.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
            pacienteDao.eliminar(pacienteEliminarDiagnostico, 3);
        } catch (Exception e) {
            mensaje("Error al eliminar diagnóstico", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
        buscarPaciente();
    }

    @FXML
    private void eliminarObraSocialPaciente(MouseEvent event) {

        try {
            Paciente pacienteEliminarObraSocialPaciente = new Paciente();
            pacienteEliminarObraSocialPaciente.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
            pacienteDao.eliminar(pacienteEliminarObraSocialPaciente, 5);
        } catch (Exception e) {
            mensaje("Error al eliminar Obra social paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
        buscarPaciente();

    }

    @FXML
    private void eliminarPaciente(MouseEvent event) {
        try {
            pacienteDao.eliminar(new Paciente(Integer.parseInt(cajaDniDatosPrincipales.getText())), 1);
        } catch (Exception e) {
            mensaje("Error al eliminar paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }

    /*
        OBRA SOCIAL         OBRA SOCIAL             OBRA SOCIAL             OBRA SOCIAL                                     
    
        OBRA SOCIAL         OBRA SOCIAL             OBRA SOCIAL             OBRA SOCIAL
    
        OBRA SOCIAL         OBRA SOCIAL             OBRA SOCIAL             OBRA SOCIAL
    
        OBRA SOCIAL         OBRA SOCIAL             OBRA SOCIAL             OBRA SOCIAL
    
     */
    //                              ****
    //                              ****
    //                              ****
    //      BUSCAR OBRA SOCIAL
    //                              ****
    //                              ****
    //                              ****
    @FXML
    private void buscarObraSocia(MouseEvent event) {

        ObraSocial obraSocial = new ObraSocial();
        obraSocialDao = new ObraSocialDAOImplementacion();
        obraSocial = obraSocialDao.obtener(new ObraSocial(cajaBuscarObraSocial.getText()));

        String planes = "";
        for (String plan : obraSocial.getPlanes()) {
            planes += plan + " | ";
        }

        cajaVerPlanesObraSocial.setText(planes);

    }

    //                      ****
    //                      ****
    //                      ****
    //   CREAR OBRA SOCIAL  
    //                      ****
    //                      ****
    //                      ****
    @FXML
    private void crearObraSocial(MouseEvent event) {
        obraSocialDao = new ObraSocialDAOImplementacion();
        ObraSocial obraSocial = new ObraSocial(cajaNombreObraSocial.getText(), new Telefono(cajaTelefonoObraSocial.getText()), new Web(cajaWebObraSocial.getText()), true, new Email(cajaEmailObraSocial.getText()));
        obraSocialDao.insertar(obraSocial, 1);
    }

    /*
        OTRAS FUNCIONES             OTRAS FUNCIONES             OTRAS FUNCIONES
        
        OTRAS FUNCIONES             OTRAS FUNCIONES             OTRAS FUNCIONES
    
        OTRAS FUNCIONES             OTRAS FUNCIONES             OTRAS FUNCIONES
    
        OTRAS FUNCIONES             OTRAS FUNCIONES             OTRAS FUNCIONES
    
     */
    //                                      ****
    //                                      ****
    //                                      ****
    //      VALIDAR  DATOS PACIENTE  
    //                                      ****
    //                                      ****
    //                                      ****
    public void validarSesion() {

    }

    /**
     * *
     * AL DAR AL BOTON BUSCAR BUSCA AL PACIENTE SEGUN EL DOCUMENTO
     *
     * @param event
     */
    @FXML
    protected void alDarEnterBuscarBoton(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                buscarPaciente();
            }
        }
    }

    @FXML
    private void agregarPlan(MouseEvent event) {
    }

}
