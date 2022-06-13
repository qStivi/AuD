package julian;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class SearchTree<K extends Comparable<K>>{

    K key;
    SearchTree<K> left;
    SearchTree<K> right;
    SearchTree<K> parent;
    SearchTree<K> root;

    public SearchTree(K key, SearchTree<K> left, SearchTree<K> right, SearchTree<K> parent) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public SearchTree() {
        this.key = null;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.root = new SearchTree<>(null, null, null, null);
    }

    public void insert(K key) {

        SearchTree<K> insertElement = new SearchTree<>(key, null, null, null);
        SearchTree<K> current = this.root;

        while(current != null && current.key != null) {
            insertElement.parent = current;
            if(insertElement.key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        } if(insertElement.parent == null) {
            this.root = insertElement;
        } else {
            if(insertElement.key.compareTo(insertElement.parent.key) < 0) {
                insertElement.parent.left = insertElement;
            } else {
                insertElement.parent.right = insertElement;
            }
        }
    }

    public void delete(K key) {

        SearchTree<K> z = findNode(key);
        SearchTree<K> y;
        SearchTree<K> x;

        if(z.left == null || z.right == null) {
            y = z;
        } else {
            y = findSuccessor(z);
        }

        if(y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }

        if(x != null) {
            x.parent = y.parent;
        }

        if(y.parent == null) {
            this.root = x;
        } else if(y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        if(y != z) {
            z.key = y.key;
        }
    }

    public K search(K key) {
        return findNode(key).key;
    }

    public K min() {
        return findMinimum(this.root).key;
    }

    public K max() {
        return findMaximum(this.root).key;
    }

    public ArrayList<K> toSortedArrayList() {
        return toSortedArrayListHelper(this.root, new ArrayList<>());
    }

    private SearchTree<K> findNode(K key) throws NoSuchElementException{
        SearchTree<K> current = this.root;

        while(current != null && current.key != key) {

            if(key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if(current == null) {
            throw new NoSuchElementException();
        }

        System.out.println();
        System.out.println(current.key);
        return current;
    }

    private SearchTree<K> findSuccessor(SearchTree<K> x) {
        if(x.right != null) {
            return findMinimum(x.right);
        }
        SearchTree<K> y = x.parent;
        while(y != null && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    private SearchTree<K> findPredecessor(SearchTree<K> x) {
        if(x.left != null) {
            return findMaximum(x.left);
        }
        SearchTree<K> y = x.parent;
        while(y != null && x == y.left) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    private SearchTree<K> findMinimum(SearchTree<K> x) {
        SearchTree<K> current = x;

        while(current.left != null) {
            current = current.left;
        }

        return current;
    }

    private SearchTree<K> findMaximum(SearchTree<K> x) {
        SearchTree<K> current = x;

        while(current.right != null) {
            current = current.right;
        }

        return current;
    }

    static int counter = 0;

    private ArrayList<K> toSortedArrayListHelper(SearchTree<K> x, ArrayList<K> list) {

        if(x != null && x.key != null) {
            toSortedArrayListHelper(x.left, list);
            list.add(x.key);
            //System.out.print(x.key + " ");
            toSortedArrayListHelper(x.right, list);
        }
        return list;
    }

    public ArrayList<SearchTree<K>> getAllLeaves() {
        return getAllLeavesHelper(this.root, new ArrayList<>());
    }

    private ArrayList<SearchTree<K>> getAllLeavesHelper(SearchTree<K> x, ArrayList<SearchTree<K>> leaves) {

        if(x.right == null && x.left == null) {
            leaves.add(x);
        } else {
            getAllLeavesHelper(x.left, leaves);
            getAllLeavesHelper(x.right, leaves);
        }
        return leaves;
    }

    public static void main(String[] args) {

        SearchTree<Integer> searchTree = new SearchTree<>();

        searchTree.insert(8);
        searchTree.insert(4);
        searchTree.insert(10);
        searchTree.insert(2);
        searchTree.insert(6);
        searchTree.insert(9);
        searchTree.insert(11);

        var x = searchTree.getAllLeaves();

        for(SearchTree<Integer> leaf : x) {
            System.out.println(leaf.key);
        }




    }

}
