/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pacientes.gestor_pacientes.controlador;


import com.jfoenix.controls.JFXButton;
import com.pacientes.gestor_pacientes.servicios.ServiciosPadre;
import com.pacientes.gestor_pacientes.utilidades.DraggedScene;
import com.pacientes.gestor_pacientes.utilidades.Tablas;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author previotto
 */
public class LIstaController extends ClasePadreController implements Initializable, DraggedScene{

    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonMinimizar;
    
    
    @FXML
    private AnchorPane anchorPadreTablas;
    
    @FXML
    private AnchorPane anchorTabla;
    @FXML
    private JFXButton botonBuscar;
    
    private TableView<Tablas> tablaPrincipal;
    @FXML
    private TextField cajaBuscar;
    @FXML
    private AnchorPane anchorBotonesBuscarArribaAbajo;
    @FXML
    private AnchorPane botonBuscarArriba;
    @FXML
    private Button botonRetornarDatosPrincipales1;
    
    private ObservableList<Tablas> listaOriginal;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ServiciosPadre serviciosPadre = new ServiciosPadre();
        anchorPadreTablas.setStyle(serviciosPadre.iniciarColorApp());
        this.onDraggedScene( anchorPadreTablas);
    }   
    
   
    public void iniciarTabla(ObservableList<Tablas> lista){
        //TableView<Tablas> tableView = new TableView<>();
        
        tablaPrincipal = new TableView<>();
        Class<?> objetoClase = lista.isEmpty() ? Object.class : lista.get(0).getClass();
        
        for (Field field : objetoClase.getDeclaredFields()) {
            TableColumn<Tablas, Object> columna = new TableColumn<>(field.getName());
            columna.setCellValueFactory(data -> {
                try {
                    field.setAccessible(true);
                    return new SimpleObjectProperty<>(field.get(data.getValue()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error al acceder al campo " + field.getName(), e);
                }
            });
            tablaPrincipal.getColumns().add(columna);
             columna.prefWidthProperty().bind(tablaPrincipal.widthProperty().multiply(1.0 / objetoClase.getDeclaredFields().length));
        }
        
        tablaPrincipal.setItems(lista);
        
        tablaPrincipal.getStyleClass().add("decoracionTabla");
        
        AnchorPane.setBottomAnchor(tablaPrincipal, 10.0);
        AnchorPane.setLeftAnchor(tablaPrincipal, 10.0);
        AnchorPane.setRightAnchor(tablaPrincipal, 10.0);
        AnchorPane.setTopAnchor(tablaPrincipal, 10.0);
        
        //anchorTable.getChildren().add(tablaPrincipal);
        
        anchorTabla.getChildren().add(tablaPrincipal);
        
        
        /*ColumnaSesionNumero.setCellValueFactory(new PropertyValueFactory<>("numeroSesion"));
             @FXML
        protected TableColumn<TablaSesiones, String> ColumnaSesionNumero;
        
        */
        
    }
    
    @FXML
    public void salir(MouseEvent event) {
         Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    
    @FXML
    private void buscar(MouseEvent event) {
     /*    String searchTerm = cajaBuscar.getText().toLowerCase();
    List<Integer> resultados = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < tablaPrincipal.getItems().size(); rowIndex++) {
        for (TableColumn<Tablas, ?> column : tablaPrincipal.getColumns()) {
            String cellValue = String.valueOf(column.getCellObservableValue(tablaPrincipal.getItems().get(rowIndex)).getValue()).toLowerCase();
            if (cellValue.contains(searchTerm)) {
                resultados.add(rowIndex);
                break; // Rompemos el bucle de columnas después de encontrar la coincidencia en la fila actual
            }
        }
    }

    if (!resultados.isEmpty()) {
        // Mostrar resultados
        for (Integer rowIndex : resultados) {
            System.out.println("String encontrado en la fila " + rowIndex);
            tablaPrincipal.getSelectionModel().select(rowIndex);
        }

        // Desplazar a la primera fila encontrada
        tablaPrincipal.scrollTo(resultados.get(0));
    } else {
        System.out.println("String no encontrado en la TableView.");
    }*/
        
        String searchTerm = cajaBuscar.getText().toLowerCase();
    
    // Guardar la lista original antes de la búsqueda
    listaOriginal = FXCollections.observableArrayList(tablaPrincipal.getItems());

    // Limpiar la tabla antes de la búsqueda
    tablaPrincipal.getItems().clear();

    // Realizar la búsqueda y almacenar los resultados
    List<Integer> resultados = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < listaOriginal.size(); rowIndex++) {
        for (TableColumn<Tablas, ?> column : tablaPrincipal.getColumns()) {
            String cellValue = String.valueOf(column.getCellObservableValue(listaOriginal.get(rowIndex)).getValue()).toLowerCase();
            if (cellValue.contains(searchTerm)) {
                resultados.add(rowIndex);
                break;
            }
        }
    }

    if (!resultados.isEmpty()) {
        // Mostrar resultados
        for (Integer rowIndex : resultados) {
            System.out.println("String encontrado en la fila " + rowIndex);
            tablaPrincipal.getItems().add(listaOriginal.get(rowIndex));
            tablaPrincipal.getSelectionModel().select(rowIndex);
        }

        // Desplazar a la primera fila encontrada
        tablaPrincipal.scrollTo(resultados.get(0));
    } else {
        System.out.println("String no encontrado en la TableView.");
    }

    // Restablecer la lista original después de la búsqueda
    //tablaPrincipal.getItems().setAll(listaOriginal);
        
    }

    

    @FXML
    private void subirBusqueda(MouseEvent event) {
    }

    @FXML
    private void bajarBusqueda(MouseEvent event) {
    }

    

    @FXML
    private void volverTablaNormal(MouseEvent event) {
        cajaBuscar.setText("");
        tablaPrincipal.getItems().setAll(listaOriginal);
    }

    
    

   

   

    

   

    
    
}
