package es.uah.matcomp.proyecto.cod.grafo;

import es.uah.matcomp.proyecto.cod.listas.ElementoLS;
import es.uah.matcomp.proyecto.cod.listas.ListaEnlazada;


public class NodoGrafo<T> extends ElementoLS {
    private String nombre;
    ListaEnlazada<Arco<T>> arcosEntrada;
    ListaEnlazada<Arco<T>> arcosSalida;


    public NodoGrafo(String nombre){
        this.nombre = nombre;
        this.arcosEntrada = new ListaEnlazada<>();
        this.arcosSalida = new ListaEnlazada<>();
    }
    public String getData(){
        return this.nombre;
    }
    public void setData(String nombre){
        this.nombre=nombre;
    }

    public String toString(){
        return ("Nodo:"+nombre);
    }

    public void addArcoSalida(Arco<T> arco) {
        this.arcosSalida.add(arco);
    }

    public void addArcoEntrada(Arco<T> arco) {
        this.arcosEntrada.add(arco);
    }

}