/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO;
import com.pacientes.gestor_pacientes.DAO.IObraSocialDAO;
import com.pacientes.gestor_pacientes.modelo.Email;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import com.pacientes.gestor_pacientes.modelo.Web;
import com.pacientes.gestor_pacientes.servicios.ConexionMariadb;
import com.pacientes.gestor_pacientes.servicios.ServicioObraSocial;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 *
 * @author previotto
 */
public class ObraSocialDAOImplementacion extends PadreDAOImplementacion implements IObraSocialDAO{
    
    
    private ObraSocial obraSocialGlobal;
    

    @Override
    public List<ObraSocial> obtenerLista() throws SQLException{
        
        String sqlObtenerObrasSociales = "SELECT os.id_obra_social,  os.nombre, os.web, e.email, tos.numero_telefono \n" +
                                        "FROM obras_sociales os \n" +
                                        "JOIN emails e ON os.id_email = e.id_email\n" +
                                        "JOIN telefonos_obras_sociales tos ON os.id_obra_social = tos.id_obra_social\n" +
                                        "JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social \n" +
                                        "JOIN usuarios us ON us.id_usuario = uos.id_usuario \n" +
                                        "WHERE os.es_obra_social = 1 AND us.es_ultima_sesion_iniciada = true";

        List<ObraSocial> listaObraSocial = new ArrayList();
        try {
            
            PreparedStatement psObrasSociales = conexion.conexion().prepareStatement(sqlObtenerObrasSociales);
            ResultSet rsObrasSociales = psObrasSociales.executeQuery();

            while (rsObrasSociales.next()) {
                
                listaObraSocial.add(
                        new ObraSocial(
                                rsObrasSociales.getInt("id_obra_social"),
                                rsObrasSociales.getString("nombre"),
                                new Telefono(rsObrasSociales.getString("numero_telefono")),
                                new Web(rsObrasSociales.getString("web")),
                                true,
                                new Email(rsObrasSociales.getString("email")),
                                obtenerListaPlanesObrasSociales(new ObraSocial(rsObrasSociales.getString("nombre")))));
            }
            
            
            return listaObraSocial;

        } catch (SQLException e) {
        }
        listaObraSocial = null;
        return listaObraSocial;
    }

    @Override
    public ObraSocial obtener(ObraSocial  obraSocial) throws SQLException{
        
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
    public void actualizar(ObraSocial  obraSocial, int numeroFuncion) throws SQLException{
        String sqlActualizarTelefono = "UPDATE telefonos_obras_sociales \n" +
                                       "SET numero_telefono = ? \n" +
                                       "WHERE id_obra_social = ?";
        
        String sqlActualizarEmail = "UPDATE emails \n" +
                                   "SET email = ?\n" +
                                   "WHERE id_email = ?";
        
        String sqlActualizarObraSocial = "UPDATE obras_sociales \n" +
                                         "SET nombre = ?, web = ?, id_email = ? \n" +
                                         "WHERE id_obra_social = ?";
        //BUSCAR ID EMAIL
        
        
        System.out.println(obraSocial.getEmail().getId());
        
        System.out.println(obraSocial.getId());
        
        if(obraSocial.getEmail().getId() != 0 && obraSocial.getId() != 0){
            
            //ACTUALIZAR TELEFONO
            PreparedStatement psActualizarTelefono = conexion.conexion().prepareStatement(sqlActualizarTelefono);
            psActualizarTelefono.setString(1, obraSocial.getTelefono().getTelefono());
            psActualizarTelefono.setInt(2, obraSocial.getId());
            psActualizarTelefono.executeQuery();
            
            //ACTUALIZAR EMAIL
            PreparedStatement psActualizarEmail = conexion.conexion().prepareStatement(sqlActualizarEmail);
            psActualizarEmail.setString(1, obraSocial.getEmail().getEmail());
            psActualizarEmail.setInt(2, obraSocial.getId());
            psActualizarEmail.executeQuery();
            
            //ACTUALIZAR OBRA SOCIAL
            PreparedStatement psActualizarObraSocial = conexion.conexion().prepareStatement(sqlActualizarObraSocial);
            psActualizarObraSocial.setString(1, obraSocial.getNombre());
            psActualizarObraSocial.setString(2, obraSocial.getWeb().getWeb());
            psActualizarObraSocial.setInt(3, obraSocial.getEmail().getId());
            psActualizarObraSocial.setInt(4, obraSocial.getId());
            psActualizarObraSocial.executeQuery();
            
        }else{
            throw sqlException;
        }
            
        
       
        
        
        

        
    }
    
    public int obtenerIdObraSocial(ObraSocial  obraSocial) throws SQLException{
        String sqlObtenerIdObraSocial = "SELECT os.id_obra_social \n" +
                                        "FROM obras_sociales os \n" +
                                        "WHERE os.nombre = ?";
        
        
        PreparedStatement psIdObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
        psIdObraSocial.setString(1, obraSocial.getNombre());
        ResultSet rsIdObraSocial = psIdObraSocial.executeQuery();
        
        if(rsIdObraSocial.next()){
            return rsIdObraSocial.getInt("id_obra_social");
        }else{
            return 0;
        }
        
    }
    
    

    @Override
    public void eliminar(ObraSocial  obraSocial, int numeroFucion) throws SQLException{
        String sqlEliminarObraSocial = "UPDATE obras_sociales \n" +
                                       "SET es_obra_social = 0 \n" +
                                       "WHERE id_obra_social = ?";
        
        PreparedStatement psEliminarObraSocial = conexion.conexion().prepareStatement(sqlEliminarObraSocial);
        psEliminarObraSocial.setInt(1, obraSocial.getId());
        psEliminarObraSocial.executeUpdate();
        
    }

    @Override
    public void insertar(ObraSocial obraSocial, int numeroFucion) throws SQLException{
        String sqlCrearObraSocial = "INSERT INTO obras_sociales (id_obra_social, nombre, web, es_obra_social, id_email) VALUES (?,?,?,?,?)";
        String sqlTelefonos = "INSERT INTO telefonos_obras_sociales (id_telefono, numero_telefono, id_obra_social) VALUES (?,?,?)";
        String sqlEmails = "INSERT INTO emails (id_email, email) VALUES (?,?)";
        
        String sqlAsociarObraSocialUsuario = "INSERT INTO usuarios_obras_sociales (id_usuario, id_obra_social) VALUES (?,?);";
        
        try {
            
               int idEmail = obtenerIdEmail(obraSocial.getEmail());
               //crear email
            if (idEmail == 0) {
                PreparedStatement pSEmail = conexion.conexion().prepareStatement(sqlEmails);
                pSEmail.setInt(1, 0);
                pSEmail.setString(2, obraSocial.getEmail().getEmail());
                pSEmail.executeUpdate();
            }
            
            idEmail = obtenerIdEmail(obraSocial.getEmail());
            //crear obra social
            PreparedStatement pSObraSocial = conexion.conexion().prepareStatement(sqlCrearObraSocial);
            pSObraSocial.setInt(1, 0);
            pSObraSocial.setString(2, obraSocial.getNombre());
            pSObraSocial.setString(3, obraSocial.getWeb().getWeb());
            pSObraSocial.setBoolean(4, true);
            pSObraSocial.setInt(5, idEmail);
            pSObraSocial.executeUpdate();

            int idObraSocial = obtenerIdObraSocial(obraSocial);
            //crear telefono
            PreparedStatement pSTelefono = conexion.conexion().prepareStatement(sqlTelefonos);
            pSTelefono.setInt(1, 0);
            pSTelefono.setString(2, obraSocial.getTelefono().getTelefono());
            pSTelefono.setInt(3, idObraSocial);
            pSTelefono.executeUpdate();
            
            //asociar obra social con usuario
            PreparedStatement pSAsociarObraSocialUsuario = conexion.conexion().prepareStatement(sqlAsociarObraSocialUsuario);
            pSAsociarObraSocialUsuario.setInt(1, VariablesEstaticas.usuario.getId());
            pSAsociarObraSocialUsuario.setInt(2, idObraSocial);
            pSAsociarObraSocialUsuario.executeUpdate();
            
            
            

            pSObraSocial.close();
            pSTelefono.close();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void agregarPlan(ObraSocial obraSocial) throws SQLException {

        String sqlSeleccionarObraSocial = "SELECT id_obra_social FROM obras_sociales WHERE nombre = ?";

        String sqlChequearExistenciaPlan = "SELECT nombre, id_plan_obra_social FROM planes_obras_sociales WHERE nombre = ?";

        String sqlAgregarPlanObraSocia = "INSERT INTO planes_obras_sociales \n"
                + " (id_plan_obra_social, nombre, descripcion) \n"
                + " VALUES (0, ? , 'descripcion')";

        String sqlVincularObraSocialPlan = "INSERT INTO obras_sociales_planes_obras_sociales \n"
                + "(id_obra_social, id_plan_obra_social) VALUES (?, ?)";

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
        if (!rsExistePlan.next()) {
            PreparedStatement psInsertarPlan = conexion.conexion().prepareStatement(sqlAgregarPlanObraSocia);
            psInsertarPlan.setString(1, obraSocial.getPlan());
            psInsertarPlan.executeQuery();

            PreparedStatement psBuscarIdPlan = conexion.conexion().prepareStatement(sqlChequearExistenciaPlan);
            psBuscarIdPlan.setString(1, obraSocial.getPlan());
            ResultSet rsIdPlan = psBuscarIdPlan.executeQuery();
            if (rsIdPlan.next()) {
                idPlan = rsIdPlan.getInt("id_plan_obra_social");
            } else {
                idPlan = 0;
            }

            psInsertarPlan.close();

            psBuscarIdPlan.close();

            rsIdPlan.close();
        } else {
            idPlan = rsExistePlan.getInt("id_plan_obra_social");
        }

        //vicular obra social con el plan agregado
        PreparedStatement psVicularObraSocialPlan = conexion.conexion().prepareStatement(sqlVincularObraSocialPlan);
        if (rsIdObraSocial.next()) {

            psVicularObraSocialPlan.setInt(1, rsIdObraSocial.getInt("id_obra_social"));
            if (idPlan != 0) {
                psVicularObraSocialPlan.setInt(2, idPlan);
            }
            psVicularObraSocialPlan.executeQuery();
        }

        psIdObrasSocial.close();

        rsIdObraSocial.close();

        psExistePlan.close();

        rsExistePlan.close();

        psVicularObraSocialPlan.close();

    }
    
    

    @Override
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
    }
    
    
    public List<String> obtenerListaPlanesObrasSociales(ObraSocial obraSocial) {
        
        System.out.println(obraSocial.getNombre());
        
        String sqlListaPlanesOS = 
                "SELECT pos.nombre AS nombrePlan, os.nombre AS nombreObraSocial FROM \n" +
                "obras_sociales os \n" +
                "JOIN obras_sociales_planes_obras_sociales ospos ON os.id_obra_social = ospos.id_obra_social \n" +
                "JOIN planes_obras_sociales pos ON ospos.id_plan_obra_social = pos.id_plan_obra_social\n" +
                "JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social \n" +
                "JOIN usuarios us ON us.id_usuario = uos.id_usuario \n" +
                "WHERE os.nombre = ? AND us.es_ultima_sesion_iniciada = true;";
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
