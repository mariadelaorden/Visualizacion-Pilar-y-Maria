package es.uah.matcomp.proyecto.estructurasdedatos.listas;

public class ElementoLDE {
    private ElementoLDE anterior;
    private ElementoLDE siguiente;
    private Object data;

    public ElementoLDE(Object data){
        this.data=data;
    }

    protected void insertarmeEn(ElementoLDE el){
        el.siguiente=this.siguiente;
        el.anterior=this;

        if (this.siguiente !=null){
            this.siguiente.anterior=el;
        }
        this.siguiente=el;
    }
    protected ElementoLDE getSiguiente(){
        return this.siguiente;
    }
    protected ElementoLDE getAnterior(){
        return this.anterior;
    }
    public Object getData(){
        return this.data;
    }
    public Object setData(Object o){
        Object temporal = data;
        this.data=o;
        return temporal;
    }
    public String toString(){
        return String.valueOf(this.data);
    }
}
