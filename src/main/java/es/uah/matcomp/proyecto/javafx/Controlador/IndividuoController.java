package es.uah.matcomp.proyecto.javafx.Controlador;

import es.uah.matcomp.proyecto.cod.Individuo;

public class IndividuoController {

    public IndividuoController(){
    }

    public Individuo cargarIndividuo(int vidas, double probReproduccion, double probClonacion, String tipoIndividuo){

        // Crear el nuevo individuo
        Individuo individuo = new Individuo(vidas, probReproduccion, probClonacion, tipoIndividuo);
        return individuo;
    }
}
