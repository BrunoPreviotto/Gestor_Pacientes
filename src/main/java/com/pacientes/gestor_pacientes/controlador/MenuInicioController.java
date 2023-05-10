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
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Array;
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
public class MenuInicioController extends ClasePadreController implements Initializable, DraggedScene {

    //DENTRO DE CLASE
    private String cssNuevo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/nuevoTitledPane.css").toExternalForm();
    private String cssViejo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/menuinicio.css").toExternalForm();
    private PacienteDAOImplementacion pacienteDao;
    private Paciente paciente;
    private Validar validar = new Validar();
    
    private PlanTratamiento planTratamiento;
    private DiagnosticoPaciente diagnostico;
    private ObraSocialPaciente obraSocialPaciente;
    private Usuario usuario;
    private UsuarioDAOImplementacion usuarioDao;
    private ObraSocialDAOImplementacion obraSocialDao;
    private ObraSocial obraSocial;
    private String valorInicialNombreObraSocialPaciente;
    private List<String> listaPlanesObrasSociales;
            
    

    private BiMap<TextInputControl, String> valoresBUsquedaDatosPrincipales = HashBiMap.create();
    private BiMap<TextInputControl, String> valoresBUsquedaSesiones = HashBiMap.create();
    private BiMap<TextInputControl, String> valoresBUsquedaPlanes = HashBiMap.create();
    private BiMap<TextInputControl, String> valoresBUsquedaDiagnostico = HashBiMap.create();
    private BiMap<TextInputControl, String> valoresBUsquedaObraSocial = HashBiMap.create();
    
    //FXML
    @FXML
    private TitledPane titlePaneDatosPrincipales;
    @FXML
    private AnchorPane apPacientes;
    @FXML
    private AnchorPane pestanaPaciente;
    @FXML
    private AnchorPane pestanaObraSocial;
    @FXML
    private AnchorPane pestanaAgenda;
    @FXML
    private AnchorPane pestanaOpciones;
    @FXML
    private AnchorPane apObraSocial;
    @FXML
    private AnchorPane apAgenda;
    @FXML
    private AnchorPane apOpciones;
    @FXML
    private TextField cajaNumeroSesion;
    @FXML
    private TextField cajaFrecuenciaSesiones;
    @FXML
    private TextField cajaEstrategiaPlan;
    @FXML
    private TextArea cajaDiagnosticoDiagnostico;
    @FXML
    private TextArea cajaObservacionDiagnostico;
    @FXML
    private AnchorPane container;
    @FXML
    private TitledPane titlePaneSesion;
    @FXML
    private TitledPane titlePanePlan;
    @FXML
    private TitledPane titlePaneDiagnostico;
    @FXML
    private TitledPane titlePaneGenograma;
    @FXML
    private TitledPane titlePaneObraSocial;
    @FXML
    private Accordion acordionPaciente;
    @FXML
    private GridPane gpCalendario;
    @FXML
    private DatePicker cajaFechaSesion;
    @FXML
    private TextField cajaAutorizacionSesion;
    @FXML
    private DatePicker cajaAsociacionSesionObraSocial;
    @FXML
    private TextField cajaCopagoSesionObraSocial;
    @FXML
    private TextArea cajaTrabajoSesion;
    @FXML
    private TextArea cajaObservacionSesion;
    @FXML
    private TextArea cajaMotivoTrabajoEmergenteSesion;
    @FXML
    private TextArea cajaObservacionSesionObraSocial;
    @FXML
    private HBox botoneraCrudDatosPrincipales;
    @FXML
    private HBox botoneraCrudSesiones;
    @FXML
    private HBox botoneraCrudPlanTratamiento;
    @FXML
    private HBox botoneraCrudDiagnostico;
    @FXML
    private HBox botoneraCrudObraSocialPaciente;
    @FXML
    private TextField cajaNombreDatosPrincipales;
    @FXML
    private TextField cajaApellidoDatosPrincipales;
    @FXML
    private TextField cajaEdadDatosPrincipales;
    @FXML
    private TextField cajaDniDatosPrincipales;
    @FXML
    private TextField cajaTelefonoDatosPrincipales;
    @FXML
    private Button botonAgregarDatosPrincipales;
    @FXML
    private Button botonActualizarDatosPrincipales;
    @FXML
    private Button botonEliminarDatosPrincipales;
    @FXML
    private Button botonRetornarDatosPrincipales;
    @FXML
    private Button botonAgregarSesiones;
    @FXML
    private Button botonActualizarSesiones;
    @FXML
    private Button botonEliminarSesiones;
    @FXML
    private Button botonRetornarSesiones;
    @FXML
    private Button botonAgregarPlanTratamiento;
    @FXML
    private Button botonActualizarPlanTratamiento;
    @FXML
    private Button botonEliminarPlanTratamiento;
    @FXML
    private Button botonRetornarPlanTratamiento;
    @FXML
    private Button botonAgregarDiagnostico;
    @FXML
    private Button botonActualizarDiagnostico;
    @FXML
    private Button botonEliminarDiagnostico;
    @FXML
    private Button botonRetornarDiagnostico;
    @FXML
    private Button botonAgregarObraSocialPaciente;
    @FXML
    private Button botonActualizarObraSocialPaciente;
    @FXML
    private Button botonEliminarObraSocialPaciente;
    @FXML
    private Button botonRetornarObraSocialPaciente;
    @FXML
    private TextField cajaNombreObraSocial;
    @FXML
    private TextField cajaBuscarObraSocial;
    @FXML
    private TextField cajaBuscarPaciente;
    @FXML
    private TableColumn<TablaSesiones, String> ColumnaSesionNumero;
    @FXML
    private TableColumn<TablaSesiones, String> ColumnaSesionFecha;
    @FXML
    private TableColumn<TablaSesiones, String> ColumnaSesionTrabajo;
    @FXML
    private TableColumn<TablaSesiones, String> ColumnaSesionObservacion;
    @FXML
    private TableColumn<TablaSesiones, String> ColumnaSesionMotivoTRabajoEmergente;
    @FXML
    private TableColumn<TablaSesiones, AutorizacionesSesionesObraSociales> columnaAutorizacionNumero;
    @FXML
    private TableColumn<TablaSesiones, String> columnaAutorizacionObservacion;
    @FXML
    private TableColumn<TablaSesiones, String> columnaAutorizacionAsociacion;
    @FXML
    private TableColumn<TablaSesiones, String> columnaAutorizacionCopago;
    @FXML
    private TableColumn<TablaSesiones, String> columnaAutorizacionCodigoFacturacion;
    @FXML
    private TableColumn<TablaSesiones, String> columnaNumeroSecionAutorizacion;
    @FXML
    private HBox ola;
    @FXML
    private ChoiceBox<String> choiseNombreObraSocialPaciente;
    @FXML
    private TextField cajaPlanObraSocialPaciente;
    @FXML
    private TextField cajaNAfiliadoObraSocialPaciente;
    @FXML
    private TextField cajaNombreTipoSesionPlan;
    @FXML
    private TextArea cajaDescripcionTipoSesionPlan;
    private Label labelNombreDeUsuario;
    @FXML
    private Label labelNombreDeUsuario4;
    @FXML
    private Label labelNombreDeUsuario3;
    @FXML
    private Label labelNombreDeUsuario2;
    @FXML
    private Label labelNombreDeUsuario1;
    @FXML
    private HBox botoneraCrudDatosPrincipales1;
    @FXML
    private Button botonAgregarObraSocial;
    @FXML
    private Button botonActualizarObraSocial;
    @FXML
    private Button botonEliminarObraSocial;
    @FXML
    private Button botonRetornarObraSocial;
    @FXML
    private ChoiceBox<String> choiseTipoSesion;
    @FXML
    private TextField cajaTelefonoObraSocial;
    @FXML
    private TextField cajaEmailObraSocial;
    @FXML
    private TextField cajaWebObraSocial;
    @FXML
    private TableView<Cliente> tablaGenograma;
    @FXML
    private ChoiceBox<String> choiseCodigoFactSesionObraSocial;
    
    @FXML
    private TableView<TablaSesiones> tableSesiones;
    @FXML
    private TableView<TablaSesiones> tablaAutorizacion;
    
    @FXML
    private TextField cajaNombreObraSocialPaciente;
    private TextField cajaPlanesObraSocial;
    @FXML
    private TextField cajaVerPlanes;
    @FXML
    private TextField cajaAgregarPlan;
    @FXML
    private Button botonAgregarPlanesObraSocial;
    @FXML
    private Button botonActualizarPlanesObraSocial;
    @FXML
    private ChoiceBox<String> choisePlanesObraSocialPacientePlan;
    @FXML
    private VBox vBoxSesiones;
    @FXML
    private VBox vBoxAutorizacion;
    
    
    

    
    //                          ****
    //                          ****
    //                          ****
    //      INICIALIZADORES  
    //                          ****
    //                          ****
    //                          ****
    
    
    /**
     * Inicializa:
     * El nombre del usuario que ha iniciado sesion
     * Los choise list 
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
        
        choiseTipoSesion.setOnAction(this::cambiarCategoria);
        
        choiseNombreObraSocialPaciente.setOnAction(this::cambiarPlanesObraSocial);

    }

    private void iniciarChoiseList() {
        pacienteDao = new PacienteDAOImplementacion();
        obraSocialDao = new ObraSocialDAOImplementacion();
        List<String> listaCodigosFacturacion = pacienteDao.obtenerListaCodigosFacturacion();
        List<String> listaTiposSesiones = pacienteDao.obtenerListaTiposSesion();
        List<String> listaNombreObrasSociales = obraSocialDao.obtenerListaNombresObrasSociales();
        
        
        if (!Objects.isNull(listaNombreObrasSociales)) {
            choiseNombreObraSocialPaciente.getItems().addAll(listaNombreObrasSociales);
            choiseNombreObraSocialPaciente.setValue(listaNombreObrasSociales.get(0));
        }
        
        listaPlanesObrasSociales = obraSocialDao.obtenerListaPlanesObrasSociales(new ObraSocial(choiseNombreObraSocialPaciente.getValue()));
        valorInicialNombreObraSocialPaciente = choiseNombreObraSocialPaciente.getValue();
        
        if(!Objects.isNull(listaPlanesObrasSociales)){
            choisePlanesObraSocialPacientePlan.getItems().addAll(listaPlanesObrasSociales);
        }
        if (!Objects.isNull(listaTiposSesiones)) {
            choiseTipoSesion.getItems().addAll(listaTiposSesiones);
        }
        if (!Objects.isNull(listaCodigosFacturacion)) {
            choiseCodigoFactSesionObraSocial.getItems().addAll(listaCodigosFacturacion);
        }
    }
    
    //                  ****
    //                  ****
    //                  ****
    //      CREAR   
    //                  ****
    //                  ****
    //                  ****

    @FXML
    private void crearPaciente(MouseEvent event) {
        validarPaciente();
        pacienteDao = new PacienteDAOImplementacion();
        List<TextField> listaCajas = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));
        if (esCajaValida(listaCajas)) {
            pacienteDao.insertar(inicializarPacienteRegistroDatosPrincipales(cajaNombreDatosPrincipales.getText(), cajaApellidoDatosPrincipales.getText(), Integer.parseInt(cajaEdadDatosPrincipales.getText()), Integer.parseInt(cajaDniDatosPrincipales.getText()), new Telefono(cajaTelefonoDatosPrincipales.getText())), 1);
            cajaNombreDatosPrincipales.setText("");
            cajaApellidoDatosPrincipales.setText("");
            cajaEdadDatosPrincipales.setText("");
            cajaDniDatosPrincipales.setText("");
            cajaTelefonoDatosPrincipales.setText("");
        }
    }
    
    @FXML
    private void crearSesion(MouseEvent event) {
        
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
            int ultimaSesion = pacienteDao.obtenerultimaSesion(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))) +1;
            cajaNumeroSesion.setText(String.valueOf(ultimaSesion));
            cajaTrabajoSesion.setText("");
            cajaObservacionSesion.setText("");
            cajaMotivoTrabajoEmergenteSesion.setText("");

            cajaAutorizacionSesion.setText("");
            cajaObservacionSesionObraSocial.setText("");
            cajaAsociacionSesionObraSocial.setValue(LocalDate.now());
            cajaCopagoSesionObraSocial.setText("");
            choiseCodigoFactSesionObraSocial.setValue("");

        }else{
            pacienteDao = new PacienteDAOImplementacion();
            paciente = new Paciente();
            AutorizacionesSesionesObraSociales  autorizacionesSesionesObraSociales;
            
            
            
            if(!cajaAutorizacionSesion.getText().equals("")){
                autorizacionesSesionesObraSociales = new AutorizacionesSesionesObraSociales(
                    Integer.valueOf(cajaAutorizacionSesion.getText()), 
                         cajaObservacionSesionObraSocial.getText(), cajaAsociacionSesionObraSocial.getValue(), 
                            Double.valueOf(cajaCopagoSesionObraSocial.getText()), 
                                  new CodigoFacturacion(choiseCodigoFactSesionObraSocial.getValue()));
            }else{
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
                    
            paciente.setSesion(sesion);
            paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
            paciente.setId(pacienteDao.obtenerIdPaciente(paciente).getId());
            
            if (!cajaDniDatosPrincipales.getText().isEmpty()) {
                pacienteDao.insertar(paciente, 3);
                vBoxSesiones.setVisible(false);
                vBoxAutorizacion.setVisible(false);
                tableSesiones.setVisible(true);
                tablaAutorizacion.setVisible(true);
                buscarPaciente();
            } else {

            }
        }
        botonAgregarSesiones.setId("1");


    }

    @FXML
    private void crearPlanTratamiento(MouseEvent event) {
        pacienteDao = new PacienteDAOImplementacion();
        paciente = new Paciente();
        TipoSesion ts = new TipoSesion(cajaNombreTipoSesionPlan.getText(), cajaDescripcionTipoSesionPlan.getText());
        planTratamiento = new PlanTratamiento(cajaEstrategiaPlan.getText(), cajaFrecuenciaSesiones.getText(), ts);
        paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
        paciente.setId(pacienteDao.obtenerIdPaciente(paciente).getId());
        paciente.setPlanTratamiento(planTratamiento);
        if (!cajaDniDatosPrincipales.getText().isEmpty()) {
            pacienteDao.insertar(paciente, 5);
        } else {

        }
    }

    @FXML
    private void crearDiagnostico(MouseEvent event) {
        pacienteDao = new PacienteDAOImplementacion();
        paciente = new Paciente();
        diagnostico = new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText());
        paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
        paciente.setId(pacienteDao.obtenerIdPaciente(paciente).getId());
        paciente.setDiagnostico(diagnostico);
        if (!cajaDniDatosPrincipales.getText().isEmpty()) {
            pacienteDao.insertar(paciente, 4);
        } else {

        }
    }

    
    @FXML
    private void crearObraSocialPaciente(MouseEvent event) {
        pacienteDao = new PacienteDAOImplementacion();
        paciente = new Paciente();
        obraSocialPaciente = new ObraSocialPaciente(Integer.parseInt(cajaNAfiliadoObraSocialPaciente.getText()), choiseNombreObraSocialPaciente.getValue().toString(), new PlanObraSocial(choisePlanesObraSocialPacientePlan.getValue().toString(), "Sin descripcion"));
        paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
        paciente.setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId());
        paciente.setObraSocialPaciente(obraSocialPaciente);
        
        
        
        if (!cajaDniDatosPrincipales.getText().isEmpty()) {
            pacienteDao.insertar(paciente, 2);
        } else {

        }
    }

    @FXML
    private void crearObraSocial(MouseEvent event) {
        obraSocialDao = new ObraSocialDAOImplementacion();
        obraSocial = new ObraSocial(cajaNombreObraSocial.getText(), new Telefono(cajaTelefonoObraSocial.getText()), new Web(cajaWebObraSocial.getText()), true, new Email(cajaEmailObraSocial.getText()));
        obraSocialDao.insertar(obraSocial, 1);
    }
    
    @FXML
    private void agregarPlan(MouseEvent event) {
        botonAgregarPlanesObraSocial.setDisable(true);
        cajaVerPlanes.setDisable(true);
        cajaAgregarPlan.setDisable(false);
        botonActualizarPlanesObraSocial.setDisable(false);
    }
    
    @FXML
    private void actualizarPlanesObrasSocial(MouseEvent event) {
        obraSocialDao = new ObraSocialDAOImplementacion();
        obraSocial = new ObraSocial();
        if(!cajaAgregarPlan.getText().isEmpty()){
            obraSocial.setNombre(cajaBuscarObraSocial.getText());
            obraSocial.setPlan(cajaAgregarPlan.getText());
            obraSocialDao.agregarPlan(obraSocial);
        }
        cajaAgregarPlan.setText("");
        cajaAgregarPlan.setDisable(true);
        botonActualizarPlanesObraSocial.setDisable(true);
        cajaVerPlanes.setDisable(false);
        botonAgregarPlanesObraSocial.setDisable(false);
    }
    
    //                  ****
    //                  ****
    //                  ****
    //      ACTUALIZAR   
    //                  ****
    //                  ****
    //                  ****
    
    @FXML
    private void actualizarDiagnostico(MouseEvent event) {
        Paciente paciente = new Paciente();
        pacienteDao = new PacienteDAOImplementacion();
        
        pacienteDao.actualizar(paciente.setDiagnostico(new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText())).setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId()), 1);
    
        
    }

    
    @FXML
    private void actualizarPlan(MouseEvent event) {
        Paciente paciente = new Paciente();
        pacienteDao = new PacienteDAOImplementacion();
        List<TextField> listaCajasPlan = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));
        //List<TextField> listaCajasDatosPrincipales = new ArrayList<TextField>(Arrays.asList(cajaFrecuenciaSesiones, cajaNombreTipoSesionPlan, cajaDescripcionTipoSesionPlan, cajaEstrategiaPlan));
        if(esCajaValida(listaCajasPlan)){
            pacienteDao.actualizar(paciente.setPlanTratamiento(new PlanTratamiento(cajaEstrategiaPlan.getText(), cajaFrecuenciaSesiones.getText(), new TipoSesion(choiseTipoSesion.getValue(), "Sin descripcion"))).setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId()), 2);
        }
    }

    @FXML
    private void actualizarPaciente(MouseEvent event) {
        Paciente paciente = new Paciente();
        pacienteDao = new PacienteDAOImplementacion();
        List<TextField> listaCajasDatosPrincipales = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));
        
        if(esCajaValida(listaCajasDatosPrincipales)){
            pacienteDao.actualizar(paciente.setNombre(cajaNombreDatosPrincipales.getText()).setApellido(cajaApellidoDatosPrincipales.getText()).setEdad(Integer.parseInt(cajaEdadDatosPrincipales.getText())).setDni(Integer.parseInt(cajaDniDatosPrincipales.getText())).setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))).getId()).setTelefono(new Telefono(cajaTelefonoDatosPrincipales.getText())), 3);
        }
    }

    //                  ****
    //                  ****
    //                  ****
    //      BUSCAR  
    //                  ****
    //                  ****
    //                  ****
    
    @FXML
    private void buscarPaciente() {
        Paciente paciente = new Paciente();
        pacienteDao = new PacienteDAOImplementacion();
        
        cajaNombreObraSocialPaciente.setText("");
        cajaPlanObraSocialPaciente.setText("");
        cajaNAfiliadoObraSocialPaciente.setText("");
        
        botonActualizarSesiones.setDisable(false);
        botonAgregarSesiones.setDisable(false);
        botonEliminarSesiones.setDisable(false);
        
        
            if (!cajaBuscarPaciente.getText().isEmpty()) {
            paciente.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
            paciente.setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))).getId());

            if (paciente.getId() != 0) {
                Paciente pacienteResultado = pacienteDao.obtener(paciente);

                ObservableList<TablaSesiones> ol = FXCollections.observableArrayList();
                
                if (Objects.nonNull(pacienteResultado)) {
                   
                    if (!Objects.isNull(paciente)) {

                        if (Objects.nonNull(pacienteResultado.getDni())) {
                            cajaNombreDatosPrincipales.setText(pacienteResultado.getNombre());
                            cajaApellidoDatosPrincipales.setText(pacienteResultado.getApellido());
                            cajaEdadDatosPrincipales.setText(String.valueOf(pacienteResultado.getEdad()));
                            cajaDniDatosPrincipales.setText(String.valueOf(pacienteResultado.getDni()));
                            cajaTelefonoDatosPrincipales.setText(pacienteResultado.getTelefono().getTelefono());

                            valoresBUsquedaDatosPrincipales = ImmutableBiMap.of(cajaNombreDatosPrincipales, cajaNombreDatosPrincipales.getText(),
                                    cajaApellidoDatosPrincipales, cajaApellidoDatosPrincipales.getText(),
                                    cajaEdadDatosPrincipales, cajaEdadDatosPrincipales.getText(),
                                    cajaDniDatosPrincipales, cajaDniDatosPrincipales.getText(),
                                    cajaTelefonoDatosPrincipales, cajaTelefonoDatosPrincipales.getText());

                            botonRetornarDatosPrincipales.setDisable(false);
                            botonActualizarDatosPrincipales.setDisable(true);
                            botonAgregarDatosPrincipales.setDisable(true);
                            botonEliminarDatosPrincipales.setDisable(false);

                        }

                        if (Objects.nonNull(pacienteResultado.getSesiones())) {
                            for (SesionPaciente sp : pacienteResultado.getSesiones()) {
                                
                                ol.add(new TablaSesiones(String.valueOf(sp.getNumeroSesion()), sp.getFecha().toString(), sp.getTrabajoSesion(), sp.getObservacion(), sp.getMotivoTrabajoEmergente(), String.valueOf(sp.getAutorizacion().getNumeroAutorizacion()), sp.getAutorizacion().getObservacion(), sp.getAutorizacion().getAsociacion().toString(), String.valueOf(sp.getAutorizacion().getCopago()), sp.getAutorizacion().getCodigoFacturacion().getNombre()));
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

                        if (Objects.nonNull(pacienteResultado.getPlanTratamiento())) {
                            cajaFrecuenciaSesiones.setText(pacienteResultado.getPlanTratamiento().getFrecuenciaSesion());
                            cajaNombreTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());
                            cajaDescripcionTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getDecripcion());
                            cajaEstrategiaPlan.setText(pacienteResultado.getPlanTratamiento().getEstrategia());
                            choiseTipoSesion.setValue(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());
                            cajaNombreTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());

                            valoresBUsquedaPlanes = ImmutableBiMap.of(cajaFrecuenciaSesiones, cajaFrecuenciaSesiones.getText(),
                                    cajaNombreTipoSesionPlan, cajaNombreTipoSesionPlan.getText(),
                                    cajaDescripcionTipoSesionPlan, cajaDescripcionTipoSesionPlan.getText(),
                                    cajaEstrategiaPlan, cajaEstrategiaPlan.getText());
                        }

                        if (Objects.nonNull(pacienteResultado.getDiagnostico())) {
                            cajaDiagnosticoDiagnostico.setText(pacienteResultado.getDiagnostico().getDiagnostico());
                            cajaObservacionDiagnostico.setText(pacienteResultado.getDiagnostico().getObservacion());
                            valoresBUsquedaDiagnostico = ImmutableBiMap.of(cajaDiagnosticoDiagnostico, cajaDiagnosticoDiagnostico.getText(),
                                    cajaObservacionDiagnostico, cajaObservacionDiagnostico.getText());
                        }

                        if (Objects.nonNull(pacienteResultado.getObraSocialPaciente())) {

                            cajaNombreObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getNombre());
                            choiseNombreObraSocialPaciente.setValue(pacienteResultado.getObraSocialPaciente().getNombre());
                            cajaPlanObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getPlan().getNombre());
                            cajaNAfiliadoObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getNumeroAfiliado().toString());
                        }
                    }
                }else{
                    mensaje("Paciente no encontrado", this, "/com/pacientes/gestor_pacientes/img/PacienteNoEncontrado.png");
                }
                    
            }else{
                mensaje("Paciente no encontrado", this, "/com/pacientes/gestor_pacientes/img/PacienteNoEncontrado.png");
            }
                

        } else {

            cajaNombreDatosPrincipales.setText("");
            cajaApellidoDatosPrincipales.setText("");
            cajaEdadDatosPrincipales.setText("");
            cajaDniDatosPrincipales.setText("");
            cajaTelefonoDatosPrincipales.setText("");

        }
        
    }
    
    @FXML
    private void buscarObraSocia(MouseEvent event) {
        
        ObraSocial obraSocial = new ObraSocial();
        obraSocialDao = new ObraSocialDAOImplementacion();
        obraSocial = obraSocialDao.obtener(new ObraSocial(cajaBuscarObraSocial.getText()));
        
        String planes = "";
        for (String plan : obraSocial.getPlanes()) {
            planes += plan + " | ";
        }
        
        cajaVerPlanes.setText(planes);
        
    }
    
    
    
    //                  ****
    //                  ****
    //                  ****
    //      ELIMINAR    ****
    //                  ****
    //                  ****
    //                  ****

    @FXML
    private void eliminarPaciente(MouseEvent event) {
        pacienteDao.eliminar(new Paciente(Integer.parseInt(cajaDniDatosPrincipales.getText())), 1);
    }

    //                  ****
    //                  ****
    //                  ****
    //      VALIDAR   
    //                  ****
    //                  ****
    //                  ****

    public void validarSesion() {

    }

    public void validarPaciente() {
        MetodosComoParametros mcp = new MetodosComoParametros();

        Map<TextField, Method> mapValidar = ImmutableMap.of(cajaNombreDatosPrincipales, mcp.retornarMetodosValidar(3), cajaApellidoDatosPrincipales, mcp.retornarMetodosValidar(3),
                cajaEdadDatosPrincipales, mcp.retornarMetodosValidar(1), cajaDniDatosPrincipales, mcp.retornarMetodosValidar(3),
                cajaTelefonoDatosPrincipales, mcp.retornarMetodosValidar(3));

        //validarCajasExedidasCaracteres(mapValidar);

    }

    private boolean esCajaValida(List<TextField> cajas) {
        HBox hb;
        ImageView img = new ImageView();
        for (TextField caja : cajas) {
            hb = (HBox) caja.getParent();
            
            if(hb.getChildren().size() > 1){
                img = (ImageView) hb.getChildren().get(1);
                if (img.getId().equals("imgError") || img.getId().equals("imgVacio")) {
                    return false;
                }
            }
            
        }
        return true;
    }

    private void blanquearCajas(List<TextField> cajas) {
        for (TextField caja : cajas) {
            caja.setText("");
        }
    }

    private void setearVisibilidad(Map<Control, Boolean> visibilidades) {
        for (Map.Entry<Control, Boolean> entry : visibilidades.entrySet()) {
            entry.getKey().setDisable(entry.getValue());
        }
    }

    @FXML
    private void retornarBotonera(MouseEvent event) {
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

    /***
     * SELECCIONAR CADA UNA DE LAS OPCIONES DEL MENU
     * @param event 
     */
    @FXML
    private void eleccionMenu(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        switch (pane.getId()) {
            case "pestanaPaciente":

                TranslateTransition ttdp = new TranslateTransition(Duration.seconds(1), titlePaneDatosPrincipales);
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
                pt.play();

                apPacientes.setVisible(true);
                apObraSocial.setVisible(false);
                apAgenda.setVisible(false);
                apOpciones.setVisible(false);
                break;
            case "pestanaObraSocial":
                apObraSocial.setVisible(true);
                apPacientes.setVisible(false);
                apAgenda.setVisible(false);
                apOpciones.setVisible(false);
                break;
            case "pestanaAgenda":
                apAgenda.setVisible(true);
                apObraSocial.setVisible(false);

                apPacientes.setVisible(false);
                apOpciones.setVisible(false);
                break;
            case "pestanaOpciones":
                apOpciones.setVisible(true);
                apAgenda.setVisible(false);
                apObraSocial.setVisible(false);
                apPacientes.setVisible(false);
                break;

            default:
                throw new AssertionError();
        }
    }

    @FXML
    private void expandirTitledPane(MouseEvent event) {

        TitledPane tp = (TitledPane) event.getSource();

        if (tp.expandedProperty().get() && acordionPaciente.getStylesheets().get(0).equals(cssViejo)) {
            acordionPaciente.getStylesheets().remove(cssViejo);
            acordionPaciente.getStylesheets().add(cssNuevo);
           

        } else if (!tp.expandedProperty().get() && acordionPaciente.getStylesheets().get(0).equals(cssNuevo)) {
            acordionPaciente.getStylesheets().remove(cssNuevo);
            acordionPaciente.getStylesheets().add(cssViejo);
           
        }

    }

   
    /***
     * AL DAR AL BOTON BUSCAR BUSCA AL PACIENTE SEGUN EL DOCUMENTO
     * @param event 
     */
    @FXML   
    private void alDarEnterBuscarBoton(KeyEvent event) {
        
        if(event.getCode() == KeyCode.ENTER){
            if(!cajaBuscarPaciente.getText().isEmpty()){
                buscarPaciente();
            }
        }
    }

    

    @FXML
    private void cambiarAcrearActualizarBorrar(KeyEvent event) {
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
                        cajaFrecuenciaSesiones.getText().isEmpty(),
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

   

    private void cambiarCategoria(ActionEvent event) {
        ChoiceBox cb = (ChoiceBox)event.getSource();
        if(cb.getAccessibleText().equals("planTratamiento")){
            if (!choiseTipoSesion.getValue().equals(cajaNombreTipoSesionPlan.getText())) {
                cajaNombreTipoSesionPlan.setText(choiseTipoSesion.getValue());
               
            }
            if (!cb.getValue().equals(valoresBUsquedaPlanes.get(cajaNombreTipoSesionPlan)) && valoresBUsquedaPlanes.get(cajaNombreTipoSesionPlan) != null) {
                botonActualizarPlanTratamiento.setDisable(false);
                
            } else {
                botonActualizarPlanTratamiento.setDisable(true);
            }
        }
    }
    
    

    private void cambiarPlanesObraSocial(ActionEvent event) {
        ChoiceBox cb = (ChoiceBox) event.getSource();
        obraSocialDao = new ObraSocialDAOImplementacion();
         List<String> listaNuevaPlanes = obraSocialDao.obtenerListaPlanesObrasSociales(new ObraSocial(choiseNombreObraSocialPaciente.getValue()));
        if (!choiseNombreObraSocialPaciente.getValue().equals(valorInicialNombreObraSocialPaciente)) {
            choisePlanesObraSocialPacientePlan.getItems().setAll(obraSocialDao.obtenerListaPlanesObrasSociales(new ObraSocial(choiseNombreObraSocialPaciente.getValue())));
            valorInicialNombreObraSocialPaciente = choiseNombreObraSocialPaciente.getValue();
        }

    }

    

    

    
    

    @FXML
    private void actualizarSesion(MouseEvent event) {
       
        MensajeAdvertenciaController mensajeAdvertencia = new MensajeAdvertenciaController();
        
        if(tableSesiones.getSelectionModel().isEmpty()){
            mensaje("Seleccione sesion para pode actualizar", this, "/com/pacientes/gestor_pacientes/img/warning.png");
            
            
            
        }else{
            LocalDate ldAutorizacion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getAsociacion());
            LocalDate ldSesion = LocalDate.parse(tableSesiones.getSelectionModel().getSelectedItem().getFechaSesion());
            Paciente pacienteSesion = new Paciente();
            SesionPaciente sesion = new SesionPaciente();
            AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
            CodigoFacturacion codigo = new CodigoFacturacion();
            
            Paciente pacienteBuscar = new Paciente();
            SesionPaciente sesionBuscar = new SesionPaciente();
            AutorizacionesSesionesObraSociales autorizacionBuscar = new AutorizacionesSesionesObraSociales();
            
            
            
            if(botonActualizarSesiones.getId().equals("1")){
                
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
                
                if(cajaAutorizacionSesion.getText().equals("0") && cajaCopagoSesionObraSocial.getText().equals("0.0") && cajaAsociacionSesionObraSocial.getValue().toString().equals("1700-01-01") && cajaObservacionSesionObraSocial.getText().trim().toString().equals("-")){
                    sesion.setAutorizacion(rellenarAutorizacionVacia());
                }else{
                    
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
            }else{
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
        
        
       
    }

    @FXML
    private void eliminarSesion(MouseEvent event) {
    }

    @FXML
    private void retornarTablaSesion(MouseEvent event) {
        tablaAutorizacion.setVisible(true);
        tableSesiones.setVisible(true);
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
    private void fucusSesionAutorizacion(MouseEvent event) {
        
        tablaAutorizacion.requestFocus();
        tablaAutorizacion.getSelectionModel().select(tableSesiones.getSelectionModel().getFocusedIndex());
        
    }
    
    
    public AutorizacionesSesionesObraSociales rellenarAutorizacionVacia(){
        
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

    

   
   
    

    

    

    

    
    
    
    

   
   
    

    
    
    

    
    
   

    

    

    

   

   

    

   
    

   

    

    
   

    

    

    

    

    

    

    

    

    

    
    

   

    

   
   

}
