package es.uah.matcomp.proyecto.cod;

public class Tablero {
    private int n;
    private int m;
    private ListaSimple listaceldas;

    public Tablero(int n, int m) {
        this.n = n;
        this.m = m;
        this.listaceldas = new ListaSimple(n * m);
        crearTablero();
    }

    private void crearTablero() {
        for (int i = 0; i < n * m; i++) {
            listaceldas.add(new Celda());
        }
    }

    public int getN(){
        return this.n;
    }
    public void setN(int n){
        n=this.n;
    }
    public int getM(){
        return this.m;
    }
    public void setM(int m){
        m=this.m;
    }
}
