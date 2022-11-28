/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;
import com.pacientes.gestor_pacientes.DAO.IObraSocialDAO;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author previotto
 */
public class ObraSocialDAOImplementacion implements IObraSocialDAO{
    
    private ObraSocial obraSocialGlobal;
    private ConexionMariadb conexion = ConexionMariadb.getInstacia();

    @Override
    public List<ObraSocial> obtenerLista(ObraSocial objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ObraSocial obtener(ObraSocial  obraSocial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(ObraSocial  obraSocial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(ObraSocial  obraSocial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(ObraSocial obraSocial) {
        String sqlCrearObraSocial = "INSERT INTO obras_sociales (id_obra_social, nombre, web, es_obra_social, id_email) VALUES (?,?,?,?,?)";
        String sqlTelefonos = "INSERT INTO telefonos_obras_sociales (id_telefono, numero_telefono, id_obra_social) VALUES (?,?,?)";
        String sqlEmails = "INSERT INTO emails (id_email, email) VALUES (?,?)";
        String sqlIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre=? AND es_obra_social=true";
        String sqlIdEmail = "SELECT id_email FROM emails WHERE email=?";
        
        try {
            ResultSet rsIdEmail = null;
            if(!obraSocial.getEmail().getEmail().isEmpty()){
                PreparedStatement pSEmail = conexion.conexion().prepareStatement(sqlEmails);
                pSEmail.setInt(1, 0);
                pSEmail.setString(2, obraSocial.getEmail().getEmail());
                pSEmail.executeUpdate();

                PreparedStatement pstIdEmail = conexion.conexion().prepareStatement(sqlIdEmail);
                pstIdEmail.setString(1,obraSocial.getEmail().getEmail());
                rsIdEmail = pstIdEmail.executeQuery();
            }
            
            PreparedStatement pSObraSocial = conexion.conexion().prepareStatement(sqlCrearObraSocial);
            pSObraSocial.setInt(1, 0);
            pSObraSocial.setString(2, obraSocial.getNombre());
            pSObraSocial.setString(3, obraSocial.getWeb().getWeb());
            pSObraSocial.setBoolean(4, true);
            
            if(rsIdEmail.next()){
                pSObraSocial.setInt(5, rsIdEmail.getInt("id_email"));
            }
            
            pSObraSocial.executeUpdate();
            
            ResultSet rsObraSocial = null;
            if(!obraSocial.getTelefono().getTelefono().isEmpty()){
                PreparedStatement pstIdObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
                pstIdObraSocial.setString(1,obraSocial.getNombre());
                rsObraSocial = pstIdObraSocial.executeQuery();
            }
            
            PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlTelefonos);
            pSTelefono.setInt(1, 0);
            pSTelefono.setString(2, obraSocial.getTelefono().getTelefono());
            
            if(rsObraSocial.next()){
                pSTelefono.setInt(3, rsObraSocial.getInt("id_obra_social"));
            }
            
            
            pSTelefono.executeUpdate();
            
            pSObraSocial.close();
            pSTelefono.close();
            rsIdEmail.close();
            rsObraSocial.close();
            
        } catch (SQLException e) {
        }
    }

    @Override
    public List<String> obtenerListaNombresObrasSociales() {
        String sqlListaNombresOS = "SELECT nombre FROM obras_sociales";
        List<String> ts = new ArrayList();
        try {
            PreparedStatement psTipo = conexion.conexion().prepareStatement(sqlListaNombresOS);
            ResultSet rsTipo = psTipo.executeQuery();
            while (rsTipo.next()) {                
                ts.add(rsTipo.getString("nombre"));
            }
            psTipo.close();
            rsTipo.close();
            return ts;
            
        } catch (SQLException e) {
        }
        return null;
    }
    
}
