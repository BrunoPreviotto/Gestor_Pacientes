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
import com.pacientes.gestor_paciente.CRUD.ObtenerPaciente;
import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.utilidades.TablaObrasSociales;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        inicializarTableObraSocial();
        
        VariablesEstaticas.usuario.setId(usuarioDao.obtenerIdUsuario());
        
        
        //AGENDA
        VariablesEstaticas.anchorPrincipalAgenda = apAgendaPrincipal;
        VariablesEstaticas.anchorAgendaAgenda = apAgendaAgenda;
        VariablesEstaticas.gridAgenda = gpCalendario;
        AgendaController agenda = new AgendaController();
        agenda.rellenarAgenda(1);

        choiseTipoSesionPlan.setOnAction(this::cambiarCategoria);

        choiseNombreObraSocialPaciente.setOnAction(this::cambiarPlanesObraSocial);
        
        choiseCodigoFactSesionObraSocial.setOnAction(this::cambiarCodigoFacturacion);

        //INICIALIZAR CAJAS ESTATICAS
        
        //PACIENTE
        VariablesEstaticas.cajasDatosPrincipales
                = Arrays.asList(
                        cajaNombreDatosPrincipales,
                        cajaApellidoDatosPrincipales,
                        cajaEdadDatosPrincipales,
                        cajaDniDatosPrincipales,
                        cajaTelefonoDatosPrincipales,
                        cajaHonorariosDatosPrincipales);

        VariablesEstaticas.cajasSesiones
                = Arrays.asList(
                        cajaAutorizacionSesion,
                        cajaCopagoSesionObraSocial,
                        cajaEstadoFacturacionSesionObraSocial,
                        cajaCodigoFacturacion,
                        cajaHonorariosPorSesion);

        VariablesEstaticas.cajasAreaSesion
                = Arrays.asList(
                        cajaTrabajoSesion,
                        cajaObservacionSesion,
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

        //OBRA SOACIAL
        VariablesEstaticas.cajasObrasSociales
                = Arrays.asList(
                        cajaNombreObraSocial,
                        cajaTelefonoObraSocial,
                        cajaEmailObraSocial,
                        cajaWebObraSocial);
        
        VariablesEstaticas.choiceObraSocial 
                = Arrays.asList(
                        choiceVerPlanesObraSocial);
        
         /*
            1 NOMBRE
            2 TELEFONO
            3 MAIL
            4 WEB
            5 PLAN
         */
        VariablesEstaticas.valoresBUsquedaObraSocial 
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "");
        
        
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
                        "5", "",
                        "6", "");

        /*
            1 FECHA SESION
            2 NUMERO SESION
            3 TRABAJO SESION
            4 OBSERVACION SESION
            5 HONORARIOS POR SESION
            6 ESTADO DE FACTURACION
            
         */
        VariablesEstaticas.valoresBUsquedaSesiones
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "",
                        "6", "");
        
        /*
            1 NUMERO AUTORIZACION
            2 OBSERVACION AUTORIZACION
            3 ASOCIACION AUTORIZACION
            4 COPAGO AUTORIZACION
            5 CODIGO FACTURACION AUTORIZACION
        */
        VariablesEstaticas.valoresBUsquedaSesionesAtorizacion
                = Map.of(
                        "1", "",
                        "2", "",
                        "3", "",
                        "4", "",
                        "5", "");

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

        VariablesEstaticas.listaBotonesActualizar
                = Arrays.asList(
                        botonActualizarDatosPrincipales,
                        botonActualizarPlanTratamiento,
                        botonActualizarDiagnostico,
                        botonActualizarObraSocialPaciente);

        VariablesEstaticas.listaBotonesEliminar
                = Arrays.asList(
                        botonEliminarDatosPrincipales,
                        botonEliminarPlanTratamiento,
                        botonEliminarDiagnostico,
                        botonEliminarObraSocialPaciente);

        VariablesEstaticas.listaBotonesCrear
                = Arrays.asList(
                        botonAgregarDatosPrincipales,
                        botonAgregarPlanTratamiento,
                        botonAgregarPlanFrecuencia,
                        botonAgregarPlanTipoSesion,
                        botonAgregarDiagnostico,
                        botonAgregarObraSocialPaciente);

        servicioPaciente.
                deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar).
                deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar);

        

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

        try {


            Paciente pacienteBuscar = new Paciente();
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //BUSCAR ID PACIENTE BUSCADO
                
                pacienteBuscar.setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))));
                //SI PACIENTE EXISTE
                if (pacienteBuscar.getId() != 0) {
                    Paciente pacienteResultado = pacienteDao.obtener(pacienteBuscar);

                    //PACIENTE NO ESTA VACIO
                    if (Objects.nonNull(pacienteResultado)) {

                        //SI DATOS PRINCIPALES EXISTEN
                        if (Objects.nonNull(pacienteResultado.getDni())) {
                            
                            buscarDatosPrincipales(pacienteResultado);

                        } else {
                            
                            servicioPaciente.
                                    vaciarListaDatosPrincipales().
                                    habilitarBotonCrear(botonAgregarDatosPrincipales).
                                    desHabilitarEliminarActualizar(botonEliminarDatosPrincipales, botonActualizarDatosPrincipales);
                        }

                        //SI SESIONES EXISTEN
                        
                        if (Objects.nonNull(pacienteResultado.getSesiones())) {
                            
                            buscarSesiones(pacienteResultado);
                            
                        }else{
                            //servicioPaciente.vaciarListaSesiones();
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
                                    deshabilitarCajas(VariablesEstaticas.cajasPlanes).
                                    desHabilitarBotonCrear(botonAgregarPlanTratamiento).
                                    habilitarEliminarActualizar(botonEliminarPlanTratamiento, botonActualizarPlanTratamiento);

                        } else {
                            servicioPaciente.
                                    vaciarListaPlan().
                                    visivilizarChoice(VariablesEstaticas.choisePlan).
                                    habilitarCajas(VariablesEstaticas.cajasPlanes).
                                    esconderCajas(VariablesEstaticas.cajasPlanes).
                                    vaciarValorChoise(VariablesEstaticas.choisePlan).
                                    vaciarCajas(VariablesEstaticas.cajasPlanes).
                                    habilitarBotonCrear(botonAgregarPlanTratamiento).
                                    desHabilitarEliminarActualizar(botonEliminarPlanTratamiento, botonActualizarPlanTratamiento);
                        }

                        //SI DIAGNOSTICO EXISTE
                        if (Objects.nonNull(pacienteResultado.getDiagnostico())) {
                            cajaDiagnosticoDiagnostico.setText(pacienteResultado.getDiagnostico().getDiagnostico());
                            cajaObservacionDiagnostico.setText(pacienteResultado.getDiagnostico().getObservacion());

                            servicioPaciente.
                                    rellenarListaDiagnostico(pacienteResultado).
                                    deshabilitarCajasArea(VariablesEstaticas.cajasAreaDiagnostico).
                                    desHabilitarBotonCrear(botonAgregarDiagnostico).
                                    habilitarEliminarActualizar(botonEliminarDiagnostico, botonActualizarDiagnostico);;

                        } else {
                            servicioPaciente.
                                    vaciarListaDiagnostico().
                                    vaciarCajasArea(VariablesEstaticas.cajasAreaDiagnostico).
                                    habilitarBotonCrear(botonAgregarDiagnostico).
                                    desHabilitarEliminarActualizar(botonEliminarDiagnostico, botonActualizarDiagnostico);
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
                                    deshabilitarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                    desHabilitarBotonCrear(botonAgregarObraSocialPaciente).
                                    habilitarEliminarActualizar(botonEliminarObraSocialPaciente, botonActualizarObraSocialPaciente);;

                        } else {
                            servicioPaciente.
                                    vaciarListaObrasocialPaciente().
                                    visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                                    habilitarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                    esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                    vaciarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                    vaciarValorChoise(VariablesEstaticas.choiseObraSocialPaciente).
                                    habilitarBotonCrear(botonAgregarObraSocialPaciente).
                                    desHabilitarEliminarActualizar(botonEliminarObraSocialPaciente, botonActualizarObraSocialPaciente);
                        }

                        servicioPaciente.
                                desHabilitarBotonCrear(botonAgregarPlanFrecuencia).
                                desHabilitarBotonCrear(botonAgregarPlanTipoSesion);

                    } else {
                        vBoxSesiones.setVisible(false);
                        vBoxAutorizacion.setVisible(false);
                        hbTablasSesionesAtorizaciones.setVisible(true);
                        mensaje("Paciente no encontrado", this, VariablesEstaticas.imgenAdvertencia);
                        servicioPaciente.
                                vaciarListas().
                                vaciarTodo().
                                habilitarTodo().
                                esconderCajas(VariablesEstaticas.cajasPlanes).
                                visivilizarChoice(VariablesEstaticas.choisePlan).
                                esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                                habilitarBotones(VariablesEstaticas.listaBotonesCrear).
                                deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar).
                                deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar);
                    }

                } else {
                    vBoxSesiones.setVisible(false);
                    vBoxAutorizacion.setVisible(false);
                    hbTablasSesionesAtorizaciones.setVisible(true);
                    mensaje("Paciente no encontrado", this, VariablesEstaticas.imgenAdvertencia);
                    servicioPaciente.
                            vaciarListas().
                            vaciarTodo().
                            habilitarTodo().
                            esconderCajas(VariablesEstaticas.cajasPlanes).
                            visivilizarChoice(VariablesEstaticas.choisePlan).
                            esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                            visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                            habilitarBotones(VariablesEstaticas.listaBotonesCrear).
                            deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar).
                            deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar);
                }

            } else {
                vBoxSesiones.setVisible(false);
                vBoxAutorizacion.setVisible(false);
                hbTablasSesionesAtorizaciones.setVisible(true);
                cajaPlanFrecuenciaSesiones.setVisible(false);
                cajaNombreTipoSesionPlan.setVisible(false);
                mensaje("Paciente no encontrado", this, VariablesEstaticas.imgenAdvertencia);
                //VACIAR CAJAS SI PACIENTE NO EXISTE
                servicioPaciente.
                        vaciarTodo().
                        vaciarListas().
                        habilitarTodo().
                        esconderCajas(VariablesEstaticas.cajasPlanes).
                        visivilizarChoice(VariablesEstaticas.choisePlan).
                        esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                        visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                        habilitarBotones(VariablesEstaticas.listaBotonesCrear).
                        deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar).
                        deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar);
            }

        } catch (Exception e) {
             mensaje("Error al buscar paciente", this, VariablesEstaticas.imgenError);
                            vBoxSesiones.setVisible(false);
                            vBoxAutorizacion.setVisible(false);
                            hbTablasSesionesAtorizaciones.setVisible(true);
                            servicioPaciente.
                                vaciarListas().
                                vaciarTodo().
                                esconderCajas(VariablesEstaticas.cajasPlanes).
                                visivilizarChoice(VariablesEstaticas.choisePlan).
                                esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                                habilitarBotones(VariablesEstaticas.listaBotonesCrear).
                                deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar).
                                deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar);
        }
        hbTablasSesionesAtorizaciones.setVisible(true);
        vBoxSesiones.setVisible(false);
        vBoxAutorizacion.setVisible(false);
        choiseNombreObraSocialPaciente.getItems().clear();
        choiseNombreObraSocialPaciente.setValue("");
        choisePlanesObraSocialPacientePlan.getItems().clear();
        choiseTipoSesionPlan.getItems().clear();
        choiseFrecuenciaSesionPlan.getItems().clear();
        choiseCodigoFactSesionObraSocial.getItems().clear();
        iniciarChoiseList();
        
        
       
    }
    
    public void buscarDatosPrincipales(Paciente pacienteResultado) {
        cajaNombreDatosPrincipales.setText(servicioPaciente.CapitalCase(pacienteResultado.getNombre()));
        cajaApellidoDatosPrincipales.setText(servicioPaciente.CapitalCase(pacienteResultado.getApellido()));
        cajaEdadDatosPrincipales.setText(String.valueOf(pacienteResultado.getEdad()));
        cajaDniDatosPrincipales.setText(String.valueOf(pacienteResultado.getDni()));
        cajaTelefonoDatosPrincipales.setText(pacienteResultado.getTelefono().getTelefono());

        cajaHonorariosDatosPrincipales.setText(String.valueOf(pacienteResultado.getHonorarios().getHonorario()));
        //RELENAR LISTA CON PACIENTE ACTUAL
        servicioPaciente.
                rellenarListaDatosPrincipales(pacienteResultado).
                deshabilitarCajas(VariablesEstaticas.cajasDatosPrincipales).
                desHabilitarBotonCrear(botonAgregarDatosPrincipales).
                habilitarEliminarActualizar(botonEliminarDatosPrincipales, botonActualizarDatosPrincipales);;

        titlePaneDatosPrincipales.setExpanded(true);
    }
    
    
    public void buscarSesiones(Paciente pacienteResultado){
        ObservableList<TablaSesiones> olSesiones = FXCollections.observableArrayList();
                            for (SesionPaciente sp : pacienteResultado.getSesiones()) {

                                olSesiones.add(new TablaSesiones(
                                        String.valueOf(sp.getNumeroSesion()),
                                        sp.getFecha().toString(),
                                        sp.getTrabajoSesion(),
                                        sp.getObservacion(),
                                        String.valueOf(sp.getHonorarioPorSesion()),
                                        sp.getEstado().getEstado(),
                                        String.valueOf(sp.getAutorizacion().getNumeroAutorizacion()),
                                        sp.getAutorizacion().getObservacion(),
                                        sp.getAutorizacion().getAsociacion().toString(),
                                        String.valueOf(sp.getAutorizacion().getCopago()),
                                        sp.getAutorizacion().getCodigoFacturacion().getNombre()));
                                
                            }

                            tablaAutorizacion.setItems(olSesiones);
                            tableSesiones.setItems(olSesiones);
                            ColumnaSesionNumero.setCellValueFactory(new PropertyValueFactory<>("numeroSesion"));
                            ColumnaSesionFecha.setCellValueFactory(new PropertyValueFactory<>("fechaSesion"));
                            ColumnaSesionTrabajo.setCellValueFactory(new PropertyValueFactory<>("trabajoSesion"));
                            ColumnaSesionObservacion.setCellValueFactory(new PropertyValueFactory<>("observacionSesion"));
                            ColumnaSesionHonorarioPorSesion.setCellValueFactory(new PropertyValueFactory<>("honorariosPorSesion"));
                            ColumnaSesionEstadoFacturacion.setCellValueFactory(new PropertyValueFactory<>("estadoFacturacion"));
                            
                            columnaAutorizacionNumero.setCellValueFactory(new PropertyValueFactory<>("numeroAutorizacion"));
                            columnaAutorizacionObservacion.setCellValueFactory(new PropertyValueFactory<>("observacionAutorizacion"));
                            columnaAutorizacionAsociacion.setCellValueFactory(new PropertyValueFactory<>("asociacion"));
                            columnaAutorizacionCopago.setCellValueFactory(new PropertyValueFactory<>("copago"));
                            columnaAutorizacionCodigoFacturacion.setCellValueFactory(new PropertyValueFactory<>("nombreCodigo"));
                            columnaNumeroSecionAutorizacion.setCellValueFactory(new PropertyValueFactory<>("numeroSesion"));
                            
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
                pacienteDao.insertar(
                        new Paciente(
                                cajaNombreDatosPrincipales.getText(), 
                                cajaApellidoDatosPrincipales.getText(), 
                                Integer.parseInt(cajaEdadDatosPrincipales.getText()), 
                                Integer.parseInt(cajaDniDatosPrincipales.getText()), 
                                new Honorario(Double.parseDouble(cajaHonorariosDatosPrincipales.getText())), 
                                new Telefono(cajaTelefonoDatosPrincipales.getText())), 1);
                //SETEAR CAJA BUSCAR CON EL PACIENTE CREADO
                cajaBuscarPaciente.setText(cajaDniDatosPrincipales.getText());
                buscarPaciente();
                //INFORMAR DE CREACION CON EXITO
                mensaje("Paciente creado con èxito", this, "/com/pacientes/gestor_pacientes/img/error.png");

            }

        } catch (Exception e) {
            cajaBuscarPaciente.setText("");
            mensaje(e.getMessage(), this, "/com/pacientes/gestor_pacientes/img/error.png");
            cajaNombreDatosPrincipales.getStyleClass().add("cajasARellenar");
        }
    }

    @FXML
    private void crearSesion(MouseEvent event) {

        if (!cajaBuscarPaciente.getText().isBlank()) {
            try {
                hbTablasSesionesAtorizaciones.setVisible(false);
                
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
                            vaciarValorChoise(VariablesEstaticas.choiseSesiones);
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
                            autorizacionesSesionesObraSociales = rellenarAutorizacionVacia();
                        }
                        servicioPaciente.
                                datosSesionVacios();

                        SesionPaciente sesion = new SesionPaciente(Integer.valueOf(cajaNumeroSesion.getText()),
                                cajaFechaSesion.getValue(),
                                cajaTrabajoSesion.getText(),
                                cajaObservacionSesion.getText(),
                                Double.parseDouble(cajaHonorariosPorSesion.getText()),
                                autorizacionesSesionesObraSociales,
                                new EstadoFacturacion(cajaEstadoFacturacionSesionObraSocial.getText()));

                        pacienteCrearSesion.setSesion(sesion);
                        pacienteCrearSesion.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
                        pacienteCrearSesion.setId(pacienteDao.obtenerIdPacienteConTodosLosDatos(pacienteCrearSesion));

                        if (!cajaDniDatosPrincipales.getText().isBlank()) {

                            servicioPaciente.
                                    datosSesionVacios().
                                    datosAutorizacionSesionVacios();

                            pacienteDao.insertar(pacienteCrearSesion, 3);
                            botonAgregarSesiones.setDisable(false);
                            botonEliminarSesiones.setDisable(false);
                            botonActualizarSesiones.setDisable(false);
                            botonRetornarSesiones.setDisable(true);
                            vBoxSesiones.setVisible(false);
                            vBoxAutorizacion.setVisible(false);
                            hbTablasSesionesAtorizaciones.setVisible(true);
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

        } else {
            mensaje("Buscar paciente para crear sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
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
                pacientePlan.setId(pacienteDao.obtenerIdPacienteConTodosLosDatos(pacientePlan));
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
            } else {
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
                pacienteCrearDiagnostico.setId(pacienteDao.obtenerIdPacienteConTodosLosDatos(pacienteCrearDiagnostico));
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
            } else {
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
                    pacienteCrearObraSocial.setId(pacienteDao.obtenerIdPacienteConTodosLosDatos(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                    pacienteDao.insertar(pacienteCrearObraSocial, 2);
                    mensaje("Obra Social del Paciente creado con èxito", this, "/com/pacientes/gestor_pacientes/img/error.png");

                    buscarPaciente();
                }
            } else {
                mensaje("Buscar paciente para crear Obra Social del paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().equals("afiliado existente")){
                mensaje(e.getMessage(), this, "/com/pacientes/gestor_pacientes/img/error.png");
            }else{
                mensaje("Error al crear obra social del paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
            
        }
    }

    @FXML
    private void insertarTipoPlan(MouseEvent event) {

        try {
            if(!cajaNombreTipoSesionPlan.getText().isBlank()){
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
                    choiseTipoSesionPlan.getItems().clear();
                    iniciarChoiseList();
                }
            }else{
                mensaje("Ingresar tipo sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
            botonAgregarPlanTipoSesion.setId("1");
        } catch (Exception e) {
            mensaje("Error al crear tipo sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
        
    }

    @FXML
    private void insertarFrecuenciaPlan(MouseEvent event) {

        try {
           if(!cajaPlanFrecuenciaSesiones.getText().isBlank()){
                Paciente pacienteTipoSesion = new Paciente();
                PlanTratamiento plan = new PlanTratamiento();
                plan.setFrecuenciaSesion(cajaPlanFrecuenciaSesiones.getText());
                pacienteTipoSesion.setPlanTratamiento(plan);
                cajaPlanFrecuenciaSesiones.setVisible(true);
                if (botonAgregarPlanFrecuencia.getId().equals("1")) {
                    pacienteDao.insertar(pacienteTipoSesion, 7);
                    botonAgregarPlanFrecuencia.setId("botonAgregarPlanFrecuencia");
                    cajaPlanFrecuenciaSesiones.setVisible(false);
                    choiseFrecuenciaSesionPlan.getItems().clear();
                    iniciarChoiseList();
                }
           }else{
               mensaje("Ingresar frecuencia", this, "/com/pacientes/gestor_pacientes/img/error.png");
           }
           botonAgregarPlanFrecuencia.setId("1");
        } catch (Exception e) {
            mensaje("Error al crear frecuencia", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
        
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
                    } else {
                        servicioPaciente.datosPrincipalesVacios();
                        pacienteDao.actualizar(
                                paciente.
                                        setNombre(cajaNombreDatosPrincipales.getText()).
                                        setApellido(cajaApellidoDatosPrincipales.getText()).
                                        setEdad(Integer.parseInt(cajaEdadDatosPrincipales.getText())).
                                        setDni(Integer.parseInt(cajaDniDatosPrincipales.getText())).
                                        setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())))).
                                        setTelefono(new Telefono(cajaTelefonoDatosPrincipales.getText())).
                                        setHonorarios(new Honorario(Double.parseDouble(cajaHonorariosDatosPrincipales.getText()))), 3);
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
    private void actualizarDiagnostico(MouseEvent event) {
        if (botonActualizarDiagnostico.getId().equals("1")) {
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                if (cajaDiagnosticoDiagnostico.getText().isBlank()) {
                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.pintarCajaAreaVaciaImportante(VariablesEstaticas.cajasAreaDiagnostico);
                } else {
                    try {
                        servicioPaciente.datosDiagnosticoVacios();
                        Paciente paciente = new Paciente();
                        pacienteDao.actualizar(paciente.setDiagnostico(new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText())).setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())))), 1);
                        botonActualizarDiagnostico.setId("botonActualizarDiagnostico");

                        servicioPaciente.deshabilitarCajasArea(VariablesEstaticas.cajasAreaDiagnostico);

                        mensaje("Diagnóstico actualizado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");

                        buscarPaciente();
                    } catch (Exception e) {
                        mensaje("Error al actualizar diagnóstico", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    }
                }
            } else {
                mensaje("Buscar paciente a actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            botonActualizarDiagnostico.setId("1");
            servicioPaciente.
                    animarCajasAreaAlDarABoton(VariablesEstaticas.cajasAreaDiagnostico).
                    habilitarCajasArea(VariablesEstaticas.cajasAreaDiagnostico);
        }

    }

    @FXML
    private void actualizarPlan(MouseEvent event) {
        if (botonActualizarPlanTratamiento.getId().equals("1")) {
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                if (Objects.isNull(choiseFrecuenciaSesionPlan.getValue()) || Objects.isNull(choiseTipoSesionPlan.getValue())) {
                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.pintarChoiseVacioImportante(VariablesEstaticas.choisePlan);
                } else {
                    try {
                        servicioPaciente.datosPlanVacios();
                        Paciente pacientePlan = new Paciente();
                        pacienteDao.
                                actualizar(
                                        pacientePlan.setPlanTratamiento(new PlanTratamiento(cajaEstrategiaPlan.getText(),
                                                cajaPlanFrecuenciaSesiones.getText(),
                                                new TipoSesion(choiseTipoSesionPlan.getValue(),
                                                        "Sin descripcion"))).setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())))),
                                        2);

                        servicioPaciente.
                                visivilizarCajas(VariablesEstaticas.cajasPlanes).
                                esconderChoice(VariablesEstaticas.choisePlan).
                                deshabilitarCajas(VariablesEstaticas.cajasPlanes);

                        mensaje("Diagnóstico actualizado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");

                        botonActualizarPlanTratamiento.setId("botonAgregarPlanTratamiento");

                        buscarPaciente();

                    } catch (Exception e) {
                        mensaje("Error al actualizar Plan", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    }
                }
            } else {
                mensaje("Buscar paciente a actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            botonActualizarPlanTratamiento.setId("1");
            servicioPaciente.
                    visivilizarChoice(VariablesEstaticas.choisePlan).
                    esconderCajas(VariablesEstaticas.cajasPlanes).
                    habilitarCajas(VariablesEstaticas.cajasPlanes).
                    animarCajasAlDarABoton(VariablesEstaticas.cajasPlanes).
                    animarChoiceAlDarABoton(VariablesEstaticas.choisePlan);
        }

    }
    
    @FXML
    private void actualizarFrecuenciaPlan(MouseEvent event) {
        try {
            PlanTratamiento plan = new PlanTratamiento();
            if (botonActualizarPlanFrecuencia.getId().equals("1")) {

                if (Objects.nonNull(choiseFrecuenciaSesionPlan.getValue())) {
                    plan.setFrecuenciaSesion(choiseFrecuenciaSesionPlan.getValue());
                    plan.setIdPlan(pacienteDao.obtenerIdFrecuenciaSesion(plan));
                    plan.setFrecuenciaSesion(cajaPlanFrecuenciaSesiones.getText());
                    pacienteDao.actualizarFrecuenciaPlan(plan);
                    choiseFrecuenciaSesionPlan.getItems().clear();
                    
                    iniciarChoiseList();
                    
                    
                } else {
                    mensaje("Seleccionar frecuencia para actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
                choiseFrecuenciaSesionPlan.setDisable(false);
                cajaPlanFrecuenciaSesiones.setVisible(false);
                botonAgregarPlanTratamiento.setDisable(false);
                botonActualizarPlanTratamiento.setDisable(false);
                botonEliminarPlanTratamiento.setDisable(false);
                botonActualizarPlanFrecuencia.setId("botonActualizarPlanFrecuencia");
                
            } else {
                botonEliminarPlanTratamiento.setDisable(true);
                botonActualizarPlanTratamiento.setDisable(true);
                botonAgregarPlanTratamiento.setDisable(true);
                cajaPlanFrecuenciaSesiones.setText(choiseFrecuenciaSesionPlan.getValue());
                choiseFrecuenciaSesionPlan.setDisable(true);
                cajaPlanFrecuenciaSesiones.setVisible(true);
                botonActualizarPlanFrecuencia.setId("1");
            }
        } catch (Exception e) {
            mensaje("Error al actualizar frecuencia", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

    }
    
    
    @FXML
    private void actualizarTipoPlan(MouseEvent event){
        try {
            TipoSesion tipo = new TipoSesion();
            if (botonActualizarPlanTipoSesion.getId().equals("1")) {

                if (!choiseTipoSesionPlan.getSelectionModel().isEmpty()) {
                    tipo.setNombre(choiseTipoSesionPlan.getValue());
                    tipo.setId(pacienteDao.obtenerIdTipoSesion(tipo));
                    tipo.setNombre(cajaNombreTipoSesionPlan.getText());
                    tipo.setDecripcion(cajaDescripcionTipoSesionPlan.getText());
                    pacienteDao.actualizarTipoSesion(tipo);
                    choiseTipoSesionPlan.getItems().clear();
                    choiseTipoSesionPlan.setValue("");
                    iniciarChoiseList();
                    
                    
                } else {
                    mensaje("Seleccionar tipo sesión para actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
                choiseTipoSesionPlan.setDisable(false);
                cajaNombreTipoSesionPlan.setVisible(false);
                botonAgregarPlanTratamiento.setDisable(false);
                botonActualizarPlanTratamiento.setDisable(false);
                botonEliminarPlanTratamiento.setDisable(false);
                cajaDescripcionTipoSesionPlan.setDisable(true);
                botonActualizarPlanTipoSesion.setId("botonActualizarPlanTipoSesion");
                
            } else {
                botonEliminarPlanTratamiento.setDisable(true);
                botonActualizarPlanTratamiento.setDisable(true);
                botonAgregarPlanTratamiento.setDisable(true);
                cajaNombreTipoSesionPlan.setText(choiseTipoSesionPlan.getValue());
                choiseTipoSesionPlan.setDisable(true);
                cajaNombreTipoSesionPlan.setVisible(true);
                cajaDescripcionTipoSesionPlan.setDisable(false);
                botonActualizarPlanTipoSesion.setId("1");
            }
        } catch (Exception e) {
            mensaje("Error al actualizar tipo sesion", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }
    
    @FXML
    private void actualizarObraSocialPaciente(MouseEvent event) {
        
        
        if (botonActualizarObraSocialPaciente.getId().equals("1")) {
            try {

                if (!cajaBuscarPaciente.getText().isEmpty()) {
                    if (Objects.isNull(choiseNombreObraSocialPaciente.getValue())
                            || Objects.isNull(choisePlanesObraSocialPacientePlan.getValue())
                            || cajaNAfiliadoObraSocialPaciente.getText().isEmpty()) {

                        //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                        mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                        servicioPaciente.
                                pintarCajaVaciaImportante(VariablesEstaticas.cajasObraSocialPaciente).
                                pintarChoiseVacioImportante(VariablesEstaticas.choiseObraSocialPaciente);

                    } else {
                        Paciente pacienteObraSocialPaciente = new Paciente();
                        ObraSocialPaciente obraSocialPaciente = new ObraSocialPaciente();
                        PlanObraSocial planObraSocial = new PlanObraSocial();
                        Afiliado afiliado = new Afiliado();

                        //setear valores para obtener ids
                        for (Map.Entry<String, String> entry : VariablesEstaticas.valoresBUsquedaObraSocialPaciente.entrySet()) {
                            switch (entry.getKey()) {
                                case "1":
                                    obraSocialPaciente.setNombre(entry.getValue());
                                    break;
                                case "2":
                                    planObraSocial.setNombre(entry.getValue());
                                    break;
                                case "3":
                                    afiliado.setNumero(Integer.parseInt(entry.getValue()));
                                    break;

                            }
                        }
                        
                        obraSocialPaciente.setPlan(planObraSocial);
                        obraSocialPaciente.setAfiliado(afiliado);
                        
                       

                        //crear paciente
                       pacienteObraSocialPaciente.setObraSocialPaciente(obraSocialPaciente);
                        
                       
                                                        
                                                                            
                        
                        
                        
                        //obtner id de anterior obra social y plan
                        pacienteObraSocialPaciente.getObraSocialPaciente().getAfiliado().setId(pacienteDao.obtenerIdAfiliadoObraSocial(pacienteObraSocialPaciente));
                        pacienteObraSocialPaciente.getObraSocialPaciente().getPlan().setId(pacienteDao.obtenerIdPlanObraSocial(pacienteObraSocialPaciente));
                        pacienteObraSocialPaciente.getObraSocialPaciente().setId(pacienteDao.obtenerIdObraSocia(pacienteObraSocialPaciente));
                        pacienteObraSocialPaciente.setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(pacienteObraSocialPaciente.setDni(Integer.valueOf(cajaBuscarPaciente.getText()))));
                        
                        //pasar valores nuevos
                       pacienteObraSocialPaciente.getObraSocialPaciente().getPlan().setNombre(choisePlanesObraSocialPacientePlan.getValue());
                       pacienteObraSocialPaciente.getObraSocialPaciente().getAfiliado().setNumero(Integer.parseInt(cajaNAfiliadoObraSocialPaciente.getText()));
                       pacienteObraSocialPaciente.getObraSocialPaciente().setNombre(choiseNombreObraSocialPaciente.getValue());
                        
                        
                        
                        
                        

                        pacienteDao.actualizar(pacienteObraSocialPaciente, 5);

                        buscarPaciente();
                        servicioPaciente.
                                visivilizarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                esconderChoice(VariablesEstaticas.choiseObraSocialPaciente).
                                deshabilitarCajas(VariablesEstaticas.cajasObraSocialPaciente);
                        botonActualizarObraSocialPaciente.setId("botonActualizarObraSocialPaciente");
                    }
                } else {
                    mensaje("Buscar paciente a actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensaje("Error al actualizar obra social del paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            botonActualizarObraSocialPaciente.setId("1");
            servicioPaciente.
                    visivilizarChoice(VariablesEstaticas.choiseObraSocialPaciente).
                    esconderCajas(VariablesEstaticas.cajasObraSocialPaciente).
                    habilitarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                    animarCajasAlDarABoton(VariablesEstaticas.cajasObraSocialPaciente).
                    animarChoiceAlDarABoton(VariablesEstaticas.choiseObraSocialPaciente);
        }

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
                    pacienteBuscar.setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(pacienteBuscar));

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
                    
                    sesion.setEstado(new EstadoFacturacion(cajaEstadoFacturacionSesionObraSocial.getText()));
                    sesion.setIdSesion(idSesion);
                    sesion.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
                    sesion.setFecha(ldsNuevo);
                    sesion.setTrabajoSesion(cajaTrabajoSesion.getText());
                    sesion.setObservacion(cajaObservacionSesion.getText());
                    sesion.setHonorarioPorSesion(Double.parseDouble(cajaHonorariosPorSesion.getText()));
                    pacienteSesion.setSesion(sesion);
                    pacienteSesion.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteSesion.setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(pacienteSesion));
                    
                    servicioPaciente.
                            datosSesionVacios().
                            vaciarCajas(VariablesEstaticas.cajasSesiones).
                            vaciarChoise(VariablesEstaticas.choiseSesiones).
                            vaciarCajasArea(VariablesEstaticas.cajasAreaSesion).
                            desHabilitarEliminarActualizar(botonEliminarSesiones, botonActualizarSesiones). 
                            habilitarBotonCrear(botonAgregarSesiones);
                    pacienteDao.actualizar(pacienteSesion, 4);
                    mensaje("Sesión actualizada con éxito sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    vBoxAutorizacion.setVisible(false);
                    vBoxSesiones.setVisible(false);
                    hbTablasSesionesAtorizaciones.setVisible(true);
                    botonActualizarSesiones.setId("botonActualizarSesiones");
                    buscarPaciente();
                } else {
                    //BOTONERA
                    hbTablasSesionesAtorizaciones.setVisible(false);
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
                    cajaHonorariosPorSesion.setText(tableSesiones.getSelectionModel().getSelectedItem().getHonorariosPorSesion());
                    cajaEstadoFacturacionSesionObraSocial.setText(tableSesiones.getSelectionModel().getSelectedItem().getEstadoFacturacion());
                    
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

    

    //                              ****
    //                              ****
    //                              ****
    //      ELIMINAR  PACIENTE  
    //                              ****
    //                              ****
    //                              ****
    @FXML
    private void eliminarPaciente(MouseEvent event) {
        if (!cajaBuscarPaciente.getText().isEmpty() || !cajaBuscarPaciente.getText().isBlank()) {
            if (!cajaNombreDatosPrincipales.getText().isBlank() && !cajaApellidoDatosPrincipales.getText().isBlank() && !cajaDniDatosPrincipales.getText().isBlank()) {
                try {
                    
                    pacienteDao.eliminar(new Paciente(Integer.parseInt(cajaDniDatosPrincipales.getText())), 1);
                    mensaje("Paciente eliminado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioPaciente.
                            vaciarTodo().
                            deshabilitarBotones(listaBotonesEliminar).
                            deshabilitarBotones(listaBotonesActualizar).
                            habilitarBotones(listaBotonesCrear);
                } catch (Exception e) {
                    mensaje("Error al eliminar paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
            } else {
                mensaje("Faltan datos para eliminar paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            mensaje("Buscar paciente para eliminar", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }

    @FXML
    private void eliminarPlan(MouseEvent event) {
        if (!cajaBuscarPaciente.getText().isEmpty()) {
            if (Objects.nonNull(choiseFrecuenciaSesionPlan.getValue()) && Objects.nonNull(choiseTipoSesionPlan.getValue())) {
                try {
                    Paciente pacienteEliminarPlanTratamiento = new Paciente();
                    pacienteEliminarPlanTratamiento.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteDao.eliminar(pacienteEliminarPlanTratamiento, 6);
                    mensaje("Plan eliminado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioPaciente.
                            vaciarValorChoise(VariablesEstaticas.choisePlan).
                            vaciarCajas(VariablesEstaticas.cajasPlanes).
                            habilitarBotonCrear(botonAgregarPlanTratamiento).
                            desHabilitarEliminarActualizar(botonEliminarPlanTratamiento, botonActualizarPlanTratamiento).
                            visivilizarChoice(choisePlan).esconderCajas(cajasPlanes);
                    buscarPaciente();
                } catch (Exception e) {
                    mensaje("Error al eliminar Plan de tratamiento", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }

            } else {
                mensaje("Faltan datos para eliminar plan", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            mensaje("Buscar paciente para eliminar", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }

    @FXML
    private void eliminarDiagnostico(MouseEvent event) {

        if (!cajaBuscarPaciente.getText().isEmpty()) {
            if (!cajaDiagnosticoDiagnostico.getText().isBlank()) {
                try {
                    Paciente pacienteEliminarDiagnostico = new Paciente();
                    pacienteEliminarDiagnostico.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteDao.eliminar(pacienteEliminarDiagnostico, 3);
                    mensaje("Diagnóstico eliminado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioPaciente.
                            vaciarCajasArea(VariablesEstaticas.cajasAreaDiagnostico).
                            habilitarBotonCrear(botonAgregarDiagnostico).
                            desHabilitarEliminarActualizar(botonEliminarDiagnostico, botonActualizarDiagnostico);
                    buscarPaciente();
                } catch (Exception e) {
                    mensaje("Error al eliminar diagnóstico", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
            } else {
                mensaje("Faltan datos para eliminar diagnóstico", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            mensaje("Buscar paciente para eliminar", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

    }

    @FXML
    private void eliminarObraSocialPaciente(MouseEvent event) {
        if (!cajaBuscarPaciente.getText().isEmpty()) {
            if (Objects.nonNull(choiseNombreObraSocialPaciente.getValue()) || Objects.nonNull(choisePlanesObraSocialPacientePlan.getValue())) {
                try {
                    Paciente pacienteEliminarObraSocialPaciente = new Paciente();
                    pacienteEliminarObraSocialPaciente.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteDao.eliminar(pacienteEliminarObraSocialPaciente, 5);
                    mensaje("Obra social del paciente eliminado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioPaciente.
                            vaciarValorChoise(VariablesEstaticas.choiseObraSocialPaciente).
                            vaciarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                            habilitarBotonCrear(botonAgregarObraSocialPaciente).
                            desHabilitarEliminarActualizar(botonEliminarObraSocialPaciente, botonActualizarObraSocialPaciente).
                            visivilizarChoice(choiseObraSocialPaciente).
                            esconderCajas(cajasObraSocialPaciente);
                    buscarPaciente();
                } catch (Exception e) {
                    mensaje("Error al eliminar Obra social paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }

            } else {
                mensaje("Faltan datos para eliminar obra social del paciente", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } else {
            mensaje("Buscar paciente para eliminar", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }

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
                if (!cajaBuscarPaciente.getText().isBlank()) {

                    //INICIALIZAR VARIABLES
                    pacienteBuscar.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteBuscar.setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(pacienteBuscar));
                    

                    autorizacionBuscar.setNumeroAutorizacion(Integer.parseInt(tablaAutorizacion.getSelectionModel().getSelectedItem().getNumeroAutorizacion()));
                    autorizacionBuscar.setAsociacion(ldAutorizacion);
                    sesionBuscar.setAutorizacion(autorizacionBuscar);
                    sesionBuscar.setFecha(ldSesion);
                    sesionBuscar.setNumeroSesion(Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion()));
                    pacienteBuscar.setSesion(sesionBuscar);

                    int idSesion = pacienteDao.obtenerIdSesionAutorizacion(pacienteBuscar).getSesion().getIdSesion();
                    int idAutorizacion = pacienteDao.obtenerIdSesionAutorizacion(pacienteBuscar).getSesion().getAutorizacion().getId();

                    LocalDate ldsNuevo = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
                    LocalDate ldsaNuevo = LocalDate.parse(tablaAutorizacion.getSelectionModel().getSelectedItem().getAsociacion());

                    /*//SI NO EXISTE AUTORIZACION LA RELLENA CON VALORES VACIOS
                    if (tablaAutorizacion.getSelectionModel().getSelectedItem().getFechaSesion().equals("0") && tablaAutorizacion.getSelectionModel().getSelectedItem().getCopago().equals("0.0") && tablaAutorizacion.getSelectionModel().getSelectedItem().getAsociacion().equals("1700-01-01") && tablaAutorizacion.getSelectionModel().getSelectedItem().getObservacionAutorizacion().equals("-")) {
                        sesion.setAutorizacion(rellenarAutorizacionVacia());
                    } else {

                        
                    }*/
                    
                    autorizacion.setId(idAutorizacion);
                    autorizacion.setNumeroAutorizacion(Integer.parseInt(tablaAutorizacion.getSelectionModel().getSelectedItem().getNumeroAutorizacion()));
                    autorizacion.setAsociacion(ldsaNuevo);
                    autorizacion.setObservacion(tablaAutorizacion.getSelectionModel().getSelectedItem().getObservacionAutorizacion());
                    autorizacion.setCopago(Double.parseDouble(tablaAutorizacion.getSelectionModel().getSelectedItem().getCopago()));
                    codigo.setNombre(tablaAutorizacion.getSelectionModel().getSelectedItem().getNombreCodigo());
                    autorizacion.setCodigoFacturacion(codigo);
                    sesion.setAutorizacion(autorizacion);
                    

                    sesion.setIdSesion(idSesion);
                    sesion.setNumeroSesion(Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion()));
                    sesion.setFecha(ldsNuevo);
                    sesion.setTrabajoSesion(tableSesiones.getSelectionModel().getSelectedItem().getTrabajoSesion());
                    sesion.setObservacion(tableSesiones.getSelectionModel().getSelectedItem().getObservacionSesion());
                    sesion.setHonorarioPorSesion(Double.parseDouble(tableSesiones.getSelectionModel().getSelectedItem().getHonorariosPorSesion()));
                    pacienteSesion.setSesion(sesion);
                    pacienteSesion.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
                    pacienteSesion.setId(pacienteDao.obtenerIdPacienteSoloConIdUsuario(pacienteSesion));
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

                    
                    buscarPaciente();
                    

                }else{
                    mensaje("Buscar paciente para eliminar sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
            }
        } catch (Exception e) {
            mensaje("Error al eliminar sesión", this, "/com/pacientes/gestor_pacientes/img/error.png");
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
    private void buscarObraSocial(MouseEvent event) {

        if (event.getClickCount() == 2) {

            Control control = (Control) event.getSource();
            if (Objects.nonNull(tablaObraSocial)) {
                
                if (Objects.nonNull(control.getId())) {
                    servicioObraSocial.desPintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                    if (control.getId().equals("tablaObraSocial")) {
                        cajaNombreObraSocial.setText(tablaObraSocial.getSelectionModel().getSelectedItem().getNombre());
                        cajaTelefonoObraSocial.setText(tablaObraSocial.getSelectionModel().getSelectedItem().getTelefono());
                        cajaEmailObraSocial.setText(tablaObraSocial.getSelectionModel().getSelectedItem().getEmail());
                        cajaWebObraSocial.setText(tablaObraSocial.getSelectionModel().getSelectedItem().getWeb());
                        choiceVerPlanesObraSocial.getItems().clear();
                        choiceVerPlanesObraSocial.getItems().addAll(tablaObraSocial.getSelectionModel().getSelectedItem().getPlanes().split("; "));
                        cajaBuscarObraSocial.setText(cajaNombreObraSocial.getText());
                        servicioObraSocial.
                                rellenarListaObraSocial(
                                        new ObraSocial(tablaObraSocial.getSelectionModel().getSelectedItem().getNombre(), 
                                                new Telefono(tablaObraSocial.getSelectionModel().getSelectedItem().getTelefono()), 
                                                new Web(tablaObraSocial.getSelectionModel().getSelectedItem().getWeb()), 
                                                new Email(tablaObraSocial.getSelectionModel().getSelectedItem().getEmail()), 
                                                Arrays.asList(tablaObraSocial.getSelectionModel().getSelectedItem().getPlanes().split("; ")))).
                                desHabilitarBotonCrear(botonAgregarObraSocial).
                                habilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                deshabilitarCajas(VariablesEstaticas.cajasObrasSociales);
                        botonAgregarPlanesObraSocial.setDisable(false);
                    }else{
                        mensaje("Error al buscar obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        servicioObraSocial.
                        vaciarListaObraSocial().
                                habilitarCajas(cajasObrasSociales).
                                desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                                vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                                vaciarChoise(VariablesEstaticas.choiceObraSocial);
                        botonAgregarPlanesObraSocial.setDisable(true);
                    }
                }else{
                    mensaje("Error al buscar obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioObraSocial.
                        vaciarListaObraSocial().
                            habilitarCajas(cajasObrasSociales).
                                desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                                vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                                vaciarChoise(VariablesEstaticas.choiceObraSocial);
                    botonAgregarPlanesObraSocial.setDisable(true);
                }

            }else{
                mensaje("Error al buscar obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
                servicioObraSocial.
                        vaciarListaObraSocial().
                        habilitarCajas(cajasObrasSociales).
                        desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                        vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                        vaciarChoise(VariablesEstaticas.choiceObraSocial);
                botonAgregarPlanesObraSocial.setDisable(true);
            }
        } else {
            if (!cajaBuscarObraSocial.getText().isBlank()) {

                if (!cajaBuscarObraSocial.getText().equals("0")) {
                    ObraSocial obraSocial = new ObraSocial();

                    try {
                        obraSocial = obraSocialDao.obtener(new ObraSocial(cajaBuscarObraSocial.getText()));
                        if (Objects.nonNull(obraSocial)) {
                            servicioObraSocial.desPintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                            cajaNombreObraSocial.setText(obraSocial.getNombre());
                            cajaTelefonoObraSocial.setText(obraSocial.getTelefono().getTelefono());
                            cajaEmailObraSocial.setText(obraSocial.getEmail().getEmail());
                            cajaWebObraSocial.setText(obraSocial.getWeb().getWeb());
                            choiceVerPlanesObraSocial.getItems().clear();
                            choiceVerPlanesObraSocial.getItems().addAll(obraSocial.getPlanes());

                            servicioObraSocial.
                                    rellenarListaObraSocial(obraSocial).
                                    desHabilitarBotonCrear(botonAgregarObraSocial).
                                    habilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                    deshabilitarCajas(VariablesEstaticas.cajasObrasSociales);;
                            botonAgregarPlanesObraSocial.setDisable(false);        
                        } else {
                            mensaje("Obra Social no encontrada", this, "/com/pacientes/gestor_pacientes/img/error.png");
                            servicioObraSocial.
                                    vaciarListaObraSocial().
                                    habilitarCajas(cajasObrasSociales).
                                    desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                                    vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                                    vaciarChoise(VariablesEstaticas.choiceObraSocial);
                            botonAgregarPlanesObraSocial.setDisable(true);
                        }
                    } catch (Exception e) {
                        mensaje("Error al buscar obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        servicioObraSocial.
                                vaciarListaObraSocial().
                                habilitarCajas(cajasObrasSociales).
                                desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                                vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                                vaciarChoise(VariablesEstaticas.choiceObraSocial);
                                botonAgregarPlanesObraSocial.setDisable(true);
                    }

                } else {
                    mensaje("Obra Social no encontrada", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioObraSocial.
                            vaciarListaObraSocial().
                            habilitarCajas(cajasObrasSociales).
                            desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                            vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                            vaciarChoise(VariablesEstaticas.choiceObraSocial);
                            botonAgregarPlanesObraSocial.setDisable(true);
                }

            } else {
                mensaje("Obra Social no encontrada", this, "/com/pacientes/gestor_pacientes/img/error.png");
                servicioObraSocial.
                        vaciarListaObraSocial().
                        habilitarCajas(cajasObrasSociales).
                        desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                        vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                        vaciarChoise(VariablesEstaticas.choiceObraSocial);
                        botonAgregarPlanesObraSocial.setDisable(true);
            }
        }

    }

    @FXML
    public void seleccionarObraSocial(MouseEvent event) {
        TableView tb = (TableView) event.getSource();
        if (event.getClickCount() == 2) {
            buscarObraSocial(event);
        }
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
        
        
        try {
            if(!cajaNombreObraSocial.getText().isBlank()){
                obraSocialDao = new ObraSocialDAOImplementacion();
                ObraSocial obraSocial = new ObraSocial(
                        cajaNombreObraSocial.getText(), 
                        new Telefono(cajaTelefonoObraSocial.getText()), 
                        new Web(cajaWebObraSocial.getText()), 
                        true, 
                        new Email(cajaEmailObraSocial.getText()));
                obraSocialDao.insertar(obraSocial, 1);
                inicializarTableObraSocial();
                botonAgregarPlanesObraSocial.setDisable(false);
                cajaBuscarObraSocial.setText(cajaNombreObraSocial.getText());
                buscarObraSocial(event);
                mensaje("Obra social creada con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }else{
                servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } catch (Exception e) {
            mensaje("Error al crear obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
        
        
        
        
    }
    
    @FXML
    private void agregarPlanObraSocia(MouseEvent event) {
        if(!cajaNombreObraSocial.getText().isBlank()){
            cajaAgregarPlanObraSocial.setVisible(true);
            botonActualizarPlanesObraSocial.setVisible(true);
            botonAgregarPlanesObraSocial.setDisable(true);
            labelAgregarPlan.setVisible(true);
        }else{
            servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
            mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }
    
    @FXML
    private void actualizarPlanesObrasSocial(MouseEvent event) {

        try {
            if (!cajaNombreObraSocial.getText().isBlank()) {
                obraSocialDao = new ObraSocialDAOImplementacion();
                ObraSocial obraSocial = new ObraSocial();
                obraSocial.setNombre(cajaBuscarObraSocial.getText());
                obraSocial.setPlan(cajaAgregarPlanObraSocial.getText());
                obraSocialDao.agregarPlan(obraSocial);
                cajaAgregarPlanObraSocial.setText("");
                cajaAgregarPlanObraSocial.setVisible(false);
                botonActualizarPlanesObraSocial.setVisible(false);
                botonAgregarPlanesObraSocial.setDisable(false);
                labelAgregarPlan.setVisible(false);
                buscarObraSocial(event);
                inicializarTableObraSocial();
            } else {
                servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } catch (Exception e) {
            mensaje("Error al crear plan", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

    }

    //                              ****
    //                              ****
    //                              ****
    //   ACTUALIZAR OBRA SOCIAL  
    //                              ****
    //                              ****
    //                              ****
    
    @FXML
    private void actualizarObraSocial(MouseEvent event) {
        try {
            //SI NO ESTAN LAS CAJAS DESHABILITADAS
            if (botonActualizarObraSocial.getId().equals("1")) {
                //SI SE BUSCO LA OBRA SOCIAL
                if (!cajaBuscarObraSocial.getText().isBlank()) {
                    //SI NO HAY CAMPOS IMPORTANTES VACIOS
                    if(!cajaNombreObraSocial.getText().isBlank()){
                        ObraSocial obraSocial = new ObraSocial(
                                cajaNombreObraSocial.getText(),
                                new Telefono(cajaTelefonoObraSocial.getText()),
                                new Web(cajaWebObraSocial.getText()),
                                new Email(cajaEmailObraSocial.getText()));
                        if(!cajaEmailObraSocial.getText().isEmpty()){
                            
                            obraSocial.getEmail().setId(obraSocialDao.obtenerIdEmail(new Email(VariablesEstaticas.valoresBUsquedaObraSocial.get("3"))));
                        }
                        obraSocial.setId(obraSocialDao.obtenerIdObraSocial(new ObraSocial(cajaBuscarObraSocial.getText())));
                        //SI SE INICIALIZAO OBRA SOCIAL
                        if (Objects.nonNull(obraSocial)) {
                            obraSocialDao.actualizar(obraSocial, 0);
                            botonActualizarObraSocial.setId("botonActualizarObraSocial");
                            cajaBuscarObraSocial.setText(cajaNombreObraSocial.getText());
                            buscarObraSocial(event);
                            inicializarTableObraSocial();
                            servicioObraSocial.
                                    deshabilitarCajas(VariablesEstaticas.cajasObrasSociales);
                            
                            mensaje("Obra social actualizada con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                            
                        } else {
                            mensaje("Error al actualizar obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        }
                    }else{
                        mensaje("Hay campos importantes vacios", this, "/com/pacientes/gestor_pacientes/img/error.png");
                        servicioPaciente.
                            pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                    }

                }else{
                    mensaje("Buscar obra social para actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
            } else {
                botonActualizarObraSocial.setId("1");
                servicioObraSocial.
                        habilitarCajas(VariablesEstaticas.cajasObrasSociales).
                        animarCajasAlDarABoton(VariablesEstaticas.cajasObrasSociales);
            }
        } catch (Exception e) {
            mensaje("Error al actualizar obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
    }
    
    
    
    @FXML
    private void actualizarOcrear(MouseEvent event) {

        
            Button boton = (Button) event.getSource();
            switch (boton.getId()) {
                case "botonAgregarCodigoFacturacion":
                    botonActualizarAgregarCodigoFacturacion.setOnMouseClicked(this::agregarCodigoFacturacion);
                    
                    break;
                case "botonActualizarCodigoFacturacion":
                    botonActualizarAgregarCodigoFacturacion.setOnMouseClicked(this::actualizarCodigoFacturacion);
                    break;
            }
            
        if (Objects.isNull(choiseCodigoFactSesionObraSocial.getValue()) && boton.getId().equals("botonActualizarCodigoFacturacion")) {
            mensaje("Ingresar datos para Agregar nuevo código", this, "/com/pacientes/gestor_pacientes/img/error.png");
        } else {
            cajaAtualizarNombreCodigoFacturacionSesionObraSocial.setText(choiseCodigoFactSesionObraSocial.getValue());
            cajaAtualizarCodigoFacturacionSesionObraSocial.setText(cajaCodigoFacturacion.getText());
            botonActualizarCodigoFacturacion.setDisable(true);
            botonAgregarCodigoFacturacion.setDisable(true);
            hboxCajasCodigosFacturacion.setVisible(true);
            etiquetaActualizarCodigoFacturacion.setVisible(true);
            hboxEtiquetasCodigosFacturacion.setVisible(true);
            botonEliminarSesiones.setDisable(true);
            botonActualizarSesiones.setDisable(true);
            botonAgregarSesiones.setDisable(true);
        }
        
    }
    
    @FXML
    private void agregarCodigoFacturacion(MouseEvent event) {

        try {

            if (!cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText().isBlank() || !cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText().isBlank()) {
                CodigoFacturacion codigoFacturacion = new CodigoFacturacion(cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText(), Integer.parseInt(cajaAtualizarCodigoFacturacionSesionObraSocial.getText()));
                pacienteDao.insertarCodigoFacturacion(codigoFacturacion);
                choiseCodigoFactSesionObraSocial.getItems().clear();
                iniciarChoiseList();
                mensaje("Código agregado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }else{
                mensaje("inngresar datos para agregar", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }

        } catch (Exception e) {
            mensaje("Error al agregar código", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

        botonActualizarCodigoFacturacion.setDisable(false);
        botonAgregarCodigoFacturacion.setDisable(false);
        hboxCajasCodigosFacturacion.setVisible(false);
        etiquetaActualizarCodigoFacturacion.setVisible(false);
        hboxEtiquetasCodigosFacturacion.setVisible(false);
        botonEliminarSesiones.setDisable(false);
        botonActualizarSesiones.setDisable(false);
        botonAgregarSesiones.setDisable(false);
    }
    
    
    @FXML
    private void actualizarCodigoFacturacion(MouseEvent event) {

        try {
            if (!cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText().isBlank() || !cajaAtualizarCodigoFacturacionSesionObraSocial.getText().isBlank()) {
            
            CodigoFacturacion codigoFacturacion = new CodigoFacturacion(choiseCodigoFactSesionObraSocial.getValue(), Integer.parseInt(cajaCodigoFacturacion.getText()));
            codigoFacturacion.setId(pacienteDao.obtenerIdCodigoFacturacion(codigoFacturacion));
            codigoFacturacion.setNombre(cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText());
            codigoFacturacion.setCodigo(Integer.parseInt(cajaAtualizarCodigoFacturacionSesionObraSocial.getText()));
            pacienteDao.actualizarCodigoFacturacion(codigoFacturacion);
            choiseCodigoFactSesionObraSocial.getItems().clear();
            iniciarChoiseList();
            mensaje("Código actualizado con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }else{
                mensaje("inngresar datos para actualizar", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } catch (Exception e) {
            mensaje("Error al actualizar código", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }

        botonAgregarCodigoFacturacion.setDisable(false);
        botonActualizarCodigoFacturacion.setDisable(false);
        hboxCajasCodigosFacturacion.setVisible(false);
        etiquetaActualizarCodigoFacturacion.setVisible(false);
        hboxEtiquetasCodigosFacturacion.setVisible(false);
        cajaCodigoFacturacion.setText("");
        botonEliminarSesiones.setDisable(false);
        botonActualizarSesiones.setDisable(false);
        botonAgregarSesiones.setDisable(false);
    }
    
    
    
    
    @FXML
    private void eliminarObraSocial(MouseEvent event){
        try {
            if(!cajaBuscarObraSocial.getText().isBlank()){
                ObraSocial obraSocial = new ObraSocial();
                obraSocial.setNombre(cajaBuscarObraSocial.getText());
                obraSocial.setId(obraSocialDao.obtenerIdObraSocial(obraSocial));
                if(Objects.nonNull(obraSocial.getId())){
                    obraSocialDao.eliminar(obraSocial, 0);
                    buscarObraSocial(event);
                    inicializarTableObraSocial();
                    mensaje("Obra social eliminada con éxito", this, "/com/pacientes/gestor_pacientes/img/error.png");
                    servicioObraSocial.
                            habilitarCajas(cajasObrasSociales).
                            habilitarBotonCrear(botonAgregarObraSocial).
                            desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                            vaciarCajas(VariablesEstaticas.cajasObrasSociales);
                    cajaBuscarObraSocial.setText("");
                    botonAgregarPlanesObraSocial.setDisable(false);
                }else{
                    mensaje("Buscar obra social para eliminar", this, "/com/pacientes/gestor_pacientes/img/error.png");
                }
                
            }else{
                mensaje("Buscar obra social para eliminar", this, "/com/pacientes/gestor_pacientes/img/error.png");
            }
        } catch (Exception e) {
            mensaje("Error al eliminar obra social", this, "/com/pacientes/gestor_pacientes/img/error.png");
        }
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
    public void administraAccion(MouseEvent event) {
        
        try {
            ImageView b = (ImageView) event.getSource();
            FXMLLoader Loader = new FXMLLoader(App.class.getResource("AdministrarAccionAgenda.fxml"));
            Parent root = Loader.load();
            AgendaController controller = Loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
           
            controller.iniciarAdministrarAccion(stage, root, b);
            
            stage.showAndWait();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    
        
        
        

    }
    
    
    @FXML
    private void cambiarAMesSuperior(MouseEvent event) {
        AgendaController agenda = new AgendaController();
        agenda.rellenarAgenda(2);
    }
    
    @FXML
    private void cambiarAMesInferior(MouseEvent event){
        AgendaController agenda = new AgendaController();
        agenda.rellenarAgenda(3);
    }
    
    

    

        
      
        
        
       

    
}
