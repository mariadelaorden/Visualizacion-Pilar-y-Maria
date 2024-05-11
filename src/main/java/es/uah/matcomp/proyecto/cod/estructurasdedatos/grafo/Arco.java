package es.uah.matcomp.proyecto.cod.estructurasdedatos.grafo;

public class Arco<T> {
    private NodoGrafo<T> inicio;
    private NodoGrafo<T> fin;
    private int peso;

    public Arco(NodoGrafo<T> inicio, NodoGrafo<T> fin, int peso){
        this.inicio= inicio;
        this.fin=fin;
        this.peso=peso;
    }

    public NodoGrafo<T> getInicio() {
        return inicio;
    }

    public void setInicio(NodoGrafo<T> inicio) {
        this.inicio = inicio;
    }

    public NodoGrafo<T> getFin() {
        return fin;
    }

    public void setFin(NodoGrafo<T> fin) {
        this.fin = fin;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}