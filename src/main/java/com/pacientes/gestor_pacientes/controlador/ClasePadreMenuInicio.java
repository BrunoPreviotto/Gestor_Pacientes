/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.pacientes.gestor_paciente.CRUD.ObtenerPaciente;
import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.implementacionDAO.AgendaDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.ObraSocialDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.Cliente;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
import com.pacientes.gestor_pacientes.modelo.ObraSocialPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.servicios.ServicioObraSocial;
import com.pacientes.gestor_pacientes.servicios.ServicioPaciente;
import com.pacientes.gestor_pacientes.utilidades.TablaObrasSociales;
import com.pacientes.gestor_pacientes.utilidades.TablaSesiones;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import com.pacientes.gestor_pacientes.validacion.Validar;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author previotto
 */
public class ClasePadreMenuInicio extends ClasePadreController{
    //DENTRO DE CLASE
    protected String cssNuevo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/nuevoTitledPane.css").toExternalForm();
    protected String cssViejo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/menuinicio.css").toExternalForm();
    protected PacienteDAOImplementacion pacienteDao = new PacienteDAOImplementacion();
    
    //protected Paciente paciente;
    protected Validar validar = new Validar();
    protected ServicioPaciente servicioPaciente = new ServicioPaciente();
    protected ServicioObraSocial servicioObraSocial = new ServicioObraSocial();
    
    
    protected Usuario usuario;
    protected UsuarioDAOImplementacion usuarioDao;
    protected ObraSocialDAOImplementacion obraSocialDao;
    protected AgendaDAOImplementacion agendaDao = new AgendaDAOImplementacion();
    
    protected String valorInicialNombreObraSocialPaciente;
    protected List<String> listaPlanesObrasSociales;
    
    
    

    /*protected BiMap<TextInputControl, String> valoresBUsquedaDatosPrincipales = HashBiMap.create();
    protected BiMap<TextInputControl, String> valoresBUsquedaSesiones = HashBiMap.create();
    protected BiMap<TextInputControl, String> valoresBUsquedaPlanes = HashBiMap.create();
    protected BiMap<TextInputControl, String> valoresBUsquedaDiagnostico = HashBiMap.create();
    protected BiMap<TextInputControl, String> valoresBUsquedaObraSocial = HashBiMap.create();*/
    
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                                    PACIENTE
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    */
    
    //ANCHOR PANE
    @FXML
    protected AnchorPane apPacientes;
    
    //CAJAS
    @FXML
    protected TextField cajaBuscarPaciente;
    
    //ACORDION
    @FXML
    protected Accordion acordionPaciente;
    
    
    /*
    
        DATOS PRINCIPALES           DATOS PRINCIPALES           DATOS PRINCIPALES
    
        DATOS PRINCIPALES           DATOS PRINCIPALES           DATOS PRINCIPALES
    
        DATOS PRINCIPALES           DATOS PRINCIPALES           DATOS PRINCIPALES
    
    
    */
    
    //ANCHOR PANE
    @FXML
    protected AnchorPane pestanaPaciente;
    
    
    //TITLEPANE
    @FXML
    protected TitledPane titlePaneDatosPrincipales;
    
    //CAJAS
    @FXML
    protected TextField cajaNombreDatosPrincipales;
    @FXML
    protected TextField cajaApellidoDatosPrincipales;
    @FXML
    protected TextField cajaEdadDatosPrincipales;
    @FXML
    protected TextField cajaDniDatosPrincipales;
    @FXML
    protected TextField cajaTelefonoDatosPrincipales;
    @FXML
    protected TextField cajaHonorariosDatosPrincipales;
    
    //BOTONES
    @FXML
    protected Button botonAgregarDatosPrincipales;
    @FXML
    protected Button botonActualizarDatosPrincipales;
    @FXML
    protected Button botonEliminarDatosPrincipales;
    @FXML
    protected Button botonRetornarDatosPrincipales;
    @FXML
    protected Button botonMaximizarDesmaximizado;
    
    @FXML
    protected HBox botoneraCrudDatosPrincipales;
    
    /*
    
        SESIONES            SESIONES                SESIONES
    
        SESIONES            SESIONES                SESIONES
    
        SESIONES            SESIONES                SESIONES
    
    */
    
    //LABEL
    @FXML
    protected Label etiquetaActualizarCodigoFacturacion;
    
    //VERTICALBOX
    @FXML
    protected VBox vBoxSesiones;
    @FXML
    protected VBox vBoxAutorizacion;
    @FXML
    protected HBox hbTablasSesionesAtorizaciones;
    
    //HORIZONTALBOX
    @FXML
    protected HBox botoneraCrudSesiones;
    @FXML
    protected HBox hboxEtiquetasCodigosFacturacion;
    @FXML
    protected HBox hboxCajasCodigosFacturacion;
    
    //TITLEPANE
    @FXML
    protected TitledPane titlePaneSesion;
    
    //CAJAS
    @FXML
    protected TextField cajaNumeroSesion;
    @FXML
    protected DatePicker cajaFechaSesion;
    @FXML
    protected TextField cajaAutorizacionSesion;
    @FXML
    protected DatePicker cajaAsociacionSesionObraSocial;
    @FXML
    protected TextField cajaCopagoSesionObraSocial;
    @FXML
    protected TextArea cajaTrabajoSesion;
    @FXML
    protected TextArea cajaObservacionSesion;
    
    @FXML
    protected TextArea cajaObservacionSesionObraSocial;
    @FXML
    protected TextField cajaEstadoFacturacionSesionObraSocial;
    @FXML
    protected TextField cajaAtualizarCodigoFacturacionSesionObraSocial;
    @FXML
    protected TextField cajaAtualizarNombreCodigoFacturacionSesionObraSocial;
    @FXML
    protected TextField cajaCodigoFacturacion;
    @FXML
    protected TextField cajaHonorariosPorSesion;
    
    
    
    //BOTONES
    @FXML
    protected Button botonAgregarSesiones;
    @FXML
    protected Button botonActualizarSesiones;
    @FXML
    protected Button botonEliminarSesiones;
    @FXML
    protected Button botonRetornarSesiones;
    @FXML
    protected Button botonActualizarCodigoFacturacion;
    @FXML
    protected Button botonAgregarCodigoFacturacion;
    @FXML
    protected Button botonActualizarAgregarCodigoFacturacion;
    
            
    
    //TABLE
    @FXML
    protected TableView<TablaSesiones> tableSesiones;
    @FXML
    protected TableView<TablaSesiones> tablaAutorizacion;
        //COLUMN
        @FXML
        protected TableColumn<TablaSesiones, String> ColumnaSesionNumero;
        @FXML
        protected TableColumn<TablaSesiones, String> ColumnaSesionFecha;
        @FXML
        protected TableColumn<TablaSesiones, String> ColumnaSesionTrabajo;
        @FXML
        protected TableColumn<TablaSesiones, String> ColumnaSesionObservacion;
        @FXML
        protected TableColumn<TablaSesiones, String> ColumnaSesionHonorarioPorSesion;
        @FXML
        protected TableColumn<TablaSesiones, String> ColumnaSesionEstadoFacturacion;
        @FXML
        protected TableColumn<TablaSesiones, AutorizacionesSesionesObraSociales> columnaAutorizacionNumero;
        @FXML
        protected TableColumn<TablaSesiones, String> columnaAutorizacionObservacion;
        @FXML
        protected TableColumn<TablaSesiones, String> columnaAutorizacionAsociacion;
        @FXML
        protected TableColumn<TablaSesiones, String> columnaAutorizacionCopago;
        @FXML
        protected TableColumn<TablaSesiones, String> columnaAutorizacionCodigoFacturacion;
        @FXML
        protected TableColumn<TablaSesiones, String> columnaNumeroSecionAutorizacion;
        
    //CHOISE
    @FXML
    protected ChoiceBox<String> choiseCodigoFactSesionObraSocial;
    
     /*
    
        PLAN TRATAMIENTO            PLAN TRATAMIENTO            PLAN TRATAMIENTO    
    
        PLAN TRATAMIENTO            PLAN TRATAMIENTO            PLAN TRATAMIENTO
    
        PLAN TRATAMIENTO            PLAN TRATAMIENTO            PLAN TRATAMIENTO
    
    */
    
    //TITLEPANE
    @FXML
    protected TitledPane titlePanePlan;
    
    //CAJAS
    @FXML
    protected TextArea cajaDescripcionTipoSesionPlan;
    @FXML
    protected TextField cajaNombreTipoSesionPlan;
    @FXML
    protected TextField cajaPlanFrecuenciaSesiones;
    @FXML
    protected TextField cajaEstrategiaPlan;
    
    //BOTONES
    @FXML
    protected HBox botoneraCrudPlanTratamiento;
    @FXML
    protected Button botonAgregarPlanFrecuencia;
    @FXML
    protected Button botonAgregarPlanTipoSesion;
    @FXML
    protected Button botonAgregarPlanTratamiento;
    @FXML
    protected Button botonActualizarPlanTratamiento;
    @FXML
    protected Button botonEliminarPlanTratamiento;
    @FXML
    protected Button botonRetornarPlanTratamiento;
    @FXML
    protected Button botonActualizarPlanFrecuencia;
    @FXML
    protected Button botonActualizarPlanTipoSesion;
    
    //CHOISE
    @FXML
    protected ChoiceBox<String> choiseTipoSesionPlan;
    @FXML
    protected ChoiceBox<String> choiseFrecuenciaSesionPlan;
    
    
    /*
    
        DIAGNOSTICO             DIAGNOSTICO             DIAGNOSTICO
    
        DIAGNOSTICO             DIAGNOSTICO             DIAGNOSTICO
    
        DIAGNOSTICO             DIAGNOSTICO             DIAGNOSTICO
    
    */
    
    //TITLEPANE
    @FXML
    protected TitledPane titlePaneDiagnostico;
    
    //CAJAS
    @FXML
    protected TextArea cajaDiagnosticoDiagnostico;
    @FXML
    protected TextArea cajaObservacionDiagnostico;
    
    //BOTONES
    @FXML
    protected HBox botoneraCrudDiagnostico;
    @FXML
    protected Button botonAgregarDiagnostico;
    @FXML
    protected Button botonActualizarDiagnostico;
    @FXML
    protected Button botonEliminarDiagnostico;
    @FXML
    protected Button botonRetornarDiagnostico;
    
    /*
    
        GENOGRAMA           GENOGRAMA           GENOGRAMA           GENOGRAMA
        
        GENOGRAMA           GENOGRAMA           GENOGRAMA           GENOGRAMA
    
        GENOGRAMA           GENOGRAMA           GENOGRAMA           GENOGRAMA
    
    */
    
    //TITLEPANE
    @FXML
    protected TitledPane titlePaneGenograma;
    
    //TABLA
    @FXML
    protected TableView<Cliente> tablaGenograma;
    
    
     /*
    
        OBRA SOCIAL PACIENTE            OBRA SOCIAL PACIENTE            OBRA SOCIAL PACIENTE
    
        OBRA SOCIAL PACIENTE            OBRA SOCIAL PACIENTE            OBRA SOCIAL PACIENTE
    
        OBRA SOCIAL PACIENTE            OBRA SOCIAL PACIENTE            OBRA SOCIAL PACIENTE
    
    */
    
    //LABEL
    @FXML
    protected Label labelAgregarPlan;
    
    //TITLEPANE
    @FXML
    protected TitledPane titlePaneObraSocial;
    
    //CAJAS
    @FXML
    protected TextField cajaNombreObraSocialPaciente;
    @FXML
    protected TextField cajaPlanObraSocialPaciente;
    @FXML
    protected TextField cajaNAfiliadoObraSocialPaciente;
    
    //BOTONES
    @FXML
    protected HBox botoneraCrudObraSocialPaciente;
    @FXML
    protected Button botonAgregarObraSocialPaciente;
    @FXML
    protected Button botonActualizarObraSocialPaciente;
    @FXML
    protected Button botonEliminarObraSocialPaciente;
    @FXML
    protected Button botonRetornarObraSocialPaciente;
    
    //CHOISE
    @FXML
    protected ChoiceBox<String> choiseNombreObraSocialPaciente;
    @FXML
    protected ChoiceBox<String> choisePlanesObraSocialPacientePlan;
    
    @FXML
    protected VBox vbObraSocial;
    
    @FXML
    protected HBox hBoxTablaObraSocial;
    
    
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                                    OBRA SOCIAL
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    */
    
    //ANCHOR PANE
    @FXML
    protected AnchorPane pestanaObraSocial;
    @FXML
    protected AnchorPane apObraSocial;
    
    //CAJAS
    @FXML
    protected TextField cajaNombreObraSocial;
    @FXML
    protected TextField cajaBuscarObraSocial;
    @FXML
    protected TextField cajaVerPlanesObraSocial;
    @FXML
    protected TextField cajaAgregarPlanObraSocial;
    @FXML
    protected TextField cajaTelefonoObraSocial;
    @FXML
    protected TextField cajaEmailObraSocial;
    @FXML
    protected TextField cajaWebObraSocial;
    
    //CHOISE
    @FXML
    protected ChoiceBox<String> choiceVerPlanesObraSocial;
    
    
    //TABLE
    @FXML
    protected TableView<TablaObrasSociales> tablaObraSocial;
        //COLUMN
        @FXML
        protected TableColumn<TablaObrasSociales, String> ColumnaObraSocialNombre;
        @FXML
        protected TableColumn<TablaObrasSociales, String> ColumnaObraSocialTelefono;
        @FXML
        protected TableColumn<TablaObrasSociales, String> ColumnaObraSocialMail;
        @FXML
        protected TableColumn<TablaObrasSociales, String> ColumnaObraSocialWeb;
        @FXML
        protected TableColumn<TablaObrasSociales, String> ColumnaObraSocialPlanes;
        
    
    
    
    //BOTONES
    @FXML
    protected Button botonAgregarPlanesObraSocial;
    @FXML
    protected Button botonActualizarPlanesObraSocial;
    @FXML
    protected Button botonAgregarObraSocial;
    @FXML
    protected Button botonActualizarObraSocial;
    @FXML
    protected Button botonEliminarObraSocial;
    @FXML
    protected Button botonRetornarObraSocial;
    @FXML
    protected HBox botoneraCRUDObraSocial;
    
    
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                                    USUARIO
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    */
    
    //ETIQUETAS
    protected Label labelNombreDeUsuario;
    @FXML
    protected Label labelNombreDeUsuario4;
    @FXML
    protected Label labelNombreDeUsuario3;
    @FXML
    protected Label labelNombreDeUsuario2;
    @FXML
    protected Label labelNombreDeUsuario1;
    
    
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                                    OPCIONES
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    */
    
    //ANCHOR PANE
    @FXML
    protected AnchorPane pestanaOpciones;
    @FXML
    protected AnchorPane apOpciones;
    @FXML
    protected Button botonCerrarSesion;
    @FXML
    protected VBox vbApOpciones;
    
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                                    AGENDA
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    */
    
    
            
            
    //ANCHOR PANE
    @FXML
    protected AnchorPane pestanaAgenda;
    @FXML
    protected AnchorPane apAgendaPrincipal;
    @FXML
    protected VBox apAgendaAgenda;
    
   //GRIDPANE
   @FXML
   protected GridPane gpCalendario;
   
   
    /*
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                                    CONTAINER PRINCIPAL
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    */
    
    //ANCHOR PANE
    @FXML
    protected AnchorPane container;
    @FXML
    protected AnchorPane anchorPanePrincipal;
    @FXML
    protected VBox vbLateral;
    @FXML
    protected AnchorPane apOpcionesUsuario;
    @FXML
    protected AnchorPane apOpcionesOpciones;
    @FXML
    protected TabPane tabPaneOpciones;
    @FXML
    protected AnchorPane apTodosLOsAPInicial;
    
    
    
    
    
    
    
    /***
     * SELECCIONAR CADA UNA DE LAS OPCIONES DEL MENU
     * @param event 
     */
    @FXML
    protected void eleccionMenu(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        switch (pane.getId()) {
            case "pestanaPaciente":

                /*TranslateTransition ttdp = new TranslateTransition(Duration.seconds(1), titlePaneDatosPrincipales);
                ttdp.setFromX(-1000);
                ttdp.setToX(0);
                ScaleTransition stdp = new ScaleTransition(Duration.seconds(1), titlePaneDatosPrincipales);

                stdp.setFromX(0);
                stdp.setToX(1);

                TranslateTransition tts = new TranslateTransition(Duration.seconds(1.5), titlePaneSesion);
                tts.setFromX(-1000);
                tts.setToX(0);
                ScaleTransition sts = new ScaleTransition(Duration.seconds(1.5), titlePaneSesion);
                sts.setFromX(0);
                sts.setToX(1);

                TranslateTransition ttpt = new TranslateTransition(Duration.seconds(2), titlePanePlan);
                ttpt.setFromX(-1000);
                ttpt.setToX(0);
                ScaleTransition stpt = new ScaleTransition(Duration.seconds(2), titlePanePlan);
                stpt.setFromX(0);
                stpt.setToX(1);

                TranslateTransition ttd = new TranslateTransition(Duration.seconds(2.5), titlePaneDiagnostico);
                ttd.setFromX(-1000);
                ttd.setToX(0);
                ScaleTransition std = new ScaleTransition(Duration.seconds(2.5), titlePaneDiagnostico);
                std.setFromX(0);
                std.setToX(1);

                TranslateTransition ttg = new TranslateTransition(Duration.seconds(3), titlePaneGenograma);
                ttg.setFromX(-1000);
                ttg.setToX(0);
                ScaleTransition stg = new ScaleTransition(Duration.seconds(3), titlePaneGenograma);
                stg.setFromX(0);
                stg.setToX(1);

                TranslateTransition ttos = new TranslateTransition(Duration.seconds(3.5), titlePaneObraSocial);
                ttos.setFromX(-1000);
                ttos.setToX(0);
                ScaleTransition stos = new ScaleTransition(Duration.seconds(3.5), titlePaneObraSocial);
                stos.setFromX(0);
                stos.setToX(1);

                ParallelTransition pt = new ParallelTransition(ttdp, ttpt, tts, ttd, ttg, ttos, stdp, stpt, sts, std, stg, stos);
                pt.play();*/

                apPacientes.setVisible(true);
                apObraSocial.setVisible(false);
                apAgendaPrincipal.setVisible(false);
                apOpciones.setVisible(false);
                break;
            case "pestanaObraSocial":
                
                apObraSocial.setVisible(true);
                apPacientes.setVisible(false);
                apAgendaPrincipal.setVisible(false);
                apOpciones.setVisible(false);
                break;
            case "pestanaAgenda":
                apAgendaPrincipal.setVisible(true);
                apObraSocial.setVisible(false);

                apPacientes.setVisible(false);
                apOpciones.setVisible(false);
                break;
            case "pestanaOpciones":
                apOpciones.setVisible(true);
                apAgendaPrincipal.setVisible(false);
                apObraSocial.setVisible(false);
                apPacientes.setVisible(false);
                break;

            default:
                throw new AssertionError();
        }
    }
    
    
    
    protected void blanquearCajas(List<TextField> cajas) {
        for (TextField caja : cajas) {
            caja.setText("");
        }
    }

    protected void setearVisibilidad(Map<Control, Boolean> visibilidades) {
        for (Map.Entry<Control, Boolean> entry : visibilidades.entrySet()) {
            entry.getKey().setDisable(entry.getValue());
        }
    }

    @FXML
    protected void retornarBotonera(MouseEvent event) {
        Button botonRetornar = (Button) event.getSource();
        List<TextField> listaCajas;
        Map<Control, Boolean> botones;
        if (botonRetornar.getId().equals("botonRetornarDatosPrincipales")) {
            listaCajas = Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales);
            botones = ImmutableMap.of(botonRetornarDatosPrincipales, true, botonAgregarDatosPrincipales, false, botonActualizarDatosPrincipales, false, botonEliminarDatosPrincipales, true);
            setearVisibilidad(botones);
            blanquearCajas(listaCajas);
        }

    }

    

    @FXML
    protected void expandirTitledPane(MouseEvent event) {

        TitledPane tp = (TitledPane) event.getSource();

        if (tp.expandedProperty().get() && acordionPaciente.getStylesheets().get(0).equals(cssViejo)) {
            acordionPaciente.getStylesheets().remove(cssViejo);
            acordionPaciente.getStylesheets().add(cssNuevo);
           

        } else if (!tp.expandedProperty().get() && acordionPaciente.getStylesheets().get(0).equals(cssNuevo)) {
            acordionPaciente.getStylesheets().remove(cssNuevo);
            acordionPaciente.getStylesheets().add(cssViejo);
           
        }

    }

   
    

    

    /*@FXML
    protected void cambiarAcrearActualizarBorrar(KeyEvent event) {
        Node accessibleTextCajas = (Node) event.getSource();
        
        List<Boolean> listaCajasLLenasOVacias;
        
        Map <String, Button> listaBotones;
        
        
        switch (accessibleTextCajas.getAccessibleText()) {
            case "datosPrincipales":
                listaCajasLLenasOVacias = Arrays.asList(
                        cajaNombreDatosPrincipales.getText().isEmpty(),
                        cajaApellidoDatosPrincipales.getText().isEmpty(),
                        cajaEdadDatosPrincipales.getText().isEmpty(),
                        cajaDniDatosPrincipales.getText().isEmpty(),
                        cajaTelefonoDatosPrincipales.getText().isEmpty());

                listaBotones = ImmutableMap.of(
                        "agregar", botonAgregarDatosPrincipales,
                        "eliminar", botonEliminarDatosPrincipales,
                        "retornar", botonRetornarDatosPrincipales,
                        "actualizar", botonActualizarDatosPrincipales);

                accionarCambiarAcrearActualizarBorrar(listaCajasLLenasOVacias,
                        listaBotones,
                        valoresBUsquedaDatosPrincipales,
                        event);
                break;
            case "planTratamiento":
                
                listaCajasLLenasOVacias = Arrays.asList(
                        cajaPlanFrecuenciaSesiones.getText().isEmpty(),
                        cajaNombreTipoSesionPlan.getText().isEmpty(),
                        cajaDescripcionTipoSesionPlan.getText().isEmpty(),
                        cajaEstrategiaPlan.getText().isEmpty());

                listaBotones = ImmutableMap.of(
                        "agregar", botonAgregarPlanTratamiento,
                        "eliminar", botonEliminarPlanTratamiento,
                        "retornar", botonRetornarPlanTratamiento,
                        "actualizar", botonActualizarPlanTratamiento);

                accionarCambiarAcrearActualizarBorrar(listaCajasLLenasOVacias,
                        listaBotones,
                        valoresBUsquedaPlanes,
                        event);

                break;
            case "diagnostico":
                
                if(!cajaDiagnosticoDiagnostico.getText().isEmpty()){
                    listaCajasLLenasOVacias = Arrays.asList(
                            cajaDiagnosticoDiagnostico.getText().isEmpty(),
                            cajaObservacionDiagnostico.getText().isEmpty());
                    listaBotones = ImmutableMap.of(
                            "agregar", botonAgregarDiagnostico,
                            "eliminar", botonEliminarDiagnostico,
                            "retornar", botonRetornarDiagnostico,
                            "actualizar", botonActualizarDiagnostico);

                    accionarCambiarAcrearActualizarBorrar(
                            listaCajasLLenasOVacias,
                            listaBotones,
                            valoresBUsquedaDiagnostico,
                            event);
                }
        }
        
        
       
    }*/
    
    public void accionarCambiarAcrearActualizarBorrar(List<Boolean> cajasVaciasOLLenas, Map<String, Button> mapBotones, Map<TextInputControl, String> mapCajas, KeyEvent event){
        TextInputControl caja = (TextInputControl) event.getSource();
        HBox hb = (HBox) caja.getParent();
        VBox vb = (VBox) hb.getParent();
        List<TextInputControl> textFieldCajasObtenidas = new ArrayList();
        
        
        
        boolean verdadero = true;
        for (Boolean c : cajasVaciasOLLenas) {
            
            if(!c){
                verdadero = false;
                break;
            }
        }
        
        if(verdadero){
            for (Map.Entry<String, Button> e : mapBotones.entrySet()) {
                switch (e.getKey()) {
                    case "agregar":
                        e.getValue().setDisable(false);
                        break;
                    case "eliminar":
                        e.getValue().setDisable(true);
                        break;
                    case "retornar":
                        e.getValue().setDisable(true);
                        break;
                }
                
            }
        }
        
         for (Node node : vb.getChildren()) {
            if(node.getTypeSelector().equals("HBox")){
                HBox h = (HBox) node;
                TextInputControl t = (TextInputControl) h.getChildren().get(0);
                textFieldCajasObtenidas.add(t);
            }
            
        }
        
        
        boolean noIgual = false;
        int i = 0;
        for (Map.Entry<TextInputControl, String> entry : mapCajas.entrySet()) {
            
            if(entry.getKey().getId().equals(textFieldCajasObtenidas.get(i).getId())){
                if(!entry.getValue().equals(textFieldCajasObtenidas.get(i).getText())){
                    noIgual = true;
                    break;
                }
                i++;
            }
            
        }
        
        if(noIgual){
            mapBotones.get("actualizar").setDisable(false);
            mapBotones.get("eliminar").setDisable(true);
            
        }else{
            mapBotones.get("actualizar").setDisable(true);
            mapBotones.get("eliminar").setDisable(false);
        }
        
        
    }

   

    protected void cambiarCategoria(ActionEvent event) {
        ChoiceBox cb = (ChoiceBox) event.getSource();
        ObtenerPaciente obtenerDescripcion = new ObtenerPaciente();
        if (cb.getAccessibleText().equals("planTratamiento")) {
            if (Objects.nonNull(choiseTipoSesionPlan.getValue())) {
                if (!choiseTipoSesionPlan.getValue().equals(cajaNombreTipoSesionPlan.getText())) {
                    cajaNombreTipoSesionPlan.setText(choiseTipoSesionPlan.getValue());

                }
            }
        }
        try {
            cajaDescripcionTipoSesionPlan.setText(obtenerDescripcion.obtenerDescripcionTipoSesionPlan(choiseTipoSesionPlan.getValue()));
        } catch (Exception e) {
        }
    }
    
    

    protected void cambiarPlanesObraSocial(ActionEvent event) {
        ChoiceBox cb = (ChoiceBox) event.getSource();
        obraSocialDao = new ObraSocialDAOImplementacion();
        List<String> listaNuevaPlanes = obraSocialDao.obtenerListaPlanesObrasSociales(new ObraSocial(choiseNombreObraSocialPaciente.getValue()));
        if (Objects.nonNull(choiseNombreObraSocialPaciente.getValue())) {
            if (!choiseNombreObraSocialPaciente.getValue().equals(valorInicialNombreObraSocialPaciente)) {
                choisePlanesObraSocialPacientePlan.getItems().setAll(obraSocialDao.obtenerListaPlanesObrasSociales(new ObraSocial(choiseNombreObraSocialPaciente.getValue())));
                valorInicialNombreObraSocialPaciente = choiseNombreObraSocialPaciente.getValue();
            }
        }

    }
    
    
    protected void cambiarCodigoFacturacion(ActionEvent event) {
        ChoiceBox cb = (ChoiceBox) event.getSource();
        try {
           
            cajaCodigoFacturacion.setText(pacienteDao.obtenerStringCodigoFacturacion(cb.getValue().toString()));
        } catch (Exception e) {
        }
    }
    
   
    
     @FXML
    protected void retornarTablaSesion(MouseEvent event) {
        hbTablasSesionesAtorizaciones.setVisible(true);
        botonActualizarSesiones.setDisable(false);
        botonAgregarSesiones.setDisable(false);
        botonEliminarSesiones.setDisable(false);
        botonRetornarSesiones.setDisable(true);
        vBoxAutorizacion.setVisible(false);
        vBoxSesiones.setVisible(false);
        botonActualizarSesiones.setId("botonActualizarSesiones");
        botonAgregarSesiones.setId("botonAgregarSesiones");
    }

    

    

    @FXML
    protected void fucusSesionAutorizacion(MouseEvent event) {
        
        tablaAutorizacion.requestFocus();
        tablaAutorizacion.getSelectionModel().select(tableSesiones.getSelectionModel().getFocusedIndex());
        
    }
    
    
    protected AutorizacionesSesionesObraSociales rellenarAutorizacionVacia(){
        
        AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
        LocalDate ld = LocalDate.parse("1700-01-01");
        CodigoFacturacion codigo = new CodigoFacturacion();
        autorizacion.setId(5);
        autorizacion.setNumeroAutorizacion(0);
        autorizacion.setObservacion("-");
        autorizacion.setAsociacion(ld);
        autorizacion.setCopago(0.0);
        codigo.setCodigo(5);
        autorizacion.setCodigoFacturacion(codigo);
        
        return autorizacion;
    }
    
    
    /***
     * 
     * @param event 
     */
     @FXML
    private void agregarPlan(MouseEvent event) {
        botonAgregarPlanesObraSocial.setDisable(true);
        cajaVerPlanesObraSocial.setDisable(true);
        cajaAgregarPlanObraSocial.setDisable(false);
        botonActualizarPlanesObraSocial.setDisable(false);
    }
    
     
    
    
    public BiMap<List<CheckBox>, Boolean> mensajeEliminarSesion(Object obj){
        try {
            BiMap<List<CheckBox>, Boolean> valoresElimenarSesion = HashBiMap.create();
            FXMLLoader Loader = new FXMLLoader(App.class.getResource( "EliminarSesion.fxml"));
            Parent root = Loader.load();
            EliminarSesionController controller = Loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            valoresElimenarSesion = controller.mensajeEliminarSesion(stage, obj);
            stage.showAndWait();
            return valoresElimenarSesion;
            
            
           
        
        } catch (IOException ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @FXML
     private void onKeyTyped(KeyEvent event){
         Control control = (Control) event.getSource();
         switch (control.getAccessibleRoleDescription()) {
             case "num":
                 soloNumero(event);
                 break;
             
         }
         if(
                 control.getId().equals("cajaEdadDatosPrincipales") || 
                 control.getId().equals("cajaDniDatosPrincipales") ||
                 control.getId().equals("cajaTelefonoDatosPrincipales")){
             
             comprobarTamañoStringDatosPrincipales(event);
         }
     }
     
     @FXML
     protected void cerrarSesionUsuario(MouseEvent event){
         try {
            
           usuarioDao.cerrarSesion();
            
            Node source = (Node) event.getSource();
            Stage stageg = (Stage) source.getScene().getWindow();
            stageg.close();
            
            
            
            
            FXMLLoader Loader = new FXMLLoader(App.class.getResource( "IniciarSesion.fxml"));
            Parent root = Loader.load();
            IniciarSesionController controller = Loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
           
            stage.showAndWait();
            
            
            /*App reabrir = new App();
            Stage newPrimaryStage = new Stage();
            
            reabrir.start(newPrimaryStage);*/
             
            
             
         } catch (Exception e) {
         }
     }
    
    
    @FXML
    public void comprobarTamañoStringDatosPrincipales(KeyEvent event) {
        TextField tf = (TextField) event.getSource();

        switch (tf.getId()) {
            case "cajaNombreDatosPrincipales":

                break;
            case "cajaApellidoDatosPrincipales":

                break;
            case "cajaEdadDatosPrincipales":
                if (event.getCharacter().matches("[0-9]")) {
                    int num = Integer.parseInt(tf.getText());
                    if (num > 150) {
                        tf.setText("150");
                    }
                }

                break;
            case "cajaDniDatosPrincipales":
                
                if (event.getCharacter().matches("[0-9]")) {
                    
                    if (tf.getText().length() == 15) {
                        tf.deletePreviousChar();
                    }
                }

                break;
            case "cajaTelefonoDatosPrincipales":
                if (event.getCharacter().matches("[0-9]")) {
                    
                    if (tf.getText().length() == 20) {
                        tf.deletePreviousChar();
                    }
                }
                break;

        }

    }
    
    protected void inicializarTableObraSocial(){
        try {
            //Inicializar lista de obras sociales
            List<ObraSocial> listaObraSocial = new ArrayList();
            listaObraSocial = obraSocialDao.obtenerLista();
            ObservableList<TablaObrasSociales> olObraSocial = FXCollections.observableArrayList();
            if (Objects.nonNull(listaObraSocial)) {

                for (ObraSocial o : listaObraSocial) {
                    olObraSocial.add(new TablaObrasSociales(
                            o.getNombre(),
                            o.getTelefono().getTelefono(),
                            o.getEmail().getEmail(),
                            o.getWeb().getWeb(),
                            o.setListaPlanesToString().getListaPlanesToString()));
                }

                tablaObraSocial.setItems(olObraSocial);

                ColumnaObraSocialNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                ColumnaObraSocialTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
                ColumnaObraSocialMail.setCellValueFactory(new PropertyValueFactory<>("email"));
                ColumnaObraSocialWeb.setCellValueFactory(new PropertyValueFactory<>("web"));
                ColumnaObraSocialPlanes.setCellValueFactory(new PropertyValueFactory<>("planes"));

        }
        } catch (Exception e) {
        }
    }
    
    
    @FXML
    protected void maximizarMenuInicio(MouseEvent event) {
        
        if(botonMaximizarDesmaximizado.getId().equals("botonMaximizarDesmaximizado")){
            maximizar(event);
            //container.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            //container.setMinSize(Double.MIN_NORMAL, Double.MIN_NORMAL);
            botonMaximizarDesmaximizado.setId("botonMaximizarMaximizado");
            diseñoMaximizar();
                    
        }else{
            //container.setMaxSize(1200, 700);
            //container.setMinSize(1100, 650);
            botonMaximizarDesmaximizado.setId("botonMaximizarDesmaximizado");
            desMaximizar(event);
            diseñoDesmaximizar();
        }
    }
    
    
    
    public void diseñoMaximizar(){
        /*acordionPaciente.setPrefSize(1400, 800);
        apObraSocial.setPrefSize(1500, 800);
        anchorPanePrincipal.setPrefSize(1500, 800);
        apAgendaPrincipal.setPrefSize(1500, 800);
        apOpciones.setPrefSize(1500, 800);
        apPacientes.setPrefSize(1500, 800);
        apOpcionesUsuario.setPrefSize(1500, 800);
        apOpcionesOpciones.setPrefSize(1500, 800);
        tabPaneOpciones.setPrefSize(1500, 800);
        apAgendaAgenda.setPrefSize(1500, 800);
        gpCalendario.setPrefSize(1400, 690);
        vbObraSocial.setPrefSize(1500, 800);
        hBoxTablaObraSocial.setPrefHeight(420);
        vbLateral.setPrefSize(100, 800);*/
        
        apTodosLOsAPInicial.setPrefSize(1500, 800);
        
        
        hBoxTablaObraSocial.setPrefHeight(420);
        
        
    }
    
    public void diseñoDesmaximizar(){
         apTodosLOsAPInicial.setPrefSize(1100, 650);
        
        hBoxTablaObraSocial.setPrefHeight(200);
        
        
    }
    
}
