package es.uah.matcomp.proyecto.modelo.tablero;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import es.uah.matcomp.proyecto.modelo.tablero.Celda;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class CeldaInfo extends VBox {
    private static final Logger logger = LogManager.getLogger(CeldaInfo.class);

    private ListView<String> individuosListView;
    private ListView<String> recursosListView;

    public CeldaInfo() {
        // Inicializa los ListViews
        individuosListView = new ListView<>();
        recursosListView = new ListView<>();

        Label individuosLabel = new Label("Individuos:");
        Label recursosLabel = new Label("Recursos:");

        // Añade los elementos a la vista
        this.getChildren().addAll(individuosLabel, individuosListView, recursosLabel, recursosListView);
    }

    public void updateCelda(Celda celda) {
        try {
            // Limpiar los ListViews antes de agregar nuevos elementos
            individuosListView.getItems().clear();
            recursosListView.getItems().clear();

            for (int i = 0; i < celda.getIndividuos().getNumeroElementos(); i++) {
                Individuo individuo = (Individuo) celda.getIndividuos().getElemento(i).getData();
                String info = "ID: " + individuo.getId() +
                        ", Generación: " + individuo.getGeneracion() +
                        ", Vida: " + individuo.getVida() +
                        ", Prob. Reproducción: " + individuo.getProbReproduccion() +
                        ", Prob. Clonación: " + individuo.getProbClonacion() +
                        ", Prob. Muerte: " + individuo.getProbMuerte() +
                        ", Tipo: " + individuo.getTipo();
                individuosListView.getItems().add(info);
                logger.info(info);
            }
            for (int i = 0; i < celda.getRecursos().getNumeroElementos(); i++) {
                Recurso recurso = (Recurso) celda.getRecursos().getElemento(i).getData();
                String info = "Tipo: " + recurso.getClass().getSimpleName() +
                        ", Duración: " + recurso.getDuracion() +
                        ", Prob. Aparición: " + Recurso.getProbAparicion();
                recursosListView.getItems().add(info);
                logger.info(info);
            }
            // Log de contenido total después de la actualización
            logger.info("Individuos en ListView: " + individuosListView.getItems().size());
            logger.info("Recursos en ListView: " + recursosListView.getItems().size());
        } catch (NullPointerException e) {
            logger.error("Error de NullPointerException al actualizar la celda", e);
        } catch (Exception e) {
            logger.error("Error desconocido al actualizar la celda", e);
        }
    }
}
