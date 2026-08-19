package com.example.demo.tree;

public class SameColorSubTree {
    static class Result {
        boolean sameColor;
        int size;
        char color;

        Result(boolean sameColor, int size, char color) {
            this.sameColor = sameColor;
            this.size = size;
            this.color = color;
        }
    }

    class TreeNode {
        char color;
        TreeNode left;
        TreeNode right;

        TreeNode(char color) {
            this.color = color;
        }
    }

    public class Solution {

        private int maxSize = 0;

        public int largestSameColorSubtree(TreeNode root) {
            dfs(root);
            return maxSize;
        }

        private Result dfs(TreeNode node) {

            if (node == null) {
                return new Result(true, 0, node == null ? '\0' : node.color);
            }

            Result left = dfs(node.left);
            Result right = dfs(node.right);

            boolean sameColor =
                    left.sameColor &&
                            right.sameColor &&
                            (node.left == null || left.color == node.color) &&
                            (node.right == null || right.color == node.color);

            int size = 1 + left.size + right.size;

            if (sameColor) {
                maxSize = Math.max(maxSize, size);
            }

            return new Result(
                    sameColor,
                    size,
                    node.color
            );
        }
    }
}
