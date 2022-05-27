package julian;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PriorityQueueMinHeap<K extends Comparable<K>> implements PriorityQueue<K>{

    private final ArrayList<K> a;

    public PriorityQueueMinHeap() {
        this.a = new ArrayList<>();
    }

    @Override
    public void addElement(K elem) {

        a.add(elem);

        int i = a.size() - 1;

        while(i > 0 && a.get(getFatherIndex(i)).compareTo(a.get(i)) > 0) { //
            swap(i, getFatherIndex(i));
            i = getFatherIndex(i);
        }
    }

    @Override
    public K getFirst() throws NoSuchElementException{ //liefert kleinstes Element
        if(a.size() != 0) {
            return a.get(0);
        } else {
            throw new NoSuchElementException("Heap is empty!");
        }
    }

    @Override
    public void deleteFirst() throws NoSuchElementException{
        if(a.size() != 0) {
            a.set(0, a.get(a.size() - 1));
            a.remove(a.size() - 1);
            minHeapify(0);
        } else {
            throw new NoSuchElementException("Heap is empty!");
        }
    }

    private void minHeapify(int i) {
        int l = getLeftIndex(i);
        int r = getRightIndex(i);
        int min;

        if(l < a.size() - 1 && a.get(l).compareTo(a.get(r)) < 0) {
            min = l;
        } else {
            min = i;
        }
        if(r < a.size() - 1 && a.get(r).compareTo(a.get(min)) < 0) {
            min = r;
        }
        if(min != i) {
            swap(i, min);
            minHeapify(min);
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
        PriorityQueueMinHeap<Integer> heap = new PriorityQueueMinHeap<>();

        heap.addElement(1);
        heap.addElement(2);
        heap.addElement(3);
        heap.addElement(4);
        heap.addElement(7);
        heap.addElement(5);
        System.out.println(heap);
        heap.addElement(0);
        System.out.println(heap);
        heap.deleteFirst();
        System.out.println(heap);
        //System.out.println(heap.getFirst());

    }
}
