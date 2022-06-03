package com.qStivi;

public class Node<K extends Comparable<K>> {

    public K key;
    public Node<K> left;
    public Node<K> right;
    public Node<K> parent;

    public Node(K key) {
        this.key = key;
    }
}
