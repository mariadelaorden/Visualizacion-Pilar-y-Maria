package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Montana extends Recurso {

    private static final Logger logger = LogManager.getLogger(Montana.class);
    private static double probAparicion;

    @Override
    public void aplicarEfecto(Individuo ind) {
        if (ind == null) {
            logger.error("Se intentó aplicar un efecto de Montana a un individuo nulo.");
            throw new IllegalArgumentException("El individuo no puede ser nulo");
        }

        // Lógica para reducir la vida del individuo
    }

    @Override
    public String toString() {
        return "M";
    }

    public static double getProbAparicion() {
        return probAparicion;
    }

    public static void setProbAparicion(double probAparicion) {
        if (probAparicion < 0 || probAparicion > 1) {
            logger.warn("Se intentó establecer una probabilidad de aparición de Montana fuera del rango [0, 1]: {}", probAparicion);
            throw new IllegalArgumentException("La probabilidad de aparición de Montana debe estar en el rango [0, 1]");
        }
        Montana.probAparicion = probAparicion;
    }
}
