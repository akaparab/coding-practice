package com.example.demo.tree;

import lombok.Data;

@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void printTree(TreeNode root) {
        if (root != null) {
            System.out.print(root.getVal() + " ");
            printTree(root.getLeft());
            printTree(root.getRight());
        }

    }

    public static TreeNode createTree(int[] nums) {
        return null;
    }
}
