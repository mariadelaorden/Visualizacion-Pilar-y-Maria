package es.uah.matcomp.proyecto.estructurasdedatos.arbol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Nodo {
    private static final Logger logger = LogManager.getLogger(Nodo.class);

    private Object data;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(Object data) {
        try {
            this.data = data;
            this.izquierda = null;
            this.derecha = null;
            logger.info("Nodo creado con datos: {}", data);
        } catch (Exception e) {
            logger.error("Error al crear el nodo", e);
            throw new RuntimeException("Error al crear el nodo", e);
        }
    }

    public Object getData() {
        try {
            return data;
        } catch (Exception e) {
            logger.error("Error al obtener los datos del nodo", e);
            throw new RuntimeException("Error al obtener los datos del nodo", e);
        }
    }

    public void setData(Object data) {
        try {
            this.data = data;
            logger.info("Datos del nodo actualizados a: {}", data);
        } catch (Exception e) {
            logger.error("Error al establecer los datos del nodo", e);
            throw new RuntimeException("Error al establecer los datos del nodo", e);
        }
    }

    public Nodo getIzquierda() {
        try {
            return this.izquierda;
        } catch (Exception e) {
            logger.error("Error al obtener el nodo izquierdo", e);
            throw new RuntimeException("Error al obtener el nodo izquierdo", e);
        }
    }

    public Nodo getDerecha() {
        try {
            return this.derecha;
        } catch (Exception e) {
            logger.error("Error al obtener el nodo derecho", e);
            throw new RuntimeException("Error al obtener el nodo derecho", e);
        }
    }

    public void setIzquierda(Nodo izquierda) {
        try {
            this.izquierda = izquierda;
            logger.info("Nodo izquierdo establecido: {}", izquierda != null ? izquierda.getData() : "null");
        } catch (Exception e) {
            logger.error("Error al establecer el nodo izquierdo", e);
            throw new RuntimeException("Error al establecer el nodo izquierdo", e);
        }
    }

    public void setDerecha(Nodo derecha) {
        try {
            this.derecha = derecha;
            logger.info("Nodo derecho establecido: {}", derecha != null ? derecha.getData() : "null");
        } catch (Exception e) {
            logger.error("Error al establecer el nodo derecho", e);
            throw new RuntimeException("Error al establecer el nodo derecho", e);
        }
    }
}
