package es.uah.matcomp.proyecto.cod.estructurasdedatos.listas;

public class ElementoLE<T> {
    private T data;
    private ElementoLE<T> siguiente;

    public ElementoLE(T data) {
        this.data = data;
        this.siguiente = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ElementoLE<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ElementoLE<T> siguiente) {
        this.siguiente = siguiente;
    }
}
