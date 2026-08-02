package com.example.demo.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoom {
    public int getNumberOfMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int maxRooms = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int[] interval : intervals) {
            while (!pq.isEmpty() && interval[0] >= pq.peek()) {
                pq.poll();
            }
            pq.offer(interval[1]);
            //     maxRooms = Math.max(maxRooms, pq.size());
        }
        // return maxRooms;
        return pq.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        MeetingRoom mr = new MeetingRoom();
        System.out.println(mr.getNumberOfMeetingRooms(intervals));

    }
}
