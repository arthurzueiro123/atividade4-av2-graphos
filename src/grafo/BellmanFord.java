package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BellmanFord {



    public boolean run(List<No> grafo,int noInit){
        //List<No> grafoaux = new ArrayList<No>(grafo);
        No aux = grafo.get(noInit);
        aux.value =0;
        aux.anterior = aux;
        System.out.println(aux.getID()+" "+aux.getValue()+" "+aux.anterior.getID());


        for(int i =0;i<(grafo.size() -1);i++){//repete pelo numero de vertices -1

            for(int j =0;j<(grafo.size());j++){//para verificar cada um dos vertices
                //No n = g.remove(0);//g.get(j);
                No n =grafo.get(j);
                for(Aresta t: n.arestas){
                    No v = t.no;
                    if(v.value > n.value + t.custo) {
                        v.value = n.value + t.custo;
                        v.anterior = n;
                    }

                }
            }
        }


        for(int j =0;j<(grafo.size());j++){//para verificar cada um dos vertices
            //No n = g.remove(0);//g.get(j);
            No n =grafo.get(j);
            for(Aresta t: n.arestas){
                No v = t.no;
                if(v.value > n.value + t.custo) {
                   return false;
                }
            }
        }

        return true;
    }
}
