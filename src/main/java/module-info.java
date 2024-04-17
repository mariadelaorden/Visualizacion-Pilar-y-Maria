module es.uah.matcomp.proyecto.javafx.visualizacion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.uah.matcomp.proyecto.javafx.visualizacion to javafx.fxml;
    exports es.uah.matcomp.proyecto.javafx.visualizacion;
}