/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;
import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.TelefonoDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Honorario;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.Telefono;
import com.pacientes.gestor_pacientes.utilidades.Exepciones;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author previotto
 */
public class DatosPrincipalesDAOImplementacion extends PadreDAOImplementacion implements CRUD<Paciente>{

   

    @Override
    public Paciente obtener(Paciente objetoParametro) throws SQLException {
        
        Telefono telefono;
        Honorario honorario;
        Paciente paciente;
        Paciente pacienteNull = new Paciente();
        try {
            String sqlDni = "SELECT n.nombre, n.apellido, p.edad, p.dni, t.numero_telefono, h.honorario \n" +
                            "FROM pacientes p \n" +
                            "JOIN nombres n ON p.id_nombre = n.id_nombre \n" +
                            "JOIN telefonos_pacientes t ON p.id_telefono_paciente = t.id_telefono_paciente \n" +
                            "JOIN honorarios h ON p.id_honorario = h.id_honorario\n" +
                            "JOIN usuarios_pacientes up ON p.id_paciente = up.id_paciente \n" +
                            "WHERE p.id_paciente = ? AND up.id_usuario = ?;";
            
            PreparedStatement pSDni = conexion.conexion().prepareStatement(sqlDni);
            pSDni.setInt(1, objetoParametro.getId());
            pSDni.setInt(2, VariablesEstaticas.getUsuario().getId());
            
            ResultSet rsSPaciente = pSDni.executeQuery();
            if (rsSPaciente.next()) {
                telefono = new Telefono(rsSPaciente.getNString(5));
                honorario = new Honorario(rsSPaciente.getDouble("honorario"));
                paciente = new Paciente(rsSPaciente.getString(1), rsSPaciente.getString(2), rsSPaciente.getInt(3), rsSPaciente.getInt(4), honorario, telefono);
                
                pSDni.close();
                rsSPaciente.close();
                return paciente;
            }

        } catch (SQLException e) {
            return pacienteNull;
        }
        return pacienteNull;
    }

    @Override
    public void actualizar(Paciente objetoParametro) throws Exception {
        String sqlPaciente = "UPDATE pacientes SET edad=?, dni=?, id_nombre=?, id_telefono_paciente=?, es_paciente = true, id_honorario=? WHERE id_paciente=?;";

        //String sqlIdNombre = "SELECT id_nombre FROM nombres WHERE nombre = ? AND apellido = ?";
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";

        //String sqlIdTelefono = "SELECT id_telefono_paciente FROM telefonos_pacientes WHERE numero_telefono = ?";
        String sqlTelefono = "INSERT INTO telefonos_pacientes(id_telefono_paciente, numero_telefono) VALUES(?,?)";

        String sqlHonorario = "INSERT INTO honorarios (id_honorario, honorario) VALUES (?, ?);";


        

        if (ExisteMasDeUnPacientePOrUsuario(objetoParametro.getDni()) > 0) {
            Exepciones exepcionPacienteExiste = new Exepciones(111);
            throw exepcionPacienteExiste;
        } else {

            int idNombre = obtenerIdNombre(objetoParametro.getNombre(), objetoParametro.getApellido());
            daoImplementacion = new TelefonoDAOImplementacion();
            int idTelefono = daoImplementacion.obtenerId(objetoParametro.getTelefono());
            daoImplementacion = new HonorarioDAOImplementacion();
            int idHonorario = daoImplementacion.obtenerId(objetoParametro.getHonorarios());

            PreparedStatement pst;

            //si no existe honorario lo crea
            if (idHonorario == 0) {
                pst = conexion.conexion().prepareStatement(sqlHonorario);
                pst.setInt(1, 0);
                pst.setDouble(2, objetoParametro.getHonorarios().getHonorario());
                pst.executeUpdate();
            }

            //si no existe nombre lo crea
            if (idNombre == 0) {
                pst = conexion.conexion().prepareStatement(sqlNombre);
                pst.setInt(1, 0);
                pst.setString(2, objetoParametro.getNombre());
                pst.setString(3, objetoParametro.getApellido());
                pst.executeUpdate();
            }

            if (idTelefono == 0) {
                pst = conexion.conexion().prepareStatement(sqlTelefono);
                pst.setInt(1, 0);
                pst.setString(2, objetoParametro.getTelefono().getTelefono());
                pst.executeUpdate();
            }

            //crea el paciente
            pst = conexion.conexion().prepareStatement(sqlPaciente);

            pst.setInt(1, objetoParametro.getEdad());
            pst.setInt(2, objetoParametro.getDni());
            pst.setInt(3, obtenerIdNombre(objetoParametro.getNombre(), objetoParametro.getApellido()));
            daoImplementacion = new TelefonoDAOImplementacion();
            pst.setInt(4, daoImplementacion.obtenerId(objetoParametro.getTelefono()));
            daoImplementacion = new HonorarioDAOImplementacion();
            pst.setInt(5, daoImplementacion.obtenerId(objetoParametro.getHonorarios()));

            pst.setInt(6, obtenerIdPacientePOrUsuario(objetoParametro.getDni()));

            pst.executeUpdate();
            pst.close();
        

            pst.setInt(6, objetoParametro.getId());

            pst.executeUpdate();
            pst.close();
        }


    }

    @Override
    public void eliminar(Paciente objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar(Paciente objetoParametro) throws Exception {
        String sqlNombre = "INSERT INTO nombres(id_nombre, nombre, apellido) VALUES(?,?,?)";

        String sqlPaciente = "INSERT INTO pacientes(id_paciente, edad, dni, es_paciente, id_nombre, id_honorario, id_telefono_paciente) VALUES(?, ?, ?, ?, ?, ?, ?)";

        String sqlTelefono = "INSERT INTO telefonos_pacientes(id_telefono_paciente, numero_telefono) VALUES(?,?)";

        String sqlHonorario = "INSERT INTO honorarios (id_honorario, honorario) VALUES (?, ?);";

        String sqlAsociarPacienteConUsuario = "INSERT INTO usuarios_pacientes (id_usuario, id_paciente) VALUES (?, ?)";

        if (ExisteMasDeUnPacientePOrUsuario(objetoParametro.getDni()) > 0) {
            Exepciones exepcionPacienteExiste = new Exepciones(111);
            throw exepcionPacienteExiste;
        } else {
            try {
                PreparedStatement pst;

                int idPaciente = obtenerIdPacienteConTodosLosDatos(objetoParametro);
                int idNombre = obtenerIdNombre(objetoParametro.getNombre(), objetoParametro.getApellido());
                daoImplementacion =  new TelefonoDAOImplementacion();
                int idTelefono = daoImplementacion.obtenerId(objetoParametro.getTelefono());
                daoImplementacion = new HonorarioDAOImplementacion();
                int idHonorario = daoImplementacion.obtenerId(objetoParametro.getHonorarios());

                //si no existe honorario lo crea
                if (idHonorario == 0) {
                    pst = conexion.conexion().prepareStatement(sqlHonorario);
                    pst.setInt(1, 0);
                    pst.setDouble(2, objetoParametro.getHonorarios().getHonorario());
                    pst.executeUpdate();
                }

                //si no existe nombre lo crea
                if (idNombre == 0) {
                    pst = conexion.conexion().prepareStatement(sqlNombre);
                    pst.setInt(1, 0);
                    pst.setString(2, objetoParametro.getNombre());
                    pst.setString(3, objetoParametro.getApellido());
                    pst.executeUpdate();
                }

                //si no existe telefono lo crea
                if (idTelefono == 0) {
                    pst = conexion.conexion().prepareStatement(sqlTelefono);
                    pst.setInt(1, 0);
                    pst.setString(2, objetoParametro.getTelefono().getTelefono());
                    pst.executeUpdate();
                }

                //crea el paciente
                if (idPaciente == 0) {
                    pst = conexion.conexion().prepareStatement(sqlPaciente);
                    pst.setInt(1, 0);
                    pst.setInt(2, objetoParametro.getEdad());
                    pst.setInt(3, objetoParametro.getDni());
                    pst.setBoolean(4, true);
                    pst.setInt(5, obtenerIdNombre(objetoParametro.getNombre(), objetoParametro.getApellido()));
                    daoImplementacion = new HonorarioDAOImplementacion();
                    pst.setInt(6, daoImplementacion.obtenerId(objetoParametro.getHonorarios()));
                    daoImplementacion = new TelefonoDAOImplementacion();
                    pst.setInt(7, daoImplementacion.obtenerId(objetoParametro.getTelefono()));
                    pst.executeUpdate();
                } else {
                    actualizar(objetoParametro);
                }
                
                //ASOCIAR PACIENTE CON USUARIO
                //crea el paciente
                pst = conexion.conexion().prepareStatement(sqlAsociarPacienteConUsuario);
                pst.setInt(1, VariablesEstaticas.usuario.getId());
                pst.setInt(2, obtenerIdPacienteConTodosLosDatos(objetoParametro));
                pst.executeUpdate();

                pst.close();
            } catch (Exception e) {
                Exepciones exepcioSql = new Exepciones(222);
                throw exepcioSql;
            }

        }
    }

    @Override
    public int obtenerId(Paciente objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Paciente> obtenerLista(Paciente objetoParametro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

   
    
}
