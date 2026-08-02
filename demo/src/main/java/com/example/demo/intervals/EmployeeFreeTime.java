package com.example.demo.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeFreeTime {
    public int[][] employeeFreeTime(int[][][] schedule) {
        int[][] intervals = Arrays.stream(schedule)
                .flatMap(Arrays::stream)
                .toArray(int[][]::new);
        if (intervals.length == 0) {
            return new int[0][];
        }
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> res = new ArrayList<>();

        int end = intervals[0][1];

        int i = 1;
        int j = intervals.length;
        while (i < j) {
            if (intervals[i][0] > end) {
                res.add(new int[]{end, intervals[i][0]});
            }
            end = Math.max(end, intervals[i][1]);
            i++;
        }

        return res.toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][][] intervals = {{{2, 4}, {7, 10}}, {{1, 5}}, {{6, 9}}};
        EmployeeFreeTime am = new EmployeeFreeTime();

        int[][] res1 = am.employeeFreeTime(intervals);
        for (int[] it : res1) {
            System.out.println(Arrays.toString(it));
        }

    }
}
