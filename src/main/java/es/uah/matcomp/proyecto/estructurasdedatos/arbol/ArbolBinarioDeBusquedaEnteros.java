package es.uah.matcomp.proyecto.estructurasdedatos.arbol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda {
    private static final Logger logger = LogManager.getLogger(ArbolBinarioDeBusquedaEnteros.class);

    public ArbolBinarioDeBusquedaEnteros() {
        super();
        logger.info("ArbolBinarioDeBusquedaEnteros creado.");
    }

    public int getSuma() {
        try {
            int suma = suma(this.getRaiz());
            logger.info("Suma de todos los elementos: {}", suma);
            return suma;
        } catch (Exception e) {
            logger.error("Error al calcular la suma de los elementos del árbol", e);
            throw e;
        }
    }

    private int suma(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        try {
            int izq = suma(nodo.getIzquierda());
            int dcha = suma(nodo.getDerecha());
            int sumatotal = (int) nodo.getData() + izq + dcha;
            return sumatotal;
        } catch (Exception e) {
            logger.error("Error al sumar los elementos del nodo: {}", nodo.getData(), e);
            throw new RuntimeException("Error al sumar los elementos del nodo", e);
        }
    }
}
