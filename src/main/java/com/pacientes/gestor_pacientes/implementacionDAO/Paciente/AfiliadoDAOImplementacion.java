/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Afiliado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public class AfiliadoDAOImplementacion extends PadreDAOImplementacion implements CRUD<Afiliado>{

    @Override
    public List<Afiliado> obtenerLista(Afiliado objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Afiliado obtener(Afiliado objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Afiliado objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Afiliado objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(Afiliado objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int obtenerId(Afiliado objetoParametro) throws Exception {
        String sqlObtenerIdAfiliado = "SELECT id_afiliado_obra_social  FROM afiliados_obras_sociales aos WHERE numero_afiliado = ?;";
        
        
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdFrecuencia = conexion.conexion().prepareStatement(sqlObtenerIdAfiliado);
            psBuscarIdFrecuencia.setInt(1, objetoParametro.getNumero());
            ResultSet rsBuscarIdAfiliado= psBuscarIdFrecuencia.executeQuery();
            if(rsBuscarIdAfiliado.next()){
                return rsBuscarIdAfiliado.getInt("id_afiliado_obra_social");
            } 
            
        } catch (Exception e) {
        }
        return 0;
    }
    
}
