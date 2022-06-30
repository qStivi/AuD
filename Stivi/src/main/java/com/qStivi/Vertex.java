package com.qStivi;

import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {

    public Integer value;

    public ArrayList<Vertex> adjacencyList;

    public Vertex(Integer value) {
        this.value = value;
        this.adjacencyList = new ArrayList<>();
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(this.value, o.value);
    }
}
