package com.example.demo.tree;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    public TreeNode lowestCommonAncestor(
            TreeNode root,
            TreeNode p,
            TreeNode q) {

        if (root == null)
            return null;

        if (root == p || root == q)
            return root;

        TreeNode left =
                lowestCommonAncestor(root.left, p, q);

        TreeNode right =
                lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // 4,2,7,1,3,6,9
        TreeNode root = new TreeNode(4);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(7);
        root.setLeft(left1);
        root.setRight(right1);

        root.getLeft().setLeft(new TreeNode(1));
        root.getLeft().setRight(new TreeNode(3));
        root.getRight().setLeft(new TreeNode(6));
        root.getRight().setRight(new TreeNode(9));

        LowestCommonAncestor lca = new LowestCommonAncestor();
        System.out.println(lca.lowestCommonAncestorBST(root, left1, right1).val);
        System.out.println(lca.lowestCommonAncestor(root, left1, right1).val);
    }

}
