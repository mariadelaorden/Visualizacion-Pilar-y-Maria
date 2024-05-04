package es.uah.matcomp.proyecto.javafx.visualizacion;

import es.uah.matcomp.proyecto.cod.Individuo;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

public class IndividuoController {

    public IndividuoController(){
    }

    public Individuo cargarIndividuo(int vidas, double probReproduccion, double probClonacion, String tipoIndividuo){

        // Crear el nuevo individuo
        Individuo individuo = new Individuo(vidas, probReproduccion, probClonacion, tipoIndividuo);
        return individuo;
    }
}
