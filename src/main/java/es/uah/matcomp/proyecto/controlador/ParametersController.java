
package es.uah.matcomp.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ParametersController implements Initializable {

    //    public Slider sliderVidasBasico;
//    public Slider sliderProbReproduccionBasico;
//    public Slider sliderProbClonacionBasico;
    public Slider sliderVidasNormal;
    public Slider sliderProbReproduccionNormal;
    public Slider sliderProbClonacionNormal;
    public Slider sliderVidasAvanzado;
    public Slider sliderProbReproduccionAvanzado;
    public Slider sliderProbClonacionAvanzado;
    /**
     * Hooks de conexión entre los controles visuales y el código, llevan @FXML para identificarlos
     **/

    private boolean openedFromMainWindow;
    private Stage prevStage;



    @FXML
    private Slider SliderAncho;
    @FXML
    private Slider SliderLargo;
    @FXML
    private Slider sliderVidasBasico;
    @FXML
    private Slider sliderProbReproduccionBasico;
    @FXML
    private Slider sliderProbClonacionBasico;

    public Tab tableroTab;
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
            tableroController.setModeloParaGUICompartido(this.model);
            tableroController.crearTablero();
            tableroController.setParametersScene(this.scene);
            tableroController.setStage(stage);
            stage.close();
            stage.show();
            this.scene.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void disableTableroTab() {
        tableroTab.setDisable(true);
    }



    @FXML
    protected void onReiniciarButtonClick() {
        this.model.rollback();
    }


    public void setStage(Stage s){
        this.scene = s;
    }

    public Stage getPrevStage() {
        return prevStage;
    }

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    public void setOpenedFromMainWindow(boolean openedFromMainWindow) {
        this.openedFromMainWindow = openedFromMainWindow;
    }

    @FXML protected void onCerrarButtonClick(){
        this.model.rollback();
        this.scene.close();
        this.prevStage.show();
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

        sliderVidasBasico.valueProperty().bindBidirectional(model.vidasBasicoProperty());
        sliderProbReproduccionBasico.valueProperty().bindBidirectional(model.probReproduccionBasicoProperty());
        sliderProbClonacionBasico.valueProperty().bindBidirectional(model.probClonacionBasicoProperty());
        sliderVidasNormal.valueProperty().bindBidirectional(model.vidasNormalProperty());
        sliderProbReproduccionNormal.valueProperty().bindBidirectional(model.probReproduccionNormalProperty());
        sliderProbClonacionNormal.valueProperty().bindBidirectional(model.probClonacionNormalProperty());
        sliderVidasAvanzado.valueProperty().bindBidirectional(model.vidasAvanzadoProperty());
        sliderProbReproduccionAvanzado.valueProperty().bindBidirectional(model.probReproduccionAvanzadoProperty());
        sliderProbClonacionAvanzado.valueProperty().bindBidirectional(model.probClonacionAvanzadoProperty());
    }

    /**
     * Este método recibe los datos del modelo y los establece
     **/
    public void loadUserData(ParameterDataModelProperties parametrosData) {
        this.model = parametrosData;
        this.updateGUIwithModel();
    }
}