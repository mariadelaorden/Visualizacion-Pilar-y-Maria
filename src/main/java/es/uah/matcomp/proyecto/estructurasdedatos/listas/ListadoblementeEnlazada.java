package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListadoblementeEnlazada<I extends Number> {
    private static final Logger logger = LogManager.getLogger(ListadoblementeEnlazada.class);

    private ElementoLDE primero;
    private ElementoLDE ultimo;

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
            ultimo = null;
            logger.info("Lista vaciada");
        } catch (Exception e) {
            logger.error("Error al vaciar la lista", e);
            throw new RuntimeException("Error al vaciar la lista", e);
        }
    }

    private int add(ElementoLDE el) {
        try {
            if (primero == null) {
                primero = el;
            } else {
                ultimo.insertarmeEn(el);
            }
            ultimo = el;
            ElementoLDE posicion = primero;
            int contador = 0;
            while (posicion.getSiguiente() != null) {
                contador++;
                posicion = posicion.getSiguiente();
            }
            logger.info("Elemento añadido: {}", el);
            return contador;
        } catch (Exception e) {
            logger.error("Error al añadir un elemento a la lista", e);
            throw new RuntimeException("Error al añadir un elemento a la lista", e);
        }
    }

    public void add(String s) {
        try {
            ElementoLDE el = new ElementoLDE(s);
            this.add(el);
        } catch (Exception e) {
            logger.error("Error al añadir un elemento (String) a la lista", e);
            throw new RuntimeException("Error al añadir un elemento (String) a la lista", e);
        }
    }

    public void add(Object o) {
        try {
            ElementoLDE el = new ElementoLDE(o);
            this.add(el);
        } catch (Exception e) {
            logger.error("Error al añadir un elemento (Object) a la lista", e);
            throw new RuntimeException("Error al añadir un elemento (Object) a la lista", e);
        }
    }

    public void insert(String s, int posicion) {
        try {
            ElementoLDE el = new ElementoLDE(s);
            if (posicion >= 0) {
                if (posicion == 0 || isVacia()) {
                    el.insertarmeEn(primero);
                    if (ultimo == null) {
                        ultimo = el;
                    }
                } else {
                    ElementoLDE actual = primero;
                    int contador = 0;
                    while (actual != null && contador < posicion) {
                        actual = actual.getSiguiente();
                        contador++;
                    }
                    if (actual != null) {
                        actual.insertarmeEn(el);
                    } else {
                        ultimo.insertarmeEn(el);
                        ultimo = el;
                    }
                }
                logger.info("Elemento insertado en la posición {}: {}", posicion, s);
            }
        } catch (Exception e) {
            logger.error("Error al insertar un elemento (String) en la posición " + posicion, e);
            throw new RuntimeException("Error al insertar un elemento (String) en la posición " + posicion, e);
        }
    }

    public void insert(Object o, int posicion) {
        try {
            ElementoLDE el = new ElementoLDE(o);
            if (posicion >= 0) {
                if (posicion == 0 || isVacia()) {
                    el.insertarmeEn(primero);
                    if (ultimo == null) {
                        ultimo = el;
                    }
                } else {
                    ElementoLDE actual = primero;
                    int contador = 0;
                    while (actual != null && contador < posicion) {
                        actual = actual.getSiguiente();
                        contador++;
                    }
                    if (actual != null) {
                        actual.insertarmeEn(el);
                    } else {
                        ultimo.insertarmeEn(el);
                        ultimo = el;
                    }
                }
                logger.info("Elemento insertado en la posición {}: {}", posicion, o);
            }
        } catch (Exception e) {
            logger.error("Error al insertar un elemento (Object) en la posición " + posicion, e);
            throw new RuntimeException("Error al insertar un elemento (Object) en la posición " + posicion, e);
        }
    }

    public int del(int posicion) {
        try {
            if (!isVacia() && posicion >= 0) {
                if (posicion == 0) {
                    primero = primero.getSiguiente();
                    if (primero == null) {
                        ultimo = null;
                    }
                    logger.info("Elemento eliminado en la posición 0");
                    return 1;
                } else {
                    ElementoLDE actual = primero;
                    int contador = 0;
                    while (actual != null && contador < posicion) {
                        actual = actual.getSiguiente();
                        contador++;
                    }
                    if (actual != null && actual.getSiguiente() != null) {
                        actual.getSiguiente().insertarmeEn(actual.getAnterior());
                        if (actual.getSiguiente() == ultimo) {
                            ultimo = actual;
                        }
                        logger.info("Elemento eliminado en la posición {}", posicion);
                        return 1;
                    }
                }
            }
            logger.warn("No se pudo eliminar el elemento en la posición {}", posicion);
            return -1;
        } catch (Exception e) {
            logger.error("Error al eliminar el elemento en la posición " + posicion, e);
            throw new RuntimeException("Error al eliminar el elemento en la posición " + posicion, e);
        }
    }

    public int getNumeroElementos() {
        try {
            int contador = 0;
            ElementoLDE actual = primero;
            while (actual != null) {
                contador++;
                actual = actual.getSiguiente();
            }
            return contador;
        } catch (Exception e) {
            logger.error("Error al obtener el número de elementos", e);
            throw new RuntimeException("Error al obtener el número de elementos", e);
        }
    }

    public int getPosicion(ElementoLDE el) {
        try {
            int posicion = 0;
            ElementoLDE actual = primero;
            while (actual != null) {
                if (actual.getData().equals(el.getData())) {
                    return posicion;
                }
                actual = actual.getSiguiente();
                posicion++;
            }
            logger.warn("Elemento no encontrado: {}", el);
            return -1;
        } catch (Exception e) {
            logger.error("Error al obtener la posición del elemento", e);
            throw new RuntimeException("Error al obtener la posición del elemento", e);
        }
    }

    public ElementoLDE getPrimero() {
        try {
            return this.primero;
        } catch (Exception e) {
            logger.error("Error al obtener el primer elemento", e);
            throw new RuntimeException("Error al obtener el primer elemento", e);
        }
    }

    public ElementoLDE getUltimo() {
        try {
            return this.ultimo;
        } catch (Exception e) {
            logger.error("Error al obtener el último elemento", e);
            throw new RuntimeException("Error al obtener el último elemento", e);
        }
    }

    public ElementoLDE getAnterior(ElementoLDE el) {
        try {
            if (el != null) {
                return el.getAnterior();
            }
            return null;
        } catch (Exception e) {
            logger.error("Error al obtener el elemento anterior", e);
            throw new RuntimeException("Error al obtener el elemento anterior", e);
        }
    }

    public ElementoLDE getSiguiente(ElementoLDE el) {
        try {
            if (el != null) {
                return el.getSiguiente();
            }
            return null;
        } catch (Exception e) {
            logger.error("Error al obtener el siguiente elemento", e);
            throw new RuntimeException("Error al obtener el siguiente elemento", e);
        }
    }

    public ElementoLDE getElemento(int posicion) {
        try {
            ElementoLDE actual = primero;
            int contador = 0;
            while (actual != null && contador < posicion) {
                actual = actual.getSiguiente();
                contador++;
            }
            return actual;
        } catch (Exception e) {
            logger.error("Error al obtener el elemento en la posición " + posicion, e);
            throw new RuntimeException("Error al obtener el elemento en la posición " + posicion, e);
        }
    }
}
