package es.uah.matcomp.proyecto.estructurasdedatos.listas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLDETest {

    @Test
    void insertarmeEn() {
        ElementoLDE el1=new ElementoLDE(1);
        ElementoLDE el2=new ElementoLDE(2);
        el1.insertarmeEn(el2);   //Unir los elementos
        ElementoLDE actual = el2.getAnterior();
        assertEquals(1,actual.getData(),"Fallo insertarmeen no coincide");
        el1.insertarmeEn(el2);   //Unir los elementos
        ElementoLDE actual1 = el1.getSiguiente();
        assertEquals(2,actual1.getData(),"Fallo insertarmeen no coincide");
    }

    @Test
    void getSiguiente() {
        ElementoLDE el1=new ElementoLDE(1);
        ElementoLDE el2=new ElementoLDE(2);
        el1.insertarmeEn(el2);   //Unir los elementos
        ElementoLDE actual = el1.getSiguiente();
        assertEquals(2,actual.getData(),"Fallo siguiente no coincide");
    }

    @Test
    void getAnterior() {
        ElementoLDE el1=new ElementoLDE(1);
        ElementoLDE el2=new ElementoLDE(2);
        el1.insertarmeEn(el2);   //Unir los elementos
        ElementoLDE actual = el2.getAnterior();
        assertEquals(1,actual.getData(),"Fallo anterior no coincide");
    }

    @Test
    void getData() {
        ElementoLDE el1= new ElementoLDE(10);
        assertEquals(10,el1.getData(),"Fallo data no coincide");
    }

    @Test
    void setData() {
        ElementoLDE el1= new ElementoLDE(null);
        el1.setData(10);
        assertEquals(10,el1.getData(),"Fallo data no coincide en el set");
    }

    @Test
    void testToString() {
        ElementoLDE el1= new ElementoLDE(10);
        assertEquals("10",el1.toString(),"Fallo toString no coincide");
    }
}