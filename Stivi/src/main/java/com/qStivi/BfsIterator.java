package com.qStivi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class BfsIterator {

    private final UndirectedGraph undirectedGraph;

    private final HashMap<Vertex, Color> colors;

    private final HashMap<Vertex, Integer> distances;

    private final Queue<Vertex> queue;

    public BfsIterator(UndirectedGraph g, Integer s) {
        this.undirectedGraph = g;
        colors = new HashMap<>();
        distances = new HashMap<>();

        var start = this.undirectedGraph.vertices.get(s);
        ArrayList<Vertex> vertices = this.undirectedGraph.vertices;
        for (int i = 1; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            colors.put(vertex, Color.WHITE);
            distances.put(vertex, Integer.MAX_VALUE);
        }
        colors.put(start, Color.GRAY);
        distances.put(start, 0);
        queue = new PriorityQueue<>();
        queue.offer(start);
    }

    public static void main(String[] args) throws Exception {
        var graph = UndirectedGraph.createUciGraph();
        var bfs = new BfsIterator(graph, 5);
        var next = bfs.next();
        while (next != null) {
            System.out.println(next);
            next = bfs.next();
        }
        System.out.println("_____");
        ArrayList<Vertex> vertices = bfs.undirectedGraph.vertices;
        for (int i = 1; i < vertices.size(); i++) {
            Vertex v = vertices.get(i);
            System.out.println(bfs.dist(v.value));
        }
    }

    public Integer next() {
        Vertex u = null;
        if (!queue.isEmpty()) {
            u = queue.poll();
            for (Vertex v : u.adjacencyList) {
                if (colors.get(v) == Color.WHITE) {
                    colors.put(v, Color.GRAY);
                    distances.put(v, distances.get(u) + 1);
                    if (!queue.offer(v)) System.out.println("wtf");
                }
            }
            colors.put(u, Color.BLACK);
        }
        return u == null ? null : u.value;
    }

    public Integer dist(Integer v) {
        var vertex = this.undirectedGraph.vertices.get(v);
        if (colors.get(vertex) != Color.WHITE) {
            return distances.get(vertex);
        } else {
            if (queue.isEmpty()) return -1;
            return null;
        }
    }


}
