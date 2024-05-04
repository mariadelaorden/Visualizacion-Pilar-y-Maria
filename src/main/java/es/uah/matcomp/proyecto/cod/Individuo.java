package es.uah.matcomp.proyecto.cod;

import java.util.Random;

public class Individuo {
    private int id;  //Identificacion del individuo
    private int generacion; //Turno en el que se creo
    private int vida; //Turnos de vidas que le quedan
    private double probreproduccion; //Probabilidad de reproduccion
    private double probclonacion; //Probabilidad de clonacion
    private double probmuerte= 1-probreproduccion; //Probabilidad de muerte
    private String tipo; //Tipo de individuo

    public Individuo(int vida, double probreproduccion, double probclonacion, String tipo){
        this.vida= vida;
        this.probreproduccion= probreproduccion;
        this.probclonacion= probreproduccion;
        this.tipo= tipo;
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
    public int getTipo(){
        if(tipo=="basico"){
            return 1;
        }
        if (tipo=="normal"){
            return 2;
        }
        if(tipo=="avanzado"){
            return 3;
        }
        return 0; //Error
    }
    public void setTipo(int tipo){
        if (tipo==1){
            this.tipo="basico";
        }
        if(tipo==2){
            this.tipo="normal";
        }
        if(tipo==3){
            this.tipo="avanzado";
        }
    }
    public void setTipo(String tipo){
        if (tipo=="basico" || tipo=="normal"|| tipo=="avanzado"){
            this.tipo=tipo;
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
    public void mover(){  //En funcion del tablero y recursos
        if (getTipo()==1){}
        else if(getTipo()==2){}
        else if(getTipo()==3){}
    }
    public void subirtipo(){
        if (getTipo()==1){
            setTipo(2);  //Tipo normal
        }
        else if(getTipo()==2){
            setTipo(3); //Tipo avanzado
        }
        else if (getTipo()==3){
            setTipo(3); //Tipo maximo
        }
    }

    public void matar(){ //muerte instantanea
        setProbmuerte(1);
    }
}