/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_paciente.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.PacienteDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author previotto
 */
public class EliminarPaciente extends PacienteDAOImplementacion {
    
    public void eliminarPaciente(Paciente pacienteParametro){
        try {
            String sqlEliminar = "UPDATE pacientes SET es_paciente = false WHERE dni=?";
            PreparedStatement pSeliminar = conexion.conexion().prepareStatement(sqlEliminar);
            pSeliminar.setInt(1, pacienteParametro.getDni());
            pSeliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
