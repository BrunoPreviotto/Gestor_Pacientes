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
import com.pacientes.gestor_pacientes.utilidades.Exepciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author previotto
 */
public class UsuarioDAOImplementacion extends PadreDAOImplementacion implements IUsuarioDAO{
    
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
    public void insertar(Usuario usuario) throws Exception{
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";
        String sqlEmail = "INSERT INTO emails(id_email, email) VALUES(?,?)";
        String sqlUsuario = "INSERT INTO usuarios(id_usuario, usuario, contraseña, es_usuario, es_ultima_sesion_iniciada, id_nombre, id_email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        String sqlUpdate = "UPDATE usuarios SET es_ultima_sesion_iniciada = false WHERE es_ultima_sesion_iniciada = true";
        String sqlCrearAgenda = "INSERT INTO agendas (id_agenda, id_usuario) VALUES (0 , ?)";
        try {
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlUpdate);
            pst.executeUpdate();
            
            int idNombre = obtenerIdNombre(usuario.getNombre(), usuario.getApellido());
            
            daoImplementacion = new EmailDAOImplementacion();
            int idEmail =  daoImplementacion.obtenerId(usuario.getEmail());
            
           
            
            if(idNombre == 0){
                pst = conexion.conexion().prepareStatement(sqlNombre);
                pst.setInt(1, 0);
                pst.setString(2,usuario.getNombre());
                pst.setString(3, usuario.getApellido());
                pst.executeUpdate();
            }
            
            if(idEmail == 0 && Objects.nonNull(usuario.getEmail())){
                pst = conexion.conexion().prepareStatement(sqlEmail);
                pst.setInt(1, 0);
                pst.setString(2, usuario.getEmail().getEmail());
                pst.executeUpdate();
            }
            
           idNombre = obtenerIdNombre(usuario.getNombre(), usuario.getApellido());
           daoImplementacion = new EmailDAOImplementacion();
           idEmail =  daoImplementacion.obtenerId(usuario.getEmail());  
           
            pst = conexion.conexion().prepareStatement(sqlUsuario);
            pst.setInt(1, 0);
            pst.setString(2, usuario.getUsuario());
            pst.setString(3, Encriptar.convertirSHA256(usuario.getContraseña()));
            pst.setBoolean(4, usuario.isEs_usuario());
            pst.setBoolean(5, usuario.getId_es_ultima_sesion_iniciada());
            pst.setInt(6, idNombre);
            pst.setInt(7, idEmail);
            pst.executeUpdate();
            
            
            int idUsuario = obtenerId(new Usuario());
            pst = conexion.conexion().prepareStatement(sqlCrearAgenda);
            pst.setInt(1, idUsuario);
            pst.executeUpdate();
            
            
            
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public int existeUsuarioReciente() {
         try{
            String sql = "SELECT id_usuario , es_ultima_sesion_iniciada FROM usuarios WHERE es_ultima_sesion_iniciada = true;";
            PreparedStatement pst = conexion.conexion().prepareStatement(sql);
           
            ResultSet rs = pst.executeQuery();
            List<String> esUsuario = new ArrayList();
            if(rs.next()){
                return 1;
            }else{
                return 0;
            }
            
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
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
    
    public void cerrarSesion()throws SQLException{
        String sqlCerrarSesion = "UPDATE usuarios SET es_ultima_sesion_iniciada = 0 WHERE es_ultima_sesion_iniciada = 1";
        PreparedStatement pSNombre = conexion.conexion().prepareStatement(sqlCerrarSesion);
        pSNombre.executeUpdate();
    }
    
     public void abrirSesion(Usuario usuario)throws SQLException{
        String sqlAbrirSesion = "UPDATE usuarios SET es_ultima_sesion_iniciada = 1 WHERE usuario=?";
        PreparedStatement pSAbrir = conexion.conexion().prepareStatement(sqlAbrirSesion);
        pSAbrir.setString(1, usuario.getUsuario());
        pSAbrir.executeUpdate();
    }

    @Override
    public int obtenerId(Usuario objetoParametro) throws Exception {
        String sqlIdUsuario = "SELECT u.id_usuario FROM usuarios u WHERE u.es_ultima_sesion_iniciada = 1;";

        try {
            PreparedStatement pstIdUsuario = conexion.conexion().prepareStatement(sqlIdUsuario);
            ResultSet rsIdUsuario = pstIdUsuario.executeQuery();
            if (rsIdUsuario.next()) {
                return rsIdUsuario.getInt("id_usuario");
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public List<Usuario> obtenerLista(Usuario objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    
    
}
