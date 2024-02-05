/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;


import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.EstadoFacturacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class EstadoFacturacionDAOImplementacion extends PadreDAOImplementacion implements CRUD<EstadoFacturacion>{

    @Override
    public List<EstadoFacturacion> obtenerLista(EstadoFacturacion objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EstadoFacturacion obtener(EstadoFacturacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(EstadoFacturacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(EstadoFacturacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(EstadoFacturacion objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int obtenerId(EstadoFacturacion objetoParametro) throws Exception {
        String sqlEstado = "SELECT ef.id_estado_facturacion FROM estados_facturacion ef WHERE estado = ?";
        
        
        
        PreparedStatement psEstado = conexion.conexion().prepareStatement(sqlEstado);
        psEstado.setString(1, objetoParametro.getEstado());
        ResultSet rsEstado = psEstado.executeQuery();
        if (rsEstado.next()) {
            psEstado.close();
            rsEstado.close();
            return rsEstado.getInt("id_estado_facturacion");
        }else{

            return 0;
        }
    }
    
}
