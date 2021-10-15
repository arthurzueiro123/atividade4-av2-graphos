package grafo;

import java.util.ArrayList;
import java.util.List;

public class BuscaPorLargura {
        public static int[][] buscar(List<No> grafo, No origem) {
            int qtdVertices = grafo.size();
            int valorOrigem = Integer.parseInt(origem.getID())-1;

            List<Integer> fila = new ArrayList<Integer>();
            int[] pai = new int[qtdVertices];
            int[] distancia = new int[qtdVertices];
            for(int i = 0; i< qtdVertices;i++){//atribui -1 pois nenhum vertice foi visitado ainda
                pai[i]=-1;
                distancia[i]=-1;
            }

            distancia[valorOrigem]=0;//pois a distancia da origem para ela mesma é o
            pai[valorOrigem]= valorOrigem;//pois o pai da origem é a propia origem
            fila.add(valorOrigem);//pois o primeiro elemento a ser verificado é a propia origem

            while(fila.size() != 0){
                int paiAtual = fila.remove(0);//removendo o primeiro elemento da fila, pois ele sera verificado
                int ndeArestas = grafo.get(paiAtual).getArestas().size();
                for(int i = 0;i<ndeArestas;i++){
                    Aresta aresta = (Aresta) grafo.get(paiAtual).getArestas().get(i);
                    int idDoNoFilho = Integer.parseInt(aresta.getNo().getID())-1;
                    if(distancia[idDoNoFilho] <0){
                        distancia[idDoNoFilho] = distancia[paiAtual]+1;
                        pai[idDoNoFilho] = paiAtual;
                        fila.add(idDoNoFilho);//a aresta mapeada entra na fila para ser verificada seus "filhos" tambem
                    }
                }

            }
//return {pai,distancia}
            int[][] retorno =new int[2][qtdVertices];
            retorno[0] = pai;
            retorno[1] = distancia;
            return retorno;
        }
}
