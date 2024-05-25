package es.uah.matcomp.proyecto.modelo.individuo;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;

public class GenealogyNode {
    private final Individuo individuo;
    private final ListaSimple hijos;

    public GenealogyNode(Individuo individuo) {
        this.individuo = individuo;
        this.hijos = new ListaSimple(10); // Tamaño máximo de la lista de hijos
    }

    public Individuo getIndividuo() {
        return individuo;
    }

    public ListaSimple getHijos() {
        return hijos;
    }

    public void addHijo(GenealogyNode hijo) {
        ElementoLS elementoHijo = new ElementoLS();
        elementoHijo.setData(hijo);
        this.hijos.add(elementoHijo);
    }
}
