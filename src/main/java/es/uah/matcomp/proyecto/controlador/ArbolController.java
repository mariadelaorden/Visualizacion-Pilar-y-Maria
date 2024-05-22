package es.uah.matcomp.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ArbolController {
    @FXML
    private TreeView<String> arbolGenealogicoTreeView;

    @FXML
    private void initialize() {
        TreeItem<String> rootItem = new TreeItem<>("Padre");
        rootItem.setExpanded(true);

        TreeItem<String> childItem1 = new TreeItem<>("Hijo");
        TreeItem<String> childItem2 = new TreeItem<>("Hijo");

        rootItem.getChildren().add(childItem1);
        rootItem.getChildren().add(childItem2);

        arbolGenealogicoTreeView.setRoot(rootItem);
    }

    @FXML
    private void onCerrarButtonClick() {
        // Cierra la ventana
        Stage stage = (Stage) arbolGenealogicoTreeView.getScene().getWindow();
        stage.close();
    }
}

