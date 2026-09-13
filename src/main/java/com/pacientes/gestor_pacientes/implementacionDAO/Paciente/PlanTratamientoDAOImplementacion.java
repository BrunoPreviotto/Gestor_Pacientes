/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.FrecuenciaSesion;

import com.pacientes.gestor_pacientes.modelo.PlanTratamiento;
import com.pacientes.gestor_pacientes.modelo.TipoSesion;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author previotto
 */
public class PlanTratamientoDAOImplementacion extends PadreDAOImplementacion implements CRUD<PlanTratamiento>{

    @Override
    public List<PlanTratamiento> obtenerLista(PlanTratamiento objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PlanTratamiento obtener(PlanTratamiento objetoParametro) throws SQLException {
        PlanTratamiento planTratamiento = new PlanTratamiento();
        
        String sqlPlanes = "SELECT fs.frecuencia, ts.nombre, ts.descripcion, pt.estrategia FROM planes_tratamientos pt JOIN tipos_sesiones ts ON pt.id_tipo_sesion = ts.id_tipo_sesion JOIN frecuencias_sesiones fs ON pt.id_frecuencia_sesion = fs.id_frecuencia_sesion WHERE id_paciente=?";

        PreparedStatement psPlan = conexion.conexion().prepareStatement(sqlPlanes);
        
        if(Objects.nonNull(VariablesEstaticas.paciente)){
            psPlan.setInt(1, VariablesEstaticas.paciente.getId());
            ResultSet rsPlan = psPlan.executeQuery();

            if (rsPlan.next()) {
                planTratamiento = new PlanTratamiento(rsPlan.getString("estrategia"), new FrecuenciaSesion(rsPlan.getString("frecuencia")), new TipoSesion(rsPlan.getString("nombre"), rsPlan.getString("descripcion")));
                return planTratamiento;
            }
        return null;
        }else{
            return null;
        }
        
        

    }

    @Override
    public void actualizar(PlanTratamiento objetoParametro) throws Exception {
        //UPDATE planes
        String sqlPlanesTratamientos = "UPDATE planes_tratamientos SET estrategia=?, id_frecuencia_sesion=?, id_tipo_sesion=? WHERE id_paciente=?";
        try {
            
            daoImplementacion =  new FrecuenciaSesionPlanDAOImplementacion();
            int idFrecuencia = daoImplementacion.obtenerId(objetoParametro.getFrecuenciaSesion());
            daoImplementacion = new TipoSesionPlanDAOImplementacion();
            int idTipoSesion = daoImplementacion.obtenerId(objetoParametro.getTipoSEsion());
            
             int idPlan =  obtenerId(objetoParametro);
            //Actualiza Planes
            
            if(idPlan==0){
                insertar(objetoParametro);
            }else{
                    PreparedStatement psPlanes = conexion.conexion().prepareStatement(sqlPlanesTratamientos);
                    psPlanes.setString(1, objetoParametro.getEstrategia());
                    psPlanes.setInt(2, idFrecuencia);
                    psPlanes.setInt(3, idTipoSesion);
                    psPlanes.setInt(4, objetoParametro.getIdPaciente());
                    psPlanes.executeQuery();

                    psPlanes.close();
            }
          
            
           
           
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void eliminar(PlanTratamiento objetoParametro) throws Exception {
        String sqlEliminarPlanTratamiento = "DELETE FROM planes_tratamientos WHERE id_paciente = ?;";
        PreparedStatement psEliminarPlanTratamiento = conexion.conexion().prepareStatement(sqlEliminarPlanTratamiento);
        try {
            psEliminarPlanTratamiento.setInt(1, objetoParametro.getIdPaciente());
        } catch (Exception e) {
            throw new SQLException();
        }
        psEliminarPlanTratamiento.executeUpdate();
    }

    @Override
    public void insertar(PlanTratamiento objetoParametro) throws Exception {
        String sqlPlanTratamiento = "INSERT INTO planes_tratamientos (id_plan_tratamiento, estrategia, id_frecuencia_sesion, id_paciente, id_tipo_sesion) VALUES (?,?,?,?,?)";
        
        daoImplementacion = new FrecuenciaSesionPlanDAOImplementacion();
        int idFrecuencia = daoImplementacion.obtenerId(objetoParametro.getFrecuenciaSesion());
        daoImplementacion =  new TipoSesionPlanDAOImplementacion();
        int idTipoSesion = daoImplementacion.obtenerId(objetoParametro.getTipoSEsion());

        //INSERTAR PLAN PACIENTE
        PreparedStatement pstPt = conexion.conexion().prepareStatement(sqlPlanTratamiento);
        pstPt.setInt(1, 0);
        pstPt.setString(2, objetoParametro.getEstrategia());

        pstPt.setInt(3, idFrecuencia);

        pstPt.setInt(4, objetoParametro.getIdPaciente());

        pstPt.setInt(5, idTipoSesion);

        pstPt.executeUpdate();

        pstPt.close();
    }

    @Override
    public int obtenerId(PlanTratamiento objetoParametro) throws Exception {
         String sqlPlanes = "SELECT pt.id_plan_tratamiento  \n" +
                                        "  FROM gestion_pacientes.planes_tratamientos pt \n" +
                                        "  WHERE id_paciente=?;";

        PreparedStatement psPlan = conexion.conexion().prepareStatement(sqlPlanes);
        
        if(Objects.nonNull(VariablesEstaticas.paciente)){
            psPlan.setInt(1, VariablesEstaticas.paciente.getId());
            ResultSet rsPlan = psPlan.executeQuery();

            if (rsPlan.next()) {
                return rsPlan.getInt("id_plan_tratamiento");
            }
        return 0;
        }else{
            return 0;
        }
    }

    
    
    

    
    
    
}
