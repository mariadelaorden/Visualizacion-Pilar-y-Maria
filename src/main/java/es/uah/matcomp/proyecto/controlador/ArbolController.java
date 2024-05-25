package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.modelo.individuo.GenealogyNode;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ArbolController {

    @FXML
    private Canvas arbolCanvas;

    @FXML
    private void initialize() {
        // Inicialización si es necesario
    }
    private Stage scene;

    public void dibujarArbol(GenealogyNode arbolGenealogico) {
        GraphicsContext grafica = arbolCanvas.getGraphicsContext2D();
        grafica.clearRect(0, 0, arbolCanvas.getWidth(), arbolCanvas.getHeight());
        grafica.setFill(Color.BLACK);
        grafica.setStroke(Color.BLACK);
        grafica.setLineWidth(2);

        if (arbolGenealogico != null) {
            double startX = arbolCanvas.getWidth() / 2;
            double startY = 50;
            dibujarNodo(grafica, arbolGenealogico, startX, startY, 200);
        }
    }

    private void dibujarNodo(GraphicsContext grafica, GenealogyNode nodo, double x, double y, double offset) {
        // Dibuja el nodo
        grafica.strokeOval(x - 30, y - 15, 60, 30);
        grafica.fillText("ID: " + nodo.getIndividuo().getId(), x - 20, y + 5);

        // Dibuja las ramas y nodos hijos
        if (offset > 20 && nodo.getHijos().getNumeroElementos() > 0) {
            for (int i = 0; i < nodo.getHijos().getNumeroElementos(); i++) {
                ElementoLS elHijo = nodo.getHijos().getElemento(i);
                if (elHijo != null) {
                    GenealogyNode hijo = (GenealogyNode) elHijo.getData();
                    double childX = x - offset + (i * 2 * offset) / (nodo.getHijos().getNumeroElementos() - 1);
                    double childY = y + 70;

                    grafica.strokeLine(x, y + 15, childX, childY - 15);
                    dibujarNodo(grafica, hijo, childX, childY, offset / 2);
                }
            }
        }
    }

    @FXML
    private void onSiguienteButtonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("estadisticas-view.fxml"));
    }
}
