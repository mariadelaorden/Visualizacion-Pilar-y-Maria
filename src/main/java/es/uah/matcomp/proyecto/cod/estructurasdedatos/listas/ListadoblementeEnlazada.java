package es.uah.matcomp.proyecto.cod.estructurasdedatos.listas;

public class ListadoblementeEnlazada {
    private ElementoLDE primero;
    private ElementoLDE ultimo;

    public boolean isVacia() {
        return primero == null;  //No hay cadena
    }

    public void vaciar() {
        primero = null;
        ultimo = null;    //Corto la cadena
    }

    private int add(ElementoLDE el) {
        if (primero== null) {
            primero = el;
        }
        else {
            ultimo.insertarmeEn(el);
        }
        ultimo=el;
        ElementoLDE posicion= primero;
        int contador=0;
        while(posicion.getSiguiente()!=null){
            contador++;
            posicion=posicion.getSiguiente();
        }
        return contador;
    }

    public void add(String s) {
        ElementoLDE el = new ElementoLDE(s);
        this.add(el);
    }

    public void add(Object o) {
        ElementoLDE el = new ElementoLDE(o);
        this.add(el);
    }

    public void insert(String s, int posicion) {
        ElementoLDE el = new ElementoLDE(s);

        if (posicion >= 0) {
            if (posicion == 0 || isVacia()) { //Se inserta el primero o esta vacio
                el.insertarmeEn(primero);
                if (ultimo == null) {
                    ultimo = el;
                }
            } else {
                ElementoLDE actual= primero;
                int contador=0;
                while(actual!=null && contador<posicion){  //Encontrar la poscion en la que insertar
                    actual=actual.getSiguiente();
                    contador ++;
                }
                if (actual!= null) {
                    actual.insertarmeEn(el);
                }
                else {
                    ultimo.insertarmeEn(el); // Insertar al final de la lista
                    ultimo = el;
                }

            }
        }
    }
    public void insert(Object o, int posicion) {
        ElementoLDE el = new ElementoLDE(o);

        if (posicion >= 0) {
            if (posicion == 0 || isVacia()) { //Se inserta el primero o esta vacio
                el.insertarmeEn(primero);
                if (ultimo == null) {
                    ultimo = el;
                }
            } else {
                ElementoLDE actual= primero;
                int contador=0;
                while(actual!=null && contador<posicion){  //Encontrar la poscion en la que insertar
                    actual=actual.getSiguiente();
                    contador ++;
                }
                if (actual!= null) {
                    actual.insertarmeEn(el);
                }
                else {
                    ultimo.insertarmeEn(el); // Insertar al final de la lista
                    ultimo = el;
                }

            }
        }
    }

    public int del(int posicion){
        if (!isVacia() && posicion >= 0) {
            if (posicion == 0) {
                primero = primero.getSiguiente();
                if (primero == null) {
                    ultimo = null;
                }
                return 1;
            }
            else {
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
                    return 1;
                }
            }
        }
        return -1; //No se ha podido
    }

    public int getNumeroElementos(){
        int contador = 0;
        ElementoLDE actual = primero;
        while(actual!=null){
            contador++;
            actual=actual.getSiguiente();
        }
        return contador;
    }
    public int getPosicion(ElementoLDE el){
        int posicion=0;
        ElementoLDE actual=primero;

        while (actual !=null){
            if(actual.getData()==el){
                return posicion;
            }
            actual=actual.getSiguiente();
            posicion++;
        }
        return -1; //No hay
    }

    public ElementoLDE getPrimero(){
        return this.primero;
    }
    public ElementoLDE getUltimo(){
        return this.ultimo;
    }

    public ElementoLDE getAnterior(ElementoLDE el){
        if (el != null) {
            return el.getAnterior();
        }
        return null;
    }

    public ElementoLDE getSiguiente(ElementoLDE el){
        if (el != null) {
            return el.getSiguiente();
        }
        return null;
    }
    public ElementoLDE getElemento(int posicion){
        ElementoLDE actual = primero;
        int contador = 0;
        while (actual != null && contador < posicion) {
            actual = actual.getSiguiente();
            contador++;
        }
        return actual;
    }

}
