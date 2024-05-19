
//package es.uah.matcomp.proyecto.modelo.tablero;
//
//import es.uah.matcomp.proyecto.estructurasdedatos.ListaEnlazada;
//import javafx.geometry.Insets;
//import javafx.scene.layout.GridPane;
//import javafx.scene.control.Label;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.RowConstraints;
//
//public class CustomCell extends GridPane {
//
//    private final int i;
//    private final int j;
//
//    public CustomCell(int row, int col) {
//        this.setMinSize(90, 90);  // Ajusta el tamaño según sea necesario
//        this.setStyle("-fx-border-color: black;");
//        this.i = row;
//        this.j = col;
//
//        // Define row and column constraints for equal division
//        RowConstraints row1 = new RowConstraints();
//        row1.setPercentHeight(50);  // 50% height
//        RowConstraints row2 = new RowConstraints();
//        row2.setPercentHeight(50);  // 50% height
//        this.getRowConstraints().addAll(row1, row2);
//
//        ColumnConstraints col1 = new ColumnConstraints();
//        col1.setPercentWidth(33.33);  // 33.33% width
//        ColumnConstraints col2 = new ColumnConstraints();
//        col2.setPercentWidth(33.33);  // 33.33% width
//        ColumnConstraints col3 = new ColumnConstraints();
//        col3.setPercentWidth(33.33);  // 33.33% width
//        this.getColumnConstraints().addAll(col1, col2, col3);
//
//        // Set padding and spacing for differentiation
//        this.setPadding(new Insets(5));
//        this.setHgap(5);
//        this.setVgap(5);
//
//        subCells = new Label[2][3];
//
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 3; j++) {
//                Label subCell = new Label();
//                subCell.setMinSize(30, 30);  // Ajusta el tamaño según sea necesario
//                subCell.setStyle("-fx-background-color: white; -fx-border-color: gray;");
//                this.add(subCell, j, i);
//                subCells[i][j] = subCell;
//            }
//        }
//    }
//
//    public Label getSubCell(int row, int col) {
//        return subCells[row][col];
//    }
//
//    public int getI() {
//        return i;
//    }
//
//    public int getJ() {
//        return j;
//    }
//}
