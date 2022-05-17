package com.qStivi;

public interface PriorityQueue<K extends Comparable<K>> {

    void addElement(K elem);

    K getFirst();

    void deleteFirst();

}
