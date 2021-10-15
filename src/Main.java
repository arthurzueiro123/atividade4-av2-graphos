import libGraphica.GraphDraw;
import grafo.BuscaPorLargura;
import grafo.No;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader("src/entrada/entrada1.txt"));
        //BufferedReader buffRead = new BufferedReader(new FileReader("src/EntradaAV3_UsarAMaior/ingenua_entrada_120_100.txt"));
        String linha = "";



        Scanner prompt = new Scanner(System.in);
        boolean primeiraLinha = true;
        int vertices;


        List<No> grafo =new ArrayList<No>();



        while (true) {//(n+1) sendo q n e o numero de linhas do arquivo
            if (linha != null) {
                if (linha != "") {//(n) vezes
                    if (primeiraLinha) {
                        //System.out.println(linha);
                        String palavrasChave = linha.trim();
                        //String[] valores = palavrasChave.split(" ");
                        vertices = Integer.parseInt(palavrasChave);
                        for (int i =0; i<vertices;i++){
                            grafo.add(new No(Integer.toString(i+1)));
                        }

                        primeiraLinha = false;
                    } else {
                        String aresta = linha.trim();
                        aresta = aresta.replaceAll(",", ".");
                        String[] valores = aresta.split(" ");
                        int no1 = Integer.parseInt(valores[0]);
                        int no2 = Integer.parseInt(valores[1]);

                        grafo.get(no1-1).addArestas(grafo.get(no2-1));
                        //f.addArestas(g);

                        //data[fornecedora][mesI][mesJ] = Double.parseDouble(valores[3]);
                    }

                }

            } else
                break;//uma vez , quando não houver mais linhas
            linha = buffRead.readLine();
        }
        buffRead.close();



        //pegar estado com maior quantidade de vizinhos(retorna o primeiro encontrado)
        No maisVertices = null;
        int qtdVertices=0;

        for (int i = 0; i < grafo.size(); i++) {
            No aux = (No) grafo.get(i);

            if(qtdVertices< aux.getArestas().size()){
                qtdVertices = aux.getArestas().size();
                maisVertices = (No) grafo.get(i);
            }
        }
        int maiorGrau=qtdVertices;
        //System.out.println("estado com mais vizinhos: "+maisVertices.getID());




        //pegar o vertice com menor grau
        No menosVertices = null;
        //qtdVertices não se altera pois ja esta com o maior valor da lista

        for (int i = 0; i < grafo.size(); i++) {
            No aux = (No) grafo.get(i);

            if(qtdVertices> aux.getArestas().size()){
                qtdVertices = aux.getArestas().size();
                menosVertices = (No) grafo.get(i);
            }
        }
        int menorGrau = qtdVertices;








        //System.out.println(grafo.get(0).getArestas());
//        for (int j = 0; j < grafo.get(0).getArestas().size(); j++) {
//            Aresta aux = (Aresta) grafo.get(0).getArestas().get(j);
//            System.out.println("no "+aux.getNo().getID());
//        }

        int qtdTotalDeVertices = grafo.size();
        int qtdTotalDeArestas=0;

        for (int i = 0; i < grafo.size(); i++) {
            No aux = (No) grafo.get(i);
            qtdTotalDeArestas += aux.getArestas().size();
        }

        qtdTotalDeArestas = (qtdTotalDeArestas/2);






        int somatGrauVertices=0;
        for (int i = 0; i < grafo.size(); i++) {
            No aux = (No) grafo.get(i);
            somatGrauVertices += aux.getArestas().size();

        }

        float grauMedio= (somatGrauVertices/qtdTotalDeVertices);

        //chamando a busca por largura

        System.out.println("insira o numero do vertice que voce escolheu como a origem");
        int verticeOrigem = prompt.nextInt();
        System.out.println("voce escolheu o vertice "+verticeOrigem+" como origem:");

        int[][] res = BuscaPorLargura.buscar(grafo,grafo.get(verticeOrigem-1));
        for(int i = 0;i < res[0].length;i++){
            System.out.println((i+1)+" Nó pai: "+(res[0][i]+1)+" distancia a origem: "+res[1][i]);
        }



        FileWriter arq = new FileWriter("src/saida/saida.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("esse grafo possui%n");
        gravarArq.printf("numero de vertices igua a: "+qtdTotalDeVertices+"%n");
        gravarArq.printf("numero de arestas igua a: "+qtdTotalDeArestas+"%n");
        gravarArq.printf("o valor do grau maximo desse grafo é igua a: "+maiorGrau+"%n");
        gravarArq.printf("o valor do grau minimo desse grafo é igua a: "+menorGrau+"%n");
        gravarArq.printf("o valor do grau medio desse grafo é igua a: "+grauMedio+"%n");
        gravarArq.printf("+-------------+%n");
        for(int i = 0;i < res[0].length;i++){
            gravarArq.printf((i+1)+" Nó pai: "+(res[0][i]+1)+" distancia a origem: "+res[1][i]+"%n");
        }

        arq.close();

        System.out.printf("\n arquivo foi gravada com sucesso em \"src/saida/saida.txt\n");




        GraphDraw frame = new GraphDraw("Test Window");
        int larguraFrame = 400;
        frame.setSize(larguraFrame,300);
        frame.setVisible(true);


//        frame.addNode("a", 50,50);
//        frame.addNode("b", 100,100);
//        frame.addNode("longNode", 200,200);
        //frame.addEdge(0,1);
//        frame.addEdge(0,2);

        Random gerador = new Random();

        boolean alterna = true;
        int xAtual =50;
        int multY=1;
        int multx=0;
        for(int i = 0;i < qtdTotalDeVertices;i++){
            if(alterna) {
                frame.addNode(Integer.toString(i + 1), xAtual, 50+((multY-1)*60+(gerador.nextInt(30))));
                alterna = false;
            }else{
                frame.addNode(Integer.toString(i + 1), xAtual, 50+(multY*60)+(gerador.nextInt(20)));
                multx++;
                xAtual=50+(multx*60);
                if(xAtual>larguraFrame){
                    multY+=2;
                }
                alterna = true;
            }
        }

        for(int i = 0;i < res[0].length;i++){
            frame.addEdge(i,res[0][i]);
        }

    }
}
