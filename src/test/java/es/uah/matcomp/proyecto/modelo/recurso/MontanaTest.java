package es.uah.matcomp.proyecto.modelo.recurso;

import static org.junit.jupiter.api.Assertions.*;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MontanaTest {

    private Montana montana;

    @BeforeEach
    void setUp() {
        montana = new Montana();
    }
    @Test
    void aplicarEfecto() {
        Montana montana = new Montana();

        // Caso de prueba 1: Individuo no nulo
        PlantillaIndividuo plantillaIndividuo = new PlantillaIndividuo();
        Individuo individuo = new Individuo(plantillaIndividuo, 1, TipoIndividuo.BASICO);
        assertDoesNotThrow(() -> montana.aplicarEfecto(individuo),
                "No debería lanzar una excepción cuando el individuo no es nulo");

        // Caso de prueba 2: Individuo nulo
        Individuo individuoNulo = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> montana.aplicarEfecto(individuoNulo),
                "Debería lanzar una excepción cuando el individuo es nulo");
        assertEquals("El individuo no puede ser nulo", exception.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
    }
    @Test
    void testToString() {
        assertEquals("M", montana.toString(), "El método toString() debe devolver 'M'");
    }

    @Test
    void getProbAparicion() {
        assertEquals(0.0, Montana.getProbAparicion(), "La probabilidad de aparición debe ser inicialmente 0.0");
    }

    @Test
    void setProbAparicion() {
        Montana.setProbAparicion(0.5);
        assertEquals(0.5, Montana.getProbAparicion(), "La probabilidad de aparición debe ser 0.5 después de establecerla");

        try {
            Montana.setProbAparicion(-0.1);
            fail("Debería lanzar una excepción al intentar establecer una probabilidad inválida");
        } catch (IllegalArgumentException e) {
            assertEquals("La probabilidad de aparición de Montana debe estar en el rango [0, 1]", e.getMessage(), "Debería lanzar una excepción al intentar establecer una probabilidad inválida");
        }
    }
}
