package es.uah.matcomp.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

// PARAMETRIZAR INDIVIDUO Y RECURSOS-> parametrizarIndividuoRecurso-view
public class IndividuoRecursosControl implements Initializable {
    @FXML
    private Slider SliderAncho;
    @FXML
    private Slider SliderLargo;

    @FXML
    protected void onReiniciarIndRecButtonClick(){
        //Restablecer los valores del tablero
    }

    @FXML
    protected void onGuardarButtonClick() {
        int ancho= (int) SliderAncho.getValue();
        int largo = (int) SliderLargo.getValue();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tablero-view.fxml"));
            Parent root = fxmlLoader.load();
            VistaTableroController tableroController = fxmlLoader.getController();
            tableroController.crearTablero(ancho, largo);

            Stage stage = new Stage();
            stage.setTitle("Tablero");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador\n");

    }
}