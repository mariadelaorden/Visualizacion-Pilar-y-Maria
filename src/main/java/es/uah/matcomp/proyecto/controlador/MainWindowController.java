
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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private Tablero tableroDataModel =
            new Tablero(5, 5);

    private PlantillaIndividuo individuoBasicoDataModel =
            new PlantillaIndividuo();

    private PlantillaIndividuo individuoNormalDataModel =
            new PlantillaIndividuo();

    private PlantillaIndividuo individuoAvanzadoDataModel =
            new PlantillaIndividuo();

    private ParameterDataModelProperties modeloParaGUICompartido;

    private Stage scene;

    @FXML
    protected void onIniciarPartidaButtonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("parametrizar-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Parametros");
            stage.setScene(scene);
            modeloParaGUICompartido =
                    ParameterDataModelProperties.getInstance(
                            tableroDataModel,
                            individuoBasicoDataModel,
                            individuoNormalDataModel,
                            individuoAvanzadoDataModel);
            ParametersController parametersController = fxmlLoader.getController();
            parametersController.loadUserData();
            parametersController.setStage(stage);
            parametersController.setOpenedFromMainWindow(true);
            parametersController.setPrevStage(this.scene);
            stage.show();
            this.scene.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCargarPartidaButtonClick() {  //No actualiza los datos
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> T cargarPartida(String rutaArchivo, Class<T> clase) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, clase);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setStage(Stage s) {
        this.scene = s;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador\n");
    }
}