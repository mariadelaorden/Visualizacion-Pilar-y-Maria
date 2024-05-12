package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Tesoro extends Recurso {
    private float aumentoProbabilidadReproduccion;

    public Tesoro(float aumentoProbabilidadReproduccion) {
        super();
        this.aumentoProbabilidadReproduccion = aumentoProbabilidadReproduccion;

    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        double probReproduccionActual = individuo.getProbreproduccion();
        probReproduccionActual += aumentoProbabilidadReproduccion;
        individuo.setProbreproduccion(probReproduccionActual);
    }
}