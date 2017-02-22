package com.chat.algorithm;

import org.springframework.stereotype.Service;

/**
 * Created by xiangtianyu on 2017/2/21.
 */
@Service
public class alService {
    public void testBST() throws Exception {
        String tree = "";
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(1);
        tree = binarySearchTree.printTree();
        System.out.println("tree: " + tree);
        binarySearchTree.insert(5);
        binarySearchTree.insert(6);
        binarySearchTree.insert(2);
        binarySearchTree.insert(7);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        tree = binarySearchTree.printTree();
        System.out.println("tree: " + tree);
        binarySearchTree.delete(5);
        tree = binarySearchTree.printTree();
        System.out.println("tree: " + tree);
        binarySearchTree.delete(1);
        tree = binarySearchTree.printTree();
        System.out.println("tree: " + tree);
    }
}
