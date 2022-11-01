/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;
import com.pacientes.gestor_pacientes.modelo.Usuario;
/**
 *
 * @author previotto
 */
public class InicializarObjeto {
   private static Usuario usuarioIniciado;
   
   public static Usuario inicializarUsuario(int id_usuario, String nombre, String apellido, String usuario, String contraseña, String mail, boolean es_usuario, int id_es_ultima_sesion_iniciada){
       usuarioIniciado = new Usuario(id_usuario, nombre, apellido, usuario, contraseña, mail, es_usuario, id_es_ultima_sesion_iniciada);
       return usuarioIniciado;
   }
}
