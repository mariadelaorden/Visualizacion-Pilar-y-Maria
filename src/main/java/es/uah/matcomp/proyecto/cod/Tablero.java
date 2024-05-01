package es.uah.matcomp.proyecto.cod;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Tablero {
    private int ancho;
    private int largo;
    private GridPane gridPane;

    public Tablero(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        this.gridPane = new GridPane();
        crearTablero();
    }

    private void crearTablero() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
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

    // Getters
    public GridPane getGridPane() {
        return gridPane;
    }

    public void actualizarTablero(int ancho, int largo) {
        // Limpiar el GridPane existente
        gridPane.getChildren().clear();

        // Crear un nuevo tablero con las nuevas dimensiones
        Tablero tablero = new Tablero(ancho, largo);

        // Agregar el nuevo tablero al GridPane
        gridPane.add(tablero.getGridPane(), 0, 0);
    }

}
