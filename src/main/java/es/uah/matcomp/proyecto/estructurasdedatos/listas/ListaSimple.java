package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListaSimple {
    private static final Logger logger = LogManager.getLogger(ListaSimple.class);
    private ElementoLS[] datos;
    private int maximo;

    public ListaSimple(int maximo) {
        this.maximo = maximo;
        datos = new ElementoLS[maximo];
        logger.info("ListaSimple creada con tamaño máximo: {}", maximo);
    }

    public boolean isVacia() {
        try {
            for (int i = 0; i < maximo; i++) {
                if (datos[i] != null) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("Error al comprobar si la lista está vacía", e);
            throw new RuntimeException("Error al comprobar si la lista está vacía", e);
        }
    }

    public void vaciar() {
        try {
            for (int i = 0; i < maximo; i++) {
                datos[i] = null;
            }
            logger.info("Lista vaciada");
        } catch (Exception e) {
            logger.error("Error al vaciar la lista", e);
            throw new RuntimeException("Error al vaciar la lista", e);
        }
    }

    private int add(ElementoLS el) {
        try {
            for (int i = 0; i < maximo; i++) {
                if (datos[i] == null) {
                    datos[i] = el;
                    logger.info("Elemento añadido en la posición {}", i);
                    return i;
                }
            }
            logger.warn("No se pudo añadir el elemento, la lista está llena");
            return -1;
        } catch (Exception e) {
            logger.error("Error al añadir un elemento a la lista", e);
            throw new RuntimeException("Error al añadir un elemento a la lista", e);
        }
    }

    public void add(Object data) {
        try {
            ElementoLS el = new ElementoLS();
            el.setData(data);
            add(el);
        } catch (Exception e) {
            logger.error("Error al añadir un dato a la lista", e);
            throw new RuntimeException("Error al añadir un dato a la lista", e);
        }
    }

    public void insert(Object data, int posicion) {
        try {
            if ((posicion >= 0) && (posicion < maximo)) {
                ElementoLS el = new ElementoLS();
                el.setData(data);
                datos[posicion] = el;
                logger.info("Elemento insertado en la posición {}: {}", posicion, data);
            } else {
                logger.warn("Posición inválida: {}", posicion);
            }
        } catch (Exception e) {
            logger.error("Error al insertar un dato en la posición " + posicion, e);
            throw new RuntimeException("Error al insertar un dato en la posición " + posicion, e);
        }
    }

    public int del(int posicion) {
        try {
            if ((posicion >= 0) && (posicion < maximo)) {
                datos[posicion] = null;
                logger.info("Elemento eliminado en la posición {}", posicion);
                return 1;
            } else {
                logger.warn("Posición inválida: {}", posicion);
                return -1;
            }
        } catch (Exception e) {
            logger.error("Error al eliminar el elemento en la posición " + posicion, e);
            throw new RuntimeException("Error al eliminar el elemento en la posición " + posicion, e);
        }
    }

    public int getNumeroElementos() {
        try {
            int cont = 0;
            for (int i = 0; i < maximo; i++) {
                if (datos[i] != null) {
                    cont++;
                }
            }
            return cont;
        } catch (Exception e) {
            logger.error("Error al obtener el número de elementos de la lista", e);
            throw new RuntimeException("Error al obtener el número de elementos de la lista", e);
        }
    }

    public int getPosicion(ElementoLS el) {
        try {
            for (int i = 0; i < maximo; i++) {
                if (datos[i] != null && datos[i].equals(el)) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            logger.error("Error al obtener la posición del elemento", e);
            throw new RuntimeException("Error al obtener la posición del elemento", e);
        }
    }

    public ElementoLS getPrimero() {
        try {
            return datos[0];
        } catch (Exception e) {
            logger.error("Error al obtener el primer elemento de la lista", e);
            throw new RuntimeException("Error al obtener el primer elemento de la lista", e);
        }
    }

    public ElementoLS getUltimo() {
        try {
            for (int i = maximo - 1; i >= 0; i--) {
                if (datos[i] != null) {
                    return datos[i];
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("Error al obtener el último elemento de la lista", e);
            throw new RuntimeException("Error al obtener el último elemento de la lista", e);
        }
    }

    public ElementoLS getSiguiente(ElementoLS el) {
        try {
            for (int i = 0; i < maximo - 1; i++) {
                if (datos[i] == el && i + 1 < maximo) {
                    return datos[i + 1];
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("Error al obtener el siguiente elemento de la lista", e);
            throw new RuntimeException("Error al obtener el siguiente elemento de la lista", e);
        }
    }

    public ElementoLS getElemento(int posicion) {
        try {
            if ((posicion >= 0) && (posicion < maximo)) {
                return datos[posicion];
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Error al obtener el elemento en la posición " + posicion, e);
            throw new RuntimeException("Error al obtener el elemento en la posición " + posicion, e);
        }
    }

    public int getMaximo() {
        return maximo;
    }

    public void imprimir() {
        try {
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < maximo; i++) {
                if (datos[i] != null) {
                    resultado.append(datos[i].getData());
                } else {
                    resultado.append("null");
                }
                if (i < maximo - 1) {
                    resultado.append(", ");
                }
            }
            System.out.println(resultado.toString());
            logger.info("Lista impresa: {}", resultado.toString());
        } catch (Exception e) {
            logger.error("Error al imprimir la lista", e);
            throw new RuntimeException("Error al imprimir la lista", e);
        }
    }

    @Override
    public String toString() {
        try {
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < maximo; i++) {
                if (datos[i] != null) {
                    resultado.append(datos[i].getData());
                } else {
                    resultado.append("null");
                }
                if (i < maximo - 1) {
                    resultado.append(", ");
                }
            }
            return resultado.toString();
        } catch (Exception e) {
            logger.error("Error en el método toString", e);
            throw new RuntimeException("Error en el método toString", e);
        }
    }
}
