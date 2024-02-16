/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author previotto
 */
public class ConsultasBD {
    
    private ConexionMariadb conexion = ConexionMariadb.getInstacia();
    
    /**
     * consulta la base de datos con la sentencia SELECT FROM
     * @param columnas lista compuesta por las columnas requeridas
     * @param tabla String compuesto por la tabla requerida
     * @return retorna un ResultSet de la consulta
     */
    public ResultSet selectFrom(List<String> columnas, String tabla){
        try{
            String listaResultado = stringSelect(columnas);
            if(!listaResultado.isEmpty() && !tabla.isEmpty()){
                String sql = "SELECT "+ listaResultado +" FROM " + tabla + ";";
                PreparedStatement pst = conexion.conexion().prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                rs.close();
                pst.close();
                if(rs.next()){
                   return rs;
                }
                
            }else{
               return null;
            }
            
        }catch(SQLException e){
            
            e.printStackTrace();
            return null;
        }
        
        return null;
    }
    
    
    
    /**
     * consulta la base de datos con la sentencia SELECT FROM JOIN
     * @param columnas lista compuesta por las columnas requeridas
     * @param tablaPrincipal String compuesto por la tabla principal requerida
     * @param idTablaPrincipal id que coincide con el de la tabla principal
     * @param Tablasjoin HashMap compuesto por las tablas join requerida
     * @return 
     */
    public ResultSet selectJoin(List<String> columnas, String tablaPrincipal, String idTablaPrincipal, HashMap<String, String> Tablasjoin){
        try{
            String listaSelectResultado = stringSelect(columnas);
            String listaJoinResultado = stringJoin(tablaPrincipal, idTablaPrincipal, Tablasjoin);
            if(!listaSelectResultado.isEmpty() && !tablaPrincipal.isEmpty() && !listaJoinResultado.isEmpty()){
                String sql = "SELECT "+ listaSelectResultado +" FROM " + tablaPrincipal + listaJoinResultado + ";";
                PreparedStatement pst = conexion.conexion().prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                rs.close();
                pst.close();
                if(rs.next()){
                   return rs;
                }
                
            }else{
               return null;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
        return null;
    }
    
     public ResultSet selectJoinWhere(List<String> columnas, String tablaPrincipal, String idTablaPrincipal, HashMap<String, String> Tablasjoin, HashMap<String, String> restricciones){
        try{
            String listaSelectResultado = stringSelect(columnas);
            String listaJoinResultado = stringJoin(tablaPrincipal, idTablaPrincipal, Tablasjoin);
            String listaRestricciones = stringWhere(restricciones);
            if(!listaSelectResultado.isEmpty() && !tablaPrincipal.isEmpty() && !listaJoinResultado.isEmpty()){
                String sql = "SELECT "+ listaSelectResultado +" FROM " + tablaPrincipal + listaJoinResultado + " " + listaRestricciones  + ";";
                PreparedStatement pst = conexion.conexion().prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                rs.close();
                pst.close();
                if(rs.next()){
                   return rs;
                }
                
            }else{
               return null;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
        return null;
    }
    
    public ResultSet selectWhere(List<String> columnas, String tablaPrincipal, HashMap<String, String> restricciones){
        try{
            String listaSelectResultado = stringSelect(columnas);
            String listaRestricciones = stringWhere(restricciones);
            if(!listaSelectResultado.isEmpty() && !tablaPrincipal.isEmpty()){
                String sql = "SELECT "+ listaSelectResultado +" FROM " + tablaPrincipal + " WHERE " + listaRestricciones  + ";";
               
                PreparedStatement pst = conexion.conexion().prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                rs.close();
                pst.close();
                if(rs.next()){
                   return rs;
                }
                
            }else{
               return null;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
        return null;
    }
    
    private static String stringSelect(List<String> lista){
        String resultado = "";
        if(!lista.isEmpty()){
                for (int i = 0; i < lista.size(); i++) {
                    if(i == 0){
                        resultado = lista.get(i);
                    }else{
                        resultado += ", " + lista.get(i);
                    }
                   
                }
        }
        return resultado;
    }
    
    private static String stringJoin(String from, String idFrom, HashMap<String, String> join){
        String resultado = "";
        
        for (Map.Entry<String, String> entry : join.entrySet()) {
            resultado += " JOIN " + entry.getKey() + " ON " + entry.getKey() + "." + entry.getValue() + "=" + from + "." +idFrom;
        }
        return resultado;
    }
    
    private static String stringWhere(HashMap<String, String> restricciones){
        String resultado = "";
        
        for (Map.Entry<String, String> entry : restricciones.entrySet()) {
            resultado += entry.getKey() + " = " + entry.getKey();
        }
        return resultado;
    }
}
