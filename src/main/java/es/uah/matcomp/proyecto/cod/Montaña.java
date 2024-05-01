package es.uah.matcomp.proyecto.cod;

public class Montaña extends Recursos {
    public Montaña() {
        super("Montaña");
    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        individuo.bajarvida(2);
    }
}