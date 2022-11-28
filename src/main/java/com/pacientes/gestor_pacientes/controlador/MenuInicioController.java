/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import com.pacientes.gestor_pacientes.implementacionDAO.*;
import com.pacientes.gestor_pacientes.modelo.*;
import com.pacientes.gestor_pacientes.utilidades.MetodosComoParametros;
import static com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas.*;
import static com.pacientes.gestor_pacientes.servicios.InicializarObjeto.*;
import com.pacientes.gestor_pacientes.validacion.Validar;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import com.google.common.collect.ImmutableMap;
import com.pacientes.gestor_pacientes.utilidades.TablaSesiones;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author previotto
 */
public class MenuInicioController extends PadreController implements Initializable, DraggedScene {

    private String cssNuevo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/nuevoTitledPane.css").toExternalForm();
    private String cssViejo = getClass().getResource("/com/pacientes/gestor_pacientes/styles/menuinicio.css").toExternalForm();
    private PacienteDAOImplementacion pacienteDao;
    private Paciente paciente;
    private Validar validar = new Validar();
    private SesionPaciente sesion;
    private PlanTratamiento planTratamiento;
    private DiagnosticoPaciente diagnostico;
    private ObraSocialPaciente obraSocialPaciente;
    private Usuario usuario;
    private UsuarioDAOImplementacion usuarioDao;
    private ObraSocialDAOImplementacion obraSocialDao;
    private ObraSocial obraSocial;

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
    private TextField cajaNombreObraSocialPaciente;

    /**
     * Initializes the controller class.
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

    }

    private void iniciarChoiseList() {
        LocalDate local = LocalDate.of(2022, 2, 2);

        sesion = new SesionPaciente(1, local, "asdasdasd", "<dsdsad", "sdsadsad", new AutorizacionesSesionesObraSociales(1, "fddasf", local, 600.0, new CodigoFacturacion("codigo", 54541)));
        pacienteDao = new PacienteDAOImplementacion();
        obraSocialDao = new ObraSocialDAOImplementacion();

        ObservableList<Cliente> cli = FXCollections.observableArrayList(new Cliente("Bruno", "Previotto"));
        tablaGenograma.setItems(cli);

        TableColumn<Cliente, String> col1 = new TableColumn<>("Nombre");
        TableColumn<Cliente, String> col2 = new TableColumn<>("Apellido");
        col1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col2.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tablaGenograma.getColumns().addAll(col1, col2);

        List<String> listaCodigosFacturacion = pacienteDao.obtenerListaCodigosFacturacion();
        List<String> listaTiposSesiones = pacienteDao.obtenerListaTiposSesion();
        List<String> listaNombreObrasSociales = obraSocialDao.obtenerListaNombresObrasSociales();
        if (!Objects.isNull(listaNombreObrasSociales)) {
            choiseNombreObraSocialPaciente.getItems().addAll(listaNombreObrasSociales);
        }
        if (!Objects.isNull(listaTiposSesiones)) {
            choiseTipoSesion.getItems().addAll(listaTiposSesiones);
        }
        if (!Objects.isNull(listaCodigosFacturacion)) {
            choiseCodigoFactSesionObraSocial.getItems().addAll(listaCodigosFacturacion);
        }
    }

    @FXML
    private void crearPaciente(MouseEvent event) {
        validarPaciente();
        pacienteDao = new PacienteDAOImplementacion();
        List<TextField> listaCajas = new ArrayList<TextField>(Arrays.asList(cajaNombreDatosPrincipales, cajaApellidoDatosPrincipales, cajaEdadDatosPrincipales, cajaDniDatosPrincipales, cajaTelefonoDatosPrincipales));
        if (esCajaValida(listaCajas)) {
            pacienteDao.insertar(inicializarPacienteRegistroDatosPrincipales(cajaNombreDatosPrincipales.getText(), cajaApellidoDatosPrincipales.getText(), Integer.parseInt(cajaEdadDatosPrincipales.getText()), Integer.parseInt(cajaDniDatosPrincipales.getText()), new Telefono(cajaTelefonoDatosPrincipales.getText())));
            cajaNombreDatosPrincipales.setText("");
            cajaApellidoDatosPrincipales.setText("");
            cajaEdadDatosPrincipales.setText("");
            cajaDniDatosPrincipales.setText("");
            cajaTelefonoDatosPrincipales.setText("");
        }
    }

    @FXML
    private void actualizarPaciente(MouseEvent event) {

    }

    @FXML
    private void buscarPaciente() {
        Paciente paciente = new Paciente();
        pacienteDao = new PacienteDAOImplementacion();
        paciente.setDni(Integer.parseInt(cajaBuscarPaciente.getText()));
        paciente.setId(pacienteDao.obtenerIdPaciente(new Paciente(Integer.valueOf(cajaBuscarPaciente.getText()))).getId());
        Paciente pacienteResultado = pacienteDao.obtener(paciente);

        

        ObservableList<TablaSesiones> ol = FXCollections.observableArrayList();
        if (!Objects.isNull(paciente)) {
            
            if (Objects.nonNull(pacienteResultado.getDni())) {
                cajaNombreDatosPrincipales.setText(pacienteResultado.getNombre());
                cajaApellidoDatosPrincipales.setText(pacienteResultado.getApellido());
                cajaEdadDatosPrincipales.setText(String.valueOf(pacienteResultado.getEdad()));
                cajaDniDatosPrincipales.setText(String.valueOf(pacienteResultado.getDni()));
                cajaTelefonoDatosPrincipales.setText(pacienteResultado.getTelefono().getTelefono());
                botonRetornarDatosPrincipales.setDisable(false);
                botonActualizarDatosPrincipales.setDisable(false);
                botonAgregarDatosPrincipales.setDisable(true);
                botonEliminarDatosPrincipales.setDisable(false);
            }
            
            
            if (Objects.nonNull(pacienteResultado.getSesiones())) {
                for (SesionPaciente sp : pacienteResultado.getSesiones()) {

                    ol.add(new TablaSesiones(String.valueOf(sp.getNumeroSesion()), sp.getFecha().toString(), sp.getTrabajoSesion(), sp.getObservacion(), sp.getMotivoTrabajoEmergente(), String.valueOf(sp.getAutorizacion().getNumeroAutorizacion()), sp.getAutorizacion().getObservacion(), sp.getAutorizacion().getAsociacion().toString(), String.valueOf(sp.getAutorizacion().getCopago()), sp.getAutorizacion().getCodigoFacturacion().getNombre()));
                }
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
            }

            if (Objects.nonNull(pacienteResultado.getPlanTratamiento())) {
                cajaFrecuenciaSesiones.setText(pacienteResultado.getPlanTratamiento().getFrecuenciaSesion());
                cajaNombreTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getNombre());
                cajaDescripcionTipoSesionPlan.setText(pacienteResultado.getPlanTratamiento().getTipoSEsion().getDecripcion());
                cajaEstrategiaPlan.setText(pacienteResultado.getPlanTratamiento().getEstrategia());
            }
            
            if(Objects.nonNull(pacienteResultado.getDiagnostico())){
                cajaDiagnosticoDiagnostico.setText(pacienteResultado.getDiagnostico().getDiagnostico());
                cajaObservacionDiagnostico.setText(pacienteResultado.getDiagnostico().getObservacion());
            }
            
            if(Objects.nonNull(pacienteResultado.getObraSocialPaciente())){
               cajaNombreObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getNombre());
               cajaPlanObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getPlan().getNombre());
               cajaNAfiliadoObraSocialPaciente.setText(pacienteResultado.getObraSocialPaciente().getNumeroAfiliado().toString());
            }
        }

    }

    @FXML
    private void eliminar(MouseEvent event) {
        pacienteDao.eliminar(new Paciente(Integer.parseInt(cajaDniDatosPrincipales.getText())));
    }

    @FXML
    private void crearSesion(MouseEvent event) {
        pacienteDao = new PacienteDAOImplementacion();
        paciente = new Paciente();
        sesion = new SesionPaciente(Integer.valueOf(cajaNumeroSesion.getText()), cajaFechaSesion.getValue(), cajaTrabajoSesion.getText(), cajaObservacionSesion.getText(), cajaMotivoTrabajoEmergenteSesion.getText(), new AutorizacionesSesionesObraSociales(Integer.valueOf(cajaAutorizacionSesion.getText()), cajaObservacionSesionObraSocial.getText(), cajaAsociacionSesionObraSocial.getValue(), Double.valueOf(cajaCopagoSesionObraSocial.getText()), new CodigoFacturacion(choiseCodigoFactSesionObraSocial.getValue())));

        paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
        if (!cajaDniDatosPrincipales.getText().isEmpty()) {
            pacienteDao.insertarSesion(pacienteDao.obtenerIdPaciente(paciente).getId(), sesion);
        } else {

        }
    }

    @FXML
    private void crearPlanTratamiento(MouseEvent event) {
        pacienteDao = new PacienteDAOImplementacion();
        paciente = new Paciente();
        TipoSesion ts = new TipoSesion(cajaNombreTipoSesionPlan.getText(), cajaDescripcionTipoSesionPlan.getText());
        planTratamiento = new PlanTratamiento(cajaEstrategiaPlan.getText(), cajaFrecuenciaSesiones.getText(), ts);
        paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
        if (!cajaDniDatosPrincipales.getText().isEmpty()) {
            pacienteDao.insertarPlanTratamiento(pacienteDao.obtenerIdPaciente(paciente).getId(), planTratamiento);
        } else {

        }
    }

    @FXML
    private void crearDiagnostico(MouseEvent event) {
        pacienteDao = new PacienteDAOImplementacion();
        paciente = new Paciente();
        diagnostico = new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText());
        paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
        if (!cajaDniDatosPrincipales.getText().isEmpty()) {
            pacienteDao.insertarDiagnostico(pacienteDao.obtenerIdPaciente(paciente).getId(), diagnostico);
        } else {

        }
    }

    @FXML
    private void crearObraSocialPaciente(MouseEvent event) {
        pacienteDao = new PacienteDAOImplementacion();
        paciente = new Paciente();
        obraSocialPaciente = new ObraSocialPaciente(Integer.parseInt(cajaNAfiliadoObraSocialPaciente.getText()), choiseNombreObraSocialPaciente.getValue().toString(), new PlanObraSocial(cajaPlanObraSocialPaciente.getText(), "Sin descripcion"));
        paciente.setDni(Integer.parseInt(cajaDniDatosPrincipales.getText()));
        if (!cajaDniDatosPrincipales.getText().isEmpty()) {
            pacienteDao.insertarObraSocialPaciente(pacienteDao.obtenerIdPaciente(paciente).getId(), obraSocialPaciente);
        } else {

        }
    }

    @FXML
    private void crearObraSocial(MouseEvent event) {
        obraSocialDao = new ObraSocialDAOImplementacion();
        obraSocial = new ObraSocial(cajaNombreObraSocial.getText(), new Telefono(cajaTelefonoObraSocial.getText()), new Web(cajaWebObraSocial.getText()), true, new Email(cajaEmailObraSocial.getText()));
        obraSocialDao.insertar(obraSocial);
    }

    public void validarSesion() {

    }

    public void validarPaciente() {
        MetodosComoParametros mcp = new MetodosComoParametros();

        Map<TextField, Method> mapValidar = ImmutableMap.of(cajaNombreDatosPrincipales, mcp.retornarMetodosValidar(3), cajaApellidoDatosPrincipales, mcp.retornarMetodosValidar(3),
                cajaEdadDatosPrincipales, mcp.retornarMetodosValidar(1), cajaDniDatosPrincipales, mcp.retornarMetodosValidar(3),
                cajaTelefonoDatosPrincipales, mcp.retornarMetodosValidar(3));

        validarCajasExedidasCaracteres(mapValidar);

    }

    private boolean esCajaValida(List<TextField> cajas) {
        HBox hb;
        ImageView img;
        for (TextField caja : cajas) {
            hb = (HBox) caja.getParent();
            if (hb.getChildren().size() < 2) {
                return false;
            }
            img = (ImageView) hb.getChildren().get(1);
            if (img.getId().equals("imgError") || img.getId().equals("imgVacio")) {
                return false;
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
            botones = ImmutableMap.of(botonRetornarDatosPrincipales, true, botonAgregarDatosPrincipales, false, botonActualizarDatosPrincipales, true, botonEliminarDatosPrincipales, true);
            setearVisibilidad(botones);
            blanquearCajas(listaCajas);
        }

    }

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
            System.out.println(acordionPaciente.getStylesheets().get(0));

        } else if (!tp.expandedProperty().get() && acordionPaciente.getStylesheets().get(0).equals(cssNuevo)) {
            acordionPaciente.getStylesheets().remove(cssNuevo);
            acordionPaciente.getStylesheets().add(cssViejo);
            System.out.println(acordionPaciente.getStylesheets().get(0));
        }

    }

   

    @FXML
    private void alDarEnterBuscarBoton(KeyEvent event) {
        
        if(event.getCode() == KeyCode.ENTER){
            if(!cajaBuscarPaciente.getText().isEmpty()){
                buscarPaciente();
            }
        }
    }

   
   

}
