package andi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UndirectedGraph {

    private ArrayList<Integer>[] al;

    public UndirectedGraph() {
        this.al = null;
    }

    public UndirectedGraph(Integer n) {
        this.al = new ArrayList[n];
        Arrays.setAll(al, i -> new ArrayList<>());
    }

    public static void main(String[] args) throws IOException {
        UndirectedGraph graph = new UndirectedGraph(34);

        FileReader fr1 = new FileReader("out.ucidata-zachary.sec");
        BufferedReader br1 = new BufferedReader(fr1);

        String line1;
        while ((line1 = br1.readLine()) != null) {
            StringBuilder number = new StringBuilder();
            for (int i = 0; i < line1.length(); i++) {
                if (line1.charAt(i) == ' ') {
                    graph.addVertex(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                } else {
                    number.append(line1.charAt(i));
                }
            }
            graph.addVertex(Integer.parseInt(number.toString()));
        }
        br1.close();

        FileReader fr2 = new FileReader("out.ucidata-zachary.sec");
        BufferedReader br2 = new BufferedReader(fr2);

        String line2;
        while ((line2 = br2.readLine()) != null) {
            StringBuilder number1 = new StringBuilder();
            StringBuilder number = new  StringBuilder();
            for (int i = 0; i < line2.length(); i++) {
                if (line2.charAt(i) == ' ') {
                    number1 = number;
                    number = new StringBuilder();
                } else {
                    number.append(line2.charAt(i));
                }
            }
            graph.addEdge(Integer.parseInt(number1.toString()), Integer.parseInt(number.toString()));
        }
        br2.close();

        for (Integer ele : graph.al[0]) {
            System.out.println(ele);
        }
    }

    public void addVertex(Integer i) {
        if (linSearch(i) == -1) {
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

    private int linSearch(Integer i) {
        for (int j = 0; j < al.length; j++) {
            if (al[j].isEmpty()) {
                return -1;
            }
            if (al[j].get(0) == i) {
                return j;
            }
        }
        return -1;
    }
}
