package andi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DfsAlgos {
    private HashMap<Integer, String> color = new HashMap<>();
    //string is: "white", "grey" or "black"
    private HashMap<Integer, Integer> pre = new HashMap<>();

    public static void main(String[] args) throws Exception {
        DirectedGraph graph1 = new DirectedGraph(11);

        FileReader fr1 = new FileReader("outbnet.sec");
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

        DfsAlgos dfs = new DfsAlgos();
        dfs.topSort(graph1).forEach(System.out::println);
        //dfs.detectCycle(graph1).forEach(System.out::println);
    }

    public LinkedList<Integer> topSort(DirectedGraph g) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < g.al.size(); i++) {
            if (!g.al.get(i).isEmpty() && g.al.get(i).get(0) != null) {
                color.put(g.al.get(i).get(0), "white");
                pre.put(g.al.get(i).get(0), null);
            }
        }
        for (int i = 0; i < g.al.size(); i++) {
            if (!g.al.get(i).isEmpty() && color.get(g.al.get(i).get(0)).equals("white")) {
                dfsViseted(g, g.al.get(i).get(0), result);
            }
            if (result.contains(Integer.MIN_VALUE)) {
                return null;
            }
        }
        return result;
    }

    private void dfsViseted(DirectedGraph g, Integer u, LinkedList<Integer> list) {
        color.put(u, "grey");
        for (Integer v : g.al.get(u)) {
            if (color.get(v).equals("white")) {
                pre.put(v, u);
                dfsViseted(g, v, list);
            } else if (color.get(v).equals("grey") && !v.equals(u)) {
                list = new LinkedList<>();
                list.add(Integer.MIN_VALUE);
            }
        }
        list.add(g.al.get(u).get(0));
        color.put(u, "black");
    }

    private void dfsViseted2(DirectedGraph g, Integer u, LinkedList<Integer> list) {
        color.put(u, "grey");
        for (Integer v : g.al.get(u)) {
            if (color.get(v).equals("white")) {
                pre.put(v, u);
                dfsViseted2(g, v, list);
            } else if (color.get(v).equals("grey") && !v.equals(u)) {
                list.add(v);
                Integer temp = u;
                while (!temp.equals(v)) {
                    list.add(temp);
                    temp = pre.get(temp);
                }
            }
        }
        color.put(u, "black");
    }


    public LinkedList<Integer> detectCycle(DirectedGraph g) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < g.al.size(); i++) {
            if (!g.al.get(i).isEmpty() && g.al.get(i).get(0) != null) {
                color.put(g.al.get(i).get(0), "white");
                pre.put(g.al.get(i).get(0), null);
            }
        }

        for (int i = 0; i < g.al.size(); i++) {
            if (!g.al.get(i).isEmpty() && color.get(g.al.get(i).get(0)).equals("white")) {
                dfsViseted2(g, g.al.get(i).get(0), result);
                if (!result.isEmpty()) {
                    return result;
                }
            }
        }
        return null;
    }
}
