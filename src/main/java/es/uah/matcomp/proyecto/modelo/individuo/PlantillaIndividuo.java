package es.uah.matcomp.proyecto.modelo.individuo;

public class PlantillaIndividuo {
    protected static double probMejora = 0.0;

    protected int vida;
    protected double probReproduccion;
    protected double probClonacion;
    protected double probMuerte;

    public PlantillaIndividuo() {
        this.vida = 0;
        this.probReproduccion = 0.0;
        this.probClonacion = 0.0;
        this.probMuerte = 0.0;
    }


    public PlantillaIndividuo(PlantillaIndividuo plantilla) {
        this.vida = plantilla.vida;
        this.probReproduccion = plantilla.probReproduccion;
        this.probClonacion = plantilla.probClonacion;
        this.probMuerte = plantilla.probMuerte;
    }

    public static void setProbMejora(double porbMejora) {
        PlantillaIndividuo.probMejora = Math.min(Math.max(0.0, porbMejora), 1.0);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public double getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(double probReproduccion) {
        this.probReproduccion = Math.min(Math.max(0.0, probReproduccion), 1.0);
    }

    public double getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(double probClonacion) {
        this.probClonacion = Math.min(Math.max(0.0, probClonacion), 1.0);
    }

    public double getProbMuerte() {
        return probMuerte;
    }

    public void setProbMuerte(double probMuerte) {
        this.probMuerte = Math.min(Math.max(0.0, probMuerte), 1.0);
    }
}