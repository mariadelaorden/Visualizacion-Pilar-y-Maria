package es.uah.matcomp.proyecto.javafx.visualizacion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TableroController extends GridPane {
    @FXML
    private GridPane tableroGridPane;

    public TableroController(){

    }

    public void crearTablero(int ancho, int largo) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                // Crear celda
                Label celda = new Label("Celda");
                celda.setMinSize(30,30);
                celda.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-text-alignment: center;");

                // Añadir celda
                tableroGridPane.add(celda, i, j);
            }
        }
    }
}