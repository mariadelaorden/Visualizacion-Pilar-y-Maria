package es.uah.matcomp.proyecto.cod;

public class Pozo extends Recursos{
    public Pozo(){
        super("Pozo");
    }

    @Override
    public void aplicarEfecto(Individuo individuo){
        individuo.matar();
    }

}