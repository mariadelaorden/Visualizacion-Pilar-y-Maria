package es.uah.matcomp.proyecto.controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//ABRIR JUEGO
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("juego-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("JUEGO DE LA VIDA");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}