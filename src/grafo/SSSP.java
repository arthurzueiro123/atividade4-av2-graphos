package grafo;
//package DIJKSTRA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SSSP {

    public List<No> run(List<No> grafo){
        List<No> g = new ArrayList<No>(grafo);
        while(g.size()>0){
            Collections.sort(g);
            No n = g.remove(0);
            for(Aresta t: n.arestas){
                No v = t.no;
                v.value = n.value+t.custo;
                v.anterior = n;
            }
        }
        Collections.sort(grafo);
        return grafo;

    }

    public List<No> run(List<No> grafo,int noInit){
        List<No> grafoaux = new ArrayList<No>(grafo);
        No aux = grafoaux.get(noInit);
        List<No> g = new ArrayList<No>();
        //g.add(aux);

        //g.remove(noInit);
        //Collections.sort(g);
        aux.value =0;
        aux.anterior = aux;
        System.out.println(aux.getID()+" "+aux.getValue()+" "+aux.anterior.getID());
        g.add(0,aux);

        while(g.size()>0){
            No n = g.remove(0);
            for(Aresta t: n.arestas){
                No v = t.no;
                if(v.value > n.value + t.custo) {
                    v.value = n.value + t.custo;
                    v.anterior = n;
                    g.add(v);
                }

            }
            //Collections.sort(g);
        }
        //Collections.sort(grafo);
        return grafo;

    }
}