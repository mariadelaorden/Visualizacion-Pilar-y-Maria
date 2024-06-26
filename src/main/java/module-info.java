module es.uah.matcomp.proyecto.javafx.visualizacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires org.apache.logging.log4j;

    opens es.uah.matcomp.proyecto.controlador to javafx.fxml, com.google.gson;
    exports es.uah.matcomp.proyecto.controlador;

    exports es.uah.matcomp.proyecto.estructurasdedatos.listas;
    opens es.uah.matcomp.proyecto.estructurasdedatos.listas to javafx.fxml, com.google.gson;

    exports es.uah.matcomp.proyecto.modelo.individuo;
    opens es.uah.matcomp.proyecto.modelo.individuo to javafx.fxml, com.google.gson;

    exports es.uah.matcomp.proyecto.modelo.recurso;
    opens es.uah.matcomp.proyecto.modelo.recurso to javafx.fxml, com.google.gson;

    exports es.uah.matcomp.proyecto.modelo.tablero;
    opens es.uah.matcomp.proyecto.modelo.tablero to javafx.fxml, com.google.gson;
}
