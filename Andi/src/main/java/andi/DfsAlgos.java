package andi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DfsAlgos {
    private HashMap<Integer, String> color;
    //string is: "white", "grey" or "black"
    private HashMap<Integer, Integer> pre;

    public LinkedList<Integer> topSort(DirectedGraph g) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < g.al.size(); i++) {
            if (g.al.get(i).get(0) != null) {
                color.put(g.al.get(i).get(0), "white");
                pre.put(g.al.get(i).get(0), null);
            }
        }
        for (int i = 0; i < g.al.size(); i++) {
            if (color.get(g.al.get(i).get(0)).equals("white")) {
                result.add(g.al.get(i).get(0));
                dfsVideted(g, g.al.get(i).get(0));
            }
            if (color.get(g.al.get(i).get(0)).equals("grey")) {
                return null;
            }
        }
        return result;
    }

    private void dfsVideted(DirectedGraph g, Integer u) {
        color.put(u, "grey");
        for (Integer v : g.al.get(u)) {
            if (color.get(v).equals("white")) {
                pre.put(v, u);
                dfsVideted(g, v);
            }
        }
        color.put(u, "black");
    }


    public LinkedList<Integer> detectCycle(DirectedGraph g) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < g.al.size(); i++) {
            if (g.al.get(i).get(0) != null) {
                color.put(g.al.get(i).get(0), "white");
                pre.put(g.al.get(i).get(0), null);
            }
        }

        for (int i = 0; i < g.al.size(); i++) {
            if (color.get(g.al.get(i).get(0)).equals("white")) {
                dfsVideted(g, g.al.get(i).get(0));
            }
            if (color.get(g.al.get(i).get(0)).equals("grey")) {
                result.add(g.al.get(i).get(0));
            }
        }
        return result;
    }
}
