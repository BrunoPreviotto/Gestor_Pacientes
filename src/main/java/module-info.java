module com.pacientes.gestor_pacientes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.webEmpty;
    requires java.base;
    requires java.sql;
    requires com.google.common;
    requires com.jfoenix;
    
    requires org.apache.commons.io;
    
    requires org.eclipse.jgit;
  
    requires dropbox.core.sdk;
    
    requires org.jsoup;
    
    
    
    
    opens com.pacientes.gestor_pacientes.controlador to javafx.fxml;
    exports com.pacientes.gestor_pacientes;
    
    exports com.pacientes.gestor_pacientes.modelo;
     exports com.pacientes.gestor_pacientes.utilidades;
     exports com.pacientes.gestor_pacientes.servicios;
     
}
