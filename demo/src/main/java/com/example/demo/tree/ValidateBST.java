package com.example.demo.tree;

public class ValidateBST {
    public Boolean validateBST(TreeNode root) {
        return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    private boolean validate(TreeNode root, int minVal, int maxVal) {
        if (root == null) return true;

        if (root.val <= minVal || root.val >= maxVal) {
            return false;
        }
        return validate(root.left, minVal, root.val) && validate(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        root.setLeft(left1);
        root.setRight(right1);

        ValidateBST md = new ValidateBST();
        System.out.println(md.validateBST(root));
    }
}
