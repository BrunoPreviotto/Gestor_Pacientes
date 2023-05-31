/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pacientes.gestor_pacientes.DAO;

import com.pacientes.gestor_pacientes.modelo.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author previotto
 */
public interface CRUD<T> {
   
    public List<T> obtenerLista() throws SQLException;
    public T obtener(T objeto)throws SQLException;
    public void actualizar(T objeto, int numeroFucion) throws SQLException;
    public void eliminar(T objeto, int numeroFuncion)throws SQLException;
    public void insertar(T objeto, int numeroFucion)throws Exception;
}
