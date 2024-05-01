package es.uah.matcomp.proyecto.cod;

public class Comida extends Recursos {
    public Comida() {
        super("Comida");
    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        individuo.subirvida(10);
    }

}