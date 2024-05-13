package es.uah.matcomp.proyecto.modelo;
import es.uah.matcomp.proyecto.modelo.ParameterDataModel;
import javafx.beans.property.*;

public class ParameterDataModelProperties {
    //Modelo de datos original
    protected ParameterDataModel original;
    //Propiedades tablero
    private IntegerProperty ancho = new SimpleIntegerProperty();
    private IntegerProperty largo = new SimpleIntegerProperty();
    //Propiedades individuos
    private IntegerProperty vidas = new SimpleIntegerProperty();
    private DoubleProperty probReproduccion = new SimpleDoubleProperty();
    private DoubleProperty probClonacion = new SimpleDoubleProperty();
    private StringProperty seleccionTipo = new SimpleStringProperty();
    //Propiedades recursos
    private DoubleProperty TiempoAparicion = new SimpleDoubleProperty();
    private DoubleProperty probAparicion = new SimpleDoubleProperty();
    private StringProperty TipoRecurso = new SimpleStringProperty();
    public ParameterDataModelProperties(ParameterDataModel original){
        setOriginal(original);
    }
    public void commit(){
        original.setAncho(ancho.get());
        original.setLargo(largo.get());

        original.setVidas(vidas.get());
        original.setProbReproduccion(probReproduccion.get());
        original.setProbClonacion(probClonacion.get());
        original.setSeleccionTipo(seleccionTipo.get());

        original.setTiempoAparicion(TiempoAparicion.get());
        original.setProbAparicion(probAparicion.get());
        original.setTipoRecurso(TipoRecurso.get());
    }
    public void rollback(){
        rollbackTablero();
        rollbackIndividuo();
        rollbackRecurso();
    }
    private void rollbackTablero() {
        ancho.set(original.getAncho());
        largo.set(original.getLargo());
    }
    private void rollbackIndividuo(){
        vidas.set(original.getVidas());
        probReproduccion.set(original.getProbReproduccion());
        probClonacion.set(original.getProbClonacion());
        seleccionTipo.set(original.getSeleccionTipo());
    }
    private void rollbackRecurso(){
        TiempoAparicion.set(original.getTiempoAparicion());
        probAparicion.set(original.getProbAparicion());
        TipoRecurso.set(original.getTipoRecurso());
    }
    public ParameterDataModel getOriginal(){
        return original;
    }
    public void setOriginal(ParameterDataModel original){
        this.original=original;
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
    public Property<Number> TiempoAparicionProperty() {
        return TiempoAparicion;
    }
    public Property<Number> probAparicionProperty() {
        return probAparicion;
    }
    public Property<String> TipoRecursoProperty() {
        return TipoRecurso;
    }


}
