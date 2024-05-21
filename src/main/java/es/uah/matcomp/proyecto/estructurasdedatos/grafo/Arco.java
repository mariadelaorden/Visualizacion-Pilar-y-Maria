package es.uah.matcomp.proyecto.estructurasdedatos.grafo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Arco<T> {
    private static final Logger logger = LogManager.getLogger(Arco.class);

    private NodoGrafo<T> inicio;
    private NodoGrafo<T> fin;
    private int peso;

    public Arco(NodoGrafo<T> inicio, NodoGrafo<T> fin, int peso) {
        try {
            this.inicio = inicio;
            this.fin = fin;
            this.peso = peso;
            logger.info("Arco creado: inicio={}, fin={}, peso={}", inicio, fin, peso);
        } catch (Exception e) {
            logger.error("Error al crear el arco", e);
            throw new RuntimeException("Error al crear el arco", e);
        }
    }

    public NodoGrafo<T> getInicio() {
        try {
            return inicio;
        } catch (Exception e) {
            logger.error("Error al obtener el nodo de inicio del arco", e);
            throw new RuntimeException("Error al obtener el nodo de inicio del arco", e);
        }
    }

    public void setInicio(NodoGrafo<T> inicio) {
        try {
            this.inicio = inicio;
            logger.info("Nodo de inicio del arco actualizado: {}", inicio);
        } catch (Exception e) {
            logger.error("Error al establecer el nodo de inicio del arco", e);
            throw new RuntimeException("Error al establecer el nodo de inicio del arco", e);
        }
    }

    public NodoGrafo<T> getFin() {
        try {
            return fin;
        } catch (Exception e) {
            logger.error("Error al obtener el nodo de fin del arco", e);
            throw new RuntimeException("Error al obtener el nodo de fin del arco", e);
        }
    }

    public void setFin(NodoGrafo<T> fin) {
        try {
            this.fin = fin;
            logger.info("Nodo de fin del arco actualizado: {}", fin);
        } catch (Exception e) {
            logger.error("Error al establecer el nodo de fin del arco", e);
            throw new RuntimeException("Error al establecer el nodo de fin del arco", e);
        }
    }

    public int getPeso() {
        try {
            return peso;
        } catch (Exception e) {
            logger.error("Error al obtener el peso del arco", e);
            throw new RuntimeException("Error al obtener el peso del arco", e);
        }
    }

    public void setPeso(int peso) {
        try {
            this.peso = peso;
            logger.info("Peso del arco actualizado: {}", peso);
        } catch (Exception e) {
            logger.error("Error al establecer el peso del arco", e);
            throw new RuntimeException("Error al establecer el peso del arco", e);
        }
    }
}
