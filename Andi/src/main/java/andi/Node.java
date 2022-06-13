package andi;

public class Node<T extends Comparable<T>> {

    public T key;
    public Node<T> p;
    public Node<T> left;
    public Node<T> right;

    public Node(T key) {
        this.key = key;
        this.p = null;
        this.left = null;
        this.right = null;
    }

    public Node(T key, Node<T> p, Node<T> left, Node<T> right) {
        this.key = key;
        this.p = p;
        this.left = left;
        this.right = right;
    }
}
