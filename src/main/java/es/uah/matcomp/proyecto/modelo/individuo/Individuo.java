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
}
