package BibliGraph;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Tutorial1 {
    public static void main(String args[]) {
        System.setProperty("org.graphstream.ui", "swing");

//        Graph graph = new SingleGraph("Tutorial 1");
//
//        Node a = graph.addNode("A");
//        a.setAttribute("ui.label", "A");
//        Node b = graph.addNode("B");
//        b.setAttribute("ui.label", "B");
//        Node c = graph.addNode("C");
//        c.setAttribute("ui.label", "C");
//        graph.addEdge("AB", a, b);
//        graph.addEdge("BC", b, c);
//        graph.addEdge("CA", c, a);

        Graph graph = new SingleGraph("tutorial 1");
        String styleSheet =
                "node {" +
                        "	fill-color: black;" +
                        "	size: 30px, 30px;" +
                        "   text-background-mode: plain;"+
                        "}" +
                        "node.marked {" +
                        "	fill-color: red;" +
                        "}";

        graph.setAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("AD", "A", "D");
        graph.addEdge("DE", "D", "E");
        graph.addEdge("DF", "D", "F");
        graph.addEdge("EF", "E", "F");

        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }


        graph.display();
    }
}