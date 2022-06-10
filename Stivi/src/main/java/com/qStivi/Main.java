package com.qStivi;

public class Main {

    public static void main(String[] args) {
        BalancedSearchTree<Integer> searchTree = new BalancedSearchTree<>(0.25);


        searchTree.insertion(30);
        searchTree.insertion(20);
        searchTree.insertion(23);
        searchTree.insertion(3);
        searchTree.insertion(21);
        searchTree.insertion(27);
        searchTree.insertion(45);
        searchTree.insertion(33);
        searchTree.insertion(32);
        searchTree.insertion(42);
        searchTree.insertion(51);
        searchTree.insertion(47);
        searchTree.insertion(67);

        searchTree.levelOrderTraversal(searchTree.root);
    }

}
