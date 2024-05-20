package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesoroTest {

    @Test
    void aplicarEfecto() {
        Tesoro tesoro = new Tesoro();

        PlantillaIndividuo plantilla = new PlantillaIndividuo();
        TipoIndividuo tipo = TipoIndividuo.NORMAL;
        Individuo individuo = new Individuo(plantilla, 1, tipo);

        assertDoesNotThrow(() -> tesoro.aplicarEfecto(individuo),
                "No debería lanzar una excepción cuando el individuo no es nulo");

        // Caso de prueba 2: Individuo nulo
        Individuo individuoNulo = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tesoro.aplicarEfecto(individuoNulo),
                "Debería lanzar una excepción cuando el individuo es nulo");
        assertEquals("El individuo no puede ser nulo", exception.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
    }

    @Test
    void getProbAparicion() {
        double probInicial = Tesoro.getProbAparicion();

        // Caso de prueba 1: Valor dentro del rango
        double nuevoValor1 = 0.5;
        assertDoesNotThrow(() -> Tesoro.setProbAparicion(nuevoValor1),
                "No debería lanzar una excepción cuando se establece un valor dentro del rango");
        assertEquals(nuevoValor1, Tesoro.getProbAparicion(),
                "El valor de probabilidad de aparición debería ser igual al establecido");

        // Caso de prueba 2: Valor fuera del rango (inferior)
        double nuevoValor2 = -0.1;
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> Tesoro.setProbAparicion(nuevoValor2),
                "Debería lanzar una excepción cuando se establece un valor fuera del rango inferior");
        assertEquals("La probabilidad de aparición debe estar en el rango [0, 1]", exception2.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
        assertEquals(nuevoValor1, Tesoro.getProbAparicion(),
                "El valor de probabilidad de aparición no debería cambiar después de un intento inválido");

        // Caso de prueba 3: Valor fuera del rango (superior)
        double nuevoValor3 = 1.1;
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,
                () -> Tesoro.setProbAparicion(nuevoValor3),
                "Debería lanzar una excepción cuando se establece un valor fuera del rango superior");
        assertEquals("La probabilidad de aparición debe estar en el rango [0, 1]", exception3.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
        assertEquals(nuevoValor1, Tesoro.getProbAparicion(),
                "El valor de probabilidad de aparición no debería cambiar después de un intento inválido");

        Tesoro.setProbAparicion(probInicial);
    }
    @Test
    void testToString() {
        Tesoro tesoro = new Tesoro();

        assertEquals("T", tesoro.toString(), "El método toString no retorna 'T' como se espera");
    }

    @Test
    void setProbAparicion() {
        double probInicial = Tesoro.getProbAparicion();

        // Caso de prueba 1: Valor dentro del rango
        double nuevoValor1 = 0.5;
        assertDoesNotThrow(() -> Tesoro.setProbAparicion(nuevoValor1),
                "No debería lanzar una excepción cuando se establece un valor dentro del rango");
        assertEquals(nuevoValor1, Tesoro.getProbAparicion(),
                "El valor de probabilidad de aparición debería ser igual al establecido");

        // Caso de prueba 2: Valor fuera del rango (inferior)
        double nuevoValor2 = -0.1;
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> Tesoro.setProbAparicion(nuevoValor2),
                "Debería lanzar una excepción cuando se establece un valor fuera del rango inferior");
        assertEquals("La probabilidad de aparición debe estar en el rango [0, 1]", exception2.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
        assertEquals(nuevoValor1, Tesoro.getProbAparicion(),
                "El valor de probabilidad de aparición no debería cambiar después de un intento inválido");

        // Caso de prueba 3: Valor fuera del rango (superior)
        double nuevoValor3 = 1.1;
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,
                () -> Tesoro.setProbAparicion(nuevoValor3),
                "Debería lanzar una excepción cuando se establece un valor fuera del rango superior");
        assertEquals("La probabilidad de aparición debe estar en el rango [0, 1]", exception3.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
        assertEquals(nuevoValor1, Tesoro.getProbAparicion(),
                "El valor de probabilidad de aparición no debería cambiar después de un intento inválido");

        Tesoro.setProbAparicion(probInicial);
    }
}
