package com.example.demo.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class CountNodes {

    /**
     * BFS traverse - iteration
     *
     * @param root
     * @return
     */
    public int countNodes1(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> dq = new ArrayDeque();

        dq.offer(root);
        int count = 0;

        while (!dq.isEmpty()) {
            int size = dq.size();
            count += size;
            for (int i = 0; i < size; i++) {
                TreeNode cur = dq.poll();
                if (cur.left != null) {
                    dq.offer(cur.left);
                }
                if (cur.right != null) {
                    dq.offer(cur.right);
                }
            }

        }
        return count;
    }

    /**
     * DFS traverse - recursion
     *
     * @param root
     */
    public int countNodes(TreeNode root) {
        return root != null ? 1 + countNodes(root.right) + countNodes(root.left) : 0;
    }

    public static void main(String[] args) {
        // 1,2,3,4,5,6
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        root.left = left1;
        root.right = right1;

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);


        CountNodes it = new CountNodes();
        System.out.println("has path sum: " + it.countNodes(root));
    }
}
