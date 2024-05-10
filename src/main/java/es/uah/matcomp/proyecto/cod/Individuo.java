package es.uah.matcomp.proyecto.cod;

import java.util.Random;

import java.util.Random;

public class Individuo {
    private int id;
    private int generacion;
    private int vida;
    private double probreproduccion;
    private double probclonacion;
    private double probmuerte;
    private TipoIndividuo tipo;

    public enum TipoIndividuo {
        BASICO,
        NORMAL,
        AVANZADO
    }

    public Individuo(int vida, double probReproduccion, double probClonacion, TipoIndividuo tipo) {
        this.vida = vida;
        this.probreproduccion = probReproduccion;
        this.probclonacion = probClonacion;
        this.probmuerte = 1 - probReproduccion;
        this.tipo = tipo;
    }

    public int getId(){
        return this.id;
    }
    public void setId(){
        Random random = new Random();
        this.id =  random.nextInt(0, 100);  //Aleatorio
    }
    //public int getGeneracion(){}    Depende del turno
    //public void setGeneracion(){}

    public int getvida(){
        return this.vida;
    }
    public void setVida(int vida){
        vida= this.vida;
    }
    public double getProbreproduccion(){
        return this.probreproduccion;
    }
    public void setProbreproduccion(double probreproduccion){
        probreproduccion= this.probreproduccion;
    }
    public double getProbclonacion(){
        return this.probclonacion;
    }
    public void setProbclonacion(double probclonacion){
        probclonacion= this.probclonacion;
    }

    public void setProbmuerte(double probmuerte){
        probmuerte=this.probmuerte;
    }
    public int getTipo() {
        if (tipo.equals(TipoIndividuo.BASICO)) {
            return 1;
        }
        if (tipo.equals(TipoIndividuo.NORMAL)) {
            return 2;
        }
        if (tipo.equals(TipoIndividuo.AVANZADO)) {
            return 3;
        }
        return 0; // Error
    }

    public void setTipo(int tipo) {
        if (tipo == 1) {
            this.tipo = TipoIndividuo.BASICO;
        } else if (tipo == 2) {
            this.tipo = TipoIndividuo.NORMAL;
        } else if (tipo == 3) {
            this.tipo = TipoIndividuo.AVANZADO;
        } else {
            System.out.println("Tipo no válido");
        }
    }

    public void setTipo(String tipo){
        if (tipo=="basico" || tipo=="normal"|| tipo=="avanzado"){
            this.tipo= TipoIndividuo.valueOf(tipo);
        }
        else{
            System.out.println("Tipo no valido");  //log??
        }
    }

    public void bajarvida(int numvidas){
        int newvida= this.getvida()-numvidas;
        setVida(newvida);
    }
    public void subirvida(int numvidas){
        int newvida= this.getvida()+numvidas;
        setVida(newvida);
    }
    public void bajarprobreproduccion(float prob){
        float newprobreproduccion = (float) (this.getProbreproduccion()-prob);
        setProbreproduccion(newprobreproduccion);
    }
    public void bajarprobclonacion(float prob){
        float newprobclonacion = (float) (this.getProbclonacion()-prob);
        setProbreproduccion(newprobclonacion);
    }
    public void subirprobreproduccion(float prob){
        float newprobreproduccion = (float) (this.getProbreproduccion()+prob);
        setProbreproduccion(newprobreproduccion);
    }
    public void subirprobclonacion(float prob){
        float newprobclonacion = (float) (this.getProbclonacion()+prob);
        setProbreproduccion(newprobclonacion);
    }
    public void mover() {
        if (getTipo() == 1) {

        } else if (getTipo() == 2) {

        } else if (getTipo() == 3) {

        } else {
            System.out.println("Tipo de individuo no válido: " + getTipo());

        }
    }


    public void subirTipo() {
        if (tipo.equals("basico")) {
            setTipo(2);  // Tipo normal
        } else if (tipo.equals("normal")) {
            setTipo(3);  // Tipo avanzado
        } else if (tipo.equals("avanzado")) {
            // No hay un tipo "más alto" que "avanzado", así que no se hace nada
            System.out.println("El individuo ya está en el tipo más alto.");
        } else {
            System.out.println("Tipo no válido");
        }
    }


    public void matar(){ //muerte instantanea
        setProbmuerte(1);
    }
}