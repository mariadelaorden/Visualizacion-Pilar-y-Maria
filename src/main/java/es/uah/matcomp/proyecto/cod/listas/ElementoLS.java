package es.uah.matcomp.proyecto.cod.listas;

public class ElementoLS<C> {
    private C data;
    public C getData(){
        return this.data;
    }
    public void setData(C data){
        this.data=data;
    }
    public String toString(){
        return String.valueOf(data);
    }
}
