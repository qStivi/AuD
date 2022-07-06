package julian;

import java.io.IOException;
import java.util.*;

public class DfsAlgos {

    HashMap<Integer, Integer> assignedVertexColor;
    HashMap<ArrayList<Integer>, Integer> assignedEdgeColor;
    HashMap<Integer, Integer> finishedTime;
    int time;

    private int getVertexColor(Integer v) {
        return this.assignedVertexColor.get(v);
    }

    private void setVertexColor(Integer v, int color) {
        this.assignedVertexColor.put(v, color);
    }

    private void setEdgeColor(Integer u, Integer v, int color) {
        ArrayList<Integer> edge = new ArrayList<>(2);
        edge.add(0, u);
        edge.add(1, v);
        this.assignedEdgeColor.put(edge, color);
    }

    public LinkedList<Integer> dfs(DirectedGraph g) {

        LinkedList<Integer> list = new LinkedList<>();
        this.finishedTime = new HashMap<>();
        this.assignedVertexColor = new HashMap<>();
        this.assignedEdgeColor = new HashMap<>();

        for(Integer v : g.getVertices()) {
            setVertexColor(v, 0);
        }

        this.time = 0;

        for(Integer v : g.getVertices()) {
            if(getVertexColor(v) == 0) {
                dfsVisit(g, v, list);
            }
        }

        return list;
    }

    public void dfsVisit(DirectedGraph g, Integer u, LinkedList<Integer> list) {

        setVertexColor(u, 1);

        time = time + 1;

        for(Integer v : g.getNeighbors(u)) {
            setEdgeColor(u, v, getVertexColor(v));

            if(getVertexColor(v) == 0) {
                dfsVisit(g, v, list);
            }
        }

        setVertexColor(u, 2);
        time = time + 1;
        setFinishedTime(u, time);
        list.addFirst(u);
    }

    public LinkedList<Integer> topSort(DirectedGraph g) {

        LinkedList<Integer> output = dfs(g);

        for(int color : assignedEdgeColor.values()) {
            if(color == 1) {
                return null;
            }
        }

        return output;
    }

    public LinkedList<Integer> detectCycle(DirectedGraph g) {

        dfs(g);

        ArrayList<Integer> nodesOrderedByfValue = new ArrayList<>(g.getVertices());
        nodesOrderedByfValue.sort((v1, v2) -> getFinishedTime(v2) - getFinishedTime(v1));

        g.invertEdges();

        this.assignedVertexColor = new HashMap<>();
        this.assignedEdgeColor = new HashMap<>();

        for(Integer v : g.getVertices()) {
            setVertexColor(v, 0);
        }


        for (Integer v : nodesOrderedByfValue) {
            if (getVertexColor(v) == 0) {
                dfsVisitCycleHelper(g, v);
            }
        }

        Integer v;

        LinkedList<Integer> circleCandidates = new LinkedList<>();

        for(Map.Entry<ArrayList<Integer>, Integer> edge : this.assignedEdgeColor.entrySet()) {
            if(edge.getValue() == 0) {

                Integer v1 = edge.getKey().get(0);
                Integer v2 = edge.getKey().get(1);

                circleCandidates.add(v2);
                circleCandidates.add(v1);
            }
        }

        if(circleCandidates.isEmpty()) {
            return null;
        }

        return getCircle(circleCandidates);
    }

    private LinkedList<Integer> getCircle(LinkedList<Integer> list) {

        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                if(list.get(i).equals(list.get(j))) {
                    return cutSubList(list, i, j - 1);
                }
            }
        }

        return cutSubList(list, 0, 1);
    }

    private LinkedList<Integer> cutSubList(LinkedList<Integer> list, int start, int end) {

        LinkedList<Integer> output = new LinkedList<>();

        for(int i = start; i < end + 1; i++) {
            output.add(list.get(i));
        }

        return output;
    }

    public void dfsVisitCycleHelper(DirectedGraph g, Integer u) {

        setVertexColor(u, 1);

        for(Integer v : g.getNeighbors(u)) {
            setEdgeColor(u, v, getVertexColor(v));

            if(getVertexColor(v) == 0) {
                dfsVisitCycleHelper(g, v);
            }
        }

        setVertexColor(u, 2);
    }

    private void setFinishedTime(Integer v, int time) {
        this.finishedTime.put(v, time);
    }

    private Integer getFinishedTime(Integer v) {
        return this.finishedTime.get(v);
    }

    public static void main(String[] args) throws IOException {

        DirectedGraph graph = new DirectedGraph(5);
////        graph.addEdge(1, 2);
////        graph.addEdge(2, 3);
////        graph.addEdge(3, 4);
////        graph.addEdge(4, 1);
//
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(5, 3);
        graph.addEdge(4, 5);
        graph.addEdge(2, 4);
        graph.addEdge(4, 1);

        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.bnet_bnet.bnet"));
        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.maayan-figeys"));
        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.moreno_dense_comm"));
        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.moreno_taro_taro"));
        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/out.simple_simple.simple"));
        //DirectedGraph graph = new DirectedGraph(new File("/Users/julian/Documents/AuD/Julian/src/resources/twitter_combined.txt"));
        DfsAlgos dfsAlgos = new DfsAlgos();
        System.out.println(dfsAlgos.detectCycle(graph));
        //System.out.println(dfsAlgos.topSort(graph));
        //System.out.println(dfsAlgos.topSort(graph));

    }
}
