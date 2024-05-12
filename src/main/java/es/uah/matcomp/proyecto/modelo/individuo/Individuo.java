package es.uah.matcomp.proyecto.modelo.individuo;

import java.util.Random;

public class Individuo {
    private static int ultimoId = 0;
    private int id;
    private int generacion;
    private int vida;
    private double probreproduccion;
    private double probclonacion;
    private double probmuerte;
    private TipoIndividuo tipo;


    public Individuo(int generacion, int vida, double probReproduccion, double probClonacion, TipoIndividuo tipo) {
        this.id = ++ultimoId;
        this.generacion = generacion;
        this.vida = vida;
        this.probreproduccion = probReproduccion;
        this.probclonacion = probClonacion;
        this.probmuerte = 1 - probReproduccion;
        this.tipo = tipo;
    }

    public int getId() {
        return this.id;
    }

    public void setId() {
        Random random = new Random();
        this.id = random.nextInt(0, 100);  //Aleatorio
    }
    //public int getGeneracion(){}    Depende del turno
    //public void setGeneracion(){}

    public int getvida() {
        return this.vida;
    }

    public void setVida(int vida) {
        vida = this.vida;
    }

    public double getProbreproduccion() {
        return this.probreproduccion;
    }

    public void setProbreproduccion(double probreproduccion) {
        probreproduccion = this.probreproduccion;
    }

    public double getProbclonacion() {
        return this.probclonacion;
    }

    public void setProbclonacion(double probclonacion) {
        probclonacion = this.probclonacion;
    }

    public void setProbmuerte(double probmuerte) {
        probmuerte = this.probmuerte;
    }

    public void bajarvida(int numvidas) {
        int newvida = this.getvida() - numvidas;
        setVida(newvida);
    }

    public void subirvida(int numvidas) {
        int newvida = this.getvida() + numvidas;
        setVida(newvida);
    }

    public void bajarprobreproduccion(float prob) {
        float newprobreproduccion = (float) (this.getProbreproduccion() - prob);
        setProbreproduccion(newprobreproduccion);
    }

    public void bajarprobclonacion(float prob) {
        float newprobclonacion = (float) (this.getProbclonacion() - prob);
        setProbreproduccion(newprobclonacion);
    }

    public void subirprobreproduccion(float prob) {
        float newprobreproduccion = (float) (this.getProbreproduccion() + prob);
        setProbreproduccion(newprobreproduccion);
    }

    public void subirprobclonacion(float prob) {
        float newprobclonacion = (float) (this.getProbclonacion() + prob);
        setProbreproduccion(newprobclonacion);
    }

    public void mover() {
        if (this.tipo == TipoIndividuo.BASICO) {

        } else if (this.tipo == TipoIndividuo.NORMAL) {

        } else {
        }

    }

    public void matar() { //muerte instantanea
        setProbmuerte(1);
    }
}