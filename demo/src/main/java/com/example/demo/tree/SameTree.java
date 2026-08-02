package com.example.demo.tree;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) {
            return false;
        }
        if (p.getVal() != q.getVal()) return false;

        boolean flag1 = isSameTree(p.getLeft(), q.getLeft());
        boolean flag2 = isSameTree(p.getRight(), q.getRight());

        return flag1 && flag2;
    }

    public static void main(String[] args) {
        // p = [1,2,3], q = [1,2,3]

        TreeNode root1 = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        root1.setLeft(left1);
        root1.setRight(right1);

        TreeNode root2 = new TreeNode(1);
        TreeNode left2 = new TreeNode(2);
        TreeNode right2 = new TreeNode(3);
        root2.setLeft(left2);
        root2.setRight(right2);

        SameTree st = new SameTree();

        System.out.println("is same tree: " + st.isSameTree(root1, root2));

    }
}
