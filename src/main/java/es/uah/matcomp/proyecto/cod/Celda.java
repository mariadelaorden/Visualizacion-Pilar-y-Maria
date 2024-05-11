package es.uah.matcomp.proyecto.cod;

import es.uah.matcomp.proyecto.cod.listas.ListaSimple;

public class Celda {
    private ListaSimple individuos;
    private ListaSimple recursos;

    public Celda() {
        this.individuos = new ListaSimple(3); // Máximo 3 individuos por celda
        this.recursos = new ListaSimple(3); // Máximo 3 recursos por celda
    }

}