package julian;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedGraph {

    HashMap<Integer, HashSet<Integer>> edges; //(i1 -> i2)

    public DirectedGraph() {
        this.edges = new HashMap<>();
    }

    public DirectedGraph(Integer n) {
        this.edges = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            addVertex(i);
        }
    }

    public DirectedGraph(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        this.edges = new HashMap<>();

        String line;
        while ((line = br.readLine()) != null) {

            String[] vertices = line.split(" ");
            int vertex1 = Integer.parseInt(vertices[0]);
            int vertex2 = Integer.parseInt(vertices[1]);
            this.addVertex(vertex1);
            this.addVertex(vertex2);
            this.addEdge(vertex1, vertex2);
        }
    }

    public void addVertex(Integer i) {
        this.edges.put(i, new HashSet<>());
    }

    public void addEdge(Integer i, Integer j) {
        this.edges.get(i).add(j);
    }

    public void deleteEdge(Integer i, Integer j) {
        this.edges.get(i).remove(j);
    }

    public Set<Integer> getVertices() {
        return this.edges.keySet();
    }

    public Set<Integer> getNeighbors(Integer v) {
        return this.edges.get(v);
    }

    public void invertEdges() {

        HashMap<Integer, HashSet<Integer>> invertEdges = new HashMap<>();

        for(Integer v : this.edges.keySet()) {
            invertEdges.put(v, new HashSet<>());
        }

        for(Map.Entry<Integer, HashSet<Integer>> entry : this.edges.entrySet()) {
            for(Integer v : entry.getValue()) {
                invertEdges.get(v).add(entry.getKey());
            }
        }

        this.edges = invertEdges;
    }


    @Override
    public String toString() {
        return this.edges.toString();
    }

    public static void main(String[] args) throws IOException {

        DirectedGraph graph = new DirectedGraph(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        graph.invertEdges();
//        DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.ucidata-zachary.sec"));
        System.out.println(graph);
    }

}
