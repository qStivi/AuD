package julian;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.PriorityQueue;

public class BfsIterator {

    private final UndirectedGraph graph;
    private final HashMap<Integer, Integer> assignedColor;
    //weiss = 0, grau = 1, schwarz = 2
    private final HashMap<Integer, Integer> distToStart;
    private final Queue<Integer> queue;

    public BfsIterator(UndirectedGraph g, Integer s) {
        this.graph = g;
        this.assignedColor = new HashMap<>();
        this.distToStart = new HashMap<>();

        for(Integer vertex : this.graph.getVertices()) {
            assignedColor.put(vertex, 0);
            distToStart.put(vertex, Integer.MAX_VALUE);
        }
        assignedColor.put(s, 1);
        distToStart.put(s, 0);
        queue = new PriorityQueue<>();
        queue.add(s);
    }

    public Integer next() {

        if(queue.isEmpty()) {
            return null;
        }

        Integer vertex = queue.poll();
        for(Integer neighbor : this.graph.getNeighbors(vertex)) {
            if(assignedColor.get(neighbor).equals(0)) {
                assignedColor.put(neighbor, 1);
                distToStart.put(neighbor, distToStart.get(vertex) + 1);
                queue.add(neighbor);
            }
        }
        assignedColor.put(vertex, 2);

        return queue.peek();
    }

    public Integer dist(Integer v) {

        if(assignedColor.get(v).equals(0)) { //Noch nicht besucht = weis
            if(this.next() == null) {
                return -1;
            }
            return null;
        }

        return distToStart.get(v);
    }

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        UndirectedGraph graph1 = new UndirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.ucidata-zachary.sec"));
        //UndirectedGraph graph2 =  new UndirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/soc-twitter-follows.txt"));
        ArrayList<Integer> vertices1 = graph1.getVertices();
//        ArrayList<Integer> vertices2 = graph2.getVertices();
        //BfsIterator bfs1 = new BfsIterator(graph1, vertices1.get(random.nextInt(0, vertices1.size() - 1)));
        BfsIterator bfs1 = new BfsIterator(graph1, 5);
//        BfsIterator bfs2 = new BfsIterator(graph2, vertices2.get(random.nextInt(0, vertices2.size() - 1)));

        Integer nextNode = bfs1.next();
        while(nextNode != null) {
            System.out.println(nextNode);
            nextNode = bfs1.next();
        }

        for(Integer vertex : vertices1)  {
            System.out.println(bfs1.dist(vertex));
        }

//        nextNode = bfs2.next();
//        while(nextNode != null) {
//            nextNode = bfs2.next();
//        }
//
//        for(Integer vertex : vertices2)  {
//            System.out.println(bfs2.dist(vertex));
//        }
    }
}
