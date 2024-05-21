
package es.uah.matcomp.proyecto.controlador;

import com.google.gson.GsonBuilder;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.*;
import es.uah.matcomp.proyecto.modelo.tablero.Celda;
import es.uah.matcomp.proyecto.modelo.tablero.CeldaInfo;
import es.uah.matcomp.proyecto.modelo.tablero.CustomLabel;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class TableroController extends GridPane implements Initializable {
    @FXML
    private GridPane tableroGridPane;
    private boolean tableroCreado = false;
    private Stage scene;

    private Stage parametersScene;
    private ParameterDataModelProperties modeloParaGUICompartido;

    private Timeline timeline;

    private int generacionActual = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        for (int i = 0; i < this.modeloParaGUICompartido.originalTablero.getLargo(); i++) {
            for (int j = 0; j < this.modeloParaGUICompartido.originalTablero.getAncho(); j++) {
                // Crear celdaLabel
                Label celdaLabel = new CustomLabel(this.modeloParaGUICompartido.originalTablero.getCelda(i, j), this.modeloParaGUICompartido);

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
//        this.modeloParaGUICompartido.originalTablero.resetearTablero();
    }

    public void updateBoard() {
        for (Node node: this.tableroGridPane.getChildren()) {
            ((CustomLabel) node).updateLabel();
        }
    }

    public void setStage(Stage s){
        this.scene = s;
    }

    @FXML
    protected void onCerrarButtonClick(){
        this.scene.close();
    }

    public void onGuardarPartidaButtonClick(ActionEvent actionEvent) {
        this.modeloParaGUICompartido.originalTablero.imprimirTablero();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Partida");

        // Configurar filtro de extensión para archivos JSON
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el cuadro de diálogo para seleccionar la ubicación y el nombre del archivo
        File file = fileChooser.showSaveDialog(scene);

        if (file != null) {
            String rutaArchivo = file.getAbsolutePath();
            guardarPartida(this.modeloParaGUICompartido.originalTablero,rutaArchivo);
        }

    }

    public static void guardarPartida(Object objeto, String rutaArchivo) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(objeto);
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onReiniciarButtonClick(ActionEvent actionEvent) {

    }

    public void onParametrosButtonClick(ActionEvent actionEvent) {
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
//            this.scene.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPlayButtonClick(ActionEvent actionEvent) {
        timeline.play();
    }

    public void onPauseButtonClick(ActionEvent actionEvent) {
        timeline.pause();
    }

    public void onStopButtonClick(ActionEvent actionEvent) {
        timeline.stop();
    }

    private void actualizarVidaIndividuos() {
        // Actualizar la vida de los individuos
        for (Node node: this.tableroGridPane.getChildren()) {
            ListaSimple individuos = ((CustomLabel) node).getCelda().getIndividuos();

            if (individuos.getNumeroElementos() > 0) {
                System.out.println("Individuos en celda antes del delete: (tamaño: " + individuos.getNumeroElementos());
                individuos.imprimir();
            }

            for (int i = 0; i < individuos.getMaximo(); i++) {
                ElementoLS el = individuos.getElemento(i);
                if (el != null) {
                    System.out.println("El indice " + i + " es: " + el.getData());
                    Individuo ind = (Individuo) individuos.getElemento(i).getData();
                    ind.setVida(ind.getVida() - 1);
                    if (ind.getVida() <= 0) {
                        individuos.del(i);

                        System.out.println("Despues del delete: ");
                        individuos.imprimir();
                    }
                }

            }
        }
    }

    private void actualizarRecursos() {
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple recursos = celda.getRecursos();

            for (int i = 0; i < recursos.getMaximo(); i++) {
                ElementoLS el = recursos.getElemento(i);
                if (el != null) {
                    Recurso recurso = (Recurso) el.getData();
                    recurso.setDuracion(recurso.getDuracion() - 1);
                    if (recurso.getDuracion() <= 0) {
                        recursos.del(i);
                    }
                }
            }
        }
    }

    private void evaluarMejorasIndividuos() {
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
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            ListaSimple individuos = celda.getIndividuos();

            while (individuos.getNumeroElementos() > Celda.maxIndividuals) {
                individuos.del(individuos.getNumeroElementos() - 1);
            }
        }
    }

    private void evaluarAparicionRecursos() {
        for (Node node : this.tableroGridPane.getChildren()) {
            Celda celda = ((CustomLabel) node).getCelda();
            if (Math.random() < Recurso.getProbAparicion()) {
                Recurso nuevoRecurso = generarNuevoRecurso();
                if (celda.getRecursos().getNumeroElementos() < Celda.maxResources) {
                    celda.addRecurso(nuevoRecurso);
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

    private void evaluarClonacionYReproduccion() {
        Tablero tablero = this.modeloParaGUICompartido.originalTablero;
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                for (int k = 0; k < celda.getIndividuos().getNumeroElementos(); k++) {
                    Individuo individuo = (Individuo) celda.getIndividuos().getElemento(k).getData();
                    Individuo nuevoIndividuo = individuo.evaluarClonacion();
                    if (nuevoIndividuo != null) {
                        celda.addIndividuo(nuevoIndividuo);
                    }
                    nuevoIndividuo = individuo.evaluarReproduccion();
                    if (nuevoIndividuo != null) {
                        celda.addIndividuo(nuevoIndividuo);
                    }
                }
            }
        }
    }

    /**

    private void moverIndividuos() {
        Tablero tablero = this.modeloParaGUICompartido.originalTablero;

        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                Celda celda = tablero.getCelda(i, j);
                ListaSimple individuosEnCelda = celda.getIndividuos();

                for (int k = 0; k < individuosEnCelda.getNumeroElementos(); k++) {
                    Individuo individuo = (Individuo) individuosEnCelda.getElemento(k).getData();

                    switch (individuo.getTipo()) {
                        case BASICO:
                            moverIndividuoBasico(individuo, i, j);
                            break;
                        case NORMAL:
                            moverIndividuoNormal(individuo, i, j);
                            break;
                        case AVANZADO:
                            moverIndividuoAvanzado(individuo, i, j);
                            break;
                    }
                }
            }
        }
    }

    private void moverIndividuoBasico(Individuo individuo, int x, int y) {
        Random rand = new Random();
        int nuevoX = x + rand.nextInt(3) - 1;
        int nuevoY = y + rand.nextInt(3) - 1;
        moverIndividuo(individuo, x, y, nuevoX, nuevoY);
    }

    private void moverIndividuoNormal(Individuo individuo, int x, int y) {
        Celda objetivo = encontrarRecursoAleatorio();
        if (objetivo != null) {
            int nuevoX = moverHacia(x, y, objetivo);
            int nuevoY = moverHacia(y, x, objetivo);
            moverIndividuo(individuo, x, y, nuevoX, nuevoY);
        }
    }

    private void moverIndividuoAvanzado(Individuo individuo, int x, int y) {
        Celda objetivo = encontrarRecursoMasCercano(x, y);
        if (objetivo != null) {
            int nuevoX = moverHacia(x, y, objetivo);
            int nuevoY = moverHacia(y, x, objetivo);
            moverIndividuo(individuo, x, y, nuevoX, nuevoY);
        }
    }

    private void moverIndividuo(Individuo individuo, int x, int y, int nuevoX, int nuevoY) {
        Tablero tablero = this.modeloParaGUICompartido.originalTablero;
        if (esPosicionValida(tablero, nuevoX, nuevoY)) {
            Celda celdaActual = tablero.getCelda(x, y);
            Celda nuevaCelda = tablero.getCelda(nuevoX, nuevoY);
            int posicionIndividuo = celdaActual.getIndividuos().getPosicion(individuo);
            if (posicionIndividuo != -1) { // Verificar si el individuo está en la lista
                celdaActual.delIndividuo(posicionIndividuo);
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

    private int moverHacia(int actual, int otro, Celda objetivo) {
        Tablero tablero = this.modeloParaGUICompartido.originalTablero;
        int nuevo = actual;
        ListaSimple posiblesCeldas = obtenerCeldasAdyacentes(tablero, otro, objetivo);

        for (int i = 0; i < posiblesCeldas.getNumeroElementos(); i++) {
            Celda celda = (Celda) posiblesCeldas.getElemento(i).getData();
            if (celda == objetivo) {
                nuevo = obtenerNuevaCoordenada(actual, otro, celda);
                break;
            }
        }

        return nuevo;
    }

    private ListaSimple obtenerCeldasAdyacentes(Tablero tablero, int otraCoord, Celda objetivo) {
        ListaSimple celdasAdyacentes = new ListaSimple(8);
        int x = objetivo.getIndividuos().get(0).getPosX();  // ejemplo de obtener coordenada x
        int y = objetivo.getIndividuos().get(0).getPosY();  // ejemplo de obtener coordenada y

        // celdas adyacentes
        if (esPosicionValida(tablero,x - 1, otraCoord)) celdasAdyacentes.add(tablero.getCelda(x - 1, otraCoord));
        if (esPosicionValida(tablero,x + 1, otraCoord)) celdasAdyacentes.add(tablero.getCelda(x + 1, otraCoord));
        if (esPosicionValida(tablero,x, y - 1)) celdasAdyacentes.add(tablero.getCelda(x, y - 1));
        if (esPosicionValida(tablero,x, y + 1)) celdasAdyacentes.add(tablero.getCelda(x, y + 1));

        return celdasAdyacentes;
    }

    private int obtenerNuevaCoordenada(int actual, int otro, Celda objetivo) {
        int nuevaCoord = actual;

        if (actual < objetivo.getIndividuos().get(0).getPosX()) {
            nuevaCoord++;
        } else if (actual > objetivo.getIndividuos().get(0).getPosX()) {
            nuevaCoord--;
        }

        return nuevaCoord;
    }

    private Celda encontrarRecursoAleatorio() {
        Tablero tablero = this.modeloParaGUICompartido.originalTablero;
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
        return celdasConRecursos.get(rand.nextInt(celdasConRecursos.getNumeroElementos()));
    }

    private Celda encontrarRecursoMasCercano(int x, int y) {
        Tablero tablero = this.modeloParaGUICompartido.originalTablero;
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
     **/


    private void buclePrincipal() {
        // Implementar las operaciones que se deben realizar cada 2 segundos

        //  Se ejecutará el movimiento de cada individuo (siempre obligatorio).

        // Para cada posición del tablero, se evaluará si deben aparecer nuevos recursos.
        // IMPORTANTE, FALTA NORMALIZAR LA PROBABILIDAD DE APARICIÓN DE LOS RECURSOS. (las V del enunciado)

        // Añadir un individuo en la posición tempContadorX (un contador temporal que puedes incrementar en cada ciclo)
        int tempContadorX = generacionActual % modeloParaGUICompartido.getOriginalTablero().getAncho(); // Ejemplo para distribuir individuos a lo largo del tablero
        Individuo ind = new Individuo(this.modeloParaGUICompartido.getIndividuoBasico(), generacionActual, TipoIndividuo.BASICO);
        Celda celda = this.modeloParaGUICompartido.getOriginalTablero().getCelda(tempContadorX, 0);
        celda.addIndividuo(ind);
        generacionActual++;
        System.out.println("Generación actual: " + generacionActual);

        // Actualizar la vida de los individuos y eliminar los que hayan muerto
        this.actualizarVidaIndividuos();

        // Actualizar la duración de los recursos y eliminar los que hayan caducado
        this.actualizarRecursos();

        // Movimiento individuos
        //this.moverIndividuos();

        // Evaluar las mejoras de los individuos
        this.evaluarMejorasIndividuos();

        // Evaluar la reproducción y clonacion de los individuos en cada posición
        this.evaluarClonacionYReproduccion();

        // Evaluar la eliminación de individuos en posiciones con sobrepoblación
        this.evaluarSobrePoblacion();

        // Evaluar la aparición de nuevos recursos en cada posición del tablero
        this.evaluarAparicionRecursos();

        // Actualizar  tablero en la interfaz gráfica
        this.updateBoard();
    }
}