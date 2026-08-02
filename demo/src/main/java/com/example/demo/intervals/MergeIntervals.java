package com.example.demo.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0];
        int end = intervals[0][1];

        List<int[]> res = new ArrayList<>();
        int i = 1;
        int j = intervals.length;

        while (i < j) {
            if (intervals[i][0] <= end) {
                start = Math.min(intervals[i][0], start);
                end = Math.max(intervals[i][1], end);
            } else {
                res.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
            i++;
        }
        res.add(new int[]{start, end});
        return res.toArray(int[][]::new);
    }

    public int[][] merge(int[][] intervals) {

        if (intervals == null ||
                intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals,
                (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        int[] current = intervals[0];

        merged.add(current);

        for (int i = 1; i < intervals.length; i++) {

            int[] next = intervals[i];

            if (next[0] <= current[1]) {

                current[1] =
                        Math.max(current[1], next[1]);

            } else {

                current = next;
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{3, 5}, {1, 4}, {7, 9}, {6, 8}};
        int[][] intervals1 = {{1, 2}, {3, 7}, {4, 6}, {8, 9}, {10, 15}};

        MergeIntervals am = new MergeIntervals();

//        int[][] res1 = am.mergeIntervals(intervals);
//        for (int[] it : res1) {
//            System.out.println(Arrays.toString(it));
//        }
        System.out.println("***************");
        int[][] res2 = am.mergeIntervals(intervals1);
        for (int[] it : res2) {
            System.out.println(Arrays.toString(it));
        }

        System.out.println("***************");

        int[][] res = am.merge(intervals);
        for (int[] it : res) {
            System.out.println(Arrays.toString(it));
        }

    }

}
