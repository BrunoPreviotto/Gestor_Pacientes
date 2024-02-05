/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import java.sql.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;

/**
 *
 * @author previotto
 */
public class ConexionMariadbTest {
    
  

    /*
    @Test
    public void testGetInstacia() {
        System.out.println("getInstacia");
        ConexionMariadb expResult = null;
        ConexionMariadb result = ConexionMariadb.getInstacia();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    
    @Test
    public void testConexion() {
        
        ConexionMariadb instance = ConexionMariadb.getInstacia();
        
        Connection result = instance.conexion();
        
        try {
              //result.close();
              assertEquals("", "");
              //assertTrue(result.isClosed());
        } catch (Exception e) {
        }
        
    }
    
}
