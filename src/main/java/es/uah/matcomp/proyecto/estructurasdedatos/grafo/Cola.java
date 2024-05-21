package es.uah.matcomp.proyecto.estructurasdedatos.grafo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cola<T> {
    private static final Logger logger = LogManager.getLogger(Cola.class);

    private NodoGrafo<T> frente;
    private NodoGrafo<T> fin;

    private static class NodoGrafo<T> {
        T dato;
        NodoGrafo<T> siguiente;

        NodoGrafo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Cola() {
        this.frente = this.fin = null;
        logger.info("Cola creada.");
    }

    public boolean isVacia() {
        return frente == null;
    }

    public void add(T elemento) {
        try {
            NodoGrafo<T> nuevoNodo = new NodoGrafo<>(elemento);

            if (isVacia()) {
                frente = fin = nuevoNodo;
            } else {
                fin.siguiente = nuevoNodo;
                fin = nuevoNodo;
            }
            logger.info("Elemento añadido a la cola: {}", elemento);
        } catch (Exception e) {
            logger.error("Error al añadir el elemento a la cola: {}", elemento, e);
            throw new RuntimeException("Error al añadir el elemento a la cola", e);
        }
    }

    public T pull() {
        try {
            if (isVacia()) {
                logger.warn("Intento de extracción desde una cola vacía.");
                return null;
            }

            T elemento = frente.dato;
            frente = frente.siguiente;

            if (frente == null) {
                fin = null;
            }

            logger.info("Elemento extraído de la cola: {}", elemento);
            return elemento;
        } catch (Exception e) {
            logger.error("Error al extraer el elemento de la cola", e);
            throw new RuntimeException("Error al extraer el elemento de la cola", e);
        }
    }
}
