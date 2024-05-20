
package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.*;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.beans.property.*;

public class ParameterDataModelProperties {

    private static ParameterDataModelProperties instance;

    //Modelo de datos original
    protected Tablero originalTablero;
    protected PlantillaIndividuo individuoBasico;
    protected PlantillaIndividuo individuoNormal;
    protected PlantillaIndividuo individuoAvanzado;


    private IntegerProperty ancho = new SimpleIntegerProperty();
    private IntegerProperty largo = new SimpleIntegerProperty();


    private IntegerProperty vidasBasico = new SimpleIntegerProperty();
    private DoubleProperty probReproduccionBasico = new SimpleDoubleProperty();
    private DoubleProperty probClonacionBasico = new SimpleDoubleProperty();
    private IntegerProperty vidasNormal = new SimpleIntegerProperty();
    private DoubleProperty probReproduccionNormal = new SimpleDoubleProperty();
    private DoubleProperty probClonacionNormal = new SimpleDoubleProperty();
    private IntegerProperty vidasAvanzado = new SimpleIntegerProperty();
    private DoubleProperty probReproduccionAvanzado = new SimpleDoubleProperty();
    private DoubleProperty probClonacionAvanzado = new SimpleDoubleProperty();

    private DoubleProperty probAparicionRecurso = new SimpleDoubleProperty();
    private DoubleProperty probTesoro = new SimpleDoubleProperty();
    private DoubleProperty probAgua = new SimpleDoubleProperty();
    private DoubleProperty probComida = new SimpleDoubleProperty();
    private DoubleProperty probMontaña = new SimpleDoubleProperty();
    private DoubleProperty probBiblioteca = new SimpleDoubleProperty();
    private DoubleProperty probPozo = new SimpleDoubleProperty();

    private ParameterDataModelProperties(Tablero originalTablero, PlantillaIndividuo individuoBasico,
                                         PlantillaIndividuo individuoNormal, PlantillaIndividuo individuoAvanzado) {
        setOriginal(originalTablero, individuoBasico, individuoNormal, individuoAvanzado);
    }

    public static ParameterDataModelProperties getInstance(Tablero originalTablero, PlantillaIndividuo individuoBasico,
                                                           PlantillaIndividuo individuoNormal, PlantillaIndividuo individuoAvanzado) {
        if (instance == null) {
            instance = new ParameterDataModelProperties(originalTablero, individuoBasico, individuoNormal, individuoAvanzado);
        }
        return instance;
    }

    public void commit() {
        originalTablero.setAncho(ancho.get());
        originalTablero.setLargo(largo.get());
        individuoBasico.setVida(vidasBasico.get());
        individuoBasico.setProbReproduccion(probReproduccionBasico.get());
        individuoBasico.setProbClonacion(probClonacionBasico.get());
        individuoNormal.setVida(vidasNormal.get());
        individuoNormal.setProbReproduccion(probReproduccionNormal.get());
        individuoNormal.setProbClonacion(probClonacionNormal.get());
        individuoAvanzado.setVida(vidasAvanzado.get());
        individuoAvanzado.setProbReproduccion(probReproduccionAvanzado.get());
        individuoAvanzado.setProbClonacion(probClonacionAvanzado.get());

        Recurso.setProbAparicion(probAparicionRecurso.get());
        Tesoro.setProbAparicion(probTesoro.get());
        Agua.setProbAparicion(probAgua.get());
        Comida.setProbAparicion(probComida.get());
        Biblioteca.setProbAparicion(probBiblioteca.get());
        Montana.setProbAparicion(probMontaña.get());
        Pozo.setProbAparicion(probPozo.get());
    }

    public void rollback() {
        ancho.set(originalTablero.getAncho());
        largo.set(originalTablero.getLargo());
        vidasBasico.set(individuoBasico.getVida());
        probReproduccionBasico.set(individuoBasico.getProbReproduccion());
        probClonacionBasico.set(individuoBasico.getProbClonacion());
        vidasNormal.set(individuoNormal.getVida());
        probReproduccionNormal.set(individuoNormal.getProbReproduccion());
        probClonacionNormal.set(individuoNormal.getProbClonacion());
        vidasAvanzado.set(individuoAvanzado.getVida());
        probReproduccionAvanzado.set(individuoAvanzado.getProbReproduccion());
        probClonacionAvanzado.set(individuoAvanzado.getProbClonacion());

        probAparicionRecurso.set(Recurso.getProbAparicion());
        probTesoro.set(Tesoro.getProbAparicion());
        probAgua.set(Recurso.getProbAparicion());
        probComida.set(Recurso.getProbAparicion());
        probMontaña.set(Recurso.getProbAparicion());
        probBiblioteca.set(Recurso.getProbAparicion());
        probPozo.set(Recurso.getProbAparicion());
    }

    public Tablero getOriginalTablero() {
        return originalTablero;
    }

    public PlantillaIndividuo getIndividuoBasico() {
        return individuoBasico;
    }

    public PlantillaIndividuo getIndividuoNormal() {
        return individuoNormal;
    }

    public PlantillaIndividuo getIndividuoAvanzado() {
        return individuoAvanzado;
    }

    public void setOriginal(Tablero originalTablero, PlantillaIndividuo individuoBasico,
                            PlantillaIndividuo individuoNormal, PlantillaIndividuo individuoAvanzado) {
        this.originalTablero = originalTablero;
        this.individuoBasico = individuoBasico;
        this.individuoNormal = individuoNormal;
        this.individuoAvanzado = individuoAvanzado;


        rollback(); //Se inicializan los properties.
    }

    public Property<Number> anchoProperty() {
        return ancho;
    }

    public Property<Number> largoProperty() {
        return largo;
    }

    public Property<Number> vidasBasicoProperty() {
        return vidasBasico;
    }

    public Property<Number> probReproduccionBasicoProperty() {
        return probReproduccionBasico;
    }

    public Property<Number> probClonacionBasicoProperty() {
        return probClonacionBasico;
    }

    public Property<Number> vidasNormalProperty() {
        return vidasNormal;
    }

    public Property<Number> probReproduccionNormalProperty() {
        return probReproduccionNormal;
    }

    public Property<Number> probClonacionNormalProperty() {
        return probClonacionNormal;
    }

    public Property<Number> vidasAvanzadoProperty() {
        return vidasAvanzado;
    }

    public Property<Number> probReproduccionAvanzadoProperty() {
        return probReproduccionAvanzado;
    }

    public Property<Number> probClonacionAvanzadoProperty() {
        return probClonacionAvanzado;
    }

    public Property<Number> probAparicionRecursoProperty() {
        return probAparicionRecurso;
    }

    public Property<Number> probTesoroProperty() {
        return probTesoro;
    }

    public Property<Number> probAguaProperty() {
        return probAgua;
    }

    public Property<Number> probComidaProperty() {
        return probComida;
    }

    public Property<Number> probMontañaProperty() {
        return probMontaña;
    }

    public Property<Number> probBibliotecaProperty() {
        return probBiblioteca;
    }

    public Property<Number> probPozoProperty() {
        return probPozo;
    }
}