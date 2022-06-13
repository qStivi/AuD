package andi;

import java.util.ArrayList;

public class SearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

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

    public void postorder() {
        postorderHelper(this.root);
    }

    private void postorderHelper(Node<T> x) {
        if (x != null) {
            postorderHelper(x.left);
            postorderHelper(x.right);
            System.out.println(x.key);
        }
    }

    public void doubleLeftRotate(T k) {
        doubleLeftRotateHelper(search(k), k);
    }

    private void doubleLeftRotateHelper(Node<T> x, T k) {
        Node<T> y = x.right;
        Node<T> z = y.left;
        x.right = z.left;
        if (z.left != null) {
            z.left.p = x;
        }

        z.p = x.p;
        if (x.p == null) {
            this.root = z;
        } else if (x == x.p.left) {
            x.p.left = z;
        } else {
            x.p.right = z;
        }
        z.left = x;
        x.p = z;

        y.left = z.right;
        if (z.right != null) {
            z.right.p = y;
        }
        z.right = y;
        y.p = z;

    }

    // Function to print all nodes of a given level from left to right
    public boolean printLevel(Node<T> root, int level) {
        // base case
        if (root == null) {
            return false;
        }

        if (level == 1) {
            System.out.print(root.key + " ");

            // return true if at least one node is present at a given level
            return true;
        }

        boolean left = printLevel(root.left, level - 1);
        boolean right = printLevel(root.right, level - 1);

        return left || right;
    }

    // Function to print level order traversal of a given binary tree
    public void levelOrderTraversal(Node<T> root) {
        // start from level 1 — till the height of the tree
        int level = 1;

        // run till printLevel() returns false
        while (printLevel(root, level)) {
            level++;
        }
    }

    public void doubleRightRotate(T k) {
        doubleRightRotateHelper(search(k), k);
    }

    private void doubleRightRotateHelper(Node<T> x, T k) {
        Node<T> y = x.left;
        Node<T> z = y.right;
        x.left = z.right;
        if (z.right != null) {
            z.right.p = x;
        }

        z.p = x.p;
        if (x.p == null) {
            this.root = z;
        } else if (x == x.p.right) {
            x.p.right = z;
        } else {
            x.p.left = z;
        }
        z.right = x;
        x.p = z;

        y.right = z.left;
        if (z.left != null) {
            z.left.p = y;
        }
        z.left = y;
        y.p = z;

    }

    public void rightRotate(T k) {
        rightRotateHelper(search(k), k);
    }

    private void rightRotateHelper(Node<T> x, T k) {
        Node<T> y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.left.p = x;
        }
        y.p = x.p;
        if (x.p == null) {
            this.root = y;
        } else if (x == x.p.right) {
            x.p.right = y;
        } else {
            x.p.left = y;
        }
        y.right = x;
        x.p = y;
    }

    public void leftRotate(T k) {
        leftRotateHelper(search(k), k);
    }

    private void leftRotateHelper(Node<T> x, T k) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.p = x;
        }
        y.p = x.p;
        if (x.p == null) {
            this.root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }
        y.left = x;
        x.p = y;
    }

    public int countSubTree (Node<T> x) {
        if (x == null) {
            return 0;
        }
        if (x != null ) {
            return countSubTree(x.left) + countSubTree(x.right) + 1;
        }
        return -1;
    }

}
