package com.example.demo.tree;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MaxPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs1(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(0, dfs(node.left));
        int rightGain = Math.max(0, dfs(node.right));

        int currentPath =
                leftGain +
                        node.val +
                        rightGain;

        maxSum = Math.max(maxSum, currentPath);

        return node.val +
                Math.max(leftGain, rightGain);
    }

    private int dfs1(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int leftGain = dfs(node.left);
        int rightGain = dfs(node.right);

        int currentPath =
                leftGain +
                        node.val +
                        rightGain;

        maxSum = Math.max(maxSum, currentPath);

        return node.val +
                Math.max(leftGain, rightGain);
    }

    static class Frame {
        TreeNode node;
        boolean visited;

        Frame(TreeNode node, boolean visited) {
            this.node = node;
            this.visited = visited;
        }
    }

    /**
     * For large skewed tree, stack overflow can happen
     * Use iterator
     */
    public int maxPathSumIterator(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;

        // Stores the result that each node contributes
        // to its parent.
        Map<TreeNode, Integer> contribution = new HashMap<>();

        Deque<Frame> stack = new ArrayDeque<>();

        stack.push(new Frame(root, false));

        while (!stack.isEmpty()) {

            Frame frame = stack.pop();

            TreeNode node = frame.node;

            if (node == null) {
                continue;
            }

            if (!frame.visited) {

                // Post-order:
                // left -> right -> node

                stack.push(new Frame(node, true));

                if (node.right != null) {
                    stack.push(new Frame(node.right, false));
                }

                if (node.left != null) {
                    stack.push(new Frame(node.left, false));
                }

            } else {

                int left =
                        contribution.getOrDefault(
                                node.left, 0);

                int right =
                        contribution.getOrDefault(
                                node.right, 0);

                left = Math.max(0, left);
                right = Math.max(0, right);

                // Complete path through current node
                int currentPath =
                        node.val + left + right;

                maxSum =
                        Math.max(maxSum, currentPath);

                // Contribution to parent
                int nodeContribution =
                        node.val + Math.max(left, right);

                contribution.put(node, nodeContribution);
            }
        }

        return maxSum;
    }


    public static void main(String[] args) {
        // root = [5,4,8,11,null,13,4,7,2,null,null,null,1],

        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(8);
        root.left = left1;
        root.right = right1;

        root.left.left = new TreeNode(11);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);

        MaxPathSum it = new MaxPathSum();
        System.out.println("max Sum: " + it.maxPathSum(root));
        System.out.println("max Sum: " + it.maxPathSumIterator(root));

    }
}
