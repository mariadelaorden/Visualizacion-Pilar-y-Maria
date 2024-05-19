
package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;

public class Biblioteca extends Recurso {
    @Override
    public void aplicarEfecto(Individuo ind) {
        // Lógica para reducir la vida del individuo
    }

    @Override
    public String toString() {
        return "B";
    }
}
