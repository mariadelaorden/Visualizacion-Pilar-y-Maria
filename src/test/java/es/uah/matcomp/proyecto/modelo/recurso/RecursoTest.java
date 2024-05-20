package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursoTest {

    @Test
    void getProbAparicion() {
        double probAparicion = Recurso.getProbAparicion();
        assertEquals(0.0, probAparicion, "La probabilidad de aparición inicial debe ser 0.0");
    }

    @Test
    void setProbAparicion() {
        // Caso de prueba 1: Probabilidad válida
        assertDoesNotThrow(() -> Recurso.setProbAparicion(0.5),
                "No debería lanzar una excepción cuando se establece una probabilidad válida");

        // Caso de prueba 2: Probabilidad fuera del rango
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Recurso.setProbAparicion(-0.1),
                "Debería lanzar una excepción cuando se establece una probabilidad fuera del rango");
        assertEquals("La probabilidad de aparición debe estar en el rango [0, 1]", exception.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
    }

    @Test
    void aplicarEfecto() {
    }

    @Test
    void getDuracion() {
        Recurso recurso = new Recurso() {
            @Override
            public void aplicarEfecto(Individuo ind) {
                // No es necesario implementar este método para la prueba de getDuracion
            }
        };
        assertEquals(0, recurso.getDuracion(), "La duración inicial del recurso debe ser 0");
    }

    @Test
    void setDuracion() {
        Recurso recurso = new Recurso() {
            @Override
            public void aplicarEfecto(Individuo ind) {
                // No es necesario implementar este método para la prueba de setDuracion
            }
        };

        // Caso de prueba 1: Duración válida
        assertDoesNotThrow(() -> recurso.setDuracion(10),
                "No debería lanzar una excepción cuando se establece una duración válida");

        // Caso de prueba 2: Duración negativa
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> recurso.setDuracion(-5),
                "Debería lanzar una excepción cuando se establece una duración negativa");
        assertEquals("La duración del recurso no puede ser negativa", exception.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
    }
}
