/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;
import com.pacientes.gestor_pacientes.DAO.IObraSocialDAO;
import com.pacientes.gestor_pacientes.modelo.Email;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
import com.pacientes.gestor_pacientes.modelo.Web;
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
        
        ObraSocial obraSocialDevuelta = new ObraSocial();
        try {
            
            String sqlIdObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre = ?";
            
            String sqlObraSocial = "SELECT os.nombre, os.web, e.email FROM \n" + 
                                   "obras_sociales os JOIN emails e ON os.id_email = e.id_email WHERE nombre = ?";
            
            String sqlPlanes = "SELECT pos.nombre FROM planes_obras_sociales pos \n" +
                               "JOIN obras_sociales_planes_obras_sociales ospos \n" +
                               "ON pos.id_plan_obra_social = ospos.id_plan_obra_social \n" +
                               "JOIN obras_sociales os ON os.id_obra_social = ospos.id_obra_social \n" +
                               "WHERE os.id_obra_social = ?";
            
            PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlIdObraSocial);
            psIdObraSocial.setString(1, obraSocial.getNombre());
            ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();
            PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlPlanes);
            List<String> listaPlanes = new ArrayList();
            if(rsIdObraSocial.next()){
                 
                 psPlanes.setInt(1, rsIdObraSocial.getInt("id_obra_social"));
                 ResultSet rsPlanes = psPlanes.executeQuery();
                 while(rsPlanes.next()){
                     listaPlanes.add(rsPlanes.getString("nombre"));
                 }
            }
           
            PreparedStatement psObraSocial = conexion.conexion().prepareStatement(sqlObraSocial);
            psObraSocial.setString(1, obraSocial.getNombre());
            ResultSet rsObraSocial = psObraSocial.executeQuery();
            
            if(rsObraSocial.next()){
                obraSocialDevuelta.setNombre(rsObraSocial.getString("nombre"));
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
    public void actualizar(ObraSocial  obraSocial, int numeroFuncion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(ObraSocial  obraSocial, int numeroFucion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(ObraSocial obraSocial, int numeroFucion) {
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
    public void agregarPlan(ObraSocial obraSocial) {
        
        String sqlSeleccionarObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre = ?";
        
        String sqlChequearExistenciaPlan = "SELECT nombre, id_plan_obra_social FROM planes_obras_sociales WHERE nombre = ?";
        
        String sqlAgregarPlanObraSocia = "INSERT INTO planes_obras_sociales \n" + 
                                         " (id_plan_obra_social, nombre, descripcion) \n" + 
                                         " VALUES (0, ? , 'descripcion')";
        
        
        String sqlVincularObraSocialPlan = "INSERT INTO obras_sociales_planes_obras_sociales \n" + 
                                           "(id_obra_social, id_plan_obra_social) VALUES (?, ?)";
        try {
             //obtener id obra social
             PreparedStatement psIdObrasSocial = conexion.conexion().prepareStatement(sqlSeleccionarObraSocial);
             psIdObrasSocial.setString(1, obraSocial.getNombre());
             ResultSet rsIdObraSocial = psIdObrasSocial.executeQuery();
             
             //comprobar existencia plan
             PreparedStatement psExistePlan = conexion.conexion().prepareStatement(sqlChequearExistenciaPlan);
             psExistePlan.setString(1, obraSocial.getPlan());
             ResultSet rsExistePlan = psExistePlan.executeQuery();
             
             //Insertar plan y obtener id
             int idPlan;
             if(!rsExistePlan.next()){
                  PreparedStatement psInsertarPlan = conexion.conexion().prepareStatement(sqlAgregarPlanObraSocia);
                  psInsertarPlan.setString(1, obraSocial.getPlan());
                  psInsertarPlan.executeQuery();
                  
                  PreparedStatement psBuscarIdPlan = conexion.conexion().prepareStatement(sqlChequearExistenciaPlan);
                  psBuscarIdPlan.setString(1, obraSocial.getPlan());
                  ResultSet rsIdPlan = psBuscarIdPlan.executeQuery();
                  if(rsIdPlan.next()){
                      idPlan = rsIdPlan.getInt("id_plan_obra_social");
                  }else{
                      idPlan=0;
                  }
                  
                  
                  psInsertarPlan.close();
                  
                  psBuscarIdPlan.close();
                  
                  rsIdPlan.close();
             }else{
                 idPlan = rsExistePlan.getInt("id_plan_obra_social");
             }
             
             //vicular obra social con el plan agregado
             PreparedStatement psVicularObraSocialPlan = conexion.conexion().prepareStatement(sqlVincularObraSocialPlan);
             if(rsIdObraSocial.next()){
                 
                 psVicularObraSocialPlan.setInt(1, rsIdObraSocial.getInt("id_obra_social"));
                 if(idPlan!=0){
                    psVicularObraSocialPlan.setInt(2, idPlan); 
                 }
                 psVicularObraSocialPlan.executeQuery();
             }
             
             psIdObrasSocial.close();
             
             rsIdObraSocial.close();
             
             psExistePlan.close();
             
             rsExistePlan.close();
             
             psVicularObraSocialPlan.close();
            
        } catch (Exception e) {
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
    
    
    public List<String> obtenerListaPlanesObrasSociales(ObraSocial obraSocial) {
        
        System.out.println(obraSocial.getNombre());
        
        String sqlListaPlanesOS = 
                "SELECT pos.nombre AS nombrePlan, os.nombre AS nombreObraSocial FROM \n" + 
                "obras_sociales os JOIN obras_sociales_planes_obras_sociales ospos ON \n " + 
                "os.id_obra_social = ospos.id_obra_social JOIN planes_obras_sociales pos ON \n" + 
                "ospos.id_plan_obra_social = pos.id_plan_obra_social WHERE os.nombre = ?;";
        List<String> ts = new ArrayList();
        try {
            PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlListaPlanesOS);
            psPlanes.setString(1, obraSocial.getNombre());
            ResultSet rsPlanes = psPlanes.executeQuery();
            while (rsPlanes.next()) {                
                ts.add(rsPlanes.getString("nombrePlan"));
            }
            psPlanes.close();
            rsPlanes.close();
            return ts;
            
        } catch (SQLException e) {
        }
        return null;
    }
    
}
