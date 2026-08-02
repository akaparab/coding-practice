package com.example.demo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Zigzag {
    public List<List<Integer>> zigZag(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> nodesForLevel = new LinkedList<>();
            // process all nodes at this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    // add the node to the back of the list
                    nodesForLevel.addLast(node.val);
                } else {
                    // add the node to the front of the list
                    nodesForLevel.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // we've processed all nodes at the current level
            // add them to the output list and toggle leftToRight
            // to prepare for the next level
            nodes.add(new ArrayList<>(nodesForLevel));
            leftToRight = !leftToRight;
        }
        return nodes;
    }

    public static void main(String[] args) {

    }
}
