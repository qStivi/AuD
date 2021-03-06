package julian;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PriorityQueueMaxHeap<K extends Comparable<K>> implements PriorityQueue<K> {

    private final ArrayList<K> a;

    public PriorityQueueMaxHeap() {
        this.a = new ArrayList<>();
    }

    @Override
    public void addElement(K elem) {

        a.add(elem);

        int i = a.size() - 1;

        while(i > 0 && a.get(getFatherIndex(i)).compareTo(a.get(i)) < 0) {
            swap(i, getFatherIndex(i));
            i = getFatherIndex(i);
        }
    }

    @Override
    public K getFirst() {
        if(a.size() != 0) {
            return a.get(0);
        } else {
            throw new NoSuchElementException("Heap is empty!");
        }
    }

    @Override
    public void deleteFirst() {
        if(a.size() != 0) {
            a.set(0, a.get(a.size() - 1));
            a.remove(a.size() - 1);
            maxHeapify(0);
        } else {
            throw new NoSuchElementException("Heap is empty!");
        }
    }

    private void maxHeapify(int i) {
        int l = getLeftIndex(i);
        int r = getRightIndex(i);
        int max;

        if(l < a.size() - 1 && a.get(l).compareTo(a.get(r)) > 0) {
            max = l;
        } else {
            max = i;
        }
        if(r < a.size() - 1 && a.get(r).compareTo(a.get(max)) > 0) {
            max = r;
        }
        if(max != i) {
            swap(i, max);
            maxHeapify(max);
        }
    }

    public boolean find(int i, K x) {

        if(a.get(i).equals(x)) { //Passt
            return true;
        }

        if(getRightIndex(i) >= a.size() && getLeftIndex(i) >= a.size()) {
            return false;
        }

        if(a.get(getLeftIndex(i)).compareTo(x) >= 0 && a.get(getRightIndex(i)).compareTo(x) >= 0) {
            return find(getLeftIndex(i), x) || find(getRightIndex(i), x); //rechts und links
        } else if(a.get(getRightIndex(i)).compareTo(x) < 0 && a.get(getLeftIndex(i)).compareTo(x) >= 0) {
            return find(getLeftIndex(i), x); //links
        } else if(a.get(getLeftIndex(i)).compareTo(x) < 0 && a.get(getRightIndex(i)).compareTo(x) >= 0) {
            return find(getRightIndex(i), x); //rechts
        } else {
            return false;
        }

    }

    public void HeapDelete(int i) {

        swap(i, a.size() - 1);
        a.remove(a.size() - 1);

        if(i == a.size()) {
            return;
        }

        while(heapCondition(i)) {

            if(getFatherIndex(i) >= 0 && a.get(getFatherIndex(i)).compareTo(a.get(i)) < 0) {
                swap(i, getFatherIndex(i));
                i = getFatherIndex(i);
            } else {

                if(getLeftIndex(i) < a.size() && getRightIndex(i) < a.size()) {
                    K max = max(a.get(getLeftIndex(i)), a.get(getRightIndex(i)));

                    if(max.equals(a.get(getLeftIndex(i)))) {
                        swap(i, getLeftIndex(i));
                        i = getLeftIndex(i);
                    } else {
                        swap(i, getRightIndex(i));
                        i = getRightIndex(i);
                    }
                } else if(getLeftIndex(i) < a.size()) {
                    swap(i, getLeftIndex(i));
                    i = getLeftIndex(i);
                } else if(getRightIndex(i) < a.size()){
                    swap(i, getRightIndex(i));
                    i = getRightIndex(i);
                }
            }
        }
    }

    private boolean heapCondition(int i) {

        return (0 <= getFatherIndex(i) && a.get(getFatherIndex(i)).compareTo(a.get(i)) < 0) ||
                getLeftIndex(i) < a.size() && a.get(getLeftIndex(i)).compareTo(a.get(i)) > 0 ||
                getRightIndex(i) < a.size() && a.get(getRightIndex(i)).compareTo(a.get(i)) > 0;
    }

    private K max(K x, K y) {
        if(x.compareTo(y) < 0) {
            return y;
        } else {
            return x;
        }
    }

    private int getLeftIndex(int i) {
        return 2*i + 1;
    }

    private int getRightIndex(int i) {
        return 2*i + 2;
    }

    private int getFatherIndex(int i) {
        return (int) Math.round(i / 2.0) - 1;
    }

    public void swap(int i, int j) {
        K temp = a.get(j);
        a.set(j, a.get(i));
        a.set(i, temp);
    }

    @Override
    public String toString() {
        return a.toString();
    }

    public static void main(String[] args) {

        PriorityQueueMaxHeap<Integer> heap = new PriorityQueueMaxHeap<>();

        heap.addElement(5);
        heap.addElement(2);
        heap.addElement(5);
        heap.addElement(1);
        heap.addElement(1);
        heap.addElement(3);
        heap.addElement(4);

        heap.HeapDelete(6);

        System.out.println(heap);


        //System.out.println(heap);

    }
}
