package com.example.demo.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


public class TreeMaxDepth {
    /**
     * DFS - recursion
     *
     * @param
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            System.out.print(root.getVal() + " ");
            int left_height = maxDepth(root.getLeft());
            int right_height = maxDepth(root.getRight());
            return java.lang.Math.max(left_height, right_height) + 1;
        }
    }

    /**
     * BFS - iterative traversing
     *
     * @param root
     * @return
     */
    public int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> dq = new ArrayDeque<>();
        int depth = 0, next = 0;
        TreeNode cur;
        dq.offer(root);
        System.out.println(Arrays.toString(dq.toArray()));

        while (!dq.isEmpty()) {
            depth++;
            next = dq.size();
            System.out.println("dq.size : " + next + " depth : " + depth);
            for (int i = 0; i < next; ++i) {
                cur = dq.poll();
                if (cur.getLeft() != null) {
                    dq.offer(cur.getLeft());
                    System.out.println("Left: " + cur.getLeft().getVal());
                }
                if (cur.getRight() != null) {
                    dq.offer(cur.getRight());
                    System.out.println("Right: " + cur.getRight().getVal());
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        //int nums[] = {3,9,20,null,null,15,7};

        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(20);
        root.setLeft(left1);
        root.setRight(right1);
        TreeNode left2 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);

        right1.setLeft(left2);
        right1.setRight(right2);
        TreeMaxDepth md = new TreeMaxDepth();
        //System.out.println("DFS Max Depth : " + md.maxDepth(root));
        System.out.println("BFS Max Depth : " + md.maxDepthBFS(root));


        // time complexity is O(N)
        // space complexity o(log(n))
        /**
         * Approach 1: Recursion (DFS)
         *
         * Time complexity: O(N) - visits each node exactly once
         * Space complexity: O(N) in worst case (unbalanced tree), O(log N) in best case (balanced tree) due to call stack
         * Approach 2: Tail Recursion + BFS
         *
         * Time complexity: O(N) - still visits each node once
         * Space complexity: O(N) - due to maintaining state information for BFS traversal
         * Approach 3: Iteration (using stack)
         *
         * Time complexity: O(N)
         * Space complexity: O(N) in worst case, O(log N) in average case (balanced tree)
         */
    }
}

