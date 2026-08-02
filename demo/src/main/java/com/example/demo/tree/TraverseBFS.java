package com.example.demo.tree;

import java.util.*;

public class TraverseBFS {
    public List<List<Integer>> traverse(TreeNode root) {
        if (root == null) return null;

        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);

        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = dq.poll();
                if (node != null)
                    list.add(node.val);
                if (node.left != null) {
                    dq.offer(node.left);
                }
                if (node.right != null) {
                    dq.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
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

        TraverseBFS it = new TraverseBFS();
        List<List<Integer>> lists = it.traverse(root);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }

    }
}
