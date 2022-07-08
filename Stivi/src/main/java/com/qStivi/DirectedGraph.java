package com.qStivi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class DirectedGraph {

    ArrayList<Vertex> vertices;

    public DirectedGraph() {
        vertices = new ArrayList<>();
    }

    public DirectedGraph(Integer n) {
        vertices = new ArrayList<>();
        for (var i = 0; i <= n; i++) {
            vertices.add(new Vertex(null));
        }
    }


    /**
     * Top bnet time: 48459
     * 2
     * 10
     * 4
     * 1
     * 9
     * 5
     * 3
     * 6
     * 7
     * 8
     * bnet Circle Time: 13583
     * No bnet circle!
     * ___________________
     * ___________________
     * Top maayan time: 403166
     * maayan Circle Time: 150750
     * 476
     * 473
     * ___________________
     * ___________________
     * Top morenoDesne time: 184083
     * 12
     * 26
     * 38
     * 23
     * 39
     * 2
     * 54
     * 31
     * 40
     * 18
     * 49
     * 3
     * 44
     * 55
     * 8
     * 50
     * 41
     * 24
     * 27
     * 43
     * 30
     * 9
     * 20
     * 19
     * 16
     * 10
     * 17
     * 32
     * 46
     * 28
     * 52
     * 13
     * 29
     * 15
     * 25
     * 42
     * 14
     * 51
     * 34
     * 35
     * 45
     * 53
     * 33
     * 36
     * 37
     * 4
     * 6
     * 7
     * 11
     * 5
     * 47
     * 22
     * 48
     * 21
     * 1
     * morenoDense Circle Time: 261500
     * No morenoDense circle!
     * ___________________
     * ___________________
     * Top morenoTaro time: 85167
     * morenoTaro Circle Time: 98959
     * 1
     * 2
     * ___________________
     * ___________________
     * Top simple time: 178292
     * 111
     * 106
     * 100
     * 96
     * 93
     * 62
     * 90
     * 71
     * 43
     * 31
     * 107
     * 85
     * 101
     * 48
     * 108
     * 102
     * 58
     * 16
     * 50
     * 110
     * 78
     * 55
     * 53
     * 46
     * 84
     * 104
     * 51
     * 64
     * 38
     * 112
     * 29
     * 65
     * 39
     * 79
     * 23
     * 33
     * 17
     * 37
     * 56
     * 41
     * 49
     * 63
     * 86
     * 9
     * 24
     * 69
     * 74
     * 70
     * 3
     * 34
     * 32
     * 89
     * 7
     * 14
     * 25
     * 52
     * 22
     * 75
     * 68
     * 99
     * 36
     * 81
     * 5
     * 15
     * 54
     * 73
     * 26
     * 57
     * 77
     * 60
     * 2
     * 1
     * 44
     * 40
     * 11
     * 10
     * 27
     * 61
     * 28
     * 82
     * 83
     * 20
     * 103
     * 13
     * 109
     * 98
     * 76
     * 6
     * 94
     * 105
     * 18
     * 67
     * 12
     * 80
     * 21
     * 4
     * 92
     * 42
     * 8
     * 45
     * 72
     * 59
     * 35
     * 88
     * 87
     * 97
     * 66
     * 91
     * 95
     * 19
     * 30
     * 47
     * simple Circle Time: 246875
     * No simple circle!
     * ___________________
     * ___________________
     */
    public static void main(String[] args) throws Exception {
        var bnet = getList("out.bnet_bnet.bnet");
        var maayan = getList("out.maayan-figeys");
        var moreno_dense = getList("out.moreno_dense_comm");
        var moreno_taro = getList("out.moreno_taro_taro");
        var simple = getList("out.simple_simple.simple");

        var dfs = new DfsAlgos();


        if (bnet == null || maayan == null || moreno_dense == null || moreno_taro == null || simple == null) throw new Exception("ERROR!");

        var maxBnet = 0;
        for (int[] edges : bnet) {
            if (edges[0] > maxBnet) maxBnet = edges[0];
            if (edges[1] > maxBnet) maxBnet = edges[1];
        }
        var maxMaayan = 0;
        for (int[] edges : maayan) {
            if (edges[0] > maxMaayan) maxMaayan = edges[0];
            if (edges[1] > maxMaayan) maxMaayan = edges[1];
        }
        var maxMorenoDense = 0;
        for (int[] edges : maayan) {
            if (edges[0] > maxMorenoDense) maxMorenoDense = edges[0];
            if (edges[1] > maxMorenoDense) maxMorenoDense = edges[1];
        }
        var maxMorenoTaro = 0;
        for (int[] edges : maayan) {
            if (edges[0] > maxMorenoTaro) maxMorenoTaro = edges[0];
            if (edges[1] > maxMorenoTaro) maxMorenoTaro = edges[1];
        }
        var maxSimple = 0;
        for (int[] edges : maayan) {
            if (edges[0] > maxSimple) maxSimple = edges[0];
            if (edges[1] > maxSimple) maxSimple = edges[1];
        }


        var bnetGraph = new DirectedGraph(maxBnet);
        for (int[] edges : bnet) {
            bnetGraph.addVertex(edges[0]);
            bnetGraph.addVertex(edges[1]);
            bnetGraph.addEdge(edges[0], edges[1]);
        }
        var stopWatch = new StopWatch();
        var top = dfs.topSort(bnetGraph);
        System.out.println("Top bnet time: " + stopWatch.getTime());
        while (top != null && top.peek() != null) {
            System.out.println(top.poll());
        }
//        System.out.println("___________________");

        stopWatch = new StopWatch();
        var circle = dfs.detectCycle(bnetGraph);
        System.out.println("bnet Circle Time: " + stopWatch.getTime());
        if (circle != null) {
            circle.forEach(System.out::println);
        } else System.out.println("No bnet circle!");
        System.out.println("___________________");
        System.out.println("___________________");


        var mayaanGraph = new DirectedGraph(maxMaayan);
        for (int[] edges : maayan) {
            mayaanGraph.addVertex(edges[0]);
            mayaanGraph.addVertex(edges[1]);
            mayaanGraph.addEdge(edges[0], edges[1]);
        }
        stopWatch = new StopWatch();
        top = dfs.topSort(mayaanGraph);
        System.out.println("Top maayan time: " + stopWatch.getTime());
        while (top != null && top.peek() != null) {
            System.out.println(top.poll());
        }
//        System.out.println("___________________");

        stopWatch = new StopWatch();
        circle = dfs.detectCycle(mayaanGraph);
        System.out.println("maayan Circle Time: " + stopWatch.getTime());
        if (circle != null) {
            circle.forEach(System.out::println);
        } else System.out.println("No maayan circle!");
        System.out.println("___________________");
        System.out.println("___________________");


        var morenoDenseGraph = new DirectedGraph(maxMorenoDense);
        for (int[] edges : moreno_dense) {
            morenoDenseGraph.addVertex(edges[0]);
            morenoDenseGraph.addVertex(edges[1]);
            morenoDenseGraph.addEdge(edges[0], edges[1]);
        }
        stopWatch = new StopWatch();
        top = dfs.topSort(morenoDenseGraph);
        System.out.println("Top morenoDesne time: " + stopWatch.getTime());
        while (top != null && top.peek() != null) {
            System.out.println(top.poll());
        }
//        System.out.println("___________________");

        stopWatch = new StopWatch();
        circle = dfs.detectCycle(morenoDenseGraph);
        System.out.println("morenoDense Circle Time: " + stopWatch.getTime());
        if (circle != null) {
            circle.forEach(System.out::println);
        } else System.out.println("No morenoDense circle!");
        System.out.println("___________________");
        System.out.println("___________________");


        var morenoTaroGraph = new DirectedGraph(maxMorenoTaro);
        for (int[] edges : moreno_taro) {
            morenoTaroGraph.addVertex(edges[0]);
            morenoTaroGraph.addVertex(edges[1]);
            morenoTaroGraph.addEdge(edges[0], edges[1]);
        }
        stopWatch = new StopWatch();
        top = dfs.topSort(morenoTaroGraph);
        System.out.println("Top morenoTaro time: " + stopWatch.getTime());
        while (top != null && top.peek() != null) {
            System.out.println(top.poll());
        }
//        System.out.println("___________________");

        stopWatch = new StopWatch();
        circle = dfs.detectCycle(morenoTaroGraph);
        System.out.println("morenoTaro Circle Time: " + stopWatch.getTime());
        if (circle != null) {
            circle.forEach(System.out::println);
        } else System.out.println("No morenoTaro circle!");
        System.out.println("___________________");
        System.out.println("___________________");


        var simpleGraph = new DirectedGraph(maxSimple);
        for (int[] edges : simple) {
            simpleGraph.addVertex(edges[0]);
            simpleGraph.addVertex(edges[1]);
            simpleGraph.addEdge(edges[0], edges[1]);
        }
        stopWatch = new StopWatch();
        top = dfs.topSort(simpleGraph);
        System.out.println("Top simple time: " + stopWatch.getTime());
        while (top != null && top.peek() != null) {
            System.out.println(top.poll());
        }
//        System.out.println("___________________");

        stopWatch = new StopWatch();
        circle = dfs.detectCycle(simpleGraph);
        System.out.println("simple Circle Time: " + stopWatch.getTime());
        if (circle != null) {
            circle.forEach(System.out::println);
        } else System.out.println("No simple circle!");
        System.out.println("___________________");
        System.out.println("___________________");
    }

    private static ArrayList<int[]> getList(String fileName) {
        var list = new ArrayList<int[]>();

        var classloader = Thread.currentThread().getContextClassLoader();
        var url = classloader.getResource(fileName);
        if (url == null) return null;
        try {
            var file = new File(url.toURI());
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                for (String line; (line = br.readLine()) != null; ) {
                    var a = line.split(" ")[0];
                    var b = line.split(" ")[1];
                    list.add(new int[]{Integer.parseInt(a), Integer.parseInt(b)});
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void addVertex(Integer i) {
        vertices.get(i).value = i;
    }

    public void addEdge(Integer i, Integer j) {
        vertices.get(i).adjacencyList.add(vertices.get(j));
    }

    public void deleteEdge(Integer i, Integer j) {
        vertices.get(i).adjacencyList.remove(vertices.get(j));
    }
}
