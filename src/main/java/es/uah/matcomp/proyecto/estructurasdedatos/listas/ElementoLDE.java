package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElementoLDE {
    private static final Logger logger = LogManager.getLogger(ElementoLDE.class);

    private ElementoLDE anterior;
    private ElementoLDE siguiente;
    private Object data;

    public ElementoLDE(Object data) {
        try {
            this.data = data;
            logger.info("ElementoLDE creado con datos: {}", data);
        } catch (Exception e) {
            logger.error("Error al crear ElementoLDE", e);
            throw new RuntimeException("Error al crear ElementoLDE", e);
        }
    }

    protected void insertarmeEn(ElementoLDE el) {
        try {
            el.siguiente = this.siguiente;
            el.anterior = this;

            if (this.siguiente != null) {
                this.siguiente.anterior = el;
            }
            this.siguiente = el;
            logger.info("Elemento insertado: {}", el.data);
        } catch (Exception e) {
            logger.error("Error al insertar el ElementoLDE", e);
            throw new RuntimeException("Error al insertar el ElementoLDE", e);
        }
    }

    protected ElementoLDE getSiguiente() {
        try {
            return this.siguiente;
        } catch (Exception e) {
            logger.error("Error al obtener el siguiente elemento", e);
            throw new RuntimeException("Error al obtener el siguiente elemento", e);
        }
    }

    protected ElementoLDE getAnterior() {
        try {
            return this.anterior;
        } catch (Exception e) {
            logger.error("Error al obtener el elemento anterior", e);
            throw new RuntimeException("Error al obtener el elemento anterior", e);
        }
    }

    public Object getData() {
        try {
            return this.data;
        } catch (Exception e) {
            logger.error("Error al obtener los datos del elemento", e);
            throw new RuntimeException("Error al obtener los datos del elemento", e);
        }
    }

    public Object setData(Object o) {
        try {
            Object temporal = this.data;
            this.data = o;
            logger.info("Datos del elemento actualizados de {} a {}", temporal, o);
            return temporal;
        } catch (Exception e) {
            logger.error("Error al establecer los datos del elemento", e);
            throw new RuntimeException("Error al establecer los datos del elemento", e);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}
