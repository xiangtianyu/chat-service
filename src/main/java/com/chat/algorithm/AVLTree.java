package com.chat.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangtianyu on 2017/2/22.
 */
public class AVLTree {
    private List<TreeNode> nodeList = new ArrayList<>();
    private class TreeNode {
        private int key;
        private TreeNode left;
        private TreeNode right;
        private int height;

        public TreeNode(int key, TreeNode left, TreeNode right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
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

    private int getNodeHeight(TreeNode node) {
        if (node != null) {
            return node.height;
        }
        return 0;
    }

    private TreeNode treeNode = null;

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

    private TreeNode llRotation (TreeNode node) {
        TreeNode n = node.left;
        node.left = n.right;
        n.right = node;

        node.height = Math.max(node.left.height, node.right.height) + 1;
        n.height = Math.max(n.left.height, n.right.height) + 1;

        return n;
    }

    private TreeNode rrRotation (TreeNode node) {
        TreeNode n = node.right;
        node.right = n.left;
        n.left = node;

        node.height = Math.max(node.left.height, node.right.height) + 1;
        n.height = Math.max(n.left.height, n.right.height) + 1;

        return n;
    }

    private TreeNode rlRotation (TreeNode node) {
        node.right = llRotation(node.right);

        return rrRotation(node);
    }

    private TreeNode lrRotation (TreeNode node) {
        node.left = rrRotation(node.left);

        return llRotation(node);
    }

    public void insert (int key) {

    }
}
