package com.qStivi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class UndirectedGraph {

    ArrayList<Vertex> graph;

    public UndirectedGraph() {
        graph = new ArrayList<>();
    }

    public UndirectedGraph(Integer n) {
        graph = new ArrayList<>();
        for (var i = 0; i <= n; i++) {
            graph.add(new Vertex(null));
        }
    }

    public static void main(String[] args) throws Exception {
        var uciDataZachary = getUci();
        var twitterFollows = getTwitter();
        if (uciDataZachary == null || twitterFollows == null) throw new Exception("ERROR!");

        var maxUci = 0;
        for (int[] edges : uciDataZachary) {
            if (edges[0] > maxUci) maxUci = edges[0];
            if (edges[1] > maxUci) maxUci = edges[1];
        }
        var maxTwitter = 0;
        for (int[] edges : twitterFollows) {
            if (edges[0] > maxTwitter) maxTwitter = edges[0];
            if (edges[1] > maxTwitter) maxTwitter = edges[1];
        }

        var uciGraph = new UndirectedGraph(maxUci);
        for (int[] edges : uciDataZachary) {
            uciGraph.addVertex(edges[0]);
            uciGraph.addVertex(edges[1]);
            uciGraph.addEdge(edges[0], edges[1]);
        }
        System.out.println("Vertex: " + uciGraph.graph.get(1).value);
        for (Vertex vertex : uciGraph.graph.get(1).adjacencyList) {
            System.out.println(vertex.value);
        }

        var twitterGraph = new UndirectedGraph(maxTwitter);
        for (int[] edges : twitterFollows) {
            twitterGraph.addVertex(edges[0]);
            twitterGraph.addVertex(edges[1]);
            twitterGraph.addEdge(edges[0], edges[1]);
        }
        System.out.println("Vertex: " + twitterGraph.graph.get(35).value);
        for (Vertex vertex : twitterGraph.graph.get(35).adjacencyList) {
            System.out.println(vertex.value);
        }
    }

    private static ArrayList<int[]> getTwitter() {
        var list = new ArrayList<int[]>();

        var classloader = Thread.currentThread().getContextClassLoader();
        var url = classloader.getResource("soc-twitter-follows.txt");
        if (url == null) return null;
        try {
            var file = new File(url.toURI());
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                for (String line; (line = br.readLine()) != null; ) {
                    var a = line.split(" ")[0];
                    var b = line.split(" ")[1];
                    list.add(new int[]{Integer.parseInt(a), Integer.parseInt(b)});
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static ArrayList<int[]> getUci() {
        var list = new ArrayList<int[]>();

        var classloader = Thread.currentThread().getContextClassLoader();
        var url = classloader.getResource("out.ucidata-zachary.sec");
        if (url == null) return null;
        try {
            var file = new File(url.toURI());
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                for (String line; (line = br.readLine()) != null; ) {
                    var a = line.split(" ")[0];
                    var b = line.split(" ")[1];
                    list.add(new int[]{Integer.parseInt(a), Integer.parseInt(b)});
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void addVertex(Integer i) {
        graph.get(i).value = i;
    }

    public void addEdge(Integer i, Integer j) {
        graph.get(i).adjacencyList.add(graph.get(j));
        graph.get(j).adjacencyList.add(graph.get(i));
    }

    public void deleteEdge(Integer i, Integer j) throws Exception {
        graph.get(i).adjacencyList.remove(graph.get(j));
        graph.get(j).adjacencyList.remove(graph.get(i));
    }
}
