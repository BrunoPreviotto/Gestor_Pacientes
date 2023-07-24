/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
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
public class CodigoFacturacionDAOImplementacion extends PadreDAOImplementacion implements CRUD<CodigoFacturacion>{

    @Override
    public List<CodigoFacturacion> obtenerLista(CodigoFacturacion objetoParametro) throws SQLException {
        String sqlListaCodigos = "SELECT nombre FROM codigos_facturaciones WHERE id_usuario = ?;";
        List<CodigoFacturacion> ts = new ArrayList();

        try {
            PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlListaCodigos);
            psCodigo.setInt(1, VariablesEstaticas.usuario.getId());
            ResultSet rsCodigos = psCodigo.executeQuery();
            while (rsCodigos.next()) {
                ts.add(new CodigoFacturacion(rsCodigos.getString("nombre")));
            }
            psCodigo.close();
            rsCodigos.close();
            return ts;

        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public CodigoFacturacion obtener(CodigoFacturacion objetoParametro) throws SQLException {
        String sqlCodigo = "SELECT cf.codigo, cf.nombre FROM codigos_facturaciones cf WHERE cf.nombre = ? AND cf.id_usuario = ?;";
        PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlCodigo);
        psCodigo.setString(1, objetoParametro.getNombre());
        psCodigo.setInt(2, VariablesEstaticas.usuario.getId());
        ResultSet rsCodigo = psCodigo.executeQuery();
        if(rsCodigo.next()){
            return new CodigoFacturacion(rsCodigo.getString("nombre"), Integer.parseInt(rsCodigo.getString("codigo")));
        }else{
            return null;
        }
    }

    @Override
    public void actualizar(CodigoFacturacion objetoParametro) throws SQLException {
        String sqlActualizarCodigoFacturacion = "UPDATE codigos_facturaciones SET nombre = ?, codigo = ? WHERE id_codigo_facturacion = ? AND id_usuario = ?;";
        PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlActualizarCodigoFacturacion);
        psCodigo.setString(1, objetoParametro.getNombre());
        psCodigo.setInt(2, objetoParametro.getCodigo());
        psCodigo.setInt(3, objetoParametro.getId());
        psCodigo.setInt(4, VariablesEstaticas.usuario.getId());
        psCodigo.executeUpdate();
    }

    @Override
    public void eliminar(CodigoFacturacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(CodigoFacturacion objetoParametro) throws Exception {
        String sqlCodigoFacturacion = "INSERT INTO codigos_facturaciones (id_codigo_facturacion, nombre, codigo, id_usuario) VALUES (0, ?, ?, ?);";
        
        daoImplementacion = new CodigoFacturacionDAOImplementacion();
        int idCodigo = obtenerId(objetoParametro);
        
        if(idCodigo == 0){
            PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlCodigoFacturacion);
            psCodigo.setString(1, objetoParametro.getNombre());
            psCodigo.setInt(2, objetoParametro.getCodigo());
            psCodigo.setInt(3, VariablesEstaticas.usuario.getId());
            psCodigo.executeUpdate();
        }
    }

    @Override
    public int obtenerId(CodigoFacturacion objetoParametro) throws Exception {
        String sqlIdCodFacturacion = "SELECT id_codigo_facturacion FROM codigos_facturaciones WHERE nombre =?";
        
        PreparedStatement psCodigo = conexion.conexion().prepareStatement(sqlIdCodFacturacion);
        psCodigo.setString(1, objetoParametro.getNombre());
        ResultSet rsCodigo = psCodigo.executeQuery();
        if (rsCodigo.next()) {
            psCodigo.close();
            rsCodigo.close();
            return rsCodigo.getInt("id_codigo_facturacion");
        }else{

            return 0;
        }
    }
    
}
