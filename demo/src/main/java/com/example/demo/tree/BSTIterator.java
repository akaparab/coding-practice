package com.example.demo.tree;


import java.util.ArrayList;
import java.util.List;

class BSTIterator {
    List<Integer> list;
    int idx;

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        idx = -1;
        inorderTraverse(root);

    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) return;

        inorderTraverse(root.left);
        list.add(root.val);
        inorderTraverse(root.right);

    }

    public int next() {
        int val = this.list.get(++this.idx);
        return val;
    }

    public boolean hasNext() {
        if (idx + 1 < list.size()) return true;
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        root.setLeft(left1);
        root.setRight(right1);
        BSTIterator obj = new BSTIterator(root);
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
        System.out.println(obj.next());

    }
}

