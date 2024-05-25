/**

import es.uah.matcomp.proyecto.controlador.ParameterDataModelProperties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public static void finalizarPartida (ParameterDataModelProperties model) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("arbol-view-vista.fxml"));
        Parent root = fxmlLoader.load();
        HBox paneGanadores = (HBox) ((AnchorPane) ((ScrollPane) ((VBox) root).getChildren().get(2)).getContent()).getChildren().getFirst();

        HBox.setHgrow(paneGanadores, Priority.ALWAYS);
        AnchorPane.setTopAnchor(paneGanadores, 0.0);
        AnchorPane.setBottomAnchor(paneGanadores, 0.0);
        AnchorPane.setLeftAnchor(paneGanadores, 0.0);
        AnchorPane.setRightAnchor(paneGanadores, 0.0);

        HashMap<individuo, ArbolBinario<individuo>> arbolesGenealogicos = juegoActual.getArbolesGenealogicos();

        int numeroGanadores = model.getIndividuos().getNumeroElementos();
        for (int i = 0; i != numeroGanadores; i ++) {
            individuo individuoActual = model.getIndividuos().getElemento(i).getData();
            ArbolBinario<individuo> arbolActual = arbolesGenealogicos.get(individuoActual);

            TreeView<individuo> vistaGanadores = new TreeView<>();

            TreeItem<individuo> raiz = new TreeItem<>();
            raiz.getChildren().add(crearArbolVista(arbolActual));
            vistaGanadores.setRoot(raiz);
            vistaGanadores.setShowRoot(false);
            vistaGanadores.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            vistaGanadores.setCellFactory(_ -> new TreeCell<>() {
                @Override
                protected void updateItem(individuo individuo, boolean empty) {
                    super.updateItem(individuo, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        if (individuo != null) {
                            setText(STR."Individuo \{individuo.getId()}");
                            setFont(new Font("Bookman Old Style", 18));
                        }
                    }
                }
            });

            paneGanadores.getChildren().add(vistaGanadores);
            HBox.setHgrow(vistaGanadores, Priority.ALWAYS);
        }


        while (!Window.getWindows().isEmpty()) {
            ((Stage) Window.getWindows().getFirst()).close();
        };
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setIconified(false);
        stage.setFullScreen(true);
        stage.show();
    } catch (IOException e) {
        log.error("No se ha encontrado la vista del menú principal");
    }
}

private static TreeItem<individuo> crearArbolVista (ArbolBinario<individuo> arbol) {
    TreeItem<individuo> itemRaiz = new TreeItem<>(arbol.getRaiz().getDato());
    if (arbol.getAltura() > 1) {
        crearArbolVistaAux(arbol.getRaiz().getDerecha(), itemRaiz);
        crearArbolVistaAux(arbol.getRaiz().getIzquierda(), itemRaiz);
    }
    return itemRaiz;
}

private static void crearArbolVistaAux (nodoBST<individuo> nodo, TreeItem<individuo> itemRaiz) {
    TreeItem<individuo> item = new TreeItem<>(nodo.getDato());
    itemRaiz.getChildren().add(item);

    if (nodo.getDerecha() != null & nodo.getIzquierda() != null) {
        crearArbolVistaAux(nodo.getDerecha(), item);
        crearArbolVistaAux(nodo.getIzquierda(), item);
    }
}

private HashMap<individuo, ArbolBinario<individuo>> crearArbolesGenealogicos () {
    HashMap<individuo, ArbolBinario<individuo>> arbolesGenealogicos = new HashMap<>();

    int totalIndividuos = model.getIndividuos().getNumeroElementos();
    for (int i = 0; i != totalIndividuos; i ++) {
        individuo individuoActual = model.getIndividuos().getElemento(i).getData();
        ArbolBinario<individuo> arbolGenealogico = new ArbolBinario<>(individuoActual);

        añadirPadres(arbolGenealogico.getRaiz());
        arbolesGenealogicos.put(individuoActual, arbolGenealogico);
    }
    return arbolesGenealogicos;
}

private void añadirPadres (nodoBST<individuo> hijo) {
    try {
        if (hijo.getDato().getPadres() != null) {
            if (hijo.getDato().getPadres().getNumeroElementos() != 2) throw new numeroPadresInvalidoException();
            hijo.setDerecha(new nodoBST<>(hijo.getDato().getPadres().getPrimero().getData()));
            hijo.setIzquierda(new nodoBST<>(hijo.getDato().getPadres().getElemento(1).getData()));

            añadirPadres(hijo.getDerecha());
            añadirPadres(hijo.getIzquierda());
        }
    } catch (numeroPadresInvalidoException e) {
        log.error("El número de padres no es 2");
    }
}
 **/