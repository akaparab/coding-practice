package com.example.demo.hashmap;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    private final int capacity;
    private int minFrequency;

    // key -> Node
    private final Map<Integer, Node> keyToNode;

    // frequency -> DLL containing nodes with that frequency
    private final Map<Integer, DoublyLinkedList> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        this.keyToNode = new HashMap<>();
        this.freqToList = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyToNode.get(key);

        if (node == null) {
            return -1;
        }

        increaseFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (capacity == 0) {
            return;
        }

        // Key already exists
        if (keyToNode.containsKey(key)) {

            Node node = keyToNode.get(key);

            node.value = value;

            increaseFrequency(node);

            return;
        }

        // Cache is full
        if (keyToNode.size() == capacity) {

            DoublyLinkedList minList = freqToList.get(minFrequency);

            Node evicted = minList.removeLast();

            keyToNode.remove(evicted.key);
        }

        // Add new node with frequency = 1
        Node node = new Node(key, value);

        keyToNode.put(key, node);

        freqToList
                .computeIfAbsent(1, f -> new DoublyLinkedList())
                .addFirst(node);

        minFrequency = 1;
    }

    private void increaseFrequency(Node node) {

        int oldFrequency = node.frequency;

        DoublyLinkedList oldList = freqToList.get(oldFrequency);

        oldList.remove(node);

        // If this was the last node with the minimum frequency
        if (oldFrequency == minFrequency && oldList.isEmpty()) {
            minFrequency++;
        }

        node.frequency++;

        freqToList
                .computeIfAbsent(node.frequency, f -> new DoublyLinkedList())
                .addFirst(node);
    }

    // ---------------- Node ----------------

    private static class Node {

        int key;
        int value;
        int frequency;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    // -------- Doubly Linked List --------

    private static class DoublyLinkedList {

        Node head;
        Node tail;

        DoublyLinkedList() {

            head = new Node(-1, -1);
            tail = new Node(-1, -1);

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {

            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null;
        }

        Node removeLast() {

            if (isEmpty()) {
                return null;
            }

            Node node = tail.prev;

            remove(node);

            return node;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }

    public static void main(String[] args) {

        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        System.out.println(cache.get(1)); // 1
        // Frequency:
        // key 1 -> 2
        // key 2 -> 1

        cache.put(3, 3);
        // Cache is full.
        // key 2 has lowest frequency, so key 2 is evicted.

        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(1)); // 1

        cache.put(4, 4);
        // Frequencies:
        // key 1 -> 3
        // key 3 -> 2
        // key 4 -> 1
        //
        // No eviction happened here because capacity was already
        // maintained after inserting 3.

        System.out.println(cache.get(1)); // 1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }

}
