/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;




import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.modelo.Actualizacion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ActualizacionDAOImplementacion extends PadreDAOImplementacion implements CRUD<Actualizacion>{

    @Override
    public List<Actualizacion> obtenerLista(Actualizacion objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Actualizacion obtener(Actualizacion objetoParametro) throws Exception {
        Actualizacion actualizacion = new Actualizacion();
        
        
        
       
            String sql = "SELECT actualizacion FROM actualizacion WHERE reciente = true;";
            PreparedStatement pst = conexion.conexion().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
               actualizacion.setVersionActual(rs.getString("actualizacion"));
            }else{
                throw sqlException;
            }
            rs.close();
            pst.close();
           
            
        return actualizacion;
    }

    @Override
    public void actualizar(Actualizacion actualizacion) throws Exception {
        String sqlActualizar = "UPDATE actualizacion SET actualizacion = ? WHERE reciente = true;";
        
        
        
        PreparedStatement pst = conexion.conexion().prepareStatement(sqlActualizar);
        pst.setString(1, actualizacion.getVersionNueva());
        
        pst.executeUpdate();
    }

    @Override
    public void eliminar(Actualizacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(Actualizacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int obtenerId(Actualizacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
}
