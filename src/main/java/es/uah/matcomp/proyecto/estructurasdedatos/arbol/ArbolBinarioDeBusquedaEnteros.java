package es.uah.matcomp.proyecto.estructurasdedatos.arbol;


public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda{
    public ArbolBinarioDeBusquedaEnteros(){
        super();
    }
    public int getSuma(){
        return suma(this.getRaiz());
    }
    private int suma(Nodo nodo){  //Metodo recursivo
        if (nodo==null){
            return 0;
        }
        int izq = suma(nodo.getIzquierda());
        int dcha = suma(nodo.getDerecha());
        int sumatotal = (int) nodo.getData() + izq + dcha;
        return sumatotal;
    }
}
