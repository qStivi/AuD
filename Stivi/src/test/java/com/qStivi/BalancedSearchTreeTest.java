package com.qStivi;

import org.junit.jupiter.api.Test;

class BalancedSearchTreeTest {

    @Test
    void test() {
        var t = new BalancedSearchTree<Integer>(((1 - ((double) 1 / Math.sqrt(2))) + ((double) 2 / 11)) / 2);
        for (int i = 0; i < 100; i++) {
            t.insertion(i);
        }
        System.out.println(t.size(t.root.left));
        System.out.println(t.size(t.root.right));
        for (int i = 0; i < 50; i++) {
            t.deletion(i);
        }
        System.out.println(t.size(t.root.left));
        System.out.println(t.size(t.root.right));
    }
}
