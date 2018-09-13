package ru.adtimokhin.lesson6;

/**
 * Class NumberTree. Created by adtimokhin. 12.09.2018 (09:47)
 **/
public class NumberTree {
    private class TreeNode {
        private int number;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int number) {
            this.number = number;

        }

        @Override
        public String toString() {
            return String.format("%d)", number);
        }
    }

    TreeNode root;

    public int find(int number) {
        TreeNode current = root;
        while (current.number != number) {
            if (number < current.number)
                current = current.left;
            else
                current = current.right;

            if (current == null)
                return -101;
        }
        return current.number;
    }

    public void insert(int number) {
        TreeNode node = new TreeNode(number);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode previous;
            while (true) {
                previous = current;
                if (number < current.number) {
                    current = current.left;
                    if (current == null) {
                        previous.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        previous.right = node;
                        return;
                    }
                }
            }
        }
    }

    public void displayTree() {
        inOrderTravers(root);
    }

    private void inOrderTravers(TreeNode current) {
        if (current != null) {
            inOrderTravers(current.left);
            System.out.println(current);
            inOrderTravers(current.right);
        }
    }

    public boolean delete(int number) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.number != number) {
            parent = current;
            if (number < current.number) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null)
                return false;
        }

        //if node is a leaf
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }
        // if one successor
        else if (current.right == null) {
            if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        } else if (current.left == null) {
            if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        // if both successors exist
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;

            successor.left = current.left;
            successor.right = current.right;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode current = node.right;
        TreeNode s = node;
        TreeNode parent = node;
        while (current != null) {
            parent = s;
            s = current;
            current = current.left;
        }
        if (s != node.right) {
            parent.left = s.right;
        }
        return s;
    }

    public int isBalanced(TreeNode root, int level){
        int i = 0,j =0;
        if(root.left == null && root.right == null && this.root != root)return level;// if leaf
        if(root.left!= null)
        i = isBalanced(root.left, level+1);
        if(root.right !=null)
        j= isBalanced(root.right, level+1);
        if(root == this.root)
            return (i == j)?1:0;
        else
        return (i > j)? i :j;
    }

}

