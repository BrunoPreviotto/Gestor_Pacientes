/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

// PROYECTO
import com.pacientes.gestor_pacientes.controlador.Paciente.PacienteController;
import com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial.ObraSocialDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.*;
import com.pacientes.gestor_pacientes.modelo.*;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.*;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import com.pacientes.gestor_pacientes.utilidades.TablaSesiones;

// EXTERNAS
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.controlador.Paciente.DatosPrincipalesController;
import com.pacientes.gestor_pacientes.controlador.Paciente.DiagnosticoController;

import com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial.PlanObraSocialDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.AfiliadoDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.AutorizacionDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.CodigoFacturacionDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.DatosPrincipalesDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.DiagnosticoDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.FrecuenciaSesionPlanDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.ObraSocialPacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.PlanTratamientoDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.SesionDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.TipoSesionPlanDAOImplementacion;
import com.pacientes.gestor_pacientes.servicios.ClienteActualizacion;
import com.pacientes.gestor_pacientes.servicios.GestorMail;
import com.pacientes.gestor_pacientes.servicios.GitHubUpdateManager;
import com.pacientes.gestor_pacientes.servicios.GoogleDriveService;
import com.pacientes.gestor_pacientes.servicios.ServicioMenuInicio;
import com.pacientes.gestor_pacientes.servicios.ServicioOpciones;
import com.pacientes.gestor_pacientes.servicios.ServicioPlanesPaciente;
import com.pacientes.gestor_pacientes.utilidades.Directorios;
import com.pacientes.gestor_pacientes.utilidades.Exepciones;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import com.pacientes.gestor_pacientes.utilidades.WebUtilidades;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.stage.DirectoryChooser;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apache.maven.shared.utils.Os;
import org.json.JSONObject;

//import org.jsoup.Jsoup;
/**
 * FXML Controller class
 *
 * @author previotto
 */
public class MenuInicioController extends PacienteController implements Initializable, DraggedScene {

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
    public HBox obtenrhb() {
        return hbTablasSesionesAtorizaciones;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServicioMenuInicio servicio = new ServicioMenuInicio();

        iniciarChoiceOpciones();
        iniciarColorContenedores();

        usuarioDao = new UsuarioDAOImplementacion();
        usuario = usuarioDao.obtenerUsuarioActual();

        iniciarVariablesEstaticas();

        this.onDraggedScene(containerMenu);

        rellenarDatosUsuario();

        iniciarChoiceFrecuencia();
        iniciarChoiceObraSocial();

        iniciarChoiceTipoSesion();
        inicializarTableObraSocial();
        iniciarChoicePlanObraSocialPaciente();

        comprobarFechaAlIniciar();

       choiseTipoSesionPlan.setOnAction(this::cambiarCategoria);

       choiseNombreObraSocialPaciente.setOnAction(this::cambiarPlanesObraSocial);

        servicioPaciente.
                deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar).
                deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar);

        iniciarlizarHtmlEditorMenu();

     
        servicio.consultarEstadoActualizacion();

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
        blanquearCajas();
        try {

            Paciente pacienteBuscar = new Paciente();
            if (Objects.nonNull(cajaBuscarPaciente.getText())) {
                if (!cajaBuscarPaciente.getText().isEmpty()) {
                    //BUSCAR ID PACIENTE BUSCADO
                    daoImplementacion = new PacienteDAOImplementacion();
                    pacienteBuscar.setId(daoImplementacion.obtenerId(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))));
                    //SI PACIENTE EXISTE
                    if (pacienteBuscar.getId() != 0) {
                        VariablesEstaticas.paciente = new Paciente();
                        VariablesEstaticas.paciente.setId(pacienteBuscar.getId());
                        Paciente pacienteResultado;
                        try {
                            pacienteResultado = (Paciente) daoImplementacion.obtener(pacienteBuscar);
                        } catch (Exception e) {
                            pacienteResultado = null;
                        }

                        //PACIENTE NO ESTA VACIO
                        if (Objects.nonNull(pacienteResultado)) {

                            //SI DATOS PRINCIPALES EXISTEN
                            buscarDatosPrincipales(pacienteResultado);

                            //SI SESIONES EXISTEN
                            buscarSesiones(pacienteResultado);

                            //SI PLANES DE TRATAMIENTOS EXISTEN
                            buscarPLanes();

                            //SI DIAGNOSTICO EXISTE
                            buscarDiagnostico(pacienteResultado);

                            //SI OBRA SOCIAL PACIENTE EXISTE
                            buscarObraSocial(pacienteResultado);

                            servicioPaciente.
                                    comprobarSiAcordeonEstaCerrado(listaContenedoresAcordeon);

                        } else {
                            cajaBuscarPaciente.setText("");
                            pacienteNoEncontradoBuscar();
                        }

                    } else {
                        cajaBuscarPaciente.setText("");
                        pacienteNoEncontradoBuscar();
                    }

                } else {
                    cajaBuscarPaciente.setText("");
                    pacienteNoEncontradoBuscar();

                }
            } else {
                pacienteNoEncontradoBuscar();
                cajaBuscarPaciente.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            cajaBuscarPaciente.setText(null);
            mensajeAdvertenciaError("Error al buscar paciente", this, VariablesEstaticas.imgenError);
            //vBoxSesiones.setVisible(false);
            //vBoxAutorizacion.setVisible(false);
            hbTablasSesionesAtorizaciones.setVisible(true);
            servicioPaciente.
                    vaciarListas().
                    vaciarTodo().
                    habilitarTodo().
                    visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                    ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                    visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                    ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                    habilitarBotones(VariablesEstaticas.listaBotonesCrear).
                    deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar).
                    deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar);
            buscarPLanes();
        }

        choiseNombreObraSocialPaciente.getItems().clear();
        choiseNombreObraSocialPaciente.setValue("");
        choisePlanesObraSocialPacientePlan.getItems().clear();
        choiseTipoSesionPlan.getItems().clear();
        choiseFrecuenciaSesionPlan.getItems().clear();

        iniciarChoiceFrecuencia();
        iniciarChoiceObraSocial();
        iniciarChoiceTipoSesion();
        inicializarTableObraSocial();
        iniciarChoicePlanObraSocialPaciente();
        botonActualizarObraSocialPaciente.setId("0");

    }

    public void pacienteNoEncontradoBuscar() {
        //vBoxSesiones.setVisible(false);
        //vBoxAutorizacion.setVisible(false);
        hbTablasSesionesAtorizaciones.setVisible(true);
        mensajeAdvertenciaError("Paciente no encontrado", this, VariablesEstaticas.imgenAdvertencia);
        super.setearBotones();
        servicioPaciente.
                vaciarListas().
                vaciarTodo().
                habilitarTodo().
                visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                habilitarBotones(VariablesEstaticas.listaBotonesCrear).
                deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar).
                deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar).
                desPintarCajaVaciaImportante(VariablesEstaticas.cajasDatosPrincipales);
        buscarPLanes();
    }

    public void buscarDatosPrincipales(Paciente pacienteResultado) {

        if (Objects.nonNull(pacienteResultado.getDni())) {
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
                    habilitarEliminarActualizar(botonEliminarDatosPrincipales, botonActualizarDatosPrincipales);

        } else {

            servicioPaciente.
                    vaciarListaDatosPrincipales().
                    habilitarBotonCrear(botonAgregarDatosPrincipales).
                    desHabilitarEliminarActualizar(botonEliminarDatosPrincipales, botonActualizarDatosPrincipales);
        }
    }

    public void buscarSesiones(Paciente pacienteResultado) {
        VariablesEstaticas.mapObservacionAutorizacionSesionesHTML.clear();
        VariablesEstaticas.mapObservacionSesionesHTML.clear();
        VariablesEstaticas.mapTrabajoSesionesHTML.clear();
        if (Objects.nonNull(pacienteResultado.getSesiones())) {
            ObservableList<TablaSesiones> olSesiones = FXCollections.observableArrayList();
            for (SesionPaciente sp : pacienteResultado.getSesiones()) {

                
                
                olSesiones.add(new TablaSesiones(
                        String.valueOf(sp.getIdSesion()),
                       String.valueOf(sp.getNumeroSesion()),
                        sp.getFecha().toString(),
                        WebUtilidades.deHTMLAString(sp.getTrabajoSesion()),
                         WebUtilidades.deHTMLAString(sp.getObservacion()),
                        String.valueOf(sp.getHonorarioPorSesion()),
                        sp.getEstado().getEstado(),
                        String.valueOf(sp.getAutorizacion().getId()),
                        String.valueOf(sp.getAutorizacion().getNumeroAutorizacion()),
                        sp.getAutorizacion().getObservacion(),
                        sp.getAutorizacion().getAsociacion().toString(),
                        String.valueOf(sp.getAutorizacion().getCopago()),
                        sp.getAutorizacion().getCodigoFacturacion().getNombre()));

            }

            tablaAutorizacion.setItems(olSesiones);
            tableSesiones.setItems(olSesiones);
            ColumnaSesionId.setCellValueFactory(new PropertyValueFactory<>("idSesion"));
            ColumnaSesionNumero.setCellValueFactory(new PropertyValueFactory<>("numeroSesion"));
            ColumnaSesionFecha.setCellValueFactory(new PropertyValueFactory<>("fechaSesion"));
            ColumnaSesionTrabajo.setCellValueFactory(new PropertyValueFactory<>("trabajoSesion"));
            ColumnaSesionObservacion.setCellValueFactory(new PropertyValueFactory<>("observacionSesion"));
            ColumnaSesionHonorarioPorSesion.setCellValueFactory(new PropertyValueFactory<>("honorariosPorSesion"));
            ColumnaSesionEstadoFacturacion.setCellValueFactory(new PropertyValueFactory<>("estadoFacturacion"));

            columnaIdAutorizacion.setCellValueFactory(new PropertyValueFactory<>("idAutorizacion"));
            columnaAutorizacionNumero.setCellValueFactory(new PropertyValueFactory<>("numeroAutorizacion"));
            columnaAutorizacionObservacion.setCellValueFactory(new PropertyValueFactory<>("observacionAutorizacion"));
            columnaAutorizacionAsociacion.setCellValueFactory(new PropertyValueFactory<>("asociacion"));
            columnaAutorizacionCopago.setCellValueFactory(new PropertyValueFactory<>("copago"));
            columnaAutorizacionCodigoFacturacion.setCellValueFactory(new PropertyValueFactory<>("nombreCodigo"));
            columnaNumeroSecionAutorizacion.setCellValueFactory(new PropertyValueFactory<>("numeroSesion"));

        } else {
            //servicioPaciente.vaciarListaSesiones();
        }
        botonRetornarSesiones.setDisable(true);

    }

    public void buscarPLanes() {

        PlanTratamientoDAOImplementacion planDAO = new PlanTratamientoDAOImplementacion();

        PlanTratamiento plan = new PlanTratamiento();

        try {
            plan = planDAO.obtener(new PlanTratamiento());
        } catch (Exception e) {
            e.printStackTrace();
        }

       

        if (Objects.nonNull(plan)) {
            hboxBotoneraFrecuenciaPLanTratamiento.setVisible(false);
            hboxBotoneraFrecuenciaPLanTratamiento.setVisible(false);
            choiseFrecuenciaSesionPlan.setValue(plan.getFrecuenciaSesion().getFrecuencia());
            choiseFrecuenciaSesionPlan.getSelectionModel().select(0);
            cajaPlanFrecuenciaSesiones.setText(plan.getFrecuenciaSesion().getFrecuencia());
            cajaDescripcionTipoSesionPlan.setText(plan.getTipoSEsion().getDecripcion());
            cajaEstrategiaPlan.setText(plan.getEstrategia());
            choiseTipoSesionPlan.setValue(plan.getTipoSEsion().getNombre());
            choiseTipoSesionPlan.getSelectionModel().select(0);
            cajaNombreTipoSesionPlan.setText(plan.getTipoSEsion().getNombre());
            
            servicioPaciente.
                    rellenarListaPlan(plan).
                    visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                    ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                    deshabilitarCajas(VariablesEstaticas.cajasPlanes);
                    botonEliminarPlanTratamiento.setDisable(false);

            

        } else {
            hboxBotoneraFrecuenciaPLanTratamiento.setVisible(true);
            hboxBotoneraFrecuenciaPLanTratamiento.setVisible(true);
            servicioPaciente.
                    vaciarListaPlan().
                    visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                    ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                    habilitarCajas(VariablesEstaticas.cajasPlanes).
                    vaciarCajas(VariablesEstaticas.cajasPlanes);
                    botonEliminarPlanTratamiento.setDisable(true);

            vBoxFrecuenciaSEsionPlanActualizaroVer.setVisible(false);

            cajaPlanFrecuenciaSesiones.setText("");
            choiseFrecuenciaSesionPlan.setFocusTraversable(true);
            choiseFrecuenciaSesionPlan.setMouseTransparent(false);

            cajaNombreTipoSesionPlan.setText("");
            cajaDescripcionTipoSesionPlan.setDisable(true);

            vBoxNombreTipoSEsionPlanActualizaroVer.setVisible(false);

            choiseTipoSesionPlan.setFocusTraversable(true);
            choiseTipoSesionPlan.setMouseTransparent(false);
            

        }

    }

    public void buscarObraSocial(Paciente pacienteResultado) {
        if (Objects.nonNull(pacienteResultado.getObraSocialPaciente())) {

            cajaNombreObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getNombre());
            choiseNombreObraSocialPaciente.setValue(pacienteResultado.getObraSocialPaciente().getNombre());
            choisePlanesObraSocialPacientePlan.setValue(pacienteResultado.getObraSocialPaciente().getPlan().getNombre());
            cajaPlanObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getPlan().getNombre());
            cajaNAfiliadoObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getAfiliado().getNumero().toString());

            servicioPaciente.
                    rellenarListaObrasocialPaciente(pacienteResultado).
                    visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                    ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                    deshabilitarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                    desHabilitarBotonCrear(botonAgregarObraSocialPaciente).
                    habilitarEliminarActualizar(botonEliminarObraSocialPaciente, botonActualizarObraSocialPaciente);;

        } else {
            servicioPaciente.
                    vaciarListaObrasocialPaciente().
                    visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                    ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                    habilitarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                    vaciarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                    vaciarValorChoise(VariablesEstaticas.choiseObraSocialPaciente).
                    habilitarBotonCrear(botonAgregarObraSocialPaciente).
                    desHabilitarEliminarActualizar(botonEliminarObraSocialPaciente, botonActualizarObraSocialPaciente);
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
        daoImplementacion = new DatosPrincipalesDAOImplementacion();
        try {
            List<TextField> listaCajas = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));
            //SI LAS CAJAS IMPORTANTES NO ESTAN VACIAS
            if (cajaNombreDatosPrincipales.getText().isBlank() || cajaApellidoDatosPrincipales.getText().isBlank() || cajaDniDatosPrincipales.getText().isBlank()) {
                //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                servicioPaciente.pintarCajaVaciaImportante(VariablesEstaticas.cajasDatosPrincipales);
            } else {
                //SI EXISTEN DATOS VACIOS NO IMPORTANTES LOS RELLENA CON VALORES POR DEFECTO
                servicioPaciente.datosPrincipalesVacios();
                //INSERTAR PACIENTE
                daoImplementacion.insertar(
                        new Paciente(
                                cajaNombreDatosPrincipales.getText(),
                                cajaApellidoDatosPrincipales.getText(),
                                Integer.parseInt(cajaEdadDatosPrincipales.getText()),
                                Integer.parseInt(cajaDniDatosPrincipales.getText()),
                                new Honorario(Double.parseDouble(cajaHonorariosDatosPrincipales.getText())),
                                new Telefono(cajaTelefonoDatosPrincipales.getText())));
                //SETEAR CAJA BUSCAR CON EL PACIENTE CREADO
                cajaBuscarPaciente.setText(cajaDniDatosPrincipales.getText());
                buscarPaciente();
                //INFORMAR DE CREACION CON EXITO
                mensajeAdvertenciaError("Paciente creado con èxito", this, VariablesEstaticas.imgenExito);

            }

        } catch (Exception e) {
            cajaBuscarPaciente.setText("");
            mensajeAdvertenciaError(e.getMessage(), this, VariablesEstaticas.imgenError);
            cajaNombreDatosPrincipales.getStyleClass().add("cajasARellenar");
            e.printStackTrace();
        }
    }

    @FXML
    public void crearSesion(MouseEvent event) {

        if (!cajaBuscarPaciente.getText().isBlank()) {
            VariablesEstaticas.actualizarOCrearSesion = 1;
            buscarPaciente();
            iniciarFXMLSesiones(1);
        } else {
            mensajeAdvertenciaError("Buscar paciente para poder crear una sesión.", this, VariablesEstaticas.imgenAdvertencia);
        }
    }

    @FXML
    private void actualizarSesion(MouseEvent event) {
        if (tableSesiones.getSelectionModel().isEmpty()) {
            VariablesEstaticas.actualizarOCrearSesion = 2;
            mensajeAdvertenciaError("Seleccione sesión para pode actualizar.", this, VariablesEstaticas.imgenAdvertencia);
        } else {
            iniciarFXMLSesiones(2);
        }

        if (!cajaBuscarPaciente.getText().isBlank()) {
            buscarPaciente();
        }
    }

    public SesionPaciente rellenarSesionSeleccionadaEnTabla() {
        //SESION
        ObservableList<?> selectedItems = tableSesiones.getSelectionModel().getSelectedItems();

      
        
        if (!selectedItems.isEmpty()) {

            
            
            return new SesionPaciente(
                    
                    Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getIdSesion()),
                    Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion()),
                    LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion()),
                    tableSesiones.getSelectionModel().getSelectedItem().getTrabajoSesion(),
                    tableSesiones.getSelectionModel().getSelectedItem().getObservacionSesion(),
                    Double.parseDouble(tableSesiones.getSelectionModel().getSelectedItem().getHonorariosPorSesion()),
                    new AutorizacionesSesionesObraSociales(
                            Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getIdAutorizacion()),
                            Long.parseLong(tableSesiones.getSelectionModel().getSelectedItem().getNumeroAutorizacion()),
                            tableSesiones.getSelectionModel().getSelectedItem().getObservacionAutorizacion(),
                            LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion()),
                            Double.parseDouble(tableSesiones.getSelectionModel().getSelectedItem().getCopago()),
                            new CodigoFacturacion(tableSesiones.getSelectionModel().getSelectedItem().getNombreCodigo()),
                            0,
                            0
                    ),
                    new EstadoFacturacion(tableSesiones.getSelectionModel().getSelectedItem().getEstadoFacturacion())
            );
        }

        return new SesionPaciente();

    }

    public void iniciarFXMLSesiones(int num) {
        try {

            FXMLLoader Loader = new FXMLLoader(App.class.getResource("Sesiones.fxml"));
            Parent root = Loader.load();
            SesionesController controller = Loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);

            controller.setSesioneSeleccionada(rellenarSesionSeleccionadaEnTabla());

            controller.setCajaBuscarPaciente(cajaBuscarPaciente.getText());

            if (num == 2) {
                controller.rellenarCajasSesionesParaActualizar();
            }

            stage.showAndWait();

            buscarPaciente();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void crearDiagnostico(MouseEvent event) {
        DiagnosticoController d = new DiagnosticoController();
        try {
            //daoImplementacion = new PacienteDAOImplementacion();
            //d.crear(cajaBuscarPaciente.getText().isBlank(), new DiagnosticoPaciente(htmlDiagnostico.getHtmlText(), htmlObservacionDiagnostico.getHtmlText(), daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())))));
            buscarPaciente();
        } catch (Exception e) {

        }

        /* try {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isBlank()) {
                daoImplementacion = new PacienteDAOImplementacion();
                DiagnosticoPaciente diagnostico = new DiagnosticoPaciente(htmlDiagnostico.getHtmlText(), htmlObservacionDiagnostico.getHtmlText(), daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                //SI DATOS IMPORTANTES TIENEN TEXTO
                if (htmlDiagnostico.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") 
                        || htmlDiagnostico.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
                    servicioPaciente.pintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaDiagnostico);
                    mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                } else {
                    mensajeAdvertenciaError("Diagnostico creado con èxito", this, VariablesEstaticas.imgenExito);
                    servicioPaciente.datosDiagnosticoVacios();
                    daoImplementacion = new DiagnosticoDAOImplementacion();
                    daoImplementacion.insertar(diagnostico);
                    buscarPaciente();
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para crear Diagnóstico", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            mensajeAdvertenciaError("Error al crear diagnóstico", this, VariablesEstaticas.imgenError);
        }*/
    }

    @FXML
    private void crearObraSocialPaciente(MouseEvent event) {

        try {
            //SI SE BUSCO PACIENTE
            if (!cajaBuscarPaciente.getText().isBlank()) {
                //SI CAJAS IMPORTANTES TIENEN VALORES
                if (Objects.isNull(choisePlanesObraSocialPacientePlan.getValue()) || cajaNAfiliadoObraSocialPaciente.getText().isBlank()) {
                    servicioPaciente.pintarChoiseVacioImportante(VariablesEstaticas.choiseObraSocialPaciente);
                    servicioPaciente.pintarCajaVaciaImportante(VariablesEstaticas.cajasObraSocialPaciente);
                    mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                } else {
                    ObraSocialPaciente obraSocialPaciente = new ObraSocialPaciente(new Afiliado(Long.parseLong(cajaNAfiliadoObraSocialPaciente.getText())), choiseNombreObraSocialPaciente.getValue().toString(), new PlanObraSocial(choisePlanesObraSocialPacientePlan.getValue().toString(), "Sin descripcion"));
                    daoImplementacion = new PacienteDAOImplementacion();
                    obraSocialPaciente.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));

                    daoImplementacion = new ObraSocialPacienteDAOImplementacion();
                    daoImplementacion.insertar(obraSocialPaciente);
                    mensajeAdvertenciaError("Obra Social del Paciente creado con èxito", this, VariablesEstaticas.imgenExito);

                    buscarPaciente();
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para crear Obra Social del paciente", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            if (e.getMessage().equals("afiliado existente")) {
                mensajeAdvertenciaError(e.getMessage(), this, VariablesEstaticas.imgenError);
            } else {
                mensajeAdvertenciaError("Error al crear obra social del paciente", this, VariablesEstaticas.imgenError);
            }
            
            e.printStackTrace();

        }
    }

    @FXML
    private void actualizarCrearTipoSesionPlaPlan(MouseEvent event) {
        Button boton = (Button) event.getSource();

        if (boton.getId().equals("botonAgregarPlanTipoSesion")) {

            habilitarElementosPlanesTipoSesion();
            botonActualizarCrearTipoSesion.setOnMouseClicked(this::insertarTipoPlan);

        } else if (boton.getId().equals("botonActualizarPlanTipoSesion")) {
            if (Objects.nonNull(choiseTipoSesionPlan.getValue())) {

                habilitarElementosPlanesTipoSesion();
                botonActualizarCrearTipoSesion.setOnMouseClicked(this::actualizarTipoPlan);
                cajaNombreTipoSesionPlan.setText(choiseTipoSesionPlan.getValue());

            } else {
                mensajeAdvertenciaError("Seleccionar tipo sesion para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }

        } else if (boton.getId().equals("botonEliminarTipoSesionPlan")) {
            
            cajaNombreTipoSesionPlan.setText(choiseTipoSesionPlan.getValue());
            eliminarTipoSesionPlan();
        }
    }

    public void eliminarTipoSesionPlan() {
        
        
        try {
           
            TipoSesion tipo = new TipoSesion();
            if (!cajaNombreTipoSesionPlan.getText().isBlank()) {
               
                tipo.setNombre(cajaNombreTipoSesionPlan.getText());
                tipo.setDecripcion(cajaDescripcionTipoSesionPlan.getText());
                
                daoImplementacion = new TipoSesionPlanDAOImplementacion();
               
                daoImplementacion.eliminar(tipo);

                deshabiltarElementosPlanesTipoSesion();

                choiseTipoSesionPlan.getItems().clear();
                choiseTipoSesionPlan.setValue("");

                iniciarChoiceTipoSesion();
                mensajeAdvertenciaError("Tipo sesión eliminada con exito", this, VariablesEstaticas.imgenExito);
            } else {
                mensajeAdvertenciaError("Seleccionar tipo sesión para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al eliminar tipo sesion. Quizás esta asociada a un paciente.", this, VariablesEstaticas.imgenError);
            e.printStackTrace();
            deshabiltarElementosPlanesTipoSesion();
        }
    }

    public void deshabiltarElementosPlanesTipoSesion() {
        cajaNombreTipoSesionPlan.setText("");

        cajaDescripcionTipoSesionPlan.setDisable(true);

        vBoxNombreTipoSEsionPlanActualizaroVer.setVisible(false);
        botonAgregarPlanTipoSesion.setDisable(false);
        botonActualizarPlanTipoSesion.setDisable(false);
        botonEliminarTipoSesionPlan.setDisable(false);
        botonActualizarPlanTipoSesion.setDisable(false);

        choiseTipoSesionPlan.setFocusTraversable(true);
        choiseTipoSesionPlan.setMouseTransparent(false);
    }

    public void habilitarElementosPlanesTipoSesion() {

        vBoxNombreTipoSEsionPlanActualizaroVer.setVisible(true);
        botonActualizarPlanTipoSesion.setDisable(true);
        botonAgregarPlanTipoSesion.setDisable(true);
        botonEliminarTipoSesionPlan.setDisable(true);
        choiseTipoSesionPlan.setFocusTraversable(false);
        choiseTipoSesionPlan.setMouseTransparent(true);
        cajaDescripcionTipoSesionPlan.setDisable(false);

    }

    @FXML
    private void insertarTipoPlan(MouseEvent event) {
        daoImplementacion = new TipoSesionPlanDAOImplementacion();
        try {
            if (!cajaNombreTipoSesionPlan.getText().isBlank()) {
                TipoSesion tipo = new TipoSesion();
                tipo.setNombre(cajaNombreTipoSesionPlan.getText());
                tipo.setDecripcion(cajaDescripcionTipoSesionPlan.getText());
                daoImplementacion.insertar(tipo);

                choiseTipoSesionPlan.getItems().clear();

                iniciarChoiceTipoSesion();

                deshabiltarElementosPlanesTipoSesion();

                mensajeAdvertenciaError("Tipo sesión creada con exito", this, VariablesEstaticas.imgenExito);
            } else {
                mensajeAdvertenciaError("Ingresar tipo sesión", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            mensajeAdvertenciaError("Error al crear tipo sesión", this, VariablesEstaticas.imgenError);
            deshabiltarElementosPlanesTipoSesion();
        }

    }

    @FXML
    private void actualizarTipoPlan(MouseEvent event) {
       
         
                
        
        
        try {
            TipoSesion tipo = new TipoSesion();
            if (!choiseTipoSesionPlan.getSelectionModel().isEmpty()) {
                
                daoImplementacion = new TipoSesionPlanDAOImplementacion();
                tipo.setNombre(choiseTipoSesionPlan.getValue());
                tipo.setId(daoImplementacion.obtenerId(tipo));
                tipo.setNombre(cajaNombreTipoSesionPlan.getText());
                tipo.setDecripcion(cajaDescripcionTipoSesionPlan.getText());
             
                daoImplementacion.actualizar(tipo);

                deshabiltarElementosPlanesTipoSesion();

                choiseTipoSesionPlan.getItems().clear();
                choiseTipoSesionPlan.setValue("");

                iniciarChoiceTipoSesion();
                mensajeAdvertenciaError("Tipo sesión actualizada con exito", this, VariablesEstaticas.imgenExito);
            } else {
                mensajeAdvertenciaError("Seleccionar tipo sesión para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al actualizar tipo sesion", this, VariablesEstaticas.imgenError);
            e.printStackTrace();
            deshabiltarElementosPlanesTipoSesion();
        }
    }

    @FXML
    private void actualizarCrearFrecuenciaPlan(MouseEvent event) {
        Button boton = (Button) event.getSource();

        if (boton.getId().equals("botonAgregarPlanFrecuencia")) {

            habilitarElementosPlanes();
            botonActualizarCrearFrecuencia.setOnMouseClicked(this::insertarFrecuenciaPlan);

        } else if (boton.getId().equals("botonActualizarPlanFrecuencia")) {
            if (Objects.nonNull(choiseFrecuenciaSesionPlan.getValue())) {

                habilitarElementosPlanes();
                botonActualizarCrearFrecuencia.setOnMouseClicked(this::actualizarFrecuenciaPlan);
                cajaPlanFrecuenciaSesiones.setText(choiseFrecuenciaSesionPlan.getValue());

            } else {
                mensajeAdvertenciaError("Seleccionar frecuencia para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }

        } else if (boton.getId().equals("botonEliminarPLanFrecuencia")) {

            cajaPlanFrecuenciaSesiones.setText(choiseFrecuenciaSesionPlan.getValue());
            eliminarFrecuenciaPlan();

        }
    }

    public void eliminarFrecuenciaPlan() {
        daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();

        try {
            if (!cajaPlanFrecuenciaSesiones.getText().isBlank()) {

                FrecuenciaSesion frecuencia = new FrecuenciaSesion(cajaPlanFrecuenciaSesiones.getText());

                daoImplementacion.eliminar(frecuencia);

                choiseFrecuenciaSesionPlan.getItems().clear();
                iniciarChoiceFrecuencia();
                deshabiltarElementosPlanes();

                mensajeAdvertenciaError("Frecuencia eliminada con exito", this, VariablesEstaticas.imgenExito);
            } else {
                mensajeAdvertenciaError("Ingresar frecunecia para agregar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError("Error al crear frecuencia. Quizás esta asociada a un paciente", this, VariablesEstaticas.imgenError);
            deshabiltarElementosPlanes();
        }

    }

    public void insertarFrecuenciaPlan(MouseEvent event) {
        daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();

        try {
            if (!cajaPlanFrecuenciaSesiones.getText().isBlank()) {

                FrecuenciaSesion frecuencia = new FrecuenciaSesion(cajaPlanFrecuenciaSesiones.getText());
                daoImplementacion.insertar(frecuencia);

                choiseFrecuenciaSesionPlan.getItems().clear();
                iniciarChoiceFrecuencia();
                deshabiltarElementosPlanes();

                mensajeAdvertenciaError("Frecuencia creada con exito", this, VariablesEstaticas.imgenExito);
            } else {
                mensajeAdvertenciaError("Ingresar frecunecia para agregar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError("Error al crear frecuencia", this, VariablesEstaticas.imgenError);
            deshabiltarElementosPlanes();
        }

    }

    public void deshabiltarElementosPlanes() {
        vBoxFrecuenciaSEsionPlanActualizaroVer.setVisible(false);
        botonAgregarPlanFrecuencia.setDisable(false);
        botonActualizarPlanFrecuencia.setDisable(false);
        cajaPlanFrecuenciaSesiones.setText("");
        choiseFrecuenciaSesionPlan.setFocusTraversable(true);
        choiseFrecuenciaSesionPlan.setMouseTransparent(false);
        botonEliminarPLanFrecuencia.setDisable(false);
    }

    public void habilitarElementosPlanes() {

        botonAgregarPlanFrecuencia.setDisable(true);
        botonActualizarPlanFrecuencia.setDisable(true);
        vBoxFrecuenciaSEsionPlanActualizaroVer.setVisible(true);
        botonEliminarPLanFrecuencia.setDisable(true);

        choiseFrecuenciaSesionPlan.setFocusTraversable(false);
        choiseFrecuenciaSesionPlan.setMouseTransparent(true);

    }

    private void actualizarFrecuenciaPlan(MouseEvent event) {
        try {
            FrecuenciaSesion frecuenciaSesion = new FrecuenciaSesion();
            if (!cajaPlanFrecuenciaSesiones.getText().isBlank()) {
                frecuenciaSesion.setFrecuencia(choiseFrecuenciaSesionPlan.getValue());
                daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();
                frecuenciaSesion.setIdFrecuencia(daoImplementacion.obtenerId(new FrecuenciaSesion(frecuenciaSesion.getFrecuencia())));
                frecuenciaSesion.setFrecuencia(cajaPlanFrecuenciaSesiones.getText());
                daoImplementacion.actualizar(frecuenciaSesion);

                choiseFrecuenciaSesionPlan.getItems().clear();
                iniciarChoiceFrecuencia();
                deshabiltarElementosPlanes();
                mensajeAdvertenciaError("Frecuencia actualizada con exito", this, VariablesEstaticas.imgenExito);

            } else {
                mensajeAdvertenciaError("Ingresar frecuencia para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError("Error al actualizar frecuencia", this, VariablesEstaticas.imgenError);
            deshabiltarElementosPlanes();
        }

    }

    @FXML
    public void eliminarTipoSesionPlanPlan(MouseEvent event) {

    }

    @FXML
    public void eliminarFrecuenciaPlan(MouseEvent event) {
        try {
            FrecuenciaSesion frecuenciaSesion = new FrecuenciaSesion();
            if (!choiseFrecuenciaSesionPlan.getValue().isBlank()) {

                frecuenciaSesion.setFrecuencia(choiseFrecuenciaSesionPlan.getValue());
                daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();

                frecuenciaSesion.setIdFrecuencia(daoImplementacion.obtenerId(new FrecuenciaSesion(frecuenciaSesion.getFrecuencia())));

                frecuenciaSesion.setFrecuencia(cajaPlanFrecuenciaSesiones.getText());

                daoImplementacion.actualizar(frecuenciaSesion);

                choiseFrecuenciaSesionPlan.getItems().clear();

                iniciarChoiceFrecuencia();
                vBoxFrecuenciaSEsionPlanActualizaroVer.setVisible(false);
                botonAgregarPlanFrecuencia.setDisable(false);
                botonActualizarPlanFrecuencia.setDisable(false);
                cajaPlanFrecuenciaSesiones.setText("");
                choiseFrecuenciaSesionPlan.setFocusTraversable(true);
                choiseFrecuenciaSesionPlan.setMouseTransparent(false);
                mensajeAdvertenciaError("Frecuencia actualizada con exito", this, VariablesEstaticas.imgenExito);

            } else {
                mensajeAdvertenciaError("Ingresar frecuencia para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError("Error al actualizar frecuencia", this, VariablesEstaticas.imgenError);
            vBoxFrecuenciaSEsionPlanActualizaroVer.setVisible(false);
            botonAgregarPlanFrecuencia.setDisable(false);
            botonActualizarPlanFrecuencia.setDisable(false);
            cajaPlanFrecuenciaSesiones.setText("");
            choiseFrecuenciaSesionPlan.setFocusTraversable(true);
            choiseFrecuenciaSesionPlan.setMouseTransparent(false);
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
                daoImplementacion = new PacienteDAOImplementacion();

                List<TextField> listaCajasDatosPrincipales = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));

                if (!cajaBuscarPaciente.getText().isEmpty()) {
                    if (cajaNombreDatosPrincipales.getText().isBlank() || cajaApellidoDatosPrincipales.getText().isBlank() || cajaDniDatosPrincipales.getText().isBlank()) {
                        //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                        mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                        //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                        servicioPaciente.pintarCajaVaciaImportante(VariablesEstaticas.cajasDatosPrincipales);
                    } else {
                        servicioPaciente.datosPrincipalesVacios();
                        daoImplementacion = new DatosPrincipalesDAOImplementacion();

                        Paciente pacienteActualizar = new Paciente(
                                cajaNombreDatosPrincipales.getText(),
                                cajaApellidoDatosPrincipales.getText(),
                                Integer.parseInt(cajaEdadDatosPrincipales.getText()),
                                Integer.parseInt(cajaDniDatosPrincipales.getText()),
                                new Honorario(Double.parseDouble(cajaHonorariosDatosPrincipales.getText())),
                                new Telefono(cajaTelefonoDatosPrincipales.getText())
                        );

                        daoImplementacion.actualizar(pacienteActualizar);

                        servicioPaciente.deshabilitarCajas(VariablesEstaticas.cajasDatosPrincipales);
                        botonActualizarDatosPrincipales.setId("botonAgregarPlanTratamiento");
                        mensajeAdvertenciaError("Paciente actualizado con éxito", this, VariablesEstaticas.imgenExito);
                        cajaBuscarPaciente.setText(cajaDniDatosPrincipales.getText());
                        buscarPaciente();
                    }

                } else {
                    mensajeAdvertenciaError("Buscar paciente a actualizar", this, VariablesEstaticas.imgenAdvertencia);
                }

            } catch (Exception e) {
                if (e.getClass().equals(Exepciones.class)) {
                    mensajeAdvertenciaError(e.getMessage(), this, VariablesEstaticas.imgenError);
                } else {
                    e.printStackTrace();
                    mensajeAdvertenciaError("Error al actualizar Paciente", this, VariablesEstaticas.imgenError);
                    vaciarTodasLasCajas(event);
                    servicioPaciente.deshabilitarCajas(VariablesEstaticas.cajasDatosPrincipales);
                }

            }
        } else {
            botonActualizarDatosPrincipales.setId("1");
            servicioPaciente.
                    animarCajasAlDarABoton(VariablesEstaticas.cajasDatosPrincipales).
                    habilitarCajas(VariablesEstaticas.cajasDatosPrincipales);
            try {
                daoImplementacion = new PacienteDAOImplementacion();
                int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));
                VariablesEstaticas.paciente.
                        setNombre(cajaNombreDatosPrincipales.getText()).
                        setApellido(cajaApellidoDatosPrincipales.getText()).
                        setEdad(Integer.parseInt(cajaEdadDatosPrincipales.getText())).
                        setDni(Integer.parseInt(cajaDniDatosPrincipales.getText())).
                        setId(idPaciente).
                        setTelefono(new Telefono(cajaTelefonoDatosPrincipales.getText())).
                        setHonorarios(new Honorario(Double.parseDouble(cajaHonorariosDatosPrincipales.getText())));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void actualizarDiagnostico(MouseEvent event) {
        //

        //SI CAJAS SON EDITABLES
        if (botonActualizarDiagnostico.getId().equals("1")) {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //SI LA CAJA DIAGNOSTICO NO ESTA VACIA
                if (htmlDiagnostico.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>")
                        || htmlDiagnostico.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.pintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaDiagnostico);
                } else {
                    try {
                        daoImplementacion = new PacienteDAOImplementacion();
                        int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));
                        //PINTAR CAJAS SI ESTAN VACIAS
                        servicioPaciente.datosDiagnosticoVacios();
                        Paciente paciente = new Paciente();
                        //ACTUALIZAR
                        daoImplementacion = new DiagnosticoDAOImplementacion();
                        daoImplementacion.actualizar(new DiagnosticoPaciente(htmlDiagnostico.getHtmlText(), htmlObservacionDiagnostico.getHtmlText(), idPaciente));
                        botonActualizarDiagnostico.setId("botonActualizarDiagnostico");

                        servicioPaciente.deshabilitarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico);

                        mensajeAdvertenciaError("Diagnóstico actualizado con éxito", this, VariablesEstaticas.imgenExito);

                        buscarPaciente();
                    } catch (Exception e) {
                        e.printStackTrace();
                        mensajeAdvertenciaError("Error al actualizar diagnóstico", this, VariablesEstaticas.imgenError);
                    }
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } else {
            botonActualizarDiagnostico.setId("1");
            servicioPaciente.
                    habilitarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico);
        }

    }

    @FXML
    private void actualizarPlanTratamientoPaciente(MouseEvent event) {
        
        //SI CAJAS ESTAN HABILITADAS
        
            //SI SE BUSCO AL PACIENTE
            if (Objects.nonNull(VariablesEstaticas.paciente)) {
                //SI LAS CAJAS IMPORTATES TIENEN VALOR

                try {
                    
                    daoImplementacion = new PacienteDAOImplementacion();
                    int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));
                    servicioPaciente.datosPlanVacios();
                    daoImplementacion = new PlanTratamientoDAOImplementacion();
                    daoImplementacion.
                            actualizar(
                                    new PlanTratamiento(cajaEstrategiaPlan.getText(),
                                            new FrecuenciaSesion(choiseFrecuenciaSesionPlan.getValue()),
                                            new TipoSesion(choiseTipoSesionPlan.getValue()),
                                            idPaciente));

                    servicioPaciente.
                            visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                            ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                            deshabilitarCajas(VariablesEstaticas.cajasPlanes);

                    mensajeAdvertenciaError("Plan de tratamiento actualizado con éxito", this, VariablesEstaticas.imgenExito);

                    botonActualizarPlanTratamiento.setId("botonAgregarPlanTratamiento");

                    buscarPLanes();

                } catch (Exception e) {
                    mensajeAdvertenciaError("Error al actualizar Plan", this, VariablesEstaticas.imgenError);
                    e.printStackTrace();
                }

            } else {
                mensajeAdvertenciaError("Buscar paciente a actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
      

    }

    @FXML
    private void actualizarObraSocialPaciente(MouseEvent event) {

        //SI CAJAS ESTAN HABILITADAS
        if (botonActualizarObraSocialPaciente.getId().equals("1")) {
            
                //SI SE BUSCO UN PACIENTE
                if (!cajaBuscarPaciente.getText().isEmpty()) {
                    //SI LAS CAJAS IMPORTANTES NO ESTAN VACIAS
                    if (Objects.isNull(choiseNombreObraSocialPaciente.getValue())
                            || Objects.isNull(choisePlanesObraSocialPacientePlan.getValue())
                            || cajaNAfiliadoObraSocialPaciente.getText().isEmpty()) {

                        //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                        mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                        //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                        servicioPaciente.
                                pintarCajaVaciaImportante(VariablesEstaticas.cajasObraSocialPaciente).
                                pintarChoiseVacioImportante(VariablesEstaticas.choiseObraSocialPaciente);
                        

                    } else {
                        try {
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
                                        afiliado.setNumero(Long.parseLong(entry.getValue()));
                                        break;

                                }
                            }

                            obraSocialPaciente.setPlan(planObraSocial);
                            obraSocialPaciente.setAfiliado(afiliado);

                            //crear paciente
                            //obtner id de anterior obra social y plan
                            daoImplementacion = new ObraSocialPacienteDAOImplementacion();
                            obraSocialPaciente.setId(daoImplementacion.obtenerId(obraSocialPaciente));

                            daoImplementacion = new PlanObraSocialDAOImplementacion();
                            obraSocialPaciente.getPlan().setId(daoImplementacion.obtenerId(new ObraSocial(obraSocialPaciente.getId(), obraSocialPaciente.getNombre(), planObraSocial.getNombre())));

                            daoImplementacion = new PacienteDAOImplementacion();
                            obraSocialPaciente.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))));

                            daoImplementacion = new AfiliadoDAOImplementacion();
                            afiliado.setIdPaciente(obraSocialPaciente.getIdPaciente());
                            obraSocialPaciente.getAfiliado().setId(daoImplementacion.obtenerId(afiliado));

                            //pasar valores nuevos
                            obraSocialPaciente.getPlan().setNombre(choisePlanesObraSocialPacientePlan.getValue());

                            obraSocialPaciente.getAfiliado().setNumero(Long.parseLong(cajaNAfiliadoObraSocialPaciente.getText()));

                            obraSocialPaciente.setNombre(choiseNombreObraSocialPaciente.getValue());

                            daoImplementacion = new ObraSocialPacienteDAOImplementacion();
                            daoImplementacion.actualizar(obraSocialPaciente);

                            buscarPaciente();
                            servicioPaciente.
                                    visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                                    ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                                    deshabilitarCajas(VariablesEstaticas.cajasObraSocialPaciente);
                            botonActualizarObraSocialPaciente.setId("botonActualizarObraSocialPaciente");
                            mensajeAdvertenciaError("Obra social del paciente actualizada con éxito", this, VariablesEstaticas.imgenExito);
                            
                            botonActualizarObraSocialPaciente.getId().equals("0");
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                            mensajeAdvertenciaError("Error al actualizar obra social del paciente", this, VariablesEstaticas.imgenError);
                            vaciarTodasLasCajas(event);
                            botonActualizarObraSocialPaciente.getId().equals("0");
                        }
                      
                    }
                } else {
                    mensajeAdvertenciaError("Buscar paciente a actualizar", this, VariablesEstaticas.imgenAdvertencia);
                    botonActualizarObraSocialPaciente.getId().equals("0");
                }

         
        } else {
            choiseNombreObraSocialPaciente.setValue(cajaNombreObraSocialPaciente.getText());
            choisePlanesObraSocialPacientePlan.setValue(cajaPlanObraSocialPaciente.getText());
            botonActualizarObraSocialPaciente.setId("1");
            servicioPaciente.
                    visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                    ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                    habilitarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                    animarCajasAlDarABoton(VariablesEstaticas.cajasObraSocialPaciente).
                    animarChoiceAlDarABoton(VariablesEstaticas.choiseObraSocialPaciente);
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
        mensajePreguntarSiONo("Desea eliminar");
        if (VariablesEstaticas.esSiONoMensajePrguntarSiONo) {
            daoImplementacion = new PacienteDAOImplementacion();
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty() || !cajaBuscarPaciente.getText().isBlank()) {
                //SI LAS CAJAS IMPORTANTES TIENEN DATOS
                if (!cajaNombreDatosPrincipales.getText().isBlank() && !cajaApellidoDatosPrincipales.getText().isBlank() && !cajaDniDatosPrincipales.getText().isBlank()) {
                    try {

                        daoImplementacion.eliminar(new Paciente(Integer.parseInt(cajaDniDatosPrincipales.getText())));
                        mensajeAdvertenciaError("Paciente eliminado con éxito", this, VariablesEstaticas.imgenExito);
                        servicioPaciente.
                                vaciarTodo().
                                habilitarTodo().
                                deshabilitarBotones(listaBotonesEliminar).
                                deshabilitarBotones(listaBotonesActualizar).
                                habilitarBotones(listaBotonesCrear);
                        cajaBuscarPaciente.setText(null);
                        vaciarTodasLasCajas(event);
                        buscarPLanes();

                    } catch (Exception e) {
                        mensajeAdvertenciaError("Error al eliminar paciente", this, VariablesEstaticas.imgenError);
                    }
                } else {
                    mensajeAdvertenciaError("Faltan datos para eliminar paciente", this, VariablesEstaticas.imgenAdvertencia);
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para eliminar", this, VariablesEstaticas.imgenAdvertencia);
            }
        }
    }

    @FXML
    private void eliminarPlan(MouseEvent event) {
        mensajePreguntarSiONo("¿Desea eliminar?");
        if (VariablesEstaticas.esSiONoMensajePrguntarSiONo) {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //SI CAJAS IMPORTANTES TIENEN TEXTO
                if (!cajaPlanFrecuenciaSesiones.getText().isBlank() && !cajaNombreTipoSesionPlan.getText().isBlank()) {
                    try {
                        daoImplementacion = new PacienteDAOImplementacion();
                        PlanTratamiento planTratamiento = new PlanTratamiento();
                        planTratamiento.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                        daoImplementacion = new PlanTratamientoDAOImplementacion();
                        daoImplementacion.eliminar(planTratamiento);
                        mensajeAdvertenciaError("Plan eliminado con éxito", this, VariablesEstaticas.imgenExito);
                        servicioPaciente.
                                vaciarValorChoise(VariablesEstaticas.choisePlan).
                                vaciarCajas(VariablesEstaticas.cajasPlanes);
                                botonEliminarPlanTratamiento.setDisable(true);
                        buscarPaciente();
                    } catch (Exception e) {
                        mensajeAdvertenciaError("Error al eliminar Plan de tratamiento", this, VariablesEstaticas.imgenError);
                    }

                } else {
                    mensajeAdvertenciaError("Faltan datos para eliminar plan", this, VariablesEstaticas.imgenAdvertencia);
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para eliminar", this, VariablesEstaticas.imgenAdvertencia);
            }
        }
    }

    //DIAGNOSTICO
    @FXML
    public void eliminarDiagnostico(MouseEvent event) {
        super.eliminarDiagnostico(event);
        buscarPaciente();

    }

    @FXML
    private void eliminarObraSocialPaciente(MouseEvent event) {
        mensajePreguntarSiONo("¿Desea eliminar?");
        if (VariablesEstaticas.esSiONoMensajePrguntarSiONo) {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //SI LAS CAJAS IMPORTANTES TIENEN TEXTO
                if (Objects.nonNull(choiseNombreObraSocialPaciente.getValue()) || Objects.nonNull(choisePlanesObraSocialPacientePlan.getValue())) {
                    try {
                        daoImplementacion = new PacienteDAOImplementacion();
                        ObraSocialPaciente obraSocialPaciente = new ObraSocialPaciente();
                        obraSocialPaciente.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));

                        daoImplementacion = new ObraSocialPacienteDAOImplementacion();
                        daoImplementacion.eliminar(obraSocialPaciente);
                        mensajeAdvertenciaError("Obra social del paciente eliminado con éxito", this, VariablesEstaticas.imgenExito);
                        servicioPaciente.
                                vaciarValorChoise(VariablesEstaticas.choiseObraSocialPaciente).
                                vaciarCajas(VariablesEstaticas.cajasObraSocialPaciente).
                                habilitarBotonCrear(botonAgregarObraSocialPaciente).
                                desHabilitarEliminarActualizar(botonEliminarObraSocialPaciente, botonActualizarObraSocialPaciente);
                        buscarPaciente();
                    } catch (Exception e) {
                        mensajeAdvertenciaError("Error al eliminar Obra social paciente", this, VariablesEstaticas.imgenError);
                    }

                } else {
                    mensajeAdvertenciaError("Faltan datos para eliminar obra social del paciente", this, VariablesEstaticas.imgenAdvertencia);
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para eliminar", this, VariablesEstaticas.imgenAdvertencia);
            }
        }
    }

    @FXML
    private void eliminarSesion(MouseEvent event) {

        List<CheckBox> check;

        try {

            if (tableSesiones.getSelectionModel().isEmpty()) {
                mensajeAdvertenciaError("Seleccione sesion para pode eliminar", this, VariablesEstaticas.imgenAdvertencia);

            } else {
                //INICIALIZAR VARIABLES
                LocalDate ldAutorizacion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion());
                LocalDate ldSesion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
                //Paciente pacienteSesion = new Paciente();
                SesionPaciente sesion = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
                CodigoFacturacion codigo = new CodigoFacturacion();

                //Paciente pacienteBuscar = new Paciente();
                SesionPaciente sesionBuscar = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacionBuscar = new AutorizacionesSesionesObraSociales();

                //SI SE SELECCIONO SESION Y SE APRETO EL BOTON DE ELIMINAR
                if (!cajaBuscarPaciente.getText().isBlank()) {

                    //INICIALIZAR VARIABLES
                    daoImplementacion = new PacienteDAOImplementacion();
                    int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));

                    sesionBuscar.setIdPaciente(idPaciente);

                    autorizacionBuscar.setNumeroAutorizacion(Long.parseLong(tablaAutorizacion.getSelectionModel().getSelectedItem().getNumeroAutorizacion()));
                    autorizacionBuscar.setAsociacion(ldAutorizacion);
                    sesionBuscar.setAutorizacion(autorizacionBuscar);
                    sesionBuscar.setFecha(ldSesion);
                    sesionBuscar.setNumeroSesion(Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion()));

                    daoImplementacion = new SesionDAOImplementacion();
                    int idSesion = Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getIdSesion());

                    LocalDate ldsNuevo = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
                    LocalDate ldsaNuevo = LocalDate.parse(tablaAutorizacion.getSelectionModel().getSelectedItem().getAsociacion());

                    daoImplementacion = new AutorizacionDAOImplementacion();
                    int idAutorizacion = Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getIdAutorizacion()); 
                    autorizacion.setId(idAutorizacion);
                    
                    autorizacion.setNumeroAutorizacion(Long.parseLong(tablaAutorizacion.getSelectionModel().getSelectedItem().getNumeroAutorizacion()));
                    autorizacion.setAsociacion(ldsaNuevo);
                    autorizacion.setObservacion(tablaAutorizacion.getSelectionModel().getSelectedItem().getObservacionAutorizacion());
                    autorizacion.setCopago(Double.parseDouble(tablaAutorizacion.getSelectionModel().getSelectedItem().getCopago()));
                    codigo.setNombre(tablaAutorizacion.getSelectionModel().getSelectedItem().getNombreCodigo());
                    daoImplementacion = new CodigoFacturacionDAOImplementacion();
                    codigo.setId(daoImplementacion.obtenerId(codigo));
                    autorizacion.setCodigoFacturacion(codigo);
                    sesion.setAutorizacion(autorizacion);

                    sesion.setIdSesion(idSesion);
                    sesion.setNumeroSesion(Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion()));
                    sesion.setFecha(ldsNuevo);
                    sesion.setTrabajoSesion(tableSesiones.getSelectionModel().getSelectedItem().getTrabajoSesion());
                    sesion.setObservacion(tableSesiones.getSelectionModel().getSelectedItem().getObservacionSesion());
                    sesion.setHonorarioPorSesion(Double.parseDouble(tableSesiones.getSelectionModel().getSelectedItem().getHonorariosPorSesion()));

                    sesion.setIdPaciente(idPaciente);

                    daoImplementacion = new SesionDAOImplementacion();
                    idSesion = daoImplementacion.obtenerId(sesion);

                    autorizacion.setIdSesion(idSesion);

                    mensajeEliminarSesion(this);
                    for (Map.Entry<List<CheckBox>, Boolean> entry : valoresElimenarSesion.entrySet()) {
                        check = entry.getKey();
                        Object val = entry.getValue();
                        if (val.equals(true)) {
                            if (!check.isEmpty()) {
                                if (!check.get(0).isSelected() && check.get(1).isSelected()) {
                                    try {
                                        daoImplementacion = new AutorizacionDAOImplementacion();
                                        //ELIMINAR AUTORIZACION

                                        daoImplementacion.actualizar(new AutorizacionesSesionesObraSociales(idAutorizacion, (long) 0, "---------", LocalDate.now(), 0.0, codigo, idSesion, idPaciente));
                                    } catch (Exception e) {
                                    }
                                } else if (check.get(0).isSelected()) {
                                    try {
                                        daoImplementacion = new SesionDAOImplementacion();
                                        //ELIMINAR SESION
                                        daoImplementacion.eliminar(sesion);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }

                    }

                    buscarPaciente();

                } else {
                    mensajeAdvertenciaError("Buscar paciente para eliminar sesión", this, VariablesEstaticas.imgenAdvertencia);
                }
            }
        } catch (Exception e) {

            mensajeAdvertenciaError("Error al eliminar sesión", this, VariablesEstaticas.imgenError);
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
        blanquearCajas();
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
                        botonActualizarPlanesObraSocial.setDisable(false);
                        botonEliminarPlanesObraSocial.setDisable(false);
                        VariablesEstaticas.obraSocial.setNombre(tablaObraSocial.getSelectionModel().getSelectedItem().getNombre());
                    } else {
                        mensajeAdvertenciaError("Error al buscar obra social", this, VariablesEstaticas.imgenError);
                        servicioObraSocial.
                                vaciarListaObraSocial().
                                habilitarCajas(cajasObrasSociales).
                                desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                                vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                                vaciarChoise(VariablesEstaticas.choiceObraSocial);
                        botonAgregarPlanesObraSocial.setDisable(true);
                        botonActualizarPlanesObraSocial.setDisable(true);
                    }
                } else {
                    mensajeAdvertenciaError("Error al buscar obra social", this, VariablesEstaticas.imgenError);
                    servicioObraSocial.
                            vaciarListaObraSocial().
                            habilitarCajas(cajasObrasSociales).
                            desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                            habilitarBotonCrear(botonAgregarObraSocial).
                            vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                            vaciarChoise(VariablesEstaticas.choiceObraSocial);
                    botonAgregarPlanesObraSocial.setDisable(true);
                    botonActualizarPlanesObraSocial.setDisable(true);
                }

            } else {
                mensajeAdvertenciaError("Error al buscar obra social", this, VariablesEstaticas.imgenError);
                servicioObraSocial.
                        vaciarListaObraSocial().
                        habilitarCajas(cajasObrasSociales).
                        desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                        habilitarBotonCrear(botonAgregarObraSocial).
                        vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                        vaciarChoise(VariablesEstaticas.choiceObraSocial);
                botonAgregarPlanesObraSocial.setDisable(true);
                botonActualizarPlanesObraSocial.setDisable(true);
            }

        } else {
            buscarObraSocialDesdeCaja();
        }
        hboxPlanObraSocial.setVisible(false);
    }
    
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    public void buscarObraSocialDesdeCaja() {
        
        blanquearCajas();
        if (!cajaBuscarObraSocial.getText().isBlank()) {

            if (!cajaBuscarObraSocial.getText().equals("0")) {
                ObraSocial obraSocial = new ObraSocial();

                try {
                    daoImplementacion = new ObraSocialDAOImplementacion();
                    obraSocial = (ObraSocial) daoImplementacion.obtener(new ObraSocial(cajaBuscarObraSocial.getText()));
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
                        botonActualizarPlanesObraSocial.setDisable(false);
                        botonEliminarPlanesObraSocial.setDisable(false);
                        VariablesEstaticas.obraSocial.setNombre(obraSocial.getNombre());
                    } else {
                        mensajeAdvertenciaError("Obra Social no encontrada", this, VariablesEstaticas.imgenAdvertencia);
                        servicioObraSocial.
                                vaciarListaObraSocial().
                                habilitarCajas(cajasObrasSociales).
                                desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                habilitarBotonCrear(botonAgregarObraSocial).
                                vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                                vaciarChoise(VariablesEstaticas.choiceObraSocial);
                        botonAgregarPlanesObraSocial.setDisable(true);
                        botonActualizarPlanesObraSocial.setDisable(true);
                    }
                } catch (Exception e) {
                    mensajeAdvertenciaError("Error al buscar obra social", this, VariablesEstaticas.imgenError);
                    servicioObraSocial.
                            vaciarListaObraSocial().
                            habilitarCajas(cajasObrasSociales).
                            desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                            habilitarBotonCrear(botonAgregarObraSocial).
                            vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                            vaciarChoise(VariablesEstaticas.choiceObraSocial);
                    botonAgregarPlanesObraSocial.setDisable(true);
                    botonActualizarPlanesObraSocial.setDisable(true);
                }

            } else {
                mensajeAdvertenciaError("Obra Social no encontrada", this, VariablesEstaticas.imgenAdvertencia);
                servicioObraSocial.
                        vaciarListaObraSocial().
                        habilitarCajas(cajasObrasSociales).
                        desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                        habilitarBotonCrear(botonAgregarObraSocial).
                        vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                        vaciarChoise(VariablesEstaticas.choiceObraSocial);
                botonAgregarPlanesObraSocial.setDisable(true);
                botonActualizarPlanesObraSocial.setDisable(true);
            }

        } else {
            mensajeAdvertenciaError("Obra Social no encontrada", this, VariablesEstaticas.imgenAdvertencia);
            servicioObraSocial.
                    vaciarListaObraSocial().
                    habilitarCajas(cajasObrasSociales).
                    desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                    habilitarBotonCrear(botonAgregarObraSocial).
                    vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                    vaciarChoise(VariablesEstaticas.choiceObraSocial);
            botonAgregarPlanesObraSocial.setDisable(true);
            botonActualizarPlanesObraSocial.setDisable(true);
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
            if (!cajaNombreObraSocial.getText().isBlank()) {

                ObraSocial obraSocial = new ObraSocial(
                        cajaNombreObraSocial.getText(),
                        new Telefono(cajaTelefonoObraSocial.getText()),
                        new Web(cajaWebObraSocial.getText()),
                        true,
                        new Email(cajaEmailObraSocial.getText()));

                obraSocial = servicioObraSocial.rellenarDatosObraSocialVacia(obraSocial);
                daoImplementacion = new ObraSocialDAOImplementacion();
                daoImplementacion.insertar(obraSocial);
                inicializarTableObraSocial();
                iniciarChoicePlanObraSocialPaciente();
                botonAgregarPlanesObraSocial.setDisable(false);
                cajaBuscarObraSocial.setText(cajaNombreObraSocial.getText());

                mensajeAdvertenciaError("Obra social creada con éxito", this, VariablesEstaticas.imgenExito);

               

                buscarObraSocial(event);

            } else {
                servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getClass().equals(Exepciones.class)) {
                mensajeAdvertenciaError(e.getMessage(), this, VariablesEstaticas.imgenError);
            } else {
                mensajeAdvertenciaError("Error al crear obra social", this, VariablesEstaticas.imgenError);
            }

        }

        vaciarCajasObraSocial(event);

    }
    
    @FXML
    public void eliminarPlanesObrasSocial(MouseEvent event){
         try {
            if (!choiceVerPlanesObraSocial.getValue().isEmpty() ) {

                ObraSocial obraSocial = new ObraSocial(
                        cajaNombreObraSocial.getText(),
                        new Telefono(cajaTelefonoObraSocial.getText()),
                        new Web(cajaWebObraSocial.getText()),
                        true,
                        new Email(cajaEmailObraSocial.getText()));

                obraSocial = servicioObraSocial.rellenarDatosObraSocialVacia(obraSocial);
                obraSocial.setNombre(cajaBuscarObraSocial.getText());
                daoImplementacion = new ObraSocialDAOImplementacion();
                obraSocial.setId(daoImplementacion.obtenerId(obraSocial));
                daoImplementacion = new PlanObraSocialDAOImplementacion();
                obraSocial.setPlan(choiceVerPlanesObraSocial.getValue());
                obraSocial.setIdPlan(daoImplementacion.obtenerId(obraSocial));
                
                 daoImplementacion.eliminar(obraSocial);
                 
                  
                cajaAgregarPlanObraSocial.setText("");
                hboxPlanObraSocial.setVisible(false);
                inicializarTableObraSocial();
                iniciarChoicePlanObraSocialPaciente();
               buscarObraSocial(event);
                botonActualizarPlanesObraSocial.setDisable(false);
                botonAgregarPlanesObraSocial.setDisable(false);
                botonActualizarObraSocial.setDisable(false);
                botonEliminarObraSocial.setDisable(false);
                mensajeAdvertenciaError("Plan eliminado con exito", this, VariablesEstaticas.imgenExito);

            } else {
               
                servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                mensajeAdvertenciaError("No hay elementos seleccionados para eliminar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError("Error al eliminar plan", this, VariablesEstaticas.imgenError);
        }
    }

    private void agregarPlanObraSocial(MouseEvent event) {
        try {
            
            if (!cajaNombreObraSocial.getText().isBlank()) {
               
                ObraSocial obraSocial = new ObraSocial(
                        cajaNombreObraSocial.getText(),
                        new Telefono(cajaTelefonoObraSocial.getText()),
                        new Web(cajaWebObraSocial.getText()),
                        true,
                        new Email(cajaEmailObraSocial.getText()));

                obraSocial = servicioObraSocial.rellenarDatosObraSocialVacia(obraSocial);
                obraSocial.setNombre(cajaBuscarObraSocial.getText());
                obraSocial.setPlan(cajaAgregarPlanObraSocial.getText());
                daoImplementacion = new PlanObraSocialDAOImplementacion();
                
                 if(choiceVerPlanesObraSocial.getItems().getFirst().equals("Sin Plan")){
                   ObraSocial obraSocialCrear = new ObraSocial();
                    daoImplementacion = new ObraSocialDAOImplementacion();
                    obraSocialCrear.setId(daoImplementacion.obtenerId(obraSocial));
                    daoImplementacion = new PlanObraSocialDAOImplementacion();
                    obraSocialCrear.setPlan(choiceVerPlanesObraSocial.getItems().getFirst());
                    obraSocialCrear.setIdPlan(daoImplementacion.obtenerId(obraSocialCrear));
                    daoImplementacion.eliminar(obraSocialCrear);
                     
                }
                
               daoImplementacion.insertar(obraSocial);
                
                cajaAgregarPlanObraSocial.setText("");
                hboxPlanObraSocial.setVisible(false);

                //choiceObraSocial.clear();
                inicializarTableObraSocial();
                iniciarChoicePlanObraSocialPaciente();
                buscarObraSocial(event);
                botonActualizarPlanesObraSocial.setDisable(false);
                botonAgregarPlanesObraSocial.setDisable(false);
                botonActualizarObraSocial.setDisable(false);
                botonEliminarObraSocial.setDisable(false);

            } else {
                servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al crear plan", this, VariablesEstaticas.imgenError);
            e.printStackTrace();
        }

    }

    private void actualizarPlanObraSocial(MouseEvent event) {
        try {
            if (!cajaNombreObraSocial.getText().isBlank()) {

                ObraSocial obraSocial = new ObraSocial(
                        cajaNombreObraSocial.getText(),
                        new Telefono(cajaTelefonoObraSocial.getText()),
                        new Web(cajaWebObraSocial.getText()),
                        true,
                        new Email(cajaEmailObraSocial.getText()));

                obraSocial = servicioObraSocial.rellenarDatosObraSocialVacia(obraSocial);
                obraSocial.setNombre(cajaBuscarObraSocial.getText());
                daoImplementacion = new ObraSocialDAOImplementacion();
                obraSocial.setId(daoImplementacion.obtenerId(obraSocial));
                daoImplementacion = new PlanObraSocialDAOImplementacion();
                obraSocial.setPlan(choiceVerPlanesObraSocial.getValue());
                obraSocial.setIdPlan(daoImplementacion.obtenerId(obraSocial));
                
                if(!cajaAgregarPlanObraSocial.getText().isBlank()){
                   obraSocial.setPlan(cajaAgregarPlanObraSocial.getText());
                    daoImplementacion.actualizar(obraSocial);
                  
                }
               
               
                cajaAgregarPlanObraSocial.setText("");
                hboxPlanObraSocial.setVisible(false);
                inicializarTableObraSocial();
                iniciarChoicePlanObraSocialPaciente();
               buscarObraSocial(event);
                botonActualizarPlanesObraSocial.setDisable(false);
                botonAgregarPlanesObraSocial.setDisable(false);
                botonActualizarObraSocial.setDisable(false);
                botonEliminarObraSocial.setDisable(false);

            } else {
                servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError("Error al crear plan", this, VariablesEstaticas.imgenError);
        }

    }

    @FXML
    private void agregarActualizarPlanesObrasSocial(MouseEvent event) {
        Button boton = (Button) event.getSource();
        if (boton.getId().equals("botonAgregarPlanesObraSocial")) {
            botonCrearActualizarPlanesObraSocial.setOnMouseClicked(this::agregarPlanObraSocial);
            hboxPlanObraSocial.setVisible(true);
            botonActualizarPlanesObraSocial.setDisable(true);
            botonAgregarPlanesObraSocial.setDisable(true);
            botonActualizarObraSocial.setDisable(true);
            botonEliminarObraSocial.setDisable(true);
        } else if (boton.getId().equals("botonActualizarPlanesObraSocial")) {
            if (Objects.nonNull(choiceVerPlanesObraSocial.getValue())) {
                botonCrearActualizarPlanesObraSocial.setOnMouseClicked(this::actualizarPlanObraSocial);
                cajaAgregarPlanObraSocial.setText(choiceVerPlanesObraSocial.getValue());
                hboxPlanObraSocial.setVisible(true);
                botonActualizarPlanesObraSocial.setDisable(true);
                botonAgregarPlanesObraSocial.setDisable(true);
                botonActualizarObraSocial.setDisable(true);
                botonEliminarObraSocial.setDisable(true);
            } else {
                mensajeAdvertenciaError("Seleccione plan para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
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
                    if (!cajaNombreObraSocial.getText().isBlank()) {
                        ObraSocial obraSocial = new ObraSocial(
                                VariablesEstaticas.valoresBUsquedaObraSocial.get("1"),
                                new Telefono(VariablesEstaticas.valoresBUsquedaObraSocial.get("2")),
                                new Web(VariablesEstaticas.valoresBUsquedaObraSocial.get("4")),
                                true,
                                new Email(VariablesEstaticas.valoresBUsquedaObraSocial.get("3")));

                        if (!cajaEmailObraSocial.getText().isEmpty()) {
                            daoImplementacion = new EmailDAOImplementacion();
                            obraSocial.getEmail().setId(daoImplementacion.obtenerId(new Email(VariablesEstaticas.valoresBUsquedaObraSocial.get("3"))));
                        }
                        daoImplementacion = new ObraSocialDAOImplementacion();
                        obraSocial.setId(daoImplementacion.obtenerId(obraSocial));
                        obraSocial.setNombre(cajaNombreObraSocial.getText());
                        obraSocial.getTelefono().setTelefono(cajaTelefonoObraSocial.getText());
                        obraSocial.getWeb().setWeb(cajaWebObraSocial.getText());
                        obraSocial.getEmail().setEmail(cajaEmailObraSocial.getText());

                        obraSocial = servicioObraSocial.rellenarDatosObraSocialVacia(obraSocial);

                        //SI SE INICIALIZAO OBRA SOCIAL
                        if (Objects.nonNull(obraSocial)) {
                            daoImplementacion = new ObraSocialDAOImplementacion();
                            daoImplementacion.actualizar(obraSocial);
                            botonActualizarObraSocial.setId("botonActualizarObraSocial");
                            cajaBuscarObraSocial.setText(cajaNombreObraSocial.getText());
                            buscarObraSocial(event);
                            inicializarTableObraSocial();
                            iniciarChoicePlanObraSocialPaciente();
                            servicioObraSocial.
                                    deshabilitarCajas(VariablesEstaticas.cajasObrasSociales);

                            mensajeAdvertenciaError("Obra social actualizada con éxito", this, VariablesEstaticas.imgenExito);

                        } else {
                            mensajeAdvertenciaError("Error al actualizar obra social", this, VariablesEstaticas.imgenError);
                        }
                    } else {
                        mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                        servicioPaciente.
                                pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                    }

                } else {
                    mensajeAdvertenciaError("Buscar obra social para actualizar", this, VariablesEstaticas.imgenAdvertencia);
                }
                vaciarCajasObraSocial(event);
            } else {
                botonActualizarObraSocial.setId("1");
                servicioObraSocial.
                        habilitarCajas(VariablesEstaticas.cajasObrasSociales).
                        animarCajasAlDarABoton(VariablesEstaticas.cajasObrasSociales);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al actualizar obra social", this, VariablesEstaticas.imgenError);
        }

    }

    @FXML
    private void eliminarObraSocial(MouseEvent event) {
        mensajePreguntarSiONo("¿Desea eliminar?");
        if (VariablesEstaticas.esSiONoMensajePrguntarSiONo) {
            try {
                if (!cajaBuscarObraSocial.getText().isBlank()) {
                    ObraSocial obraSocial = new ObraSocial(
                            cajaNombreObraSocial.getText(),
                            new Telefono(cajaTelefonoObraSocial.getText()),
                            new Web(cajaWebObraSocial.getText()),
                            true,
                            new Email(cajaEmailObraSocial.getText()));

                    obraSocial = servicioObraSocial.rellenarDatosObraSocialVacia(obraSocial);

                    daoImplementacion = new ObraSocialDAOImplementacion();
                    obraSocial.setId(daoImplementacion.obtenerId(obraSocial));
                   
                    if (Objects.nonNull(obraSocial.getId())) {
                        daoImplementacion.eliminar(obraSocial);

                        inicializarTableObraSocial();
                        iniciarChoicePlanObraSocialPaciente();
                        mensajeAdvertenciaError("Obra social eliminada con éxito", this, VariablesEstaticas.imgenExito);
                        servicioObraSocial.
                                habilitarCajas(cajasObrasSociales).
                                habilitarBotonCrear(botonAgregarObraSocial).
                                desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                                vaciarCajas(VariablesEstaticas.cajasObrasSociales);
                        cajaBuscarObraSocial.setText("");
                        botonAgregarPlanesObraSocial.setDisable(false);
                    } else {
                        mensajeAdvertenciaError("Buscar obra social para eliminar", this, VariablesEstaticas.imgenAdvertencia);
                    }

                } else {
                    mensajeAdvertenciaError("Buscar obra social para eliminar", this, VariablesEstaticas.imgenAdvertencia);
                }
            } catch (Exception e) {
                mensajeAdvertenciaError("Error al eliminar obra social", this, VariablesEstaticas.imgenError);
            }
        }
    }

    @FXML
    public void vaciarCajasObraSocial(MouseEvent event) {
        blanquearCajas();
        servicioObraSocial.
                vaciarListaObraSocial().
                habilitarCajas(cajasObrasSociales).
                desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                habilitarBotonCrear(botonAgregarObraSocial).
                vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                vaciarChoise(VariablesEstaticas.choiceObraSocial);

        cajaBuscarObraSocial.setText("");
        hboxPlanObraSocial.setVisible(false);
        botonAgregarPlanesObraSocial.setDisable(true);
        botonActualizarPlanesObraSocial.setDisable(true);
        botonEliminarPlanesObraSocial.setDisable(true);
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
    private void cambiarAMesSuperior(MouseEvent event) {
        AgendaController agenda = new AgendaController();
        agenda.rellenarAgenda(2);
    }

    @FXML
    private void cambiarAMesInferior(MouseEvent event) {
        AgendaController agenda = new AgendaController();
        agenda.rellenarAgenda(3);
    }

    @FXML
    protected void alDarEnterBoton(KeyEvent event) {

        Node node = (Node) event.getSource();

        if (event.getCode() == KeyCode.ENTER) {
            switch (node.getId()) {
                case "cajaBuscarPaciente":
                    TextField tf = (TextField) node;

                    buscarPaciente();
                    break;
                case "cajaBuscarObraSocial":

                    buscarObraSocialDesdeCaja();

                    break;
                case "cajaUsuario":
                    break;
            }

        }

    }

    @FXML
    protected void vaciarCajaBuscar(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        if (tf.getText().length() - 1 >= 0) {
            if (!Character.isDigit(tf.getText().charAt(tf.getText().length() - 1))) {
                tf.deletePreviousChar();
            }
        }

    }

    @FXML
    protected void vaciarTodasLasCajas(MouseEvent event) {

        blanquearCajas();
        cajaBuscarPaciente.setText("");

        servicioPaciente.
                vaciarListas().
                vaciarTodo().
                habilitarTodo().
                visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                habilitarBotones(VariablesEstaticas.listaBotonesCrear).
                deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar).
                deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar);
        VariablesEstaticas.paciente = null;
        buscarPLanes();

        botonActualizarPlanTratamiento.setId("0");
        

    }

    @FXML
    public void actualizarUsuarioOpciones(MouseEvent event) {

        try {
            Usuario usuarioActualizar;
            if (botonActualizarUsuarioOpciones.getId().equals("1")) {

                if (!cajaNombreOpcionesUsuario.getText().isBlank()
                        || !cajaApellidoOpcionesUsuario.getText().isBlank()
                        || !cajaUusarioOpcionesUsuario.getText().isBlank()) {

                    if (cajaEmailOpcionesUsuario.getText().isBlank()) {
                        cajaEmailOpcionesUsuario.setText("Sin Email");
                    }

                    usuarioActualizar = new Usuario(usuario.getId(), cajaNombreOpcionesUsuario.getText(), cajaApellidoOpcionesUsuario.getText(), cajaUusarioOpcionesUsuario.getText(), new Email(cajaEmailOpcionesUsuario.getText()));

                    if (!usuarioDao.existeNombreUsuario(usuarioActualizar)) {

                        daoImplementacion = new UsuarioDAOImplementacion();
                        daoImplementacion.actualizar(usuarioActualizar);
                        mensajeAdvertenciaError("Usuario actualizado con éxito", this, VariablesEstaticas.imgenExito);
                        servicioPaciente.deshabilitarCajas(VariablesEstaticas.cajasOpcionesUsuario);
                    } else {
                        if (usuario.getUsuario().equals(cajaUusarioOpcionesUsuario.getText())) {
                            daoImplementacion = new UsuarioDAOImplementacion();
                            daoImplementacion.actualizar(usuarioActualizar);
                            mensajeAdvertenciaError("Usuario actualizado con éxito", this, VariablesEstaticas.imgenExito);
                            servicioPaciente.deshabilitarCajas(VariablesEstaticas.cajasOpcionesUsuario);
                        } else {
                            mensajeAdvertenciaError("Ya existe nombre usuario", this, VariablesEstaticas.imgenAdvertencia);
                        }

                    }

                } else {
                    mensajeAdvertenciaError("Hay cajas vacias importantes", this, VariablesEstaticas.imgenAdvertencia);
                }
                botonActualizarUsuarioOpciones.setId("botonActualizarUsuarioOpciones");
            } else {
                servicioPaciente.
                        habilitarCajas(VariablesEstaticas.cajasOpcionesUsuario).
                        animarCajasAlDarABoton(VariablesEstaticas.cajasOpcionesUsuario);

                botonActualizarUsuarioOpciones.setId("1");
            }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al actualizar Usuario", this, VariablesEstaticas.imgenError);
        }
    }

    @FXML
    public void guardarBaseDeDatos(MouseEvent event) {
        hBoxCopiaBD.setVisible(true);
    }

    @FXML
    public void guardarBackuipSegunEleccion(MouseEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecciona una carpeta para guardar");

        File selectedDirectory = directoryChooser.showDialog(new Stage());

        String path = selectedDirectory.getAbsolutePath();

        Task<Void> tareaBackup = new Task<>() {

            @Override
            protected Void call() {
               
                ServicioOpciones servicioOp = new ServicioOpciones();
                try {

                    File sqlFile = servicioOp.generarBackupMariaDB("localhost", "3306", "cliente", "", "gestion_pacientes", path);
                    updateProgress(1.0, 1.0);
                    updateMessage("¡Respaldo LOCAL completado con éxito!");

                
                } catch (Exception e) {
                    updateProgress(1.0, 1.0);
                    updateMessage("¡ERROR!");
                    e.printStackTrace();
                    //mensajeAdvertenciaError("Error al crear copia de seguridad", this, VariablesEstaticas.imgenError);
                }

                return null;
            }
        };

        //progressBar.visibleProperty().bind(tareaBackup.runningProperty());
        //lblEstado.textProperty().bind(tareaBackup.messageProperty());
        new Thread(tareaBackup).start();

        hBoxCopiaBD.setVisible(false);

    }

    @FXML
    public void administraAccion(MouseEvent event) {

        try {

            HBox b = (HBox) event.getSource();

            FXMLLoader Loader = new FXMLLoader(App.class.getResource("AdministrarAccionAgenda.fxml"));
            Parent root = Loader.load();
            AgendaController controller = Loader.getController();
            Scene scene = new Scene(root);
            //scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initOwner(VariablesEstaticas.stagePrincipal);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);

            VBox vboxParent = (VBox) b.getParent();
            HBox hboxRecordatorio = null;
            for (Node node : vboxParent.getChildren()) {
                if (node.getId().equals("recordatorio")) {
                    hboxRecordatorio = (HBox) node;
                }
            }

            controller.setHboxPrecionadoRecordatorio(hboxRecordatorio);
            controller.setHboxPrecionadoParaEditarOverAccion(b);
            controller.iniciarAdministrarAccion(stage, root, b);
            controller.setHboxAbiertoParaEditarAccion(b.getId());

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void agrandarCajaParaVer(MouseEvent event) {
        try {

            FXMLLoader Loader = new FXMLLoader(App.class.getResource("CajaVerHtml.fxml"));
            Parent root = Loader.load();
            CajaVerHtmlController controller = Loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);

            Node ev = (Node) event.getSource();
            String textoAVer = "";

            switch (ev.getId()) {
                case "botonVerDiagnostico":
                    textoAVer = VariablesEstaticas.valoresBUsquedaDiagnosticoHTML.get("1");
                    break;
                case "botonVerObservacionDiagnostico":
                    textoAVer = VariablesEstaticas.valoresBUsquedaDiagnosticoHTML.get("2");
                    break;

            }

            if (!cajaBuscarPaciente.getText().isEmpty()) {

                controller.llenarCaja(textoAVer);
                controller.setIdBoton(ev.getId());
                controller.setNumDNIPaciente(Integer.parseInt(cajaBuscarPaciente.getText()));
                stage.showAndWait();
            }

            if (ev.getId().equals("botonVerDiagnostico") || ev.getId().equals("botonVerObservacionDiagnostico")) {
                buscarPaciente();
            }

        } catch (IOException ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarVariablesEstaticas() {
        try {
            VariablesEstaticas.usuario.setId(usuarioDao.obtenerId(new Usuario()));
        } catch (Exception e) {
        }

//INICIALIZAR CAJAS ESTATICAS
        //USUARIO
        VariablesEstaticas.cajasOpcionesUsuario
                = Arrays.asList(
                        cajaNombreOpcionesUsuario,
                        cajaApellidoOpcionesUsuario,
                        cajaUusarioOpcionesUsuario,
                        cajaEmailOpcionesUsuario
                );

        //PACIENTE
        VariablesEstaticas.cajasDatosPrincipales
                = Arrays.asList(
                        cajaNombreDatosPrincipales,
                        cajaApellidoDatosPrincipales,
                        cajaEdadDatosPrincipales,
                        cajaDniDatosPrincipales,
                        cajaTelefonoDatosPrincipales,
                        cajaHonorariosDatosPrincipales);

        /*VariablesEstaticas.cajasAreaSesion
                = Arrays.asList(
                        htmlTrabajoSesion,
                        htmlObservacionSesion,
                        htmlObservacionAutorizacion);*/
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
                        htmlDiagnostico,
                        htmlObservacionDiagnostico);

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

        /*
            
            1 DIAGNOSTICO
            2 OBSERVACION
        
         */
        VariablesEstaticas.valoresBUsquedaDiagnosticoHTML = Map.of("1", "", "2", "");

        VariablesEstaticas.listaBotonesActualizar
                = Arrays.asList(
                        botonActualizarDatosPrincipales,
                        
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
                        botonAgregarObraSocialPaciente);

        VariablesEstaticas.listaContenedoresAcordeon
                = Arrays.asList(
                        titlePaneDatosPrincipales,
                        titlePaneSesion,
                        titlePanePlan,
                        titlePaneDiagnostico,
                        titlePaneGenograma,
                        titlePaneObraSocial);

        VariablesEstaticas.tabDatosPricipales = titlePaneDatosPrincipales;

        VariablesEstaticas.vboxsPlanesTratamiento
                = Arrays.asList(
                        vBoxFrecuenciaSEsionPlan,
                        vBoxNombreTipoSEsionPlan);

        VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer
                = Arrays.asList(
                        vBoxFrecuenciaSEsionPlanActualizaroVer,
                        vBoxNombreTipoSEsionPlanActualizaroVer
                );

        VariablesEstaticas.vboxsObraSocialPaciente
                = Arrays.asList(
                        vboxNombreObraSocialPaciente,
                        vboxPlanObraSocialPaciente);
        VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer
                = Arrays.asList(
                        vboxNombreObraSocialPacienteActualizarVer,
                        vboxPlanObraSocialPacienteActualizarVer);

        //agenda
        VariablesEstaticas.imagenRecordatorioAgendaLateral = imgRecordatorio;

        //AGENDA
        VariablesEstaticas.anchorPrincipalAgenda = apAgendaPrincipal;
        VariablesEstaticas.anchorAgendaAgenda = apAgendaAgenda;
        VariablesEstaticas.gridAgenda = gpCalendario;
        AgendaController agenda = new AgendaController();
        agenda.rellenarAgenda(1);

        VariablesEstaticas.setImgenExito("/com/pacientes/gestor_pacientes/img/exito.png");
        VariablesEstaticas.setImgenError("/com/pacientes/gestor_pacientes/img/error.png");
        VariablesEstaticas.setImgenAdvertencia("/com/pacientes/gestor_pacientes/img/warning.png");

        VariablesEstaticas.setImagenVer(new Image("/com/pacientes/gestor_pacientes/img/ver.png"));
        VariablesEstaticas.setImagenAgregar(new Image("/com/pacientes/gestor_pacientes/img/lapiz.png"));
        VariablesEstaticas.setImagenRecordar(new Image("/com/pacientes/gestor_pacientes/img/recordatorio.png"));

    }

    public void iniciarChoiceTipoSesion() {
        try {
            daoImplementacion = new TipoSesionPlanDAOImplementacion();
            List<TipoSesion> listaTiposSesiones = daoImplementacion.obtenerLista(new TipoSesion());
            if (!Objects.isNull(listaTiposSesiones)) {
                for (TipoSesion h : listaTiposSesiones) {
                    choiseTipoSesionPlan.getItems().add(h.getNombre());
                }

            }
        } catch (Exception e) {
        }
    }

    public void iniciarChoiceOpciones() {

        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Copia local",
                "Copia en Google Drive"
        );

        // 2. Asignarla al ChoiceBox
        choiceBoxBDBackup.setItems(opciones);

        // Opcional: Establecer una opción seleccionada por defecto
        choiceBoxBDBackup.setValue("Copia local");

    }

    public void iniciarChoiceObraSocial() {
        try {
            daoImplementacion = new ObraSocialDAOImplementacion();
            List<ObraSocial> listaObrasSociales = daoImplementacion.obtenerLista(new ObraSocial());
            if (!Objects.isNull(listaObrasSociales)) {
                for (ObraSocial h : listaObrasSociales) {
                    choiseNombreObraSocialPaciente.getItems().add(h.getNombre());

                }
              //  choiseNombreObraSocialPaciente.setValue(choiseNombreObraSocialPaciente.getItems().getFirst());
                //12cambiarPlanesObraSocial(new ActionEvent(choiseNombreObraSocialPaciente, null));
            }
        } catch (Exception e) {
        }
    }

    public void iniciarChoiceFrecuencia() {
        try {
            daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();
            List<FrecuenciaSesion> listaFreceunciaPlan = daoImplementacion.obtenerLista(new FrecuenciaSesion());

            if (!Objects.isNull(listaFreceunciaPlan)) {
                for (FrecuenciaSesion h : listaFreceunciaPlan) {
                    choiseFrecuenciaSesionPlan.getItems().add(h.getFrecuencia());
                }

            }
        } catch (Exception e) {
        }
    }

    private void iniciarChoicePlanObraSocialPaciente() {
        
        daoImplementacion = new PlanObraSocialDAOImplementacion();
        try {
            listaPlanesObrasSociales = daoImplementacion.obtenerLista(new ObraSocial(choiseNombreObraSocialPaciente.getValue()));
        } catch (Exception e) {
        }

        valorInicialNombreObraSocialPaciente = choiseNombreObraSocialPaciente.getValue();

        if (!Objects.isNull(listaPlanesObrasSociales)) {
            for (ObraSocial plan : listaPlanesObrasSociales) {

                choisePlanesObraSocialPacientePlan.getItems().add(plan.getPlan());
                
                
            }
            
           // choisePlanesObraSocialPacientePlan.setValue(choisePlanesObraSocialPacientePlan.getItems().getFirst());
        }

    }

    @FXML
    public void actualizarAplicacion() {

        try {

            Path miJar = Paths.get(
                    MenuInicioController.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            );

            

            Path otroJar = miJar
                    .resolveSibling("../../actualizacionGestorPaciente/target/actualizador-1.0-SNAPSHOT.jar")
                    .normalize();

            

            ProcessBuilder pb = new ProcessBuilder("java", "-jar", otroJar.toString());

            pb.start();

          
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void abrirLIstaDePacientes(MouseEvent event) {
        DatosPrincipalesController datospples = new DatosPrincipalesController();
        datospples.rellenarLista();
    }

    @FXML
    void subrayarLabel(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setUnderline(true);
        label.setCursor(Cursor.HAND);
    }

    @FXML
    void desSubRayarLabel(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setUnderline(false);
        label.setCursor(Cursor.DEFAULT);
    }

    @FXML
    void recuperarContraseña(MouseEvent event) {
        vBoxCodiRecuperacionOpciones.setVisible(true);

        long codigo = 100000 + new Random().nextInt(900000);

        try {
            GestorMail.enviarCodigo(cajaEmailOpcionesUsuario.getText(), Long.toString(codigo));
            usuarioDao.insertarCodigo(codigo, usuario.getId());
        } catch (Exception e) {
        }

        //
    }

    @FXML
    void comprobarCodigoUsuarioOpciones(MouseEvent event) {
        try {

            if (cajaCodigoOpcionesUsuario.getText().equals(String.valueOf(usuarioDao.obtenerCodigo(usuario).getCodigo()))) {
                vBoxRecuperarContraseña.setVisible(true);
                vBoxCodiRecuperacionOpciones.setVisible(false);
            } else {
                mensajeAdvertenciaError("El código no coincide.", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
        }

    }

    //dsd
    @FXML
    void actualizaContraseñarUsuarioOpciones(MouseEvent event) {

        if (!cajaRestaurarContraseñaopcionesUsuario.getText().isBlank()) {
            if (cajaRestaurarContraseñaopcionesUsuario.getText().equals(cajaRepetirRestaurarContraseñaOpcionesUsuario.getText())) {
                UsuarioDAOImplementacion usuarioDaoActOp = new UsuarioDAOImplementacion();
                vBoxRecuperarContraseña.setVisible(false);

                Usuario usuarioActContra = new Usuario();
                usuarioActContra.setContraseña(cajaRestaurarContraseñaopcionesUsuario.getText());
                usuarioActContra.setId(usuario.getId());

                try {
                    long codigo = usuarioDaoActOp.obtenerCodigo(usuarioActContra).getCodigo();
                    if (codigo != 0) {
                        usuarioDaoActOp.actualizarContraseña(usuarioActContra);
                        usuarioDao.insertarCodigo(0, usuario.getId());
                        mensajeAdvertenciaError("contraseña actualizada con éxito.", this, VariablesEstaticas.imgenExito);
                        vBoxRecuperarContraseña.setVisible(false);

                        Usuario usuarioComContra = new Usuario();

                        usuarioComContra.setContraseña(cajaRestaurarContraseñaopcionesUsuario.getText());
                        usuarioComContra.setId(usuario.getId());

                    } else {
                        mensajeAdvertenciaError("El tiempo del código ha vencido.", this, VariablesEstaticas.imgenAdvertencia);
                        vBoxRecuperarContraseña.setVisible(false);
                    }
                } catch (Exception e) {
                }

            } else {
                mensajeAdvertenciaError("Las contraseñas no coinciden.", this, VariablesEstaticas.imgenError);
            }
        } else {
            mensajeAdvertenciaError("Agregar una contraseña.", this, VariablesEstaticas.imgenAdvertencia);
        }

    }

    @FXML
    public void elegirDirectorioAPP(MouseEvent event) {
        try {
            String path = Directorios.buscaArchivo();
            Actualizacion actualizacion = new Actualizacion();
            actualizacion.setRuta(path);
            usuarioDao.insertarRutaActualizacion(actualizacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
