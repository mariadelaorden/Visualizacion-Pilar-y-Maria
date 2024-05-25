
package es.uah.matcomp.proyecto.modelo.tablero;


import es.uah.matcomp.proyecto.controlador.ParameterDataModelProperties;
import es.uah.matcomp.proyecto.excepciones.CeldaLlenaException;
import es.uah.matcomp.proyecto.modelo.individuo.Individuo;
import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
import es.uah.matcomp.proyecto.modelo.individuo.TipoIndividuo;
import es.uah.matcomp.proyecto.modelo.recurso.Agua;
import es.uah.matcomp.proyecto.modelo.tablero.Celda;
import es.uah.matcomp.proyecto.modelo.tablero.CustomLabel;
import es.uah.matcomp.proyecto.modelo.tablero.Tablero;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLabelTest {

    private CustomLabel customLabel;
    private Celda celda;
    private ParameterDataModelProperties parametros;

    @BeforeEach
    void setUp() {
        // Inicializa la celda
        celda = new Celda(); // Usar el constructor de Celda sin parámetros

        // Inicializa los parámetros necesarios para ParameterDataModelProperties
        Tablero tablero = new Tablero(10, 10); // Crear un tablero con dimensiones arbitrarias
        PlantillaIndividuo individuoBasico = new PlantillaIndividuo();
        PlantillaIndividuo individuoNormal = new PlantillaIndividuo();
        PlantillaIndividuo individuoAvanzado = new PlantillaIndividuo();

        // Obtener la instancia de ParameterDataModelProperties
        parametros = ParameterDataModelProperties.getInstance(tablero, individuoBasico, individuoNormal, individuoAvanzado);

        // Inicializar CustomLabel con la celda y los parámetros
        customLabel = new CustomLabel(celda, parametros);
    }

    @Test
    void getCelda() {
        assertEquals(celda, customLabel.getCelda());
    }

    @Test
    void actualizarTiempoVida() throws CeldaLlenaException {
        Individuo individuo = new Individuo(parametros.getIndividuoNormal(), 1, TipoIndividuo.NORMAL);
        celda.addIndividuo(individuo);
        customLabel.actualizarTiempoVida();
        assertEquals(0, celda.getIndividuos().getNumeroElementos());
    }

    @Test
    void updateLabel() throws CeldaLlenaException {
        celda.addIndividuo(new Individuo(parametros.getIndividuoNormal(), 1, TipoIndividuo.NORMAL));
        celda.addRecurso(new Agua());
        customLabel.updateLabel();
        assertEquals(celda.toString(), customLabel.getText());
    }

    @Test
    void addIndividuoBasico() {
        MenuItem individuoBasicoMenu = customLabel.getContextMenu().getItems().get(0); // Suponiendo que es el primer item
        individuoBasicoMenu.getOnAction().handle(null);
        assertEquals(1, celda.getIndividuos().getNumeroElementos());
        assertEquals(TipoIndividuo.BASICO, ((Individuo) celda.getIndividuos().getElemento(0).getData()).getTipo());
    }

    @Test
    void addRecursoAgua() {
        MenuItem aguaMenu = customLabel.getContextMenu().getItems().get(3); // Suponiendo que es el cuarto item
        aguaMenu.getOnAction().handle(null);
        assertEquals(1, celda.getRecursos().getNumeroElementos());
        assertTrue(celda.getRecursos().getElemento(0).getData() instanceof Agua);
    }
}

