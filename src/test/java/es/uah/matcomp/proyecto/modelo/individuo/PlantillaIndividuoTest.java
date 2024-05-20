package es.uah.matcomp.proyecto.modelo.individuo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlantillaIndividuoTest {

    @Test
    void constructorCopia() {
        PlantillaIndividuo original = new PlantillaIndividuo();
        original.setVida(10);
        original.setProbReproduccion(0.5);
        original.setProbClonacion(0.3);
        original.setProbMuerte(0.2);

        PlantillaIndividuo copia = null;
        try {
            copia = new PlantillaIndividuo(original);
        } catch (IllegalArgumentException e) {
            fail("No debería lanzar una excepción al crear una copia válida");
        }

        assertEquals(original.getVida(), copia.getVida());
        assertEquals(original.getProbReproduccion(), copia.getProbReproduccion());
        assertEquals(original.getProbClonacion(), copia.getProbClonacion());
        assertEquals(original.getProbMuerte(), copia.getProbMuerte());
    }

    @Test
    void constructorCopiaNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlantillaIndividuo(null);
        });
    }

    @Test
    void setProbMejoraValido() {
        try {
            PlantillaIndividuo.setProbMejora(0.5);
        } catch (IllegalArgumentException e) {
            fail("No debería lanzar una excepción al establecer una probabilidad de mejora válida");
        }

        assertEquals(0.5, PlantillaIndividuo.probMejora);
    }

    @Test
    void setProbMejoraInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            PlantillaIndividuo.setProbMejora(-0.5);
        });

        assertEquals(0.5, PlantillaIndividuo.probMejora);
    }

    @Test
    void setVidaNegativa() {
        PlantillaIndividuo individuo = new PlantillaIndividuo();
        assertThrows(IllegalArgumentException.class, () -> {
            individuo.setVida(-5);
        });
    }

    @Test
    void setProbabilidadReproduccionInvalida() {
        PlantillaIndividuo individuo = new PlantillaIndividuo();
        assertThrows(IllegalArgumentException.class, () -> {
            individuo.setProbReproduccion(1.5);
        });
    }

    @Test
    void setProbabilidadClonacionInvalida() {
        PlantillaIndividuo individuo = new PlantillaIndividuo();
        assertThrows(IllegalArgumentException.class, () -> {
            individuo.setProbClonacion(-0.1);
        });
    }

    @Test
    void setProbabilidadMuerteInvalida() {
        PlantillaIndividuo individuo = new PlantillaIndividuo();
        assertThrows(IllegalArgumentException.class, () -> {
            individuo.setProbMuerte(1.2);
        });
    }
}
