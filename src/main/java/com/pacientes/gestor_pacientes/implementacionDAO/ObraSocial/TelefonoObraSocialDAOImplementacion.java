/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class TelefonoObraSocialDAOImplementacion extends PadreDAOImplementacion implements CRUD<Telefono>{

    @Override
    public List<Telefono> obtenerLista(Telefono objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Telefono obtener(Telefono objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Telefono objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Telefono objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(Telefono objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int obtenerId(Telefono objetoParametro) throws Exception {
        String sqlSTelefono = "SELECT tos.id_telefono \n" +
                                "FROM telefonos_obras_sociales tos \n" +
                                "WHERE tos.numero_telefono = ? \n" +
                                "AND tos.id_obra_social = ?;";
        try {
            
            
            
            PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlSTelefono);
            pSTelefono.setString(1, objetoParametro.getTelefono());
            pSTelefono.setInt(2, objetoParametro.getIdObjeto());
            ResultSet rsSTelefono = pSTelefono.executeQuery();
           
            
            if(rsSTelefono.next()){
                return rsSTelefono.getInt("id_telefono");
            }
            
           pSTelefono.close();
           rsSTelefono.close();
            
            
        } catch (Exception e) {
        }
        return 0;
    }
    
}
