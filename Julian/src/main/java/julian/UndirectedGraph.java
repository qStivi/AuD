package julian;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class UndirectedGraph {

    ArrayList<LinkedList<Integer>> adjacentList; //An Stelle 1 Knoten 1, usw...

    public UndirectedGraph() {
        this.adjacentList = new ArrayList<>(0);
        this.adjacentList.add(null); //An Stelle 0 kein Knoten 0
    }

    public UndirectedGraph(Integer n) {
        this.adjacentList = new ArrayList<>(0);
        this.adjacentList.add(0, null);

        for(int i = 1; i <= n; i++) {
            this.adjacentList.add(i, new LinkedList<>());
        }
    }

    public UndirectedGraph(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        this.adjacentList = new ArrayList<>(0);
        this.adjacentList.add(0, null);

        int i = 0;
        String line;
        while((line = br.readLine()) != null) {

            String[] vertices = line.split(" ");
            int vertex1 = Integer.parseInt(vertices[0]);
            int vertex2 = Integer.parseInt(vertices[1]);
            this.addVertex(vertex1);
            this.addVertex(vertex2);
            this.addEdge(vertex1, vertex2);
        }
    }

    public void addVertex(Integer i) {

        if(i > adjacentList.size() - 1) {

            int n = i + 1 - adjacentList.size();

            while(n > 0) {
                adjacentList.add(null);
                n--;
            }
        }

        if(adjacentList.get(i) != null) {
            return;
        }

        this.adjacentList.set(i, new LinkedList<>());
    }

    public void addEdge(Integer i, Integer j) {

        if(adjacentList.get(i) == null) {
            throw new IllegalArgumentException("Node " + i + " does not exist");
        }

        if(adjacentList.get(j) == null) {
            throw new IllegalArgumentException("Node " + j + " does not exist");
        }

        this.adjacentList.get(i).add(j);
        this.adjacentList.get(j).add(i);
    }

    public void deleteEdge(Integer i, Integer j) {

        if(adjacentList.get(i) == null) {
            throw new IllegalArgumentException("Node " + i + " does not exist");
        }

        if(adjacentList.get(j) == null) {
            throw new IllegalArgumentException("Node " + j + " does not exist");
        }

        this.adjacentList.get(i).remove(j);
        this.adjacentList.get(j).remove(i);
    }

    public ArrayList<Integer> getVertices() {

        ArrayList<Integer> vertices = new ArrayList<>();

        for(int i = 1; i < this.adjacentList.size(); i++) {
            if(this.adjacentList.get(i) != null) {
                vertices.add(i);
            }
        }

        return vertices;
    }

    public LinkedList<Integer> getNeighbors(Integer vertex) {
        return this.adjacentList.get(vertex);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < this.adjacentList.size(); i++) {

            if(this.adjacentList.get(i) == null) {
                continue;
            }

            LinkedList<Integer> neighbors = this.adjacentList.get(i);

            sb.append(i);
            sb.append(" = ");

            sb.append("[");
            for(Integer neighbor : neighbors) {
                sb.append(neighbor);
                sb.append(", ");
            }
            sb.replace(sb.length() - 2, sb.length(), "");

            sb.append("], ");
        }

        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {

//        UndirectedGraph graph = new UndirectedGraph(10);
//        graph.addVertex(11);
//        graph.addEdge(11, 1);
//        graph.addVertex(11);

        //UndirectedGraph graph = new UndirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.ucidata-zachary.sec"));
        UndirectedGraph graph = new UndirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/soc-twitter-follows.txt"));

        ArrayList<Integer> test = graph.getVertices();

        for(int i = 1; i < test.size(); i++) {
            if(test.get(i) == null) {
                System.out.println("Hilfe");
            }
        }
        System.out.println("Fertig");
    }

}
