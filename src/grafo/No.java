package grafo;

import java.util.ArrayList;
import java.util.List;

public class No {
    protected final String ID;
    //protected double value = Double.MAX_VALUE;
    protected final List<Aresta> arestas = new ArrayList<Aresta>();

    public No(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    //public double getValue() { return value; }

    public List getArestas(){
        return arestas;
    }

    public void addArestas(No n){
        arestas.add(new Aresta(n));
        n.arestas.add(new Aresta(this));
    }


}