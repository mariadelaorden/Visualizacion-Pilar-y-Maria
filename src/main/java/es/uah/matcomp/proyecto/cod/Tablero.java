package es.uah.matcomp.proyecto.cod;

import es.uah.matcomp.proyecto.cod.listas.ListaSimple;

public class Tablero {
    private int ancho;
    private int largo;
    private ListaSimple listaceldas;

    public Tablero(int ancho, int largo) {
        this.ancho = ancho;
        this.ancho = largo;
        this.listaceldas = new ListaSimple(ancho * largo);
        crearTablero();
    }

    private void crearTablero() {
        for (int i = 0; i < ancho * largo; i++) {
            listaceldas.add(new Celda());
        }
    }

    public int getAncho(){
        return this.ancho;
    }
    public void setAncho(int n){
        n=this.ancho;
    }
    public int getLargo(){
        return this.largo;
    }
    public void setLargo(int m){
        m=this.largo;
    }
}

