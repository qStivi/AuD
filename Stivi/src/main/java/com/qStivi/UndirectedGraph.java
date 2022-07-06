package com.qStivi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class UndirectedGraph {

    ArrayList<Vertex> vertices;

    public UndirectedGraph() {
        vertices = new ArrayList<>();
    }

    public UndirectedGraph(Integer n) {
        vertices = new ArrayList<>();
        for (var i = 0; i <= n; i++) {
            vertices.add(new Vertex(null));
        }
    }

    public static UndirectedGraph createUciGraph() throws Exception {
        var uciDataZachary = getUci();
        if (uciDataZachary == null) throw new Exception("ERROR!");

        var maxUci = 0;
        for (int[] edges : uciDataZachary) {
            if (edges[0] > maxUci) maxUci = edges[0];
            if (edges[1] > maxUci) maxUci = edges[1];
        }

        var uciGraph = new UndirectedGraph(maxUci);
        for (int[] edges : uciDataZachary) {
            uciGraph.addVertex(edges[0]);
            uciGraph.addVertex(edges[1]);
            uciGraph.addEdge(edges[0], edges[1]);
        }

        return uciGraph;
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
        System.out.println("Vertex: " + uciGraph.vertices.get(1).value);
        for (Vertex vertex : uciGraph.vertices.get(1).adjacencyList) {
            System.out.println(vertex.value);
        }

        var twitterGraph = new UndirectedGraph(maxTwitter);
        for (int[] edges : twitterFollows) {
            twitterGraph.addVertex(edges[0]);
            twitterGraph.addVertex(edges[1]);
            twitterGraph.addEdge(edges[0], edges[1]);
        }
        System.out.println("Vertex: " + twitterGraph.vertices.get(35).value);
        for (Vertex vertex : twitterGraph.vertices.get(35).adjacencyList) {
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
        vertices.get(i).value = i;
    }

    public void addEdge(Integer i, Integer j) {
        vertices.get(i).adjacencyList.add(vertices.get(j));
        vertices.get(j).adjacencyList.add(vertices.get(i));
    }

    public void deleteEdge(Integer i, Integer j) {
        vertices.get(i).adjacencyList.remove(vertices.get(j));
        vertices.get(j).adjacencyList.remove(vertices.get(i));
    }
}
