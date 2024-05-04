package es.uah.matcomp.proyecto.cod;

public class Tesoro extends Recursos {
    private float aumentoProbabilidadReproduccion;

    public Tesoro(float aumentoProbabilidadReproduccion) {
        super("Tesoro");
        this.aumentoProbabilidadReproduccion = aumentoProbabilidadReproduccion;

    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        double probReproduccionActual = individuo.getProbreproduccion();
        probReproduccionActual += aumentoProbabilidadReproduccion;
        individuo.setProbreproduccion(probReproduccionActual);
    }
}