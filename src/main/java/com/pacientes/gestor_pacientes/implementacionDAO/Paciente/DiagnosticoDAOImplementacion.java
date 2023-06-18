/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.DiagnosticoPaciente;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class DiagnosticoDAOImplementacion extends PadreDAOImplementacion implements CRUD<DiagnosticoPaciente>{

  

    @Override
    public DiagnosticoPaciente obtener(DiagnosticoPaciente objeto) throws SQLException {
        DiagnosticoPaciente diagnostico = new DiagnosticoPaciente();
        
        try {
            String sqlDiagnostico = "SELECT diagnostico, observacion FROM diagnosticos WHERE id_paciente=?";
            PreparedStatement psDiagnostico = conexion.conexion().prepareStatement(sqlDiagnostico);
            psDiagnostico.setInt(1, objeto.getIdPaciente());
            ResultSet rsDiagnostico = psDiagnostico.executeQuery();
            
            
           if(rsDiagnostico.next()){
                diagnostico = new DiagnosticoPaciente(rsDiagnostico.getString("diagnostico"), rsDiagnostico.getString("observacion"));
                return diagnostico;
           }
           
            return null;
            
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void actualizar(DiagnosticoPaciente objetoParametro) throws SQLException {
        String sqlDiagnostico = "UPDATE diagnosticos SET diagnostico=?, observacion=? WHERE id_paciente=?";
        
        try {
            
            PreparedStatement psDiagnostico = conexion.conexion().prepareStatement(sqlDiagnostico);
            psDiagnostico.setString(1, objetoParametro.getDiagnostico());
            psDiagnostico.setString(2, objetoParametro.getObservacion());
            psDiagnostico.setInt(3, objetoParametro.getIdPaciente());
            psDiagnostico.executeQuery();
            
            
        } catch (Exception e) {
        }
    }

    @Override
    public void eliminar(DiagnosticoPaciente objetoParametro) throws SQLException {
        String sqlEliminarDiagnostico = "DELETE FROM diagnosticos WHERE id_paciente = ?;";
        PreparedStatement psEliminarDiagnostico = conexion.conexion().prepareStatement(sqlEliminarDiagnostico);
        psEliminarDiagnostico.setInt(1, objetoParametro.getIdPaciente());
        psEliminarDiagnostico.executeUpdate();
    }

    @Override
    public void insertar(DiagnosticoPaciente objetoParametro) throws Exception {
        String sqlDiagnostico = "INSERT INTO diagnosticos (id_diagnostico, diagnostico, observacion, id_paciente) VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlDiagnostico);
            pst.setInt(1, 0);
            pst.setString(2, objetoParametro.getDiagnostico());
            pst.setString(3, objetoParametro.getObservacion());
            pst.setInt(4, objetoParametro.getIdPaciente());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int obtenerId(DiagnosticoPaciente objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DiagnosticoPaciente> obtenerLista(DiagnosticoPaciente objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    
    
   
    
}
