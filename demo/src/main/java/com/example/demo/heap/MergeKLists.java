package com.example.demo.heap;

import com.example.demo.ListCycle;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<Integer> mergeKSortedLists(
            List<List<Integer>> lists) {

        PriorityQueue<Node> minHeap =
                new PriorityQueue<>(
                        Comparator.comparingInt(n -> n.value)
                );

        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.offer(
                        new Node(lists.get(i).get(0), i, 0)
                );
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {

            Node node = minHeap.poll();

            result.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < lists.get(node.listIndex).size()) {

                minHeap.offer(
                        new Node(
                                lists.get(node.listIndex).get(nextIndex),
                                node.listIndex,
                                nextIndex
                        )
                );
            }
        }

        return result;
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

        List<List<Integer>> list = List.of(
                List.of(1, 4, 5),
                List.of(1, 3, 4),
                List.of(2, 6)
        );
        System.out.println(mkl.mergeKSortedLists(list));

    }

    public void printList(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    static class Node {
        int value;
        int listIndex;
        int elementIndex;

        Node(int value, int listIndex, int elementIndex) {
            this.value = value;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
    }
}
