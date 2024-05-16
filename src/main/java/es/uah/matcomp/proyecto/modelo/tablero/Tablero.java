package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;

public class Tablero {

    private int ancho;
    private int largo;
    private ListaSimple listaceldas;

    public Tablero(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        this.listaceldas = new ListaSimple(ancho * largo);
    }

    // Resetear el tablero (llenarlo de celdas vacias)
    public void resetearTablero() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                Celda celda = new Celda();
                listaceldas.add(celda);
            }
        }
    }

    public Celda getCelda(int x, int y) {
        return (Celda) listaceldas.getElemento(x * largo + y).getData();
    }

    public void setCelda(int x, int y, Celda celda) {
        listaceldas.insert(celda, x * largo + y);
    }

    // Imprimir las celdas del tablero que no estén vacías
    public void imprimirTablero() {
        System.out.println("Tablero:");
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                Celda celda = getCelda(i, j);
                if (!celda.isEmpty()) {
                    System.out.println("Celda (" + i + ", " + j + ")");
                }
            }
        }
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }
}