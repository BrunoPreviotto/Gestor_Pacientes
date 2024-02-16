/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import com.pacientes.gestor_pacientes.modelo.Afiliado;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.FrecuenciaSesion;
import com.pacientes.gestor_pacientes.modelo.ObraSocialPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.PlanObraSocial;
import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import com.pacientes.gestor_pacientes.modelo.TipoSesion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.util.List;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author previotto
 */
public class ServicioPacienteTest {
    
    ServicioPaciente instance = new ServicioPaciente();
    
    public ServicioPacienteTest() {
    }
    
    

   
    /*@Test
    public void testOcultarLIstVBox() {
        System.out.println("ocultarLIstVBox");
        List<VBox> listaVBox = null;
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.ocultarLIstVBox(listaVBox);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

   
   /* @Test
    public void testVisibilizarLIstVBox() {
        System.out.println("visibilizarLIstVBox");
        List<VBox> listaVBox = null;
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.visibilizarLIstVBox(listaVBox);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    
    /*@Test
    public void testVaciarTodo() {
        System.out.println("vaciarTodo");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.vaciarTodo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
    
    @Test
    public void testRellenarListaDatosPrincipales() {
        System.out.println("rellenarListaDatosPrincipales");
        Paciente pacienteParametro = new Paciente("Bruno", "Previotto", 25, 38449500, new Telefono("00000000"));
        instance.rellenarListaDatosPrincipales(pacienteParametro);
        
        assertEquals(pacienteParametro.getNombre(), VariablesEstaticas.valoresBUsquedaDatosPrincipales.get("1"));
    }

    
    @Test
    public void testVaciarListaDatosPrincipales() {
        instance.vaciarListaDatosPrincipales();
        assertEquals("", VariablesEstaticas.valoresBUsquedaDatosPrincipales.get("1"));
        
    }

   
   /* @Test
    public void testRellenarListaSesiones() {
        System.out.println("rellenarListaSesiones");
        Paciente pacienteParametro = null;
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.rellenarListaSesiones(pacienteParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testRellenarListaSesionesAutorizaciones() {
        System.out.println("rellenarListaSesionesAutorizaciones");
        AutorizacionesSesionesObraSociales autorizacion = null;
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.rellenarListaSesionesAutorizaciones(autorizacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  
    @Test
    public void testVaciarListaSesiones() {
        System.out.println("vaciarListaSesiones");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.vaciarListaSesiones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testVaciarListaSesionesAutorizacion() {
        System.out.println("vaciarListaSesionesAutorizacion");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.vaciarListaSesionesAutorizacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testHabilitarTodo() {
        System.out.println("habilitarTodo");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.habilitarTodo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

   
    @Test
    public void testRellenarListaPlan() {
        
        Paciente pacienteParametro = new Paciente();
        pacienteParametro.setPlanTratamiento(
                new PlanTratamiento(
                        "Estrategia", 
                        new FrecuenciaSesion("Frecuencia"), 
                        new TipoSesion("Tipo sesion", "Descripcion")));
        instance.rellenarListaPlan(pacienteParametro);
        assertEquals("Estrategia", VariablesEstaticas.valoresBUsquedaPlanes.get("4"));
        
    }

  
    @Test
    public void testVaciarListaPlan() {
        instance.vaciarListaPlan();
        assertEquals("", VariablesEstaticas.valoresBUsquedaPlanes.get("4"));
        
    }

   
    @Test
    public void testRellenarListaDiagnostico() {
        
        Paciente pacienteParametro = new Paciente();
        pacienteParametro.setDiagnostico(new DiagnosticoPaciente("Diagnostico", "Observacion"));
        
        instance.rellenarListaDiagnostico(pacienteParametro);
        assertEquals("Diagnostico", VariablesEstaticas.valoresBUsquedaDiagnostico.get("1"));
        
    }

    
    @Test
    public void testVaciarListaDiagnostico() {
        instance.vaciarListaDiagnostico();
        assertEquals("", VariablesEstaticas.valoresBUsquedaDiagnostico.get("1"));
        
        
    }

    
    @Test
    public void testRellenarListaObrasocialPaciente() {
        
        Paciente pacienteParametro = new Paciente();
        pacienteParametro.setObraSocialPaciente(new ObraSocialPaciente(new Afiliado(000000000), "Obra social", new PlanObraSocial("Plan", "Descripcion")));
        
        
        instance.rellenarListaObrasocialPaciente(pacienteParametro);
        assertEquals("Obra social", pacienteParametro.getObraSocialPaciente().getNombre());
        
    }

    
    @Test
    public void testVaciarListaObrasocialPaciente() {
        
        instance.vaciarListaObrasocialPaciente();
        assertEquals("", VariablesEstaticas.valoresBUsquedaObraSocialPaciente.get("1"));
        
    }

    
    /*@Test
    public void testVaciarListas() {
        System.out.println("vaciarListas");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.vaciarListas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  
    @Test
    public void testComprobarTamañoStringDatosPrincipales() {
        System.out.println("comprobarTama\u00f1oStringDatosPrincipales");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.comprobarTamañoStringDatosPrincipales();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testDatosSesionChoiceVacios() {
        System.out.println("datosSesionChoiceVacios");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosSesionChoiceVacios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testDatosPrincipalesVacios() {
        System.out.println("datosPrincipalesVacios");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosPrincipalesVacios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testDatosSesion() {
        System.out.println("datosSesion");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosSesion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testDatosSesionAutorizacion() {
        System.out.println("datosSesionAutorizacion");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosSesionAutorizacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testDatosPlanVacios() {
        System.out.println("datosPlanVacios");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosPlanVacios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testDatosDiagnosticoVacios() {
        System.out.println("datosDiagnosticoVacios");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosDiagnosticoVacios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testDatosSesionCajasAreaVacios() {
        System.out.println("datosSesionCajasAreaVacios");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosSesionCajasAreaVacios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testDatosSesionCajasVacios() {
        System.out.println("datosSesionCajasVacios");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosSesionCajasVacios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testDatosAutorizacionSesionVacios() {
        System.out.println("datosAutorizacionSesionVacios");
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.datosAutorizacionSesionVacios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testComprobarSiAcordeonEstaCerrado() {
        System.out.println("comprobarSiAcordeonEstaCerrado");
        List<TitledPane> listaTitled = null;
        ServicioPaciente instance = new ServicioPaciente();
        ServicioPaciente expResult = null;
        ServicioPaciente result = instance.comprobarSiAcordeonEstaCerrado(listaTitled);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testRellenarCajasAutorizacionVacias() {
        System.out.println("rellenarCajasAutorizacionVacias");
        ServicioPaciente instance = new ServicioPaciente();
        instance.rellenarCajasAutorizacionVacias();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
