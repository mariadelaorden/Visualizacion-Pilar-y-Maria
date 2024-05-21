package es.uah.matcomp.proyecto.estructurasdedatos.arbol;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListadoblementeEnlazada;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArbolBinarioDeBusqueda {
    private static final Logger logger = LogManager.getLogger(ArbolBinarioDeBusqueda.class);

    private Nodo raiz = null;

    public ArbolBinarioDeBusqueda() {
    }

    private boolean isVacio() {
        return raiz == null;
    }

    private Nodo addRecursivo(Nodo raiz, Object data) {
        if (raiz == null) {
            Nodo n1 = new Nodo(data);
            raiz = n1;
            return raiz;
        } else {
            try {
                if (((Comparable) data).compareTo(raiz.getData()) < 0) {
                    Nodo añadido = addRecursivo(raiz.getIzquierda(), data);
                    raiz.setIzquierda(añadido);
                } else {
                    Nodo añadido = addRecursivo(raiz.getDerecha(), data);
                    raiz.setDerecha(añadido);
                }
                return raiz;
            } catch (ClassCastException e) {
                logger.error("Error al añadir elemento: el objeto no es comparable.", e);
                throw new IllegalArgumentException("El objeto debe implementar la interfaz Comparable", e);
            }
        }
    }

    public void add(Object data) {
        try {
            if (isVacio()) {
                Nodo n1 = new Nodo(data);
                raiz = n1;
            } else {
                addRecursivo(raiz, data);
            }
            logger.info("Elemento añadido: {}", data);
        } catch (Exception e) {
            logger.error("Error al añadir elemento al árbol", e);
            throw e;
        }
    }

    public ArbolBinarioDeBusqueda getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda subArbol = new ArbolBinarioDeBusqueda();
        if (raiz != null && raiz.getIzquierda() != null) {
            subArbol.raiz = raiz.getIzquierda();
        }
        return subArbol;
    }

    public ArbolBinarioDeBusqueda getSubArbolDerecha() {
        ArbolBinarioDeBusqueda subArbol = new ArbolBinarioDeBusqueda();
        if (raiz != null && raiz.getDerecha() != null) {
            subArbol.raiz = raiz.getDerecha();
        }
        return subArbol;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public int getGrado() {
        try {
            return getGradoRecursivo(raiz);
        } catch (Exception e) {
            logger.error("Error al obtener el grado del árbol", e);
            throw e;
        }
    }

    private int getGradoRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int gradoIzquierda = getGradoRecursivo(nodo.getIzquierda());
        int gradoDerecha = getGradoRecursivo(nodo.getDerecha());
        return Math.max(gradoIzquierda, gradoDerecha) + 1;
    }

    public int getAltura() {
        try {
            return getAlturaRecursivo(raiz);
        } catch (Exception e) {
            logger.error("Error al obtener la altura del árbol", e);
            throw e;
        }
    }

    private int getAlturaRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzquierda = getAlturaRecursivo(nodo.getIzquierda());
        int alturaDerecha = getAlturaRecursivo(nodo.getDerecha());
        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }

    public ListadoblementeEnlazada<Integer> getListaDatosNivel(int nivel) {
        ListadoblementeEnlazada<Integer> datosNivel = new ListadoblementeEnlazada<>();
        try {
            getDatosNivelRecursivo(raiz, nivel, datosNivel, 1);
        } catch (Exception e) {
            logger.error("Error al obtener la lista de datos por nivel", e);
            throw e;
        }
        return datosNivel;
    }

    private void getDatosNivelRecursivo(Nodo nodo, int nivel, ListadoblementeEnlazada<Integer> datosNivel, int nivelActual) {
        if (nodo == null) {
            return;
        }
        if (nivelActual == nivel) {
            datosNivel.add((Integer) nodo.getData());
        } else {
            getDatosNivelRecursivo(nodo.getIzquierda(), nivel, datosNivel, nivelActual + 1);
            getDatosNivelRecursivo(nodo.getDerecha(), nivel, datosNivel, nivelActual + 1);
        }
    }

    public boolean isArbolHomogeneo() {
        try {
            return isArbolHomogeneoRecursivo(raiz, getGrado());
        } catch (Exception e) {
            logger.error("Error al verificar si el árbol es homogéneo", e);
            throw e;
        }
    }

    private boolean isArbolHomogeneoRecursivo(Nodo nodo, int grado) {
        if (nodo == null) {
            return true;
        }
        if ((nodo.getIzquierda() != null && nodo.getDerecha() == null) ||
                (nodo.getIzquierda() == null && nodo.getDerecha() != null) ||
                getGradoRecursivo(nodo) != grado) {
            return false;
        }
        return isArbolHomogeneoRecursivo(nodo.getIzquierda(), grado) && isArbolHomogeneoRecursivo(nodo.getDerecha(), grado);
    }

    public boolean isArbolCompleto() {
        try {
            int altura = getAltura();
            for (int i = 1; i < altura - 1; i++) {
                if (getListaDatosNivel(i).getNumeroElementos() != Math.pow(2, i - 1)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("Error al verificar si el árbol es completo", e);
            throw e;
        }
    }

    public boolean isArbolCasiCompleto() {
        try {
            int altura = getAltura();
            return isArbolCasiCompletoRecursivo(this.raiz, 0, altura);
        } catch (Exception e) {
            logger.error("Error al verificar si el árbol es casi completo", e);
            throw e;
        }
    }

    private boolean isArbolCasiCompletoRecursivo(Nodo nodo, int index, int cantidadNodos) {
        if (nodo == null)
            return true;

        if (index >= cantidadNodos)
            return false;

        return (isArbolCasiCompletoRecursivo(nodo.getIzquierda(), 2 * index + 1, cantidadNodos) &&
                isArbolCasiCompletoRecursivo(nodo.getDerecha(), 2 * index + 2, cantidadNodos));
    }

    public ListadoblementeEnlazada<Integer> getCamino(Integer elemento) {
        ListadoblementeEnlazada<Integer> camino = new ListadoblementeEnlazada<>();
        try {
            obtenerCamino(raiz, elemento, camino);
        } catch (Exception e) {
            logger.error("Error al obtener el camino hasta el elemento: {}", elemento, e);
            throw e;
        }
        return camino;
    }

    private boolean obtenerCamino(Nodo nodo, Integer elemento, ListadoblementeEnlazada<Integer> camino) {
        if (nodo == null) {
            return false;
        }
        camino.add((Integer) nodo.getData());
        if (nodo.getData().equals(elemento)) {
            return true;
        }
        if (obtenerCamino(nodo.getIzquierda(), elemento, camino) || obtenerCamino(nodo.getDerecha(), elemento, camino)) {
            return true;
        }
        camino.del(camino.getNumeroElementos());  //Elimino del final
        return false;
    }

    public ListadoblementeEnlazada<Integer> getListaPreOrden() {
        ListadoblementeEnlazada<Integer> lpreO = new ListadoblementeEnlazada<>();
        try {
            listaPreOrden(raiz, lpreO);
        } catch (Exception e) {
            logger.error("Error al obtener la lista en preorden", e);
            throw e;
        }
        return lpreO;
    }

    private void listaPreOrden(Nodo nodo, ListadoblementeEnlazada<Integer> lista) {
        if (nodo == null) {
            return;
        }
        lista.add((Integer) nodo.getData());  //Añade el dato del nodo a la lista
        listaPreOrden(nodo.getIzquierda(), lista);
        listaPreOrden(nodo.getDerecha(), lista);
    }

    public ListadoblementeEnlazada<Integer> getListaPostOrden() {
        ListadoblementeEnlazada<Integer> lpostO = new ListadoblementeEnlazada<>();
        try {
            listaPostOrden(raiz, lpostO);
        } catch (Exception e) {
            logger.error("Error al obtener la lista en postorden", e);
            throw e;
        }
        return lpostO;
    }

    private void listaPostOrden(Nodo nodo, ListadoblementeEnlazada<Integer> lista) {
        if (nodo == null) {
            return;
        }
        listaPostOrden(nodo.getIzquierda(), lista);
        listaPostOrden(nodo.getDerecha(), lista);
        lista.add((Integer) nodo.getData());  //Añade el dato del nodo a la lista
    }

    public ListadoblementeEnlazada<Integer> getListaOrdenCentral() {
        ListadoblementeEnlazada<Integer> lOcent = new ListadoblementeEnlazada<>();
        try {
            listaOrdenCentral(raiz, lOcent);
        } catch (Exception e) {
            logger.error("Error al obtener la lista en orden central", e);
            throw e;
        }
        return lOcent;
    }

    private void listaOrdenCentral(Nodo nodo, ListadoblementeEnlazada<Integer> lista) {
        if (nodo == null) {
            return;
        }
        listaOrdenCentral(nodo.getIzquierda(), lista);
        lista.add((Integer) nodo.getData());  //Añade el dato del nodo a la lista
        listaOrdenCentral(nodo.getDerecha(), lista);
    }
}
