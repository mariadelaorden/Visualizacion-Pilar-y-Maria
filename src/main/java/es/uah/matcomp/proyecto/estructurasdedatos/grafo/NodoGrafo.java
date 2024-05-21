package es.uah.matcomp.proyecto.estructurasdedatos.grafo;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaEnlazada;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NodoGrafo<T> extends ElementoLS {
    private static final Logger logger = LogManager.getLogger(NodoGrafo.class);

    private String nombre;
    ListaEnlazada<Arco<T>> arcosEntrada;
    ListaEnlazada<Arco<T>> arcosSalida;

    public NodoGrafo(String nombre) {
        try {
            this.nombre = nombre;
            this.arcosEntrada = new ListaEnlazada<>();
            this.arcosSalida = new ListaEnlazada<>();
            logger.info("NodoGrafo creado: {}", nombre);
        } catch (Exception e) {
            logger.error("Error al crear NodoGrafo", e);
            throw new RuntimeException("Error al crear NodoGrafo", e);
        }
    }

    public String getData() {
        try {
            return this.nombre;
        } catch (Exception e) {
            logger.error("Error al obtener los datos del NodoGrafo", e);
            throw new RuntimeException("Error al obtener los datos del NodoGrafo", e);
        }
    }

    public void setData(String nombre) {
        try {
            this.nombre = nombre;
            logger.info("Datos del NodoGrafo actualizados a: {}", nombre);
        } catch (Exception e) {
            logger.error("Error al establecer los datos del NodoGrafo", e);
            throw new RuntimeException("Error al establecer los datos del NodoGrafo", e);
        }
    }

    @Override
    public String toString() {
        return ("Nodo:" + nombre);
    }

    public void addArcoSalida(Arco<T> arco) {
        try {
            this.arcosSalida.add(arco);
            logger.info("Arco de salida añadido al NodoGrafo {}: {}", nombre, arco);
        } catch (Exception e) {
            logger.error("Error al añadir arco de salida al NodoGrafo {}", nombre, e);
            throw new RuntimeException("Error al añadir arco de salida al NodoGrafo", e);
        }
    }

    public void addArcoEntrada(Arco<T> arco) {
        try {
            this.arcosEntrada.add(arco);
            logger.info("Arco de entrada añadido al NodoGrafo {}: {}", nombre, arco);
        } catch (Exception e) {
            logger.error("Error al añadir arco de entrada al NodoGrafo {}", nombre, e);
            throw new RuntimeException("Error al añadir arco de entrada al NodoGrafo", e);
        }
    }
}
