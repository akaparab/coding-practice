package com.example.demo.tree;

public class PathSum {
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (targetSum - root.val == 0) return true;
        boolean flag1 = false;
        boolean flag2 = false;

        if (root.left != null) {
            flag1 = hasPathSum(root.left, targetSum - root.val);
        }
        if (root.right != null) {
            flag2 = hasPathSum(root.right, targetSum - root.val);
        }

        if (flag1 || flag2) return true;
        return false;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (sum - root.val == 0) return true;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public boolean pathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null || root.right == null) {
            if (sum - root.val == 0) return true;
            else return false;
        }


        return pathSum(root.left, sum - root.val) || pathSum(root.right, sum - root.val);
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

        PathSum it = new PathSum();
        System.out.println("has path sum: " + it.hasPathSum(root, 22));
    }
}
