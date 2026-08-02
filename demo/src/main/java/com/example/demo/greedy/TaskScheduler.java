package com.example.demo.greedy;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFeqCount = 0;

        for (int i = 0; i < tasks.length; i++) {
            char ch = tasks[i];
            freq[ch - 'A']++;
            maxFeqCount = Math.max(maxFeqCount, freq[ch - 'A']);
        }
        int count = 0;

        for (int i = 0; i < 26; i++) {
            if (maxFeqCount == freq[i]) {
                count++;
            }
        }

        System.out.println("maxFeqCount : " + maxFeqCount + " N : " + n + " count: " + count);
        return ((maxFeqCount - 1) * (n + 1)) + count;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        TaskScheduler ts = new TaskScheduler();
        System.out.println(ts.leastInterval(tasks, n));
    }
}
