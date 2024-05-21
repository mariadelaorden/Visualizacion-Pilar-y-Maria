package es.uah.matcomp.proyecto.estructurasdedatos.listas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElementoLS<C> {
    private static final Logger logger = LogManager.getLogger(ElementoLS.class);

    private C data;

    public C getData() {
        try {
            return this.data;
        } catch (Exception e) {
            logger.error("Error al obtener los datos del elemento", e);
            throw new RuntimeException("Error al obtener los datos del elemento", e);
        }
    }

    public void setData(C data) {
        try {
            this.data = data;
            logger.info("Datos del elemento actualizados a: {}", data);
        } catch (Exception e) {
            logger.error("Error al establecer los datos del elemento", e);
            throw new RuntimeException("Error al establecer los datos del elemento", e);
        }
    }

    @Override
    public String toString() {
        try {
            return String.valueOf(this.data);
        } catch (Exception e) {
            logger.error("Error al convertir los datos del elemento a String", e);
            throw new RuntimeException("Error al convertir los datos del elemento a String", e);
        }
    }
}
