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

//        DirectedGraph graph = new DirectedGraph(5);
////        graph.addEdge(1, 2);
////        graph.addEdge(2, 4);
////        graph.addEdge(1, 3);
////        graph.addEdge(3, 4);
//
//        graph.addEdge(1, 2);
//        graph.addEdge(2, 3);
//        graph.addEdge(3, 4);
//        graph.addEdge(4, 5);
//        graph.addEdge(5, 2);

        DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.moreno_taro_taro"));

        DfsAlgos dfsAlgos = new DfsAlgos();
        System.out.println(dfsAlgos.topSort(graph));
        System.out.println(dfsAlgos.detectCycle(graph));



    }
}
