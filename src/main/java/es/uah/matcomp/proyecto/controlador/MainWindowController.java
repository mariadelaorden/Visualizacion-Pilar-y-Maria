package es.uah.matcomp.proyecto.controlador;

import com.google.gson.Gson;
import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.tablero.CeldaInfo;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger(MainWindowController.class);

    private Tablero tableroDataModel = new Tablero(5, 5);
    private PlantillaIndividuo individuoBasicoDataModel = new PlantillaIndividuo();
    private PlantillaIndividuo individuoNormalDataModel = new PlantillaIndividuo();
    private PlantillaIndividuo individuoAvanzadoDataModel = new PlantillaIndividuo();

    private ParameterDataModelProperties modeloParaGUICompartido;
    private Stage scene;

    @FXML
    protected void onIniciarPartidaButtonClick() {
        logger.info("Iniciando nueva partida...");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("parametrizar-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Parametros");
            stage.setScene(scene);
            modeloParaGUICompartido = ParameterDataModelProperties.getInstance(
                    tableroDataModel, individuoBasicoDataModel, individuoNormalDataModel, individuoAvanzadoDataModel);
            ParametersController parametersController = fxmlLoader.getController();
            parametersController.loadUserData();
            parametersController.setStage(stage);
            parametersController.setOpenedFromMainWindow(true);
            parametersController.setPrevStage(this.scene);
            stage.show();
            if (this.scene != null) {
                this.scene.close();
            }
            logger.info("Ventana de parámetros mostrada.");
        } catch (Exception e) {
            logger.error("Error al iniciar la partida", e);
            throw new RuntimeException("Error al iniciar la partida", e);
        }
    }

    @FXML
    protected void onCargarPartidaButtonClick() {
        logger.info("Cargando partida desde archivo...");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de partida");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File archivoSeleccionado = fileChooser.showOpenDialog(new Stage());

        if (archivoSeleccionado != null) {
            CeldaInfo partida = cargarPartida(archivoSeleccionado.getAbsolutePath(), CeldaInfo.class);
            if (partida != null) {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tablero-view.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    stage.setTitle("Tablero");
                    stage.setScene(scene);
                    stage.show();
                    logger.info("Partida cargada y ventana de tablero mostrada.");
                } catch (Exception e) {
                    logger.error("Error al cargar la vista del tablero", e);
                    throw new RuntimeException("Error al cargar la vista del tablero", e);
                }
            } else {
                logger.warn("La partida cargada es nula.");
            }
        } else {
            logger.warn("No se seleccionó ningún archivo para cargar la partida.");
        }
    }

    public static <T> T cargarPartida(String rutaArchivo, Class<T> clase) {
        logger.info("Cargando partida desde el archivo: {}", rutaArchivo);
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, clase);
        } catch (IOException e) {
            logger.error("Error al cargar la partida desde el archivo: {}", rutaArchivo, e);
            return null;
        }
    }

    public void setStage(Stage s) {
        this.scene = s;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Inicializando MainWindowController...");
    }

    @FXML
    protected void onGuardarPartidaButtonClick() {
        logger.info("Guardando partida en archivo...");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar partida");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File archivoSeleccionado = fileChooser.showSaveDialog(new Stage());

        if (archivoSeleccionado != null) {
            boolean exito = guardarPartida(archivoSeleccionado.getAbsolutePath(), tableroDataModel);
            if (exito) {
                logger.info("Partida guardada exitosamente en el archivo: " + archivoSeleccionado.getAbsolutePath());
            } else {
                logger.error("Error al guardar la partida en el archivo: " + archivoSeleccionado.getAbsolutePath());
            }
        } else {
            logger.warn("No se seleccionó ningún archivo para guardar la partida.");
        }
    }

    public static <T> boolean guardarPartida(String rutaArchivo, T partida) {
        logger.info("Guardando partida en el archivo: {}", rutaArchivo);
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(rutaArchivo)) {
            gson.toJson(partida, writer);
            return true;
        } catch (IOException e) {
            logger.error("Error al guardar la partida en el archivo: {}", rutaArchivo, e);
            return false;
        }
    }

    private PartidaDTO toDTO() {
        PartidaDTO dto = new PartidaDTO();
        dto.setTablero(tableroDataModel);
        dto.setIndividuoBasico(individuoBasicoDataModel);
        dto.setIndividuoNormal(individuoNormalDataModel);
        dto.setIndividuoAvanzado(individuoAvanzadoDataModel);
        return dto;
    }

    private void fromDTO(PartidaDTO dto) {
        tableroDataModel = dto.getTablero();
        individuoBasicoDataModel = dto.getIndividuoBasico();
        individuoNormalDataModel = dto.getIndividuoNormal();
        individuoAvanzadoDataModel = dto.getIndividuoAvanzado();
    }


}
