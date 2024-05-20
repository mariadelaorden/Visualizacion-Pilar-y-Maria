
package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.tablero.CeldaInfo;
import es.uah.matcomp.proyecto.modelo.tablero.CustomLabel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class TableroController extends GridPane implements Initializable {
    @FXML
    private GridPane tableroGridPane;
    @FXML
    private Slider sliderVidas;
    @FXML
    private Slider sliderProbReproduccion;
    @FXML
    private Slider sliderProbClonacion;
    @FXML
    private ChoiceBox<String> seleccionTipo;

    private boolean tableroCreado = false;
    private Stage scene;

    private Stage parametersScene;
    private ParameterDataModelProperties modeloParaGUICompartido;

    private Timeline timeline;

    private int generacionActual = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            // Aquí van las operaciones que se deben realizar cada 2 segundos
            buclePrincipal();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void setParametersScene(Stage parametersScene) {
        this.parametersScene = parametersScene;
    }

    public void setModeloParaGUICompartido() {

        this.modeloParaGUICompartido = ParameterDataModelProperties.getInstance(null, null, null, null);
    }

    public void crearTablero() {
        for (int i = 0; i < this.modeloParaGUICompartido.originalTablero.getLargo(); i++) {
            for (int j = 0; j < this.modeloParaGUICompartido.originalTablero.getAncho(); j++) {
                // Crear celdaLabel
                Label celdaLabel = new CustomLabel(this.modeloParaGUICompartido.originalTablero.getCelda(i, j), this.modeloParaGUICompartido);

                // Abrir menu de info si se hace click izquierdo
                celdaLabel.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        CeldaInfo celdaInfo = new CeldaInfo();
                        CustomLabel celda = (CustomLabel) event.getSource();
                        celdaInfo.updateCelda(celda.getCelda());
                        Stage stage = new Stage();
                        stage.setTitle("Información de la celda");
                        stage.setScene(new Scene(celdaInfo, 700, 400));
                        stage.show();
                    }
                });


                // Añadir celdaLabel
                tableroGridPane.add(celdaLabel, i, j);
            }
        }
//        this.modeloParaGUICompartido.originalTablero.resetearTablero();
    }

    public void updateBoard() {
        for (Node node: this.tableroGridPane.getChildren()) {
            ((CustomLabel) node).updateLabel();
        }
    }

    public void setStage(Stage s){
        this.scene = s;
    }

    @FXML protected void onCerrarButtonClick(){
        this.scene.close();
    }

    public void onGuardarButtonClick(ActionEvent actionEvent) {
        this.modeloParaGUICompartido.originalTablero.imprimirTablero();
    }

    public void onReiniciarButtonClick(ActionEvent actionEvent) {

    }

    public void onParametrosButtonClick(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("parametrizar-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Parametros");
            stage.setScene(scene);
            ParametersController parametersController = fxmlLoader.getController();
            parametersController.loadUserData();
            parametersController.setStage(stage);
            parametersController.setOpenedFromMainWindow(false);
            parametersController.setPrevStage(this.scene);
            parametersController.disableTableroTab();
            parametersController.updateGUIwithModel();
            stage.show();
//            this.scene.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPlayButtonClick(ActionEvent actionEvent) {
        timeline.play();
    }

    public void onPauseButtonClick(ActionEvent actionEvent) {
        timeline.pause();
    }

    public void onStopButtonClick(ActionEvent actionEvent) {
        timeline.stop();
    }

    private void actualizarVidaIndividuos() {
        // Actualizar la vida de los individuos
        for (Node node: this.tableroGridPane.getChildren()) {
            ListaSimple individuos = ((CustomLabel) node).getCelda().getIndividuos();

            if (individuos.getNumeroElementos() > 0) {
                System.out.println("Individuos en celda antes del delete: (tamaño: " + individuos.getNumeroElementos());
                individuos.imprimir();
            }

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    System.out.println("El indice " + i + " es: " + el.getData());
                    Individuo ind = (Individuo) individuos.getElemento(i).getData();
                    ind.setVida(ind.getVida() - 1);
                    if (ind.getVida() <= 0) {
                        individuos.del(i);

                        System.out.println("Despues del delete: ");
                        individuos.imprimir();
                    }
                }

            }
        }
    }

    private void buclePrincipal() {
        // Implementar las operaciones que se deben realizar cada 2 segundos
        // Añadir un individuo en la posicion tempContadorX
        Individuo ind = new Individuo(this.modeloParaGUICompartido.individuoBasico, generacionActual, TipoIndividuo.BASICO);
//        Celda celda = this.modeloParaGUICompartido.originalTablero.getCelda(tempContadorX, 0);
//        celda.addIndividuo(ind);
        generacionActual++;
        System.out.println("Generación actual: " + generacionActual);

        // Para cada individuo, se actualiza su tiempo de vida, y en su caso se elimina si ha muerto.
        this.actualizarVidaIndividuos();

        //  Para cada recurso, evaluará si sigue activo o debe eliminarse (por su tiempo de aparición).

        //  Se ejecutará el movimiento de cada individuo (siempre obligatorio).

        //  Para cada individuo evaluará las mejoras obtenidas por los distintos recursos que se
        //encuentren en su nueva posición.

        // Para cada posición, evaluará si existe reproducción o no.

        // Para cada individuo, evaluará si existe clonación o no.

        // Para cada posición del tablero en la que existan varios individuos, se evaluará si deben
        //desaparecer algunos.

        // Para cada posición del tablero, se evaluará si deben aparecer nuevos recursos.
        // IMPORTANTE, FALTA NORMALIZAR LA PROBABILIDAD DE APARICIÓN DE LOS RECURSOS. (las V del enunciado)

        this.updateBoard();
    }
}