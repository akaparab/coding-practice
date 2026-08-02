package com.example.demo.tree;

import java.util.*;


public class NrySerializedDesirialized {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // --------------------------------------------------
    // SERIALIZE
    // --------------------------------------------------

    public String serialize(Node root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        serializeHelper(root, sb);

        return sb.toString();
    }

    private void serializeHelper(
            Node node,
            StringBuilder sb) {

        if (node == null) {
            return;
        }

        // Add node value
        sb.append(node.val).append(",");

        // Add number of children
        sb.append(node.children.size()).append(",");

        // Serialize each child
        for (Node child : node.children) {

            serializeHelper(child, sb);
        }
    }

    // --------------------------------------------------
    // DESERIALIZE
    // --------------------------------------------------

    public Node deserialize(String data) {

        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] values = data.split(",");

        // Index shared across recursive calls
        int[] index = {0};

        return deserializeHelper(values, index);
    }

    private Node deserializeHelper(
            String[] values,
            int[] index) {

        // Read node value
        int value =
                Integer.parseInt(values[index[0]++]);

        // Read number of children
        int childCount =
                Integer.parseInt(values[index[0]++]);


        Node node = new Node(value);

        // Deserialize children
        for (int i = 0; i < childCount; i++) {

            Node child =
                    deserializeHelper(values, index);

            node.children.add(child);
        }

        return node;
    }

    // --------------------------------------------------
    // PRINT TREE
    // --------------------------------------------------

    public static void printTree(
            Node root,
            int level) {

        if (root == null) {
            return;
        }

        // Indentation
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }

        System.out.println(root.val);

        for (Node child : root.children) {

            printTree(child, level + 1);
        }
    }

    // --------------------------------------------------
    // MAIN
    // --------------------------------------------------

    public static void main(String[] args) {

        /*
                 1
               / | \
              2  3  4
                / \
               5   6
        */

        Node root = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node3.children.add(node5);
        node3.children.add(node6);

        root.children.add(node2);
        root.children.add(node3);
        root.children.add(node4);
        NrySerializedDesirialized obj = new NrySerializedDesirialized();

        // Serialize
        String serialized = obj.serialize(root);

        System.out.println(
                "Serialized Tree:"
        );

        System.out.println(serialized);

        // Deserialize
        Node deserialized =
                obj.deserialize(serialized);

        System.out.println(
                "\nDeserialized Tree:"
        );

        printTree(deserialized, 0);
    }
}
