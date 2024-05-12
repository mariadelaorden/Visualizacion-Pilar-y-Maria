package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Montaña extends Recurso {
    public Montaña() {
        super();
    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        individuo.bajarvida(2);
    }
}