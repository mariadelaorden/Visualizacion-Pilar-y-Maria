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

    private Stage scene;

    @FXML
    private void initialize() {
    }

    public void dibujarArbol(GenealogyNode arbolGenealogico, Individuo ultimoIndividuo) {
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

        if (ultimoIndividuo != null) {
            double centerX = arbolCanvas.getWidth() / 2;
            double centerY = arbolCanvas.getHeight() - 50;
            dibujarCirculo(grafica, centerX, centerY, "Último Individuo", ultimoIndividuo.getId());
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

    private void dibujarCirculo(GraphicsContext grafica, double centerX, double centerY, String texto, int idIndividuo) {
        grafica.setFill(Color.BLUE);
        grafica.fillOval(centerX - 20, centerY - 20, 40, 40);
        grafica.setFill(Color.WHITE);
        grafica.fillText(texto + ": " + idIndividuo, centerX - 30, centerY);
    }

    @FXML
    private void onCerrarButtonClick() {
        Scene scene = arbolCanvas.getScene();
        // Si la escena no es nula, obtenemos la ventana asociada
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();

            // Si la ventana no es nula, la cerramos
            if (stage != null) {
                stage.close();
            }
        }
    }
}
