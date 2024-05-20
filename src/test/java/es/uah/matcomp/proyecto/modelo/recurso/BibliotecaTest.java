package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    @Test
    void aplicarEfecto_NullIndividuo() {
        Biblioteca biblioteca = new Biblioteca();
        assertThrows(IllegalArgumentException.class, () -> {
            biblioteca.aplicarEfecto(null);
        });
    }

    @Test
    void testToString() {
        Biblioteca biblioteca = new Biblioteca();
        assertEquals("B", biblioteca.toString());
    }

    @Test
    void setProbAparicion_ValidValue() {
        double validValue = 0.5;
        Biblioteca.setProbAparicion(validValue);
        assertEquals(validValue, Biblioteca.getProbAparicion());
    }

    @Test
    void setProbAparicion_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Biblioteca.setProbAparicion(-0.5);
        });
    }
}
