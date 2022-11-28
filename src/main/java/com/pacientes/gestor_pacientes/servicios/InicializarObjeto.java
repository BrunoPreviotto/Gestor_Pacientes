/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.modelo.Usuario;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import java.util.List;
/**
 *
 * @author previotto
 */
public class InicializarObjeto {
   private static Usuario usuarioIniciado;
   
   /**
    * Este metodo inicializa un usuario con el constructor 2
    * @param id_usuario
    * @param nombre
    * @param apellido
    * @param usuario
    * @param contraseña
    * @param mail
    * @param es_usuario
    * @param id_es_ultima_sesion_iniciada
    * @return Usuario 
    */
   public static Usuario inicializarUsuario(int id_usuario, String nombre, String apellido, String usuario, String contraseña, String mail, boolean es_usuario, boolean es_ultima_sesion_iniciada){
       usuarioIniciado = new Usuario(id_usuario, nombre, apellido, usuario, contraseña, mail, es_usuario, es_ultima_sesion_iniciada);
       return usuarioIniciado;
   }
   
   public static Paciente inicializarPacienteRegistroDatosPrincipales(String nombre, String apellido, int edad, int dni,Telefono telefono){
       return new Paciente(nombre, apellido, edad, dni, telefono);
   }
   
   
}
