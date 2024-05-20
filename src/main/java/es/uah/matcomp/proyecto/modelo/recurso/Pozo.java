
package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;

public class Pozo extends Recurso {

    private static double probAparicion;

    @Override
    public void aplicarEfecto(Individuo ind) {
        // Lógica para reducir la vida del individuo
    }

    @Override
    public String toString() {
        return "P";
    }

    public static double getProbAparicion() {
        return probAparicion;
    }

    public static void setProbAparicion(double probAparicion) {
        Pozo.probAparicion = probAparicion;
    }
}