/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.modelo.Web;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author bruno
 */
public class WebDaoImplementacion extends PadreDAOImplementacion implements CRUD<Web>{

    @Override
    public List<Web> obtenerLista(Web objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Web obtener(Web objetoParametro) throws Exception {
        String sqlObtenerAccion = "SELECT w.web  FROM web w WHERE w.id_web = ?;";
        
        PreparedStatement psAgenda = conexion.conexion().prepareStatement(sqlObtenerAccion);
        psAgenda.setLong(1, objetoParametro.getId());
        ResultSet rs = psAgenda.executeQuery();
        
        
        if(rs.next()){
            return new Web(rs.getString("web"));
            
        }
        
        return null;
    }

    @Override
    public void actualizar(Web objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Web objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(Web objetoParametro) throws Exception {
       String sqlEmails = "INSERT INTO web (id_web, web) VALUES (?,?)";
        
        int idEmail = obtenerId(objetoParametro);
        //crear email
        if (idEmail == 0) {
            PreparedStatement pSEmail = conexion.conexion().prepareStatement(sqlEmails);
            pSEmail.setInt(1, 0);
            pSEmail.setString(2, objetoParametro.getWeb());
            pSEmail.executeUpdate();
        }
    }

    @Override
    public int obtenerId(Web objetoParametro) throws Exception {
        String sqlObtenerIdEmail = "SELECT w.id_web  FROM web w WHERE w.web  LIKE ?";

        PreparedStatement psIdEmail = conexion.conexion().prepareStatement(sqlObtenerIdEmail);
        psIdEmail.setString(1, objetoParametro.getWeb());
        ResultSet rsIdEmail = psIdEmail.executeQuery();

        if (rsIdEmail.next()) {
            return rsIdEmail.getInt("id_web");
        } else {
            return 0;
        }
    }


   
    
        
}