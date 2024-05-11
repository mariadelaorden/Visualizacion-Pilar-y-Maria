package es.uah.matcomp.proyecto.cod.listas;

public class ListaEnlazada<T> {
    private ElementoLE<T> primero;

    public ListaEnlazada() {
        this.primero = null;
    }

    public boolean isVacia() {
        return primero == null;
    }

    public void vaciar() {
        primero = null;
    }

    public void add(ElementoLE<T> el) {
        if (primero == null) {
            primero = el;
        } else {
            ElementoLE<T> actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(el);
        }
    }

    public void add(T data) {
        ElementoLE<T> nuevoElemento = new ElementoLE<>(data);
        add(nuevoElemento);
    }

    public void insert(ElementoLE<T> elemento, int posicion) {
        if (posicion < 0) {
            System.out.println("Posición inválida");
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
                System.out.println("Posición inválida");
            }
        }
    }
    public void insert(T data, int posicion) {
        ElementoLE<T> nuevoElemento = new ElementoLE<>(data);
        insert(nuevoElemento,posicion);
    }

    public int del(int posicion) {
        if (posicion < 0 || primero == null) {
            System.out.println("Posición inválida o lista vacía");
            return posicion;
        }
        if (posicion == 0) {
            primero = primero.getSiguiente();
        } else {
            ElementoLE<T> anterior = getElemento(posicion - 1);
            if (anterior != null && anterior.getSiguiente() != null) {
                anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
            } else {
                System.out.println("Posición inválida");
            }
        }
        return posicion;
    }

    public int length() {
        int contador = 0;
        ElementoLE<T> actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();

        }
        return contador;
    }

    public int getPosicion(ElementoLE<T> el) {
        int posicion = 0;
        ElementoLE<T> actual = primero;
        while (actual != null) {
            if (actual == el) {
                return posicion;
            }
            posicion++;
            actual = actual.getSiguiente();
        }
        return -1;
    }

    public ElementoLE<T> getUltimo() {
        ElementoLE<T> actual = primero;
        while (actual != null && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public ElementoLE<T> getSiguiente(ElementoLE<T> el) {
        return el.getSiguiente();
    }

    public ElementoLE<T> getElemento(int posicion) {
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
    }
}
