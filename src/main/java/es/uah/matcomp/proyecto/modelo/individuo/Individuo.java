
package es.uah.matcomp.proyecto.modelo.individuo;

public class Individuo extends PlantillaIndividuo{

    private static int siguienteID = 0;
    private final int id;
    private final int generacion;
    private TipoIndividuo tipo;

    // Constructor
    public Individuo(PlantillaIndividuo plantilla, int generacion, TipoIndividuo tipo) {
        super(plantilla);
        this.id = Individuo.siguienteID++; // Incrementar y asignar el ID
        this.generacion = generacion;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public int getGeneracion() {
        return generacion;
    }

    public TipoIndividuo getTipo() {
        return tipo;
    }

    public void setTipo(TipoIndividuo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "" + tipo.name().charAt(0);
    }
}