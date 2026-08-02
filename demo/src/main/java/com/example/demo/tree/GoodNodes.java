package com.example.demo.tree;

import java.util.ArrayList;
import java.util.List;

public class GoodNodes {
    public Integer goodNodesNum(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    public List<TreeNode> goodNodes(TreeNode root) {
        if (root == null) return null;
        List<TreeNode> res = new ArrayList<TreeNode>();
        getGoodNodes(root, Integer.MIN_VALUE, res);
        return res;
    }

    private void getGoodNodes(TreeNode root, int max, List<TreeNode> list) {
        if (root == null) return;
        if (root.val >= max) {
            max = root.val;
            list.add(root);
        }
        getGoodNodes(root.left, max, list);
        getGoodNodes(root.right, max, list);
    }

    public int dfs(TreeNode root, int max) {
        if (root == null) return 0;
        int count = 0;

        if (root.val >= max) {
            max = root.val;
            count++;
        }

        int leftMax = dfs(root.left, max);
        int rightMax = dfs(root.right, max);

        return count + Math.max(leftMax, rightMax);
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(7);
        root.setLeft(left1);
        root.setRight(right1);
        TreeNode left2 = new TreeNode(1);
        TreeNode right2 = new TreeNode(3);

        left1.setLeft(left2);
        left1.setRight(right2);

        right1.left = new TreeNode(6);
        right1.right = new TreeNode(9);

        GoodNodes md = new GoodNodes();
        System.out.println(md.goodNodes(root));
        List<TreeNode> list = md.goodNodes(root);

    }
}
