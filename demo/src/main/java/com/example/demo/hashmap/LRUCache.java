package com.example.demo.hashmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LRUCache {
    int capacity;
    Map<Integer, ListNode> dic;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!dic.containsKey(key)) {
            return -1;
        }

        ListNode node = dic.get(key);
        removeNode(node);
        addToHead(node, head);
        return node.val;
    }

    public void put(int key, int value) {
        if (dic.containsKey(key)) {
            ListNode oldNode = dic.get(key);
            removeNode(oldNode);
        }

        ListNode node = new ListNode(key, value);
        dic.put(key, node);
        addToHead(node, head);

        if (dic.size() > capacity) {
            // ListNode nodeToDelete = head.next;
            ListNode nodeToDelete = removeTail(tail);
            dic.remove(nodeToDelete.key);
        }
    }

    private static void addToHead(ListNode node, ListNode head) {
        // Add node right after head (most recently used position)
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private static void removeNode(ListNode node) {
        // Remove node from doubly linked list
        ListNode prevNode = node.prev;
        ListNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private static ListNode removeTail(ListNode node) {
        // Remove least recently used node (before tail)
        ListNode lruNode = node.prev;
        removeNode(lruNode);
        return lruNode;
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
        int capacity = 2;
        LRUCache cache = new LRUCache(capacity);

        List<List<Object>> operations = List.of(List.of("put", 1, 1), List.of("put", 2, 2), List.of("get", 1),
                List.of("put", 3, 3), List.of("get", 2), List.of("put", 4, 4), List.of("get", 1), List.of("get", 3), List.of("get", 4));

        for (List<Object> op : operations) {
            if (op.get(0).equals("get")) {
                System.out.println("" + cache.get((Integer) op.get(1)));
            } else if (op.get(0).equals("put")) {
                cache.put((Integer) op.get(1), (Integer) op.get(2));
            }
        }

    }

}

