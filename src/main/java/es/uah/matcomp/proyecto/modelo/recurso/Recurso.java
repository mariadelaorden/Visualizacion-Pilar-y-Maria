package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Recurso {

    protected static final Logger logger = LogManager.getLogger(Recurso.class);

    protected int duracion;
    protected static double probAparicion;

    public Recurso() {
        this.duracion = 0;
    }

    public static double getProbAparicion() {
        return probAparicion;
    }

    public static void setProbAparicion(double probAparicion) {
        if (probAparicion < 0 || probAparicion > 1) {
            logger.warn("Se intentó establecer una probabilidad de aparición fuera del rango [0, 1]: {}", probAparicion);
            throw new IllegalArgumentException("La probabilidad de aparición debe estar en el rango [0, 1]");
        }
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
        if (duracion < 0) {
            logger.warn("Se intentó establecer una duración negativa para el recurso: {}", duracion);
            throw new IllegalArgumentException("La duración del recurso no puede ser negativa");
        }
        this.duracion = duracion;
    }
}
