package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;

public abstract class Recurso {
    protected int vida;
    protected int posicionX;
    protected int posicionY;

    //Para saber el tipo utilizar instaceof

    public abstract void aplicarEfecto(Individuo individuo);

}