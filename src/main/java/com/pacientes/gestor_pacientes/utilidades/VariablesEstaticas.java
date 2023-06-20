/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author previotto
 */
public class VariablesEstaticas {
    
    //principal
    public static Scene scenePrincipalVar;
    
    //valida email
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final String CHEQUEAR_DATOS = "Corrigir datos inválidos";
    public static  final String NO_NUMEROS = "Ingresar solo texto";
    public static  final String NO_TEXTO = "Ingresar solo números";
    public static final String EXEDIDO_CARACTERES = "Exedido de caracteres";
    public static final String CONTRASEÑA_NO_COINCIDEN = "Las contraseñas no coinciden";
    public static final String EMAIL_NO_VALIDO = "El email tiene un formato no válido";
    
    public static BiMap<List<CheckBox>, Boolean> valoresElimenarSesion = HashBiMap.create();
    
    //USUARIO
    public static Usuario usuario = new Usuario();
    
    
    //PACIENTE
    
    //CAJAS AUTORIZACION
    
    
    public static TextField cajaAutorizacionSesion;
    
    public static DatePicker cajaAsociacionSesionObraSocial;
    
    public static TextArea cajaObservacionSesionObraSocial;
    
    public static TextField cajaAtualizarCodigoFacturacionSesionObraSocial;
    
    public static TextField cajaAtualizarNombreCodigoFacturacionSesionObraSocial;
    
    public static TextField cajaCodigoFacturacion;
    
    public static TextField cajaCopagoSesionObraSocial;
    
    public static ChoiceBox<String> choiseCodigoFactSesionObraSocial;
    
    
    //LISTAS CONTROLES
    //LISTA CAJAS
    public static List<TextField> cajasOpcionesUsuario;
    public static List<TextField> cajasDatosPrincipales;
    public static List<TextField> cajasSesiones;
    public static List<TextField> cajasPlanes;
    public static List<TextField> cajasObraSocialPaciente;
    //LISTA TEXTAREA
    public static List<TextArea> cajasAreaSesion;
    public static List<TextArea> cajasAreaPlan;
    public static List<TextArea> cajasAreaDiagnostico;
    //LISTA TABLES
    public static List<TableView> tableSesiones;
    //LISTA CHOISE
    public static List<ChoiceBox> choiseSesiones;
    public static List<ChoiceBox> choisePlan;
    public static List<ChoiceBox> choiseObraSocialPaciente;
    //LISTA DATEPICKER
    public static List<DatePicker> datePickerSesiones;
    //LISTA VBOX PLANES
    public static List<VBox> vboxsPlanesTratamiento;
    public static List<VBox> vboxsPlanesTratamientoActualizaroVer;
    //LISTA VBOX OBRA SOCIAL PACIENTE
    public static List<VBox> vboxsObraSocialPaciente;
    public static List<VBox> vboxsObraSocialPacienteActualizaroVer;
    
    
    //LISTAS VALORES PACIENTE ACTUAL
    public static Map<String, String> valoresBUsquedaDatosPrincipales;
    public static Map<String, String> valoresBUsquedaSesiones;
    public static Map<String, String> valoresBUsquedaSesionesAtorizacion;
    public static Map<String, String> valoresBUsquedaListaSesiones;
    public static Map<String, String> valoresBUsquedaListaSesionesAutorizacion;
    public static Map<String, String> valoresBUsquedaPlanes;
    public static Map<String, String> valoresBUsquedaDiagnostico;
    public static Map<String, String> valoresBUsquedaObraSocialPaciente;
    
    
    //BOTONES
    public static List<Button> listaBotonesCrear;
    public static List<Button> listaBotonesActualizar;
    public static List<Button> listaBotonesEliminar;
    
    //OBRA SOCIAL
    //LISTAS VALORES PACIENTE ACTUAL
    public static Map<String, String> valoresBUsquedaObraSocial;
    
    //CAJAS
    public static List<TextField> cajasObrasSociales;
    
    //CHOICE
    public static List<ChoiceBox> choiceObraSocial;
    
    //AGENDA
    //ANCHOR 
    public static AnchorPane anchorPrincipalAgenda;
    public static VBox anchorAgendaAgenda;
    
    //GRID PANE 
    public static GridPane gridAgenda;
    
    //REGISTRAR
    //LISTA CAJAS
     public static List<TextField> cajasRegistrar;
    
    //IMAGENES
    public static final String imgenExito = "/com/pacientes/gestor_pacientes/img/exito.png";
    public static final String imgenError = "/com/pacientes/gestor_pacientes/img/error.png";
    
    public static final String imgenAdvertencia = "/com/pacientes/gestor_pacientes/img/warning.png";
    
    public static final Image imagenVer = new Image("/com/pacientes/gestor_pacientes/img/ver.png");
    public static final Image imagenAgregar = new Image("/com/pacientes/gestor_pacientes/img/lapiz.png");
    public static final Image imagenRecordar = new Image("/com/pacientes/gestor_pacientes/img/recordatorio.png");
     
     //MENSAJES
    //MENSAJEPREGUNTARSIONO
    public static boolean esSiONoMensajePrguntarSiONo;
   
    //ACORDEON
    public static List<TitledPane> listaContenedoresAcordeon;
    public static TitledPane tabDatosPricipales;
    
    
}
