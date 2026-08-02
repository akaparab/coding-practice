package com.example.demo.intervals;

import java.util.Arrays;

public class NonOverlappingIntervals {

    public Integer nonOverlappingIntervals1(int[][] intervals) {
        if (intervals.length == 0) return 0;
        if (intervals.length == 1) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 0;
        int i = 1;
        int start = intervals[0][0];
        int end = intervals[0][1];

        while (i < intervals.length) {
            if ((end > intervals[i][0])) {
                count++;
            } else {
                end = Math.max(end, intervals[i][1]);
            }
            i++;
        }
        return count;
    }

    public int nonOverlappingIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            // Non-overlapping interval found
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {5, 8}, {4, 10}, {11, 13}};
        int[][] intervals1 = {{1, 2}, {1, 2}, {1, 2}};
        int[][] intervals2 = {{1, 5}, {2, 3}, {3, 4}, {4, 6}};

        NonOverlappingIntervals nol = new NonOverlappingIntervals();
        System.out.println(nol.nonOverlappingIntervals(intervals));
        System.out.println(nol.nonOverlappingIntervals(intervals1));
        System.out.println(nol.nonOverlappingIntervals(intervals2));

        System.out.println(nol.nonOverlappingIntervals1(intervals));
        System.out.println(nol.nonOverlappingIntervals1(intervals1));
        System.out.println(nol.nonOverlappingIntervals1(intervals2));

    }
}
