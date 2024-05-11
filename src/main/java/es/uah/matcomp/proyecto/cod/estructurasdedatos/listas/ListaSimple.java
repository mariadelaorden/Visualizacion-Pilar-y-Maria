package es.uah.matcomp.proyecto.cod.estructurasdedatos.listas;

public class ListaSimple<N> {
    private ElementoLS[] datos;
    private int maximo;
    public ListaSimple(int maximo){
        this.maximo=maximo;
        datos=new ElementoLS[maximo];
    }
    public boolean isVacia(){
        for (int i=0;i<maximo-1;i++){
            if (datos[i]!=null){
                return false;
            }
        }
        return true;
    }
    public void vaciar(){
        if (!isVacia()){
            for (int i=0;i<maximo-1;i++){
                datos[i]=null;
            }
        }
    }
    private int add(ElementoLS el){
        for (int i=0;i<maximo-1;i++){
            if (datos[i]==null){   //Hay una posición libre
                datos[i]=el;
                return i;
            }
        }
        return -1; //indica que la lista está llena
    }
    public void add(String s){
        ElementoLS el=new ElementoLS(); //Creo nuevo elemento
        el.setData(s);
        add(el);  //Agrego
    }
    public void add(Object o){
        ElementoLS el=new ElementoLS(); //Creo nuevo elemento
        el.setData(o);
        add(el);  //Agrego
    }
    public void insert(Object o, int posicion) {
        if ((posicion >= 0) && (posicion < maximo-1)) {  //Posicion valida
            ElementoLS el=new ElementoLS(); //Creo nuevo elemento
            el.setData(o);
            datos[posicion] = el;  //Insertar elemento
        }
    }
    public void insert(String s, int posicion) {
        if((posicion > 0) && (posicion < maximo-1)) {  //Posicion valida
            ElementoLS el=new ElementoLS(); //Creo nuevo elemento
            el.setData(s);
            datos[posicion] = el;  //Insertar elemento
        }
    }
    public int del(int posicion) {
        if ((posicion > 0) && (posicion < maximo-1)) {  //Posicion valida
            datos[posicion] = null;
            return 1;
        }
        return -1;  //Indica error
    }
    public int getNumeroElementos() {
        int cont = 0;
        for (int i = 0; i < maximo-1; i++) {
            if (datos[i] != null) {
                cont++;
            }
        }
        return cont;
    }
    public int getPosicion(ElementoLS el) {
        ElementoLS actual = getPrimero();
        int posicion = 0;

        while (actual != null) {
            if (actual.getData().equals(el)) {
                return posicion; // Se encontró el elemento
            }
            actual = getSiguiente(actual); // Siguiente
            posicion++; // Aumenta la posición
        }
        return -1;  //Error
    }
    public ElementoLS getPrimero() {
        if (datos[0]!=null){
            return datos[0];
        }
        return null;//Lista vacia;
    }
    public ElementoLS getUltimo() {
        for (int i=maximo-1;i>0;i--){
            if (datos[i]!=null){
                return datos[i];
            }
        }
        return null;//Lista vacia
    }

    protected ElementoLS getSiguiente(ElementoLS el) {
        if (el != null) {
            ElementoLS siguiente = null;
            for (int i = 0; i < maximo; i++) {
                if (datos[i] == el) {
                    if (i + 1 < maximo) {
                        siguiente = datos[i + 1];
                    }
                    break; //
                }
            }
            return siguiente;
        }
        return null;
    }
    public ElementoLS getElemento(int posicion) {
        if ((posicion >= 0) && (posicion < maximo-1)) {
            return datos[posicion];
        }
        return null; //No hay
    }
}


