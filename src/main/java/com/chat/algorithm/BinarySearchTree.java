package com.chat.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangtianyu on 2017/2/21.
 */
public class BinarySearchTree {
    private List<TreeNode> nodeList = new ArrayList<>();
    private class TreeNode {
        private int key;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        public TreeNode(int key, TreeNode left, TreeNode right, TreeNode parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public int getKey() {
            return key;
        }

        public String toString() {
            String lkey = (left == null) ? "" : Integer.toString(left.key);
            String rkey = (right == null) ? "" : Integer.toString(right.key);
            return "(" + lkey + "," + key + "," + rkey + ")";
        }
    }

    private TreeNode treeNode = null;

    private TreeNode recSearch(TreeNode node, int key) {
        if (node.key > key) {
            return recSearch(node.left, key);
        }
        else if (node.key < key){
            return recSearch(node.right, key);
        }
        else {
            return node;
        }
    }

    public TreeNode loopSearch (int key) {
        TreeNode node = treeNode;
        while (node != null && node.key != key) {
            if (node.key > key) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return node;
    }

    private TreeNode maxNode(TreeNode node) throws Exception{
        if (node == null) {
            throw new Exception("node is null");
        }
        TreeNode n = node;
        while(n.right != null) {
            n = n.right;
        }
        return n;
    }

    private TreeNode minNode(TreeNode node) throws Exception{
        if (node == null) {
            throw new Exception("node is null");
        }
        TreeNode n = node;
        while(n.left != null) {
            n = n.left;
        }
        return n;
    }

    public void insert (int key) {
        TreeNode node = treeNode;
        TreeNode newNode = new TreeNode(key, null, null, null);
        if (treeNode == null) {
            treeNode = newNode;
            return;
        }
        while (node != null && node.key != key) {
            if (key < node.key) {
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node.left = new TreeNode(key, null, null, node);
                }
            }
            else {
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    node.right = new TreeNode(key, null, null, node);
                }
            }
        }
    }

    public void delete (int key) throws Exception {
        TreeNode node = loopSearch(key);
        delete(node);
    }

    public void delete (TreeNode node) throws Exception {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && node.parent != null) {
            TreeNode parent = node.parent;
            if (parent.left == node) {
                parent.left = null;
            }
            else {
                parent.right = null;
            }
        }
        else if (node.left != null && node.right == null && node.parent != null) {
            TreeNode parent = node.parent;
            TreeNode lChild = node.left;
            if (parent.left == node) {
                parent.left = lChild;
                lChild.parent = parent;
            }
            else {
                parent.right = lChild;
                lChild.parent = parent;
            }
        }
        else if (node.left == null && node.right != null && node.parent != null) {
            TreeNode parent = node.parent;
            TreeNode rChild = node.right;
            if (parent.right == node) {
                parent.right = rChild;
                rChild.parent = parent;
            }
            else {
                parent.left = rChild;
                rChild.parent = parent;
            }
        }
        else {
            TreeNode minNode = minNode(node.right);
            node.key = minNode.key;
            delete(minNode);
        }
    }

    private void preOrder (TreeNode node) {
        if (node != null) {
            nodeList.add(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void inOrder (TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            nodeList.add(node);
            inOrder(node.right);
        }
    }

    public String printTree() {
        String tree = "";
        if (nodeList != null) {
            nodeList = new ArrayList<>();
        }
        inOrder(treeNode);
        for (TreeNode node : nodeList) {
            tree += node.key;
            tree += " ";
        }
        return tree;
    }
}
