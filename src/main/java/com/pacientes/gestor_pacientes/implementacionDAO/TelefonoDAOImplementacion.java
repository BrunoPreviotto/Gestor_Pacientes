/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_pacientes.DAO.CRUD;

import com.pacientes.gestor_pacientes.modelo.Telefono;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class TelefonoDAOImplementacion extends PadreDAOImplementacion implements CRUD<Telefono>{

    @Override
    public List<Telefono> obtenerLista(Telefono objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Telefono obtener(Telefono objetoParametro) throws Exception {
        String sqlObtenerAccion = "SELECT t.telefono FROM telefonos t  WHERE t.id_telefono  = ?;";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlObtenerAccion);
        psAgenda.setLong(1, objetoParametro.getId());
        ResultSet rs = psAgenda.executeQuery();
        
        
        if(rs.next()){
            return new Telefono(rs.getString("telefono"));
        }
        
        return null;
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
           String sqlTelefono = "INSERT INTO telefonos  (id_telefono , telefono ) VALUES(?, ?);";
        
            int idTelefono = obtenerId(objetoParametro);
            System.out.println("ID TELEFONO: " + idTelefono);
            //crear email
            if (idTelefono == 0) {
                PreparedStatement pSTel = conexion.conexion().prepareStatement(sqlTelefono);
                pSTel.setInt(1, 0);
                pSTel.setString(2, objetoParametro.getTelefono());
                pSTel.executeUpdate();
                pSTel.close();
            }
    }

    @Override
    public int obtenerId(Telefono objetoParametro) throws Exception {
          String sqlSTelefono = "SELECT t.id_telefono FROM telefonos t  WHERE t.telefono   = ?";
        try {
            
            
            
            PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlSTelefono);
            pSTelefono.setString(1, objetoParametro.getTelefono());
          
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
