package es.uah.matcomp.proyecto.modelo.individuo;

import es.uah.matcomp.proyecto.excepciones.IndividuoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Individuo extends PlantillaIndividuo {

    private static final Logger logger = LogManager.getLogger(Individuo.class);

    private static int siguienteID = 0;
    private final int id;
    private final int generacion;
    private TipoIndividuo tipo;

    // Constructor
    public Individuo(PlantillaIndividuo plantilla, int generacion, TipoIndividuo tipo) {
        super(plantilla);
        this.id = obtenerSiguienteID();
        this.generacion = generacion;
        this.tipo = tipo;

        logger.info("Individuo creado: ID = {}, Generacion = {}, Tipo = {}", id, generacion, tipo);
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public int getGeneracion() {
        return generacion;
    }

    public TipoIndividuo getTipo() {
        return tipo;
    }

    public void setTipo(TipoIndividuo tipo) throws IndividuoException {
        if (tipo == null) {
            logger.error("Tipo de individuo no puede ser null");
            throw new IndividuoException("Tipo de individuo no puede ser null");
        }
        logger.info("Cambiando tipo de individuo ID = {} a {}", id, tipo);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "" + tipo.name().charAt(0);
    }

    private static synchronized int obtenerSiguienteID() {
        if (siguienteID < 0) {
            logger.error("El ID del siguiente individuo es negativo. Reiniciando a 0.");
            siguienteID = 0;
        }
        return siguienteID++;
    }
    public Individuo crearNuevoIndividuo() {
        // Usamos la misma plantilla
        PlantillaIndividuo plantilla = new PlantillaIndividuo(this);

        // Creamos un nuevo individuo con la misma plantilla y generación, pero con un nuevo ID
        Individuo nuevoIndividuo = new Individuo(plantilla, this.generacion, this.tipo);

        return nuevoIndividuo;
    }

    public Individuo evaluarClonacion() {
        double probabilidad = Math.random(); // Genera un número aleatorio entre 0 y 1
        if (probabilidad < this.getProbClonacion()) {
            // Se clona el individuo
            return this.crearNuevoIndividuo();
        } else {
            return null; // No se realiza la clonación
        }
    }
    public Individuo evaluarReproduccion() {
        double probabilidad = Math.random(); // Genera un número aleatorio entre 0 y 1
        if (probabilidad < this.getProbReproduccion()) {
            // Se crea un nuevo individuo
            return this.crearNuevoIndividuo();
        } else {
            return null; // No se realiza la reproducción
        }
    }


}

