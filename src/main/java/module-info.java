module com.pacientes.gestor_pacientes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    

    opens com.pacientes.gestor_pacientes.controlador to javafx.fxml;
    exports com.pacientes.gestor_pacientes;
}
