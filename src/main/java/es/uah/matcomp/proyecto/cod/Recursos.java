package es.uah.matcomp.proyecto.cod;

public abstract class Recursos {
    private String nombre;
    public Recursos (String nombre){
        this.nombre = nombre;
    }
    public String getNombre() {
        return  nombre;
    }

    public abstract void aplicarEfecto(Individuo individuo);

}