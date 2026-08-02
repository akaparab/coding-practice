package com.example.demo.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class TreeInvert {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> dq = new ArrayDeque<>();
        int depth = 0, next = 0;
        TreeNode cur;
        dq.offer(root);
        System.out.println(Arrays.toString(dq.toArray()));

        while (!dq.isEmpty()) {
            next = dq.size();
            System.out.println("dq.size : " + next + " depth : " + depth);
            for (int i = 0; i < next; ++i) {
                cur = dq.poll();
                TreeNode tmp = cur.getLeft();
                cur.setLeft(cur.getRight());
                cur.setRight(tmp);
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
        return root;
    }

//    private void printTree(TreeNode root) {
//        if (root != null) {
//            System.out.print(root.getVal() + " ");
//            printTree(root.getLeft());
//            printTree(root.getRight());
//        }
//
//    }

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

        TreeInvert it = new TreeInvert();
        TreeNode node = it.invertTree(root);
        TreeNode.printTree(node);

        // Time Complexity: O(n)
        // Space Complexity: O(w)
    }
}
