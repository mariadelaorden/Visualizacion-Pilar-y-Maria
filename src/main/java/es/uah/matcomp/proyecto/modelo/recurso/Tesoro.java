package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tesoro extends Recurso {

    private static final Logger logger = LogManager.getLogger(Tesoro.class);

    private static double probAparicion;

    @Override
    public void aplicarEfecto(Individuo ind) {
        if (ind == null) {
            logger.error("Se intentó aplicar un efecto de Tesoro a un individuo nulo.");
            throw new IllegalArgumentException("El individuo no puede ser nulo");
        }
        // Lógica para reducir la vida del individuo
    }

    @Override
    public String toString() {
        return "T";
    }

    public static double getProbAparicion() {
        return probAparicion;
    }

    public static void setProbAparicion(double probAparicion) {
        if (probAparicion < 0 || probAparicion > 1) {
            logger.warn("Se intentó establecer una probabilidad de aparición de Tesoro fuera del rango [0, 1]: {}", probAparicion);
            throw new IllegalArgumentException("La probabilidad de aparición debe estar en el rango [0, 1]");
        }
        Tesoro.probAparicion = probAparicion;
    }
}
