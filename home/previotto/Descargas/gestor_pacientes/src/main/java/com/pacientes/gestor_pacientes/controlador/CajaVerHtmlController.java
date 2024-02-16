/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;


import com.pacientes.gestor_pacientes.controlador.Paciente.DiagnosticoController;

import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.servicios.ServiciosPadre;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author previotto
 */
public class CajaVerHtmlController extends ClasePadreController implements Initializable, DraggedScene {

    @FXML
    private Button botonMaximizarDesmaximizado;
    @FXML
    private Button botonMinimizar;
    @FXML
    private Button botonAgregarDiagnostico;
    @FXML
    private Button botonActualizarDiagnostico;
    
    @FXML
    private AnchorPane anchorHTML;
    
    
    @FXML
    private HTMLEditor cajaHtmlVer;
    
    private int numDNIPaciente;
    
    private String idBoton;
   
    private ServiciosPadre serviciosPadre = new ServiciosPadre();
   

    public void setIdBoton(String idBoton) {
        this.idBoton = idBoton;
    }
    
    

    public void setNumDNIPaciente(int numDNIPaciente) {
        this.numDNIPaciente = numDNIPaciente;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorHTML.setStyle(serviciosPadre.iniciarColorApp());
        this.onDraggedScene(anchorHTML);
        
    }    

    public void setCajaHtmlVer(HTMLEditor cajaHtmlVer) {
        this.cajaHtmlVer = cajaHtmlVer;
    }

    
    public void llenarCaja(String texto){
        cajaHtmlVer.setHtmlText(texto);
        if(texto.isBlank() || texto.equals("<html><head></head><body contenteditable=\"true\"></body></html>") || texto.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")){
            botonAgregarDiagnostico.setDisable(false);
        }else{
            botonActualizarDiagnostico.setDisable(false);
            
        }
    }

    
    @FXML
    protected void salir(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    
    


    @FXML
    private void crear(MouseEvent event){
        switch (idBoton) {
            case "botonVerDiagnostico":
                crearDiagnostico(event);
                break;
            case "botonVerObservacionDiagnostico":
                crearObservacionDiagnostico(event);
                break;
            case "botonVerTrabajoSesion":
                setearVer(1);
                break;
            case "botonVerObservacionSesion":
                setearVer(2);
                break;
            case "botonVerObservacionAutorizacion":
                setearVer(3);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void setearVer(int num){
        switch (num) {
            case 1:
                VariablesEstaticas.cajasAreaSesion.get(0).setHtmlText(cajaHtmlVer.getHtmlText());
                break;
                
            case 2:
                VariablesEstaticas.cajasAreaSesion.get(1).setHtmlText(cajaHtmlVer.getHtmlText());
                break;
                
            case 3:
                VariablesEstaticas.cajasAreaSesion.get(2).setHtmlText(cajaHtmlVer.getHtmlText());
                break;
            default:
                throw new AssertionError();
        }
        Stage stage = (Stage)anchorHTML.getScene().getWindow();
        stage.close();
    }
    
    
    
    
    
    @FXML
    private void actualizar(MouseEvent event) {
        switch (idBoton) {
            case "botonVerDiagnostico":
                actualizarDiagnostico(event);
                break;
            case "botonVerObservacionDiagnostico":
                actualizarObservacionDiagnostico(event);
                break;
            case "botonVerTrabajoSesion":
                setearVer(1);
                break;
            case "botonVerObservacionSesion":
                setearVer(2);
                break;
            case "botonVerObservacionAutorizacion":
                setearVer(3);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void crearDiagnostico(MouseEvent event) {
        DiagnosticoController diagnosticoController = new DiagnosticoController();
        int idPaciente;
        
        try {
            daoImplementacion = new PacienteDAOImplementacion();
            idPaciente = daoImplementacion.obtenerId(new Paciente(numDNIPaciente));
        } catch (Exception e) {
            idPaciente = 0;
        }
        
        
        if( idPaciente != 0){
            if(!VariablesEstaticas.cajasAreaDiagnostico.get(1).getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") || !VariablesEstaticas.cajasAreaDiagnostico.get(1).getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")){
                diagnosticoController.crear(true, new DiagnosticoPaciente(cajaHtmlVer.getHtmlText(), VariablesEstaticas.valoresBUsquedaDiagnosticoHTML.get("2"), idPaciente)); 
                
            }else{
                diagnosticoController.crear(true, new DiagnosticoPaciente(cajaHtmlVer.getHtmlText(), "--------", idPaciente)); 
            }
        }
         
        salir(event);
       
    }
    
    private void crearObservacionDiagnostico(MouseEvent event) {
        DiagnosticoController diagnosticoController = new DiagnosticoController();
        int idPaciente;
        
        try {
            daoImplementacion = new PacienteDAOImplementacion();
            idPaciente = daoImplementacion.obtenerId(new Paciente(numDNIPaciente));
        } catch (Exception e) {
            idPaciente = 0;
        }
        
        
        if( idPaciente != 0){
            if(VariablesEstaticas.cajasAreaDiagnostico.get(0).getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") || VariablesEstaticas.cajasAreaDiagnostico.get(0).getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")){
                mensajeAdvertenciaError( "Para crear observación primero debe crear el diagnostico", this, VariablesEstaticas.imgenAdvertencia);
                
            }else{
                diagnosticoController.actualizar(true, new DiagnosticoPaciente(VariablesEstaticas.cajasAreaDiagnostico.get(0).getHtmlText(), cajaHtmlVer.getHtmlText(), idPaciente)); 
            }
        }
            
        salir(event);
    }
    
    private void actualizarDiagnostico(MouseEvent event) {
        DiagnosticoController diagnosticoController = new DiagnosticoController();
        int idPaciente;
        
        try {
            daoImplementacion = new PacienteDAOImplementacion();
            idPaciente = daoImplementacion.obtenerId(new Paciente(numDNIPaciente));
        } catch (Exception e) {
            idPaciente = 0;
        }
        
        
        if( idPaciente != 0){
            if(!VariablesEstaticas.cajasAreaDiagnostico.get(1).getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>") || !VariablesEstaticas.cajasAreaDiagnostico.get(1).getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")){
                diagnosticoController.actualizar(true, new DiagnosticoPaciente(cajaHtmlVer.getHtmlText(), VariablesEstaticas.valoresBUsquedaDiagnosticoHTML.get("2"), idPaciente)); 
                
            }else{
                diagnosticoController.actualizar(true, new DiagnosticoPaciente(cajaHtmlVer.getHtmlText(), "--------", idPaciente)); 
            }
        }
            salir(event);
    }
    
    private void actualizarObservacionDiagnostico(MouseEvent event) {
        DiagnosticoController diagnosticoController = new DiagnosticoController();
        int idPaciente;
        
        try {
            daoImplementacion = new PacienteDAOImplementacion();
            idPaciente = daoImplementacion.obtenerId(new Paciente(numDNIPaciente));
        } catch (Exception e) {
            idPaciente = 0;
        }
        
        
        if( idPaciente != 0){
           diagnosticoController.actualizar(true, new DiagnosticoPaciente(VariablesEstaticas.valoresBUsquedaDiagnosticoHTML.get("1"), cajaHtmlVer.getHtmlText(), idPaciente)); 
        }
            
        salir(event);
    }

 

   

    
    
    
    
}
