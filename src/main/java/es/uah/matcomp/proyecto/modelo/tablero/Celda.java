
package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.*;

public class Celda {
    private ListaSimple individuos;
    private ListaSimple recursos;
    private int maxIndividuals = 3;
    private int maxResources = 3;

    public Celda() {
        this.individuos = new ListaSimple(maxIndividuals); // Máximo 3 individuos por celda
        this.recursos = new ListaSimple(maxResources); // Máximo 3 recursos por celda
    }

    public ListaSimple getIndividuos() {
        return individuos;
    }

    public ListaSimple getRecursos() {
        return recursos;
    }

    public void addIndividuo(Individuo individuo) {
        individuos.add(individuo);
    }

    public void addRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    public boolean isEmpty() {
        return individuos.isVacia() && recursos.isVacia();
    }

    @Override
    public String toString() {
        String resString = "";
        System.out.println("Hemos llegado a toString de Celda: " + individuos.toString() + "///" + recursos.toString() + "\n");
        for (int i = 0; i < individuos.getMaximo(); i++) {
            if (!individuos.isVacia()) {
                ElementoLS elemento = individuos.getElemento(i);
                if (elemento != null) {
                    Individuo ind = (Individuo) elemento.getData();

                    if (ind.getTipo() == TipoIndividuo.BASICO) {
                        resString += "\uD83D\uDC64"; // 👤 Individuo Básico
                    } else if (ind.getTipo() == TipoIndividuo.NORMAL) {
                        resString += "\uD83D\uDC65"; // 👥 Individuo Normal
                    } else if (ind.getTipo() == TipoIndividuo.AVANZADO) {
                        resString += "\uD83E\uDDD1\u200D\uD83D\uDD2C"; // 🧑‍🔬 Individuo Avanzado
                    }
                }

            } else {
                resString += " ";
            }
        }

        resString += "\n";

        for (int i = 0; i < recursos.getMaximo(); i++) {

            if (!recursos.isVacia()) {
                ElementoLS elemento = recursos.getElemento(i);
                if (elemento != null) {
                    Recurso rec = (Recurso) elemento.getData();

                    if (rec instanceof Agua) {
                        resString += "\uD83D\uDCA7"; // 💧 Agua
                    } else if (rec instanceof Biblioteca) {
                        resString += "\uD83D\uDCDA"; // 📚 Biblioteca
                    } else if (rec instanceof Comida) {
                        resString += "\uD83C\uDF54"; // 🍔 Comida
                    } else if (rec instanceof Montana) {
                        resString += "\u26F0"; // ⛰️ Montaña
                    } else if (rec instanceof Pozo) {
                        resString += "\uD83D\uDD73"; // 🍳 Pozo
                    } else if (rec instanceof Tesoro) {
                        resString += "\uD83D\uDC8E"; // 💎 Tesoro
                    }
                }
            } else {
                resString += " ";
            }
        }
        System.out.println("El resultado de toString de Celda es: " + resString + "\n");
        return resString;
    }
}