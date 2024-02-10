/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador.Paciente;

import com.pacientes.gestor_pacientes.controlador.ClasePadreMenuInicio;
import com.pacientes.gestor_pacientes.controlador.MenuInicioController;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.DiagnosticoDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.util.Map;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
//import org.jsoup.Jsoup;


/**
 *
 * @author previotto
 */
public class DiagnosticoController extends ClasePadreMenuInicio{
    
    
    public void buscarDiagnostico(Paciente pacienteResultado) {
        
        if (Objects.nonNull(pacienteResultado.getDiagnostico())) {
            
            VariablesEstaticas.valoresBUsquedaDiagnosticoHTML = Map.of("1", pacienteResultado.getDiagnostico().getDiagnostico(), "2", pacienteResultado.getDiagnostico().getObservacion());
            
            //cajaDiagnosticoDiagnostico.setText(Jsoup.parse(pacienteResultado.getDiagnostico().getDiagnostico()).text());
            //cajaObservacionDiagnostico.setText(Jsoup.parse(pacienteResultado.getDiagnostico().getObservacion()).text());
            //.setText(Jsoup.parse(pacienteResultado.getDiagnostico().getDiagnostico()).text());
            //cajaObservacionDiagnosticoJFX.setText(Jsoup.parse(pacienteResultado.getDiagnostico().getObservacion()).text());
            
            
             htmlDiagnostico.setHtmlText(pacienteResultado.getDiagnostico().getDiagnostico());
            
            System.out.println(pacienteResultado.getDiagnostico().getDiagnostico());
           
            htmlObservacionDiagnostico.setHtmlText(pacienteResultado.getDiagnostico().getObservacion());
            
            servicioPaciente.
                    rellenarListaDiagnostico(pacienteResultado).
                    deshabilitarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico);
                    botonEliminarDiagnostico.setDisable(false);

        } else {
            VariablesEstaticas.valoresBUsquedaDiagnosticoHTML = Map.of("1", "", "2", "");
            servicioPaciente.
                    vaciarListaDiagnostico().
                    vaciarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico).
                    habilitarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico);
                    botonEliminarDiagnostico.setDisable(true);
        }

    }
    
    
     @FXML
    public void eliminarDiagnostico(MouseEvent event) {
        mensajePreguntarSiONo();
        if (VariablesEstaticas.esSiONoMensajePrguntarSiONo) {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isEmpty()) {
                //SI LAS CAJAS IMPORTANTES NO ESTAN VACIAS
                if (!htmlDiagnostico.getHtmlText().equals("<html><head></head><body contenteditable=\"true\"></body></html>")) {
                    try {
                        DiagnosticoPaciente diagnosticoPaciente = new DiagnosticoPaciente();
                        daoImplementacion = new PacienteDAOImplementacion();
                        diagnosticoPaciente.setIdPaciente(daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                        daoImplementacion = new DiagnosticoDAOImplementacion();
                        daoImplementacion.eliminar(diagnosticoPaciente);
                        mensajeAdvertenciaError("Diagnóstico eliminado con éxito", this, VariablesEstaticas.imgenExito);
                        servicioPaciente.
                                vaciarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico);
                                botonEliminarDiagnostico.setDisable(true);
                        
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
    public void crearDiagnostico(MouseEvent event) {
        /*DiagnosticoController d = new DiagnosticoController();
        try {
            daoImplementacion = new PacienteDAOImplementacion();
            d.crear(cajaBuscarPaciente.getText().isBlank(), new DiagnosticoPaciente(cajaDiagnosticoDiagnostico.getText(), cajaObservacionDiagnostico.getText(), daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText())))));
            buscarPaciente();
        } catch (Exception e) {
            
        }*/
        MenuInicioController mi = new MenuInicioController();
        mi.buscarPaciente();
        /*try {
            //SI SE BUSCO AL PACIENTE
            if (!cajaBuscarPaciente.getText().isBlank()) {
                daoImplementacion = new PacienteDAOImplementacion();
                DiagnosticoPaciente diagnostico = new DiagnosticoPaciente(cajaDiagnosticoDiagnosticoJFX.getText(), cajaObservacionDiagnosticoJFX.getText(), daoImplementacion.obtenerId(new Paciente(Integer.parseInt(cajaBuscarPaciente.getText()))));
                //SI DATOS IMPORTANTES TIENEN TEXTO
                if (cajaDiagnosticoDiagnosticoJFX.getText().isBlank()) {
                    servicioPaciente.pintarCajaAreaVaciaImportanteJFX(VariablesEstaticas.cajasAreaDiagnostico);
                    mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                } else {
                    mensajeAdvertenciaError("Diagnostico creado con èxito", this, VariablesEstaticas.imgenExito);
                    servicioPaciente.datosDiagnosticoVacios();
                    daoImplementacion = new DiagnosticoDAOImplementacion();
                    daoImplementacion.insertar(diagnostico);
                    MenuInicioController mi = new MenuInicioController();
                            mi.buscarPaciente();
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para crear Diagnóstico", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            mensajeAdvertenciaError("Error al crear diagnóstico", this, VariablesEstaticas.imgenError);
        }*/
    }
    
    
    
    public void crear( boolean existePaciente ,DiagnosticoPaciente diagnosticoParametro){
        
         try {
            //SI SE BUSCO AL PACIENTE
            if (existePaciente) {
                daoImplementacion = new PacienteDAOImplementacion();
                DiagnosticoPaciente diagnostico = diagnosticoParametro;
                //SI DATOS IMPORTANTES TIENEN TEXTO
                if (diagnostico.getDiagnostico().isBlank()) {
                    servicioPaciente.pintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaDiagnostico);
                    mensajeAdvertenciaError("Hay campos importantes vacios", this, VariablesEstaticas.imgenAdvertencia);
                } else {
                    
                    servicioPaciente.datosDiagnosticoVacios();
                    daoImplementacion = new DiagnosticoDAOImplementacion();
                    daoImplementacion.insertar(diagnostico);
                    mensajeAdvertenciaError("Diagnostico creado con èxito", this, VariablesEstaticas.imgenExito);
                }
            } else {
                mensajeAdvertenciaError("Buscar paciente para crear Diagnóstico", this, VariablesEstaticas.imgenAdvertencia);
            }

        } catch (Exception e) {
            mensajeAdvertenciaError("Error al crear diagnóstico", this, VariablesEstaticas.imgenError);
        }
    }
    
    public void actualizar(boolean existePaciente ,DiagnosticoPaciente diagnosticoParametro){
        //
        
        
        
            //SI SE BUSCO AL PACIENTE
            if (existePaciente) {
                //SI LA CAJA DIAGNOSTICO NO ESTA VACIA
                if (diagnosticoParametro.getDiagnostico().equals("-")) {
                    //MENSAJE DE ERROR AL TENER CAJAS VACIAS
                    mensajeAdvertenciaError( "Completar la caja de diagnostico", this, VariablesEstaticas.imgenAdvertencia);
                    //PINTAR CAJAS IMPORTATES VACIAS AL CREAR
                    servicioPaciente.pintarCajaAreaVaciaImportanteHTML(VariablesEstaticas.cajasAreaDiagnostico);
                } else {
                    try {
                        
                        //PINTAR CAJAS SI ESTAN VACIAS
                        servicioPaciente.datosDiagnosticoVacios();
                        Paciente paciente = new Paciente();
                        //ACTUALIZAR
                        daoImplementacion = new DiagnosticoDAOImplementacion();
                        daoImplementacion.actualizar(diagnosticoParametro);
                        
                        servicioPaciente.deshabilitarCajasAreaHTML(VariablesEstaticas.cajasAreaDiagnostico);

                        mensajeAdvertenciaError( "Diagnóstico actualizado con éxito", this, VariablesEstaticas.imgenExito);

                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        mensajeAdvertenciaError( "Error al actualizar diagnóstico", this, VariablesEstaticas.imgenError);
                    }
                }
            } else {
                mensajeAdvertenciaError( "Buscar paciente para actualizar", this, VariablesEstaticas.imgenAdvertencia);
            }
        
    }
    
   
}
