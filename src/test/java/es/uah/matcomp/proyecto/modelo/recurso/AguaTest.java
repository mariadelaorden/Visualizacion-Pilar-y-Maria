package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AguaTest {

    @Test
    void aplicarEfecto_NullIndividuo() {
        Agua agua = new Agua();
        assertThrows(IllegalArgumentException.class, () -> {
            agua.aplicarEfecto(null);
        });
    }

    @Test
    void setProbAparicion_ValidValue() {
        double validValue = 0.5;
        Agua.setProbAparicion(validValue);
        assertEquals(validValue, Agua.getProbAparicion());
    }
    @Test
    void testToString() {
        Agua agua = new Agua();
        assertEquals("A", agua.toString());
    }

    @Test
    void setProbAparicion_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Agua.setProbAparicion(-0.5);
        });
    }
}
