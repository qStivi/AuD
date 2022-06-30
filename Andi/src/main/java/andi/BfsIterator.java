package andi;

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

    public BfsIterator(UndirectedGraph g, Integer s) {
        this.graph = g;
        this.color = new HashMap<>();
        this.disToStart = new HashMap<>();

        for(int i = 0; i < this.graph.al.length; i ++) {
            color.put(this.graph.al[i].get(0), "white");
            disToStart.put(this.graph.al[i].get(0), Integer.MAX_VALUE);
        }
        color.put(s, "grey");
        disToStart.put(s, 0);
        this.queue = new PriorityQueue<>();
        queue.add(s);
    }

    public Integer next() {
        if (this.queue.isEmpty()) {
            return null;
        }
        Integer u = queue.poll();
        for () {

        }

        return null;
    }

}
