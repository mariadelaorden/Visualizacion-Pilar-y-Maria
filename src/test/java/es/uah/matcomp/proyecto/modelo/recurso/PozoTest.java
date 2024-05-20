package es.uah.matcomp.proyecto.modelo.recurso;

import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PozoTest {

    @Test
    void aplicarEfecto() {
        Pozo pozo = new Pozo();

        // Caso de prueba 1: Individuo no nulo
        PlantillaIndividuo plantilla = new PlantillaIndividuo();
        int generacion = 1;
        TipoIndividuo tipo = TipoIndividuo.BASICO;
        Individuo individuo = new Individuo(plantilla, generacion, tipo);

        assertDoesNotThrow(() -> pozo.aplicarEfecto(individuo),
                "No debería lanzar una excepción cuando el individuo no es nulo");

        // Caso de prueba 2: Individuo nulo
        Individuo individuoNulo = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> pozo.aplicarEfecto(individuoNulo),
                "Debería lanzar una excepción cuando el individuo es nulo");
        assertEquals("El individuo no puede ser nulo", exception.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
    }

    @Test
    void testToString() {
        Pozo pozo = new Pozo();
        assertEquals("P", pozo.toString(), "El método toString() debería devolver 'P'");
    }

    @Test
    void getProbAparicion() {
        Pozo pozo = new Pozo();
        double probAparicion = 0.5;
        pozo.setProbAparicion(probAparicion);
        assertEquals(probAparicion, pozo.getProbAparicion(), "La probabilidad de aparición debería ser igual a 0.5");
    }

    @Test
    void setProbAparicion() {
        Pozo pozo = new Pozo();

        // Caso de prueba 1: Probabilidad dentro del rango permitido
        double probAparicionValida = 0.7;
        assertDoesNotThrow(() -> pozo.setProbAparicion(probAparicionValida),
                "No debería lanzar una excepción cuando la probabilidad está dentro del rango [0, 1]");
        assertEquals(probAparicionValida, pozo.getProbAparicion(),
                "La probabilidad de aparición debería ser igual a 0.7");

        // Caso de prueba 2: Probabilidad fuera del rango permitido (menor que 0)
        double probAparicionInvalidaMenorQueCero = -0.1;
        IllegalArgumentException exceptionMenorQueCero = assertThrows(IllegalArgumentException.class,
                () -> pozo.setProbAparicion(probAparicionInvalidaMenorQueCero),
                "Debería lanzar una excepción cuando la probabilidad es menor que 0");
        assertEquals("La probabilidad de aparición debe estar en el rango [0, 1]",
                exceptionMenorQueCero.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");

        // Caso de prueba 3: Probabilidad fuera del rango permitido (mayor que 1)
        double probAparicionInvalidaMayorQueUno = 1.1;
        IllegalArgumentException exceptionMayorQueUno = assertThrows(IllegalArgumentException.class,
                () -> pozo.setProbAparicion(probAparicionInvalidaMayorQueUno),
                "Debería lanzar una excepción cuando la probabilidad es mayor que 1");
        assertEquals("La probabilidad de aparición debe estar en el rango [0, 1]",
                exceptionMayorQueUno.getMessage(),
                "El mensaje de la excepción no coincide con lo esperado");
    }
}
