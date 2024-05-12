package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Comida extends Recurso {
    public Comida() {
        super();
    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        individuo.subirvida(10);
    }

}