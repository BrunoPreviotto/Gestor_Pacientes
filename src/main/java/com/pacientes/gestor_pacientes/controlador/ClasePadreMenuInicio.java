/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.jfoenix.controls.JFXTextArea;
import com.pacientes.gestor_pacientes.App;

import com.pacientes.gestor_pacientes.implementacionDAO.AgendaDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial.ObraSocialDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial.PlanObraSocialDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.CodigoFacturacionDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.TipoSesionPlanDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.Cliente;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
import com.pacientes.gestor_pacientes.modelo.TipoSesion;

import com.pacientes.gestor_pacientes.servicios.ServicioObraSocial;
import com.pacientes.gestor_pacientes.servicios.ServicioPaciente;
import com.pacientes.gestor_pacientes.servicios.ServiciosPadre;
import com.pacientes.gestor_pacientes.utilidades.TablaObrasSociales;
import com.pacientes.gestor_pacientes.utilidades.TablaSesiones;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.cajasObrasSociales;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.usuario;
import com.pacientes.gestor_pacientes.validacion.Validar;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 *
 * @author previotto
 */
public class ClasePadreMenuInicio extends ClasePadreController{
    
     public Timeline enableTimeline;
    
    @FXML
    protected AnchorPane anchorBurbujas;
    
    
    protected ServiciosPadre servicioPadre = new ServiciosPadre();

    //DENTRO DE CLASE
    protected String cssNuevo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/nuevoTitledPane.css").toExternalForm();
    protected String cssViejo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/menuinicio.css").toExternalForm();
    
    
    //protected Paciente paciente;
    protected Validar validar = new Validar();
    protected ServicioPaciente servicioPaciente = new ServicioPaciente();
    protected ServicioObraSocial servicioObraSocial = new ServicioObraSocial();
    
    
    protected UsuarioDAOImplementacion usuarioDao;
    protected AgendaDAOImplementacion agendaDao = new AgendaDAOImplementacion();
    
    
    
   
   
    
    protected String valorInicialNombreObraSocialPaciente;
    protected List<ObraSocial> listaPlanesObrasSociales;
    
    protected static Timer timer;
    protected static TimerTask tarea;

    public static Timer getTimer() {
        return timer;
    }

    public static TimerTask getTarea() {
        return tarea;
    }
    
    
    
    

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
    protected Button botonMinimizar;
    
    @FXML
    protected Button botonCerrar;
    
    @FXML
    protected HBox botoneraCrudDatosPrincipales;
    
    /*
    
        SESIONES            SESIONES                SESIONES
    
        SESIONES            SESIONES                SESIONES
    
        SESIONES            SESIONES                SESIONES
    
    */
    
    @FXML
    protected HTMLEditor htmlTrabajoSesion;
    @FXML
    protected HTMLEditor htmlObservacionSesion;
    @FXML
    protected HTMLEditor htmlObservacionAutorizacion;
    
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
    protected TextField cajaHonorariosPorSesion;
    @FXML
    protected TextField cajaEstadoFacturacionSesionObraSocial;
    
    @FXML
    protected TextField cajaAutorizacionSesion;
    @FXML
    protected DatePicker cajaAsociacionSesionObraSocial;
    
    @FXML
    protected TextField cajaAtualizarCodigoFacturacionSesionObraSocial;
    @FXML
    protected TextField cajaAtualizarNombreCodigoFacturacionSesionObraSocial;
    @FXML
    protected TextField cajaCodigoFacturacion;
    @FXML
    protected TextField cajaCopagoSesionObraSocial;
   
   
    
    
    
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
    @FXML
    protected Button botonVerTrabajoSesion;
    @FXML
    protected Button botonVerObservacionSesion;
    @FXML
    protected Button botonVerObservacionAutorizacion;
    
            
    
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
    //LABEL
    @FXML
    protected Label etiquetaNombreTipoSEsionPlan;
    @FXML
    protected Label etiquetaFrecuenciaSesionPlan;
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
    @FXML
    protected Button botonActualizarCrearFrecuencia;
    @FXML
    protected Button botonActualizarCrearTipoSesion;
    
    //CHOISE
    @FXML
    protected ChoiceBox<String> choiseTipoSesionPlan;
    @FXML
    protected ChoiceBox<String> choiseFrecuenciaSesionPlan;
    
    //VBOX
    @FXML
    protected VBox vBoxFrecuenciaSEsionPlan;
    @FXML
    protected VBox vBoxNombreTipoSEsionPlan;
    @FXML
    protected VBox vBoxFrecuenciaSEsionPlanActualizaroVer;
    @FXML
    protected VBox vBoxNombreTipoSEsionPlanActualizaroVer;
    
    /*
    
        DIAGNOSTICO             DIAGNOSTICO             DIAGNOSTICO
    
        DIAGNOSTICO             DIAGNOSTICO             DIAGNOSTICO
    
        DIAGNOSTICO             DIAGNOSTICO             DIAGNOSTICO
    
    */
    
    //HBOX
    @FXML
    protected HBox hbHtmlEditObsevacionDiagnostico;
    @FXML
    protected HBox hbHtmlEditDiagnostico;
    
    @FXML
    protected HTMLEditor htmlDiagnostico;
    
    @FXML
    protected HTMLEditor htmlObservacionDiagnostico;
    
    //TITLEPANE
    @FXML
    protected TitledPane titlePaneDiagnostico;
    
    //CAJAS
    /*@FXML
    protected JFXTextArea cajaDiagnosticoDiagnosticoJFX;*/
    /*@FXML
    protected TextArea cajaDiagnosticoDiagnostico;*/
    /*@FXML
    protected TextArea cajaObservacionDiagnostico;*/
    
   /* @FXML
    protected JFXTextArea cajaObservacionDiagnosticoJFX;*/
    
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
    @FXML
    protected Button botonVerDiagnostico;
    @FXML
    protected Button botonVerObservacionDiagnostico;
    
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
    
     //VBOX
    @FXML
    protected VBox vboxNombreObraSocialPaciente;
    @FXML
    protected VBox vboxNombreObraSocialPacienteActualizarVer;
    @FXML
    protected VBox vboxPlanObraSocialPaciente;
    @FXML
    protected VBox vboxPlanObraSocialPacienteActualizarVer;
    
    /***
     * 
     * 
     * 
     * GENOGRAMA
     * 
     * 
     * 
     * 
     * 
     */
    
    @FXML
    protected AnchorPane apGenograma;
    
    
    
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
    protected Button botonCrearActualizarPlanesObraSocial;
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
    
    //HBOX
    @FXML
    protected HBox hboxPlanObraSocial;
    
    
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
    @FXML
    protected Label etiquetaNombreInicio;
    
    
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
    
    //CAJAS
    @FXML
    protected TextField cajaNombreOpcionesUsuario;
    @FXML
    protected TextField cajaApellidoOpcionesUsuario;
    @FXML
    protected TextField cajaUusarioOpcionesUsuario;
    @FXML
    protected TextField cajaContraseñaOpcionesUsuario;
    @FXML
    protected TextField cajaEmailOpcionesUsuario;
    
    //ANCHOR PANE
    @FXML
    protected AnchorPane pestanaOpciones;
    @FXML
    protected AnchorPane apOpciones;
    @FXML
    protected Button botonCerrarSesion;
    @FXML
    protected VBox vbApOpciones;
    
    @FXML
    protected HBox hboxColores;
    
    @FXML
    protected Button botonColorVerde;
    
    @FXML
    protected Button botonColorAzul;
    
    @FXML
    protected Button botonColorMarron;
    
    @FXML
    protected Button botonColorGris;
    
    @FXML
    protected Button botonColorSalmon;
    
    //BOTON
    @FXML
    protected Button botonActualizarUsuarioOpciones;
    
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
    
    
    @FXML
    protected AnchorPane imagenOjoVer; 
            
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
    protected HBox hboxcontainer;
    @FXML
    protected AnchorPane anchorPanePrincipal;
    @FXML
    protected AnchorPane vbLateral;
    @FXML
    protected AnchorPane apOpcionesUsuario;
    @FXML
    protected AnchorPane apOpcionesOpciones;
    @FXML
    protected TabPane tabPaneOpciones;
    @FXML
    protected AnchorPane apTodosLOsAPInicial;
    @FXML
    protected AnchorPane containerMenu;
    @FXML
    protected ImageView imgRecordatorio;
    
    
    
    
    
    
    
    
    /***
     * SELECCIONAR CADA UNA DE LAS OPCIONES DEL MENU
     * @param event 
     */
    @FXML
    protected void eleccionMenu(MouseEvent event) {
        containerMenu.setStyle(servicioPadre.iniciarColorApp());
        
        Pane pane = (Pane) event.getSource();
        switch (pane.getId()) {
            case "pestanaPaciente":
                apPacientes.setVisible(true);
                apObraSocial.setVisible(false);
                apAgendaPrincipal.setVisible(false);
                apOpciones.setVisible(false);
                vaciarPaciente();
                break;
            case "pestanaObraSocial":
                
                apObraSocial.setVisible(true);
                apPacientes.setVisible(false);
                apAgendaPrincipal.setVisible(false);
                apOpciones.setVisible(false);
                vaciarPaciente();
                break;
            case "pestanaAgenda":
                apAgendaPrincipal.setVisible(true);
                apObraSocial.setVisible(false);

                apPacientes.setVisible(false);
                apOpciones.setVisible(false);
                vaciarPaciente();
                break;
            case "pestanaOpciones":
                apOpciones.setVisible(true);
                apAgendaPrincipal.setVisible(false);
                apObraSocial.setVisible(false);
                apPacientes.setVisible(false);
                vaciarPaciente();
                break;

            default:
                throw new AssertionError();
        }
    }
    
    protected void blanquearCajas(){
        servicioPaciente.
                desPintarCajaVaciaImportante(VariablesEstaticas.cajasDatosPrincipales).
                desPintarCajaVaciaImportante(VariablesEstaticas.cajasObraSocialPaciente).
                desPintarCajaVaciaImportante(VariablesEstaticas.cajasPlanes).
                desPintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaDiagnostico).
                desPintarCajaAreaVaciaImportante(VariablesEstaticas.cajasAreaPlan).
                desPintarCajaVaciaImportante(VariablesEstaticas.cajasObrasSociales);
    }
    
    protected void vaciarPaciente(){
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
        
       // vBoxSesiones.setVisible(false);
                   // vBoxAutorizacion.setVisible(false);
                    hbTablasSesionesAtorizaciones.setVisible(true);
                    cajaBuscarPaciente.setText("");
                    setearBotones();
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
    
    protected void setearBotones(){
        botonAgregarDatosPrincipales.setId("botonAgregarDatosPrincipales");
        botonActualizarDatosPrincipales.setId("botonActualizarDatosPrincipales");
        botonEliminarDatosPrincipales.setId("botonEliminarDatosPrincipales");
        
        botonAgregarSesiones.setId("botonAgregarSesiones");
        botonActualizarSesiones.setId("botonActualizarSesiones");
        botonEliminarSesiones.setId("botonEliminarSesiones");
        
        botonAgregarPlanTratamiento.setId("botonAgregarPlanTratamiento");
        botonActualizarPlanTratamiento.setId("botonActualizarPlanTratamiento");
        botonEliminarPlanTratamiento.setId("botonEliminarPlanTratamiento");
        
        
        
        botonEliminarDiagnostico.setId("botonEliminarDiagnostico");
        
        botonActualizarObraSocialPaciente.setId("botonActualizarObraSocialPaciente");
        botonAgregarObraSocialPaciente.setId("botonAgregarObraSocialPaciente");
        botonEliminarObraSocialPaciente.setId("botonEliminarObraSocialPaciente");
        botonEliminarObraSocialPaciente.setId("botonEliminarObraSocialPaciente");
        
        //botonActualizarAgregarCodigoFacturacion.setId("botonActualizarAgregarCodigoFacturacion");
        
        //botonAgregarCodigoFacturacion.setId("botonAgregarCodigoFacturacion");
       // botonActualizarCodigoFacturacion.setId("botonActualizarCodigoFacturacion");
        
        botonActualizarCrearFrecuencia.setId("botonActualizarCrearFrecuencia");
        
        botonActualizarCrearTipoSesion.setId("botonActualizarCrearTipoSesion");
        
        botonAgregarObraSocial.setId("botonAgregarObraSocial");
        botonActualizarObraSocial.setId("botonActualizarObraSocial");
        botonEliminarObraSocial.setId("botonEliminarObraSocial");
        
        botonAgregarPlanFrecuencia.setId("botonAgregarPlanFrecuencia");
        botonActualizarPlanFrecuencia.setId("botonActualizarPlanFrecuencia");
        
        botonAgregarPlanTipoSesion.setId("botonAgregarPlanTipoSesion");
        botonActualizarPlanTipoSesion.setId("botonActualizarPlanTipoSesion");
        
        botonAgregarPlanesObraSocial.setId("botonAgregarPlanesObraSocial");
        botonActualizarPlanesObraSocial.setId("botonActualizarPlanesObraSocial");
        
        botonActualizarUsuarioOpciones.setId("botonActualizarUsuarioOpciones");
        
        
               
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
        if (cb.getAccessibleText().equals("planTratamiento")) {
            if (Objects.nonNull(choiseTipoSesionPlan.getValue())) {
                if (!choiseTipoSesionPlan.getValue().equals(cajaNombreTipoSesionPlan.getText())) {
                    cajaNombreTipoSesionPlan.setText(choiseTipoSesionPlan.getValue());

                }
            }
        }
        try {
            TipoSesion tipo = new TipoSesion();
            tipo.setNombre(choiseTipoSesionPlan.getValue());
            daoImplementacion = new TipoSesionPlanDAOImplementacion();
            TipoSesion tipoResultado = (TipoSesion)daoImplementacion.obtener(tipo);
            cajaDescripcionTipoSesionPlan.setText(tipoResultado.getDecripcion());
        } catch (Exception e) {
        }
    }
    
    

    protected void cambiarPlanesObraSocial(ActionEvent event) {
        try {
            ChoiceBox cb = (ChoiceBox) event.getSource();
            daoImplementacion = new PlanObraSocialDAOImplementacion();
            ObraSocialDAOImplementacion obraSocialDao = new ObraSocialDAOImplementacion();
            List<String> listaNuevaPlanes = planesAString(daoImplementacion.obtenerLista(new ObraSocial( obraSocialDao.obtenerIdConUsuario(new ObraSocial(choiseNombreObraSocialPaciente.getValue())),choiseNombreObraSocialPaciente.getValue() )));
           
            if (Objects.nonNull(choiseNombreObraSocialPaciente.getValue())) {
                if (!choiseNombreObraSocialPaciente.getValue().equals(valorInicialNombreObraSocialPaciente)) {
                    choisePlanesObraSocialPacientePlan.getItems().setAll(listaNuevaPlanes);
                    valorInicialNombreObraSocialPaciente = choiseNombreObraSocialPaciente.getValue();
                }
            }
        } catch (Exception e) {
        }
        //daoImplementacion.obtenerLista(new ObraSocial(choiseNombreObraSocialPaciente.getValue()))
    }
    
    public List<String> planesAString(List<ObraSocial> listaObraSocial){
        List<String> lista = new ArrayList();
            try {
                for (ObraSocial obraSocial : listaObraSocial) {
                    lista.add(obraSocial.getPlan());
                }
            } catch (Exception e) {
            }
            return lista;
    }
    
    
    protected void cambiarCodigoFacturacion(ActionEvent event) {
        ChoiceBox cb = (ChoiceBox) event.getSource();
        try {
            daoImplementacion = new CodigoFacturacionDAOImplementacion();
            CodigoFacturacion codigoFacturacion = (CodigoFacturacion)daoImplementacion.obtener(new CodigoFacturacion(cb.getValue().toString()));
            cajaCodigoFacturacion.setText(codigoFacturacion.getCodigo().toString());
            
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
        //vBoxAutorizacion.setVisible(false);
        //vBoxSesiones.setVisible(false);
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
     protected void onKeyTyped(KeyEvent event){
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
           
            
        
            stage.getIcons().add(imagenIocono);
            
            
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
            daoImplementacion = new ObraSocialDAOImplementacion();
            listaObraSocial = daoImplementacion.obtenerLista(new ObraSocial());
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
        vbLateral.setPrefSize(100, Region.USE_COMPUTED_SIZE);
        hBoxTablaObraSocial.setPrefHeight(420);
        
    }
    
    public void diseñoDesmaximizar(){
        //apTodosLOsAPInicial.setPrefSize(1100, 650);
        vbLateral.setPrefSize(100, Region.USE_COMPUTED_SIZE);
        hBoxTablaObraSocial.setPrefHeight(200);
        
        
    }
    
    /*@FXML
    public void agrandarCajaParaVer(MouseEvent event){
        /*try {
            
            FXMLLoader Loader = new FXMLLoader(App.class.getResource( "CajaVerHtml.fxml"));
            Parent root = Loader.load();
            CajaVerHtmlController controller = Loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            
            Node ev = (Node)event.getSource();
            String textoAVer = "";
            
             System.out.println("ID: " + ev.getId());
            
            switch (ev.getId()) {
                case "botonVerDiagnostico":
                    textoAVer = cajaDiagnosticoDiagnostico.getText();
                    break;
                case "botonVerObservacionDiagnostico":
                    textoAVer = cajaObservacionDiagnostico.getText();
                    break;
                case "botonVerTrabajoSesion":
                    textoAVer = cajaTrabajoSesion.getText();
                    break;
                case "botonVerObservacionSesion":
                    textoAVer = cajaObservacionSesion.getText();
                    break;
                case "botonVerObservacionAutorizacion":
                    textoAVer = cajaObservacionSesionObraSocial.getText();
                    break;
                
            }
            
            
            if(!cajaBuscarPaciente.getText().isEmpty()){
               
               controller.llenarCaja(textoAVer);
               controller.setIdBoton(ev.getId());
               controller.setNumDNIPaciente(Integer.parseInt(cajaBuscarPaciente.getText()));
               stage.showAndWait(); 
            }
           
            
           
        
        } catch (IOException ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
   @FXML
   public void elegirColorApp(MouseEvent event){
       UsuarioDAOImplementacion usuarioDAOImplementacion = new UsuarioDAOImplementacion();
       Button boton = (Button)event.getSource();
       switch (boton.getId()) {
           case "botonColorSalmon":
               usuarioDAOImplementacion.actualizarColorTemaAplicacion(1);
               hboxColores.setVisible(false);
               mensajeAdvertenciaError("Reiniciar para cambiar de color", this, VariablesEstaticas.imgenExito);
               break;
           case "botonColorGris":
               usuarioDAOImplementacion.actualizarColorTemaAplicacion(2);
               hboxColores.setVisible(false);
               mensajeAdvertenciaError("Reiniciar para cambiar de color", this, VariablesEstaticas.imgenExito);
               break;
           case "botonColorAzul":
               usuarioDAOImplementacion.actualizarColorTemaAplicacion(4);
               hboxColores.setVisible(false);
               mensajeAdvertenciaError("Reiniciar para cambiar de color", this, VariablesEstaticas.imgenExito);
               break;
           case "botonColorVerde":
               usuarioDAOImplementacion.actualizarColorTemaAplicacion(3);
               hboxColores.setVisible(false);
               mensajeAdvertenciaError("Reiniciar para cambiar de color", this, VariablesEstaticas.imgenExito);
               break;
           case "botonColorMarron":
               usuarioDAOImplementacion.actualizarColorTemaAplicacion(5);
               hboxColores.setVisible(false);
               mensajeAdvertenciaError("Reiniciar para cambiar de color", this, VariablesEstaticas.imgenExito);
               break;
           
       }
       
   }
   
   @FXML
   public void cambiarColor(MouseEvent event){
       hboxColores.setVisible(true);
   }
   
   
   
   
    
    

   public void comprobarFechaAlIniciar() {
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                // Coloca aquí la acción que deseas ejecutar cada x tiempo
                AgendaDAOImplementacion agenda =  new AgendaDAOImplementacion();
                try {
                    
                        if(agenda.obtenerRecordatorio(LocalDate.now())){
                            
                        
                        imgRecordatorio.setVisible(true);
                        
                        }else{
                            imgRecordatorio.setVisible(false);
                            
                        }   
                    
                    
                } catch (Exception e) {
                }
            }
        };

        // Definir el intervalo de tiempo en milisegundos (por ejemplo, 5 segundos)
        long intervalo = 30000;

        // Iniciar la ejecución de la tarea repetida cada x tiempo
        timer.scheduleAtFixedRate(tarea, 0, intervalo);
        
        
        
        
    }
   
    public void rellenarDatosUsuario(){
        etiquetaNombreInicio.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario1.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario2.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario3.setText(usuario.getNombre() + " " + usuario.getApellido());
        labelNombreDeUsuario4.setText(usuario.getNombre() + " " + usuario.getApellido());
        
        cajaNombreOpcionesUsuario.setText(usuario.getNombre());
        cajaApellidoOpcionesUsuario.setText(usuario.getApellido());
        cajaUusarioOpcionesUsuario.setText(usuario.getUsuario());
        cajaEmailOpcionesUsuario.setText(usuario.getEmail().getEmail());
    }
    
    public void iniciarColorContenedores(){
        anchorBurbujas.getChildren().add(servicioPadre.patronBurbujas());
        containerMenu.setStyle(servicioPadre.iniciarColorApp());
        apPacientes.setStyle(servicioPadre.iniciarColorApp());
        apOpciones.setStyle(servicioPadre.iniciarColorApp());
        apObraSocial.setStyle(servicioPadre.iniciarColorApp());
        apAgendaPrincipal.setStyle(servicioPadre.iniciarColorApp());
        botonMinimizar.setStyle(servicioPadre.iniciarColorApp());
        botonCerrar.setStyle(servicioPadre.iniciarColorApp());
        botonMaximizarDesmaximizado.setStyle(servicioPadre.iniciarColorApp());
        vbLateral.setStyle(servicioPadre.iniciarColorApp());
        acordionPaciente.setStyle(servicioPadre.iniciarColorApp());
    }
    
    public void habilitarDeshabilitar(MouseEvent event){
        
        HTMLEditor html = (HTMLEditor) event.getSource();
        
        html.setDisable(false);
       
       
        
        
        
        
    }
    
    

    public void iniciarlizarHtmlEditorMenu(){
        List<HTMLEditor> hTMLEditor = new ArrayList();
        
        hTMLEditor.addAll(List.of( htmlObservacionDiagnostico, htmlDiagnostico));
        inicializarPropiedadesHtmlEditor(hTMLEditor);
    }
    
    public void iniciarlizarHtmlEditorSesion(){
        List<HTMLEditor> hTMLEditor = new ArrayList();
        
        hTMLEditor.addAll(List.of(  htmlObservacionAutorizacion, htmlObservacionSesion, htmlTrabajoSesion));
        inicializarPropiedadesHtmlEditor(hTMLEditor);
    }
    
    public void inicializarPropiedadesHtmlEditor(List<HTMLEditor> listHtmlEditor) {
        for (HTMLEditor hTMLEditor : listHtmlEditor) {
            hTMLEditor.addEventFilter(KeyEvent.KEY_TYPED, KeyEvent::consume);
            hTMLEditor.addEventFilter(KeyEvent.KEY_PRESSED, KeyEvent::consume);
            hTMLEditor.addEventFilter(KeyEvent.KEY_RELEASED, KeyEvent::consume);
            hTMLEditor.lookup(".bottom-toolbar").setVisible(false);
            hTMLEditor.lookup(".top-toolbar").setVisible(false);
        }
    }
    
}
