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
public interface IUsuarioDAO extends CRUD<Usuario>{
    public List<String> existeUsuarioReciente();
    public Usuario obtenerNombreUsuario();
}
