package es.uah.matcomp.proyecto.estructurasdedatos.arbol;


public class Nodo {
    private Object data;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(Object data) {
        this.data = data;
        this.izquierda = null;
        this.derecha = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Nodo getIzquierda(){
        return this.izquierda;
    }
    public Nodo getDerecha(){
        return this.derecha;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }
    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}

