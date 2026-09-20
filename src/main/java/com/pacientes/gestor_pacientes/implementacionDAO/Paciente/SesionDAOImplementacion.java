/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.implementacionDAO.Paciente;

import com.pacientes.gestor_pacientes.DAO.CRUD;

import com.pacientes.gestor_pacientes.implementacionDAO.PadreDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.AutorizacionesSesionesObraSociales;
import com.pacientes.gestor_pacientes.modelo.CodigoFacturacion;
import com.pacientes.gestor_pacientes.modelo.EstadoFacturacion;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.modelo.SesionPaciente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author previotto
 */
public class SesionDAOImplementacion extends PadreDAOImplementacion implements CRUD<SesionPaciente>{

    @Override
    public List<SesionPaciente> obtenerLista(SesionPaciente objetoParametro) throws SQLException {
        String sqlListaSesiones = "SELECT  sp.id_sesion_paciente, a.id_autorizacion, sp.numero_sesion, sp.fecha, sp.trabajo_sesion, sp.observacion  AS obsevacionSesion, \n"
                + "sp.honorarios_por_sesion , a.numero_autorizacion, a.observacion AS observacionAutorizacion,\n"
                + "a.asociacion, a.copago, cf.nombre, ef.estado \n"
                + "FROM autorizaciones a JOIN sesiones_pacientes_autorizaciones spa ON a.id_autorizacion = spa.id_autorizacion\n"
                + "JOIN sesiones_pacientes sp ON spa.id_sesion_paciente = sp.id_sesion_paciente\n"
                + "JOIN codigos_facturaciones cf ON a.id_codigo_facturacion = cf.id_codigo_facturacion\n"
                + "JOIN estados_facturacion ef ON ef.id_estado_facturacion = sp.id_estado_facturacion \n"
                + "WHERE id_paciente=?\n"
                + "ORDER BY sp.numero_sesion ASC; ";
        List<SesionPaciente> ts = new ArrayList();
        Paciente paciente = new Paciente();
       

            PreparedStatement psSesiones = conexion.conexion().prepareStatement(sqlListaSesiones);
            psSesiones.setInt(1, objetoParametro.getIdPaciente());
            ResultSet rsSesiones = psSesiones.executeQuery();
            while (rsSesiones.next()) {

                ts.add(new SesionPaciente(rsSesiones.getInt("id_sesion_paciente"),
                        rsSesiones.getInt("numero_sesion"),
                        LocalDate.parse(rsSesiones.getString("fecha")),
                        rsSesiones.getString("trabajo_sesion"),
                        rsSesiones.getString("obsevacionSesion"),
                        Double.parseDouble(rsSesiones.getString("honorarios_por_sesion")),
                        new AutorizacionesSesionesObraSociales(
                                rsSesiones.getInt("id_autorizacion"), 
                                rsSesiones.getLong("numero_autorizacion"),
                                rsSesiones.getString("observacionAutorizacion"),
                                LocalDate.parse(rsSesiones.getString("asociacion")),
                                rsSesiones.getDouble("copago"), new CodigoFacturacion(rsSesiones.getString("nombre"))),
                        new EstadoFacturacion(rsSesiones.getString("estado"))));

            }
            if (!Objects.isNull(ts)) {
               return ts;
            }
            psSesiones.close();
            rsSesiones.close();
            
            return null;
        
    }

    @Override
    public SesionPaciente obtener(SesionPaciente objetoParametro) throws Exception {
         String sqlListaSesiones = "SELECT sp.id_sesion_paciente, a.id_autorizacion, sp.numero_sesion, sp.fecha, sp.trabajo_sesion, sp.observacion  AS obsevacionSesion, \n" +
"                sp.honorarios_por_sesion , a.numero_autorizacion, a.observacion AS observacionAutorizacion,\n" +
"                a.asociacion, a.copago, cf.nombre, ef.estado \n" +
"                FROM gestion_pacientes.autorizaciones a JOIN gestion_pacientes.sesiones_pacientes_autorizaciones spa ON a.id_autorizacion = spa.id_autorizacion\n" +
"                JOIN gestion_pacientes.sesiones_pacientes sp ON spa.id_sesion_paciente = sp.id_sesion_paciente\n" +
"               	JOIN gestion_pacientes.codigos_facturaciones cf ON a.id_codigo_facturacion = cf.id_codigo_facturacion\n" +
"               	JOIN gestion_pacientes.estados_facturacion ef ON ef.id_estado_facturacion = sp.id_estado_facturacion \n" +
"                WHERE sp.id_sesion_paciente = ? \n" +
"                ORDER BY sp.numero_sesion ASC;";
         
            SesionPaciente sesion;
       

            PreparedStatement psSesiones = conexion.conexion().prepareStatement(sqlListaSesiones);
            psSesiones.setInt(1, objetoParametro.getIdSesion());
            ResultSet rsSesiones = psSesiones.executeQuery();
            
            if(rsSesiones.next()){
                 sesion = new SesionPaciente(rsSesiones.getInt("id_sesion_paciente"),
                        rsSesiones.getInt("numero_sesion"),
                        LocalDate.parse(rsSesiones.getString("fecha")),
                        rsSesiones.getString("trabajo_sesion"),
                        rsSesiones.getString("obsevacionSesion"),
                        Double.parseDouble(rsSesiones.getString("honorarios_por_sesion")),
                        new AutorizacionesSesionesObraSociales(
                                rsSesiones.getInt("id_autorizacion"), 
                                rsSesiones.getLong("numero_autorizacion"),
                                rsSesiones.getString("observacionAutorizacion"),
                                LocalDate.parse(rsSesiones.getString("asociacion")),
                                rsSesiones.getDouble("copago"), new CodigoFacturacion(rsSesiones.getString("nombre"))),
                        new EstadoFacturacion(rsSesiones.getString("estado")));
            }else{
                sesion = null;
            }
         
           
            psSesiones.close();
            rsSesiones.close();
            
            return sesion;
    }

    @Override
    public void actualizar(SesionPaciente objetoParametro) throws Exception {
        String sqlActualizarSesion = "UPDATE sesiones_pacientes SET\n"
                + "fecha = ?, \n"
                + "trabajo_sesion = ?, \n"
                + "observacion = ?, \n"
                + "honorarios_por_sesion = ?,\n"
                + "id_estado_facturacion = ?\n"
                + "WHERE id_sesion_paciente = ? AND id_paciente=?;";

        

        String sqlEstadoFacturacion = "INSERT INTO estados_facturacion (id_estado_facturacion, estado) VALUES (0, ?);";
        
        daoImplementacion = new EstadoFacturacionDAOImplementacion();
        int idEstadoFacturacion = daoImplementacion.obtenerId(objetoParametro.getEstado());
        int idCodigoFacturacion = 0;
        daoImplementacion = new CodigoFacturacionDAOImplementacion();
        try {
            idCodigoFacturacion = daoImplementacion.obtenerId(objetoParametro.getAutorizacion().getCodigoFacturacion());
        } catch (Exception e) {
            throw new SQLException();
        }

        if (idEstadoFacturacion == 0) {
            PreparedStatement pstEstado = conexion.conexion().prepareStatement(sqlEstadoFacturacion);
            pstEstado.setString(1, objetoParametro.getEstado().getEstado());
            pstEstado.executeUpdate();
        }

        //ACTUALIZAR SESION
        PreparedStatement psActualizarSesion = conexion.conexion().prepareStatement(sqlActualizarSesion);
        psActualizarSesion.setDate(1, Date.valueOf(objetoParametro.getFecha().toString()));
        psActualizarSesion.setString(2, objetoParametro.getTrabajoSesion());
        psActualizarSesion.setString(3, objetoParametro.getObservacion());
        psActualizarSesion.setDouble(4, objetoParametro.getHonorarioPorSesion());
        daoImplementacion = new EstadoFacturacionDAOImplementacion();
        psActualizarSesion.setInt(5, daoImplementacion.obtenerId(objetoParametro.getEstado()));
        psActualizarSesion.setInt(6, objetoParametro.getIdSesion());
        psActualizarSesion.setInt(7, objetoParametro.getIdPaciente());

        psActualizarSesion.executeQuery();
        
        
        

        //ACTUALIZAR AUTORIZACION
        if (!existeAutorizacion(objetoParametro)) {
           objetoParametro.getAutorizacion().getCodigoFacturacion().setId(idCodigoFacturacion);
           daoImplementacion = new AutorizacionDAOImplementacion();
           daoImplementacion.actualizar(objetoParametro.getAutorizacion());
        }
    }

    @Override
    public void eliminar(SesionPaciente objetoParametro) throws Exception {
        String sqlEliminarSesion = "DELETE from sesiones_pacientes_autorizaciones  WHERE id_sesion_paciente = ? AND id_autorizacion = ?;";
        PreparedStatement psEliminarSesion = conexion.conexion().prepareStatement(sqlEliminarSesion);
        psEliminarSesion.setInt(1, objetoParametro.getIdSesion());
        psEliminarSesion.setInt(2, objetoParametro.getAutorizacion().getId());
        
        System.out.println("idsesion: " + objetoParametro.getIdSesion() + " idaut: " + objetoParametro.getAutorizacion().getId());
        psEliminarSesion.executeUpdate();
    }

    @Override
    public void insertar(SesionPaciente objetoParametro) throws Exception {
        String sqlSesion = "INSERT INTO sesiones_pacientes (id_sesion_paciente, fecha, trabajo_sesion, observacion, honorarios_por_sesion, id_paciente, numero_sesion, id_estado_facturacion) VALUES(?,?,?,?,?,?,?,?)";

        String sqlSesionAutorizacion = "INSERT INTO sesiones_pacientes_autorizaciones (id_sesion_paciente, id_autorizacion) VALUES (?, ?)";

        String sqlEstadoFacturacion = "INSERT INTO estados_facturacion (id_estado_facturacion, estado) VALUES (0, ?);";

        try {

            daoImplementacion = new EstadoFacturacionDAOImplementacion();
            int idEstadoFacturacion = daoImplementacion.obtenerId(objetoParametro.getEstado());
         
            
            
        

           
            
            if (idEstadoFacturacion == 0) {
                PreparedStatement pstEstado = conexion.conexion().prepareStatement(sqlEstadoFacturacion);
                pstEstado.setString(1, objetoParametro.getEstado().getEstado());
                pstEstado.executeUpdate();
                
            }
            
            
            //crea sesion
            PreparedStatement pst = conexion.conexion().prepareStatement(sqlSesion);
            pst.setInt(1, 0);
            pst.setDate(2, Date.valueOf(objetoParametro.getFecha()));
            pst.setString(3, objetoParametro.getTrabajoSesion());
            pst.setString(4, objetoParametro.getObservacion());
            pst.setDouble(5, objetoParametro.getHonorarioPorSesion());
            pst.setInt(6, objetoParametro.getIdPaciente());
            pst.setInt(7, objetoParametro.getNumeroSesion());
            daoImplementacion = new EstadoFacturacionDAOImplementacion();
            pst.setInt(8, daoImplementacion.obtenerId(objetoParametro.getEstado()));
            pst.executeUpdate();

           
            
            
             //ASOCIAR AUTORIZACION VACIA CON SESION
                int idSesion = obtenerId(objetoParametro);
                daoImplementacion = new AutorizacionDAOImplementacion();
                objetoParametro.getAutorizacion().setIdSesion(idSesion);
                daoImplementacion.insertar(objetoParametro.getAutorizacion());
                
                int idAutorizacion = daoImplementacion.obtenerId(objetoParametro.getAutorizacion());
                System.out.println("DADADADADADADA: " + idAutorizacion);
                if(idSesion != 0 && idAutorizacion!=0){
                       PreparedStatement psSesionAutorizacion = conexion.conexion().prepareStatement(sqlSesionAutorizacion);
                    psSesionAutorizacion.setInt(1, idSesion);
                    psSesionAutorizacion.setInt(2, idAutorizacion);
                    psSesionAutorizacion.executeUpdate();
                }else{
                    throw new Exception();
                }
             
            
           

            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int obtenerId(SesionPaciente objetoParametro) throws Exception {
        String sqlObtenerIdSesion = "SELECT sp.id_sesion_paciente  \n" +
                                         "FROM sesiones_pacientes sp \n" +
                                         "WHERE sp.numero_sesion  = ? \n" +
                                         "AND sp.fecha = ? \n" +
                                         "AND sp.id_paciente = ?;";
        
         
        try {
            
            //BUSCAR ID DE AFILIADO OBRA SOCIAL  
            PreparedStatement psBuscarIdSesion = conexion.conexion().prepareStatement(sqlObtenerIdSesion);
            
            
            psBuscarIdSesion.setInt(1, objetoParametro.getNumeroSesion());
            
            psBuscarIdSesion.setDate(2, Date.valueOf(objetoParametro.getFecha()));
            
            psBuscarIdSesion.setInt(3, objetoParametro.getIdPaciente());
            ResultSet rsBuscarSesion= psBuscarIdSesion.executeQuery();
            if(rsBuscarSesion.next()){
                return rsBuscarSesion.getInt("id_sesion_paciente");
            } 
            
            
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    public boolean existeAutorizacion(SesionPaciente sesion){
       
        return sesion.getAutorizacion().getNumeroAutorizacion() == 0 
               && sesion.getAutorizacion().getObservacion().equals("-") 
               && sesion.getAutorizacion().getAsociacion().toString().equals("1700-01-01")
               && sesion.getAutorizacion().getCopago() == 0;
    }
    
    public int obtenerultimaSesion(Paciente pacienteParametro){
        String sqlUltimaSesion = "SELECT MAX(numero_sesion) AS numeroSesion FROM sesiones_pacientes WHERE id_paciente = ?;";
        try {
            daoImplementacion = new PacienteDAOImplementacion();
            int idPaciente = daoImplementacion.obtenerId(pacienteParametro);
            
            PreparedStatement psUltimaSesion = conexion.conexion().prepareStatement(sqlUltimaSesion);
            
            if(idPaciente != 0){
                psUltimaSesion.setInt(1, idPaciente);
            }else{
                throw new SQLException();
            }
            
            ResultSet rsUltimaSesion = psUltimaSesion.executeQuery();
            if(rsUltimaSesion.next()){
                return rsUltimaSesion.getInt("numeroSesion");
            }
            return 0;
        } catch (Exception e) {
        }
        return 0;
    }

    
    
}
