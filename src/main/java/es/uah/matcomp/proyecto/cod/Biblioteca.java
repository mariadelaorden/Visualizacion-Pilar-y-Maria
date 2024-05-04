package es.uah.matcomp.proyecto.cod;

public class Biblioteca extends Recursos {
    private int aumentoProbabilidadClonacion;
    private int aumentoTipoIndividuo;

    public Biblioteca(float aumentoProbabilidadClonacion, int aumentoTipoIndividuo) {
        super("Biblioteca");
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