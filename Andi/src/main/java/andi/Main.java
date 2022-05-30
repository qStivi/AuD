package andi;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SearchTree<Integer> searchTree = new SearchTree<>();


        searchTree.insert(30);
        searchTree.insert(20);
        searchTree.insert(23);
        searchTree.insert(3);
        searchTree.insert(21);
        searchTree.insert(27);
        searchTree.insert(45);
        searchTree.insert(33);
        searchTree.insert(42);
        searchTree.insert(51);
        searchTree.insert(47);
        searchTree.insert(67);



        //System.out.println(searchTree.maximum().key);
        //System.out.println(searchTree.minimum().key);

        //searchTree.delete(8);
        //searchTree.delete(2);

        searchTree.postorder();
        //ArrayList<Integer> arr = searchTree.toSortedArrayList();

        //System.out.println(arr.toString());
    }
}
