/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.modelo.Email;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class EmailDAOImplementacion extends PadreDAOImplementacion implements CRUD<Email>{

    @Override
    public List<Email> obtenerLista(Email objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Email obtener(Email objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Email objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Email objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(Email objetoParametro) throws Exception {
        String sqlEmails = "INSERT INTO emails (id_email, email) VALUES (?,?)";
        
        int idEmail = obtenerId(objetoParametro);
        //crear email
        if (idEmail == 0) {
            PreparedStatement pSEmail = conexion.conexion().prepareStatement(sqlEmails);
            pSEmail.setInt(1, 0);
            pSEmail.setString(2, objetoParametro.getEmail());
            pSEmail.executeUpdate();
        }
    }

    @Override
    public int obtenerId(Email objetoParametro) throws Exception {
        String sqlObtenerIdEmail = "SELECT e.id_email \n" +
                                        "FROM emails e \n" +
                                        "WHERE e.email LIKE ?";
        
        
        
        PreparedStatement psIdEmail = conexion.conexion().prepareStatement(sqlObtenerIdEmail);
        psIdEmail.setString(1, objetoParametro.getEmail());
        ResultSet rsIdEmail = psIdEmail.executeQuery();
        
        if(rsIdEmail.next()){
            return rsIdEmail.getInt("id_email");
        }else{
            return 0;
        }
    }
    
}
