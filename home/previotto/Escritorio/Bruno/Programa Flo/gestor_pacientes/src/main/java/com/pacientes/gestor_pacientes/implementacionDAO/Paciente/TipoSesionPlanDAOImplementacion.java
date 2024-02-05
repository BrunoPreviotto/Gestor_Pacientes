/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;

import com.pacientes.gestor_pacientes.modelo.TipoSesion;
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
public class TipoSesionPlanDAOImplementacion extends PadreDAOImplementacion implements CRUD<TipoSesion>{

   

    @Override
    public TipoSesion obtener(TipoSesion objetoParametro) throws Exception {
        TipoSesion tipoSesionResultado = new TipoSesion();

        String sqlDescripcionTipoSesionPlan = "SELECT ts.descripcion, ts.nombre FROM tipos_sesiones ts WHERE ts.nombre = ? AND id_usuario = ?";

        int idTipoSesion = obtenerId(objetoParametro);
        
        if (idTipoSesion != 0) {
            PreparedStatement psDescripcionTipoSesionPlan = conexion.conexion().prepareStatement(sqlDescripcionTipoSesionPlan);
            psDescripcionTipoSesionPlan.setString(1, objetoParametro.getNombre());
            psDescripcionTipoSesionPlan.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rsDescripcionTipoSesionPlan = psDescripcionTipoSesionPlan.executeQuery();
            if (rsDescripcionTipoSesionPlan.next()) {
                tipoSesionResultado.setDecripcion(rsDescripcionTipoSesionPlan.getString("descripcion"));
                return tipoSesionResultado;
            }
        }else{
            throw sqlException;
        }

        return tipoSesionResultado;

    }

    @Override
    public void actualizar(TipoSesion objetoParametro) throws SQLException {
        String sqlActualizarTipoSesion = "UPDATE tipos_sesiones SET nombre = ?, descripcion = ? WHERE id_tipo_sesion = ? AND id_usuario = ?;";

        PreparedStatement psTipoSesion = conexion.conexion().prepareStatement(sqlActualizarTipoSesion);
        psTipoSesion.setString(1, objetoParametro.getNombre());
        psTipoSesion.setString(2, objetoParametro.getDecripcion());
        psTipoSesion.setInt(3, objetoParametro.getId());
        psTipoSesion.setInt(4, VariablesEstaticas.usuario.getId());
        psTipoSesion.executeUpdate(); 
    }

    @Override
    public void eliminar(TipoSesion objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(TipoSesion objetoParametro) throws Exception {
         String sqlTipoSesion = "INSERT INTO tipos_sesiones (id_tipo_sesion, nombre, descripcion, id_usuario) VALUES (?,?,?,?)";

        int idTipoSesion = obtenerId(objetoParametro);
        if (idTipoSesion == 0) {
            //INSERTAR SESION SI NO EXISTE
            PreparedStatement pstTs = conexion.conexion().prepareStatement(sqlTipoSesion);
            pstTs.setInt(1, 0);
            System.out.println(objetoParametro.getNombre());
            pstTs.setString(2, objetoParametro.getNombre());
            System.out.println(objetoParametro.getDecripcion());
            pstTs.setString(3, objetoParametro.getDecripcion());
            pstTs.setInt(4, VariablesEstaticas.usuario.getId());
            pstTs.executeUpdate();
            pstTs.close();
        } 
    }

    @Override
    public int obtenerId(TipoSesion objetoParametro) throws Exception {
         String sqlObtenerIdATipoSesion = "SELECT id_tipo_sesion FROM tipos_sesiones WHERE nombre=? AND id_usuario = ?;";
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdTipoSesion = conexion.conexion().prepareStatement(sqlObtenerIdATipoSesion);
            psBuscarIdTipoSesion.setString(1, objetoParametro.getNombre());
            psBuscarIdTipoSesion.setInt(2, VariablesEstaticas.usuario.getId());
            ResultSet rsBuscarIdTipoSesion= psBuscarIdTipoSesion.executeQuery();
            if(rsBuscarIdTipoSesion.next()){
                return rsBuscarIdTipoSesion.getInt("id_tipo_sesion");
            }else{
                return 0;
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public List<TipoSesion> obtenerLista(TipoSesion objetoParametro) throws SQLException {
        String sqlListaTipos = "SELECT nombre FROM tipos_sesiones WHERE id_usuario = ?";
        List<TipoSesion> ts = new ArrayList();

        try {
            PreparedStatement psTipo = conexion.conexion().prepareStatement(sqlListaTipos);
            psTipo.setInt(1, VariablesEstaticas.usuario.getId());
            ResultSet rsTipo = psTipo.executeQuery();
            while (rsTipo.next()) {
                ts.add(new TipoSesion(rsTipo.getString("nombre")));
            }
            psTipo.close();
            rsTipo.close();
            return ts;

        } catch (SQLException e) {
        }
        return null;
    }

   
    
}
