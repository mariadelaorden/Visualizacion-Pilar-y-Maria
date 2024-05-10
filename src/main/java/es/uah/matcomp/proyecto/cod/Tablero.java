package es.uah.matcomp.proyecto.cod;

public class Tablero {
    private int ancho;
    private int largo;
    private ListaSimple<Celda> celdas;

    public Tablero(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        this.celdas = new ListaSimple<Celda>(ancho * largo);
        crearTablero();
    }

    private void crearTablero() {
        for (int i = 0; i < ancho * largo; i++) {
            celdas.add(new Celda());
        }
    }
/**
    public Celda getElemento(int i, int j){
        //return celdas.getElemento(i*ancho+j).getData();
    }
 **/


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

