package es.uah.matcomp.proyecto.javafx.visualizacion;

import es.uah.matcomp.proyecto.cod.Individuo;
import es.uah.matcomp.proyecto.cod.Tablero;
import es.uah.matcomp.proyecto.javafx.visualizacion.TableroController;
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

    public ParameterDataModelProperties(Tablero originaltablero, Individuo originalIndividuo){
        setOriginal(originalTablero,originalIndividuo);
    }

    public void commit(){
        originalTablero.setAncho(ancho.get());
        originalTablero.setLargo(largo.get());
        originalIndividuo.setVida(vidas.get());
        originalIndividuo.setProbreproduccion(probReproduccion.get());
        originalIndividuo.setProbclonacion(probClonacion.get());
        originalIndividuo.setTipo(seleccionTipo.get());
    }

    public void rollback(){
        ancho.set(originalTablero.getAncho());
        largo.set(originalTablero.getLargo());

    }

    public Tablero getOriginalTablero(){
        return originalTablero;
    }
    public Individuo getOriginalIndividuo(){
        return originalIndividuo;
    }

    public void setOriginal(Tablero originalTablero, Individuo originalIndividuo){
        this.originalTablero = originalTablero;
        this.originalIndividuo= originalIndividuo;
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
