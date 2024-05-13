package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.tablero.Celda;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// PARAMETRIZAR TABLERO -> parametrizarTablero-view
public class TableroController implements Initializable {

    @FXML
    private GridPane tableroGridPane;
    private Tablero tablero;

    private int anchoTablero;
    private int largoTablero;

    @FXML
    protected void onSiguienteButtonClick(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("parametrizarIndividuoRecurso-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Parametros Individuo y Recursos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void crearTablero(int anchoTablero, int largoTablero) {
        for (int i = 0; i < anchoTablero; i++) {
            for (int j = 0; j < largoTablero; j++) {
                Label celdaLabel = new CustomLabel(i, j, "");
                celdaLabel.setMinSize(30, 30);
                celdaLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-text-alignment: center;");
                celdaLabel.setOnMouseClicked(event -> addIndividuo((CustomLabel) event.getSource()));
                tableroGridPane.add(celdaLabel, i, j);
            }
        }
        tablero.resetearTablero();
    }


    private void addIndividuo(CustomLabel celdaLabel) {
        System.out.println("Clicked position: " + celdaLabel.getI() + " " + celdaLabel.getJ());
        Celda celda = tablero.getCelda(celdaLabel.getI(), celdaLabel.getJ());
        celda.addIndividuo(new Individuo(1, 1, 1, 1, TipoIndividuo.AVANZADO));
        celdaLabel.setStyle("-fx-background-color: black;");
    }

    public void onCargarPartidaButtonClick(ActionEvent actionEvent) {
        this.tablero.imprimirTablero();
    }
    @FXML
    protected void onReiniciarTableroButtonClick(){
        //Restablecer los valores del tablero
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.anchoTablero = 10; // Configura el tamaño según necesites
        this.largoTablero = 10;

        this.tablero = new Tablero(anchoTablero, largoTablero);
        crearTablero(anchoTablero, largoTablero); // Pasar el ancho y largo del tablero como argumentos
    }

}