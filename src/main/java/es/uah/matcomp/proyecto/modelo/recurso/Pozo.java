package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Pozo extends Recurso {
    public Pozo(){
        super();
    }

    @Override
    public void aplicarEfecto(Individuo individuo){
        individuo.matar();
    }

}