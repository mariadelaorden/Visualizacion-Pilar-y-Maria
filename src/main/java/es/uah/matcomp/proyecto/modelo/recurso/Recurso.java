package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.controlador.ParameterDataModelProperties;
import es.uah.matcomp.proyecto.controlador.ParametersController;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;

public abstract class Recurso {

    protected int duracion;
    protected static double probAparicion;

    public Recurso() {
        this.duracion = 0;
    }

    public static double getProbAparicion() {
        return probAparicion;
    }

    public static void setProbAparicion(double probAparicion) {
        Recurso.probAparicion = probAparicion;
    }

    /**
     * Método abstracto para aplicar el efecto de este recurso en un individuo.
     */
    public abstract void aplicarEfecto(Individuo ind);

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}