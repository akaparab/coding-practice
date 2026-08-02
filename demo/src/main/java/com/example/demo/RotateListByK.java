package com.example.demo;

import com.example.demo.heap.ListNode;
import lombok.Data;

public class RotateListByK {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode node = head;
        ListNode cur = head;
        ListNode res = head;

        int count = 0;

        while (node != null) {
            node = node.next;
            count++;
        }
        for (int i = 0; i < count - k; i++) {
            cur = cur.next;
        }

        ListNode newHead = cur;
        while (cur.next != null)
            cur = cur.next;
        for (int i = 0; i < count - k; i++) {
            cur.next = res;
            res = res.next;
            cur = cur.next;
        }
        cur.next = null;
        head = newHead;

        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        ListNode head = node1;
        node1.next = node2;

        node2.next = node3;

        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        RotateListByK obj = new RotateListByK();
        ListNode node = obj.rotateRight(head, 3);

        while (node != null) {
            System.out.println(node.val);
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
