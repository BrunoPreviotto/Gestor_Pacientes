/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.FrecuenciaSesion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author previotto
 */
public class FrecuenciaSesionPlanDAOImplementacion extends PadreDAOImplementacion implements CRUD<FrecuenciaSesion>{

    

    @Override
    public FrecuenciaSesion obtener(FrecuenciaSesion objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(FrecuenciaSesion objetoParametro) throws SQLException {
        String sqlActualizarFrecuencia = "UPDATE frecuencias_sesiones SET frecuencia = ? WHERE id_frecuencia_sesion = ? AND id_usuario = ?;";
        
        PreparedStatement psFrecuencia = conexion.conexion().prepareStatement(sqlActualizarFrecuencia);
        psFrecuencia.setString(1, objetoParametro.getFrecuencia());
        psFrecuencia.setInt(2, objetoParametro.getIdFrecuencia());
        psFrecuencia.setInt(3, VariablesEstaticas.usuario.getId());
        psFrecuencia.executeUpdate();
    }

    @Override
    public void eliminar(FrecuenciaSesion objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(FrecuenciaSesion objetoParametro) throws Exception {
        String sqlFrecuenciaSesion = "INSERT INTO frecuencias_sesiones (id_frecuencia_sesion, frecuencia, id_usuario) VALUES (?,?,?)";

       
            int idFrecuencia = obtenerId(objetoParametro);

            if (idFrecuencia == 0) {
                //INSERTAR FRECUENCIA SI NO EXISTE
                PreparedStatement pstFs = conexion.conexion().prepareStatement(sqlFrecuenciaSesion);
                pstFs.setInt(1, 0);
                pstFs.setString(2, objetoParametro.getFrecuencia());
                pstFs.setInt(3, VariablesEstaticas.usuario.getId());
                if (idFrecuencia == 0) {
                    pstFs.executeUpdate();
                }
                pstFs.close();
            }

        
    }

    @Override
    public int obtenerId(FrecuenciaSesion objetoParametro) throws Exception {
        String sqlObtenerIdAFrecuencia = "SELECT id_frecuencia_sesion FROM frecuencias_sesiones WHERE frecuencia LIKE ? AND id_usuario = ?;";
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdFrecuencia = conexion.conexion().prepareStatement(sqlObtenerIdAFrecuencia);
            psBuscarIdFrecuencia.setString(1, objetoParametro.getFrecuencia());
            psBuscarIdFrecuencia.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rsBuscarIdFrecuencia= psBuscarIdFrecuencia.executeQuery();
            if(rsBuscarIdFrecuencia.next()){
                return rsBuscarIdFrecuencia.getInt("id_frecuencia_sesion");
            }else{
                return 0;
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public List<FrecuenciaSesion> obtenerLista(FrecuenciaSesion objetoParametro) throws SQLException {
        String sqlListaFrecuencia = "SELECT frecuencia  FROM frecuencias_sesiones WHERE id_usuario = ?";
        List<FrecuenciaSesion> ts = new ArrayList();

        try {
            PreparedStatement psFrecuencia = conexion.conexion().prepareStatement(sqlListaFrecuencia);
            
            psFrecuencia.setInt(1, VariablesEstaticas.usuario.getId());
            ResultSet rsFrecuencia = psFrecuencia.executeQuery();
            while (rsFrecuencia.next()) {
                ts.add(new FrecuenciaSesion(rsFrecuencia.getString("frecuencia")));
            }
            psFrecuencia.close();
            rsFrecuencia.close();
            return ts;

        } catch (SQLException e) {
        }
        return null;
    }

    
    
}
