package es.uah.matcomp.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ArbolController {

    @FXML
    private Canvas arbolCanvas;

    @FXML
    private void initialize() {
        dibujarArbol();
    }

    //EJEMPLO DIBUJAR ARBOL: hacerlo con los individuos

    private void dibujarArbol() {
        GraphicsContext grafica = arbolCanvas.getGraphicsContext2D();
        grafica.setFill(Color.BLACK);
        grafica.setStroke(Color.BLACK);
        grafica.setLineWidth(2);

        double startX = arbolCanvas.getWidth() / 2;
        double startY = 50;

        dibujarNodo(grafica, "Padre", startX, startY, 200);
    }

    private void dibujarNodo(GraphicsContext grafica, String text, double x, double y, double offset) {
        // Dibuja el nodo
        grafica.strokeOval(x - 30, y - 15, 60, 30);
        grafica.fillText(text, x - 20, y + 5);

        // Dibuja las ramas y nodos hijos
        if (offset > 20) {
            grafica.strokeLine(x, y + 15, x - offset, y + 70);
            grafica.strokeLine(x, y + 15, x + offset, y + 70);
            dibujarNodo(grafica, "Hijo 1", x - offset, y + 70, offset / 2);
            dibujarNodo(grafica, "Hijo 2", x + offset, y + 70, offset / 2);
        }
    }

    @FXML
    private void onCerrarButtonClick() {
        Stage stage = (Stage) arbolCanvas.getScene().getWindow();
        stage.close();
    }
}

