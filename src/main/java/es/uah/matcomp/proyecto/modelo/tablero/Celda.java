package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Celda {
    private ListaSimple individuos;
    private ListaSimple recursos;
    private int maxIndividuos = 3;
    private int maxRecursos = 3;

    public Celda() {
        this.individuos = new ListaSimple(this.maxIndividuos); // Máximo 3 individuos por celda
        this.recursos = new ListaSimple(this.maxRecursos); // Máximo 3 recursos por celda
    }

    public ListaSimple getIndividuos() {
        return individuos;
    }

    public ListaSimple getRecursos() {
        return recursos;
    }

    public void addIndividuo(Individuo individuo) {
        individuos.add(individuo);
    }

    public void addRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    public boolean isEmpty() {
        return individuos.isVacia() && recursos.isVacia();
    }

}