package com.example.demo.intervals;

import java.util.Arrays;

public class AttendMeeting {
    public Boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0) return true;
        if (intervals.length == 1) return true;
        // sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (prevEnd > intervals[i][0]) {
                return false;
            }
            prevEnd = intervals[i][1];
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = {{10, 12}, {6, 9}, {13, 15}};
        AttendMeeting am = new AttendMeeting();

        System.out.println(am.canAttendMeetings(intervals));

    }
}
