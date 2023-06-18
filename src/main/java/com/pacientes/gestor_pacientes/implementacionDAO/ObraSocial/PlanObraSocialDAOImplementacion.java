/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
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
public class PlanObraSocialDAOImplementacion extends PadreDAOImplementacion implements CRUD<ObraSocial>{

    @Override
    public List<ObraSocial> obtenerLista(ObraSocial objetoParametro) throws SQLException {
        String sqlListaPlanesOS = 
                "SELECT pos.nombre AS nombrePlan, os.nombre AS nombreObraSocial FROM \n" +
                "obras_sociales os \n" +
                "JOIN obras_sociales_planes_obras_sociales ospos ON os.id_obra_social = ospos.id_obra_social \n" +
                "JOIN planes_obras_sociales pos ON ospos.id_plan_obra_social = pos.id_plan_obra_social\n" +
                "WHERE os.nombre = ? AND ospos.id_usuario = ? AND os.id_obra_social = ? ;";
        List<ObraSocial> ts = new ArrayList();
        try {
            PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlListaPlanesOS);
            psPlanes.setString(1, objetoParametro.getNombre());
            psPlanes.setInt(2, VariablesEstaticas.usuario.getId());
            psPlanes.setInt(3, objetoParametro.getId());
            ResultSet rsPlanes = psPlanes.executeQuery();
            ObraSocial obraSocial;
            while (rsPlanes.next()) {   
                obraSocial = new ObraSocial();
                obraSocial.setPlan(rsPlanes.getString("nombrePlan"));
                ts.add(obraSocial);
            }
            psPlanes.close();
            rsPlanes.close();
            return ts;
            
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public ObraSocial obtener(ObraSocial objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(ObraSocial objetoParametro) throws Exception {
        if(objetoParametro.getIdPlan() == 0){
            insertar(objetoParametro);
        }else{
            eliminar(objetoParametro);
            insertar(objetoParametro);
        }
    }

    @Override
    public void eliminar(ObraSocial objetoParametro) throws Exception {
        String sql = "DELETE FROM obras_sociales_planes_obras_sociales \n" +
                        "WHERE id_obra_social = ? AND id_plan_obra_social = ? AND id_usuario = ?";
        
        PreparedStatement ps = conexion.conexion().prepareStatement(sql);
        ps.setInt(1, objetoParametro.getId());
        ps.setInt(2, objetoParametro.getIdPlan());
        ps.setInt(3, VariablesEstaticas.usuario.getId());
        ps.executeUpdate();
        
    }

    @Override
    public void insertar(ObraSocial objetoParametro) throws Exception {
       
        String sqlChequearExistenciaPlan = "SELECT nombre, id_plan_obra_social FROM planes_obras_sociales WHERE nombre = ?";

        String sqlAgregarPlanObraSocia = "INSERT INTO planes_obras_sociales \n"
                + " (id_plan_obra_social, nombre, descripcion) \n"
                + " VALUES (0, ? , 'descripcion')";

        String sqlVincularObraSocialPlan = "INSERT INTO obras_sociales_planes_obras_sociales \n"
                + "(id_obra_social, id_plan_obra_social, id_usuario) VALUES (?, ?, ?)";

        //obtener id obra social
        daoImplementacion = new ObraSocialDAOImplementacion();
        int idObraSocial = daoImplementacion.obtenerId(objetoParametro);
        

        //comprobar existencia plan
        PreparedStatement psExistePlan = conexion.conexion().prepareStatement(sqlChequearExistenciaPlan);
        psExistePlan.setString(1, objetoParametro.getPlan());
        ResultSet rsExistePlan = psExistePlan.executeQuery();

        //Insertar plan y obtener id
        int idPlan;
        if (!rsExistePlan.next()) {
            PreparedStatement psInsertarPlan = conexion.conexion().prepareStatement(sqlAgregarPlanObraSocia);
            psInsertarPlan.setString(1, objetoParametro.getPlan());
            psInsertarPlan.executeQuery();

            PreparedStatement psBuscarIdPlan = conexion.conexion().prepareStatement(sqlChequearExistenciaPlan);
            psBuscarIdPlan.setString(1, objetoParametro.getPlan());
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

        
        
        if (obtenerId(objetoParametro) == 0) {
            //vicular obra social con el plan agregado
            PreparedStatement psVicularObraSocialPlan = conexion.conexion().prepareStatement(sqlVincularObraSocialPlan);
            if (idObraSocial != 0) {

                psVicularObraSocialPlan.setInt(1, idObraSocial);
                if (idPlan != 0) {
                    psVicularObraSocialPlan.setInt(2, idPlan);
                }
                psVicularObraSocialPlan.setInt(3, VariablesEstaticas.usuario.getId());
                psVicularObraSocialPlan.executeQuery();
            }
             psVicularObraSocialPlan.close();
        }

       
        psExistePlan.close();

        rsExistePlan.close();

       
    }
    
    /*public int obtenerIdSimple(ObraSocial objetoParametro) throws Exception{
        String sql = "SELECT pos.id_plan_obra_social  \n" +
                        "FROM planes_obras_sociales pos\n" +
                        "WHERE pos.nombre = ?";
        PreparedStatement ps = conexion.conexion().prepareStatement(sql);
        ps.setString(1, objetoParametro.getNombre());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id_plan_obra_social");
        }
        
        return 0;
    }*/

    @Override
    public int obtenerId(ObraSocial objetoParametro) throws Exception {
        String sqlObtenerIdObraSocial = "SELECT pos.id_plan_obra_social  \n" +
                                        "FROM planes_obras_sociales pos\n" +
                                        "JOIN obras_sociales_planes_obras_sociales ospos ON pos.id_plan_obra_social = ospos.id_plan_obra_social \n" +
                                        "JOIN obras_sociales os ON ospos.id_obra_social = os.id_obra_social\n" +
                                        "WHERE pos.nombre = ? AND os.id_obra_social = ? AND ospos.id_usuario = ?;";
        
        try {
            
            //BUSCAR ID DE PLAN OBRA SOCIAL  
            PreparedStatement psBuscarIdPlanObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
            
            psBuscarIdPlanObraSocial.setString(1, objetoParametro.getPlan());
            
            psBuscarIdPlanObraSocial.setInt(2, objetoParametro.getId());
            psBuscarIdPlanObraSocial.setInt(3, VariablesEstaticas.usuario.getId());
            ResultSet rsBuscarIdPlanObraSocial= psBuscarIdPlanObraSocial.executeQuery();
            if(rsBuscarIdPlanObraSocial.next()){
                return rsBuscarIdPlanObraSocial.getInt("id_plan_obra_social");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
    
}
