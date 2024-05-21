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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametersController implements Initializable {
    private static final Logger logger = LogManager.getLogger(ParametersController.class);

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

    // Otros componentes
    @FXML
    private Slider SliderAncho;
    @FXML
    private Slider SliderLargo;
    public Tab tableroTab;

    private boolean openedFromMainWindow;
    private Stage prevStage;
    private Scene tableroScene;

    private ParameterDataModelProperties model;
    private Stage scene;

    @FXML
    protected void onGuardarButtonClick() {
        try {
            model.commit();
            logger.info("Parámetros guardados y aplicados.");

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
                    logger.info("Nuevo tablero creado y ventana principal cerrada.");
                } catch (Exception e) {
                    logger.error("Error al abrir la vista del tablero", e);
                    throw new RuntimeException("Error al abrir la vista del tablero", e);
                }
            }
        } catch (Exception e) {
            logger.error("Error al guardar los parámetros", e);
            throw new RuntimeException("Error al guardar los parámetros", e);
        }
    }

    public void disableTableroTab() {
        tableroTab.setDisable(true);
    }

    @FXML
    protected void onReiniciarButtonClick() {
        try {
            this.model.rollback();
            logger.info("Parámetros reiniciados a los valores originales.");
        } catch (Exception e) {
            logger.error("Error al reiniciar los parámetros", e);
            throw new RuntimeException("Error al reiniciar los parámetros", e);
        }
    }

    public void setStage(Stage s) {
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

    @FXML
    protected void onCerrarButtonClick() {
        try {
            this.model.rollback();
            this.scene.close();
            this.prevStage.show();
            logger.info("Ventana de parámetros cerrada y ventana principal mostrada.");
        } catch (Exception e) {
            logger.error("Error al cerrar la ventana de parámetros", e);
            throw new RuntimeException("Error al cerrar la ventana de parámetros", e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Inicializando ParametersController...");
        if (this.model != null) {
            this.updateGUIwithModel();
        }
    }

    protected void updateGUIwithModel() {
        try {
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

            logger.info("Modelo actualizado con la GUI.");
        } catch (Exception e) {
            logger.error("Error al actualizar la GUI con el modelo", e);
            throw new RuntimeException("Error al actualizar la GUI con el modelo", e);
        }
    }

    public void loadUserData() {
        try {
            this.model = ParameterDataModelProperties.getInstance(null, null, null, null);
            this.updateGUIwithModel();
            logger.info("Datos de usuario cargados en el modelo.");
        } catch (Exception e) {
            logger.error("Error al cargar los datos de usuario", e);
            throw new RuntimeException("Error al cargar los datos de usuario", e);
        }
    }
}
