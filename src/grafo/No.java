package grafo;

import java.util.ArrayList;
import java.util.List;

public class No implements java.lang.Comparable<No>{
    protected final String ID;
    protected double value = Double.MAX_VALUE;
    protected No anterior;
    protected final List<Aresta> arestas = new ArrayList<Aresta>();

    public No(String ID) {
        this.ID = ID;
        anterior = this;
    }

    public String getID() {
        return ID;
    }

    public double getValue() { return value; }

    public List getArestas(){
        return arestas;
    }

    public No getAnterior() { return anterior; }

    public void addArestas(No n,double custo){
        arestas.add(new Aresta(n, custo));
        //n.arestas.add(new Aresta(this, custo));
    }

    public String getCaminho() {
        if(anterior == this){
            return "";
        }
        return " -> "+anterior.getID()+anterior.getCaminho();
    }
    @Override
    public int compareTo(No no) {
        if(this.value < no.value){
            return -1;
        }
        if(this.value > no.value){
            return 1;
        }
        return this.ID.hashCode()<no.ID.hashCode()?-1:1;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof No){
            No x = (No) o;
            return this.ID.equals(x.ID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.ID.hashCode();
    }

    @Override
    public String toString() {
        return "No{" +
                "ID='" + ID  +
                ", anterior=" + anterior.getID() +
                ", value=" + value +
                '}';
    }


}