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
}
