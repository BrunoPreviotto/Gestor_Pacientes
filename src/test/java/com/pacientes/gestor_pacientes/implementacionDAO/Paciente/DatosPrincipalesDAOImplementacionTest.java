/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import java.sql.SQLException;
import java.util.List;
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
    /***
     * Objeto para ejecutar el crud de datosPrincipales
     */
    CRUD dao = new DatosPrincipalesDAOImplementacion();
    
    public DatosPrincipalesDAOImplementacionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of obtener method, of class DatosPrincipalesDAOImplementacion.
     */
    @Test
    public void testObtener() throws Exception {
        
        try {
            System.out.println("obtener");
            Paciente objetoParametro = new Paciente(38449500);

            Paciente expResult = null;
            Paciente result = (Paciente)dao.obtener(objetoParametro);
        assertEquals("Bruno", result.getNombre());
        } catch (ClassNotFoundException | SQLException e) {
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
            e.printStackTrace();
        }
        
    }

    /**
     * Test of actualizar method, of class DatosPrincipalesDAOImplementacion.
     */
    @Test
    public void testActualizar() throws Exception {
        System.out.println("actualizar");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        instance.actualizar(objetoParametro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class DatosPrincipalesDAOImplementacion.
     */
    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        instance.eliminar(objetoParametro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertar method, of class DatosPrincipalesDAOImplementacion.
     */
    @Test
    public void testInsertar() throws Exception {
        System.out.println("insertar");
        Paciente objetoParametro = null;
        DatosPrincipalesDAOImplementacion instance = new DatosPrincipalesDAOImplementacion();
        instance.insertar(objetoParametro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerId method, of class DatosPrincipalesDAOImplementacion.
     */
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

    /**
     * Test of obtenerLista method, of class DatosPrincipalesDAOImplementacion.
     */
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
    }
    
}
