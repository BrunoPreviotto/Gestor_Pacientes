/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pacientes.gestor_pacientes.DAO;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public interface CRUD<T> {
   
    public List<T> obtenerLista(T objetoParametro) throws SQLException;
    public T obtener(T objetoParametro)throws Exception;
    public void actualizar(T objetoParametro) throws Exception;
    public void eliminar(T objetoParametro)throws Exception;
    public void insertar(T objetoParametro)throws Exception;
    public int obtenerId(T objetoParametro)throws Exception;
    
}
