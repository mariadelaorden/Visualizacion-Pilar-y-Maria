package es.uah.matcomp.proyecto.modelo.individuo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlantillaIndividuo {
    private static final Logger logger = LogManager.getLogger(PlantillaIndividuo.class);

    protected static double probMejora = 0.0;

    protected int vida;
    protected double probReproduccion;
    protected double probClonacion;
    protected double probMuerte;

    public PlantillaIndividuo() {
        this.vida = 0;
        this.probReproduccion = 0.0;
        this.probClonacion = 0.0;
        this.probMuerte = 0.0;
    }

    public PlantillaIndividuo(PlantillaIndividuo plantilla) {
        if (plantilla == null) {
            logger.error("Se intentó crear una instancia de PlantillaIndividuo a partir de un valor nulo.");
            throw new IllegalArgumentException("La plantilla no puede ser nula");
        }
        this.vida = plantilla.vida;
        this.probReproduccion = plantilla.probReproduccion;
        this.probClonacion = plantilla.probClonacion;
        this.probMuerte = plantilla.probMuerte;
    }

    public static void setProbMejora(double probMejora) {
        if (probMejora < 0 || probMejora > 1) {
            logger.warn("Se intentó establecer una probabilidad de mejora fuera del rango [0, 1]: {}", probMejora);
            throw new IllegalArgumentException("La probabilidad de mejora debe estar en el rango [0, 1]");
        }
        PlantillaIndividuo.probMejora = probMejora;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            logger.warn("Se intentó establecer una vida negativa: {}", vida);
            throw new IllegalArgumentException("La vida no puede ser negativa");
        }
        this.vida = vida;
    }

    public double getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(double probReproduccion) {
        if (probReproduccion < 0 || probReproduccion > 1) {
            logger.warn("Se intentó establecer una probabilidad de reproducción fuera del rango [0, 1]: {}", probReproduccion);
            throw new IllegalArgumentException("La probabilidad de reproducción debe estar en el rango [0, 1]");
        }
        this.probReproduccion = probReproduccion;
    }

    public double getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(double probClonacion) {
        if (probClonacion < 0 || probClonacion > 1) {
            logger.warn("Se intentó establecer una probabilidad de clonación fuera del rango [0, 1]: {}", probClonacion);
            throw new IllegalArgumentException("La probabilidad de clonación debe estar en el rango [0, 1]");
        }
        this.probClonacion = probClonacion;
    }

    public double getProbMuerte() {
        return probMuerte;
    }

    public void setProbMuerte(double probMuerte) {
        if (probMuerte < 0 || probMuerte > 1) {
            logger.warn("Se intentó establecer una probabilidad de muerte fuera del rango [0, 1]: {}", probMuerte);
            throw new IllegalArgumentException("La probabilidad de muerte debe estar en el rango [0, 1]");
        }
        this.probMuerte = probMuerte;
    }
}
