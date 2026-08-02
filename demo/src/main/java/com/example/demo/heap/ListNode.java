package com.example.demo.heap;

import com.example.demo.ListCycle;
import lombok.Data;

@Data
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
