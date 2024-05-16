package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.modelo.ParameterDataModelProperties;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private Tablero tableroDataModel =
            new Tablero(5, 5);

    private Individuo individuoDataModel =
            new Individuo(0, 0, 0, 0, 0, TipoIndividuo.BASICO);

    private ParameterDataModelProperties modeloParaGUICompartido =
            new ParameterDataModelProperties(
                    tableroDataModel,
                    individuoDataModel
            );

    @FXML
    protected void onIniciarPartidaButtonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("parametrizar-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Parametros");
            stage.setScene(scene);
            ParametersController parametersController = fxmlLoader.getController();
            parametersController.loadUserData(this.modeloParaGUICompartido);
            parametersController.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCargarPartidaButtonClick() {
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

//    @FXML
//    protected void onGuardarButtonClick() {
//        int ancho = (int) SliderAncho.getValue();
//        int largo = (int) SliderLargo.getValue();
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tablero-view.fxml"));
//            Parent root = fxmlLoader.load();
//            TableroController tableroController = fxmlLoader.getController();
//            tableroController.crearTablero(ancho, largo);
//
//            Stage stage = new Stage();
//            stage.setTitle("Tablero");
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador\n");
    }
}