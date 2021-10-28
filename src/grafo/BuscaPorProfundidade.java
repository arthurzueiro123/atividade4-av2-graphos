package grafo;

import java.util.ArrayList;
import java.util.List;

public class BuscaPorProfundidade {

    private static int[] paiRec;
    private static boolean primeiraExec= true;




    public static int[] buscar(List<No> grafo, No origem) {

        List<Integer> pilha = new ArrayList<Integer>();
        //pilha.remove(pilha.size() - 1);//pop//desempilha
        //pilha.get(pilha.size() - 1);//top//topo da pilha
        //pilha.add();//push//empilha

        int qtdVertices = grafo.size();
        int valorOrigem = Integer.parseInt(origem.getID())-1;
        int[] pai = new int[qtdVertices];

        for(int i = 0; i< qtdVertices;i++){//atribui -1 pois nenhum vertice foi visitado ainda
            pai[i]=-1;
        }
        pai[valorOrigem]= valorOrigem;//pois o pai da origem é a propia origem
        pilha.add(valorOrigem);//pois o primeiro elemento a ser verificado é a propia origem

        while(pilha.size() != 0){
            int topoPilha = pilha.get(pilha.size() - 1);
            No verticeTopo = (No) grafo.get(topoPilha);
            for(int i =0; i<verticeTopo.getArestas().size();i++) {//percore todos os visinhos
                Aresta aresta = (Aresta) grafo.get(topoPilha).getArestas().get(i);
                int idDoNoFilho = Integer.parseInt(aresta.getNo().getID()) - 1;
                if(pai[idDoNoFilho] == -1){//se ouver algum visinho inexplorado
                    pai[idDoNoFilho] = topoPilha;
                    pilha.add(idDoNoFilho);
                    break;
                }
                if(i == verticeTopo.getArestas().size() - 1){//se ele não encontrar nenhum visinho inexplorado
                    pilha.remove(pilha.size() - 1);
                }
            }
            System.out.println(pilha);
        }

        return pai;
    }

    public static int [] buscaRecursiva(List<No> grafo, No visit){
        int aux = Integer.parseInt(visit.getID()) - 1;
        No verticeTopo = (No) grafo.get(aux);
        System.out.println(aux);

        if(primeiraExec){
            int qtdVertices = grafo.size();
            paiRec = new int[qtdVertices];
            for(int i = 0; i< qtdVertices;i++){//atribui -1 pois nenhum vertice foi visitado ainda
                paiRec[i]=-1;
            }
            paiRec[aux]=aux;
            primeiraExec = false;
            System.out.println(primeiraExec);
        }


        for(int i =0; i<verticeTopo.getArestas().size();i++) {//percore todos os visinhos
            Aresta aresta = (Aresta) verticeTopo.getArestas().get(i);
            int idDoNoFilho = Integer.parseInt(aresta.getNo().getID()) - 1;
            if(paiRec[idDoNoFilho] == -1){//se ouver algum visinho inexplorado
                paiRec[idDoNoFilho] = aux;
                //pilha.add(idDoNoFilho);
                buscaRecursiva(grafo,grafo.get(idDoNoFilho));
            }
        }

        return paiRec;
    }



}
