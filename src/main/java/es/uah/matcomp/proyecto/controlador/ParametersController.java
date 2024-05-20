
package es.uah.matcomp.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import java.net.URL;
import java.util.ResourceBundle;

public class ParametersController implements Initializable {
    // Individuos
    public Slider sliderVidasBasico;
    public Slider sliderProbReproduccionBasico;
    public Slider sliderProbClonacionBasico;
    public Slider sliderVidasNormal;
    public Slider sliderProbReproduccionNormal;
    public Slider sliderProbClonacionNormal;
    public Slider sliderVidasAvanzado;
    public Slider sliderProbReproduccionAvanzado;
    public Slider sliderProbClonacionAvanzado;

    // Recursos
    public Slider sliderProbAparicionRecurso;
    public Slider sliderProbTesoro;
    public Slider sliderProbAgua;
    public Slider sliderProbComida;
    public Slider sliderProbMontaña;
    public Slider sliderProbBiblioteca;
    public Slider sliderProbPozo;
    /**
     * Hooks de conexión entre los controles visuales y el código, llevan @FXML para identificarlos
     **/

    private boolean openedFromMainWindow;
    private Stage prevStage;

    private Scene tableroScene;

    @FXML
    private Slider SliderAncho;
    @FXML
    private Slider SliderLargo;

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

        if (!openedFromMainWindow) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Cambios aplicados con éxito.");
            alert.showAndWait();
        } else {

            model.originalTablero.updateTableroSize();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tablero-view.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Tablero");
                stage.setScene(scene);
                TableroController tableroController = fxmlLoader.getController();
                tableroController.setModeloParaGUICompartido();
                tableroController.setParametersScene(this.scene);
                tableroController.setStage(stage);
                tableroController.crearTablero();
                stage.sizeToScene();
                stage.show();
                this.scene.close();
                this.openedFromMainWindow = false;
                disableTableroTab();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        sliderProbAparicionRecurso.valueProperty().bindBidirectional(model.probAparicionRecursoProperty());
        sliderProbTesoro.valueProperty().bindBidirectional(model.probTesoroProperty());
        sliderProbAgua.valueProperty().bindBidirectional(model.probAguaProperty());
        sliderProbComida.valueProperty().bindBidirectional(model.probComidaProperty());
        sliderProbMontaña.valueProperty().bindBidirectional(model.probMontañaProperty());
        sliderProbBiblioteca.valueProperty().bindBidirectional(model.probBibliotecaProperty());
        sliderProbPozo.valueProperty().bindBidirectional(model.probPozoProperty());
    }

    /**
     * Este método recibe los datos del modelo y los establece
     **/
    public void loadUserData() {
        this.model = ParameterDataModelProperties.getInstance(null, null, null, null);
        this.updateGUIwithModel();
    }
}