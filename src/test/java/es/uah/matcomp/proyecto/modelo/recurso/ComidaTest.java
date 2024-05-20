package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComidaTest {

    @Test
    void aplicarEfecto_NullIndividuo() {
        Comida comida = new Comida();
        assertThrows(IllegalArgumentException.class, () -> {
            comida.aplicarEfecto(null);
        });
    }

    @Test
    void testToString() {
        Comida comida = new Comida();
        assertEquals("C", comida.toString());
    }

    @Test
    void setProbAparicion_ValidValue() {
        double validValue = 0.5;
        Comida.setProbAparicion(validValue);
        assertEquals(validValue, Comida.getProbAparicion());
    }

    @Test
    void setProbAparicion_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Comida.setProbAparicion(-0.5);
        });
    }
}
