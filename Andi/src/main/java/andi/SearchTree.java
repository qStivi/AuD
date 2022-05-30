package andi;

import java.util.ArrayList;

public class SearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public SearchTree(Node<T> root) {
        this.root = root;
    }

    public SearchTree() {
        this.root = null;
    }

    public Node<T> search(T k) {
        return searchHelper(this.root, k);
    }

    private Node<T> searchHelper(Node<T> x, T k) {
        if (x == null || x.key == k) {
            return x;
        }
        if (k.compareTo(x.key) < 0) {
            return searchHelper(x.left, k);
        } else {
            return searchHelper(x.right, k);
        }
    }

    public ArrayList<T> toSortedArrayList() {
        return toSortedArrayListHelper(this.root, new ArrayList<T>());
    }

    private ArrayList<T> toSortedArrayListHelper(Node<T> x, ArrayList<T> arr) {
        if (x != null) {
            toSortedArrayListHelper(x.left, arr);
            arr.add(x.key);
            toSortedArrayListHelper(x.right, arr);
        }
        return arr;
    }


    public void delete(T k) {
        deleteHelper(search(k));
    }

    public void deleteHelper(Node<T> z) {
        Node<T> y;
        Node<T> x;
        if (z.left == null || z.right == null) {
            y = z;
        } else {
            y = treeSucessor(z);
        }
        if (y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }
        if (x != null) {
            x.p = y.p;
        }
        if (y.p == null) {
            this.root = x;
        } else if (y == y.p.left) {
            y.p.left = x;
        } else {
            y.p.right = x;
        }
        if (y != z) {
            z.key = y.key;
        }
    }

    public void insert(T k) {
        insertHelper(new Node<T>(k));
    }

    private void insertHelper(Node<T> z) {
        Node<T> y = this.root;
        while (y != null) {
            z.p = y;
            if (z.key.compareTo(y.key) < 0) {
                y = y.left;
            } else {
                y = y.right;
            }
        }
        if (z.p == null) {
            this.root = z;
        } else {
            if (z.key.compareTo(z.p.key) < 0) {
                z.p.left = z;
            } else {
                z.p.right = z;
            }
        }
    }

    public Node<T> treeSucessor(Node<T> x) {
        if (x.right != null) {
            return minimumHelper(x.right);
        }
        Node<T> y = x.p;
        while (y != null && x == y.right) {
            x = y;
            y = x.p;
        }
        return y;
    }

    public Node<T> minimum() {
        return minimumHelper(root);
    }

    private Node<T> minimumHelper(Node<T> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Node<T> maximum() {
        return maximumumHelper(root);
    }

    private Node<T> maximumumHelper(Node<T> x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public void inorder() {
        inorderHelper(this.root);
    }

    private void inorderHelper(Node<T> x) {
        if (x != null) {
            inorderHelper(x.left);
            System.out.println(x.key);
            inorderHelper(x.right);
        }
    }


}
