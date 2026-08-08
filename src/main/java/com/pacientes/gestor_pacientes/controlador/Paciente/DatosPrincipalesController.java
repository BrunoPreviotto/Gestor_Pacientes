/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador.Paciente;


import com.pacientes.gestor_pacientes.App;
import com.pacientes.gestor_pacientes.controlador.LIstaController;
import com.pacientes.gestor_pacientes.controlador.MensajeAdvertenciaController;
import com.pacientes.gestor_pacientes.controlador.MenuInicioController;
import com.pacientes.gestor_pacientes.implementacionDAO.Paciente.DatosPrincipalesDAOImplementacion;
import com.pacientes.gestor_pacientes.implementacionDAO.UsuarioDAOImplementacion;
import com.pacientes.gestor_pacientes.modelo.Paciente;
import com.pacientes.gestor_pacientes.utilidades.TablaDatosPrincipales;
import com.pacientes.gestor_pacientes.utilidades.Tablas;
import com.pacientes.gestor_pacientes.utilidades.VariablesEstaticas;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author previotto
 */
public class DatosPrincipalesController extends MenuInicioController{
    
    
    public void rellenarLista() {

        try {
            FXMLLoader Loader = new FXMLLoader(App.class.getResource("LIsta.fxml"));
            Parent root = Loader.load();
            LIstaController controller = Loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            scene.setFill(Color.TRANSPARENT);

            stage.initOwner(VariablesEstaticas.stagePrincipal);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.WINDOW_MODAL);

            daoImplementacion = new DatosPrincipalesDAOImplementacion();
            controller.iniciarTabla(pasarListaDatosPpalesAString(daoImplementacion.obtenerLista(new Paciente())));
          
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public ObservableList<Tablas> pasarListaDatosPpalesAString(List<Paciente> pacientes) {
        
        ObservableList<Tablas> olPacientes = FXCollections.observableArrayList();
        for (Paciente paciente : pacientes)  {
              olPacientes.add(new TablaDatosPrincipales(
                      paciente.getNombre(), 
                      paciente.getApellido(), 
                      String.valueOf(paciente.getEdad()),
                      String.valueOf(paciente.getDni()),
                      paciente.getTelefono().getTelefono(),
                      String.valueOf(paciente.getHonorarios().getHonorario())));
        }
        
           
        return  olPacientes;
    }
    
    
}
