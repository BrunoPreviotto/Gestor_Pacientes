/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author previotto
 */
public class ConexionMariadb {
   
    private static ConexionMariadb instancia;
    
    private ConexionMariadb(){};
    
    public static ConexionMariadb getInstacia(){
        if(instancia == null){
            instancia = new ConexionMariadb();
        }
        return instancia;
    }
    
    public Connection conexion(){
        Connection conexion = null;
        String url = "jdbc:mariadb://localhost:3306/gestion_pacientes";
        String user = "root";
        String pwd = "";
        try{
            conexion = DriverManager.getConnection(url, user, pwd);
        }catch(SQLException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }finally{
            return conexion;
        }
        
        
    }
    
    
}
