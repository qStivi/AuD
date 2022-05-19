package com.qStivi;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static java.util.Collections.swap;

public class PriorityQueueMinHeap<K extends Comparable<K>> implements PriorityQueue<K> {

    private final ArrayList<K> a = new ArrayList<>();

    // For testing purposes
    public ArrayList<K> getA() {
        return a;
    }

    @Override
    public void addElement(K elem) {
        if (a.size() == 0) {
            a.add(0, elem);
        } else {
            a.add(0, elem);
            minHeapify(a, 0);
        }
    }

    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    private void minHeapify(ArrayList<K> a, int i) {
        var l = 2 * i + 1;
        var r = 2 * i + 2;
        int min;
        if (l < a.size() && a.get(l).compareTo(a.get(i)) < 0) {
            min = l;
        } else {
            min = i;
        }
        if (r < a.size() && a.get(r).compareTo(a.get(min)) < 0) {
            min = r;
        }
        if (min != i) {
            swap(a, i, min);
            minHeapify(a, min);
        }
    }

    @Override
    public K getFirst() {
        if (a.size() < 1) {
            throw new NoSuchElementException();
        }
        return a.get(0);
    }

    @Override
    public void deleteFirst() {
        if (a.size() < 1) {
            throw new NoSuchElementException();
        }
        a.remove(0);
        minHeapify(a, 0);
    }

}
