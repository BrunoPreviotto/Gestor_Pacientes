/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.ObraSocial.PlanObraSocialDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Afiliado;
import com.pacientes.gestor_pacientes.modelo.ObraSocial;
import com.pacientes.gestor_pacientes.modelo.ObraSocialPaciente;

import com.pacientes.gestor_pacientes.modelo.PlanObraSocial;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class ObraSocialPacienteDAOImplementacion extends PadreDAOImplementacion implements CRUD<ObraSocialPaciente>{

    @Override
    public List<ObraSocialPaciente> obtenerLista(ObraSocialPaciente objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ObraSocialPaciente obtener(ObraSocialPaciente objetoParametro) throws SQLException {
        
        try {
            String sqlObraSocial = "SELECT aos.numero_afiliado, os.nombre AS nombreObraSocial, pos.nombre AS nombrePlan \n" +
                                   "FROM afiliados_obras_sociales aos JOIN planes_obras_sociales pos\n" +
                                   "ON pos.id_plan_obra_social  = aos.id_plan_obra_social \n" + 
                                   "JOIN obras_sociales_planes_obras_sociales ospos \n" +
                                   "ON pos.id_plan_obra_social = ospos.id_plan_obra_social \n" + 
                                   "JOIN obras_sociales os ON os.id_obra_social = ospos.id_obra_social \n" + 
                                   "WHERE id_paciente = ?";
            PreparedStatement psObraSocial = conexion.conexion().prepareStatement(sqlObraSocial);
            psObraSocial.setInt(1, objetoParametro.getIdPaciente());
            ResultSet rsObraSocial = psObraSocial.executeQuery();
            
            if(rsObraSocial.next()){
                return new ObraSocialPaciente(new Afiliado(rsObraSocial.getInt("numero_afiliado")), rsObraSocial.getString("nombreObraSocial"), new PlanObraSocial(rsObraSocial.getString("nombrePlan"), ""));
            }
            return null;
            
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void actualizar(ObraSocialPaciente objetoParametro) throws Exception {
       String sqlActualizarObraSocialPaciente = "UPDATE afiliados_obras_sociales \n"
                + "SET id_obra_social = ?, id_plan_obra_social = ?, numero_afiliado = ?\n"
                + "WHERE  id_afiliado_obra_social = ? ";

        //ACTUALIZAR OBRA SOCIAL PACIENTE
        PreparedStatement psBuscarIdCodigo = conexion.conexion().prepareStatement(sqlActualizarObraSocialPaciente);
        
        int idObraSocialPaciente = obtenerId(objetoParametro);
        
        
        psBuscarIdCodigo.setInt(1, idObraSocialPaciente);
        
        daoImplementacion = new PlanObraSocialDAOImplementacion();
        int idPlan = daoImplementacion.obtenerId(new ObraSocial(obtenerId(objetoParametro),objetoParametro.getNombre(), objetoParametro.getPlan().getNombre()));
        
        psBuscarIdCodigo.setInt(2, idPlan);
        
        psBuscarIdCodigo.setInt(3, objetoParametro.getAfiliado().getNumero());
        
        psBuscarIdCodigo.setInt(4, objetoParametro.getAfiliado().getId());
        System.out.println(objetoParametro.getAfiliado().getId());
        
        
        
        psBuscarIdCodigo.executeUpdate();
    }

    @Override
    public void eliminar(ObraSocialPaciente objetoParametro) throws Exception {
        String sqlEliminarObraSocialPaciente = "DELETE FROM afiliados_obras_sociales WHERE id_paciente = ?;";
        PreparedStatement psEliminarObraSocialPaciente = conexion.conexion().prepareStatement(sqlEliminarObraSocialPaciente);
        try {
            psEliminarObraSocialPaciente.setInt(1, objetoParametro.getIdPaciente());
        } catch (Exception e) {
            throw sqlException;
        }
        psEliminarObraSocialPaciente.executeUpdate();
    }

    @Override
    public void insertar(ObraSocialPaciente objetoParametro) throws Exception {
        String sqlAfiliado = "INSERT INTO afiliados_obras_sociales (id_afiliado_obra_social, numero_afiliado, id_paciente, id_obra_social, id_plan_obra_social) VALUES (?,?,?,?,?)";
        String sqlObrasSocialesPlanesObrasSociales = "INSERT INTO obras_sociales_planes_obras_sociales (id_obra_social, id_plan_obra_social) VALUES (?,?)";

        daoImplementacion = new PlanObraSocialDAOImplementacion();
        
        int idObraSocia = obtenerId(objetoParametro);
        int idPlan = daoImplementacion.obtenerId(new ObraSocial(idObraSocia, objetoParametro.getNombre(), objetoParametro.getPlan().getNombre()));
        
            
        //CREAR NUEVO AFILIADO
        PreparedStatement psAfiliado = conexion.conexion().prepareStatement(sqlAfiliado);
        psAfiliado.setInt(1, 0);
        psAfiliado.setInt(2, objetoParametro.getAfiliado().getNumero());
        psAfiliado.setInt(3, objetoParametro.getIdPaciente());
        psAfiliado.setInt(4, idObraSocia);
        psAfiliado.setInt(5, idPlan);
        psAfiliado.executeQuery();
    }

    @Override
    public int obtenerId(ObraSocialPaciente objetoParametro) throws Exception {
        String sqlObtenerIdObraSocial = "SELECT os.id_obra_social FROM obras_sociales os\n" +
                                "JOIN usuarios_obras_sociales uos ON os.id_obra_social = uos.id_obra_social \n" +
                                "WHERE os.nombre = ? AND uos.id_usuario = ?;";
        
       
        
        try {
            
            //BUSCAR ID DE OBRA SOCIAL  
            PreparedStatement psBuscarIdObraSocial = conexion.conexion().prepareStatement(sqlObtenerIdObraSocial);
            psBuscarIdObraSocial.setString(1, objetoParametro.getNombre());
            psBuscarIdObraSocial.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rsBuscarIdObraSocial= psBuscarIdObraSocial.executeQuery();
            if(rsBuscarIdObraSocial.next()){
               
                return rsBuscarIdObraSocial.getInt("id_obra_social");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }

    
    
}
