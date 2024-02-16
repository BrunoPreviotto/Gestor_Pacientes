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
  
    
    requires maven.shared.utils;
   
    
    requires org.json;
    
    requires java.net.http;
    
    requires maven.invoker;
    
    opens com.pacientes.gestor_pacientes to javafx.graphics, javafx.fxml;
    
   opens com.pacientes.gestor_pacientes.controlador to javafx.fxml;
    exports com.pacientes.gestor_pacientes;
    exports com.pacientes.gestor_pacientes.modelo;
     exports com.pacientes.gestor_pacientes.utilidades;
     exports com.pacientes.gestor_pacientes.servicios;
     
     
     
      
}
