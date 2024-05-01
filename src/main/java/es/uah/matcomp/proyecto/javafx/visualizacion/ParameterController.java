package es.uah.matcomp.proyecto.javafx.visualizacion;

import es.uah.matcomp.proyecto.javafx.visualizacion.TableroController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ParameterController implements Initializable {
    /**
     * Hooks de conexión entre los controles visuales y el código, llevan @FXML para identificarlos
     **/

    @FXML
    private Slider SliderAncho;
    @FXML
    private Slider SliderLargo;
    @FXML


    /**
     * Controlador con modelo de datos en el que trabajar
     **/
    private ParameterDataModelProperties model;
    private Stage scene;
    private TableroController tablero;


    /** Métodos de respuesta a eventos: El GUI llama a estos métodos del controlador para realizar operaciones **/
    /**
     * La convención es llamarlos on+TipoControl+operacionalaqueresponde :
     * onMiBotonEjemploClick indica que es un "manejador de evento de tipo click" del botón MiBotonEjemplo del interfaz
     */


    @FXML
    protected void onGuardarButtonClick() {
        model.commit();
    }

    @FXML
    protected void onReiniciarButtonClick() {
        model.rollback();
    }

    @FXML protected void onCerrarButtonClick(){
        scene.close();
    }

    /**
     * Métodos de configuración
     **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");

        if (model != null) {
            this.updateGUIwithModel();
        }
    }

    /**
     * Este método se encarga de conectar los datos del modelo con el GUI
     **/
    protected void updateGUIwithModel() {
        SliderAncho.valueProperty().bindBidirectional(model.anchoProperty());
        SliderLargo.valueProperty().bindBidirectional(model.largoProperty());
    }

    /**
     * Este método recibe los datos del modelo y los establece
     **/
    public void loadUserData(ParameterDataModelProperties parametrosData) {
        this.model = parametrosData;
        this.updateGUIwithModel();
    }

    public void setStage(Stage s){
        this.scene = s;
    }
    public void setTablero(TableroController tablero) {
        this.tablero = tablero;
    }
}

