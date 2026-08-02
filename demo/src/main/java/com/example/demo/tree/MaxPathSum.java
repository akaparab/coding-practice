package com.example.demo.tree;

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
    }
}
