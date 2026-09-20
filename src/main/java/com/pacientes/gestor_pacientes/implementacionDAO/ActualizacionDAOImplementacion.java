/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;




import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.modelo.Actualizacion;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ActualizacionDAOImplementacion extends PadreDAOImplementacion implements CRUD<Actualizacion>{
    
    private ConexionMariadb conexion = ConexionMariadb.getInstacia();

    @Override
    public List<Actualizacion> obtenerLista(Actualizacion objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Actualizacion obtener(Actualizacion objetoParametro) throws Exception {
        Actualizacion actualizacion = new Actualizacion();
        
        
        
       
            String sql = "SELECT reciente, exito FROM actualizacion WHERE id_usuario=?;";
            
           
            
            PreparedStatement pst = conexion.conexion().prepareStatement(sql);
            pst.setInt(1, VariablesEstaticas.usuario.getId());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
               actualizacion.setReciente(rs.getBoolean("reciente"));
               actualizacion.setExito(rs.getBoolean("exito"));
            }else{
                throw new SQLException();
            }
            rs.close();
            pst.close();
           
            
        return actualizacion;
    }

    @Override
    public void actualizar(Actualizacion actualizacion) throws Exception {
        String sqlActualizar = "UPDATE actualizacion SET reciente = ? WHERE id_usuario = ?;";
        
        
        
        PreparedStatement pst = conexion.conexion().prepareStatement(sqlActualizar);
        pst.setBoolean(1, actualizacion.isReciente());
         pst.setInt(2, VariablesEstaticas.usuario.getId());
        
        pst.executeUpdate();
    }
    
    
    
    public void actualizarExito(Actualizacion actualizacion) throws Exception {
        String sqlActualizar = "UPDATE actualizacion SET reciente =? WHERE id_usuario = ?;";

        UsuarioDAOImplementacion usuarioDAOImplementacion = new UsuarioDAOImplementacion();

        int us = usuarioDAOImplementacion.obtener(new Usuario()).getId();

        if (us == 0) {
            throw new SQLException();
        } else {
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlActualizar);
            
            pst.setBoolean(1, actualizacion.isReciente());
            pst.setInt(2, us);

            pst.executeUpdate();

            pst.close();

        }

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
