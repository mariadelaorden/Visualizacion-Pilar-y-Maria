package es.uah.matcomp.proyecto.controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Application {
    private static final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) {
        logger.info("Iniciando aplicación...");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("juego-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1300, 650);
            stage.setTitle("JUEGO DE LA VIDA");
            stage.setScene(scene);
            stage.show();
            logger.info("Interfaz de juego cargada y mostrada.");
        } catch (IOException e) {
            logger.error("Error al cargar la vista del juego", e);
            throw new RuntimeException("Error al cargar la vista del juego", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Lanzando aplicación...");
        launch();
    }
}
