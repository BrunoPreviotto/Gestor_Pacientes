/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

// PROYECTO
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
import com.pacientes.gestor_pacientes.DAO.IPacienteDAO;
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
import com.pacientes.gestor_pacientes.utilidades.Exepciones;

import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;

import java.net.URL;



import java.time.LocalDate;
import java.time.Month;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


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
        
       

       
        
        usuarioDao = new UsuarioDAOImplementacion();
        usuario = usuarioDao.obtenerNombreUsuario();
        try {
            VariablesEstaticas.usuario.setId(usuarioDao.obtenerId(new Usuario()));
        } catch (Exception e) {
        }
        
        this.onDraggedScene(containerMenu);
        
        etiquetaNombreInicio.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario1.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario2.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario3.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario4.setText(usuario.getNombre() + " " + usuario.getApellido());
        iniciarChoiceCodigoFacturacion();
        iniciarChoiceFrecuencia();
        iniciarChoiceObraSocial();
        
        iniciarChoiceTipoSesion();
        inicializarTableObraSocial();
        iniciarChoicePlanTratamiento();
        
        
        comprobarFechaAlIniciar();
        
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

        servicioPaciente.
                deshabilitarBotones(VariablesEstaticas.listaBotonesActualizar).
                deshabilitarBotones(VariablesEstaticas.listaBotonesEliminar);

         //cajas autrizacion
         VariablesEstaticas.cajaAutorizacionSesion = cajaAutorizacionSesion;

         VariablesEstaticas.cajaAsociacionSesionObraSocial = cajaAsociacionSesionObraSocial;

         VariablesEstaticas.cajaObservacionSesionObraSocial = cajaObservacionSesionObraSocial;

         VariablesEstaticas.cajaAtualizarCodigoFacturacionSesionObraSocial = cajaAtualizarCodigoFacturacionSesionObraSocial;

         VariablesEstaticas.cajaAtualizarNombreCodigoFacturacionSesionObraSocial = cajaAtualizarNombreCodigoFacturacionSesionObraSocial;

         VariablesEstaticas.cajaCodigoFacturacion = cajaCodigoFacturacion;
         
         VariablesEstaticas.cajaCopagoSesionObraSocial = cajaCopagoSesionObraSocial;
         
         VariablesEstaticas.choiseCodigoFactSesionObraSocial = choiseCodigoFactSesionObraSocial;
         
         

    }

     private void iniciarChoicePlanTratamiento() {
        
        
       daoImplementacion = new PlanObraSocialDAOImplementacion();
        try {
            listaPlanesObrasSociales = daoImplementacion.obtenerLista(new ObraSocial(choiseNombreObraSocialPaciente.getValue()));
        } catch (Exception e) {
        }
        
        valorInicialNombreObraSocialPaciente = choiseNombreObraSocialPaciente.getValue();

        if (!Objects.isNull(listaPlanesObrasSociales)) {
            for (ObraSocial plan: listaPlanesObrasSociales) {
                choisePlanesObraSocialPacientePlan.getItems().add(plan.getPlan());
            }
            
        }
        
        
        
    }
     
    public void iniciarChoiceTipoSesion(){
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
    
    public void iniciarChoiceCodigoFacturacion(){
        try {
             daoImplementacion = new CodigoFacturacionDAOImplementacion();
             List<CodigoFacturacion> listaCodigosFacturacion = daoImplementacion.obtenerLista(new CodigoFacturacion());
             if (!Objects.isNull(listaCodigosFacturacion)) {
                 for (CodigoFacturacion h : listaCodigosFacturacion) {
                    choiseCodigoFactSesionObraSocial.getItems().add(h.getNombre());
                 }

             }
         } catch (Exception e) {
         }
    }
    
    
    
    public void iniciarChoiceObraSocial(){
         try {
             daoImplementacion = new ObraSocialDAOImplementacion();
             List<ObraSocial> listaObrasSociales = daoImplementacion.obtenerLista(new ObraSocial());
             if (!Objects.isNull(listaObrasSociales)) {
                 for (ObraSocial h : listaObrasSociales) {
                     choiseNombreObraSocialPaciente.getItems().add(h.getNombre());

                 }

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
                daoImplementacion = new PacienteDAOImplementacion();
                pacienteBuscar.setId(daoImplementacion.obtenerId(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))));
                //SI PACIENTE EXISTE
                if (pacienteBuscar.getId() != 0) {
                    Paciente pacienteResultado;
                    try {
                        pacienteResultado = (Paciente)daoImplementacion.obtener(pacienteBuscar);
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
                        buscarPLanes(pacienteResultado);
                        

                        //SI DIAGNOSTICO EXISTE
                        buscarDiagnostico(pacienteResultado);
                        

                        //SI OBRA SOCIAL PACIENTE EXISTE
                        buscarObraSocial(pacienteResultado);  
                        

                        servicioPaciente.
                                comprobarSiAcordeonEstaCerrado(listaContenedoresAcordeon).
                                desHabilitarBotonCrear(botonAgregarPlanFrecuencia).
                                desHabilitarBotonCrear(botonAgregarPlanTipoSesion);

                    } else {
                        cajaBuscarPaciente.setText(null);
                        pacienteNoEncontradoBuscar();
                    }

                } else {
                    cajaBuscarPaciente.setText(null);
                    pacienteNoEncontradoBuscar();
                }

            } else {
                cajaBuscarPaciente.setText(null);
                pacienteNoEncontradoBuscar();
                
            }
            
            
             

        } catch (Exception e) {
            e.printStackTrace();
            cajaBuscarPaciente.setText(null);
            mensajeAdvertenciaError("Error al buscar paciente", this, VariablesEstaticas.imgenError);
            vBoxSesiones.setVisible(false);
            vBoxAutorizacion.setVisible(false);
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
        iniciarChoiceCodigoFacturacion();
        iniciarChoiceFrecuencia();
        iniciarChoiceObraSocial();
        iniciarChoiceTipoSesion();
        inicializarTableObraSocial();
        iniciarChoicePlanTratamiento();
        
        
       
    }
    
    public void pacienteNoEncontradoBuscar(){
        vBoxSesiones.setVisible(false);
                    vBoxAutorizacion.setVisible(false);
                    hbTablasSesionesAtorizaciones.setVisible(true);
                    mensajeAdvertenciaError("Paciente no encontrado", this, VariablesEstaticas.imgenAdvertencia);
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
                    habilitarEliminarActualizar(botonEliminarDatosPrincipales, botonActualizarDatosPrincipales);;

        } else {

            servicioPaciente.
                    vaciarListaDatosPrincipales().
                    habilitarBotonCrear(botonAgregarDatosPrincipales).
                    desHabilitarEliminarActualizar(botonEliminarDatosPrincipales, botonActualizarDatosPrincipales);
        }
    }
    
    
    public void buscarSesiones(Paciente pacienteResultado) {
        if (Objects.nonNull(pacienteResultado.getSesiones())) {
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

        } else {
            //servicioPaciente.vaciarListaSesiones();
        }

    }
    
    public void buscarPLanes(Paciente pacienteResultado) {
        if (Objects.nonNull(pacienteResultado.getPlanTratamiento())) {
            choiseFrecuenciaSesionPlan.setValue(pacienteResultado.getPlanTratamiento().getFrecuenciaSesion().getFrecuencia());
            cajaPlanFrecuenciaSesiones.setText(pacienteResultado.getPlanTratamiento().getFrecuenciaSesion().getFrecuencia());
            cajaDescripcionTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getDecripcion());
            cajaEstrategiaPlan.setText(pacienteResultado.getPlanTratamiento().getEstrategia());
            choiseTipoSesionPlan.setValue(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());
            cajaNombreTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());

            servicioPaciente.
                    rellenarListaPlan(pacienteResultado).
                    visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                    ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                    deshabilitarCajas(VariablesEstaticas.cajasPlanes).
                    desHabilitarBotonCrear(botonAgregarPlanTratamiento).
                    habilitarEliminarActualizar(botonEliminarPlanTratamiento, botonActualizarPlanTratamiento);
            
          
            
            
        } else {
            servicioPaciente.
                    vaciarListaPlan().
                    visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                    ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                    habilitarCajas(VariablesEstaticas.cajasPlanes).
                    vaciarValorChoise(VariablesEstaticas.choisePlan).
                    vaciarCajas(VariablesEstaticas.cajasPlanes).
                    habilitarBotonCrear(botonAgregarPlanTratamiento).
                    desHabilitarEliminarActualizar(botonEliminarPlanTratamiento, botonActualizarPlanTratamiento);
        }

    }
    
    public void buscarDiagnostico(Paciente pacienteResultado) {
        
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

    }
    
    public void buscarObraSocial(Paciente pacienteResultado){
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
        }
    }

    @FXML
    private void crearSesion(MouseEvent event) {
        
        
        if (!cajaBuscarPaciente.getText().isBlank()) {
            try {
                hbTablasSesionesAtorizaciones.setVisible(false);
                
                
                vBoxAutorizacion.setVisible(true);
                vBoxSesiones.setVisible(true);

                if (!botonAgregarSesiones.getId().equals("1")) {
                    botonRetornarSesiones.setDisable(false);

                    cajaFechaSesion.setValue(LocalDate.now());
                    SesionDAOImplementacion sesionDAOImplementacion = new SesionDAOImplementacion();
                    int ultimaSesion = sesionDAOImplementacion.obtenerultimaSesion(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))) + 1;
                    cajaNumeroSesion.setText(String.valueOf(ultimaSesion));

                    servicioPaciente.deshabilitarBotones(List.of(botonActualizarSesiones, botonEliminarSesiones)).
                            vaciarCajasArea(VariablesEstaticas.cajasAreaSesion).
                            vaciarCajas(VariablesEstaticas.cajasSesiones).
                            vaciarFechas(VariablesEstaticas.datePickerSesiones).
                            vaciarValorChoise(VariablesEstaticas.choiseSesiones);
                    //SI EXISTEN CAJAS PRINCIPALES VACIAS DE SESION
                    botonAgregarSesiones.setId("1");
                    
                } else {
                    botonRetornarSesiones.setDisable(false);
                    if (Objects.isNull(
                            cajaFechaSesion.getValue())
                            || cajaTrabajoSesion.getText().isBlank()) {

                        //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                        mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                        //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                        servicioPaciente.
                                pintarCajaVaciaImportante(VariablesEstaticas.cajasSesiones).
                                pintarCajaAreaVaciaImportante(VariablesEstaticas.cajasAreaSesion);

                    } else {

                        
                        AutorizacionesSesionesObraSociales autorizacionesSesionesObraSociales;
                        
                        servicioPaciente.rellenarCajasAutorizacionVacias();
                        
                        autorizacionesSesionesObraSociales = new AutorizacionesSesionesObraSociales(
                                    Integer.valueOf(cajaAutorizacionSesion.getText()),
                                    cajaObservacionSesionObraSocial.getText(), cajaAsociacionSesionObraSocial.getValue(),
                                    Double.valueOf(cajaCopagoSesionObraSocial.getText()),
                                    new CodigoFacturacion(choiseCodigoFactSesionObraSocial.getValue()));
                        
                        servicioPaciente.
                                datosSesionCajasAreaVacios().datosSesionCajasVacios().datosSesionChoiceVacios();

                        SesionPaciente sesion = new SesionPaciente(Integer.valueOf(cajaNumeroSesion.getText()),
                                cajaFechaSesion.getValue(),
                                cajaTrabajoSesion.getText(),
                                cajaObservacionSesion.getText(),
                                Double.parseDouble(cajaHonorariosPorSesion.getText()),
                                new EstadoFacturacion(cajaEstadoFacturacionSesionObraSocial.getText()));
                        
                        
                        daoImplementacion = new PacienteDAOImplementacion();
                        int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));
                        sesion.setIdPaciente(idPaciente);
                        autorizacionesSesionesObraSociales.setIdPaciente(idPaciente);
                        daoImplementacion = new SesionDAOImplementacion();
                        autorizacionesSesionesObraSociales.setIdSesion(daoImplementacion.obtenerId(sesion));
                        
                        
                        sesion.setAutorizacion(autorizacionesSesionesObraSociales);
                        if (!cajaDniDatosPrincipales.getText().isBlank()) {
                            
                            
                            servicioPaciente.
                                    datosSesionCajasAreaVacios().
                                    datosSesionCajasVacios().
                                    datosAutorizacionSesionVacios();
                            daoImplementacion = new SesionDAOImplementacion();
                            daoImplementacion.insertar(sesion);
                            
                            vBoxSesiones.setVisible(false);
                            vBoxAutorizacion.setVisible(false);
                            hbTablasSesionesAtorizaciones.setVisible(true);
                            buscarPaciente();
                            mensajeAdvertenciaError("Sesion creado con éxito", this, VariablesEstaticas.imgenExito);
                            botonAgregarSesiones.setId("botonAgregarSesiones");
                            servicioPaciente.
                                    habilitarBotones(List.of(botonActualizarSesiones, botonEliminarSesiones));
                        } else {
                            mensajeAdvertenciaError("Buscar paciente para crear sesión", this, VariablesEstaticas.imgenAdvertencia);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensajeAdvertenciaError("Error al crear sesión", this, VariablesEstaticas.imgenError);
            }

        } else {
            mensajeAdvertenciaError("Buscar paciente para crear sesión", this, VariablesEstaticas.imgenAdvertencia);
        }

    }

    @FXML
    private void crearPlanTratamiento(MouseEvent event) {
        
        try {
            //SI PACIENTE FUE BUSCADO
            if (!cajaBuscarPaciente.getText().isBlank()) {
                //CREAR PLAN
                //Paciente pacientePlan = new Paciente();
                TipoSesion ts = new TipoSesion(choiseTipoSesionPlan.getValue(), cajaDescripcionTipoSesionPlan.getText());
                PlanTratamiento planTratamiento = new PlanTratamiento(cajaEstrategiaPlan.getText(), new FrecuenciaSesion(choiseFrecuenciaSesionPlan.getValue()), ts);
                
                daoImplementacion = new PacienteDAOImplementacion();
                planTratamiento.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                
              

                //VALIDAR CAMPOS NECESARIOS
                if (Objects.isNull(choiseFrecuenciaSesionPlan.getValue()) || Objects.isNull(choiseTipoSesionPlan.getValue())) {
                    servicioPaciente.pintarChoiseVacioImportante(VariablesEstaticas.choisePlan);
                    mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                } else {
                    servicioPaciente.datosPlanVacios();
                    daoImplementacion = new PlanTratamientoDAOImplementacion();
                    daoImplementacion.insertar(planTratamiento);
                    mensajeAdvertenciaError("Plan creado con èxito", this, VariablesEstaticas.imgenExito);
                    buscarPaciente();
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para crear plan", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al crear plan de tratamiento", this, VariablesEstaticas.imgenError);
        }

    }

    @FXML
    private void crearDiagnostico(MouseEvent event) {
        
        try {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isBlank()) {
                daoImplementacion = new PacienteDAOImplementacion();
                DiagnosticoPaciente diagnostico = new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText(), daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                //SI DATOS IMPORTANTES TIENEN TEXTO
                if (cajaDiagnosticoDiagnostico.getText().isBlank()) {
                    servicioPaciente.pintarCajaAreaVaciaImportante(VariablesEstaticas.cajasAreaDiagnostico);
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
        }
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
                    ObraSocialPaciente obraSocialPaciente = new ObraSocialPaciente(new Afiliado(Integer.parseInt(cajaNAfiliadoObraSocialPaciente.getText())), choiseNombreObraSocialPaciente.getValue().toString(), new PlanObraSocial(choisePlanesObraSocialPacientePlan.getValue().toString(), "Sin descripcion"));
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
            if(e.getMessage().equals("afiliado existente")){
                mensajeAdvertenciaError(e.getMessage(), this, VariablesEstaticas.imgenError);
            }else{
                mensajeAdvertenciaError("Error al crear obra social del paciente", this, VariablesEstaticas.imgenError);
            }
            
        }
    }
    
    @FXML
    private void actualizarCrearTipoSesionPlaPlan(MouseEvent event){
        Button boton = (Button) event.getSource();
        
        if (boton.getId().equals("botonAgregarPlanTipoSesion")) {
            
            cajaNombreTipoSesionPlan.setVisible(true);
            botonActualizarCrearTipoSesion.setVisible(true);
            botonAgregarPlanTipoSesion.setDisable(true);
            botonActualizarPlanTipoSesion.setDisable(true);
            botonActualizarCrearTipoSesion.setOnMouseClicked(this::insertarTipoPlan);
            choiseTipoSesionPlan.setFocusTraversable(false);
            choiseTipoSesionPlan.setMouseTransparent(true);
            cajaDescripcionTipoSesionPlan.setDisable(false);

            
        } else if (boton.getId().equals("botonActualizarPlanTipoSesion")) {
            if (Objects.nonNull(choiseTipoSesionPlan.getValue())) {
                
                cajaNombreTipoSesionPlan.setVisible(true);
                botonActualizarCrearTipoSesion.setVisible(true);
                botonAgregarPlanTipoSesion.setDisable(true);
                botonActualizarPlanTipoSesion.setDisable(true);
                botonActualizarCrearTipoSesion.setOnMouseClicked(this::actualizarTipoPlan);
                cajaNombreTipoSesionPlan.setText(choiseTipoSesionPlan.getValue());
                choiseTipoSesionPlan.setFocusTraversable(false);
                choiseTipoSesionPlan.setMouseTransparent(true);
                cajaDescripcionTipoSesionPlan.setDisable(false);
                
            } else {
                mensajeAdvertenciaError("Seleccionar tipo sesion para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }

        }
    }

    @FXML
    private void insertarTipoPlan(MouseEvent event) {
        daoImplementacion = new TipoSesionPlanDAOImplementacion();
        try {
            if(!cajaNombreTipoSesionPlan.getText().isBlank()){
                Paciente pacienteTipoSesion = new Paciente();
                PlanTratamiento plan = new PlanTratamiento();
                TipoSesion tipo = new TipoSesion();
                tipo.setNombre(cajaNombreTipoSesionPlan.getText());
                plan.setTipoSEsion(tipo);
                pacienteTipoSesion.setPlanTratamiento(plan);
                
                
                
                
                daoImplementacion.insertar(pacienteTipoSesion);
                
                choiseTipoSesionPlan.getItems().clear();

               
                iniciarChoicePlanTratamiento();
                cajaNombreTipoSesionPlan.setText("");
                
                cajaDescripcionTipoSesionPlan.setDisable(true);
                cajaNombreTipoSesionPlan.setVisible(false);
                botonActualizarCrearTipoSesion.setVisible(false);
                botonAgregarPlanTipoSesion.setDisable(false);
                botonActualizarPlanTipoSesion.setDisable(false);
                
                choiseTipoSesionPlan.setFocusTraversable(true);
                choiseTipoSesionPlan.setMouseTransparent(false);
                
                mensajeAdvertenciaError("Tipo sesión creada con exito", this, VariablesEstaticas.imgenExito);
            }else{
                mensajeAdvertenciaError("Ingresar tipo sesión", this, VariablesEstaticas.imgenAdvertencia);
            }
            
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al crear tipo sesión", this, VariablesEstaticas.imgenError);
        }
        
    }
    
    @FXML
    private void actualizarTipoPlan(MouseEvent event) {
        try {
            TipoSesion tipo = new TipoSesion();
            if (!choiseTipoSesionPlan.getSelectionModel().isEmpty()) {
                tipo.setNombre(choiseTipoSesionPlan.getValue());
                daoImplementacion = new TipoSesionPlanDAOImplementacion();
                tipo.setId(daoImplementacion.obtenerId(tipo));
                tipo.setNombre(cajaNombreTipoSesionPlan.getText());
                tipo.setDecripcion(cajaDescripcionTipoSesionPlan.getText());
                daoImplementacion.actualizar(tipo);
                
                cajaNombreTipoSesionPlan.setVisible(false);
                botonActualizarCrearTipoSesion.setVisible(false);
                botonAgregarPlanTipoSesion.setDisable(false);
                botonActualizarPlanTipoSesion.setDisable(false);
                
                cajaNombreTipoSesionPlan.setText("");
                choiseTipoSesionPlan.setFocusTraversable(true);
                choiseTipoSesionPlan.setMouseTransparent(false);
                cajaDescripcionTipoSesionPlan.setDisable(true);
                
                choiseTipoSesionPlan.getItems().clear();
                choiseTipoSesionPlan.setValue("");
                iniciarChoicePlanTratamiento();
                mensajeAdvertenciaError("Tipo sesión actualizada con exito", this, VariablesEstaticas.imgenExito);
            } else {
                mensajeAdvertenciaError("Seleccionar tipo sesión para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError("Error al actualizar tipo sesion", this, VariablesEstaticas.imgenError);
        }
    }
    
    @FXML
    private void actualizarCrearFrecuenciaPlan(MouseEvent event) {
        Button boton = (Button) event.getSource();
        

        if (boton.getId().equals("botonAgregarPlanFrecuencia")) {
            cajaPlanFrecuenciaSesiones.setVisible(true);
            botonActualizarCrearFrecuencia.setVisible(true);
            botonAgregarPlanFrecuencia.setDisable(true);
            botonActualizarPlanFrecuencia.setDisable(true);

            botonActualizarCrearFrecuencia.setOnMouseClicked(this::insertarFrecuenciaPlan);
            choiseFrecuenciaSesionPlan.setFocusTraversable(false);
            choiseFrecuenciaSesionPlan.setMouseTransparent(true);

        } else if (boton.getId().equals("botonActualizarPlanFrecuencia")) {
            if (Objects.nonNull(choiseFrecuenciaSesionPlan.getValue())) {
                cajaPlanFrecuenciaSesiones.setVisible(true);
                botonActualizarCrearFrecuencia.setVisible(true);
                botonAgregarPlanFrecuencia.setDisable(true);
                botonActualizarPlanFrecuencia.setDisable(true);
                botonActualizarCrearFrecuencia.setOnMouseClicked(this::actualizarFrecuenciaPlan);
                cajaPlanFrecuenciaSesiones.setText(choiseFrecuenciaSesionPlan.getValue());
                choiseFrecuenciaSesionPlan.setFocusTraversable(false);
                choiseFrecuenciaSesionPlan.setMouseTransparent(true);
            } else {
                mensajeAdvertenciaError("Seleccionar frecuencia para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }

        }
    }
    
    
    private void actualizarFrecuenciaPlan(MouseEvent event) {
        try {
            FrecuenciaSesion frecuenciaSesion  = new FrecuenciaSesion();
            if (!cajaPlanFrecuenciaSesiones.getText().isBlank()) {
                frecuenciaSesion.setFrecuencia(choiseFrecuenciaSesionPlan.getValue());
                daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();
                frecuenciaSesion.setIdFrecuencia(daoImplementacion.obtenerId(frecuenciaSesion.getFrecuencia()));
                frecuenciaSesion.setFrecuencia(cajaPlanFrecuenciaSesiones.getText());
                daoImplementacion.actualizar(frecuenciaSesion);
                
                choiseFrecuenciaSesionPlan.getItems().clear();

                iniciarChoiceFrecuencia();
                cajaPlanFrecuenciaSesiones.setVisible(false);
                botonActualizarCrearFrecuencia.setVisible(false);
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
            mensajeAdvertenciaError("Error al actualizar frecuencia", this, VariablesEstaticas.imgenError);
        }

    }

    
    public void insertarFrecuenciaPlan(MouseEvent event) {
        daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();
        
        try {
                if(!cajaPlanFrecuenciaSesiones.getText().isBlank() ){
                    Paciente pacienteTipoSesion = new Paciente();
                    PlanTratamiento plan = new PlanTratamiento();
                    plan.setFrecuenciaSesion(new FrecuenciaSesion(cajaPlanFrecuenciaSesiones.getText()));
                    pacienteTipoSesion.setPlanTratamiento(plan);
                    daoImplementacion.insertar(pacienteTipoSesion);
                    
                    
                    choiseFrecuenciaSesionPlan.getItems().clear();
                    iniciarChoiceFrecuencia();
                    cajaPlanFrecuenciaSesiones.setVisible(false);
                    botonActualizarCrearFrecuencia.setVisible(false);
                    botonAgregarPlanFrecuencia.setDisable(false);
                    botonActualizarPlanFrecuencia.setDisable(false);
                    cajaPlanFrecuenciaSesiones.setText("");
                    choiseFrecuenciaSesionPlan.setFocusTraversable(true);
                    choiseFrecuenciaSesionPlan.setMouseTransparent(false);
                    mensajeAdvertenciaError("Frecuencia creada con exito", this, VariablesEstaticas.imgenExito);
                }else{
                    mensajeAdvertenciaError("Ingresar frecunecia para agregar", this, VariablesEstaticas.imgenAdvertencia);
                }
            } catch (Exception e) {
                mensajeAdvertenciaError("Error al crear frecuencia", this, VariablesEstaticas.imgenError);
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
                int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));
                Paciente paciente = new Paciente();
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
                        daoImplementacion.actualizar(
                                paciente.
                                        setNombre(cajaNombreDatosPrincipales.getText()).
                                        setApellido(cajaApellidoDatosPrincipales.getText()).
                                        setEdad(Integer.parseInt(cajaEdadDatosPrincipales.getText())).
                                        setDni(Integer.parseInt(cajaDniDatosPrincipales.getText())).
                                        setId(idPaciente).
                                        setTelefono(new Telefono(cajaTelefonoDatosPrincipales.getText())).
                                        setHonorarios(new Honorario(Double.parseDouble(cajaHonorariosDatosPrincipales.getText()))));
                        servicioPaciente.deshabilitarCajas(VariablesEstaticas.cajasDatosPrincipales);
                        botonActualizarDatosPrincipales.setId("botonAgregarPlanTratamiento");
                        mensajeAdvertenciaError("Paciente actualizado con éxito", this, VariablesEstaticas.imgenExito);
                        cajaBuscarPaciente.setText(cajaDniDatosPrincipales.getText());
                        buscarPaciente();
                    }

                } else {
                    mensajeAdvertenciaError( "Buscar paciente a actualizar", this, VariablesEstaticas.imgenAdvertencia);
                }

            } catch (Exception e) {
                mensajeAdvertenciaError( "Error al actualizar Paciente", this, VariablesEstaticas.imgenError);
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
        //
        
        
        //SI CAJAS SON EDITABLES
        if (botonActualizarDiagnostico.getId().equals("1")) {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //SI LA CAJA DIAGNOSTICO NO ESTA VACIA
                if (cajaDiagnosticoDiagnostico.getText().isBlank()) {
                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensajeAdvertenciaError( "Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.pintarCajaAreaVaciaImportante(VariablesEstaticas.cajasAreaDiagnostico);
                } else {
                    try {
                        daoImplementacion = new PacienteDAOImplementacion();
                        int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));
                        //PINTAR CAJAS SI ESTAN VACIAS
                        servicioPaciente.datosDiagnosticoVacios();
                        Paciente paciente = new Paciente();
                        //ACTUALIZAR
                        daoImplementacion = new DiagnosticoDAOImplementacion();
                        daoImplementacion.actualizar(new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText(), idPaciente));
                        botonActualizarDiagnostico.setId("botonActualizarDiagnostico");
                        
                        servicioPaciente.deshabilitarCajasArea(VariablesEstaticas.cajasAreaDiagnostico);

                        mensajeAdvertenciaError( "Diagnóstico actualizado con éxito", this, VariablesEstaticas.imgenExito);

                        buscarPaciente();
                    } catch (Exception e) {
                        e.printStackTrace();
                        mensajeAdvertenciaError( "Error al actualizar diagnóstico", this, VariablesEstaticas.imgenError);
                    }
                }
            } else {
                mensajeAdvertenciaError( "Buscar paciente para actualizar", this, VariablesEstaticas.imgenAdvertencia);
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
        
        //SI CAJAS ESTAN HABILITADAS
        if (botonActualizarPlanTratamiento.getId().equals("1")) {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //SI LAS CAJAS IMPORTATES TIENEN VALOR
                if (Objects.isNull(choiseFrecuenciaSesionPlan.getValue()) || Objects.isNull(choiseTipoSesionPlan.getValue())) {
                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensajeAdvertenciaError( "Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.pintarChoiseVacioImportante(VariablesEstaticas.choisePlan);
                //ACTUALIZAR
                } else {
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

                        mensajeAdvertenciaError( "Plan de tratamiento actualizado con éxito", this, VariablesEstaticas.imgenExito);

                        botonActualizarPlanTratamiento.setId("botonAgregarPlanTratamiento");

                        buscarPaciente();

                    } catch (Exception e) {
                        mensajeAdvertenciaError( "Error al actualizar Plan", this, VariablesEstaticas.imgenError);
                    }
                }
            } else {
                mensajeAdvertenciaError( "Buscar paciente a actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } else {
            botonActualizarPlanTratamiento.setId("1");
            servicioPaciente.
                    visibilizarLIstVBox(VariablesEstaticas.vboxsPlanesTratamiento).
                    ocultarLIstVBox(VariablesEstaticas.vboxsPlanesTratamientoActualizaroVer).
                    habilitarCajas(VariablesEstaticas.cajasPlanes).
                    animarCajasAlDarABoton(VariablesEstaticas.cajasPlanes).
                    animarChoiceAlDarABoton(VariablesEstaticas.choisePlan);
        }

    }
    
    
    
    
    
    
    @FXML
    private void actualizarObraSocialPaciente(MouseEvent event) {
        
        //SI CAJAS ESTAN HABILITADAS
        if (botonActualizarObraSocialPaciente.getId().equals("1")) {
            try {
                //SI SE BUSCO UN PACIENTE
                if (!cajaBuscarPaciente.getText().isEmpty()) {
                    //SI LAS CAJAS IMPORTANTES NO ESTAN VACIAS
                    if (Objects.isNull(choiseNombreObraSocialPaciente.getValue())
                            || Objects.isNull(choisePlanesObraSocialPacientePlan.getValue())
                            || cajaNAfiliadoObraSocialPaciente.getText().isEmpty()) {

                        //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                        mensajeAdvertenciaError( "Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                        //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                        servicioPaciente.
                                pintarCajaVaciaImportante(VariablesEstaticas.cajasObraSocialPaciente).
                                pintarChoiseVacioImportante(VariablesEstaticas.choiseObraSocialPaciente);

                    } else {
                        
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
                        

                        //obtner id de anterior obra social y plan
                        daoImplementacion = new AfiliadoDAOImplementacion();
                        obraSocialPaciente.getAfiliado().setId(daoImplementacion.obtenerId(afiliado));
                        
                        daoImplementacion = new PlanObraSocialDAOImplementacion();
                        obraSocialPaciente.getPlan().setId(daoImplementacion.obtenerId(new ObraSocial(obraSocialPaciente.getNombre(), planObraSocial.getNombre())));
                        
                        daoImplementacion = new ObraSocialPacienteDAOImplementacion();
                        obraSocialPaciente.setId(daoImplementacion.obtenerId(obraSocialPaciente));
                        
                        daoImplementacion = new PacienteDAOImplementacion();
                        obraSocialPaciente.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))));
                        

                        //pasar valores nuevos
                        obraSocialPaciente.getPlan().setNombre(choisePlanesObraSocialPacientePlan.getValue());
                        
                        obraSocialPaciente.getAfiliado().setNumero(Integer.parseInt(cajaNAfiliadoObraSocialPaciente.getText()));
                        
                        obraSocialPaciente.setNombre(choiseNombreObraSocialPaciente.getValue());
                        
                        
                        daoImplementacion = new ObraSocialPacienteDAOImplementacion();
                        daoImplementacion.actualizar(obraSocialPaciente);

                        buscarPaciente();
                        servicioPaciente.
                                visibilizarLIstVBox(VariablesEstaticas.vboxsObraSocialPacienteActualizaroVer).
                                ocultarLIstVBox(VariablesEstaticas.vboxsObraSocialPaciente).
                                deshabilitarCajas(VariablesEstaticas.cajasObraSocialPaciente);
                        botonActualizarObraSocialPaciente.setId("botonActualizarObraSocialPaciente");
                    }
                } else {
                    mensajeAdvertenciaError( "Buscar paciente a actualizar", this, VariablesEstaticas.imgenAdvertencia);
                }

            } catch (Exception e) {
                e.printStackTrace();
                mensajeAdvertenciaError( "Error al actualizar obra social del paciente", this, VariablesEstaticas.imgenError);
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

    @FXML
    private void actualizarSesion(MouseEvent event) {
        
        
        try {
            //SI NO SE SELECCIONA LA SESION
            if (tableSesiones.getSelectionModel().isEmpty()) {
                mensajeAdvertenciaError( "Seleccione sesion para pode actualizar", this, VariablesEstaticas.imgenAdvertencia);

            } else {
                LocalDate ldAutorizacion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion());
                LocalDate ldSesion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
                
                SesionPaciente sesion = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
                CodigoFacturacion codigo = new CodigoFacturacion();
                
                SesionPaciente sesionBuscar = new SesionPaciente();
                AutorizacionesSesionesObraSociales autorizacionBuscar = new AutorizacionesSesionesObraSociales();
                
                //SI SE PUEDEN EDITAR LAS CAJAS
                if (botonActualizarSesiones.getId().equals("1")) {
                    botonRetornarSesiones.setDisable(true);
                    
                    daoImplementacion = new PacienteDAOImplementacion();
                    sesionBuscar.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                    autorizacionBuscar.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
                    autorizacionBuscar.setAsociacion(ldAutorizacion);
                    sesionBuscar.setAutorizacion(autorizacionBuscar);
                    sesionBuscar.setFecha(ldSesion);
                    sesionBuscar.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
                    
                    
                    daoImplementacion = new SesionDAOImplementacion();
                    int idSesion = daoImplementacion.obtenerId(sesionBuscar);
                    
                    daoImplementacion = new PacienteDAOImplementacion();
                    int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())));

                    LocalDate ldsNuevo = LocalDate.parse(cajaFechaSesion.getValue().toString());
                    LocalDate ldsaNuevo = LocalDate.parse(cajaAsociacionSesionObraSocial.getValue().toString());
                    
                    //SI LAS CAJAS DE AUTORIZACION ESTAN VACIAS LAS RELLENA CON VALORES NULOS
                    
                    servicioPaciente.rellenarCajasAutorizacionVacias();
                    daoImplementacion = new AutorizacionDAOImplementacion();
                    autorizacion.setIdPaciente(idPaciente);
                    autorizacion.setIdSesion(idSesion);
                    autorizacion.setId(daoImplementacion.obtenerId(new AutorizacionesSesionesObraSociales(Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroAutorizacion()), LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion()), idSesion, idPaciente)));
                    autorizacion.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
                    autorizacion.setAsociacion(ldsaNuevo);
                    autorizacion.setObservacion(cajaObservacionSesionObraSocial.getText());
                    autorizacion.setCopago(Double.parseDouble(cajaCopagoSesionObraSocial.getText()));
                    codigo.setNombre(choiseCodigoFactSesionObraSocial.getValue());
                    autorizacion.setCodigoFacturacion(codigo);
                    
                    
                    
                        
                    sesion.setAutorizacion(autorizacion);
                    //INICIALIZAR SESION
                    sesion.setEstado(new EstadoFacturacion(cajaEstadoFacturacionSesionObraSocial.getText()));
                    sesion.setIdSesion(idSesion);
                    sesion.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
                    sesion.setFecha(ldsNuevo);
                    sesion.setTrabajoSesion(cajaTrabajoSesion.getText());
                    sesion.setObservacion(cajaObservacionSesion.getText());
                    sesion.setHonorarioPorSesion(Double.parseDouble(cajaHonorariosPorSesion.getText()));
                    sesion.setIdPaciente(idPaciente);
                    
                    //ACTIVAR SERVICIOS
                    servicioPaciente.
                            datosSesionCajasAreaVacios().
                            datosSesionCajasVacios().
                            vaciarCajas(VariablesEstaticas.cajasSesiones).
                            vaciarChoise(VariablesEstaticas.choiseSesiones).
                            vaciarCajasArea(VariablesEstaticas.cajasAreaSesion);
                    
                    //ACTUALIZAR
                    daoImplementacion = new SesionDAOImplementacion();
                    daoImplementacion.actualizar(sesion);
                    mensajeAdvertenciaError( "Sesión actualizada con éxito sesión", this, VariablesEstaticas.imgenExito);
                    vBoxAutorizacion.setVisible(false);
                    vBoxSesiones.setVisible(false);
                    hbTablasSesionesAtorizaciones.setVisible(true);
                    botonActualizarSesiones.setId("botonActualizarSesiones");
                    buscarPaciente();
                    servicioPaciente.habilitarBotones(List.of(botonAgregarSesiones, botonEliminarSesiones));
                } else {
                    botonRetornarSesiones.setDisable(false);
                    //BOTONERA
                    hbTablasSesionesAtorizaciones.setVisible(false);
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
                    botonActualizarSesiones.setId("1");
                    servicioPaciente.deshabilitarBotones(List.of(botonAgregarSesiones, botonEliminarSesiones));
                    
                    servicioPaciente.
                            rellenarListaSesionesAutorizaciones(
                                    new AutorizacionesSesionesObraSociales(
                                            Integer.parseInt(tablaAutorizacion.getSelectionModel().getSelectedItem().getNumeroAutorizacion()) ,
                                            tablaAutorizacion.getSelectionModel().getSelectedItem().getObservacionAutorizacion(),
                                            LocalDate.parse(tablaAutorizacion.getSelectionModel().getSelectedItem().getAsociacion()),
                                            Double.parseDouble(tablaAutorizacion.getSelectionModel().getSelectedItem().getCopago()),
                                            new CodigoFacturacion(tablaAutorizacion.getSelectionModel().getSelectedItem().getNombreCodigo())
                                    ));
                    
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError( "Error al actualizar sesión", this, VariablesEstaticas.imgenError);
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
        mensajePreguntarSiONo();
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
        mensajePreguntarSiONo();
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
                                vaciarCajas(VariablesEstaticas.cajasPlanes).
                                habilitarBotonCrear(botonAgregarPlanTratamiento).
                                desHabilitarEliminarActualizar(botonEliminarPlanTratamiento, botonActualizarPlanTratamiento).
                                visivilizarChoice(choisePlan).esconderCajas(cajasPlanes);
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

    @FXML
    private void eliminarDiagnostico(MouseEvent event) {
        mensajePreguntarSiONo();
        if (VariablesEstaticas.esSiONoMensajePrguntarSiONo) {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //SI LAS CAJAS IMPORTANTES NO ESTAN VACIAS
                if (!cajaDiagnosticoDiagnostico.getText().isBlank()) {
                    try {
                        DiagnosticoPaciente diagnosticoPaciente = new DiagnosticoPaciente();
                        daoImplementacion = new PacienteDAOImplementacion();
                        diagnosticoPaciente.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                        daoImplementacion = new DiagnosticoDAOImplementacion();
                        daoImplementacion.eliminar(diagnosticoPaciente);
                        mensajeAdvertenciaError("Diagnóstico eliminado con éxito", this, VariablesEstaticas.imgenExito);
                        servicioPaciente.
                                vaciarCajasArea(VariablesEstaticas.cajasAreaDiagnostico).
                                habilitarBotonCrear(botonAgregarDiagnostico).
                                desHabilitarEliminarActualizar(botonEliminarDiagnostico, botonActualizarDiagnostico);
                        buscarPaciente();
                    } catch (Exception e) {
                        mensajeAdvertenciaError("Error al eliminar diagnóstico", this, VariablesEstaticas.imgenError);
                    }
                } else {
                    mensajeAdvertenciaError("Faltan datos para eliminar diagnóstico", this, VariablesEstaticas.imgenAdvertencia);
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para eliminar", this, VariablesEstaticas.imgenAdvertencia);
            }
        }

    }

    @FXML
    private void eliminarObraSocialPaciente(MouseEvent event) {
        mensajePreguntarSiONo();
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
                                desHabilitarEliminarActualizar(botonEliminarObraSocialPaciente, botonActualizarObraSocialPaciente).
                                visivilizarChoice(choiseObraSocialPaciente).
                                esconderCajas(cajasObraSocialPaciente);
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

                        autorizacionBuscar.setNumeroAutorizacion(Integer.parseInt(tablaAutorizacion.getSelectionModel().getSelectedItem().getNumeroAutorizacion()));
                        autorizacionBuscar.setAsociacion(ldAutorizacion);
                        sesionBuscar.setAutorizacion(autorizacionBuscar);
                        sesionBuscar.setFecha(ldSesion);
                        sesionBuscar.setNumeroSesion(Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroSesion()));

                        daoImplementacion = new SesionDAOImplementacion();
                        int idSesion = daoImplementacion.obtenerId(sesionBuscar);
                        autorizacionBuscar.setIdSesion(idSesion);
                        
                        
                        

                        LocalDate ldsNuevo = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
                        LocalDate ldsaNuevo = LocalDate.parse(tablaAutorizacion.getSelectionModel().getSelectedItem().getAsociacion());

                        daoImplementacion = new AutorizacionDAOImplementacion();
                        int idAutorizacion = daoImplementacion.obtenerId(new AutorizacionesSesionesObraSociales(Integer.parseInt(tableSesiones.getSelectionModel().getSelectedItem().getNumeroAutorizacion()), LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion()), idSesion, idPaciente));
                        autorizacion.setId(idAutorizacion);
                        autorizacion.setNumeroAutorizacion(Integer.parseInt(tablaAutorizacion.getSelectionModel().getSelectedItem().getNumeroAutorizacion()));
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
                                            
                                            daoImplementacion.actualizar(new AutorizacionesSesionesObraSociales(idAutorizacion, 0, "---------", LocalDate.EPOCH, 0.0, codigo, idSesion, idPaciente));
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
                e.printStackTrace();
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
                    }else{
                        mensajeAdvertenciaError( "Error al buscar obra social", this, VariablesEstaticas.imgenError);
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
                }else{
                    mensajeAdvertenciaError( "Error al buscar obra social", this, VariablesEstaticas.imgenError);
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

            }else{
                mensajeAdvertenciaError( "Error al buscar obra social", this, VariablesEstaticas.imgenError);
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
    
    public void buscarObraSocialDesdeCaja() {
        if (!cajaBuscarObraSocial.getText().isBlank()) {

            if (!cajaBuscarObraSocial.getText().equals("0")) {
                ObraSocial obraSocial = new ObraSocial();

                try {
                    daoImplementacion = new ObraSocialDAOImplementacion();
                    obraSocial = (ObraSocial)daoImplementacion.obtener(new ObraSocial(cajaBuscarObraSocial.getText()));
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
                    } else {
                        mensajeAdvertenciaError( "Obra Social no encontrada", this, VariablesEstaticas.imgenAdvertencia);
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
                    mensajeAdvertenciaError( "Error al buscar obra social", this, VariablesEstaticas.imgenError);
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
                mensajeAdvertenciaError( "Obra Social no encontrada", this, VariablesEstaticas.imgenAdvertencia);
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
            mensajeAdvertenciaError( "Obra Social no encontrada", this, VariablesEstaticas.imgenAdvertencia);
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
            if(!cajaNombreObraSocial.getText().isBlank()){
                
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
                botonAgregarPlanesObraSocial.setDisable(false);
                cajaBuscarObraSocial.setText(cajaNombreObraSocial.getText());
                buscarObraSocial(event);
                mensajeAdvertenciaError( "Obra social creada con éxito", this, VariablesEstaticas.imgenExito);
            }else{
                servicioObraSocial.pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                mensajeAdvertenciaError( "Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
             if(e.getClass().equals(Exepciones.class)){
                 mensajeAdvertenciaError( e.getMessage(), this, VariablesEstaticas.imgenError);
             }else{
                 mensajeAdvertenciaError( "Error al crear obra social", this, VariablesEstaticas.imgenError);
             }
              
            
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
                daoImplementacion.insertar(obraSocial);
                cajaAgregarPlanObraSocial.setText("");
                hboxPlanObraSocial.setVisible(false);

                //choiceObraSocial.clear();
                inicializarTableObraSocial();
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
                obraSocial.setPlan(cajaAgregarPlanObraSocial.getText());
                daoImplementacion.actualizar(obraSocial);
                cajaAgregarPlanObraSocial.setText("");
                hboxPlanObraSocial.setVisible(false);

                inicializarTableObraSocial();
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
                    if(!cajaNombreObraSocial.getText().isBlank()){
                        ObraSocial obraSocial = new ObraSocial(
                                VariablesEstaticas.valoresBUsquedaObraSocial.get("1"),
                                new Telefono(VariablesEstaticas.valoresBUsquedaObraSocial.get("2")),
                                new Web(VariablesEstaticas.valoresBUsquedaObraSocial.get("4")),
                                true,
                                new Email(VariablesEstaticas.valoresBUsquedaObraSocial.get("3")));

                        
                        if(!cajaEmailObraSocial.getText().isEmpty()){
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
                            servicioObraSocial.
                                    deshabilitarCajas(VariablesEstaticas.cajasObrasSociales);
                            
                            mensajeAdvertenciaError( "Obra social actualizada con éxito", this, VariablesEstaticas.imgenExito);
                            
                        } else {
                            mensajeAdvertenciaError( "Error al actualizar obra social", this, VariablesEstaticas.imgenError);
                        }
                    }else{
                        mensajeAdvertenciaError( "Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                        servicioPaciente.
                            pintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
                    }

                }else{
                    mensajeAdvertenciaError( "Buscar obra social para actualizar", this, VariablesEstaticas.imgenAdvertencia);
                }
            } else {
                botonActualizarObraSocial.setId("1");
                servicioObraSocial.
                        habilitarCajas(VariablesEstaticas.cajasObrasSociales).
                        animarCajasAlDarABoton(VariablesEstaticas.cajasObrasSociales);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError( "Error al actualizar obra social", this, VariablesEstaticas.imgenError);
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
            mensajeAdvertenciaError( "Ingresar datos para Agregar nuevo código", this, VariablesEstaticas.imgenAdvertencia);
        } else {
            cajaAtualizarNombreCodigoFacturacionSesionObraSocial.setText(choiseCodigoFactSesionObraSocial.getValue());
            cajaAtualizarCodigoFacturacionSesionObraSocial.setText(cajaCodigoFacturacion.getText());
            botonActualizarCodigoFacturacion.setDisable(true);
            botonAgregarCodigoFacturacion.setDisable(true);
            hboxCajasCodigosFacturacion.setVisible(true);
            etiquetaActualizarCodigoFacturacion.setVisible(true);
            hboxEtiquetasCodigosFacturacion.setVisible(true);
            
        }
        
    }
    
    @FXML
    private void agregarCodigoFacturacion(MouseEvent event) {

        try {

            if (!cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText().isBlank() || !cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText().isBlank()) {
                CodigoFacturacion codigoFacturacion = new CodigoFacturacion(cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText(), Integer.parseInt(cajaAtualizarCodigoFacturacionSesionObraSocial.getText()));
                daoImplementacion = new CodigoFacturacionDAOImplementacion();
                daoImplementacion.insertar(codigoFacturacion);
                choiseCodigoFactSesionObraSocial.getItems().clear();
                iniciarChoiceCodigoFacturacion();
                mensajeAdvertenciaError( "Código agregado con éxito", this, VariablesEstaticas.imgenExito);
            }else{
                mensajeAdvertenciaError( "ingresar datos para agregar", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            mensajeAdvertenciaError( "Error al agregar código", this, VariablesEstaticas.imgenError);
        }

        botonActualizarCodigoFacturacion.setDisable(false);
        botonAgregarCodigoFacturacion.setDisable(false);
        hboxCajasCodigosFacturacion.setVisible(false);
        etiquetaActualizarCodigoFacturacion.setVisible(false);
        hboxEtiquetasCodigosFacturacion.setVisible(false);
        
    }
    
    
    @FXML
    private void actualizarCodigoFacturacion(MouseEvent event) {

        try {
            if (!cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText().isBlank() || !cajaAtualizarCodigoFacturacionSesionObraSocial.getText().isBlank()) {
            
            CodigoFacturacion codigoFacturacion = new CodigoFacturacion(choiseCodigoFactSesionObraSocial.getValue(), Integer.parseInt(cajaCodigoFacturacion.getText()));
            
            daoImplementacion = new CodigoFacturacionDAOImplementacion();
            codigoFacturacion.setId(daoImplementacion.obtenerId(codigoFacturacion));
            codigoFacturacion.setNombre(cajaAtualizarNombreCodigoFacturacionSesionObraSocial.getText());
            codigoFacturacion.setCodigo(Integer.parseInt(cajaAtualizarCodigoFacturacionSesionObraSocial.getText()));
            daoImplementacion.actualizar(codigoFacturacion);
            choiseCodigoFactSesionObraSocial.getItems().clear();
            iniciarChoiceCodigoFacturacion();
            mensajeAdvertenciaError( "Código actualizado con éxito", this, VariablesEstaticas.imgenExito);
            }else{
                mensajeAdvertenciaError( "inngresar datos para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        } catch (Exception e) {
            mensajeAdvertenciaError( "Error al actualizar código", this, VariablesEstaticas.imgenError);
        }

        botonAgregarCodigoFacturacion.setDisable(false);
        botonActualizarCodigoFacturacion.setDisable(false);
        hboxCajasCodigosFacturacion.setVisible(false);
        etiquetaActualizarCodigoFacturacion.setVisible(false);
        hboxEtiquetasCodigosFacturacion.setVisible(false);
        cajaCodigoFacturacion.setText("");
       
    }
    
    
    
    
    @FXML
    private void eliminarObraSocial(MouseEvent event) {
        mensajePreguntarSiONo();
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
                    System.out.println(obraSocial.getId());
                    if (Objects.nonNull(obraSocial.getId())) {
                        daoImplementacion.eliminar(obraSocial);
                        
                        inicializarTableObraSocial();
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
    public void vaciarCajasObraSocial(MouseEvent event){
           servicioObraSocial.
                    vaciarListaObraSocial().
                    habilitarCajas(cajasObrasSociales).
                    desHabilitarEliminarActualizar(botonEliminarObraSocial, botonActualizarObraSocial).
                    habilitarBotonCrear(botonAgregarObraSocial).
                    vaciarCajas(VariablesEstaticas.cajasObrasSociales).
                    vaciarChoise(VariablesEstaticas.choiceObraSocial);
           
           cajaBuscarObraSocial.setText("");
           hboxPlanObraSocial.setVisible(false);
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
    public void administraAccion(MouseEvent event) {
        
        try {
            HBox b = (HBox) event.getSource();
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
    
    

    @FXML
    protected void alDarEnterBoton(KeyEvent event){
    Node node = (Node)event.getSource();
        if (event.getCode() == KeyCode.ENTER) {
            switch (node.getId()) {
                case "cajaBuscarPaciente":
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
    protected void vaciarTodasLasCajas(MouseEvent event) {
        cajaBuscarPaciente.setText("");
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
        
        
    }

        
        
       

    
}
