
package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;

public class Agua extends Recurso {

    private static double probAparicion;

    public Agua() {
        super();
    }

    @Override
    public void aplicarEfecto(Individuo ind) {
        // Lógica para reducir la vida del individuo
    }

    @Override
    public String toString() {
        return "A";
    }

    public static double getProbAparicion() {
        return probAparicion;
    }

    public static void setProbAparicion(double probAparicion) {
        Agua.probAparicion = probAparicion;
    }
}