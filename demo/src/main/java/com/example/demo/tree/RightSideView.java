package com.example.demo.tree;

import java.util.*;

public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        LinkedList<Integer> nodesForLevel = new LinkedList<>();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // process all nodes at this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (i == levelSize - 1) {
                    nodesForLevel.add(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return nodesForLevel;

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

        RightSideView it = new RightSideView();
        List<Integer> nodes = it.rightSideView(root);
        System.out.println(nodes.toString());

        // Time Complexity: O(n)
        // Space Complexity: O(w)
    }

}
