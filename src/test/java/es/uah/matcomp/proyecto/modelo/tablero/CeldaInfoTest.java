//package es.uah.matcomp.proyecto.modelo.tablero;
//
//import es.uah.matcomp.proyecto.modelo.individuo.PlantillaIndividuo;
//import es.uah.matcomp.proyecto.modelo.recurso.Agua;
//import es.uah.matcomp.proyecto.modelo.recurso.Biblioteca;
//import es.uah.matcomp.proyecto.modelo.recurso.Recurso;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.control.ListView;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.testfx.framework.junit5.ApplicationTest;
//import javafx.stage.Stage;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CeldaInfoTest extends ApplicationTest {
//
//    private CeldaInfo celdaInfo;
//
//    @BeforeAll
//    static void initToolkit() {
//        // Inicializa el toolkit de JavaFX en un thread separado
//        Thread toolkitThread = new Thread(() -> new JFXPanel());
//        toolkitThread.setDaemon(true);
//        toolkitThread.start();
//    }
//
//    @Override
//    public void start(Stage stage) {
//        celdaInfo = new CeldaInfo();
//    }
//
//    @Test
//    void updateCelda() {
//        // Crear una instancia de Celda
//        Celda celda = new Celda();
//
//        // Crear algunos individuos y recursos
//        PlantillaIndividuo individuo1 = new PlantillaIndividuo();
//        individuo1.setVida(100);
//        individuo1.setProbReproduccion(0.5);
//        individuo1.setProbClonacion(0.3);
//        individuo1.setProbMuerte(0.2);
//
//        PlantillaIndividuo individuo2 = new PlantillaIndividuo();
//        individuo2.setVida(80);
//        individuo2.setProbReproduccion(0.6);
//        individuo2.setProbClonacion(0.4);
//        individuo2.setProbMuerte(0.3);
//
//        Recurso recurso1 = new Agua();
//        Recurso recurso2 = new Biblioteca();
//
//        // Agregar individuos y recursos a la celda utilizando el método add de ListaSimple
//        celda.getIndividuos().add(individuo1);
//        celda.getIndividuos().add(individuo2);
//        celda.getRecursos().add(recurso1);
//        celda.getRecursos().add(recurso2);
//
//        // Llamar al método updateCelda con la celda creada
//        celdaInfo.updateCelda(celda);
//
//        // Obtener los ListViews indirectamente desde la instancia de CeldaInfo
//        ListView<String> individuosListView = getListView(celdaInfo, 1);
//        ListView<String> recursosListView = getListView(celdaInfo, 3);
//
//        // Verificar que los ListViews contengan la cantidad correcta de elementos
//        assertEquals(2, individuosListView.getItems().size());
//        assertEquals(2, recursosListView.getItems().size());
//
//        // Verificar el contenido específico de los ListViews
//        assertTrue(individuosListView.getItems().get(0).contains("Vida: 100"));
//        assertTrue(individuosListView.getItems().get(1).contains("Vida: 80"));
//        assertTrue(recursosListView.getItems().get(0).contains("Tipo: Agua"));
//        assertTrue(recursosListView.getItems().get(1).contains("Tipo: Biblioteca"));
//    }
//
//    // Método auxiliar para obtener los ListViews desde la instancia de CeldaInfo
//    private ListView<String> getListView(CeldaInfo celdaInfo, int index) {
//        return (ListView<String>) celdaInfo.getChildren().get(index);
//    }
//}
