package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class CeldaInfo extends VBox {
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
            System.out.println(info);
        }
        for (int i = 0; i < celda.getRecursos().getNumeroElementos(); i++) {
            Recurso recurso = (Recurso) celda.getRecursos().getElemento(i).getData();
            String info = "Tipo: " + recurso.getClass().getSimpleName() +
                    ", Duración: " + recurso.getDuracion() +
                    ", Prob. Aparición: " + Recurso.getProbAparicion();
            recursosListView.getItems().add(info);
            System.out.println(info);
        }
    }
}