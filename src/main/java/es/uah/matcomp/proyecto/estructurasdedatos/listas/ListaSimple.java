package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;

public class ListaSimple {
    private ElementoLS[] datos;
    private int maximo;

    public ListaSimple(int maximo) {
        this.maximo = maximo;

        datos = new ElementoLS[maximo];
    }

    public boolean isVacia() {
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null) {
                return false;
            }
        }
        return true;
    }

    public void vaciar() {
        for (int i = 0; i < maximo; i++) {
            datos[i] = null;
        }
    }

    private int add(ElementoLS el) {
        for (int i = 0; i < maximo; i++) {
            if (datos[i] == null) {
                datos[i] = el;
                return i;
            }
        }
        return -1;
    }

    public void add(Object data) {
        ElementoLS el = new ElementoLS();
        el.setData(data);
        add(el);
    }

    public void insert(Object data, int posicion) {
        if ((posicion >= 0) && (posicion < maximo)) {
            ElementoLS el = new ElementoLS();
            el.setData(data);
            datos[posicion] = el;
        }
    }

    public int del(int posicion) {
        if ((posicion >= 0) && (posicion < maximo)) {
            datos[posicion] = null;
            return 1;
        }
        return -1;
    }

    public int getNumeroElementos() {
        int cont = 0;
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null) {
                cont++;
            }
        }
        return cont;
    }

    public int getPosicion(ElementoLS el) {
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null && datos[i].equals(el)) {
                return i;
            }
        }
        return -1;
    }

    public ElementoLS getPrimero() {
        return datos[0];
    }

    public ElementoLS getUltimo() {
        for (int i = maximo - 1; i >= 0; i--) {
            if (datos[i] != null) {
                return datos[i];
            }
        }
        return null;
    }

    public ElementoLS getSiguiente(ElementoLS el) {
        for (int i = 0; i < maximo - 1; i++) {
            if (datos[i] == el && i + 1 < maximo) {
                return datos[i + 1];
            }
        }
        return null;
    }

    public ElementoLS getElemento(int posicion) {
        if ((posicion >= 0) && (posicion < maximo)) {
            return datos[posicion];
        }
        return null;
    }

    public int getMaximo() {
        return maximo;
    }

    public void imprimir() {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null) {
                resultado.append(datos[i].getData());
            } else {
                resultado.append("null");
            }
            if (i < maximo - 1) {
                resultado.append(", ");  // Añade una coma y un espacio entre los elementos
            }
        }
        System.out.println(resultado.toString());
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null) {
                resultado.append(datos[i].getData());
            } else {
                resultado.append("null");
            }
            if (i < maximo - 1) {
                resultado.append(", ");  // Añade una coma y un espacio entre los elementos
            }
        }
        return resultado.toString();
    }

}