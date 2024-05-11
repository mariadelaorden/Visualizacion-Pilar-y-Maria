package es.uah.matcomp.proyecto.cod.estructurasdedatos.grafo;



import es.uah.matcomp.proyecto.cod.estructurasdedatos.listas.ListaSimple;

public class Camino<T> {
    private ListaSimple camino;
    private double peso;

    public Camino(ListaSimple camino, double peso) {
        this.camino = camino;
        this.peso=peso;
    }

    public ListaSimple<NodoGrafo<T>> getCamino() {
        return camino;
    }

    public double getCoste() {
        return peso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < camino.getNumeroElementos(); i++) {
            sb.append(camino.getElemento(i).getData());
            if (i < camino.getNumeroElementos() - 1) {
                sb.append(" -> ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
