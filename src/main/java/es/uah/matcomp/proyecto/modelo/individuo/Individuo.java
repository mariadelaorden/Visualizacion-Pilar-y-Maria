package es.uah.matcomp.proyecto.modelo.individuo;

import es.uah.matcomp.proyecto.modelo.tablero.Celda;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;

import java.util.Random;

public class Individuo {

    private int posicionX;
    private int posicionY;
    private static int ultimoId = 0;
    private int id;
    private int generacion;
    private int vida;
    private double probreproduccion;
    private double probclonacion;
    private double probmuerte;
    private TipoIndividuo tipo;


    public Individuo(int generacion, int posicionX,int posicionY, int vida, double probReproduccion, double probClonacion, TipoIndividuo tipo) {
        this.id = ++ultimoId;  //Va aumentando
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.generacion = generacion;
        this.vida = vida;
        this.probreproduccion = probReproduccion;
        this.probclonacion = probClonacion;
        this.probmuerte = 1 - probReproduccion;
        this.tipo = tipo;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        posicionX = posicionX;
    }
    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        posicionY = posicionY;
    }

    public int getId() {
        return this.id;
    }

    public void setId() {
        Random random = new Random();
        this.id = random.nextInt(0, 100);  //Aleatorio
    }
    public int getGeneracion() {  //Dependerá del turno
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public TipoIndividuo getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoIndividuo tipo) {
        this.tipo = tipo;
    }

    public int getvida() {return vida;
    }
    public void setVida(int vida) {vida = this.vida;
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

    public void mover(Tablero tablero) {
        if (this.tipo == TipoIndividuo.BASICO) { //Movimiento aleatorio
            moverAleatorio(tablero);
        } else if (this.tipo == TipoIndividuo.NORMAL) {
            //Dependera tb de los recursos
        } else {
        }

    }
    public void matar() { //muerte instantanea
        setProbmuerte(1);
    }

    protected void moverAleatorio(Tablero tablero) {
        Random r = new Random();
        int movimiento = r.nextInt(1,8);
        try {
            switch (movimiento) {
                case 1:
                    cambiarPosicion(getPosicionX() + 1, getPosicionY(), tablero);
                    break;
                case 2:
                    cambiarPosicion(getPosicionX() + 1, getPosicionY() - 1, tablero);
                    break;
                case 3:
                    cambiarPosicion(getPosicionX(), getPosicionY() - 1, tablero);
                    break;
                case 4:
                    cambiarPosicion(getPosicionX() - 1, getPosicionY() - 1, tablero);
                    break;
                case 5:
                    cambiarPosicion(getPosicionX() - 1, getPosicionY(), tablero);
                    break;
                case 6:
                    cambiarPosicion(getPosicionX() - 1, getPosicionY() + 1, tablero);
                    break;
                case 7:
                    cambiarPosicion(getPosicionX(), getPosicionY() + 1, tablero);
                    break;
                case 8:
                    cambiarPosicion(getPosicionX() + 1, getPosicionY() + 1, tablero);
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            moverAleatorio(tablero);
        }
    }

    protected void cambiarPosicion (int nuevaX, int nuevaY, Tablero tablero) {
        Celda nuevaCasilla = tablero.getCelda(nuevaX, nuevaY);
        //Terminar
    }

}