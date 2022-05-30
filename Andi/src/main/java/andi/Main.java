package andi;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SearchTree<Integer> searchTree = new SearchTree<>();


        searchTree.insert(2);
        searchTree.insert(4);
        searchTree.insert(6);
        searchTree.insert(8);
        searchTree.insert(9);
        searchTree.insert(10);
        searchTree.insert(11);


        //System.out.println(searchTree.maximum().key);
        //System.out.println(searchTree.minimum().key);

        searchTree.delete(8);
        searchTree.delete(2);

        searchTree.inorder();
        ArrayList<Integer> arr = searchTree.toSortedArrayList();

        System.out.println(arr.toString());
    }
}
