package com.example.demo.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class SymmetricTree {

    public boolean isSymmetric1(TreeNode root) {
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);

        while (!dq.isEmpty()) {
            int size = dq.size();
            System.out.println("size : " + size);

            int i = 0;

            int[] row = new int[size];
            for (i = 0; i < size; i++) {
                TreeNode cur = dq.poll();
                row[i] = cur.val;
                System.out.print(cur.val + "");
                if (cur.left == null && cur.right != null) {
                    return false;
                }
                if (cur.left != null && cur.right == null) {
                    return false;
                }
                if (cur.left != null) {
                    dq.offer(cur.left);
                }
                if (cur.right != null) {
                    dq.offer(cur.right);
                }
            }
            if (size > 1) {
                if (!isPalindrome(row)) {
                    return false;
                }
            }
            System.out.println("\n");
        }
        return true;
    }

    private boolean isPalindrome(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while (i <= j) {
            if (nums[i] != nums[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (
                (t1.val == t2.val) &&
                        isMirror(t1.right, t2.left) &&
                        isMirror(t1.left, t2.right)
        );
    }

    public static void main(String[] args) {
        // 1,2,2,3,4,4,3
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(2);
        root.left = left1;
        root.setRight(right1);

        root.getLeft().setLeft(new TreeNode(3));
        root.getLeft().setRight(new TreeNode(4));
        root.getRight().setLeft(new TreeNode(4));
        root.getRight().setRight(new TreeNode(3));

        SymmetricTree it = new SymmetricTree();
        System.out.println("is Symmetric: " + it.isSymmetric(root));

        /**
         * Time complexity: O(n). Because we traverse the entire input tree once,
         * the total run time is O(n), where n is the total number of nodes in the tree.
         *
         * Space complexity: The number of recursive calls is bound by the height of
         * the tree. In the worst case, the tree is linear and the height is in O(n).
         * Therefore, space complexity due to recursive calls on the stack is O(n) in
         * the worst case.
         */
    }
}
