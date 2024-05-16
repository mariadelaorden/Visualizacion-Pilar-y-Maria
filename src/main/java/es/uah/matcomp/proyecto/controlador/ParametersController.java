
package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ParametersController implements Initializable {
    /**
     * Hooks de conexión entre los controles visuales y el código, llevan @FXML para identificarlos
     **/

    @FXML
    private Slider SliderAncho;
    @FXML
    private Slider SliderLargo;
    @FXML
    private Slider sliderVidas;
    @FXML
    private Slider sliderProbReproduccion;
    @FXML
    private Slider sliderProbClonacion;
    @FXML
    ChoiceBox<String> seleccionTipo;

    /**
     * Controlador con modelo de datos en el que trabajar
     **/
    private ParameterDataModelProperties model;
    private Stage scene;

    /** Métodos de respuesta a eventos: El GUI llama a estos métodos del controlador para realizar operaciones **/
    /**
     * La convención es llamarlos on+TipoControl+operacionalaqueresponde :
     * onMiBotonEjemploClick indica que es un "manejador de evento de tipo click" del botón MiBotonEjemplo del interfaz
     */

    @FXML
    protected void onGuardarButtonClick() {
        model.commit();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tablero-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Tablero");
            stage.setScene(scene);
            TableroController tableroController = fxmlLoader.getController();
            tableroController.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void onReiniciarButtonClick() {
        this.model.rollback();
    }

    public void setStage(Stage s){
        this.scene = s;
    }

    @FXML protected void onCerrarButtonClick(){
        this.scene.close();
    }

    /**
     * Métodos de configuración
     **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");

        if (this.model != null) {
            this.updateGUIwithModel();
        }

    }

    /**
     * Este método se encarga de conectar los datos del modelo con el GUI
     **/
    protected void updateGUIwithModel() {
        SliderAncho.valueProperty().bindBidirectional(model.anchoProperty());
        SliderLargo.valueProperty().bindBidirectional(model.largoProperty());
        sliderVidas.valueProperty().bindBidirectional(model.vidasProperty());
        sliderProbReproduccion.valueProperty().bindBidirectional(model.probReproduccionProperty());
        sliderProbClonacion.valueProperty().bindBidirectional(model.probClonacionProperty());
        seleccionTipo.valueProperty().bindBidirectional(model.seleccionTipoProperty());
    }

    /**
     * Este método recibe los datos del modelo y los establece
     **/
    public void loadUserData(ParameterDataModelProperties parametrosData) {
        this.model = parametrosData;
        this.updateGUIwithModel();
    }


}
