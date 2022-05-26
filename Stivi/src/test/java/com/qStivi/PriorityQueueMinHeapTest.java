package com.qStivi;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class PriorityQueueMinHeapTest {

    @Test
    void addElement() {
        var queue = new PriorityQueueMinHeap<Integer>();
        queue.addElement(6); // 6
        assertEquals(6, queue.getA().get(0));
        queue.addElement(8); // 6, 8
        assertEquals(8, queue.getA().get(1));
        queue.addElement(8); // 6, 8
        assertEquals(8, queue.getA().get(2));
        queue.addElement(4); // 4, 6, 8
        assertEquals(4, queue.getA().get(0));
        queue.addElement(Integer.MAX_VALUE); // 4, 6, 8, max
        assertEquals(Integer.MAX_VALUE, queue.getA().get(3));
        queue.addElement(Integer.MIN_VALUE);// min, 4, 6, 8, max
        assertEquals(Integer.MIN_VALUE, queue.getA().get(0));
        queue.addElement(17); // min, 4, 6, 8, 17, max
        assertEquals(17, queue.getA().get(4));
        queue.addElement(8); // 6, 8
        assertEquals(8, queue.getA().get(4));
    }

    @Test
    void getFirst() {
        var queue = new PriorityQueueMinHeap<Integer>();
        assertThrowsExactly(NoSuchElementException.class, queue::getFirst);
        queue.addElement(6); // 6
        assertEquals(6, queue.getFirst());
        queue.addElement(8); // 6, 8
        assertEquals(6, queue.getFirst());
        queue.addElement(4); // 4, 6, 8
        assertEquals(4, queue.getFirst());
        queue.addElement(Integer.MAX_VALUE); // 4, 6, 8, max
        assertEquals(4, queue.getFirst());
        queue.addElement(Integer.MIN_VALUE);// min, 4, 6, 8, max
        assertEquals(Integer.MIN_VALUE, queue.getFirst());
        queue.addElement(17); // min, 4, 6, 8, 17, max
        assertEquals(Integer.MIN_VALUE, queue.getFirst());
    }

    @Test
    void deleteFirst() {
        var queue = new PriorityQueueMinHeap<Integer>();
        queue.addElement(6); // 6
        queue.addElement(8); // 6, 8
        queue.addElement(4); // 4, 6, 8
        queue.addElement(Integer.MAX_VALUE); // 4, 6, 8, max
        queue.addElement(Integer.MIN_VALUE);// min, 4, 6, 8, max
        queue.addElement(17); // min, 4, 6, 8, 17, max

        queue.deleteFirst();
        assertEquals(4, queue.getFirst());
        queue.deleteFirst();
        assertEquals(6, queue.getFirst());
        queue.deleteFirst();
        assertEquals(8, queue.getFirst());
        queue.deleteFirst();
        assertEquals(17, queue.getFirst());
        queue.deleteFirst();
        assertEquals(Integer.MAX_VALUE, queue.getFirst());
        queue.deleteFirst();
        assertThrowsExactly(NoSuchElementException.class, queue::deleteFirst);
    }
}
