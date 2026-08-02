package com.example.demo.heap;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Data
@AllArgsConstructor
class ElementCount {
    int element;
    int counter;
}

public class TopKFrequentElements {

    public List<Integer> getTopKFrequentElements(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        PriorityQueue<ElementCount> pq = new PriorityQueue<>((a, b) -> a.counter - b.counter);
        for (int i = 0; i < nums.length; i++) {
            //map.put(nums[i], map.computeIfAbsent(nums[i], val -> 0) + 1);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (count < k) {
                pq.offer(new ElementCount(entry.getKey(), entry.getValue()));
                count++;
            } else {
                if (entry.getValue() > pq.peek().counter) {
                    pq.poll();
                    pq.offer(new ElementCount(entry.getKey(), entry.getValue()));
                }
            }
        }

        while (!pq.isEmpty()) {
            list.add(pq.poll().element);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        TopKFrequentElements te = new TopKFrequentElements();
        System.out.println(te.getTopKFrequentElements(nums, k).toString());

    }
}
