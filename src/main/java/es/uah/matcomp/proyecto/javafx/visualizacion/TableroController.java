package es.uah.matcomp.proyecto.javafx.visualizacion;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TableroController {
    private int ancho;
    private int largo;
    private GridPane gridPane;

    // Constructor sin argumentos
    public TableroController() {
        // Puedes inicializar los atributos con valores por defecto aquí si es necesario
    }

    public TableroController(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        this.gridPane = new GridPane();
        crearTablero(ancho, largo);
    }

    public void crearTablero(int ancho, int largo) {
        for (int i = 0; i < this.ancho; i++) {
            for (int j = 0; j < this.largo; j++) {
                // Crear celda como un StackPane que contendrá el label
                StackPane celda = new StackPane();

                // Crear label para representar la celda
                Label label = new Label();
                label.setPrefSize(50, 50); // Tamaño de la celda
                label.setStyle("-fx-border-color: black; -fx-background-color: white;"); // Borde negro y fondo blanco
                label.setText(""); // Texto vacío

                // Añadir label a la celda
                celda.getChildren().add(label);

                // Añadir celda al tablero
                gridPane.add(celda, i, j);
            }
        }
    }

    // Getters y setters
    public int getAncho() {
        return this.ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return this.largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public GridPane getGridPane() {
        return this.gridPane;
    }
}
