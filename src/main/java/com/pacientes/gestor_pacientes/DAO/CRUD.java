/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pacientes.gestor_pacientes.DAO;

import com.pacientes.gestor_pacientes.modelo.Usuario;
import java.util.List;

/**
 *
 * @author previotto
 */
public interface CRUD<T> {
   
    public List<T> obtenerLista(T objeto);
    public T obtener(T objeto);
    public void actualizar(T objeto, int numeroFucion);
    public void eliminar(T objeto, int numeroFuncion);
    public void insertar(T objeto, int numeroFucion);
}
