package andi;

import java.util.ArrayList;

public class BalancedSearchTree<T extends Comparable<T>> {

    double alpha = 0.25;
    double b = 0.2373557003;
    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
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
        rebalanceTree(z, alpha);
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
        rebalanceTree(z, alpha);
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

    public void rebalanceTree(Node<T> k, double a) {
        Node<T> temp = k;
        while (temp != null) {
            if (rootBalance(temp) < a) {
                if (rootBalance(temp.right) <= 1 / (2 - a)) {
                    leftRotate(temp.key);
                } else {
                    doubleLeftRotate(temp.key);
                }
            }
            if (rootBalance(temp) > 1 - a) {
                if (rootBalance(temp.left) >= 1 - 1 / (2 - a)) {
                    rightRotate(temp.key);
                } else {
                    doubleRightRotate(temp.key);
                }
            }
            temp = temp.p;
        }
    }

    private double rootBalance(Node<T> k) {
        if (k != null) {
            return (countSubTree(k.left) + 1) / (countSubTree(k) + 1);
        }
        return 0;
    }

    public double countSubTree(Node<T> x) {
        if (x == null) {
            return 0;
        }
        if (x != null) {
            return countSubTree(x.left) + countSubTree(x.right) + 1;
        }
        return 0;
    }

    public void doubleRightRotate(T k) {
        doubleRightRotateHelper(search(k), k);
    }

    private void doubleRightRotateHelper(Node<T> x, T k) {
        if (x.left != null && x.left.right != null) {
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
    }

    public void doubleLeftRotate(T k) {
        doubleLeftRotateHelper(search(k), k);
    }

    private void doubleLeftRotateHelper(Node<T> x, T k) {
        if (x.right != null && x.right.left != null) {
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
    }

    public void rightRotate(T k) {
        rightRotateHelper(search(k), k);
    }

    private void rightRotateHelper(Node<T> x, T k) {
        if (x.left != null) {
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
    }

    public void leftRotate(T k) {
        leftRotateHelper(search(k), k);
    }

    private void leftRotateHelper(Node<T> x, T k) {
        if (x.right != null) {
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

    public Node<T> search(T k) {
        return searchHelper(this.root, k);
    }

    private Node<T> searchHelper(Node<T> x, T k) {
        if (x == null || x.key.equals(k)) {
            return x;
        }
        if (k.compareTo(x.key) < 0) {
            return searchHelper(x.left, k);
        } else {
            return searchHelper(x.right, k);
        }
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

    public void inorder() {
        inorderHelper(this.root);
        System.out.println();
    }

    private void inorderHelper(Node<T> x) {
        if (x != null) {
            inorderHelper(x.left);
            System.out.print(x.key + " ");
            inorderHelper(x.right);
        }
    }
}
