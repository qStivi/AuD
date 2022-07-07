package julian;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DfsAlgos {

    HashMap<Integer, Integer> assignedVertexColor;
    HashMap<Integer, Integer> parentNode;
    boolean circleDetected;
    LinkedList<Integer> list;

    private int getVertexColor(Integer v) {
        return this.assignedVertexColor.get(v);
    }
    private void setVertexColor(Integer v, int color) {
        this.assignedVertexColor.put(v, color);
    }
    private void setParentNode(Integer v, Integer u) {
        this.parentNode.put(v, u);
    }
    private Integer getParentNode(Integer v) {
        return this.parentNode.get(v);
    }

    public LinkedList<Integer> dfs(DirectedGraph g, boolean reconstructCircle) {

        this.parentNode = new HashMap<>();
        this.assignedVertexColor = new HashMap<>();
        this.circleDetected = false;
        this.list = new LinkedList<>();

        for(Integer v : g.getVertices()) {
            setVertexColor(v, 0);
        }

        for(Integer v : g.getVertices()) {

            if(circleDetected) {
                break;
            }

            if(getVertexColor(v) == 0) {
                dfsVisit(g, v, reconstructCircle);
            }
        }

        return this.list;
    }

    public void dfsVisit(DirectedGraph g, Integer u, boolean reconstructCircle) {

        setVertexColor(u, 1);

        for(Integer v : g.getNeighbors(u)) {
            setParentNode(v, u);

            if(getVertexColor(v) == 1) {
                circleDetected = true;
                if(reconstructCircle) {
                    this.list = constructCircle(v);
                }
                break;
            }

            if(getVertexColor(v) == 0) {
                dfsVisit(g, v, reconstructCircle);
            }
        }

        setVertexColor(u, 2);

        if(!reconstructCircle) {
            this.list.addFirst(u);
        }
    }

    public LinkedList<Integer> constructCircle(Integer startNode) {

        LinkedList<Integer> circle = new LinkedList<>();
        Integer currentNode = getParentNode(startNode);

        while(!Objects.equals(currentNode, startNode)) {
            circle.addFirst(currentNode);
            currentNode = getParentNode(currentNode);
        }

        circle.addFirst(startNode);
        return circle;
    }

    public LinkedList<Integer> topSort(DirectedGraph g) {

        LinkedList<Integer> output = dfs(g, false);

        if(circleDetected) {
            return null;
        }

        return output;
    }

    public LinkedList<Integer> detectCycle(DirectedGraph g) {

        LinkedList<Integer> output = dfs(g, true);

        if(!circleDetected) {
            return null;
        }

        return output;
    }

    public static void main(String[] args) throws IOException {

        DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.bnet_bnet.bnet"));
        //[2, 10, 4, 1, 9, 7, 3, 8, 6, 5]
        //null

        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.maayan-figeys"));
        //null
        //[476, 473]

        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.moreno_dense_comm"));
        //[12, 26, 38, 23, 39, 2, 54, 31, 40, 18, 49, 3, 44, 55, 8, 50, 41, 24, 27, 43, 30, 9, 20, 19, 16, 10, 17, 32, 46, 28, 52, 13, 29, 15, 25, 42, 14, 51, 34, 35, 45, 53, 33, 36, 37, 4, 6, 7, 11, 5, 47, 22, 48, 21, 1]
        //null

        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.moreno_taro_taro"));
        //null
        //[1, 4]

        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.simple_simple.simple"));
        //[111, 106, 100, 96, 93, 62, 90, 71, 43, 31, 48, 107, 85, 101, 108, 58, 102, 16, 55, 104, 51, 53, 46, 64, 84, 50, 78, 110, 38, 29, 79, 23, 33, 112, 17, 37, 63, 56, 49, 41, 86, 65, 39, 9, 24, 69, 74, 70, 3, 34, 32, 89, 15, 7, 14, 25, 52, 54, 22, 75, 73, 68, 99, 60, 26, 5, 57, 36, 81, 77, 2, 1, 44, 11, 10, 61, 28, 103, 13, 109, 76, 27, 82, 20, 83, 40, 98, 6, 94, 105, 18, 12, 67, 80, 21, 4, 92, 42, 8, 45, 72, 59, 35, 95, 88, 87, 66, 30, 91, 97, 47, 19]
        //null

        DfsAlgos dfsAlgos = new DfsAlgos();
        System.out.println(dfsAlgos.topSort(graph));
        System.out.println(dfsAlgos.detectCycle(graph));

    }
}
