module com.pacientes.gestor_pacientes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.webEmpty;
    requires java.base;
    requires java.sql;
    requires com.google.common;
    requires com.jfoenix;
    
    opens com.pacientes.gestor_pacientes.controlador to javafx.fxml;
    exports com.pacientes.gestor_pacientes;
    
    exports com.pacientes.gestor_pacientes.modelo;
     exports com.pacientes.gestor_pacientes.utilidades;
}
