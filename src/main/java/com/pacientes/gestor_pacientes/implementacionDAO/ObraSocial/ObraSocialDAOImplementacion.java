/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial;
import com.pacientes.gestor_pacientes.DAO.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.EmailDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;

import com.pacientes.gestor_pacientes.modelo.Email;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import com.pacientes.gestor_pacientes.modelo.Web;

import com.pacientes.gestor_pacientes.utilidades.Exepciones;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;


/**
 *
 * @author previotto
 */
public class ObraSocialDAOImplementacion extends PadreDAOImplementacion implements CRUD<ObraSocial>{
    
    
    private ObraSocial obraSocialGlobal;
    

    @Override
    public List<ObraSocial> obtenerLista(ObraSocial objetoParametro) throws SQLException{
        
        String sqlObtenerObrasSociales = "SELECT os.id_obra_social,  os.nombre, os.web, e.email, tos.numero_telefono \n" +
                                            "FROM obras_sociales os \n" +
                                            "JOIN emails e ON os.id_email = e.id_email\n" +
                                            "JOIN telefonos_obras_sociales tos ON os.id_obra_social = tos.id_obra_social\n" +
                                            "JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social \n" +
                                            "WHERE id_usuario = ?";

        List<ObraSocial> listaObraSocial = new ArrayList();
        try {
            
            PreparedStatement psObrasSociales = conexion.conexion().prepareStatement(sqlObtenerObrasSociales);
            psObrasSociales.setInt(1, VariablesEstaticas.usuario.getId());
            ResultSet rsObrasSociales = psObrasSociales.executeQuery();
            List<String> listaPlanes;
            daoImplementacion = new PlanObraSocialDAOImplementacion();
            
            
            
            
            while (rsObrasSociales.next()) {
                listaPlanes = new ArrayList();
                try {
                    
                    for (Object object : daoImplementacion.obtenerLista(new ObraSocial(rsObrasSociales.getInt("id_obra_social"),rsObrasSociales.getString("nombre")))) {
                        ObraSocial obraSocial = (ObraSocial) object;
                        listaPlanes.add(obraSocial.getPlan());
                        
                    }
                } catch (Exception e) {
                }
                
                
                listaObraSocial.add(
                        new ObraSocial(
                                rsObrasSociales.getInt("id_obra_social"),
                                rsObrasSociales.getString("nombre"),
                                new Telefono(rsObrasSociales.getString("numero_telefono")),
                                new Web(rsObrasSociales.getString("web")),
                                true,
                                new Email(rsObrasSociales.getString("email")),
                                listaPlanes));
            }
            
            
            return listaObraSocial;

        } catch (SQLException e) {
        }
        listaObraSocial = null;
        return listaObraSocial;
    }

    @Override
    public ObraSocial obtener(ObraSocial obraSocial) throws SQLException{
        
        ObraSocial obraSocialDevuelta = new ObraSocial();
        
        
        try {
            
            String sqlIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre = ?";
            
            String sqlObraSocial = "SELECT os.nombre, os.web, e.email, tos.numero_telefono  \n" +
                                    "FROM obras_sociales os \n" +
                                    "JOIN emails e ON os.id_email = e.id_email \n" +
                                    "JOIN telefonos_obras_sociales tos ON os.id_obra_social = tos.id_obra_social\n" +
                                    "JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social \n" +
                                    "JOIN usuarios us ON us.id_usuario = uos.id_usuario \n" +
                                    "WHERE os.nombre = ? AND us.es_ultima_sesion_iniciada = true";
            
            String sqlPlanes = "SELECT pos.nombre FROM planes_obras_sociales pos \n" +
                               "JOIN obras_sociales_planes_obras_sociales ospos \n" +
                               "ON pos.id_plan_obra_social = ospos.id_plan_obra_social \n" +
                               "JOIN obras_sociales os ON os.id_obra_social = ospos.id_obra_social \n" +
                               "WHERE os.id_obra_social = ?";
            
           
           
            PreparedStatement psObraSocial = conexion.conexion().prepareStatement(sqlObraSocial);
            psObraSocial.setString(1, obraSocial.getNombre());
            ResultSet rsObraSocial = psObraSocial.executeQuery();
            
            if(rsObraSocial.next()){
                PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
                psIdObraSocial.setString(1, obraSocial.getNombre());
                ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();
                PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlPlanes);
                List<String> listaPlanes = new ArrayList();
                if (rsIdObraSocial.next()) {

                    psPlanes.setInt(1, rsIdObraSocial.getInt("id_obra_social"));
                    ResultSet rsPlanes = psPlanes.executeQuery();
                    while (rsPlanes.next()) {
                        listaPlanes.add(rsPlanes.getString("nombre"));
                    }
                }
                
                obraSocialDevuelta.setNombre(rsObraSocial.getString("nombre"));
                obraSocialDevuelta.setTelefono(new Telefono(rsObraSocial.getString("numero_telefono")));
                obraSocialDevuelta.setWeb(new Web(rsObraSocial.getString("web")));
                obraSocialDevuelta.setEmail(new Email(rsObraSocial.getString("email")));
                obraSocialDevuelta.setPlanes(listaPlanes);
            }
            
            return obraSocialDevuelta;
            
        } catch (SQLException e) {
            return obraSocialDevuelta;
        }
    }

    @Override
    public void actualizar(ObraSocial  obraSocial) throws Exception{
       
        if(obraSocial.getId() != 0){
            
            eliminar(obraSocial);
            insertar(obraSocial);
            
            
        }else{
            insertar(obraSocial);
        }
            
        
    }
    
    
    
    

    @Override
    public void eliminar(ObraSocial  obraSocial) throws SQLException{
        String sqlEliminarObraSocial = "DELETE FROM usuarios_obras_sociales \n" +
                                        "WHERE id_obra_social = ? \n" +
                                        "AND id_usuario = ?;";
        
        PreparedStatement psEliminarObraSocial = conexion.conexion().prepareStatement(sqlEliminarObraSocial);
        psEliminarObraSocial.setInt(1, obraSocial.getId());
         psEliminarObraSocial.setInt(2, VariablesEstaticas.usuario.getId());
        psEliminarObraSocial.executeUpdate();
        
    }

    @Override
    public void insertar(ObraSocial obraSocial) throws Exception {
        String sqlCrearObraSocial = "INSERT INTO obras_sociales (id_obra_social, nombre, web, es_obra_social, id_email) VALUES (?,?,?,?,?)";
        String sqlTelefonos = "INSERT INTO telefonos_obras_sociales (id_telefono, numero_telefono, id_obra_social) VALUES (?,?,?)";

        String sqlAsociarObraSocialUsuario = "INSERT INTO usuarios_obras_sociales (id_usuario, id_obra_social) VALUES (?,?);";

        try {
           
            if (obtenerIdConUsuario(obraSocial) == 0) {
                daoImplementacion = new EmailDAOImplementacion();
                daoImplementacion.insertar(obraSocial.getEmail());

                int idObraSocial = obtenerId(obraSocial);

                if (idObraSocial == 0) {

                    daoImplementacion = new EmailDAOImplementacion();
                    int idEmail = daoImplementacion.obtenerId(obraSocial.getEmail());
                    //crear obra social
                    PreparedStatement pSObraSocial = conexion.conexion().prepareStatement(sqlCrearObraSocial);
                    pSObraSocial.setInt(1, 0);
                    pSObraSocial.setString(2, obraSocial.getNombre());
                    pSObraSocial.setString(3, obraSocial.getWeb().getWeb());
                    pSObraSocial.setBoolean(4, true);
                    pSObraSocial.setInt(5, idEmail);
                    pSObraSocial.executeUpdate();

                    pSObraSocial.close();

                }

                idObraSocial = obtenerIdSinTelefono(obraSocial);

                
                daoImplementacion = new TelefonoObraSocialDAOImplementacion();
                int idTelefono = daoImplementacion.obtenerId(new Telefono(obraSocial.getTelefono().getTelefono(), idObraSocial));
               
                //crear telefono
                if (idTelefono == 0) {
                    PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlTelefonos);
                    pSTelefono.setInt(1, 0);
                    pSTelefono.setString(2, obraSocial.getTelefono().getTelefono());
                    pSTelefono.setInt(3, idObraSocial);
                    pSTelefono.executeUpdate();

                    pSTelefono.close();
                }

                idObraSocial = obtenerId(obraSocial);
                obraSocial.setId(idObraSocial);
                if (!existeObraSocialAsociada(obraSocial)) {
                    //asociar obra social con usuario
                    PreparedStatement pSAsociarObraSocialUsuario = conexion.conexion().prepareStatement(sqlAsociarObraSocialUsuario);
                    pSAsociarObraSocialUsuario.setInt(1, VariablesEstaticas.usuario.getId());
                    pSAsociarObraSocialUsuario.setInt(2, idObraSocial);
                    pSAsociarObraSocialUsuario.executeUpdate();
                }
            }else{
                throw new Exepciones(333);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
   /*@Override
    public List<String> obtenerListaNombresObrasSociales() {
        String sqlListaNombresOS = "SELECT nombre \n" +
                                    "FROM obras_sociales os\n" +
                                    "JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social \n" +
                                    "JOIN usuarios us ON us.id_usuario = uos.id_usuario \n" +
                                    "WHERE us.es_ultima_sesion_iniciada = true";
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
    }*/
    
    
    
    public boolean existeObraSocialAsociada(ObraSocial obraSocial) throws Exception{
        String sql = "SELECT id_usuario, id_obra_social\n" +
                        "FROM usuarios_obras_sociales\n" +
                        "WHERE id_usuario = ? AND id_obra_social = ?";
        
         PreparedStatement ps = conexion.conexion().prepareStatement(sql);
         ps.setInt(1, VariablesEstaticas.usuario.getId());
         ps.setInt(2, obraSocial.getId());
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
             return true;
             
         }
         return false;
    }
    
    public int obtenerIdSinTelefono(ObraSocial objetoParametro)throws Exception{
        String sqlObtenerIdObraSocial = "SELECT os.id_obra_social\n" +
                                        "FROM obras_sociales os\n" +
                                        "WHERE os.nombre = ? \n" +
                                        "AND os.web = ? \n" +
                                        "AND os.id_email = (SELECT e.id_email FROM emails e WHERE e.email = ? );";
                                        

        PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
        psIdObraSocial.setString(1, objetoParametro.getNombre());
        psIdObraSocial.setString(2, objetoParametro.getWeb().getWeb());
        psIdObraSocial.setString(3, objetoParametro.getEmail().getEmail());
        ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();

        if (rsIdObraSocial.next()) {
            return rsIdObraSocial.getInt("id_obra_social");
        } else {
            return 0;
        }
    }
    
    public int obtenerIdConUsuario(ObraSocial objetoParametro) throws Exception{
        String sqlObtenerIdObraSocial = "SELECT os.id_obra_social  \n" +
                                        "FROM obras_sociales os \n" +
                                        "JOIN usuarios_obras_sociales uos  ON os.id_obra_social = uos.id_obra_social \n" +
                                        "WHERE os.nombre = ? AND uos.id_usuario = ?;";
                                        

        PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
        
        psIdObraSocial.setString(1, objetoParametro.getNombre());
        
        psIdObraSocial.setInt(2, VariablesEstaticas.usuario.getId());
        
        
        ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();

        if (rsIdObraSocial.next()) {
            return rsIdObraSocial.getInt("id_obra_social");
        } else {
            return 0;
        }
    }
    

    @Override
    public int obtenerId(ObraSocial objetoParametro) throws Exception {
         String sqlObtenerIdObraSocial = "SELECT os.id_obra_social\n" +
                                        "FROM obras_sociales os\n" +
                                        "JOIN telefonos_obras_sociales tos ON os.id_obra_social = tos.id_obra_social\n" +
                                        "WHERE os.nombre = ?\n" +
                                        "AND os.web = ?\n" +
                                        "AND os.id_email = (SELECT e.id_email FROM emails e WHERE e.email = ?)\n" +
                                        "AND tos.numero_telefono = ?;";
                                        

        PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
        
        psIdObraSocial.setString(1, objetoParametro.getNombre());
        
        psIdObraSocial.setString(2, objetoParametro.getWeb().getWeb());
        
        psIdObraSocial.setString(3, objetoParametro.getEmail().getEmail());
        
        psIdObraSocial.setString(4, objetoParametro.getTelefono().getTelefono());
        ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();

        if (rsIdObraSocial.next()) {
            return rsIdObraSocial.getInt("id_obra_social");
        } else {
            return 0;
        }
    }

    

    
    
}
