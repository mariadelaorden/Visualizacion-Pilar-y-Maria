package es.uah.matcomp.proyecto.cod.grafo;


import es.uah.matcomp.proyecto.cod.ElementoLE;
import es.uah.matcomp.proyecto.cod.ListaEnlazada;
import es.uah.matcomp.proyecto.cod.ListaSimple;


public class Grafo<T> {
    private ListaSimple<NodoGrafo<T>> nodos;
    private ListaEnlazada<Arco<T>> arcos;

    public Grafo(int maxnodos) {
        this.nodos = new ListaSimple<>(maxnodos);
        this.arcos = new ListaEnlazada<>();
    }

    public void addNodo(NodoGrafo<T> nodo) {
        this.nodos.add(nodo);
    }

    public void addArco(Arco<T> arco) {
        this.arcos.add(arco);
        arco.getInicio().addArcoSalida(arco);
        arco.getFin().addArcoEntrada(arco);
    }

    public void borrarNodo(NodoGrafo<T> nodo) {
        nodos.del(nodos.getPosicion((NodoGrafo<NodoGrafo<T>>) nodo));
        //Eliminar arcos conectados
        for (int i = 0; i < arcos.getElemento(); i++) {
            Arco<T> arco = (Arco<T>) arcos.getElemento(i).getData();
            if (arco.getInicio().equals(nodo) || arco.getFin().equals(nodo)) {
                arcos.del(i);
                i--; //Se elimina un elemento
            }
        }
    }

    public void borrarArco(NodoGrafo<T> inicio, NodoGrafo<T> fin) {
        for (int i = 0; i < arcos.getNumeroElementos(); i++) {
            Arco<T> arco = (Arco<T>) arcos.getElemento(i).getData();
            if (arco.getInicio().equals(inicio) && arco.getFin().equals(fin)) {
                arcos.del(i);
                return;
            }
        }
    }

    public Arco<T> buscarArco(NodoGrafo<T> inicio, NodoGrafo<T> fin) {
        for (int i = 0; i < arcos.getNumeroElementos(); i++) {
            ElementoLE elemento = arcos.getElemento(i);  //Selecciono el arco de la lista
            if (elemento.getData() instanceof Arco) {  //Verifica que es un arco
                Arco<T> arco = (Arco<T>) elemento.getData();   //Extrae los datos
                if (arco.getInicio().equals(inicio) && arco.getFin().equals(fin)) {
                    return arco;
                }
            }
        }
        return null; //No encontrado
    }

    public NodoGrafo<T> buscarNodo(String nombre) {
        for (int i = 0; i < nodos.getNumeroElementos(); i++) {
            NodoGrafo<T> nodo = (NodoGrafo<T>) nodos.getElemento(i).getData();
            if (nodo.getData().equals(nombre)) {
                return nodo;
            }
        }
        return null; // No encontrado
    }


    public Mapa<NodoGrafo<T>, Camino<T>> dijkstra(NodoGrafo<T> origen) {  //Caminos minimos

        //Preparamos las variables.
        Mapa<NodoGrafo<T>, Double> distancias = new Mapa<>();   // Mantiene las distancias mínimas conseguidas a cada vértice.
        Cola<NodoGrafo<T>> colaPendientes = new Cola<>();  // Mantiene cuáles son los siguientes vértices a calcular.
        Mapa<NodoGrafo<T>, NodoGrafo<T>> verticesAnteriores = new Mapa<>(); //Guarda el rastro del camino para recalcularlo después.

        this.dijkstra_init(origen, distancias, colaPendientes, verticesAnteriores);  //Inicialización
        this.dijkstra_calcula(distancias, colaPendientes, verticesAnteriores);      //Cálculo
        return this.dijkstra_procesaResultados(distancias, verticesAnteriores);    //Procesamiento de resultados
    }

    protected void dijkstra_init(NodoGrafo<T> origen, Mapa<NodoGrafo<T>, Double> distancias, Cola<NodoGrafo<T>> colaPendientes, Mapa<NodoGrafo<T>, NodoGrafo<T>> verticesAnteriores) {
        for (int i = 0; i < nodos.getNumeroElementos(); i++) {
            NodoGrafo<T> vertice = (NodoGrafo<T>) nodos.getElemento(i).getData();
            distancias.put(vertice, Double.MAX_VALUE);  //Se incializa como valores grandes
        }

        distancias.put(origen, 0.0);  // Distancia del nodo origen
        colaPendientes.add(origen);
    }

    protected void dijkstra_calcula(Mapa<NodoGrafo<T>, Double> distancias, Cola<NodoGrafo<T>> colaPendientes, Mapa<NodoGrafo<T>, NodoGrafo<T>> verticesAnteriores) {
        while (!colaPendientes.isVacia()) {
            NodoGrafo<T> verticeActual = colaPendientes.pull();

            for (int i = 0; i < verticeActual.arcosSalida.getElemento(); i++) {
                Arco<T> arco = (Arco<T>) verticeActual.arcosSalida.getElemento(i).getData();  //Exploramos los arcos de salido
                NodoGrafo<T> verticeVecino = arco.getFin();  //Vertices de salida del arco

                double nuevoCalculoDeDistancia = arco.getPeso();

                if (nuevoCalculoDeDistancia < distancias.get(verticeVecino)) {  //comprobar si la distancia es mejor que la anterior
                    distancias.put(verticeVecino, nuevoCalculoDeDistancia);  //guardar distancia
                    verticesAnteriores.put(verticeVecino, verticeActual);   //guardar nuevo vertice anterior
                    colaPendientes.add(verticeVecino);  //Añadir nuevo vertice
                }
            }
        }
    }

    protected Mapa<NodoGrafo<T>, Camino<T>> dijkstra_procesaResultados(Mapa<NodoGrafo<T>, Double> distancias, Mapa<NodoGrafo<T>, NodoGrafo<T>> verticesAnteriores) {
        Mapa<NodoGrafo<T>, Camino<T>> caminos = new Mapa<>();
        for (int i = 0; i < nodos.getNumeroElementos(); i++) {
            NodoGrafo<T> verticeDestino = (NodoGrafo<T>) nodos.getElemento(i).getData();
            ListaSimple<NodoGrafo<T>> caminoCalculado = new ListaSimple<>(1000);
            NodoGrafo<T> v = verticeDestino;
            while (v != null) {
                caminoCalculado.add(v);
                v = verticesAnteriores.get(v);
            }
            // Invertimos el camino
            ListaSimple<NodoGrafo<T>> caminoInvertido = new ListaSimple<>(1000);
            for (int j = caminoCalculado.getNumeroElementos() - 1; j >= 0; j--) {
                caminoInvertido.add(caminoCalculado.getElemento(j).getData());
            }
            caminos.put(verticeDestino, new Camino<>(caminoInvertido, distancias.get(verticeDestino)));
        }
        return caminos;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph Grafo {\n");

        // Nodos
        for (int i = 0; i < nodos.getNumeroElementos(); i++) {
            NodoGrafo<T> nodo = (NodoGrafo<T>) nodos.getElemento(i).getData();
            sb.append("\t").append(nodo.getData()).append(";\n");
        }

        // Arcos
        Mapa<Arco<T>, Boolean> arcosProcesados = new Mapa<>(); // Para evitar duplicados
        for (int i = 0; i < arcos.getElemento(); i++) {
            Arco<T> arco = (Arco<T>) arcos.getElemento(i).getData();
            if (!arcosProcesados.containsKey(arco)) {
                arcosProcesados.put(arco, true);
                sb.append("\t").append(arco.getInicio().getData()).append(" -> ").append(arco.getFin().getData());
                sb.append(" [label=\"").append(arco.getPeso()).append("\"];\n");
            }
        }

        sb.append("}");

        return sb.toString();
    }
}