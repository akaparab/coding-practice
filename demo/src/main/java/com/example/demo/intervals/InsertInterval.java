package com.example.demo.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    public int[][] insertIntervals(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[1];

        List<int[]> res = new ArrayList<>();
        int i = 0;
        int j = intervals.length;

        while (i < j && intervals[i][1] < start) {
            res.add(intervals[i]);
            i++;
        }

        while (i < j && intervals[i][0] < end) {
            start = Math.min(intervals[i][0], start);
            end = Math.max(intervals[i][1], end);
            i++;

        }
        res.add(new int[]{start, end});
        while (i < j) {
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[][] intervals1 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] interval = {2, 5};
        int[] interval1 = {4, 8};

        InsertInterval am = new InsertInterval();

        int[][] res = am.insertIntervals(intervals, interval);
        int[][] res1 = am.insertIntervals(intervals1, interval1);
        for (int[] it : res) {
            System.out.println(Arrays.toString(it));
        }

    }
}
