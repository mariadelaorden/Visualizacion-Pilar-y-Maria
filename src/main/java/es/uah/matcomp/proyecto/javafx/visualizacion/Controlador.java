package es.uah.matcomp.proyecto.javafx.visualizacion;

import es.uah.matcomp.proyecto.cod.Tablero;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlador implements Initializable {
    @FXML
    private Slider SliderAncho;
    @FXML
    private Slider SliderLargo;
    @FXML
    protected void onIniciarPartidaButtonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("parametrizar-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Parametros");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    protected void onCargarPartidaButtonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tablero-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Tablero");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    protected void onGuardarButtonClick() {
            int ancho = (int) SliderAncho.getValue();
            int largo = (int) SliderLargo.getValue();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tablero-view.fxml"));
            Parent root = fxmlLoader.load();
            TableroController tableroController = fxmlLoader.getController();
            tableroController.crearTablero(ancho, largo);

            Stage stage = new Stage();
            stage.setTitle("Tablero");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador\n");
    }
}