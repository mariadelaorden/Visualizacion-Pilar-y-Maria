package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElementoLE<T> {
    private static final Logger logger = LogManager.getLogger(ElementoLE.class);

    private T data;
    private ElementoLE<T> siguiente;

    public ElementoLE(T data) {
        try {
            this.data = data;
            this.siguiente = null;
            logger.info("ElementoLE creado con datos: {}", data);
        } catch (Exception e) {
            logger.error("Error al crear ElementoLE", e);
            throw new RuntimeException("Error al crear ElementoLE", e);
        }
    }

    public T getData() {
        try {
            return data;
        } catch (Exception e) {
            logger.error("Error al obtener los datos del elemento", e);
            throw new RuntimeException("Error al obtener los datos del elemento", e);
        }
    }

    public void setData(T data) {
        try {
            this.data = data;
            logger.info("Datos del elemento actualizados a: {}", data);
        } catch (Exception e) {
            logger.error("Error al establecer los datos del elemento", e);
            throw new RuntimeException("Error al establecer los datos del elemento", e);
        }
    }

    public ElementoLE<T> getSiguiente() {
        try {
            return siguiente;
        } catch (Exception e) {
            logger.error("Error al obtener el siguiente elemento", e);
            throw new RuntimeException("Error al obtener el siguiente elemento", e);
        }
    }

    public void setSiguiente(ElementoLE<T> siguiente) {
        try {
            this.siguiente = siguiente;
            logger.info("Siguiente elemento establecido: {}", siguiente);
        } catch (Exception e) {
            logger.error("Error al establecer el siguiente elemento", e);
            throw new RuntimeException("Error al establecer el siguiente elemento", e);
        }
    }
}
