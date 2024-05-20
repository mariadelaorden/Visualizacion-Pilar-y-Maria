package es.uah.matcomp.proyecto.modelo.tablero;

import es.uah.matcomp.proyecto.estructurasdedatos.listas.ElementoLS;
import es.uah.matcomp.proyecto.estructurasdedatos.listas.ListaSimple;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaTest {

    @Test
    void addIndividuo() {
        Celda celda = new Celda();
        Individuo individuo = new Individuo(new PlantillaIndividuo(), 0, TipoIndividuo.BASICO);

        assertTrue(celda.isEmpty(), "La celda debería estar vacía al principio");

        celda.addIndividuo(individuo);

        assertFalse(celda.isEmpty(), "La celda no debería estar vacía después de añadir un individuo");
    }

    @Test
    void addRecurso() {
        Celda celda = new Celda();
        Recurso recurso = new Tesoro();

        assertTrue(celda.isEmpty(), "La celda debería estar vacía al principio");

        celda.addRecurso(recurso);

        assertFalse(celda.isEmpty(), "La celda no debería estar vacía después de añadir un recurso");
    }
    @Test
    void testGetIndividuos() {
        Celda celda = new Celda();

        assertTrue(celda.getIndividuos().isVacia());

        Individuo individuo = new Individuo(new PlantillaIndividuo(), 1, TipoIndividuo.BASICO);
        celda.addIndividuo(individuo);

        assertTrue(contiene(celda.getIndividuos(), individuo));
    }

    @Test
    void testGetRecursos() {
        Celda celda = new Celda();

        assertTrue(celda.getRecursos().isVacia());

        Recurso recurso = new Biblioteca();
        celda.addRecurso(recurso);

        assertTrue(contiene(celda.getRecursos(), recurso));
    }

    private <T> boolean contiene(ListaSimple lista, T elemento) {
        for (int i = 0; i < lista.getMaximo(); i++) {
            ElementoLS elementoLS = lista.getElemento(i);
            if (elementoLS != null && elementoLS.getData().equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    @Test
    void isEmpty() {
        Celda celda = new Celda();
        assertTrue(celda.isEmpty(), "La celda debería estar vacía al principio");
    }

    @Test
    void testToStringWithBiblioteca() {
        Celda celda = new Celda();

        celda.addIndividuo(new Individuo(new PlantillaIndividuo(), 1, TipoIndividuo.BASICO));
        celda.addRecurso(new Biblioteca());

        String expectedRepresentation = "\uD83D\uDC64\n\uD83D\uDCDA";

        String actualRepresentation = celda.toString();

        assertEquals(expectedRepresentation, actualRepresentation);
    }

    @Test
    void testToStringWithAgua() {
        Celda celda = new Celda();

        celda.addRecurso(new Agua());

        String expectedRepresentation = "   \n" +
                "\uD83D\uDCA7";

        String actualRepresentation = celda.toString();

        assertEquals(expectedRepresentation, actualRepresentation);
    }

    @Test
    void testToStringWithComida() {
        Celda celda = new Celda();

        celda.addRecurso(new Comida());

        String expectedRepresentation = "   \n" +
                "\uD83C\uDF54";

        String actualRepresentation = celda.toString();

        assertEquals(expectedRepresentation, actualRepresentation);
    }

    @Test
    void testToStringWithMontana() {
        Celda celda = new Celda();

        celda.addRecurso(new Montana());

        String expectedRepresentation = "   \n" +
                "⛰";

        String actualRepresentation = celda.toString();

        assertEquals(expectedRepresentation, actualRepresentation);
    }
    @Test
    void testToStringWithPozo() {
        Celda celda = new Celda();

        celda.addRecurso(new Pozo());

        String expectedRepresentation = "   \n" +
                "\uD83D\uDD73";

        String actualRepresentation = celda.toString();

        assertEquals(expectedRepresentation, actualRepresentation);
    }

    @Test
    void testToStringWithTesoro() {
        Celda celda = new Celda();

        celda.addRecurso(new Tesoro());

        String expectedRepresentation = "   \n" +
                "\uD83D\uDC8E";

        String actualRepresentation = celda.toString();

        assertEquals(expectedRepresentation, actualRepresentation);
    }


}
