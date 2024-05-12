package es.uah.matcomp.proyecto.controlador;

import javafx.scene.control.Label;

public class CustomLabel extends Label {

    private final int i;
    private final int j;

    public CustomLabel(int i, int j, String text) {
        super(text);
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}