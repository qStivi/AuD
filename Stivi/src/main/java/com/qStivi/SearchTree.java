package com.qStivi;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SearchTree<K extends Comparable<K>> {

    private Node<K> root;

    public Node<K> getRoot() {
        return root;
    }

    public void insert(K key) {
        var z = new Node<>(key);
        var y = root;
        while (y != null) {
            z.parent = y;
            if (z.key.compareTo(y.key) < 0) {
                y = y.left;
            } else {
                y = y.right;
            }
        }
        if (z.parent == null) {
            this.root = z;
        } else {
            if (z.key.compareTo(z.parent.key) < 0) {
                z.parent.left = z;
            } else {
                z.parent.right = z;
            }
        }
    }

    public ArrayList<K> toSortedArrayList(Node<K> root) {
        return toSortedArrayListHelper(root, null);
    }

    private ArrayList<K> toSortedArrayListHelper(Node<K> root, ArrayList<K> list) {
        if (list == null) list = new ArrayList<K>();
        if (root != null) {
            toSortedArrayListHelper(root.left, list);
            list.add(root.key);
            toSortedArrayListHelper(root.right, list);
        }
        return list;
    }

    public void delete(K key) {
        var z = search(this.getRoot(), key);
        if (z == null) throw new NoSuchElementException();
        var y = z;
        if (z.left != null && z.right != null) y = successor(z);
        var x = y.left != null ? y.left : y.right;
        if (x != null) {
            x.parent = y.parent;
        }
        if (y.parent == null) {
            this.root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else y.parent.right = x;
        if (y != z) {
            z.key = y.key;
        }
    }

    public Node<K> successor(Node<K> x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        var y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    public Node<K> minimum(Node<K> x) {
        if (x == null) throw new NoSuchElementException("Tree is empty!");
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Node<K> maximum(Node<K> x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Node<K> search(Node<K> root, K key) {
        if (root == null || root.key.equals(key)) return root;
        if (key.compareTo(root.key) < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }
}
