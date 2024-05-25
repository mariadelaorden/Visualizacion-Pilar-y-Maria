package es.uah.matcomp.proyecto.controlador;

import com.google.gson.annotations.Expose;
import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;

public class PartidaDTO {
    @Expose
    private Tablero tablero;
    @Expose
    private PlantillaIndividuo individuoBasico;
    @Expose
    private PlantillaIndividuo individuoNormal;
    @Expose
    private PlantillaIndividuo individuoAvanzado;

    // Getters
    public Tablero getTablero() {
        return tablero;
    }

    public PlantillaIndividuo getIndividuoBasico() {
        return individuoBasico;
    }

    public PlantillaIndividuo getIndividuoNormal() {
        return individuoNormal;
    }

    public PlantillaIndividuo getIndividuoAvanzado() {
        return individuoAvanzado;
    }

    // Setters
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setIndividuoBasico(PlantillaIndividuo individuoBasico) {
        this.individuoBasico = individuoBasico;
    }

    public void setIndividuoNormal(PlantillaIndividuo individuoNormal) {
        this.individuoNormal = individuoNormal;
    }

    public void setIndividuoAvanzado(PlantillaIndividuo individuoAvanzado) {
        this.individuoAvanzado = individuoAvanzado;
    }
}
