package com.example.demo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AddNextNode {

    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        List<List<Node>> lists = new ArrayList<>();

        while (!q.isEmpty()) {
            int size = q.size();
            List<Node> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                list.add(node);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }

            }
            lists.add(list);
        }
        for (List<Node> list : lists) {
            list.stream().forEach(n -> {
                //System.out.println(n.val + " " + n.left + " " + n.right + " " + n.next);
                //System.out.println(n.val + " " + n.left + " " + n.right);
                n.print();
            });

        }
        return root;

    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public void print() {
            System.out.print(val);
            if (left != null)
                System.out.print(", " + left.val);
            if (right != null)
                System.out.print(", " + right.val);
            if (next != null)
                System.out.print(", " + next.val);
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        Node left1 = new Node(9);
        Node right1 = new Node(20);

        root.left = left1;
        root.right = right1;
        Node left2 = new Node(15);
        Node right2 = new Node(7);

        right1.left = left2;
        right1.right = right2;

        AddNextNode obj = new AddNextNode();
        Node res = obj.connect(root);
    }
}
