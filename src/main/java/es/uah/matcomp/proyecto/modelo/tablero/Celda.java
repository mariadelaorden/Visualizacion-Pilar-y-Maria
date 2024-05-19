
package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Celda {
    private ListaSimple individuos;
    private ListaSimple recursos;
    private int maxIndividuals = 3;
    private int maxResources = 3;

    public Celda() {
        this.individuos = new ListaSimple(maxIndividuals); // Máximo 3 individuos por celda
        this.recursos = new ListaSimple(maxResources); // Máximo 3 recursos por celda
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

    @Override
    public String toString() {
        String resString = "";

        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            resString += individuos.getElemento(i).getData().toString();
        }

        resString += "\n";

        for (int i = 0; i < recursos.getNumeroElementos(); i++) {
            resString += recursos.getElemento(i).getData().toString();
        }

        return resString;
    }
}
