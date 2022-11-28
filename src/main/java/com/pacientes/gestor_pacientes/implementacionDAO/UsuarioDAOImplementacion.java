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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author previotto
 */
public class UsuarioDAOImplementacion implements IUsuarioDAO{
    
    private Usuario usuarioGlobal;
    private ConexionMariadb conexion = ConexionMariadb.getInstacia();

    /**
     * Este metodo busca al usuario registrado en la base de datos
     * @param usuario 
     * @param contraseña
     * @return retorna una lista con dos parametros 1 es el usuario si esta registrado y 2 es la contraseña si esta registrado
     */
    @Override
    public Usuario obtener(Usuario usuario) {
         Usuario usuarioObtenido = new Usuario();
         try{
            
            usuario.setContraseña(Encriptar.convertirSHA256(usuario.getContraseña()));
            String sql = "SELECT usuario , contraseña FROM usuarios WHERE usuario=? AND contraseña=?;";
            PreparedStatement pst = conexion.conexion().prepareStatement(sql);
            pst.setString(1,usuario.getUsuario());
            pst.setString(2, usuario.getContraseña());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                usuarioObtenido.setUsuario(rs.getString(1));
                usuarioObtenido.setContraseña(Encriptar.convertirSHA256(rs.getString(2)));
            }
            rs.close();
            pst.close();
           
            
            return usuarioObtenido;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
        
    @Override
    public List<Usuario> obtenerLista(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }   
       

    @Override
    public void actualizar(Usuario cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Usuario cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    /**
     * Permite insertar un nuevo usuario a la base de datos
     * @param usuario 
     */
    @Override
    public void insertar(Usuario usuario) {
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";
        String sqlEmail = "INSERT INTO emails(id_email, email) VALUES(?,?)";
        String sqlUsuario = "INSERT INTO usuarios(id_usuario, usuario, contraseña, es_usuario, es_ultima_sesion_iniciada, id_nombre, id_email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        String sqlUpdate = "UPDATE usuarios SET es_ultima_sesion_iniciada = false WHERE es_ultima_sesion_iniciada = true";
        try {
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlUpdate);
            pst.executeUpdate();
            
            
            String sqlSNombre = "SELECT id_nombre FROM nombres WHERE nombre=? AND apellido=?;";
            PreparedStatement pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            pSNombre.setString(1, usuario.getNombre());
            pSNombre.setString(2, usuario.getApellido());
            ResultSet rsSNombre = pSNombre.executeQuery();
            
            String sqlSEmail = "SELECT id_email FROM emails WHERE email=?;";
            PreparedStatement pSEmail = conexion.conexion().prepareStatement(sqlSEmail);
            pSEmail.setString(1, usuario.getMail());
            ResultSet rsSEmail = pSEmail.executeQuery();
            
            if(!rsSNombre.next()){
                pst = conexion.conexion().prepareStatement(sqlNombre);
                pst.setInt(1, 0);
                pst.setString(2,usuario.getNombre());
                pst.setString(3, usuario.getApellido());
                pst.executeUpdate();
            }
            
            if(!rsSEmail.next()){
                pst = conexion.conexion().prepareStatement(sqlEmail);
                pst.setInt(1, 0);
                pst.setString(2, usuario.getMail());
                pst.executeUpdate();
            }
            
            sqlSNombre = "SELECT id_nombre FROM nombres WHERE nombre=? AND apellido=?;";
            pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            pSNombre.setString(1, usuario.getNombre());
            pSNombre.setString(2, usuario.getApellido());
            rsSNombre = pSNombre.executeQuery();
            
            sqlSEmail = "SELECT id_email FROM emails WHERE email=?;";
            pSEmail = conexion.conexion().prepareStatement(sqlSEmail);
            pSEmail.setString(1, usuario.getMail());
            rsSEmail = pSEmail.executeQuery();
                
            pst = conexion.conexion().prepareStatement(sqlUsuario);
            pst.setInt(1, 0);
            pst.setString(2, usuario.getUsuario());
            pst.setString(3, Encriptar.convertirSHA256(usuario.getContraseña()));
            pst.setBoolean(4, usuario.isEs_usuario());
            pst.setBoolean(5, usuario.getId_es_ultima_sesion_iniciada());
            
            if(rsSNombre.next()){
                pst.setInt(6, rsSNombre.getInt(1));
            }
            
            if(rsSEmail.next()){
                pst.setInt(7, rsSEmail.getInt(1));
            }
            
            
            
            
            
            pst.executeUpdate();
            pSEmail.close();
            pSNombre.close();
            rsSEmail.close();
            rsSNombre.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> existeUsuarioReciente() {
         try{
            String sql = "SELECT id_usuario , es_ultima_sesion_iniciada FROM usuarios WHERE es_ultima_sesion_iniciada = true;";
            PreparedStatement pst = conexion.conexion().prepareStatement(sql);
           
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario obtenerNombreUsuario() {
        usuarioGlobal = new Usuario();
        try {
            String sqlSNombre = "SELECT n.nombre, n.apellido FROM nombres n JOIN usuarios u ON n.id_nombre = u.id_nombre WHERE es_ultima_sesion_iniciada=true;";
            PreparedStatement pSNombre = conexion.conexion().prepareStatement(sqlSNombre);
            ResultSet rsSNombre = pSNombre.executeQuery();
            if(rsSNombre.next()){
                usuarioGlobal.setNombre(rsSNombre.getString(1));
                usuarioGlobal.setApellido(rsSNombre.getString(2));
                return usuarioGlobal;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
}
