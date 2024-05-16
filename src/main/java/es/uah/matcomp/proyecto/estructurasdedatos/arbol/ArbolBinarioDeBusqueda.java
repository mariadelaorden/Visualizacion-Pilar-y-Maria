package es.uah.matcomp.proyecto.estructurasdedatos.arbol;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListadoblementeEnlazada;


public class ArbolBinarioDeBusqueda {
    Nodo raiz=null;

    public ArbolBinarioDeBusqueda() {
    }

    private boolean isVacio() {
        return raiz == null;
    }

    private Nodo addrecursivo(Nodo raiz, Object data){
        if (raiz == null) {
            Nodo n1 = new Nodo(data);
            raiz=n1;
            return raiz;
        }
        else{
            if (((Comparable)data).compareTo(raiz.getData())<0){
                Nodo añadido= addrecursivo(raiz.getIzquierda(),data);
                raiz.setIzquierda(añadido);
            }
            else{
                Nodo añadido= addrecursivo(raiz.getDerecha(),data);
                raiz.setDerecha(añadido);
            }
            return raiz;
        }
    }
    public void add(Object data) {
        if(isVacio()){
            Nodo n1 = new Nodo(data);
            raiz=n1;
        }
        else {
            addrecursivo(raiz, data);
        }
    }
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros subArbol = new ArbolBinarioDeBusquedaEnteros();
        if (raiz != null && raiz.getIzquierda() != null) {
            subArbol.raiz = raiz.getIzquierda();
        }
        return subArbol;
    }

    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros subArbol = new ArbolBinarioDeBusquedaEnteros();
        if (raiz != null && raiz.getDerecha() != null) {
            subArbol.raiz = raiz.getDerecha();
        }
        return subArbol;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public int getGrado() {
        return getGradoRecursivo(raiz);
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
        return getAlturaRecursivo(raiz);
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
        ListadoblementeEnlazada<Integer> datosnivel = new ListadoblementeEnlazada<>();
        getDatosNivelRecursivo(raiz, nivel, datosnivel, 1);
        return datosnivel;
    }

    private void getDatosNivelRecursivo(Nodo nodo, int nivel, ListadoblementeEnlazada<Integer> datosnivel, int nivelActual) {
        if (nodo == null) {
            return;
        }
        if (nivelActual == nivel) {
            datosnivel.add((Integer) nodo.getData());
        } else {
            getDatosNivelRecursivo(nodo.getIzquierda(), nivel, datosnivel, nivelActual + 1);
            getDatosNivelRecursivo(nodo.getDerecha(), nivel, datosnivel, nivelActual + 1);
        }
    }

    public boolean isArbolHomogeneo() {
        return isArbolHomogeneoRecursivo(raiz, getGrado());
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
        int altura = getAltura();
        for (int i = 1; i < altura-1; i++) {
            if (getListaDatosNivel(i).getNumeroElementos() != Math.pow(2, i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isArbolCasiCompleto() {
        int altura = getAltura();
        return isArbolCasiCompletoRecursivo(this.raiz, 0, altura);
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
        obtenerCamino(raiz, elemento, camino);
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
    public ListadoblementeEnlazada<Integer> getListaPreOrden(){
        ListadoblementeEnlazada<Integer> lpreO = new ListadoblementeEnlazada<>();
        ListaPreOrden(raiz, lpreO);
        return lpreO;
    }

    private void ListaPreOrden(Nodo nodo, ListadoblementeEnlazada<Integer> lista) {
        if (nodo == null) {
            return;
        }
        lista.add((Integer) nodo.getData());  //Añade el dato del nodo a la lista
        ListaPreOrden(nodo.getIzquierda(), lista);
        ListaPreOrden(nodo.getDerecha(), lista);
    }

    public ListadoblementeEnlazada<Integer> getListaPostOrden(){
        ListadoblementeEnlazada<Integer> lpostO = new ListadoblementeEnlazada<>();
        ListaPostOrden(raiz, lpostO);
        return lpostO;
    }

    private void ListaPostOrden(Nodo nodo, ListadoblementeEnlazada<Integer> lista) {
        if (nodo == null) {
            return;
        }
        ListaPostOrden(nodo.getIzquierda(), lista);
        ListaPostOrden(nodo.getDerecha(), lista);
        lista.add((Integer) nodo.getData());  //Añade el dato del nodo a la lista
    }

    public ListadoblementeEnlazada<Integer> getListaOrdenCentral(){
        ListadoblementeEnlazada<Integer> lOcent = new ListadoblementeEnlazada<>();
        ListaOrdenCentral(raiz, lOcent);
        return lOcent;
    }

    private void ListaOrdenCentral(Nodo nodo, ListadoblementeEnlazada<Integer> lista) {
        if (nodo == null) {
            return;
        }
        ListaOrdenCentral(nodo.getIzquierda(), lista);
        lista.add((Integer) nodo.getData());  //Añade el dato del nodo a la lista
        ListaOrdenCentral(nodo.getDerecha(), lista);
    }



}
