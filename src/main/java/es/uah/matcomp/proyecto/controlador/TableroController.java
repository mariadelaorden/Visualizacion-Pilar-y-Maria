
package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.tablero.Celda;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TableroController extends GridPane implements Initializable {
    @FXML
    private GridPane tableroGridPane;
    private Tablero tablero;

    private int anchoTablero;
    private int largoTablero;

    private Stage scene;

    @FXML
    private Slider sliderVidas;
    @FXML
    private Slider sliderProbReproduccion;
    @FXML
    private Slider sliderProbClonacion;
    @FXML
    private ChoiceBox<String> seleccionTipo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.tablero = new Tablero(anchoTablero, largoTablero);
        crearTablero();
    }
//    public TableroController() {
//        this.anchoTablero = 10;
//        this.largoTablero = 10;
//
//        this.tablero = new Tablero(anchoTablero, largoTablero);
//        crearTablero();
//    }

    public void crearTablero() {
        for (int i = 0; i < this.anchoTablero; i++) {
            for (int j = 0; j < this.largoTablero; j++) {
                // Crear celdaLabel
                Label celdaLabel = new CustomLabel(i, j, "");
                celdaLabel.setMinSize(30, 30);
                celdaLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-text-alignment: center;");

                // Agregar opciones
                // celdaLabel.setOnMouseClicked(event -> mostrarOpcionesCelda(celdaLabel));
                celdaLabel.setOnMouseClicked(event -> addIndividuo((CustomLabel) event.getSource()));

                // Añadir celdaLabel
                tableroGridPane.add(celdaLabel, i, j);
            }
        }
        tablero.resetearTablero();
    }

    private void addIndividuo(CustomLabel celdaLabel) {
        System.out.println("Clicked position: " + celdaLabel.getI() + " " + celdaLabel.getJ());
        Celda celda = tablero.getCelda(celdaLabel.getI(), celdaLabel.getJ());
        celda.addIndividuo(new Individuo(1, 1, 1, 1, 1, TipoIndividuo.AVANZADO));
        celdaLabel.setStyle("-fx-background-color: black;");
    }

    public void onCargarPartidaButtonClick(ActionEvent actionEvent) {
        this.tablero.imprimirTablero();
    }

    public void setStage(Stage s){
        this.scene = s;
    }

    @FXML protected void onCerrarButtonClick(){
        this.scene.close();
    }
//    private void mostrarOpcionesCelda(Label celda) {
//        ContextMenu menu = new ContextMenu();
//        MenuItem opcion1 = new MenuItem("Añadir individuo");
//        MenuItem opcion2 = new MenuItem("Añadir recurso");
//
//        opcion1.setOnAction(event -> {
//            TextInputDialog dialog = new TextInputDialog();
//            dialog.setTitle("Añadir Individuo");
//            dialog.setHeaderText(null);
//            dialog.setContentText("Identificacion individuo:");
//
//            Optional<String> result = dialog.showAndWait();
//            result.ifPresent(identificacion -> {
//                // Capturar los valores de los sliders
//                int vidas = (int) sliderVidas.getValue();
//                double probReproduccion = sliderProbReproduccion.getValue();
//                double probClonacion = sliderProbClonacion.getValue();
//                String tipoIndividuo = seleccionTipo.getValue();
//
//                // Crear un nuevo individuo con los valores de los sliders
//                Individuo individuo = new Individuo(vidas, probReproduccion, probClonacion, tipoIndividuo);
//
//                // Agregar el nuevo individuo a la lista
//                individuos.add(individuo);
//
//                // Marcar la celda como ocupada
//                celda.setText("I");
//            });
//        });
//
//        menu.getItems().addAll(opcion1, opcion2);
//
//        menu.show(celda, Side.BOTTOM, 0, 0);
//    }

//
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Vincular los sliders a las propiedades adecuadas
//        sliderVidas.valueProperty().addListener((observable, oldValue, newValue) -> {
//            // Manejar el cambio de valor, si es necesario
//        });
//
//        sliderProbReproduccion.valueProperty().addListener((observable, oldValue, newValue) -> {
//            // Manejar el cambio de valor, si es necesario
//        });
//
//        sliderProbClonacion.valueProperty().addListener((observable, oldValue, newValue) -> {
//            // Manejar el cambio de valor, si es necesario
//        });
//    }
}
