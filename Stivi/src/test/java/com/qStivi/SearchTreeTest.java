package com.qStivi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SearchTreeTest {

    @Test
    void insert() {
        var T = new SearchTree<Integer>();
        T.insert(5);
        assertEquals(new Node<>(5).key, T.getRoot().key);
        assertNull(T.getRoot().left);
        assertNull(T.getRoot().right);
        assertNull(T.getRoot().parent);


        T.insert(3);
        assertEquals(new Node<>(5).key, T.getRoot().key);
        assertNull(T.getRoot().right);
        assertNull(T.getRoot().parent);
        assertNotNull(T.getRoot().left);

        var x = new Node<>(3);
        x.parent = new Node<>(5);
        assertEquals(x.key, T.getRoot().left.key);
        assertEquals(x.left, T.getRoot().left.left);
        assertEquals(x.right, T.getRoot().left.right);
        assertEquals(x.parent.key, T.getRoot().left.parent.key);

        T.insert(8);
        x = new Node<>(8);
        x.parent = new Node<>(5);
        assertEquals(x.key, T.getRoot().right.key);
        assertEquals(x.left, T.getRoot().right.left);
        assertEquals(x.right, T.getRoot().right.right);
        assertEquals(x.parent.key, T.getRoot().right.parent.key);

        T.insert(10);
        x = new Node<>(10);
        x.parent = new Node<>(8);
        assertEquals(x.key, T.getRoot().right.right.key);
        assertEquals(x.left, T.getRoot().right.right.left);
        assertEquals(x.right, T.getRoot().right.right.right);
        assertEquals(x.parent.key, T.getRoot().right.right.parent.key);

        T.insert(6);
        x = new Node<>(6);
        x.parent = new Node<>(8);
        assertEquals(x.key, T.getRoot().right.left.key);
        assertEquals(x.left, T.getRoot().right.left.left);
        assertEquals(x.right, T.getRoot().right.left.right);
        assertEquals(x.parent.key, T.getRoot().right.left.parent.key);
    }

    @Test
    void delete() {
        var T = new SearchTree<Integer>();

        assertThrows(NoSuchElementException.class, () -> T.delete(0));
        assertThrows(NoSuchElementException.class, () -> T.delete(null));

        T.insert(17);
        T.delete(17);
        assertNull(T.getRoot());

        T.insert(5);
        T.insert(3);
        T.insert(8);
        T.insert(10);
        T.insert(6);
        T.insert(11);

        T.delete(3);
        assertEquals(5, T.getRoot().key);
        assertEquals(8, T.getRoot().right.key);
        assertNull(T.getRoot().left);

        T.delete(8);
        assertEquals(5, T.getRoot().key);
        assertNull(T.getRoot().left);
        assertEquals(10, T.getRoot().right.key);
        assertEquals(6, T.getRoot().right.left.key);
        assertEquals(11, T.getRoot().right.right.key);

        T.delete(11);
        assertEquals(5, T.getRoot().key);
        assertNull(T.getRoot().left);
        assertEquals(10, T.getRoot().right.key);
        assertEquals(6, T.getRoot().right.left.key);
        assertNull(T.getRoot().right.right);

        T.delete(10);
        assertEquals(5, T.getRoot().key);
        assertNull(T.getRoot().left);
        assertEquals(6, T.getRoot().right.key);
        assertNull(T.getRoot().right.left);

        T.delete(5);
        assertEquals(6, T.getRoot().key);
        assertNull(T.getRoot().left);
        assertNull(T.getRoot().right);
    }

    @Test
    void search() {
        var T = new SearchTree<Integer>();

        assertNull(T.search(T.getRoot(), 5));

        T.insert(5);
        T.insert(3);
        T.insert(8);
        T.insert(10);
        T.insert(6);

        assertEquals(5, T.search(T.getRoot(), 5).key);
        assertEquals(3, T.search(T.getRoot(), 5).left.key);
        assertEquals(8, T.search(T.getRoot(), 5).right.key);
        assertNull(T.search(T.getRoot(), 5).parent);

        assertEquals(3, T.search(T.getRoot(), 3).key);
        assertNull(T.search(T.getRoot(), 3).left);
        assertNull(T.search(T.getRoot(), 3).right);
        assertEquals(5, T.search(T.getRoot(), 3).parent.key);

        assertEquals(8, T.search(T.getRoot(), 8).key);
        assertEquals(6, T.search(T.getRoot(), 8).left.key);
        assertEquals(10, T.search(T.getRoot(), 8).right.key);
        assertEquals(5, T.search(T.getRoot(), 8).parent.key);

        assertEquals(10, T.search(T.getRoot(), 10).key);
        assertNull(T.search(T.getRoot(), 10).left);
        assertNull(T.search(T.getRoot(), 10).right);
        assertEquals(8, T.search(T.getRoot(), 10).parent.key);

        assertEquals(6, T.search(T.getRoot(), 6).key);
        assertNull(T.search(T.getRoot(), 6).left);
        assertNull(T.search(T.getRoot(), 6).right);
        assertEquals(8, T.search(T.getRoot(), 6).parent.key);
    }

    @Test
    void minimum() {
        var T = new SearchTree<Integer>();

        assertThrows(NoSuchElementException.class, () -> T.minimum(T.getRoot()));

        T.insert(5);
        T.insert(3);
        T.insert(8);
        T.insert(10);
        T.insert(6);

        assertEquals(3, T.minimum(T.getRoot()).key);
        assertEquals(5, T.minimum(T.getRoot()).parent.key);

        assertEquals(3, T.minimum(T.getRoot().left).key);
        assertEquals(5, T.minimum(T.getRoot().left).parent.key);

        assertEquals(6, T.minimum(T.getRoot().right).key);
        assertEquals(8, T.minimum(T.getRoot().right).parent.key);

        assertEquals(10, T.minimum(T.getRoot().right.right).key);
        assertEquals(8, T.minimum(T.getRoot().right.right).parent.key);

        assertEquals(6, T.minimum(T.getRoot().right.left).key);
        assertEquals(8, T.minimum(T.getRoot().right.left).parent.key);
    }

    @Test
    void maximum() {
        var T = new SearchTree<Integer>();

        assertThrows(NoSuchElementException.class, () -> T.minimum(T.getRoot()));

        T.insert(5);
        T.insert(3);
        T.insert(8);
        T.insert(10);
        T.insert(6);

        assertEquals(10, T.maximum(T.getRoot()).key);
        assertEquals(8, T.maximum(T.getRoot()).parent.key);

        assertEquals(3, T.maximum(T.getRoot().left).key);
        assertEquals(5, T.maximum(T.getRoot().left).parent.key);

        assertEquals(10, T.maximum(T.getRoot().right).key);
        assertEquals(8, T.maximum(T.getRoot().right).parent.key);

        assertEquals(10, T.maximum(T.getRoot().right.right).key);
        assertEquals(8, T.maximum(T.getRoot().right.right).parent.key);

        assertEquals(6, T.maximum(T.getRoot().right.left).key);
        assertEquals(8, T.maximum(T.getRoot().right.left).parent.key);
    }

    @Test
    void toSortedArrayList() {
        var T = new SearchTree<Integer>();

        assertEquals(0, T.toSortedArrayList(T.getRoot()).size());

        T.insert(5);
        T.insert(3);
        T.insert(8);
        T.insert(10);
        T.insert(6);

        var a = T.toSortedArrayList(T.getRoot());
        var b = new ArrayList<>();
        b.add(3);
        b.add(5);
        b.add(6);
        b.add(8);
        b.add(10);

        assertEquals(b.size(), a.size());

        for (int i = 0; i < a.size(); i++) {
            assertEquals(b.get(i), a.get(i));
        }
    }
}
