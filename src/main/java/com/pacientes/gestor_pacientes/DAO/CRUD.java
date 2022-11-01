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
    public List<T> obtenerClientes();
    public List<String> obtener(String usuario, String contraseña);
    public void actualizar(T usuario);
    public void eliminar(T usuario);
    public void insertar(T usuario);
}
