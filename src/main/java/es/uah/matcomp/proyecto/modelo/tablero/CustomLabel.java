
package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.controlador.ParameterDataModelProperties;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.Agua;
import es.uah.matcomp.proyecto.modelo.recurso.Biblioteca;
import es.uah.matcomp.proyecto.modelo.recurso.Comida;
import es.uah.matcomp.proyecto.modelo.recurso.Montana;
import es.uah.matcomp.proyecto.modelo.recurso.Pozo;
import es.uah.matcomp.proyecto.modelo.recurso.Tesoro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;

public class CustomLabel extends Label {

    private final ParameterDataModelProperties parametros;
    private final Celda celda;

    public CustomLabel(Celda celda, ParameterDataModelProperties parametros) {
        super();
        this.celda = celda;
        this.parametros = parametros;
        this.setMinSize(80, 80);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5;");
        this.setOnMouseEntered(event -> this.setStyle("-fx-background-color: lightgray;  -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5;"));
        this.setOnMouseExited(event ->this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5;"));

        this.setFont(Font.font("Segoe UI Emoji", 15));
        addContextMenu();
        updateLabel();
    }

    private void addContextMenu() {
        // create a menu
        ContextMenu contextMenu = new ContextMenu();

        // create a menu item
        MenuItem individuoBasico = new MenuItem("Individuo Basico");
        MenuItem individuoNormal = new MenuItem("Individuo Normal");
        MenuItem individuoAvanzado = new MenuItem("Individuo Avanzado");
        MenuItem agua = new MenuItem("Agua");
        MenuItem comida = new MenuItem("Comida");
        MenuItem biblioteca = new MenuItem("Biblioteca");
        MenuItem montaña = new MenuItem("Montaña");
        MenuItem pozo = new MenuItem("Pozo");
        MenuItem tesoro = new MenuItem("Tesoro");

        individuoBasico.setOnAction(e -> {
            celda.addIndividuo(new Individuo(parametros.getIndividuoBasico(), 0, TipoIndividuo.BASICO));
            updateLabel();
        });

        individuoNormal.setOnAction(e -> {
            celda.addIndividuo(new Individuo(parametros.getIndividuoNormal(), 0, TipoIndividuo.NORMAL));
            updateLabel();
        });

        individuoAvanzado.setOnAction(e -> {
            celda.addIndividuo(new Individuo(parametros.getIndividuoAvanzado(), 0, TipoIndividuo.AVANZADO));
            updateLabel();
        });

        agua.setOnAction(e -> {
            celda.addRecurso(new Agua());
            updateLabel();
        });

        comida.setOnAction(e -> {
            celda.addRecurso(new Comida());
            updateLabel();
        });

        biblioteca.setOnAction(e -> {
            celda.addRecurso(new Biblioteca());
            updateLabel();
        });

        montaña.setOnAction(e -> {
            celda.addRecurso(new Montana());
            updateLabel();
        });

        pozo.setOnAction(e -> {
            celda.addRecurso(new Pozo());
            updateLabel();
        });

        tesoro.setOnAction(e -> {
            celda.addRecurso(new Tesoro());
            updateLabel();
        });


        contextMenu.getItems().addAll(individuoBasico, individuoNormal, individuoAvanzado, agua, comida, biblioteca, montaña, pozo, tesoro);

        this.setContextMenu(contextMenu);
    }

    public Celda getCelda() {
        return celda;
    }

    public void actualizarTiempoVida() {
        for (int i = 0; i < this.celda.getIndividuos().getNumeroElementos(); i++) {
            Individuo ind = (Individuo) celda.getIndividuos().getElemento(i).getData();
            ind.setVida(ind.getVida() - 1);
            if (ind.getVida() <= 0) {
                celda.getIndividuos().del(i);
            }
        }
    }

    public void updateLabel() {
        this.setText(this.celda.toString());
    }
}