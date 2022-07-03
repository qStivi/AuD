package andi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DirectedGraph {
    public HashMap<Integer, ArrayList<Integer>> al;


    public DirectedGraph() {
        this.al = null;
    }

    public DirectedGraph(Integer n) {
        this.al = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            al.put(i, new ArrayList<Integer>());
        }
    }

    public void addVertex(Integer i) {
        if (!al.get(i).contains(i)) {
            al.get(i).add(i);
        }
    }

    public void addEdge(Integer i, Integer j) {
        if (!al.get(i).contains(j)) {
            al.get(i).add(j);
        }
    }

    public void deleteEdge(Integer i, Integer j) {
        if (al.get(i).contains(j)) {
            al.get(i).remove(j);
        }
    }
}
