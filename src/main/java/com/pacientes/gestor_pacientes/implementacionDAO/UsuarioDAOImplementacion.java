/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;
import com.pacientes.gestor_pacientes.DAO.IUsuarioDAO;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.servicios.Encriptar;
import java.util.List;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import static com.pacientes.gestor_pacientes.servicios.ConexionMariadb.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author previotto
 */
public class UsuarioDAOImplementacion implements IUsuarioDAO{

    @Override
    public List<Usuario> obtenerClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Este metodo busca al usuario registrado en la base de datos
     * @param usuario 
     * @param contraseña
     * @return retorna una lista con dos parametros 1 es el usuario si esta registrado y 2 es la contraseña si esta registrado
     */
    @Override
    public List<String> obtener(String usuario, String contraseña) {
       
         try{
            
            String sql = "SELECT usuario , contraseña FROM usuarios WHERE usuario=? AND contraseña=?;";
            PreparedStatement pst = conexion().prepareStatement(sql);
            pst.setString(1,usuario);
            pst.setString(2, contraseña);
            ResultSet rs = pst.executeQuery();
            List<String> esUsuario = new ArrayList();
            if(rs.next()){
                esUsuario.add(rs.getString(1));
                esUsuario.add(rs.getString(2));
            }
            rs.close();
            pst.close();
           
            
            return esUsuario;
        }catch(SQLException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return null;
    }
        
        
       

    @Override
    public void actualizar(Usuario cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Usuario cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios(id_usuario, nombre, apellido, usuario, contraseña, email, es_usuario, id_es_ultima_sesion_iniciada) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlUpdate = "UPDATE usuarios SET id_es_ultima_sesion_iniciada = 1 WHERE id_es_ultima_sesion_iniciada = 2";
        try {
            PreparedStatement pst = conexion().prepareStatement(sqlUpdate);
            pst.executeUpdate();
            pst.close();
            
            pst = conexion().prepareStatement(sql);
            pst.setInt(1, usuario.getId());
            pst.setString(2,usuario.getNombre());
            pst.setString(3, usuario.getApellido());
            pst.setString(4, usuario.getUsuario());
            pst.setString(5, Encriptar.convertirSHA256(usuario.getContraseña()));
            pst.setString(6, usuario.getMail());
            pst.setBoolean(7, usuario.isEs_usuario());
            pst.setInt(8, usuario.getId_es_ultima_sesion_iniciada());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
