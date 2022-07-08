package com.qStivi;

import java.util.LinkedList;

public class DfsAlgos {

    public LinkedList<Integer> topSort(DirectedGraph g) {

        var list = new LinkedList<Integer>();
        var vertices = g.vertices;

        for (Vertex vertex : vertices) {
            vertex.color = Color.WHITE;
        }

        for (Vertex v : vertices) {
            if (v.value == null) continue;
            var circle = false;
            if (v.color == Color.WHITE) circle = dfsVisit(g, v, list);
            if (circle) {
                return null;
            }
        }


        return list;
    }

    private boolean dfsVisit(DirectedGraph g, Vertex v, LinkedList<Integer> list) {
        v.color = Color.GRAY;

        for (Vertex vertex : v.adjacencyList) {
            vertex.pi = v;

            if (vertex.color == Color.WHITE) {
                dfsVisit(g, vertex, list);
            } else if (vertex.color == Color.GRAY) {
                return true;
            }
        }
        v.color = Color.BLACK;
        list.offerFirst(v.value);
        return false;
    }

    public LinkedList<Integer> detectCycle(DirectedGraph g) {

        var list = new LinkedList<Integer>();
        var vertices = g.vertices;

        for (Vertex vertex : vertices) {
            vertex.color = Color.WHITE;
            vertex.pi = null;
        }

        for (Vertex v : vertices) {
            if (v.value == null) continue;
            if (v.color == Color.WHITE) list = dfsVisitCirlce(g, v);
            if (!list.isEmpty()) return list;
        }

        return null;
    }

    private LinkedList<Integer> dfsVisitCirlce(DirectedGraph g, Vertex v) {
        v.color = Color.GRAY;
        var list = new LinkedList<Integer>();

        for (Vertex vertex : v.adjacencyList) {
            vertex.pi = v;

            if (vertex.color == Color.WHITE) {
                list = dfsVisitCirlce(g, vertex);
                if (!list.isEmpty()) return list;
            } else if (vertex.color == Color.GRAY) {
                var current = v;

                while (current != vertex) {
                    if (current == null) continue;
                    list.offerFirst(current.value);
                    current = current.pi;
                }
                list.offerFirst(vertex.value);
                return list;
            }
        }
        v.color = Color.BLACK;
        return list;
    }


}
