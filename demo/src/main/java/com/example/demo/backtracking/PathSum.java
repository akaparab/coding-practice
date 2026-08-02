package com.example.demo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, new ArrayList<>(), 0, target, result);
        return result;
    }

    private void backtrack(TreeNode node, List<Integer> path, int total, int target, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        path.add(node.getVal());
        total += node.getVal();

        // KEY STEP 2
        // current sum exceeds target
        // so pop to remove the current node from the path
        // return to backtrack to previous node on the call stack
        if (total > target) {
            path.remove(path.size() - 1);
            return;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            // add the path to the result
            // note we have to make a copy (new ArrayList<>(path)) of the path
            // since future recursive calls modify path
            if (total == target) {
                result.add(new ArrayList<>(path));
            }
        } else {
            backtrack(node.getLeft(), path, total, target, result);
            backtrack(node.getRight(), path, total, target, result);
        }

        // KEY STEP 1
        // we have finished exploring all paths containing the current node
        // so pop to remove the current node from the path
        // return to backtrack to previous node on the call stack.
        path.removeLast();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(8);
        root.setLeft(left1);
        root.setRight(right1);

        root.left.left = new TreeNode(11);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);

        PathSum ps = new PathSum();
        List<List<Integer>> res = ps.pathSum(root, 22);
        for (List<Integer> it : res) {
            System.out.println(it.toString());
        }
    }
}
