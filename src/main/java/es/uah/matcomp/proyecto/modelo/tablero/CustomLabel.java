package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.controlador.ParameterDataModelProperties;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLabel extends Label {

    private static final Logger logger = LogManager.getLogger(CustomLabel.class);

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
        this.setOnMouseExited(event -> this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5;"));

        this.setFont(Font.font("Segoe UI Emoji", 15));
        addContextMenu();
        updateLabel();
        logger.info("CustomLabel initialized.");
    }

    private void addContextMenu() {
        try {
            ContextMenu contextMenu = new ContextMenu();

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
                try {
                    celda.addIndividuo(new Individuo(parametros.getIndividuoBasico(), 0, TipoIndividuo.BASICO));
                    updateLabel();
                    logger.info("Individuo Basico added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Individuo Basico", ex);
                }
            });

            individuoNormal.setOnAction(e -> {
                try {
                    celda.addIndividuo(new Individuo(parametros.getIndividuoNormal(), 0, TipoIndividuo.NORMAL));
                    updateLabel();
                    logger.info("Individuo Normal added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Individuo Normal", ex);
                }
            });

            individuoAvanzado.setOnAction(e -> {
                try {
                    celda.addIndividuo(new Individuo(parametros.getIndividuoAvanzado(), 0, TipoIndividuo.AVANZADO));
                    updateLabel();
                    logger.info("Individuo Avanzado added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Individuo Avanzado", ex);
                }
            });

            agua.setOnAction(e -> {
                try {
                    celda.addRecurso(new Agua());
                    updateLabel();
                    logger.info("Agua added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Agua", ex);
                }
            });

            comida.setOnAction(e -> {
                try {
                    celda.addRecurso(new Comida());
                    updateLabel();
                    logger.info("Comida added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Comida", ex);
                }
            });

            biblioteca.setOnAction(e -> {
                try {
                    celda.addRecurso(new Biblioteca());
                    updateLabel();
                    logger.info("Biblioteca added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Biblioteca", ex);
                }
            });

            montaña.setOnAction(e -> {
                try {
                    celda.addRecurso(new Montana());
                    updateLabel();
                    logger.info("Montaña added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Montana", ex);
                }
            });

            pozo.setOnAction(e -> {
                try {
                    celda.addRecurso(new Pozo());
                    updateLabel();
                    logger.info("Pozo added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Pozo", ex);
                }
            });

            tesoro.setOnAction(e -> {
                try {
                    celda.addRecurso(new Tesoro());
                    updateLabel();
                    logger.info("Tesoro added.");
                } catch (Exception ex) {
                    logger.error("Failed to add Tesoro", ex);
                }
            });

            contextMenu.getItems().addAll(individuoBasico, individuoNormal, individuoAvanzado, agua, comida, biblioteca, montaña, pozo, tesoro);
            this.setContextMenu(contextMenu);
            logger.info("Context menu added.");
        } catch (Exception ex) {
            logger.error("Failed to add context menu", ex);
        }
    }

    public Celda getCelda() {
        return celda;
    }

    public void actualizarTiempoVida() {
        try {
            for (int i = 0; i < this.celda.getIndividuos().getNumeroElementos(); i++) {
                Individuo ind = (Individuo) celda.getIndividuos().getElemento(i).getData();
                ind.setVida(ind.getVida() - 1);
                if (ind.getVida() <= 0) {
                    celda.getIndividuos().del(i);
                }
            }
            logger.info("Tiempo de vida actualizado.");
        } catch (Exception ex) {
            logger.error("Failed to actualizar tiempo de vida", ex);
        }
    }

    public void updateLabel() {
        try {
            this.setText(this.celda.toString());
            logger.info("Label updated.");
        } catch (Exception ex) {
            logger.error("Failed to update label", ex);
        }
    }
}
