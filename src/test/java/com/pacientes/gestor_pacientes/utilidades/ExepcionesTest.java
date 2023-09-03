/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

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
public class ExepcionesTest {
    
    public ExepcionesTest() {
    }
    
    

    /**
     * Test of getMessage method, of class Exepciones.
     */
    @Test
    public void testGetMessage() {
        
        Exepciones instance = new Exepciones(111);
        String expResult = "Ya existe paciente registrado con ese DNI";
        String result = instance.getMessage();
        
        assertEquals(expResult, result);
        
    }
    
}
