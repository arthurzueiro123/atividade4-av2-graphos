package grafo;

public class Aresta {
    protected No no;
    protected double custo;


    public No getNo() {
        return no;
    }

    public Aresta(No no,double custo) {
        this.no = no;
        this.custo = custo;
    }
}