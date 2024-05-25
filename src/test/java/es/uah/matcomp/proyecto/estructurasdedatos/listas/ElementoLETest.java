package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLETest {

    @Test
    void getData() {
        ElementoLE elemento = new ElementoLE("Hola");

        assertEquals("Hola", elemento.getData());
    }

    @Test
    void setData() {
        ElementoLE elemento = new ElementoLE(null);
        elemento.setData("mundo");
        assertEquals("mundo", elemento.getData());
    }

    @Test
    void getSiguiente() {
        ElementoLE elemento1 = new ElementoLE("primero");
        ElementoLE elemento2 = new ElementoLE("segundo");
        elemento1.setSiguiente(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());
    }

    @Test
    void setSiguiente() {
        ElementoLE elemento1 = new ElementoLE("primero");
        ElementoLE elemento2 = new ElementoLE("segundo");
        elemento1.setSiguiente(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());
    }
}
