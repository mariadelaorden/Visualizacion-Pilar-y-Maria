package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Biblioteca extends Recurso {
    private int aumentoProbabilidadClonacion;

    public Biblioteca(float aumentoProbabilidadClonacion) {
        super();
        this.aumentoProbabilidadClonacion = (int) aumentoProbabilidadClonacion;
    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        double probClonacionActual = individuo.getProbclonacion();
        probClonacionActual += aumentoProbabilidadClonacion;
        individuo.setProbreproduccion(probClonacionActual);

        TipoIndividuo tipoActual = individuo.getTipo();
        TipoIndividuo nuevoTipo = null;
        switch (tipoActual) {
            case BASICO:
                nuevoTipo = TipoIndividuo.NORMAL;
                break;
            case NORMAL:
                nuevoTipo = TipoIndividuo.AVANZADO;
                break;
            case AVANZADO:
                nuevoTipo = TipoIndividuo.AVANZADO; //nivel max
                break;
            default:
                //tipo de individuo no válido
                String mensajeError = "Tipo de individuo no válido: " + tipoActual;
                throw new IllegalArgumentException(mensajeError);
        }
        individuo.setTipo(nuevoTipo);
    }

}