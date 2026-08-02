package com.example.demo.heap;

import com.example.demo.ListCycle;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKLists {

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }

        List<ListNode> nonEmpty = new ArrayList<>();
        for (ListNode head : lists) {
            if (head != null) {
                nonEmpty.add(head);
            }
        }
        if (nonEmpty.isEmpty()) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode head : nonEmpty) {
            heap.offer(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                heap.offer(node.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        List<ListNode> lists = new ArrayList<>();
        // [3,4,6],[2,3,5],[-1,6]

        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(6);

        lists.add(head1);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(5);
        lists.add(head2);

        ListNode head3 = new ListNode(-1);
        head3.next = new ListNode(6);
        lists.add(head3);
        MergeKLists mkl = new MergeKLists();
        ListNode res = mkl.mergeKLists(lists);

        mkl.printList(res);

    }

    public void printList(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
