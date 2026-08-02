package com.example.demo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public static boolean canFinish(
            int numCourses,
            int[][] prerequisites) {

        List<List<Integer>> graph =
                new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {

            int course = pre[0];
            int prereq = pre[1];

            graph.get(prereq).add(course);

            indegree[course]++;
        }

        Queue<Integer> queue =
                new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {

            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completed = 0;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            completed++;

            for (int next : graph.get(current)) {

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return completed == numCourses;
    }

    public static List<Integer> getCompletedCourses(
            int numCourses,
            int[][] prerequisites) {

        List<List<Integer>> graph =
                new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {

            int course = pre[0];
            int prereq = pre[1];

            graph.get(prereq).add(course);

            indegree[course]++;
        }

        Queue<Integer> queue =
                new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {

            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completed = 0;
        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()) {

            int current = queue.poll();

            res.add(current);

            for (int next : graph.get(current)) {

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prereq = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[][] prereq1 = {{1, 0}, {2, 0}, {3, 1}, {3, 0}};

        System.out.println(CourseSchedule.canFinish(numCourses, prereq));
        System.out.println(CourseSchedule.getCompletedCourses(numCourses, prereq));

        System.out.println(CourseSchedule.canFinish(numCourses, prereq1));
        System.out.println(CourseSchedule.getCompletedCourses(numCourses, prereq1));
    }
}
