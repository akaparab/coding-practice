package com.example.demo;

import lombok.Data;

public class ListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        if (slow == null || fast == null) return false;

        while (slow != fast) {
            if (fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 0, -4};
        ListCycle lc = new ListCycle();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        ListNode head = node1;
        node1.next = node2;

        node2.next = node3;

        node3.next = node4;
        node4.next = node2;


//        ListNode head = lc.buildList(nums);
//        ListNode node = head;
//        lc.printList(node);
        System.out.println("isCyclic : " + lc.hasCycle(head));
    }

    public ListNode buildList(int[] nums) {
        ListNode node = new ListNode(nums[0]);
        ListNode head = node;
        for (int i = 1; i < nums.length; i++) {
            if (node.next == null) {
                ListNode node1 = new ListNode(nums[i]);
                node.next = node1;
                node = node.next;
            }
        }
        return head;
    }

    public void printList(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    @Data
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
