package es.uah.matcomp.proyecto.javafx.visualizacion;

import es.uah.matcomp.proyecto.cod.ElementoLS;
import es.uah.matcomp.proyecto.cod.Individuo;
import es.uah.matcomp.proyecto.cod.ListaEnlazada;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableroController extends GridPane {
    @FXML
    private GridPane tableroGridPane;
    private ListaEnlazada individuos;

    @FXML
    private Slider sliderVidas;
    @FXML
    private Slider sliderProbReproduccion;
    @FXML
    private Slider sliderProbClonacion;
    @FXML
    private ChoiceBox<String> seleccionTipo;

    public TableroController(){

    }

    public void crearTablero(int ancho, int largo) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                // Crear celda
                Label celda = new Label("");
                celda.setMinSize(30,30);
                celda.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-text-alignment: center;");

                // Agregar opciones
                celda.setOnMouseClicked(event -> mostrarOpcionesCelda(celda));

                // Añadir celda
                tableroGridPane.add(celda, i, j);
            }
        }
    }
    private void mostrarOpcionesCelda(Label celda) {
        ContextMenu menu = new ContextMenu();
        MenuItem opcion1 = new MenuItem("Añadir individuo");
        MenuItem opcion2 = new MenuItem("Añadir recurso");

        opcion1.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Añadir Individuo");
            dialog.setHeaderText(null);
            dialog.setContentText("Identificacion individuo:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(identificacion -> {
                // Capturar los valores de los sliders
                int vidas = (int) sliderVidas.getValue();
                double probReproduccion = sliderProbReproduccion.getValue();
                double probClonacion = sliderProbClonacion.getValue();
                String tipoIndividuo = seleccionTipo.getValue();

                // Crear un nuevo individuo con los valores de los sliders
                Individuo individuo = new Individuo(vidas, probReproduccion, probClonacion, tipoIndividuo);

                // Agregar el nuevo individuo a la lista
                individuos.add(individuo);

                // Marcar la celda como ocupada
                celda.setText("I");
            });
        });

        menu.getItems().addAll(opcion1, opcion2);

        menu.show(celda, Side.BOTTOM, 0, 0);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Vincular los sliders a las propiedades adecuadas
        sliderVidas.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Manejar el cambio de valor, si es necesario
        });

        sliderProbReproduccion.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Manejar el cambio de valor, si es necesario
        });

        sliderProbClonacion.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Manejar el cambio de valor, si es necesario
        });
    }
}