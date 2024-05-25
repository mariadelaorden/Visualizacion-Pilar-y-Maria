package es.uah.matcomp.proyecto.controlador;

import com.google.gson.GsonBuilder;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.excepciones.CeldaLlenaException;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.GenealogyNode;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.*;
import es.uah.matcomp.proyecto.modelo.tablero.Celda;
import es.uah.matcomp.proyecto.modelo.tablero.CeldaInfo;
import es.uah.matcomp.proyecto.modelo.tablero.CustomLabel;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class TableroController extends GridPane implements Initializable {
    private static final Logger logger = LogManager.getLogger(TableroController.class);

    @FXML
    private GridPane tableroGridPane;
    private boolean tableroCreado = false;
    private Stage scene;

    private Stage parametersScene;
    private ParameterDataModelProperties modeloParaGUICompartido;

    private Timeline timeline;

    private int generacionActual = 0;
    @FXML
    private Label turnoLabel;
    @FXML
    private Label individuoMasLongevoLabel;

    @FXML
    private Label totalReproduccionesLabel;
    @FXML
    private Label totalClonacionesLabel;
    @FXML
    private Label IdClonacionesLabel;
    @FXML
    private Label IdReproduccionesLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("Inicializando TableroController...");
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            // Aquí van las operaciones que se deben realizar cada 2 segundos
            buclePrincipal();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void setParametersScene(Stage parametersScene) {
        this.parametersScene = parametersScene;
    }

    public void setModeloParaGUICompartido() {
        this.modeloParaGUICompartido = ParameterDataModelProperties.getInstance(null, null, null, null);
    }

    public void crearTablero() {
        logger.info("Creando tablero...");
        try {
            for (int i = 0; i < this.modeloParaGUICompartido.getOriginalTablero().getLargo(); i++) {
                for (int j = 0; j < this.modeloParaGUICompartido.getOriginalTablero().getAncho(); j++) {
                    // Crear celdaLabel
                    Label celdaLabel = new CustomLabel(this.modeloParaGUICompartido.getOriginalTablero().getCelda(i, j), this.modeloParaGUICompartido);

                    // Abrir menu de info si se hace click izquierdo
                    celdaLabel.setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            CeldaInfo celdaInfo = new CeldaInfo();
                            CustomLabel celda = (CustomLabel) event.getSource();
                            celdaInfo.updateCelda(celda.getCelda());
                            Stage stage = new Stage();
                            stage.setTitle("Información de la celda");
                            stage.setScene(new Scene(celdaInfo, 700, 400));
                            stage.show();
                        }
                    });

                    // Añadir celdaLabel
                    tableroGridPane.add(celdaLabel, i, j);
                }
            }
            logger.info("Tablero creado exitosamente.");
        } catch (Exception e) {
            logger.error("Error al crear el tablero", e);
            throw new RuntimeException("Error al crear el tablero", e);
        }
    }

    public void updateBoard() {
        logger.info("Actualizando tablero...");
        for (Node node: this.tableroGridPane.getChildren()) {
            ((CustomLabel) node).updateLabel();
        }
    }

    public void setStage(Stage s){
        this.scene = s;
    }

    @FXML
    protected void onCerrarButtonClick(){
        logger.info("Cerrando aplicación...");
        this.scene.close();
    }

    public void onGuardarPartidaButtonClick(ActionEvent actionEvent) {
        logger.info("Guardando partida...");
        this.modeloParaGUICompartido.getOriginalTablero().imprimirTablero();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Partida");

        // Configurar filtro de extensión para archivos JSON
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos JSON (.json)", ".json");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el cuadro de diálogo para seleccionar la ubicación y el nombre del archivo
        File file = fileChooser.showSaveDialog(scene);

        if (file != null) {
            String rutaArchivo = file.getAbsolutePath();
            guardarPartida(this.modeloParaGUICompartido.getOriginalTablero(), rutaArchivo);
        }

    }

    public static void guardarPartida(Object objeto, String rutaArchivo) {
        logger.info("Guardando partida en el archivo: {}", rutaArchivo);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(objeto);
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(json);
        } catch (IOException e) {
            logger.error("Error al guardar la partida", e);
            throw new RuntimeException("Error al guardar la partida", e);
        }
    }

    public void onReiniciarButtonClick(ActionEvent actionEvent) {
        logger.info("Reiniciando tablero...");
        // Implementar la lógica de reinicio
    }

    public void onParametrosButtonClick(ActionEvent actionEvent) {
        logger.info("Abriendo ventana de parámetros...");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("parametrizar-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Parametros");
            stage.setScene(scene);
            ParametersController parametersController = fxmlLoader.getController();
            parametersController.loadUserData();
            parametersController.setStage(stage);
            parametersController.setOpenedFromMainWindow(false);
            parametersController.setPrevStage(this.scene);
            parametersController.disableTableroTab();
            parametersController.updateGUIwithModel();
            stage.show();
        } catch (Exception e) {
            logger.error("Error al abrir la ventana de parámetros", e);
            throw new RuntimeException("Error al abrir la ventana de parámetros", e);
        }
    }

    public void onPlayButtonClick(ActionEvent actionEvent) {
        logger.info("Iniciando simulación...");
        timeline.play();
    }

    public void onPauseButtonClick(ActionEvent actionEvent) {
        logger.info("Pausando simulación...");
        timeline.pause();
    }

    public void onStopButtonClick(ActionEvent actionEvent) {
        logger.info("Deteniendo simulación...");
        timeline.stop();
        mostrarArbolGenealogico();
    }
    private void actualizarVidaIndividuos() {
        logger.info("Actualizando vida de los individuos...");
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            if (individuos.getNumeroElementos() > 0) {
                logger.debug("Individuos en celda antes del delete: (tamaño: {})", individuos.getNumeroElementos());
                individuos.imprimir();
            }

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    logger.debug("El indice {} es: {}", i, el.getData());
                    Individuo ind = (Individuo) el.getData();
                    int nuevaVida = ind.getVida() - 1;
                    if (nuevaVida <= 0) {
                        individuos.del(i);
                        logger.debug("Individuo eliminado. Estado actual de la celda:");
                        individuos.imprimir();
                    } else {
                        ind.setVida(nuevaVida);
                    }
                }
            }
        }
    }

    private void actualizarRecursos() {
        logger.info("Actualizando duración de los recursos...");
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple recursos = celda.getRecursos();

            for (int i = 0; i < recursos.getMaximo(); i++) {
                ElementoLS el = recursos.getElemento(i);
                if (el != null) {
                    Recurso recurso = (Recurso) el.getData();
                    if (recurso.getDuracion() > 0) {
                        recurso.setDuracion(recurso.getDuracion() - 1);
                        if (recurso.getDuracion() <= 0) {
                            recursos.del(i);
                        }
                    } else {
                        recursos.del(i);
                        logger.debug("Recurso eliminado: {}", recurso);
                    }
                }
            }
        }
    }

    private void evaluarMejorasIndividuos() {
        logger.info("Evaluando mejoras de los individuos...");
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();
            ListaSimple recursos = celda.getRecursos();

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS elIndividuo = individuos.getElemento(i);
                if (elIndividuo != null) {
                    Individuo individuo = (Individuo) elIndividuo.getData();
                    for (int j = 0; j < recursos.getMaximo(); j++) {
                        ElementoLS elRecurso = recursos.getElemento(j);
                        if (elRecurso != null) {
                            Recurso recurso = (Recurso) elRecurso.getData();
                            recurso.aplicarEfecto(individuo);
                        }
                    }
                }
            }
        }
    }

    private void evaluarSobrePoblacion() {
        logger.info("Evaluando sobrepoblación...");
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            while (individuos.getNumeroElementos() > Celda.maxIndividuals) {
                individuos.del(individuos.getNumeroElementos() - 1);
                logger.debug("Individuo eliminado por sobrepoblación. Estado actual de la celda:");
                individuos.imprimir();
            }
        }
    }

    private void evaluarAparicionRecursos() {
        logger.info("Evaluando aparición de nuevos recursos...");
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            if (Math.random() < Recurso.getProbAparicion()) {
                Recurso nuevoRecurso = generarNuevoRecurso();
                if (celda.getRecursos().getNumeroElementos() < Celda.maxResources) {
                    try {
                        celda.addRecurso(nuevoRecurso);
                    } catch (IllegalStateException | CeldaLlenaException e) {
                        logger.warn("No se pudo agregar el recurso: " + e.getMessage());
                    }
                    logger.debug("Nuevo recurso añadido: {}", nuevoRecurso);
                }
            }
        }
    }

    private Recurso generarNuevoRecurso() {
        double probabilidadTotal = 0;
        double probabilidadAleatoria = Math.random();

        // Calcular probabilidades de aparición
        probabilidadTotal += Agua.getProbAparicion();
        probabilidadTotal += Comida.getProbAparicion();
        probabilidadTotal += Montana.getProbAparicion();
        probabilidadTotal += Pozo.getProbAparicion();
        probabilidadTotal += Biblioteca.getProbAparicion();
        probabilidadTotal += Tesoro.getProbAparicion();

        // Determinar probabilidad aleatoria
        double acumuladorProbabilidad = 0;
        if (probabilidadAleatoria <= Agua.getProbAparicion() / probabilidadTotal) {
            return new Agua();
        } else {
            acumuladorProbabilidad += Agua.getProbAparicion() / probabilidadTotal;
        }

        if (probabilidadAleatoria <= (Comida.getProbAparicion() + acumuladorProbabilidad)) {
            return new Comida();
        } else {
            acumuladorProbabilidad += Comida.getProbAparicion() / probabilidadTotal;
        }

        if (probabilidadAleatoria <= (Montana.getProbAparicion() + acumuladorProbabilidad)) {
            return new Montana();
        } else {
            acumuladorProbabilidad += Montana.getProbAparicion() / probabilidadTotal;
        }

        if (probabilidadAleatoria <= (Pozo.getProbAparicion() + acumuladorProbabilidad)) {
            return new Pozo();
        } else {
            acumuladorProbabilidad += Pozo.getProbAparicion() / probabilidadTotal;
        }

        if (probabilidadAleatoria <= (Biblioteca.getProbAparicion() + acumuladorProbabilidad)) {
            return new Biblioteca();
        } else {
            acumuladorProbabilidad += Biblioteca.getProbAparicion() / probabilidadTotal;
        }

        return new Tesoro();
    }

    private void evaluarClonacionYReproduccion() throws CeldaLlenaException {
        logger.info("Evaluando clonación y reproducción de los individuos...");
        Tablero tablero = this.modeloParaGUICompartido.getOriginalTablero();
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                for (int k = 0; k < celda.getIndividuos().getNumeroElementos(); k++) {
                    Individuo individuo = (Individuo) celda.getIndividuos().getElemento(k).getData();
                    Individuo nuevoIndividuo = individuo.evaluarClonacion();
                    if (nuevoIndividuo != null) {
                        celda.addIndividuo(nuevoIndividuo);
                        logger.debug("Nuevo individuo clonado añadido: {}", nuevoIndividuo);
                    }
                    nuevoIndividuo = individuo.evaluarReproduccion();
                    if (nuevoIndividuo != null) {
                        celda.addIndividuo(nuevoIndividuo);
                        logger.debug("Nuevo individuo reproducido añadido: {}", nuevoIndividuo);
                    }
                }
            }
        }
    }

    private void moverIndividuos() throws CeldaLlenaException {
        Tablero tablero = this.modeloParaGUICompartido.originalTablero;

        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                ListaSimple individuosEnCelda = celda.getIndividuos();

                for (int k = 0; k < individuosEnCelda.getNumeroElementos(); k++) {
                    Individuo individuo = (Individuo) individuosEnCelda.getElemento(k).getData();

                    switch (individuo.getTipo()) {
                        case BASICO:
                            moverIndividuoBasico(tablero, individuo, i, j);
                            break;
                        case NORMAL:
                            moverIndividuoNormal(tablero, individuo, i, j);
                            break;
                        case AVANZADO:
                            moverIndividuoAvanzado(tablero, individuo, i, j);
                            break;
                    }
                }
            }
        }
    }

    private void moverIndividuoBasico(Tablero tablero, Individuo individuo, int x, int y) throws CeldaLlenaException {
        Random rand = new Random();
        int nuevoX = x + rand.nextInt(3) - 1; // Movimiento aleatorio en el rango [-1, 1]
        int nuevoY = y + rand.nextInt(3) - 1;
        moverIndividuo(tablero, individuo, x, y, nuevoX, nuevoY);
    }

    private void moverIndividuoNormal(Tablero tablero, Individuo individuo, int x, int y) throws CeldaLlenaException {
        Celda objetivo = encontrarRecursoAleatorio(tablero);
        if (objetivo != null) {
            int[] coordsObjetivo = obtenerCoordenadasCelda(tablero, objetivo);
            if (coordsObjetivo != null) {
                int nuevoX = moverHacia(x, coordsObjetivo[0]);
                int nuevoY = moverHacia(y, coordsObjetivo[1]);
                moverIndividuo(tablero, individuo, x, y, nuevoX, nuevoY);
            }
        }
    }

    private void moverIndividuoAvanzado(Tablero tablero, Individuo individuo, int x, int y) throws CeldaLlenaException {
        Celda objetivo = encontrarRecursoMasCercano(tablero, x, y);
        if (objetivo != null) {
            int[] coordsObjetivo = obtenerCoordenadasCelda(tablero, objetivo);
            if (coordsObjetivo != null) {
                int nuevoX = moverHacia(x, coordsObjetivo[0]);
                int nuevoY = moverHacia(y, coordsObjetivo[1]);
                moverIndividuo(tablero, individuo, x, y, nuevoX, nuevoY);
            }
        }
    }

    private void moverIndividuo(Tablero tablero, Individuo individuo, int x, int y, int nuevoX, int nuevoY) throws CeldaLlenaException {
        if (esPosicionValida(tablero, nuevoX, nuevoY)) {
            Celda celdaActual = tablero.getCelda(x, y);
            Celda nuevaCelda = tablero.getCelda(nuevoX, nuevoY);

            // Crear un ElementoLS con el Individuo
            ElementoLS elementoIndividuo = new ElementoLS();
            elementoIndividuo.setData(individuo);

            int posicionIndividuo = celdaActual.getIndividuos().getPosicion(elementoIndividuo);
            if (posicionIndividuo != -1) { // Verificar si el individuo está en la lista
                celdaActual.getIndividuos().del(posicionIndividuo);
                nuevaCelda.addIndividuo(individuo);
            } else {
                // El individuo no está en la celda actual
                System.out.println("Error: El individuo no está en la celda actual");
            }
        }
    }

    private boolean esPosicionValida(Tablero tablero, int x, int y) {
        return x >= 0 && x < tablero.getAncho() && y >= 0 && y < tablero.getLargo();
    }

    private int moverHacia(int actual, int objetivo) {
        if (actual < objetivo) {
            return actual + 1;
        } else if (actual > objetivo) {
            return actual - 1;
        } else {
            return actual;
        }
    }

    private Celda encontrarRecursoAleatorio(Tablero tablero) {
        ListaSimple celdasConRecursos = new ListaSimple(60);

        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                if (!celda.getRecursos().isVacia()) {
                    celdasConRecursos.add(celda);
                }
            }
        }

        if (celdasConRecursos.isVacia()) {
            return null;
        }

        Random rand = new Random();
        return (Celda) celdasConRecursos.getElemento(rand.nextInt(celdasConRecursos.getNumeroElementos())).getData();
    }

    private Celda encontrarRecursoMasCercano(Tablero tablero, int x, int y) {
        Celda recursoMasCercano = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                if (!celda.getRecursos().isVacia() && (i != x || j != y)) {
                    double distancia = calcularDistancia(x, y, i, j);
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        recursoMasCercano = celda;
                    }
                }
            }
        }

        return recursoMasCercano;
    }

    private double calcularDistancia(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private int[] obtenerCoordenadasCelda(Tablero tablero, Celda celda) {
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                if (tablero.getCelda(i, j) == celda) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private int contarIndividuosEnTablero() {
        int totalIndividuos = 0;
        Tablero tablero = this.modeloParaGUICompartido.getOriginalTablero();
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                totalIndividuos += celda.getIndividuos().getNumeroElementos();
            }
        }
        return totalIndividuos;
    }

    private Individuo obtenerIndividuoGanador() {
        // Aquí debes implementar la lógica para obtener el individuo ganador del tablero
        // Este es un ejemplo simple que simplemente retorna el primer individuo que encuentra
        Tablero tablero = this.modeloParaGUICompartido.getOriginalTablero();
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                if (!celda.getIndividuos().isVacia()) {
                    return (Individuo) celda.getIndividuos().getElemento(0).getData();
                }
            }
        }
        return null;
    }

    private void mostrarArbolGenealogico() {
        logger.info("Mostrando arbol...");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("arbol-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            ArbolController arbolController = fxmlLoader.getController();
            Individuo ganador = obtenerIndividuoGanador();
            GenealogyNode arbolGenealogico = ganador != null ? ganador.getGenealogyNode() : null;
            //arbolController.dibujarArbol(arbolGenealogico);
            stage.setTitle("Árbol Genealógico");
            stage.setScene(scene);
            stage.show();
            logger.info("Nueva ventana creada.");
            this.scene.close();
        } catch (Exception e) {
            logger.error("Error al abrir la vista del arbol", e);
            throw new RuntimeException("Error al abrir la vista del arbol", e);
        }
    }

    public Individuo calcularIndividuoMasLongevo() {
        Individuo individuoMasLongevo = null;
        int maxVida = Integer.MIN_VALUE;

        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    Individuo ind = (Individuo) el.getData();
                    if (ind.getVida() > maxVida) {
                        maxVida = ind.getVida();
                        individuoMasLongevo = ind;
                    }
                }
            }
        }

        return individuoMasLongevo;
    }
    public int calcularTotalReproducciones() {
        int totalReproducciones = 0;

        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    Individuo ind = (Individuo) el.getData();
                    totalReproducciones += ind.getReproducciones();
                }
            }
        }

        return totalReproducciones;
    }

    public int calcularTotalMutaciones() {
        int totalMutaciones = 0;

        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    Individuo ind = (Individuo) el.getData();
                    totalMutaciones += ind.getMutaciones();
                }
            }
        }

        return totalMutaciones;
    }
    public int calcularIdMayorNumeroMutaciones() {
        int maxMutaciones = Integer.MIN_VALUE;
        int idMayorNumeroMutaciones = -1;

        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    Individuo ind = (Individuo) el.getData();
                    if (ind.getMutaciones() > maxMutaciones) {
                        maxMutaciones = ind.getMutaciones();
                        idMayorNumeroMutaciones = ind.getId();
                    }
                }
            }
        }

        return idMayorNumeroMutaciones;
    }

    public int calcularIdMayorNumeroReproducciones() {
        int maxReproducciones = Integer.MIN_VALUE;
        int idMayorNumeroReproducciones = -1;

        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    Individuo ind = (Individuo) el.getData();
                    if (ind.getReproducciones() > maxReproducciones) {
                        maxReproducciones = ind.getReproducciones();
                        idMayorNumeroReproducciones = ind.getId();
                    }
                }
            }
        }

        return idMayorNumeroReproducciones;
    }

    private void buclePrincipal() {
        logger.info("Ejecutando bucle principal...");

        try {
            //Incrementar turno y actualizar estadisticas
            generacionActual++;
            turnoLabel.setText("Turno: " + generacionActual);
            individuoMasLongevoLabel.setText("Id Individuo más longevo: "+ calcularIndividuoMasLongevo().getId());
            IdReproduccionesLabel.setText("Id individuo más reproducciones: "+ calcularIdMayorNumeroReproducciones());
            IdClonacionesLabel.setText("Id individduo más clonaciones: "+ calcularIdMayorNumeroMutaciones());

            // Añadir un individuo en la posición tempContadorX (un contador temporal que puedes incrementar en cada ciclo)
            int tempContadorX = generacionActual % modeloParaGUICompartido.getOriginalTablero().getAncho(); // Ejemplo para distribuir individuos a lo largo del tablero
            Individuo ind = new Individuo(this.modeloParaGUICompartido.getIndividuoBasico(), generacionActual, TipoIndividuo.BASICO);
            Celda celda = this.modeloParaGUICompartido.getOriginalTablero().getCelda(tempContadorX, 0);
            celda.addIndividuo(ind);

            logger.info("Generación actual: {}", generacionActual);
            logger.info("Generación actual: {}", generacionActual);

            // Actualizar la vida de los individuos y eliminar los que hayan muerto
            this.actualizarVidaIndividuos();

            // Actualizar la duración de los recursos y eliminar los que hayan caducado
            this.actualizarRecursos();

            // Movimiento individuos
            this.moverIndividuos();

            // Evaluar las mejoras de los individuos
            this.evaluarMejorasIndividuos();

            // Evaluar la reproducción y clonación de los individuos en cada posición
            this.evaluarClonacionYReproduccion();

            // Evaluar la eliminación de individuos en posiciones con sobrepoblación
            this.evaluarSobrePoblacion();

            // Evaluar la aparición de nuevos recursos en cada posición del tablero
            this.evaluarAparicionRecursos();

            //Evaluar si no quedan individuos para finalizar el juego
            if (contarIndividuosEnTablero() == 0) {
                timeline.stop();
                mostrarArbolGenealogico();
            }

            // Actualizar tablero en la interfaz gráfica
            this.updateBoard();
        } catch (CeldaLlenaException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrió un error durante la ejecución");
            alert.setContentText("Detalles del error: " + e.getMessage());
            logger.error("Error en el bucle principal: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
