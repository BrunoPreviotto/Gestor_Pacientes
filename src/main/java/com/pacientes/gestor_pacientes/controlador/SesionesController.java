/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;


import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.controlador.Paciente.DatosPrincipalesController;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.AutorizacionDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.CodigoFacturacionDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.SesionDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
import com.pacientes.gestor_pacientes.modelo.EstadoFacturacion;
import com.pacientes.gestor_pacientes.modelo.SesionPaciente;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.util.List;
import java.util.Objects;

import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;


import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javax.xml.transform.Source;

/**
 *
 * @author previotto
 */
public class SesionesController extends MenuInicioController implements Initializable, DraggedScene{

    @FXML
    public AnchorPane anchorSesiones;
    
    public String cajaBuscarPacientePasado;
    
    
    private SesionPaciente sesioneSeleccionada;

    public SesionPaciente getSesioneSeleccionada() {
        return sesioneSeleccionada;
    }

    public void setSesioneSeleccionada(SesionPaciente sesioneSeleccionada) {
        this.sesioneSeleccionada = sesioneSeleccionada;
    }
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         this.onDraggedScene(anchorSesiones);
        System.out.println("si se inicio");
          iniciarChoiceCodigoFacturacion();
         
        anchorSesiones.setStyle(servicioPadre.iniciarColorApp());
       iniciarlizarHtmlEditorSesion();
        
        choiseCodigoFactSesionObraSocial.setOnAction(this::cambiarCodigoFacturacion);
        
        //cajas autrizacion
         VariablesEstaticas.cajaAutorizacionSesion = cajaAutorizacionSesion;

         VariablesEstaticas.cajaAsociacionSesionObraSocial = cajaAsociacionSesionObraSocial;

         //VariablesEstaticas.cajaObservacionSesionObraSocial = htmlObservacionAutorizacion;

         VariablesEstaticas.cajaAtualizarCodigoFacturacionSesionObraSocial = cajaAtualizarCodigoFacturacionSesionObraSocial;

         VariablesEstaticas.cajaAtualizarNombreCodigoFacturacionSesionObraSocial = cajaAtualizarNombreCodigoFacturacionSesionObraSocial;

         VariablesEstaticas.cajaCodigoFacturacion = cajaCodigoFacturacion;
         
         VariablesEstaticas.cajaCopagoSesionObraSocial = cajaCopagoSesionObraSocial;
         
         VariablesEstaticas.choiseCodigoFactSesionObraSocial = choiseCodigoFactSesionObraSocial;
        
        VariablesEstaticas.cajasAreaSesion
                = Arrays.asList(
                        htmlTrabajoSesion,
                        htmlObservacionSesion,
                        htmlObservacionAutorizacion);
        
        VariablesEstaticas.cajasSesiones
                = Arrays.asList(
                        cajaAutorizacionSesion,
                        cajaCopagoSesionObraSocial,
                        cajaEstadoFacturacionSesionObraSocial,
                        cajaCodigoFacturacion,
                        cajaHonorariosPorSesion);
        
        VariablesEstaticas.datePickerSesiones
                = Arrays.asList(
                        cajaFechaSesion,
                        cajaAsociacionSesionObraSocial);

        VariablesEstaticas.choiseSesiones
                = Arrays.asList(
                        choiseCodigoFactSesionObraSocial);
         
        VariablesEstaticas.cajaObservacionSesionObraSocial = htmlObservacionAutorizacion;
         
        servicioPaciente.desPintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaSesion);
        
         servicioPaciente.
                            vaciarCajasAreaHTML(VariablesEstaticas.cajasAreaSesion).
                            vaciarCajas(VariablesEstaticas.cajasSesiones).
                            vaciarFechas(VariablesEstaticas.datePickerSesiones).
                            vaciarValorChoise(VariablesEstaticas.choiseSesiones);
    }
    
     public void iniciarChoiceCodigoFacturacion(){
         System.out.println("entro a iniciar choise");
         try {
             daoImplementacion = new CodigoFacturacionDAOImplementacion();
             List<CodigoFacturacion> listaCodigosFacturacion = daoImplementacion.obtenerLista(new CodigoFacturacion());
             if (!Objects.isNull(listaCodigosFacturacion)) {
                 
                 for (CodigoFacturacion h : listaCodigosFacturacion) {
                    choiseCodigoFactSesionObraSocial.getItems().add(h.getNombre());
                     System.out.println("sdadasdasd");
                 }

             }
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    public void setCajaBuscarPaciente(String cajaBuscarPaciente) {
        this.cajaBuscarPacientePasado = cajaBuscarPaciente;
    }
   
    
   
    
    @FXML
    public void crearSesion(MouseEvent event) {
      if (!cajaBuscarPacientePasado.isBlank()) {
            try {

                if (     htmlTrabajoSesion.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>")
                        || htmlTrabajoSesion.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") 
                        || htmlTrabajoSesion.getHtmlText().equals("")) {
                    
                    
                    
                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.
                            pintarCajaVaciaImportante(VariablesEstaticas.cajasSesiones).
                            pintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaSesion);
                    

                } else {
                    cajaFechaSesion.setValue(LocalDate.now());
                    SesionDAOImplementacion sesionDAOImplementacion = new SesionDAOImplementacion();
                    int ultimaSesion = sesionDAOImplementacion.obtenerultimaSesion(new Paciente(Integer.parseInt(cajaBuscarPacientePasado))) + 1;
                    cajaNumeroSesion.setText(String.valueOf(ultimaSesion));

                    

                    AutorizacionesSesionesObraSociales autorizacionesSesionesObraSociales;
                    
                    setearValoresCajasVaciasAutorizacionSesiones();

                    servicioPaciente.rellenarCajasAutorizacionVacias();

                    autorizacionesSesionesObraSociales = new AutorizacionesSesionesObraSociales(
                            Integer.valueOf(cajaAutorizacionSesion.getText()),
                            htmlObservacionAutorizacion.getHtmlText(), cajaAsociacionSesionObraSocial.getValue(),
                            Double.valueOf(cajaCopagoSesionObraSocial.getText()),
                            new CodigoFacturacion(choiseCodigoFactSesionObraSocial.getValue()));

                    servicioPaciente.
                            datosSesionCajasAreaVacios().datosSesionCajasVacios().datosSesionChoiceVacios();

                    SesionPaciente sesion = new SesionPaciente(Integer.valueOf(cajaNumeroSesion.getText()),
                            cajaFechaSesion.getValue(),
                            htmlTrabajoSesion.getHtmlText(),
                            htmlObservacionSesion.getHtmlText(),
                            Double.parseDouble(cajaHonorariosPorSesion.getText()),
                            new EstadoFacturacion(cajaEstadoFacturacionSesionObraSocial.getText()));
                    System.out.println("trabajo:" + htmlTrabajoSesion.getHtmlText());
                    
                    daoImplementacion = new PacienteDAOImplementacion();
                    int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPacientePasado)));
                    sesion.setIdPaciente(idPaciente);
                    autorizacionesSesionesObraSociales.setIdPaciente(idPaciente);
                    daoImplementacion = new SesionDAOImplementacion();
                    autorizacionesSesionesObraSociales.setIdSesion(daoImplementacion.obtenerId(sesion));

                    sesion.setAutorizacion(autorizacionesSesionesObraSociales);
                    if (!cajaBuscarPacientePasado.isBlank()) {

                        servicioPaciente.
                                datosSesionCajasAreaVacios().
                                datosSesionCajasVacios().
                                datosAutorizacionSesionVacios();
                        daoImplementacion = new SesionDAOImplementacion();
                        daoImplementacion.insertar(sesion);

                        mensajeAdvertenciaError("Sesion creado con éxito", this, VariablesEstaticas.imgenExito);
                        
                        
                    } else {
                        mensajeAdvertenciaError("Buscar paciente para crear sesión", this, VariablesEstaticas.imgenAdvertencia);
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
    
    public void rellenarCajasSesionesParaActualizar(){
         //SESION
            cajaFechaSesion.setValue(sesioneSeleccionada.getFecha());
            cajaNumeroSesion.setText(String.valueOf(sesioneSeleccionada.getNumeroSesion()));
            htmlTrabajoSesion.setHtmlText(sesioneSeleccionada.getTrabajoSesion());
            htmlObservacionSesion.setHtmlText(sesioneSeleccionada.getObservacion());
            cajaHonorariosPorSesion.setText(String.valueOf(sesioneSeleccionada.getHonorarioPorSesion()));
            cajaEstadoFacturacionSesionObraSocial.setText(sesioneSeleccionada.getEstado().getEstado());

            //AUTORIZACION
            cajaAutorizacionSesion.setText(String.valueOf(sesioneSeleccionada.getAutorizacion().getNumeroAutorizacion()));
            htmlObservacionAutorizacion.setHtmlText(sesioneSeleccionada.getAutorizacion().getObservacion());
            cajaAsociacionSesionObraSocial.setValue(sesioneSeleccionada.getAutorizacion().getAsociacion());
            cajaCopagoSesionObraSocial.setText(String.valueOf(sesioneSeleccionada.getAutorizacion().getCopago()));
            choiseCodigoFactSesionObraSocial.setValue(String.valueOf(sesioneSeleccionada.getAutorizacion().getCodigoFacturacion().getNombre()));
    }
    
    
    
    @FXML
    private void actualizarSesion(MouseEvent event) {

        try {
            //SI NO SE SELECCIONA LA SESION

            LocalDate ldAutorizacion = sesioneSeleccionada.getFecha();
            LocalDate ldSesion = sesioneSeleccionada.getAutorizacion().getAsociacion();

            SesionPaciente sesion = new SesionPaciente();
            AutorizacionesSesionesObraSociales autorizacion = new AutorizacionesSesionesObraSociales();
            CodigoFacturacion codigo = new CodigoFacturacion();

            SesionPaciente sesionBuscar = new SesionPaciente();
            AutorizacionesSesionesObraSociales autorizacionBuscar = new AutorizacionesSesionesObraSociales();

           
           
            servicioPaciente.rellenarListaSesionesAutorizaciones(sesioneSeleccionada.getAutorizacion());

            daoImplementacion = new PacienteDAOImplementacion();
            sesionBuscar.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPacientePasado))));
            autorizacionBuscar.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
            autorizacionBuscar.setAsociacion(ldAutorizacion);
            sesionBuscar.setAutorizacion(autorizacionBuscar);
            sesionBuscar.setFecha(ldSesion);
            sesionBuscar.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));

            daoImplementacion = new SesionDAOImplementacion();
            int idSesion = daoImplementacion.obtenerId(sesionBuscar);

            daoImplementacion = new PacienteDAOImplementacion();
            int idPaciente = daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPacientePasado)));

            LocalDate ldsNuevo = LocalDate.parse(cajaFechaSesion.getValue().toString());
            LocalDate ldsaNuevo = LocalDate.parse(cajaAsociacionSesionObraSocial.getValue().toString());

            //SI LAS CAJAS DE AUTORIZACION ESTAN VACIAS LAS RELLENA CON VALORES NULOS
            servicioPaciente.rellenarCajasAutorizacionVacias();
            daoImplementacion = new AutorizacionDAOImplementacion();
            autorizacion.setIdPaciente(idPaciente);
            autorizacion.setIdSesion(idSesion);
            autorizacion.setId(daoImplementacion.obtenerId(sesioneSeleccionada.getAutorizacion()));
            autorizacion.setNumeroAutorizacion(Integer.parseInt(cajaAutorizacionSesion.getText()));
            autorizacion.setAsociacion(ldsaNuevo);
            autorizacion.setObservacion(htmlObservacionAutorizacion.getHtmlText());
            autorizacion.setCopago(Double.parseDouble(cajaCopagoSesionObraSocial.getText()));
            codigo.setNombre(choiseCodigoFactSesionObraSocial.getValue());
            autorizacion.setCodigoFacturacion(codigo);

            sesion.setAutorizacion(autorizacion);
            //INICIALIZAR SESION
            sesion.setEstado(new EstadoFacturacion(cajaEstadoFacturacionSesionObraSocial.getText()));
            sesion.setIdSesion(idSesion);
            sesion.setNumeroSesion(Integer.parseInt(cajaNumeroSesion.getText()));
            sesion.setFecha(ldsNuevo);
            sesion.setTrabajoSesion(htmlTrabajoSesion.getHtmlText());
            sesion.setObservacion(htmlObservacionSesion.getHtmlText());
            sesion.setHonorarioPorSesion(Double.parseDouble(cajaHonorariosPorSesion.getText()));
            sesion.setIdPaciente(idPaciente);

            //ACTUALIZAR
            daoImplementacion = new SesionDAOImplementacion();
            daoImplementacion.actualizar(sesion);
            mensajeAdvertenciaError("Sesión actualizada con éxito sesión", this, VariablesEstaticas.imgenExito);
            
            

        } catch (Exception e) {
            e.printStackTrace();
            mensajeAdvertenciaError("Error al actualizar sesión", this, VariablesEstaticas.imgenError);
        }

    }
    
    
    
    
    public void setearValoresCajasVaciasAutorizacionSesiones(){
        
            VariablesEstaticas.cajaAutorizacionSesion.setText(cajaAutorizacionSesion.getText());
        
            VariablesEstaticas.cajaObservacionSesionObraSocial.setHtmlText(htmlObservacionAutorizacion.getHtmlText());
        
            VariablesEstaticas.cajaCopagoSesionObraSocial.setText(cajaCopagoSesionObraSocial.getText());
        
            VariablesEstaticas.choiseCodigoFactSesionObraSocial.setValue(choiseCodigoFactSesionObraSocial.getValue());
        
    }

    

  

    @FXML
    protected void salir(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }

   

    
    
    @FXML
    private void despintarCajaImportante(MouseEvent event){
        servicioPaciente.desPintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaSesion);
    }

    
    @FXML
    public void agrandarCajaParaVer(MouseEvent event){
        try {
            
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
            
             
            
            switch (ev.getId()) {
                case "botonVerTrabajoSesion":
                    textoAVer = htmlTrabajoSesion.getHtmlText();
                    break;
                case "botonVerObservacionSesion":
                   textoAVer = htmlObservacionSesion.getHtmlText();
                    break;
                case "botonVerObservacionAutorizacion":
                    textoAVer = htmlObservacionAutorizacion.getHtmlText();
                    break;
                
            }
            
            
            if(!cajaBuscarPacientePasado.equals("")){
               
               controller.llenarCaja(textoAVer);
               controller.setIdBoton(ev.getId());
               controller.setNumDNIPaciente(Integer.parseInt(cajaBuscarPacientePasado));
               stage.showAndWait(); 
            }
            
           
        
        } catch (IOException ex) {
            Logger.getLogger(MensajeAdvertenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    //SESIONES AGREGAR
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
    //SESIONES AGREGAR
    
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
    
    //SESIONES AGREGAR
    
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
    
    
    
    
    
    
    
    

   
}
