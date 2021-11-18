import grafo.No;
import grafo.SSSP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDIJKSTRA {
    public static void main(String[] args) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("src/entrada/entrada-ex2.txt"));
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
                        String palavrasChave = linha.trim();
                        vertices = Integer.parseInt(palavrasChave);
                        for (int i =0; i<vertices;i++){
                            String auxID =Integer.toString(i+1);
                            grafo.add(new No(auxID));
                            //System.out.println(grafo.get(i));
                        }

                        primeiraLinha = false;
                    } else {
                        String aresta = linha.trim();
                        aresta = aresta.replaceAll(",", ".");
                        String[] valores = aresta.split(" ");
                        int no1 = Integer.parseInt(valores[0]);
                        int no2 = Integer.parseInt(valores[1]);

                        grafo.get(no1-1).addArestas(grafo.get(no2-1), Double.parseDouble(valores[2]));
                        //f.addArestas(g);

                        //data[fornecedora][mesI][mesJ] = Double.parseDouble(valores[3]);
                    }

                }

            } else
                break;//uma vez , quando não houver mais linhas
            linha = buffRead.readLine();
        }
        buffRead.close();

        System.out.println("insira o numero do vertice que voce escolheu como a origem");
        int verticeOrigem = prompt.nextInt();
        System.out.println("voce escolheu o vertice "+verticeOrigem+" como origem:");



        SSSP sssp = new SSSP();
        List<No> grafoRes =new ArrayList<No>();
        grafoRes = sssp.run(grafo,(verticeOrigem-1));
        boolean cond = true;
        for(int i = 0;i < grafoRes.size();i++){
            cond = true;
            System.out.println(grafoRes.get(i).getID()+" custo: "+(grafoRes.get(i).getValue())+" no pai: "+(grafoRes.get(i).getAnterior().getID()));
            System.out.println(grafoRes.get(i).getID()+grafoRes.get(i).getCaminho());
        }

    }
}
