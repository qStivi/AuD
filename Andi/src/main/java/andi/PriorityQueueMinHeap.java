package andi;

import java.util.ArrayList;

public class PriorityQueueMinHeap<K extends Comparable<K>> implements PriorityQueue<K> {

    private ArrayList<K> a;

    @Override
    public void addElement(K elem) {
        K temp = a.get(0);
        a.add(0, elem);
        a.add(a.size(), temp);
        MinHeapify(a, 0);
    }

    public K getFirst() {
        return a.get(0);
    }

    public void deleteFirst() {
        a.add(0, a.get(a.size() - 1));
        a.remove(a.size() - 1);
        MinHeapify(a, 0);
    }

    public void MinHeapify(ArrayList<K> a, int i) {
        int min = -1;
        int l = 2 * i;
        int r = 2 * i + 1;
        if (l < a.size() && a.get(l).compareTo(a.get(i)) < 0) {
            min = l;
        } else {
            min = i;
        }
        if (r < a.size() && a.get(r).compareTo(a.get(i)) < 0) {
            min = r;
        }
        if (min != i) {
            swap(i, min);
            MinHeapify(a, min);
        }
    }

    private void swap(int x, int y) {
        K temp = a.get(x);
        a.add(x, a.get(y));
        a.add(y, temp);
    }
}
