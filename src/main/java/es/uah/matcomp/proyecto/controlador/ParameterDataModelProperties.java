
package es.uah.matcomp.proyecto.controlador;

import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import javafx.beans.property.*;

public class ParameterDataModelProperties {
    //Modelo de datos original
    protected Tablero originalTablero;
    protected Individuo originalIndividuo;

    private IntegerProperty ancho = new SimpleIntegerProperty();
    private IntegerProperty largo = new SimpleIntegerProperty();
    private IntegerProperty vidas = new SimpleIntegerProperty();
    private DoubleProperty probReproduccion = new SimpleDoubleProperty();
    private DoubleProperty probClonacion = new SimpleDoubleProperty();
    private StringProperty seleccionTipo = new SimpleStringProperty();

    public ParameterDataModelProperties(Tablero originalTablero, Individuo originalIndividuo) {
        setOriginal(originalTablero, originalIndividuo);
    }

    public void commit() {
        originalTablero.setAncho(ancho.get());
        originalTablero.setLargo(largo.get());
        originalIndividuo.setVida(vidas.get());
        originalIndividuo.setProbReproduccion(probReproduccion.get());
        originalIndividuo.setProbClonacion(probClonacion.get());
        String tipo = seleccionTipo.get();
        switch (tipo) {
            case "Básico" -> originalIndividuo.setTipo(TipoIndividuo.BASICO);
            case "Normal" -> originalIndividuo.setTipo(TipoIndividuo.NORMAL);
            case "Avanzado" -> originalIndividuo.setTipo(TipoIndividuo.AVANZADO);
            default -> originalIndividuo.setTipo(TipoIndividuo.BASICO);
        }
    }

    public void rollback() {
        ancho.set(originalTablero.getAncho());
        largo.set(originalTablero.getLargo());
        vidas.set(originalIndividuo.getVida());
        probReproduccion.set(originalIndividuo.getProbReproduccion());
        probClonacion.set(originalIndividuo.getProbClonacion());
    }

    public Tablero getOriginalTablero() {
        return originalTablero;
    }

    public Individuo getOriginalIndividuo() {
        return originalIndividuo;
    }

    public void setOriginal(Tablero originalTablero, Individuo originalIndividuo) {
        this.originalTablero = originalTablero;
        this.originalIndividuo = originalIndividuo;
        rollback(); //Se inicializan los properties.

    }

    public Property<Number> anchoProperty() {
        return ancho;
    }

    public Property<Number> largoProperty() {
        return largo;
    }

    public Property<Number> vidasProperty() {
        return vidas;
    }

    public Property<Number> probReproduccionProperty() {
        return probReproduccion;
    }

    public Property<Number> probClonacionProperty() {
        return probClonacion;
    }

    public Property<String> seleccionTipoProperty() {
        return seleccionTipo;
    }
}
