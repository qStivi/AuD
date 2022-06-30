package andi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class BfsIterator {

    private UndirectedGraph graph;
    private HashMap<Integer, String> color;
    //string is: "white", "grey" or "black"
    private HashMap<Integer, Integer> disToStart;
    private Queue<Integer> queue;

    public static void main(String[] args) throws Exception {
        UndirectedGraph graph1 = new UndirectedGraph(34);

        FileReader fr1 = new FileReader("out.ucidata-zachary.sec");
        BufferedReader br1 = new BufferedReader(fr1);

        String line1;
        while ((line1 = br1.readLine()) != null) {
            String[] vertices = line1.split(" ");
            int vertex1 = Integer.parseInt(vertices[0]);
            int vertex2 = Integer.parseInt(vertices[1]);
            graph1.addVertex(vertex1);
            graph1.addVertex(vertex2);
            graph1.addEdge(vertex1, vertex2);
        }
        br1.close();

        BfsIterator bfs = new BfsIterator(graph1, 24);

        Integer node = bfs.next();
        while (node != null) {
            System.out.println(node);
            node = bfs.next();
        }

        System.out.println(bfs.dist(4));

    }

    public BfsIterator(UndirectedGraph g, Integer s) {
        this.graph = g;
        this.color = new HashMap<>();
        this.disToStart = new HashMap<>();

        for (int i = 0; i < this.graph.al.length; i++) {
            color.put(this.graph.al[i].get(0), "white");
            disToStart.put(this.graph.al[i].get(0), Integer.MAX_VALUE);
        }
        color.put(s, "grey");
        disToStart.put(s, 0);
        this.queue = new PriorityQueue<>();
        queue.add(s);
    }

    public Integer next() {
        if (queue.isEmpty()) {
            return null;
        }
        Integer u = queue.poll();
        for (Integer v : this.graph.al[this.graph.linSearch(u)]) {
            if (color.get(v).equals("white")) {
                color.put(v, "grey");
                disToStart.put(v, disToStart.get(u) + 1);
                queue.add(v);
            }
        }
        color.put(u, "black");

        return queue.peek();
    }

    public Integer dist(Integer v) {
        if (color.get(v).equals("white")) {
            if (this.next() == null) {
                return -1;
            }
            return null;
        }
        return disToStart.get(v);
    }



}
