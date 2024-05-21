package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListaEnlazada<T> {
    private static final Logger logger = LogManager.getLogger(ListaEnlazada.class);
    private ElementoLE<T> primero;

    public ListaEnlazada() {
        this.primero = null;
        logger.info("ListaEnlazada creada");
    }

    public boolean isVacia() {
        try {
            return primero == null;
        } catch (Exception e) {
            logger.error("Error al comprobar si la lista está vacía", e);
            throw new RuntimeException("Error al comprobar si la lista está vacía", e);
        }
    }

    public void vaciar() {
        try {
            primero = null;
            logger.info("Lista vaciada");
        } catch (Exception e) {
            logger.error("Error al vaciar la lista", e);
            throw new RuntimeException("Error al vaciar la lista", e);
        }
    }

    public void add(ElementoLE<T> el) {
        try {
            if (primero == null) {
                primero = el;
            } else {
                ElementoLE<T> actual = primero;
                while (actual.getSiguiente() != null) {
                    actual = actual.getSiguiente();
                }
                actual.setSiguiente(el);
            }
            logger.info("Elemento añadido: {}", el.getData());
        } catch (Exception e) {
            logger.error("Error al añadir un elemento a la lista", e);
            throw new RuntimeException("Error al añadir un elemento a la lista", e);
        }
    }

    public void add(T data) {
        try {
            ElementoLE<T> nuevoElemento = new ElementoLE<>(data);
            add(nuevoElemento);
        } catch (Exception e) {
            logger.error("Error al añadir un dato a la lista", e);
            throw new RuntimeException("Error al añadir un dato a la lista", e);
        }
    }

    public void insert(ElementoLE<T> elemento, int posicion) {
        try {
            if (posicion < 0) {
                logger.warn("Posición inválida: {}", posicion);
                return;
            }

            if (posicion == 0) {
                elemento.setSiguiente(primero);
                primero = elemento;
            } else {
                ElementoLE<T> anterior = getElemento(posicion - 1);
                if (anterior != null) {
                    elemento.setSiguiente(anterior.getSiguiente());
                    anterior.setSiguiente(elemento);
                } else {
                    logger.warn("Posición inválida: {}", posicion);
                }
            }
            logger.info("Elemento insertado en la posición {}: {}", posicion, elemento.getData());
        } catch (Exception e) {
            logger.error("Error al insertar un elemento en la posición " + posicion, e);
            throw new RuntimeException("Error al insertar un elemento en la posición " + posicion, e);
        }
    }

    public void insert(T data, int posicion) {
        try {
            ElementoLE<T> nuevoElemento = new ElementoLE<>(data);
            insert(nuevoElemento, posicion);
        } catch (Exception e) {
            logger.error("Error al insertar un dato en la posición " + posicion, e);
            throw new RuntimeException("Error al insertar un dato en la posición " + posicion, e);
        }
    }

    public int del(int posicion) {
        try {
            if (posicion < 0 || primero == null) {
                logger.warn("Posición inválida o lista vacía: {}", posicion);
                return posicion;
            }
            if (posicion == 0) {
                primero = primero.getSiguiente();
            } else {
                ElementoLE<T> anterior = getElemento(posicion - 1);
                if (anterior != null && anterior.getSiguiente() != null) {
                    anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
                } else {
                    logger.warn("Posición inválida: {}", posicion);
                }
            }
            logger.info("Elemento eliminado en la posición {}", posicion);
            return posicion;
        } catch (Exception e) {
            logger.error("Error al eliminar el elemento en la posición " + posicion, e);
            throw new RuntimeException("Error al eliminar el elemento en la posición " + posicion, e);
        }
    }

    public int length() {
        try {
            int contador = 0;
            ElementoLE<T> actual = primero;
            while (actual != null) {
                contador++;
                actual = actual.getSiguiente();
            }
            return contador;
        } catch (Exception e) {
            logger.error("Error al obtener la longitud de la lista", e);
            throw new RuntimeException("Error al obtener la longitud de la lista", e);
        }
    }

    public int getPosicion(ElementoLE<T> el) {
        try {
            int posicion = 0;
            ElementoLE<T> actual = primero;
            while (actual != null) {
                if (actual == el) {
                    return posicion;
                }
                posicion++;
                actual = actual.getSiguiente();
            }
            logger.warn("Elemento no encontrado: {}", el.getData());
            return -1;
        } catch (Exception e) {
            logger.error("Error al obtener la posición del elemento", e);
            throw new RuntimeException("Error al obtener la posición del elemento", e);
        }
    }

    public ElementoLE<T> getUltimo() {
        try {
            ElementoLE<T> actual = primero;
            while (actual != null && actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            return actual;
        } catch (Exception e) {
            logger.error("Error al obtener el último elemento", e);
            throw new RuntimeException("Error al obtener el último elemento", e);
        }
    }

    public ElementoLE<T> getSiguiente(ElementoLE<T> el) {
        try {
            return el.getSiguiente();
        } catch (Exception e) {
            logger.error("Error al obtener el siguiente elemento", e);
            throw new RuntimeException("Error al obtener el siguiente elemento", e);
        }
    }

    public ElementoLE<T> getElemento(int posicion) {
        try {
            if (posicion < 0 || primero == null) {
                return null;
            }
            ElementoLE<T> actual = primero;
            for (int i = 0; i < posicion; i++) {
                if (actual == null) {
                    return null;
                }
                actual = actual.getSiguiente();
            }
            return actual;
        } catch (Exception e) {
            logger.error("Error al obtener el elemento en la posición " + posicion, e);
            throw new RuntimeException("Error al obtener el elemento en la posición " + posicion, e);
        }
    }
}
