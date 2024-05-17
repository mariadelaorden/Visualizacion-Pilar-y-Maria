package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Tesoro extends Recurso {
    private double aumentoProbabilidadReproduccion;

    public Tesoro(double aumentoProbabilidadReproduccion) {
        super();
        this.aumentoProbabilidadReproduccion = aumentoProbabilidadReproduccion;

    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        double probReproduccionActual = individuo.getProbReproduccion();
        probReproduccionActual += aumentoProbabilidadReproduccion;
        individuo.setProbReproduccion(probReproduccionActual);
    }
}