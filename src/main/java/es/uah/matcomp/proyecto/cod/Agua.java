package es.uah.matcomp.proyecto.cod;

public class Agua extends Recursos{
    public Agua(){
        super("Agua");
    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        individuo.subirvida(2);
    }
}