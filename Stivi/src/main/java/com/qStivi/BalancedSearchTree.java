package com.qStivi;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BalancedSearchTree<K extends Comparable<K>> {

    public Node<K> root;
    public double α;

    public BalancedSearchTree(double α) {
        this.α = α;
    }

    public void leftRotate(Node<K> x) {
        if (x == null || x.right == null) return;
        var y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node<K> x) {
        if (x == null || x.left == null) return;
        var y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    public void doubleLeftRotate(Node<K> x) {
        rightRotate(x.right);
        leftRotate(x);
    }

    public void doubleRightRotate(Node<K> x) {
        leftRotate(x.left);
        rightRotate(x);
    }

    public double ρ(Node<K> root) {
        return (double) (root == null ? 0 : size(root.left) + 1) / (double) (size(root) + 1);
    }

    public long size(Node<K> root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    public void rebalance(Node<K> changed) {
        while (changed != null) {
            if (ρ(changed) < α) {
                if (ρ(changed.right) <= (1 / (2 - α))) {
                    leftRotate(changed);
                } else doubleLeftRotate(changed);
            }
            if (ρ(changed) > 1 - α) {
                if (ρ(changed.left) >= (1 / (2 - α))) {
                    rightRotate(changed);
                } else doubleRightRotate(changed);
            }
            changed = changed.parent;
        }
    }

    public void insertion(K key) {
        var z = new Node<>(key);
        var y = this.root;
        while (y != null) {
            z.parent = y;
            if (z.key.compareTo(y.key) < 0) {
                y = y.left;
            } else {
                y = y.right;
            }
        }
        if (z.parent == null) {
            this.root = z;
        } else {
            if (z.key.compareTo(z.parent.key) < 0) {
                z.parent.left = z;
            } else {
                z.parent.right = z;
            }
        }
        rebalance(z);
    }

    public void deletion(K key) {
        var z = search(this.root, key);
        if (z == null) throw new NoSuchElementException();
        var y = z;
        if (z.left != null && z.right != null) y = successor(z);
        var x = y.left != null ? y.left : y.right;
        if (x != null) {
            x.parent = y.parent;
        }
        if (y.parent == null) {
            this.root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else y.parent.right = x;
        if (y != z) {
            z.key = y.key;
        }
        rebalance(z);
    }

    public Node<K> search(Node<K> root, K key) {
        if (root == null || root.key.equals(key)) return root;
        if (key.compareTo(root.key) < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public Node<K> successor(Node<K> x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        var y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    public Node<K> minimum(Node<K> x) {
        if (x == null) throw new NoSuchElementException("Tree is empty!");
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Node<K> maximum(Node<K> x) {
        if (x == null) throw new NoSuchElementException("Tree is empty!");
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public ArrayList<K> toSortedArrayList(Node<K> root) {
        return toSortedArrayListHelper(root, new ArrayList<>());
    }

    private ArrayList<K> toSortedArrayListHelper(Node<K> root, ArrayList<K> list) {
        if (root != null) {
            toSortedArrayListHelper(root.left, list);
            list.add(root.key);
            toSortedArrayListHelper(root.right, list);
        }
        return list;
    }

    // Function to print all nodes of a given level from left to right
    public boolean printLevel(Node<K> root, int level) {
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
    public void levelOrderTraversal(Node<K> root) {
        // start from level 1 — till the height of the tree
        int level = 1;

        // run till printLevel() returns false
        while (printLevel(root, level)) {
            level++;
        }
    }
}
