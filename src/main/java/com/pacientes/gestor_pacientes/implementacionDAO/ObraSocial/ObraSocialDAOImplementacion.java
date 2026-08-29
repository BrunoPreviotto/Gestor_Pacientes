/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial;
import com.pacientes.gestor_pacientes.DAO.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.EmailDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.TelefonoDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.WebDaoImplementacion;

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
    public EmailDAOImplementacion emailOImplementacion  =  new EmailDAOImplementacion();
    public WebDaoImplementacion webDaoImplementacion = new WebDaoImplementacion();
    public TelefonoDAOImplementacion telefonoObraSocialDAOImplementacion = new TelefonoDAOImplementacion();
    

    @Override
    public List<ObraSocial> obtenerLista(ObraSocial objetoParametro) throws SQLException{
        
        String sqlObtenerObrasSociales = "SELECT os.id_obra_social,  os.nombre, osw.id_web, ose.id_email, ost.id_telefono, uos.es_obra_social \n" +
"                                            FROM obras_sociales os\n" +
"                                            join obras_sociales_web osw on os.id_obra_social  = osw.id_obra_social \n" +
"                                            JOIN obra_social_email ose ON os.id_obra_social = ose.id_obra_social \n" +
"                                            JOIN obras_sociales_telefonos ost ON os.id_obra_social = ost.id_obra_social\n" +
"                                            JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social\n" +
"                                            WHERE uos.id_usuario = ? AND uos.es_obra_social =1;";
        
        String email = "";
        String web = "";
        String telefono = "";

        List<ObraSocial> listaObraSocial = new ArrayList();
        try {
            
         
                    
            PreparedStatement psObrasSociales = conexion.conexion().prepareStatement(sqlObtenerObrasSociales);
            psObrasSociales.setInt(1, VariablesEstaticas.usuario.getId());
            ResultSet rs = psObrasSociales.executeQuery();
            List<String> listaPlanes;
            daoImplementacion = new PlanObraSocialDAOImplementacion();
            
            
            
            
            while (rs.next()) {
                listaPlanes = new ArrayList();
                try {
                    
                    for (Object object : daoImplementacion.obtenerLista(new ObraSocial(rs.getInt("id_obra_social"),rs.getString("nombre")))) {
                        ObraSocial obraSocial = (ObraSocial) object;
                        listaPlanes.add(obraSocial.getPlan());
                        
                    }
                    
                     email = emailOImplementacion.obtener(new Email(rs.getInt("id_email"))).getEmail();
                     
                     web = webDaoImplementacion.obtener(new Web((Long)rs.getLong("id_web"))).getWeb();
                    
                     telefono = telefonoObraSocialDAOImplementacion.obtener(new Telefono((Long)rs.getLong("id_telefono"))).getTelefono();
                     
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
               
                
                listaObraSocial.add(
                        new ObraSocial(
                                rs.getInt("id_obra_social"),
                                rs.getString("nombre"),
                                new Telefono(telefono),
                                new Web(web),
                                true,
                                new Email(email),
                                listaPlanes));
            }
            
            
            return listaObraSocial;

        } catch (SQLException e) {
        }
        listaObraSocial = null;
        return listaObraSocial;
    }

    @Override
    public ObraSocial obtener(ObraSocial obraSocial) throws Exception{
        
        ObraSocial obraSocialDevuelta = new ObraSocial();
        
         String email = "";
        String web = "";
        String telefono = "";
        try {
            
            String sqlIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre = ?";
            
            String sqlObraSocial = "SELECT os.nombre, osw.id_web , ose.id_email, ost.id_telefono , uos.es_obra_social\n" +
"                                    FROM obras_sociales os \n" +
"                                    join obras_sociales_web osw on os.id_obra_social = osw.id_obra_social \n" +
"                                    JOIN obra_social_email ose  ON os.id_obra_social  = ose.id_obra_social \n" +
"                                    JOIN obras_sociales_telefonos ost ON os.id_obra_social = ost.id_obra_social\n" +
"                                    JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social \n" +
"                                    JOIN usuarios us ON us.id_usuario = uos.id_usuario \n" +
"                                    WHERE os.nombre = ? AND us.es_ultima_sesion_iniciada = true";
            
            String sqlPlanes = "SELECT pos.nombre FROM planes_obras_sociales pos \n" +
                               "JOIN obras_sociales_planes_obras_sociales ospos \n" +
                               "ON pos.id_plan_obra_social = ospos.id_plan_obra_social \n" +
                               "JOIN obras_sociales os ON os.id_obra_social = ospos.id_obra_social \n" +
                               "WHERE os.id_obra_social = ?";
            
            
           
           //OBRA SOCIAL
            PreparedStatement psObraSocial = conexion.conexion().prepareStatement(sqlObraSocial);
            psObraSocial.setString(1, obraSocial.getNombre());
            ResultSet rsObraSocial = psObraSocial.executeQuery();
            
          
            
            if(rsObraSocial.next()){
                
                 email = emailOImplementacion.obtener(new Email(rsObraSocial.getInt("id_email"))).getEmail();
                 web = webDaoImplementacion.obtener(new Web((Long)rsObraSocial.getLong("id_web"))).getWeb();
                 telefono = telefonoObraSocialDAOImplementacion.obtener(new Telefono((Long)rsObraSocial.getLong("id_telefono"))).getTelefono();
               
                //ID OBRA SOCIAL
                PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
                psIdObraSocial.setString(1, obraSocial.getNombre());
                ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();
                
                //PLANES
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
                obraSocialDevuelta.setTelefono(new Telefono(telefono));
                obraSocialDevuelta.setWeb(new Web(web));
                obraSocialDevuelta.setEmail(new Email(email));
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
        String sqlCrearObraSocial = "INSERT "
                + "INTO obras_sociales "
                + "(id_obra_social, nombre) "
                + "VALUES (?,?)";
        
        String sqlAsociarObraSocialUsuario = "INSERT INTO usuarios_obras_sociales (id_usuario, id_obra_social, es_obra_social) VALUES (?,?,?);";

        try {
           
            if (obtenerIdConUsuario(obraSocial) == 0) {
              

                int idObraSocial = obtenerId(obraSocial);

                if (idObraSocial == 0) {

                   
                    PreparedStatement pSObraSocial = conexion.conexion().prepareStatement(sqlCrearObraSocial);
                    pSObraSocial.setInt(1, 0);
                    pSObraSocial.setString(2, obraSocial.getNombre());
                    
                    pSObraSocial.executeUpdate();

                    pSObraSocial.close(); 

                }

                idObraSocial = obtenerId(obraSocial);
                
                
                 //////////////////////////TELEFONO//////////////////////////
                if(obraSocial.getTelefono().getTelefono().isBlank() || obraSocial.getTelefono().getTelefono().isEmpty()){
                           obraSocial.getTelefono().setTelefono("0");
                           System.out.println("ENTRA A CREAR TEL: " + obraSocial.getTelefono().getTelefono() );
                           
                 }
                  daoImplementacion = new TelefonoDAOImplementacion();
                int idTelefono = daoImplementacion.obtenerId(new Telefono(obraSocial.getTelefono().getTelefono(), idObraSocial));
               
                if (idTelefono == 0) {
                     
                    daoImplementacion.insertar(new Telefono(obraSocial.getTelefono().getTelefono(),  idObraSocial));
                }
                System.out.println("ID TEL: " + idTelefono);
                idTelefono = daoImplementacion.obtenerId(new Telefono(obraSocial.getTelefono().getTelefono()));
               
                String sqlAgregaTel =  "INSERT INTO obras_sociales_telefonos (id_telefono , id_obra_social) VALUES(?, ?);";
                PreparedStatement pS = conexion.conexion().prepareStatement(sqlAgregaTel);
                pS.setInt(1, idTelefono);
                pS.setInt(2, idObraSocial);
                pS.executeUpdate();
                
                ///////////////////////
                
                //////////////////////////EMAIL///////////////////////////////////
                 if(obraSocial.getEmail().getEmail().isBlank() || obraSocial.getEmail().getEmail().isEmpty()){
                           obraSocial.getEmail().setEmail("-");
                           
                 }
                daoImplementacion = new EmailDAOImplementacion();
                int idEmail = daoImplementacion.obtenerId(new Email(obraSocial.getEmail().getEmail()));
               
                //email
                if (idEmail == 0) {
                    daoImplementacion.insertar(new Email(obraSocial.getEmail().getEmail()));
                    
                }
                
                 idEmail = daoImplementacion.obtenerId(new Email(obraSocial.getEmail().getEmail()));
                String sqlAgregarEmail =  "INSERT INTO obra_social_email (id_email, id_obra_social) VALUES(?, ?);";
                pS = conexion.conexion().prepareStatement(sqlAgregarEmail);
                pS.setInt(1,idEmail);
                pS.setInt(2,idObraSocial);
                pS.executeUpdate();
                
                
                /////////////
                
                ////////////////////////////////WEB/////////////////////////////////////////
                 if(obraSocial.getWeb().getWeb().isBlank() || obraSocial.getWeb().getWeb().isEmpty()){
                           obraSocial.getWeb().setWeb("-");
                           
                 }
                
                daoImplementacion = new WebDaoImplementacion();
                int idWeb = daoImplementacion.obtenerId(new Web(obraSocial.getWeb().getWeb()));
               
                
                if (idWeb == 0) {
                    daoImplementacion.insertar(new Web(obraSocial.getWeb().getWeb()));
                }
                idWeb = daoImplementacion.obtenerId(new Web(obraSocial.getWeb().getWeb()));
                String sqlAgregarWeb = "INSERT INTO obras_sociales_web (id_obra_social, id_web) VALUES (?, ?);";
                
                pS = conexion.conexion().prepareStatement(sqlAgregarWeb);
                pS.setInt(1,idObraSocial);
                pS.setInt(2,idWeb);
                pS.executeUpdate();
                pS.close();
              //////////////////////
                obraSocial.setId(idObraSocial);
                if (!existeObraSocialAsociada(obraSocial)) {
                    //asociar obra social con usuario
                    PreparedStatement pSAsociarObraSocialUsuario = conexion.conexion().prepareStatement(sqlAsociarObraSocialUsuario);
                    pSAsociarObraSocialUsuario.setInt(1, VariablesEstaticas.usuario.getId());
                    pSAsociarObraSocialUsuario.setInt(2, idObraSocial);
                    pSAsociarObraSocialUsuario.setInt(3, 1);
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
                        "WHERE id_usuario = ? AND id_obra_social = ? AND es_obra_social=1";
        
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
         
        
        String sqlObtenerIdObraSocial = "SELECT os.id_obra_social FROM obras_sociales os WHERE os.nombre = ?;";
                                        
        PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
        
        psIdObraSocial.setString(1, objetoParametro.getNombre());
      
        ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();

        if (rsIdObraSocial.next()) {
            return rsIdObraSocial.getInt("id_obra_social");
        } else {
            return 0;
        }
    }

    

    
    
}
