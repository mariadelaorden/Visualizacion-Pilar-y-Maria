package es.uah.matcomp.proyecto.javafx.visualizacion;

import es.uah.matcomp.proyecto.javafx.visualizacion.TableroController;
import javafx.beans.property.*;

public class ParameterDataModelProperties {
    //Modelo de datos original
    protected TableroController original;

    private IntegerProperty ancho = new SimpleIntegerProperty();
    private IntegerProperty largo = new SimpleIntegerProperty();

    public ParameterDataModelProperties(TableroController original){
        setOriginal(original);
    }

    public void commit(){
        original.setAncho(ancho.get());
        original.setLargo(largo.get());
    }

    public void rollback(){
        ancho.set(original.getAncho());
        largo.set(original.getLargo());
    }

    public TableroController getOriginal(){
        return original;
    }

    public void setOriginal(TableroController original){
        this.original = original;
        rollback(); //Se inicializan los properties.

    }

    public Property<Number> anchoProperty() {
        return ancho;
    }

    public Property<Number> largoProperty() {
        return largo;
    }

}
