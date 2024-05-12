package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.recurso.Recurso;

public class Biblioteca extends Recurso {
    private int aumentoProbabilidadClonacion;
    private int aumentoTipoIndividuo;

    public Biblioteca(float aumentoProbabilidadClonacion, int aumentoTipoIndividuo) {
        super();
        this.aumentoProbabilidadClonacion = (int) aumentoProbabilidadClonacion;
        this.aumentoTipoIndividuo = aumentoTipoIndividuo;
    }

    @Override
    public void aplicarEfecto(Individuo individuo) {
        double probClonacionActual = individuo.getProbclonacion();
        probClonacionActual += aumentoProbabilidadClonacion;
        individuo.setProbreproduccion(probClonacionActual);

        /**

        int tipoIndividuoActual = Integer.parseInt(individuo.getTipo());
        tipoIndividuoActual += aumentoTipoIndividuo;
        individuo.setTipo(tipoIndividuoActual);
         **/
    }

}