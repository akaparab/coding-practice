package com.example.demo.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreeSerializeDeserialize {

    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        dfs(root, sb);
        return sb.toString();

        // Time complexity O(N)
    }

    private void dfs(TreeNode root, StringBuffer sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    public TreeNode deseialize(String nodes) {
        Queue<String> queue =
                new LinkedList<>(
                        Arrays.asList(nodes.split(",")));

        return buildTree(queue);

        // Time complexity O(N)
        // Space complexity O(N)

    }

    private TreeNode buildTree(Queue<String> queue) {
        String str = queue.poll();
        assert str != null;
        if ("null".equals(str)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
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
        TreeSerializeDeserialize md = new TreeSerializeDeserialize();
        String nodes = md.serialize(root);
        TreeNode node = md.deseialize(nodes);
    }
}
