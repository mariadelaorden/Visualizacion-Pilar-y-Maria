//package es.uah.matcomp.proyecto.modelo;
//
//import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaEnlazada;
//
//public class ParameterDataModel {
//
//    // listas
//    private ListaEnlazada individuos = new ListaEnlazada<>();
//    private ListaEnlazada recursos = new ListaEnlazada<>();
//
//    // datos
//    private Boolean isPausa = false;
//
//    // datos tablero
//    private int IndividuosMaximos = 3;
//    private int RecursosMaximos = 3;
//    private int ancho;
//    private int largo;
//
//    // datos individuo
//    private int vidas;
//    private double probReproduccion;
//    private double probClonacion;
//    private String seleccionTipo;
//
//    // datos recurso
//    private double probAparicion;
//    private double TiempoAparicion;
//    private String TipoRecurso;
//
//    public ParameterDataModel(int ancho, int largo, int vidas, double probReproduccion, double probClonacion, String seleccionTipo, double TiempoAparcion, double probAparicion, String TipoRecurso) {
//        this.ancho = ancho;
//        this.largo = largo;
//        this.vidas = vidas;
//        this.probReproduccion = probReproduccion;
//        this.probClonacion = probClonacion;
//        this.seleccionTipo = seleccionTipo;
//        this.TiempoAparicion = TiempoAparcion;
//        this.probAparicion = probAparicion;
//        this.TipoRecurso = TipoRecurso;
//    }
//
//    public ListaEnlazada getIndividuos() {
//        return individuos;
//    }
//
//    public void setIndividuos(ListaEnlazada individuos) {
//        this.individuos = individuos;
//    }
//
//    public ListaEnlazada getRecursos() {
//        return recursos;
//    }
//
//    public void setRecursos(ListaEnlazada recursos) {
//        this.recursos = recursos;
//    }
//
//    public int getIndividuosMaximos() {
//        return IndividuosMaximos;
//    }
//
//    public void setIndividuosMaximos(int individuosMaximos) {
//        IndividuosMaximos = individuosMaximos;
//    }
//
//    public int getRecursosMaximos() {
//        return RecursosMaximos;
//    }
//
//    public void setRecursosMaximos(int recursosMaximos) {
//        RecursosMaximos = recursosMaximos;
//    }
//
//    public int getAncho() {
//        return ancho;
//    }
//
//    public void setAncho(int ancho) {
//        this.ancho = ancho;
//    }
//
//    public int getLargo() {
//        return largo;
//    }
//
//    public void setLargo(int largo) {
//        this.largo = largo;
//    }
//
//    public int getVidas() {
//        return vidas;
//    }
//
//    public void setVidas(int vidas) {
//        this.vidas = vidas;
//    }
//
//    public double getProbReproduccion() {
//        return probReproduccion;
//    }
//
//    public void setProbReproduccion(double probReproduccion) {
//        this.probReproduccion = probReproduccion;
//    }
//
//    public double getProbClonacion() {
//        return probClonacion;
//    }
//
//    public void setProbClonacion(double probClonacion) {
//        this.probClonacion = probClonacion;
//    }
//
//    public String getSeleccionTipo() {
//        return seleccionTipo;
//    }
//
//    public void setSeleccionTipo(String seleccionTipo) {
//        this.seleccionTipo = seleccionTipo;
//    }
//
//    public double getProbAparicion() {
//        return probAparicion;
//    }
//
//    public void setProbAparicion(double probAparicion) {
//        this.probAparicion = probAparicion;
//    }
//
//    public double getTiempoAparicion() {
//        return TiempoAparicion;
//    }
//
//    public void setTiempoAparicion(double tiempoAparicion) {
//        TiempoAparicion = tiempoAparicion;
//    }
//    public String getTipoRecurso() {
//        return TipoRecurso;
//    }
//
//    public void setTipoRecurso(String tipoRecurso) {
//        TipoRecurso = tipoRecurso;
//    }
//}
