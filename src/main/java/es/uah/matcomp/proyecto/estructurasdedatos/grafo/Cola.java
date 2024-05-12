package es.uah.matcomp.proyecto.estructurasdedatos.grafo;

public class Cola<T> {
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
    }

    public boolean isVacia() {
        return frente == null;
    }

    public void add(T elemento) {
        NodoGrafo<T> nuevoNodo = new NodoGrafo<>(elemento);

        if (isVacia()) {
            frente = fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
    }

    public T pull() {
        if (isVacia()) {
            return null;
        }

        T elemento = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        return elemento;
    }
}