package es.uah.matcomp.proyecto.modelo.individuo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoIndividuoTest {

    @Test
    void valoresCorrectos() {
        assertEquals(4, TipoIndividuo.values().length); // Verifica que hay 4 constantes en la enumeración

        assertEquals(TipoIndividuo.BASICO, TipoIndividuo.valueOf("BASICO")); // Verifica el valor de BASICO
        assertEquals(TipoIndividuo.NORMAL, TipoIndividuo.valueOf("NORMAL")); // Verifica el valor de NORMAL
        assertEquals(TipoIndividuo.AVANZADO, TipoIndividuo.valueOf("AVANZADO")); // Verifica el valor de AVANZADO
        assertEquals(TipoIndividuo.AGUA, TipoIndividuo.valueOf("AGUA")); // Verifica el valor de AGUA
    }
}
