/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.controlador.MenuInicioController;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;

import java.util.List;
import javafx.stage.Stage;
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
public class DatosPrincipalesDAOImplementacionTest {
   Paciente paciente;
   DatosPrincipalesDAOImplementacion dp = new DatosPrincipalesDAOImplementacion();
  
   UsuarioDAOImplementacion usuarioDao = new UsuarioDAOImplementacion();
   PacienteDAOImplementacion pacienteDAOImplementacion = new PacienteDAOImplementacion();
   
   Usuario usuarioTest = new Usuario();
   
    @Test
    public void testObtener() throws Exception {
       
        
        
        
        usuarioTest.setId(usuarioDao.obtenerId(new Usuario()));
        
        try {
            VariablesEstaticas.setUsuario(usuarioTest);
            
        } catch (Exception e) {
        }
       
        paciente = new Paciente();
        paciente.setId(4);
        paciente.setId(pacienteDAOImplementacion.obtenerId(new Paciente(38449500)));
        
        
        
        Paciente result = dp.obtener(paciente);
        
       
       
        
        assertEquals( "Bruno", result.getNombre());
        /*Paciente objetoParametro = null;
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

 
   /* @Test
    public void testActualizar() throws Exception {
        System.out.println("actualizar");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        instance.actualizar(objetoParametro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        instance.eliminar(objetoParametro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testInsertar() throws Exception {
        System.out.println("insertar");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        instance.insertar(objetoParametro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

 
    @Test
    public void testObtenerId() throws Exception {
        System.out.println("obtenerId");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        int expResult = 0;
        int result = instance.obtenerId(objetoParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testObtenerLista() throws Exception {
        System.out.println("obtenerLista");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        List<Paciente> expResult = null;
        List<Paciente> result = instance.obtenerLista(objetoParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
