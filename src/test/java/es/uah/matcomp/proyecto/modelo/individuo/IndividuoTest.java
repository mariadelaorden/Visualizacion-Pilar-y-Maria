package es.uah.matcomp.proyecto.modelo.individuo;

import es.uah.matcomp.proyecto.excepciones.IndividuoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IndividuoTest {

    private PlantillaIndividuo plantilla;
    private Individuo individuo;

    @BeforeEach
    void setUp() {
        resetSiguienteID();

        // Inicializar la plantilla y el individuo
        plantilla = new PlantillaIndividuo();
        individuo = new Individuo(plantilla, 1, TipoIndividuo.BASICO);
    }

    @Test
    void getId() {
        assertEquals(0, individuo.getId());
    }

    @Test
    void getGeneracion() {
        assertEquals(1, individuo.getGeneracion());
    }

    @Test
    void getTipo() {
        assertEquals(TipoIndividuo.BASICO, individuo.getTipo());
    }

    @Test
    void setTipo() {
        try {
            individuo.setTipo(TipoIndividuo.AVANZADO);
            assertEquals(TipoIndividuo.AVANZADO, individuo.getTipo());
        } catch (IndividuoException e) {
            fail("IndividuoException should not be thrown");
        }
    }

    @Test
    void setTipoNull() {
        Exception exception = assertThrows(IndividuoException.class, () -> {
            individuo.setTipo(null);
        });

        String expectedMessage = "Tipo de individuo no puede ser null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testToString() {
        assertEquals("B", individuo.toString());
    }

    private void resetSiguienteID() {
        try {
            java.lang.reflect.Field field = Individuo.class.getDeclaredField("siguienteID");
            field.setAccessible(true);
            field.setInt(null, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
