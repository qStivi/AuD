package andi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UndirectedGraph {

    public ArrayList<Integer>[] al;

    public UndirectedGraph() {
        this.al = null;
    }

    public UndirectedGraph(Integer n) {
        this.al = new ArrayList[n];
        Arrays.setAll(al, i -> new ArrayList<>());
    }

    public static void main(String[] args) throws IOException {
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


        UndirectedGraph graph2 = new UndirectedGraph(500000);

        FileReader fr2 = new FileReader("soc-twitter-follows.txt");
        BufferedReader br2 = new BufferedReader(fr2);

        String line2;
        int counter = 1;
        while ((line2 = br2.readLine()) != null) {
            String[] vertices = line2.split(" ");
            int vertex1 = Integer.parseInt(vertices[0]);
            int vertex2 = Integer.parseInt(vertices[1]);
            graph2.addVertex(vertex1);
            graph2.addVertex(vertex2);
            graph2.addEdge(vertex1, vertex2);
            System.out.println(counter);
            counter++;
        }
        br2.close();

        for (Integer ele : graph2.al[0]) {
            System.out.println(ele);
        }
    }

    public void addVertex(Integer i) {
        if (linSearch(i) == -2) {
            for (int j = 0; j < al.length; j++) {
                if (al[j].isEmpty()) {
                    al[j].add(i);
                    break;
                }
            }
        }
    }

    public void addEdge(Integer i, Integer j) {
        // Ich gehe jetzt mal davon aus, dass niemand eine Kante mit nicht existenten Knoten einfügt.
        if (!al[linSearch(i)].contains(j)) {
            al[linSearch(i)].add(j);
        }
        if (!al[linSearch(j)].contains(i)) {
            al[linSearch(j)].add(i);
        }
    }

    public void deleteEdge(Integer i, Integer j) {
        al[linSearch(i)].remove(j);
        al[linSearch(j)].remove(i);
    }

    public int linSearch(Integer i) {
        for (int j = 0; j < al.length; j++) {
            if (al[j].isEmpty()) {
                return -2;
            }
            if (al[j].get(0).equals(i)) {
                return j;
            }
        }
        return -1;
    }
}
