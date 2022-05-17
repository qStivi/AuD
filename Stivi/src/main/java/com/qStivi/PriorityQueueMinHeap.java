package com.qStivi;

import java.util.ArrayList;

import static java.util.Collections.swap;

public class PriorityQueueMinHeap<K extends Comparable<K>> implements PriorityQueue<K> {

    private ArrayList<K> a;

    @Override
    public void addElement(K elem) {

    }

    @Override
    public K getFirst() {
        return null;
    }

    @Override
    public void deleteFirst() {

    }

    private void increaseKey(ArrayList<K> A, int i, K key) throws Exception {
        if (key.compareTo(A.get(i)) < 0) {
            throw new Exception("ERROR");
        }
        A.add(i, key);
        var vater = i / 2;
        while (i > 0 && A.get(vater).compareTo(A.get(i)) < 0) {
            swap(A, i, vater);
            i = vater;
        }
    }
}
