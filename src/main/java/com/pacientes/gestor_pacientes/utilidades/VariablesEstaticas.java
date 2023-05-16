/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author previotto
 */
public class VariablesEstaticas {
    //valida email
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final String CHEQUEAR_DATOS = "Corrigir datos inválidos";
    public static  final String NO_NUMEROS = "Ingresar solo texto";
    public static  final String NO_TEXTO = "Ingresar solo números";
    public static final String EXEDIDO_CARACTERES = "Exedido de caracteres";
    public static final String CONTRASEÑA_NO_COINCIDEN = "Las contraseñas no coinciden";
    public static final String EMAIL_NO_VALIDO = "El email tiene un formato no válido";
    
    public static BiMap<List<CheckBox>, Boolean> valoresElimenarSesion = HashBiMap.create();
    
    //PACIENTE
    
    //LISTAS CONTROLES
    //LISTA CAJAS
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
    
    
    //LISTAS VALORES PACIENTE ACTUAL
    public static Map<String, String> valoresBUsquedaDatosPrincipales;
    public static Map<String, String> valoresBUsquedaSesiones;
    public static Map<String, String> valoresBUsquedaListaSesiones;
    public static Map<String, String> valoresBUsquedaPlanes;
    public static Map<String, String> valoresBUsquedaDiagnostico;
    public static Map<String, String> valoresBUsquedaObraSocialPaciente;
    
    
    
}
